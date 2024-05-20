/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.uncuyo.dbapp.view;

import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author tomas
 */
public class EliminarUsuario extends javax.swing.JFrame {

    /**
     * Creates new form EliminarUsuario
     */
    public EliminarUsuario() {
        initComponents();
        ((AbstractDocument) txtCuilUsuario.getDocument()).setDocumentFilter(new DocumentFilter() {
           @Override
           public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
               if (string == null) {
                   return;
               }

               StringBuilder sb = new StringBuilder(txtCuilUsuario.getText());
               sb.insert(offset, string);
               if (sb.toString().matches("\\d{0,11}")) {
                   super.insertString(fb, offset, string, attr);
               }
           }

           @Override
           public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
               if (text == null) {
                   return;
               }

               StringBuilder sb = new StringBuilder(txtCuilUsuario.getText());
               sb.replace(offset, offset + length, text);
               if (sb.toString().matches("\\d{0,11}")) {
                   super.replace(fb, offset, length, text, attrs);
               }
           }
       });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCuilUsuario = new javax.swing.JTextField();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cuil del usuario a eliminar de la base:");

        txtCuilUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuilUsuarioActionPerformed(evt);
            }
        });

        Aceptar.setText("Aceptar");
        Aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AceptarMouseClicked(evt);
            }
        });

        Cancelar.setText("Cancelar");
        Cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCuilUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancelar))
                    .addComponent(jLabel1))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtCuilUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Aceptar)
                    .addComponent(Cancelar))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCuilUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuilUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuilUsuarioActionPerformed

    private void AceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AceptarMouseClicked
        try {
        String cuil = txtCuilUsuario.getText();
        mainframe.eliminarUsuario(cuil);
        this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error con el usuario a eliminar, reintente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AceptarMouseClicked

    private void CancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelarMouseClicked
        this.dispose();
    }//GEN-LAST:event_CancelarMouseClicked

    public void setMainframe(MainFrame mainframe) {
        this.mainframe = mainframe;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EliminarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EliminarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EliminarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EliminarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EliminarUsuario().setVisible(true);
            }
        });
    }

    private MainFrame mainframe;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtCuilUsuario;
    // End of variables declaration//GEN-END:variables
}
