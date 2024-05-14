package com.uncuyo.dbapp.pdf;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ExportPDF {

    public ExportPDF() {
    }
    
    public void exportarPDF() {
        String url = "jdbc:postgresql://localhost:5432/clon";
        String user = "postgres";
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
}
