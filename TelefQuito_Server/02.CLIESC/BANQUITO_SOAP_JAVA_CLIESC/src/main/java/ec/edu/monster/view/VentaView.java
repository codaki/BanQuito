/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.monster.view;

import ec.edu.monster.controller.VentaController;
import ec.edu.monster.service.TelefonoService;
import ec.edu.monster.ws.Carrito;
import ec.edu.monster.ws.TelefonoCarrito;
import ec.edu.monster.ws.Telefonos;
import jakarta.activation.DataHandler;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author JOSE
 */
public class VentaView extends javax.swing.JFrame {

    private Telefonos telf;
    private Carrito carrito;
    private double total;

    /**
     * Creates new form VentaView
     */
    public VentaView() {
        initComponents();
        configureTransactionType();
        total = 0;
        calcularTotal();
        configureTransactionType();
    }

    public VentaView(Carrito carrito) {
        initComponents();
        this.carrito = carrito;
        total = 0;
        calcularTotal();
        configureTransactionType();
    }

    public Carrito getCarrito() {
        return this.carrito;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblTitulo2 = new javax.swing.JLabel();
        lblTitulo4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        messageLabel = new javax.swing.JLabel();
        lblCtaOrigen1 = new javax.swing.JLabel();
        lblCtaOrigen4 = new javax.swing.JLabel();
        cbPago = new javax.swing.JComboBox<>();
        lbldiferido = new javax.swing.JLabel();
        txtMeses = new javax.swing.JTextField();
        lblmeses = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblTitulo26 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblTitDescuento = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        btnPagar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        txtCedula = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblTitulo.setText("TelfQuito");

        lblTitulo2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblTitulo2.setText("La mejor tienda de celulares del país");

        lblTitulo4.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblTitulo4.setText("Carrito de compras");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTitulo2)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTitulo4)
                        .addGap(69, 69, 69))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTitulo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo4)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 590, 90));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblCtaOrigen1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblCtaOrigen1.setForeground(new java.awt.Color(0, 102, 102));
        lblCtaOrigen1.setText("Cédula Cliente");

        lblCtaOrigen4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblCtaOrigen4.setForeground(new java.awt.Color(0, 102, 102));
        lblCtaOrigen4.setText("Tipo de Pago");

        cbPago.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        cbPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Crédito Directo" }));
        cbPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPagoActionPerformed(evt);
            }
        });

        lbldiferido.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lbldiferido.setForeground(new java.awt.Color(0, 102, 102));
        lbldiferido.setText("Diferido a");

        txtMeses.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtMeses.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        txtMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesesActionPerformed(evt);
            }
        });

        lblmeses.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblmeses.setText("meses");

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));

        lblTitulo26.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblTitulo26.setText("TOTAL");

        lblTotal.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblTotal.setText("420.62");

        lblTitDescuento.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblTitDescuento.setText("descuento");

        lblDescuento.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblDescuento.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblTitulo26, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTitDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitDescuento)
                    .addComponent(lblDescuento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblTitulo26, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPagar.setBackground(new java.awt.Color(0, 153, 153));
        btnPagar.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(255, 255, 255));
        btnPagar.setText("Pagar");
        btnPagar.setToolTipText("");
        btnPagar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));
        btnPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        lblMensaje.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        btnRegresar.setBackground(new java.awt.Color(153, 153, 153));
        btnRegresar.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.setToolTipText("");
        btnRegresar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));
        btnRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnVerificar.setBackground(new java.awt.Color(0, 153, 153));
        btnVerificar.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnVerificar.setForeground(new java.awt.Color(255, 255, 255));
        btnVerificar.setText("Verificar crédito");
        btnVerificar.setToolTipText("");
        btnVerificar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));
        btnVerificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel3);

        txtCedula.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtCedula.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(messageLabel)
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(273, 273, 273)
                                        .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbldiferido)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblmeses, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCtaOrigen4)
                                .addGap(18, 18, 18)
                                .addComponent(cbPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(lblCtaOrigen1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCtaOrigen4)
                    .addComponent(cbPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCtaOrigen1)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldiferido)
                    .addComponent(txtMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmeses)
                    .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 740, 430));

        txtBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 243, 33));

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondo2.png"))); // NOI18N
        fondo.setText("fondo");
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesesActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed

        String cedula = txtBuscar.getText();
        String plazo = txtMeses.getText();
        String selectedTransaction = (String) cbPago.getSelectedItem();

        VentaController controller = new VentaController();
        calcularTotal();
        if ("Efectivo".equals(selectedTransaction)) {
            controller.ventaEfectivo(this.carrito, cedula, this);
        } else {
            controller.ventaCredito(this.carrito, cedula, plazo, this);
        }

    }//GEN-LAST:event_btnPagarActionPerformed

    private void cbPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPagoActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

        CatalogoView cat = new CatalogoView(this.carrito);
        cat.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        int plazoInt = Integer.parseInt(txtMeses.getText());

        if (plazoInt < 3 || plazoInt > 18) {
            lblMensaje.setText("Seleccione un plazo válido (3-18)");
        } else {
            lblMensaje.setText("Plazo válido");
            btnPagar.setEnabled(true);
        }
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

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
            java.util.logging.Logger.getLogger(VentaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentaView().setVisible(true);
            }
        });
    }

    private void configureTransactionType() {
        updateVentaView("Efectivo");

        cbPago.addActionListener(evt -> {
            String selectedTransaction = (String) cbPago.getSelectedItem();
            updateVentaView(selectedTransaction);
        });
    }

    public JPanel crearCeldaVenta(int codigo, String modelo, String marca, double precio, int cantidad) {
        JPanel panelCelda = new JPanel(new GridLayout(1, 5));
        panelCelda.setBackground(new Color(240, 240, 240));
        panelCelda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiquetas
        JLabel lblModelo = new JLabel(modelo);
        lblModelo.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblMarca = new JLabel(marca);
        JLabel lblPrecio = new JLabel(String.format("%.2f", precio));
        JLabel lblSubtotal = new JLabel(String.format("%.2f", precio * cantidad));

        // Botones para cantidad
        JPanel panelCantidad = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnMenos = new JButton("-");
        JLabel lblCantidad = new JLabel(String.valueOf(cantidad));
        JButton btnMas = new JButton("+");

        panelCantidad.add(btnMenos);
        panelCantidad.add(lblCantidad);
        panelCantidad.add(btnMas);

        // Listeners para botones
        btnMenos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("menos codigo " + codigo);
                for (TelefonoCarrito telCarrito : carrito.getTelefonoCarrito()) {
                    if (telCarrito.getTelefonoId() == codigo) {
                        int cantidadActual = telCarrito.getCantidad();
                        if (cantidadActual > 1) {
                            cantidadActual--;
                            lblSubtotal.setText(String.valueOf(cantidadActual * precio));

                            lblCantidad.setText(String.valueOf(cantidadActual));
                            telCarrito.setCantidad(cantidadActual);
                            calcularTotal();
                            String selectedTransaction = (String) cbPago.getSelectedItem();
                            updateVentaView(selectedTransaction);
                        }
                        break;
                    }
                }

            }
        });

        btnMas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (TelefonoCarrito telCarrito : carrito.getTelefonoCarrito()) {
                    if (telCarrito.getTelefonoId() == codigo) {
                        System.out.println("más codigo " + codigo);
                        int cantidadActual = telCarrito.getCantidad();
                        cantidadActual++;
                        
                        lblSubtotal.setText(String.valueOf(cantidadActual * precio));
                        lblCantidad.setText(String.valueOf(cantidadActual));
                        telCarrito.setCantidad(cantidadActual);

                        calcularTotal();
                        String selectedTransaction = (String) cbPago.getSelectedItem();
                        updateVentaView(selectedTransaction);

                        break;
                    }
                }
            }
        });

        // Añadir componentes al panel
        panelCelda.add(lblModelo);
        panelCelda.add(lblMarca);
        panelCelda.add(lblPrecio);
        panelCelda.add(panelCantidad);
        panelCelda.add(lblSubtotal);

        return panelCelda;
    }

    public JScrollPane getjScrollPane1() {
        return this.jScrollPane1;
    }

    public void calcularTotal() {
        total = 0;
        TelefonoService telService = new TelefonoService();
        List<Telefonos> telefonos = telService.obtenerTelefonos();

        for (TelefonoCarrito telCarrito : carrito.getTelefonoCarrito()) {
            for (Telefonos telf : telefonos) {
                if (telCarrito.getTelefonoId() == telf.getCodTelefono()) {
                    double subtotal = telCarrito.getCantidad() * telf.getPrecio();
                    total += subtotal;
                }

            }
        }
    }

    private void updateVentaView(String pagoType) {
        switch (pagoType) {
            case "Efectivo":
                lblTitDescuento.setText("Descuento 42%");
                lbldiferido.setVisible(false);
                txtMeses.setVisible(false);
                lblmeses.setVisible(false);
                btnVerificar.setVisible(false);
                lblMensaje.setVisible(false);
                btnPagar.setEnabled(true);

                // Crear un formateador para dos decimales
                DecimalFormat df = new DecimalFormat("#.00");
                double precioD = total;
                double descuento = (precioD * 42) / 100;
                double pagar = precioD - descuento;

                // Formatear los valores
                lblDescuento.setText(df.format(descuento));
                lblTotal.setText(df.format(pagar));
                break;
            case "Crédito Directo":
                lblTitDescuento.setText("Descuento 0%");
                lbldiferido.setVisible(true);
                txtMeses.setVisible(true);
                lblmeses.setVisible(true);
                btnVerificar.setVisible(true);
                lblMensaje.setVisible(true);
                btnPagar.setEnabled(false);
                lblDescuento.setText("0");
                lblTotal.setText(Double.toString(total));
                break;
            default:
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JComboBox<String> cbPago;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCtaOrigen1;
    private javax.swing.JLabel lblCtaOrigen4;
    public javax.swing.JLabel lblDescuento;
    public javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitDescuento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo26;
    private javax.swing.JLabel lblTitulo4;
    public javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lbldiferido;
    private javax.swing.JLabel lblmeses;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula;
    public javax.swing.JTextField txtMeses;
    // End of variables declaration//GEN-END:variables
}
