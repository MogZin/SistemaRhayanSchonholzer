package view;

import bean.RpsVendas;
import dao.VendasDAO;
import java.util.List;
import tools.Util;

public class JDlgVendasPesquisar extends javax.swing.JDialog {

    private JDlgVendas jDlgVendas;
    ControllerVendas controllerVendas;

    public JDlgVendasPesquisar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        iniciarRelogio("Pesquisar Vendas"); // coloque o nome do usuário logado aqui
        controllerVendas = new ControllerVendas();
        VendasDAO vendasDAO = new VendasDAO();
        List lista = (List) vendasDAO.listAll();
        controllerVendas.setList(lista);
        rps_jTable.setModel(controllerVendas);
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

    public void setTelaAnterior(JDlgVendas jDlgVendas) {
        this.jDlgVendas = jDlgVendas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        rps_jTable = new javax.swing.JTable();
        rps_jBtnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        rps_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        rps_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rps_jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(rps_jTable);

        rps_jBtnOk.setText("OK");
        rps_jBtnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addComponent(rps_jBtnOk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rps_jBtnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rps_jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnOkActionPerformed
        if (rps_jTable.getSelectedRow() == -1) {
            Util.mensagem("Nenhum registro foi selecionada! Favor selecionar um registro.");
        } else {
            RpsVendas vendas = controllerVendas.getBean(rps_jTable.getSelectedRow());
            jDlgVendas.beanView(vendas);
            this.setVisible(false);
        }
    }//GEN-LAST:event_rps_jBtnOkActionPerformed

    private void rps_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rps_jTableMouseClicked
        if (evt.getClickCount() == 2) {
            rps_jBtnOkActionPerformed(null);
        }
    }//GEN-LAST:event_rps_jTableMouseClicked

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
            java.util.logging.Logger.getLogger(JDlgVendasPesquisar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgVendasPesquisar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgVendasPesquisar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgVendasPesquisar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgVendasPesquisar dialog = new JDlgVendasPesquisar(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton rps_jBtnOk;
    private javax.swing.JTable rps_jTable;
    // End of variables declaration//GEN-END:variables
}
