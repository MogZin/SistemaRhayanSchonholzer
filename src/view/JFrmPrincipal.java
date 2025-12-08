package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JFrmPrincipal extends javax.swing.JFrame {

    public JFrmPrincipal() {
        initComponents();
        setTitle("Sistema de Vendas de Jogos");
        setExtendedState(MAXIMIZED_BOTH);

        try {
            File arquivoMusica = new File("src/musica/musicainicial.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivoMusica);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            //clip.start(); // toca uma vez só 
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largura = screenSize.width;
        int altura = screenSize.height;

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/img/telaPrincipal.jpg"));
        Image image = backgroundIcon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, largura, altura);

        getContentPane().add(background);
        getContentPane().setComponentZOrder(background, getContentPane().getComponentCount() - 1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        rps_jMnuCadastros = new javax.swing.JMenu();
        rps_jMnuClientes = new javax.swing.JMenuItem();
        rps_jMnuProdutos = new javax.swing.JMenuItem();
        rps_jMnuUsuarios = new javax.swing.JMenuItem();
        rps_jMnuVendedores = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        rps_jMnuSair = new javax.swing.JMenuItem();
        rps_jMnuMovimentos = new javax.swing.JMenu();
        rps_jMnuVendas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientes.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-joystick-24.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Usuario.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Vendedora.png"))); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);
        jToolBar1.add(jSeparator2);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/venda.png"))); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);
        jToolBar1.add(jSeparator3);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/convendedor.png"))); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conProdutos.png"))); // NOI18N
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conusuario.png"))); // NOI18N
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-consultar-24.png"))); // NOI18N
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton10);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conempresa.png"))); // NOI18N
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        rps_jMnuCadastros.setMnemonic('c');
        rps_jMnuCadastros.setText("Cadastros");

        rps_jMnuClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientes.png"))); // NOI18N
        rps_jMnuClientes.setMnemonic('c');
        rps_jMnuClientes.setText("Clientes");
        rps_jMnuClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuClientesActionPerformed(evt);
            }
        });
        rps_jMnuCadastros.add(rps_jMnuClientes);

        rps_jMnuProdutos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-joystick-24.png"))); // NOI18N
        rps_jMnuProdutos.setMnemonic('p');
        rps_jMnuProdutos.setText("Produtos");
        rps_jMnuProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuProdutosActionPerformed(evt);
            }
        });
        rps_jMnuCadastros.add(rps_jMnuProdutos);

        rps_jMnuUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Usuario.png"))); // NOI18N
        rps_jMnuUsuarios.setMnemonic('u');
        rps_jMnuUsuarios.setText("Usuários");
        rps_jMnuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuUsuariosActionPerformed(evt);
            }
        });
        rps_jMnuCadastros.add(rps_jMnuUsuarios);

        rps_jMnuVendedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuVendedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Vendedora.png"))); // NOI18N
        rps_jMnuVendedores.setMnemonic('v');
        rps_jMnuVendedores.setText("Vendedores");
        rps_jMnuVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuVendedoresActionPerformed(evt);
            }
        });
        rps_jMnuCadastros.add(rps_jMnuVendedores);
        rps_jMnuCadastros.add(jSeparator1);

        rps_jMnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
        rps_jMnuSair.setMnemonic('s');
        rps_jMnuSair.setText("Sair");
        rps_jMnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuSairActionPerformed(evt);
            }
        });
        rps_jMnuCadastros.add(rps_jMnuSair);

        jMenuBar1.add(rps_jMnuCadastros);

        rps_jMnuMovimentos.setMnemonic('m');
        rps_jMnuMovimentos.setText("Movimentos");

        rps_jMnuVendas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/venda.png"))); // NOI18N
        rps_jMnuVendas.setMnemonic('v');
        rps_jMnuVendas.setText("Vendas");
        rps_jMnuVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuVendasActionPerformed(evt);
            }
        });
        rps_jMnuMovimentos.add(rps_jMnuVendas);

        jMenuBar1.add(rps_jMnuMovimentos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 237, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rps_jMnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_rps_jMnuSairActionPerformed

    private void rps_jMnuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuUsuariosActionPerformed
        JDlgUsuarios jDlgUsuarios = new JDlgUsuarios(this, true);
        jDlgUsuarios.setVisible(true);
    }//GEN-LAST:event_rps_jMnuUsuariosActionPerformed

    private void rps_jMnuClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuClientesActionPerformed
        JDlgClientes jDlgClientes = new JDlgClientes(this, true);
        jDlgClientes.setVisible(true);
    }//GEN-LAST:event_rps_jMnuClientesActionPerformed

    private void rps_jMnuProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuProdutosActionPerformed
        JDlgProdutos jDlgProdutos = new JDlgProdutos(this, true);
        jDlgProdutos.setVisible(true);
    }//GEN-LAST:event_rps_jMnuProdutosActionPerformed

    private void rps_jMnuVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuVendedoresActionPerformed
        JDlgVendedor jDlgVendedor = new JDlgVendedor(this, true);
        jDlgVendedor.setVisible(true);
    }//GEN-LAST:event_rps_jMnuVendedoresActionPerformed

    private void rps_jMnuVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuVendasActionPerformed
        JDlgVendas jDlgVendas = new JDlgVendas(this, true);
        jDlgVendas.setVisible(true);
    }//GEN-LAST:event_rps_jMnuVendasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
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
            java.util.logging.Logger.getLogger(JFrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu rps_jMnuCadastros;
    private javax.swing.JMenuItem rps_jMnuClientes;
    private javax.swing.JMenu rps_jMnuMovimentos;
    private javax.swing.JMenuItem rps_jMnuProdutos;
    private javax.swing.JMenuItem rps_jMnuSair;
    private javax.swing.JMenuItem rps_jMnuUsuarios;
    private javax.swing.JMenuItem rps_jMnuVendas;
    private javax.swing.JMenuItem rps_jMnuVendedores;
    // End of variables declaration//GEN-END:variables
}
