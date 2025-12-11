package view;

import bean.RpsProdutos;
import bean.RpsVendasProdutos;
import dao.ProdutosDAO;
import java.util.List;
import tools.Util;

public class JDlgVendasProdutos extends javax.swing.JDialog {

    JDlgVendas jDlgVendas;
    boolean incluir;

    public JDlgVendasProdutos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        iniciarRelogio("Vendas produtos"); // coloque o nome do usuário logado aqui
        setLocationRelativeTo(null);
        rps_jTxtQuantidade.setText("1");
        ProdutosDAO produtosDAO = new ProdutosDAO();
        List lista = (List) produtosDAO.listAll();
        for (Object object : lista) {
            rps_jCboProdutos.addItem((RpsProdutos) object);
        }
        Util.habilitar(false, rps_jTxtValorUnitario, rps_jTxtTotal);
    }

    private void iniciarRelogio(String nomeUsuario) {
        javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
            java.text.SimpleDateFormat sdfHora = new java.text.SimpleDateFormat("HH:mm:ss");
            java.text.SimpleDateFormat sdfData = new java.text.SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy");

            String hora = sdfHora.format(new java.util.Date());
            String data = sdfData.format(new java.util.Date());

            // Capitaliza o dia da semana
            data = data.substring(0, 1).toUpperCase() + data.substring(1);

            // Define título com usuário, data e hora
            setTitle(nomeUsuario + " | " + data + " | " + hora);
        });
        timer.start();
    }

    public void setTelaAnterior(JDlgVendas jDlgVendas, RpsVendasProdutos rpsVendasProdutos) {
        this.jDlgVendas = jDlgVendas;
        if (rpsVendasProdutos != null) {
            incluir = false;
            rps_jCboProdutos.setSelectedItem(rpsVendasProdutos.getRpsProdutos());
            rps_jTxtQuantidade.setText(Util.intToStr(rpsVendasProdutos.getRpsQuantidade()));
        } else {
            incluir = true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        rps_jCboProdutos = new javax.swing.JComboBox<RpsProdutos>();
        jLabel2 = new javax.swing.JLabel();
        rps_jTxtQuantidade = new javax.swing.JTextField();
        rps_jTxtValorUnitario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rps_jTxtTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rps_jBtnOk = new javax.swing.JButton();
        rps_jBtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Produtos");

        rps_jCboProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jCboProdutosActionPerformed(evt);
            }
        });

        jLabel2.setText("Quantidade");

        rps_jTxtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rps_jTxtQuantidadeKeyReleased(evt);
            }
        });

        rps_jTxtValorUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jTxtValorUnitarioActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor Unitário");

        jLabel4.setText("Total");

        rps_jBtnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        rps_jBtnOk.setText("OK");
        rps_jBtnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnOkActionPerformed(evt);
            }
        });

        rps_jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        rps_jBtnCancelar.setText("Cancelar");
        rps_jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(rps_jCboProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rps_jBtnOk)
                                .addGap(40, 40, 40)
                                .addComponent(rps_jBtnCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(rps_jTxtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rps_jTxtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rps_jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rps_jTxtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rps_jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rps_jCboProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rps_jTxtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jBtnOk)
                    .addComponent(rps_jBtnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rps_jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnOkActionPerformed
        RpsVendasProdutos rpsVendasProdutos = new RpsVendasProdutos();
        rpsVendasProdutos.setRpsProdutos((RpsProdutos) rps_jCboProdutos.getSelectedItem());
        rpsVendasProdutos.setRpsQuantidade(Util.strToInt(rps_jTxtQuantidade.getText()));
        rpsVendasProdutos.setRpsValorUnitario(Util.strToDouble(rps_jTxtValorUnitario.getText()));
        if (incluir == true) {
            jDlgVendas.controllerVenProd.addBean(rpsVendasProdutos);
        } else {
            jDlgVendas.controllerVenProd.removeBean(jDlgVendas.getjTable1().getSelectedRow());
            jDlgVendas.controllerVenProd.addBean(rpsVendasProdutos);
        }
        setVisible(false);
    }//GEN-LAST:event_rps_jBtnOkActionPerformed

    private void rps_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnCancelarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_rps_jBtnCancelarActionPerformed

    private void rps_jTxtValorUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jTxtValorUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jTxtValorUnitarioActionPerformed

    private void rps_jTxtQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rps_jTxtQuantidadeKeyReleased
        if (rps_jTxtQuantidade.getText().isEmpty() == false) {
            RpsProdutos rpsProdutos = (RpsProdutos) rps_jCboProdutos.getSelectedItem();
            int quant = Util.strToInt(rps_jTxtQuantidade.getText());
            rps_jTxtTotal.setText(Util.doubleToStr(quant * rpsProdutos.getRpsValor()));
        } else {
            Util.limpar(rps_jTxtTotal);
        }
    }//GEN-LAST:event_rps_jTxtQuantidadeKeyReleased

    private void rps_jCboProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jCboProdutosActionPerformed
        RpsProdutos produtos = (RpsProdutos) rps_jCboProdutos.getSelectedItem();
        rps_jTxtValorUnitario.setText(Util.doubleToStr(produtos.getRpsValor()));
        int quant = Util.strToInt(rps_jTxtQuantidade.getText());
        rps_jTxtTotal.setText(Util.doubleToStr(quant * produtos.getRpsValor()));
    }//GEN-LAST:event_rps_jCboProdutosActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgVendasProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgVendasProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgVendasProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgVendasProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgVendasProdutos dialog = new JDlgVendasProdutos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton rps_jBtnCancelar;
    private javax.swing.JButton rps_jBtnOk;
    private javax.swing.JComboBox<RpsProdutos> rps_jCboProdutos;
    private javax.swing.JTextField rps_jTxtQuantidade;
    private javax.swing.JTextField rps_jTxtTotal;
    private javax.swing.JTextField rps_jTxtValorUnitario;
    // End of variables declaration//GEN-END:variables
}
