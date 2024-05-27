package com.uncuyo.dbapp.pdf;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ExportPDF {

    public ExportPDF() {
    }
    
    public void exportarPDF() {
        String url = "jdbc:postgresql://localhost:5432/uncuyo";
        String user = "administrador";
        String password = "1234";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PDDocument document = new PDDocument();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, "public", "%", new String[] { "TABLE" });
            List<String> tableNames = new ArrayList<>();

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                tableNames.add(tableName);
            }

            // Procesar cada tabla y agregar sus datos al documento PDF
            for (String tableName : tableNames) {
                PDPage page = new PDPage();
                document.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
                int y = 700;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, y);
                contentStream.showText("Tabla: " + tableName.toUpperCase());
                contentStream.endText();
                int rowCount = 0;
                int maxRowsPerPage = 33;
                y -= 20;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
                
                while (rs.next()) {
                    rowCount++;
                 
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        String columnName = rs.getMetaData().getColumnName(i);
                        String columnValue = rs.getString(i);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(50, y);
                        contentStream.showText(columnName + ": " + columnValue);
                        contentStream.endText();
                        y -= 15;
                        rowCount = rowCount + 1;
                        if (rowCount > maxRowsPerPage) {
                        contentStream.beginText();
                        contentStream.endText();
                        contentStream.close();
                        document.addPage(new PDPage());
                        page = document.getPage(document.getNumberOfPages() - 1);
                        contentStream = new PDPageContentStream(document, page);
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
                        y = 700;
                        rowCount = 1;
                    }
                    }
                    
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, y);
                    contentStream.showText("-----------------");
                    contentStream.endText();
                    y -= 15;
                    rowCount = rowCount + 1; 
                }

                rs.close();
                stmt.close();
                contentStream.beginText();
                contentStream.endText();
                contentStream.close();
            }

            document.save("datosTablas.pdf");
            document.close();

            System.out.println("Archivo PDF generado exitosamente");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean exportTablesPDF(JTable jTable1, JTable jTable2, String pdfNewFile) {
        try {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }
            document.open();
            document.addTitle("Tablas Usuarios y Dietas");
            document.addSubject("HealthyLife");
            document.addKeywords("Java, PDF");
            document.addAuthor("HealthyLife");
            document.addCreator("HealthyLife");

            addTablePDF(document, jTable1, "Usuarios");
            addTablePDF(document, jTable2, "Dietas");

            document.close();
            return true;
        } catch (DocumentException documentException) {
            return false;
        }
    }

    private void addTablePDF(Document document, JTable jTable, String title) throws DocumentException {
        Font fontT = new Font(FontFamily.HELVETICA, 10, Font.BOLD);
        Font font = new Font(FontFamily.HELVETICA, 7, Font.BOLD);
        Anchor anchor = new Anchor(title, fontT);
        anchor.setName(title);

        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Tabla:", fontT);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph(""));

        PdfPTable table = new PdfPTable(jTable.getColumnCount());

        PdfPCell columnHeader;
        for (int column = 0; column < jTable.getColumnCount(); column++) {
            columnHeader = new PdfPCell(new Phrase(jTable.getColumnName(column), font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
        }
        table.setHeaderRows(1);

        for (int row = 0; row < jTable.getRowCount(); row++) {
            for (int column = 0; column < jTable.getColumnCount(); column++) {
                PdfPCell cell = new PdfPCell(new Phrase(jTable.getValueAt(row, column).toString(), font)); // Usamos la fuente más pequeña
                table.addCell(cell);
            }
        }
        subCatPart.add(table);

        document.add(catPart);
    }
}
