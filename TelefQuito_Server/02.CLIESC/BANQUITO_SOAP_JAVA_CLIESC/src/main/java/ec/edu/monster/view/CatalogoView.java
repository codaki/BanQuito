/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.monster.view;

import ec.edu.monster.controller.TelefonoController;
import ec.edu.monster.ws.Telefonos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author JOSE
 */
public final class CatalogoView extends javax.swing.JFrame {

    public TelefonoController controller;
    /**
     * Creates new form CatalogoView
     */
    public CatalogoView() {
        controller = new TelefonoController();
        initComponents();
        cargarCatalogo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTab = new javax.swing.JButton();
        btnCrud = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTab.setBackground(new java.awt.Color(0, 153, 153));
        btnTab.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnTab.setForeground(new java.awt.Color(255, 255, 255));
        btnTab.setText("Consultar créditos activos");
        btnTab.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));
        btnTab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabActionPerformed(evt);
            }
        });
        getContentPane().add(btnTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 310, 40));

        btnCrud.setBackground(new java.awt.Color(0, 153, 153));
        btnCrud.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnCrud.setForeground(new java.awt.Color(255, 255, 255));
        btnCrud.setText("Agregar Teléfono");
        btnCrud.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));
        btnCrud.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCrud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrud, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 310, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 680, 450));

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondo2.png"))); // NOI18N
        fondo.setText("jLabel6");
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabActionPerformed
        TablaView tabla = new TablaView();
        tabla.setVisible(true);
//        MovimientoController controller = new MovimientoController();
//        String cuenta = txtCuenta.getText();
//
//        if (cuenta == null || cuenta.trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de cuenta válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        controller.cargarMovimientos(cuenta, this);
    }//GEN-LAST:event_btnTabActionPerformed

    private void btnCrudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudActionPerformed
        CrudView crud = new CrudView();
        crud.setVisible(true);
    }//GEN-LAST:event_btnCrudActionPerformed

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
            java.util.logging.Logger.getLogger(CatalogoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CatalogoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CatalogoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatalogoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CatalogoView().setVisible(true);
            }
        });
    }

    public JPanel crearCelda(String codigo, String marca, String modelo, String disponible, String precio) {
        JPanel panelCelda = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(bg);
                repaint();
            }
        };

        panelCelda.setLayout(new BorderLayout());
        panelCelda.setBackground(new Color(214, 209, 246));
        panelCelda.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panelCelda.setMaximumSize(new Dimension(550, 150));

        // Texto principal
        JLabel labelModelo = new JLabel(modelo);
        JLabel lblMarca = new JLabel(marca);
        JLabel labelImporte = new JLabel(precio);
        labelModelo.setFont(new Font("Arial", Font.BOLD, 14));
        labelImporte.setFont(new Font("Arial", Font.BOLD, 16));
        labelImporte.setForeground(new Color(45, 150, 255));
        
        // Botón Editar
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new java.awt.Color(0, 153, 153));
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));
        btnEditar.addActionListener(e -> {
            // Acción para editar
                Telefonos telefono = controller.obtenerPorId(Integer.parseInt(codigo));
                CrudView crudView = new CrudView(telefono);
                crudView.setVisible(true);

            System.out.println("Editar: " + codigo);
        });

        // Botón Activar/Desactivar
        String textoBotonActivar = disponible.equals("1") ? "Desactivar" : "Activar";
        JButton btnActivar = new JButton(textoBotonActivar);
        btnActivar.setBackground(new java.awt.Color(0, 153, 153));
        btnActivar.setForeground(new java.awt.Color(255, 255, 255));
        btnActivar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));
        btnActivar.addActionListener(e -> {
            // Acción para activar/desactivar
            System.out.println(textoBotonActivar + ": " + codigo);
        });

        // Botón Vender
        JButton btnVender = new JButton("Vender");
        btnVender.setBackground(new java.awt.Color(0, 153, 153));
        btnVender.setForeground(new java.awt.Color(255, 255, 255));
        btnVender.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true)); 
        btnVender.addActionListener(e -> {
            // Acción para vender
            System.out.println("Vender: " + codigo);
            controller.activarpantallaVenta(codigo);
        }); 

        // Contenedor de datos
        JPanel datosPanel = new JPanel(new GridLayout(3, 2, 5, 15)); // Espacio entre filas y columnas
        datosPanel.setBackground(new Color(214, 209, 246));
        datosPanel.add(labelModelo);
        datosPanel.add(btnActivar);
        
        datosPanel.add(lblMarca);
        datosPanel.add(btnEditar);
        
        datosPanel.add(labelImporte);   
        datosPanel.add(btnVender);
        

        // Agregar al panel de celda
        panelCelda.add(datosPanel, BorderLayout.CENTER);

        // Agregar borde con espacio entre celdas
        panelCelda.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10), // Espaciado entre celdas
                BorderFactory.createLineBorder(new Color(180, 180, 220), 2) // Borde de color claro
        ));

        return panelCelda;
    }

    public void cargarCatalogo() {
        controller.cargarTelefonos(this);
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrud;
    private javax.swing.JButton btnTab;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}