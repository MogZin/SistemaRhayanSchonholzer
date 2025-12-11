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
        iniciarRelogio("Sistema de Vendas de Jogos"); // coloque o nome do usuário logado aqui
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        rps_jBtnCadastrosClientes = new javax.swing.JButton();
        rps_jBtnCadastrosProdutos = new javax.swing.JButton();
        rps_jBtnCadastrosUsuarios = new javax.swing.JButton();
        rps_jBtnCadastrosVendedor = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        rps_jBtnMovimentoClientes = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        rps_jBtnConsultasClientes = new javax.swing.JButton();
        rps_jBtnConsultasProdutos = new javax.swing.JButton();
        rps_jBtnConsultasUsuarios = new javax.swing.JButton();
        rps_jBtnConsultasVendedor = new javax.swing.JButton();
        rps_jBtnConsultasVendas = new javax.swing.JButton();
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
        rps_jMnuConsultas = new javax.swing.JMenu();
        rps_jMnuConsultasClientes = new javax.swing.JMenuItem();
        rps_jMnuConsultasProdutos = new javax.swing.JMenuItem();
        rps_jMnuConsultasUsuarios = new javax.swing.JMenuItem();
        rps_jMnuConsultasVendedores = new javax.swing.JMenuItem();
        rps_jMnuConsultasVendas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        rps_jBtnCadastrosClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientes.png"))); // NOI18N
        rps_jBtnCadastrosClientes.setFocusable(false);
        rps_jBtnCadastrosClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnCadastrosClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnCadastrosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnCadastrosClientesActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnCadastrosClientes);

        rps_jBtnCadastrosProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-joystick-24.png"))); // NOI18N
        rps_jBtnCadastrosProdutos.setFocusable(false);
        rps_jBtnCadastrosProdutos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnCadastrosProdutos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnCadastrosProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnCadastrosProdutosActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnCadastrosProdutos);

        rps_jBtnCadastrosUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Usuario.png"))); // NOI18N
        rps_jBtnCadastrosUsuarios.setFocusable(false);
        rps_jBtnCadastrosUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnCadastrosUsuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnCadastrosUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnCadastrosUsuariosActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnCadastrosUsuarios);

        rps_jBtnCadastrosVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Vendedora.png"))); // NOI18N
        rps_jBtnCadastrosVendedor.setFocusable(false);
        rps_jBtnCadastrosVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnCadastrosVendedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnCadastrosVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnCadastrosVendedorActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnCadastrosVendedor);
        jToolBar1.add(jSeparator2);

        rps_jBtnMovimentoClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/venda.png"))); // NOI18N
        rps_jBtnMovimentoClientes.setFocusable(false);
        rps_jBtnMovimentoClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnMovimentoClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnMovimentoClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnMovimentoClientesActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnMovimentoClientes);
        jToolBar1.add(jSeparator3);

        rps_jBtnConsultasClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/convendedor.png"))); // NOI18N
        rps_jBtnConsultasClientes.setFocusable(false);
        rps_jBtnConsultasClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnConsultasClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnConsultasClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnConsultasClientesActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnConsultasClientes);

        rps_jBtnConsultasProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conProdutos.png"))); // NOI18N
        rps_jBtnConsultasProdutos.setFocusable(false);
        rps_jBtnConsultasProdutos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnConsultasProdutos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnConsultasProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnConsultasProdutosActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnConsultasProdutos);

        rps_jBtnConsultasUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conusuario.png"))); // NOI18N
        rps_jBtnConsultasUsuarios.setFocusable(false);
        rps_jBtnConsultasUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnConsultasUsuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnConsultasUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnConsultasUsuariosActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnConsultasUsuarios);

        rps_jBtnConsultasVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-consultar-24.png"))); // NOI18N
        rps_jBtnConsultasVendedor.setFocusable(false);
        rps_jBtnConsultasVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnConsultasVendedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnConsultasVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnConsultasVendedorActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnConsultasVendedor);

        rps_jBtnConsultasVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conempresa.png"))); // NOI18N
        rps_jBtnConsultasVendas.setFocusable(false);
        rps_jBtnConsultasVendas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rps_jBtnConsultasVendas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rps_jBtnConsultasVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnConsultasVendasActionPerformed(evt);
            }
        });
        jToolBar1.add(rps_jBtnConsultasVendas);

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

        rps_jMnuConsultas.setText("Consultas");

        rps_jMnuConsultasClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuConsultasClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/convendedor.png"))); // NOI18N
        rps_jMnuConsultasClientes.setMnemonic('C');
        rps_jMnuConsultasClientes.setText("Clientes");
        rps_jMnuConsultasClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuConsultasClientesActionPerformed(evt);
            }
        });
        rps_jMnuConsultas.add(rps_jMnuConsultasClientes);

        rps_jMnuConsultasProdutos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuConsultasProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conProdutos.png"))); // NOI18N
        rps_jMnuConsultasProdutos.setMnemonic('P');
        rps_jMnuConsultasProdutos.setText("Produtos");
        rps_jMnuConsultasProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuConsultasProdutosActionPerformed(evt);
            }
        });
        rps_jMnuConsultas.add(rps_jMnuConsultasProdutos);

        rps_jMnuConsultasUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuConsultasUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conusuario.png"))); // NOI18N
        rps_jMnuConsultasUsuarios.setMnemonic('U');
        rps_jMnuConsultasUsuarios.setText("Usuários");
        rps_jMnuConsultasUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuConsultasUsuariosActionPerformed(evt);
            }
        });
        rps_jMnuConsultas.add(rps_jMnuConsultasUsuarios);

        rps_jMnuConsultasVendedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuConsultasVendedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-consultar-24.png"))); // NOI18N
        rps_jMnuConsultasVendedores.setMnemonic('V');
        rps_jMnuConsultasVendedores.setText("Vendedores");
        rps_jMnuConsultasVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuConsultasVendedoresActionPerformed(evt);
            }
        });
        rps_jMnuConsultas.add(rps_jMnuConsultasVendedores);

        rps_jMnuConsultasVendas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        rps_jMnuConsultasVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/conempresa.png"))); // NOI18N
        rps_jMnuConsultasVendas.setMnemonic('V');
        rps_jMnuConsultasVendas.setText("Vendas");
        rps_jMnuConsultasVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jMnuConsultasVendasActionPerformed(evt);
            }
        });
        rps_jMnuConsultas.add(rps_jMnuConsultasVendas);

        jMenuBar1.add(rps_jMnuConsultas);

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

    private void rps_jBtnCadastrosProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnCadastrosProdutosActionPerformed
        JDlgProdutos jDlgProdutos = new JDlgProdutos(this, true);
        jDlgProdutos.setVisible(true);
    }//GEN-LAST:event_rps_jBtnCadastrosProdutosActionPerformed

    private void rps_jBtnCadastrosUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnCadastrosUsuariosActionPerformed
        JDlgUsuarios jDlgUsuarios = new JDlgUsuarios(this, true);
        jDlgUsuarios.setVisible(true);
    }//GEN-LAST:event_rps_jBtnCadastrosUsuariosActionPerformed

    private void rps_jBtnCadastrosVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnCadastrosVendedorActionPerformed
        JDlgVendedor jDlgVendedor = new JDlgVendedor(this, true);
        jDlgVendedor.setVisible(true);
    }//GEN-LAST:event_rps_jBtnCadastrosVendedorActionPerformed

    private void rps_jBtnMovimentoClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnMovimentoClientesActionPerformed
        JDlgVendas jDlgVendas = new JDlgVendas(this, true);
        jDlgVendas.setVisible(true);
    }//GEN-LAST:event_rps_jBtnMovimentoClientesActionPerformed

    private void rps_jBtnConsultasClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnConsultasClientesActionPerformed
        JDlgConsultaClientes jDlgConsultaClientes = new JDlgConsultaClientes(this, true);
        jDlgConsultaClientes.setVisible(true);
    }//GEN-LAST:event_rps_jBtnConsultasClientesActionPerformed

    private void rps_jBtnConsultasVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnConsultasVendasActionPerformed
        JDlgConsultaVendas jDlgConsultaVendas = new JDlgConsultaVendas(this, true);
        jDlgConsultaVendas.setVisible(true);
    }//GEN-LAST:event_rps_jBtnConsultasVendasActionPerformed

    private void rps_jBtnConsultasUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnConsultasUsuariosActionPerformed
        JDlgConsultaUsuarios jDlgConsultaUsuarios = new JDlgConsultaUsuarios(this, true);
        jDlgConsultaUsuarios.setVisible(true);
    }//GEN-LAST:event_rps_jBtnConsultasUsuariosActionPerformed

    private void rps_jBtnConsultasProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnConsultasProdutosActionPerformed
        JDlgConsultaProdutos jDlgConsultaProdutos = new JDlgConsultaProdutos(this, true);
        jDlgConsultaProdutos.setVisible(true);
    }//GEN-LAST:event_rps_jBtnConsultasProdutosActionPerformed

    private void rps_jBtnConsultasVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnConsultasVendedorActionPerformed
        JDlgConsultaVendedor jDlgConsultaVendedor = new JDlgConsultaVendedor(this, true);
        jDlgConsultaVendedor.setVisible(true);
    }//GEN-LAST:event_rps_jBtnConsultasVendedorActionPerformed

    private void rps_jBtnCadastrosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnCadastrosClientesActionPerformed
        JDlgClientes jDlgClientes = new JDlgClientes(this, true);
        jDlgClientes.setVisible(true);
    }//GEN-LAST:event_rps_jBtnCadastrosClientesActionPerformed

    private void rps_jMnuConsultasClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuConsultasClientesActionPerformed
        JDlgConsultaClientes jDlgConsultaClientes = new JDlgConsultaClientes(this, true);
        jDlgConsultaClientes.setVisible(true);
    }//GEN-LAST:event_rps_jMnuConsultasClientesActionPerformed

    private void rps_jMnuConsultasProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuConsultasProdutosActionPerformed
        JDlgConsultaProdutos jDlgConsultaProdutos = new JDlgConsultaProdutos(this, true);
        jDlgConsultaProdutos.setVisible(true);
    }//GEN-LAST:event_rps_jMnuConsultasProdutosActionPerformed

    private void rps_jMnuConsultasUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuConsultasUsuariosActionPerformed
        JDlgConsultaUsuarios jDlgConsultaUsuarios = new JDlgConsultaUsuarios(this, true);
        jDlgConsultaUsuarios.setVisible(true);
    }//GEN-LAST:event_rps_jMnuConsultasUsuariosActionPerformed

    private void rps_jMnuConsultasVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuConsultasVendedoresActionPerformed
        JDlgConsultaVendedor jDlgConsultaVendedor = new JDlgConsultaVendedor(this, true);
        jDlgConsultaVendedor.setVisible(true);
    }//GEN-LAST:event_rps_jMnuConsultasVendedoresActionPerformed

    private void rps_jMnuConsultasVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jMnuConsultasVendasActionPerformed
        JDlgConsultaVendas jDlgConsultaVendas = new JDlgConsultaVendas(this, true);
        jDlgConsultaVendas.setVisible(true);
    }//GEN-LAST:event_rps_jMnuConsultasVendasActionPerformed
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
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton rps_jBtnCadastrosClientes;
    private javax.swing.JButton rps_jBtnCadastrosProdutos;
    private javax.swing.JButton rps_jBtnCadastrosUsuarios;
    private javax.swing.JButton rps_jBtnCadastrosVendedor;
    private javax.swing.JButton rps_jBtnConsultasClientes;
    private javax.swing.JButton rps_jBtnConsultasProdutos;
    private javax.swing.JButton rps_jBtnConsultasUsuarios;
    private javax.swing.JButton rps_jBtnConsultasVendas;
    private javax.swing.JButton rps_jBtnConsultasVendedor;
    private javax.swing.JButton rps_jBtnMovimentoClientes;
    private javax.swing.JMenu rps_jMnuCadastros;
    private javax.swing.JMenuItem rps_jMnuClientes;
    private javax.swing.JMenu rps_jMnuConsultas;
    private javax.swing.JMenuItem rps_jMnuConsultasClientes;
    private javax.swing.JMenuItem rps_jMnuConsultasProdutos;
    private javax.swing.JMenuItem rps_jMnuConsultasUsuarios;
    private javax.swing.JMenuItem rps_jMnuConsultasVendas;
    private javax.swing.JMenuItem rps_jMnuConsultasVendedores;
    private javax.swing.JMenu rps_jMnuMovimentos;
    private javax.swing.JMenuItem rps_jMnuProdutos;
    private javax.swing.JMenuItem rps_jMnuSair;
    private javax.swing.JMenuItem rps_jMnuUsuarios;
    private javax.swing.JMenuItem rps_jMnuVendas;
    private javax.swing.JMenuItem rps_jMnuVendedores;
    // End of variables declaration//GEN-END:variables
}
