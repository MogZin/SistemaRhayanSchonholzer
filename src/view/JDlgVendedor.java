package view;

import bean.RpsVendedor;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import tools.Util;
import dao.VendedorDAO;

public class JDlgVendedor extends javax.swing.JDialog {

    private boolean incluir;
    private MaskFormatter mascaraCpf, mascaraTel, mascaraDataContratacao;

    public JDlgVendedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setForeground(Color.WHITE);
        jLabel5.setForeground(Color.WHITE);
        jLabel6.setForeground(Color.WHITE);
        jLabel7.setForeground(Color.WHITE);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/img/telaVendedor.jpg"));
        Image image = backgroundIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(background);
        getContentPane().setComponentZOrder(background, getContentPane().getComponentCount() - 1);

        Util.habilitar(false, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jTxtNome, rps_jTxtSalario, rps_jFmtCpf, rps_jFmtDataContratacao, rps_jFmtEmail, rps_jFmtTelefone);
        try {
            mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraTel = new MaskFormatter("(##)#####-####");
            mascaraDataContratacao = new MaskFormatter("##/##/####");
            rps_jFmtCpf.setFormatterFactory(new DefaultFormatterFactory(mascaraCpf));
            rps_jFmtTelefone.setFormatterFactory(new DefaultFormatterFactory(mascaraTel));
            rps_jFmtDataContratacao.setFormatterFactory(new DefaultFormatterFactory(mascaraDataContratacao));
        } catch (ParseException ex) {
            Logger.getLogger(JDlgVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        // === FORMATAÇÃO AUTOMÁTICA DE SALDO COM R$ ===
        rps_jTxtSalario.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                // Remove tudo que não for número
                String texto = rps_jTxtSalario.getText().replaceAll("[^0-9]", "");

                if (texto.isEmpty()) {
                    rps_jTxtSalario.setText("R$ 0,00");
                    return;
                }

                // Garante pelo menos 3 dígitos (para manter 2 casas decimais)
                while (texto.length() < 3) {
                    texto = "0" + texto;
                }

                try {
                    double valor = Double.parseDouble(texto) / 100.0;
                    java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
                    rps_jTxtSalario.setText("R$ " + df.format(valor));
                } catch (NumberFormatException e) {
                    // em caso de erro de conversão, reseta o campo
                    rps_jTxtSalario.setText("R$ 0,00");
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                // bloqueia qualquer caractere que não seja número
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                }
            }
        });

        // inicializa o campo bonitinho
        rps_jTxtSalario.setText("R$ 0,00");
        rps_jTxtSalario.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        rps_jTxtSalario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        rps_jTxtSalario.setForeground(new java.awt.Color(34, 139, 34)); // verde "saldo positivo"

        iniciarRelogio("Cadastro de Vendedores"); // coloque o nome do usuário logado aqui
    }

    public void beanView(RpsVendedor vendedor) {
        rps_jTxtCodigo.setText(Util.intToStr(vendedor.getRpsIdvendedor()));
        rps_jTxtNome.setText(vendedor.getRpsNome());
        rps_jTxtSalario.setText(Util.doubleToStr(vendedor.getRpsSalario()));
        rps_jFmtCpf.setText(vendedor.getRpsCpf());
        rps_jFmtEmail.setText(vendedor.getRpsEmail());
        rps_jFmtTelefone.setText(vendedor.getRpsTelefone());
        rps_jFmtDataContratacao.setText(Util.dateToStr(vendedor.getRpsDataContratacao()));
    }

    public RpsVendedor viewBean() {
        RpsVendedor Rpsvendedor = new RpsVendedor();
        int cod = Util.strToInt(rps_jTxtCodigo.getText());
        Rpsvendedor.setRpsIdvendedor(cod);
        Rpsvendedor.setRpsNome(rps_jTxtNome.getText());
        Rpsvendedor.setRpsSalario(Util.strToDouble(rps_jTxtSalario.getText()));
        Rpsvendedor.setRpsCpf(rps_jFmtCpf.getText());
        Rpsvendedor.setRpsEmail(rps_jFmtEmail.getText());
        Rpsvendedor.setRpsTelefone(rps_jFmtTelefone.getText());
        Rpsvendedor.setRpsDataContratacao(Util.strToDate(rps_jFmtDataContratacao.getText()));
        return Rpsvendedor;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        rps_jTxtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rps_jTxtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rps_jFmtCpf = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        rps_jFmtTelefone = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        rps_jFmtEmail = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        rps_jFmtDataContratacao = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        rps_jTxtSalario = new javax.swing.JTextField();
        rps_jBtnIncluir = new javax.swing.JButton();
        rps_jBtnAlterar = new javax.swing.JButton();
        rps_jBtnExcluir = new javax.swing.JButton();
        rps_jBtnConfirmar = new javax.swing.JButton();
        rps_jBtnCancelar = new javax.swing.JButton();
        rps_jBtnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setDisplayedMnemonic('c');
        jLabel1.setText("Código");

        jLabel2.setDisplayedMnemonic('n');
        jLabel2.setText("Nome");

        jLabel3.setDisplayedMnemonic('c');
        jLabel3.setText("Cpf");

        jLabel4.setDisplayedMnemonic('t');
        jLabel4.setText("Telefone");

        jLabel5.setDisplayedMnemonic('e');
        jLabel5.setText("E-mail");

        rps_jFmtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jFmtEmailActionPerformed(evt);
            }
        });

        jLabel6.setDisplayedMnemonic('d');
        jLabel6.setText("Data de Contratação");

        jLabel7.setDisplayedMnemonic('s');
        jLabel7.setText("Salário");

        rps_jTxtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jTxtSalarioActionPerformed(evt);
            }
        });

        rps_jBtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/incluir.png"))); // NOI18N
        rps_jBtnIncluir.setText("Incluir");
        rps_jBtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnIncluirActionPerformed(evt);
            }
        });

        rps_jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/alterar.png"))); // NOI18N
        rps_jBtnAlterar.setText("Alterar");
        rps_jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnAlterarActionPerformed(evt);
            }
        });

        rps_jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Excluir.png"))); // NOI18N
        rps_jBtnExcluir.setText("Excluir");
        rps_jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnExcluirActionPerformed(evt);
            }
        });

        rps_jBtnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gravar.png"))); // NOI18N
        rps_jBtnConfirmar.setText("Confirmar");
        rps_jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnConfirmarActionPerformed(evt);
            }
        });

        rps_jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        rps_jBtnCancelar.setText("Cancelar");
        rps_jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnCancelarActionPerformed(evt);
            }
        });

        rps_jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pesquisar.png"))); // NOI18N
        rps_jBtnPesquisar.setText("Pesquisar");
        rps_jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(rps_jTxtNome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(rps_jBtnIncluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rps_jBtnAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rps_jBtnExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rps_jBtnConfirmar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rps_jBtnCancelar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rps_jBtnPesquisar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(rps_jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rps_jFmtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(rps_jFmtTelefone))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6)
                                    .addComponent(rps_jTxtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rps_jFmtDataContratacao, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(rps_jFmtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rps_jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rps_jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jFmtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jFmtDataContratacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jFmtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jTxtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rps_jFmtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jBtnIncluir)
                    .addComponent(rps_jBtnAlterar)
                    .addComponent(rps_jBtnExcluir)
                    .addComponent(rps_jBtnConfirmar)
                    .addComponent(rps_jBtnCancelar)
                    .addComponent(rps_jBtnPesquisar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rps_jFmtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jFmtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jFmtEmailActionPerformed

    private void rps_jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnIncluirActionPerformed
        Util.habilitar(true, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jTxtNome, rps_jTxtSalario, rps_jFmtCpf, rps_jFmtDataContratacao, rps_jFmtEmail, rps_jFmtTelefone);
        Util.limpar(rps_jTxtCodigo, rps_jTxtNome, rps_jTxtSalario, rps_jFmtCpf, rps_jFmtDataContratacao, rps_jFmtEmail, rps_jFmtTelefone);
        Util.habilitar(false, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        rps_jTxtCodigo.grabFocus();
        incluir = true;
    }//GEN-LAST:event_rps_jBtnIncluirActionPerformed

    private void rps_jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnAlterarActionPerformed
        if (rps_jTxtCodigo.getText().trim().isEmpty()) {
            Util.mensagem("Pesquise um vendedor antes de Alterar");
            return;
        }
        Util.habilitar(true, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtNome, rps_jTxtSalario, rps_jFmtCpf, rps_jFmtDataContratacao, rps_jFmtEmail, rps_jFmtTelefone);
        Util.habilitar(false, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.habilitar(false, rps_jTxtCodigo);
        rps_jTxtNome.grabFocus();
        incluir = false;
    }//GEN-LAST:event_rps_jBtnAlterarActionPerformed

    private void rps_jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnExcluirActionPerformed
        if (rps_jTxtCodigo.getText().trim().isEmpty()) {
            Util.mensagem("Pesquise um vendedor antes de Excluir");
            return;
        }

        if (Util.pergunta("Deseja excluir ?") == true) {
            VendedorDAO vendedorDAO = new VendedorDAO();
            RpsVendedor rpsVendedor = viewBean();
            vendedorDAO.delete(rpsVendedor);
        }
        Util.limpar(rps_jTxtCodigo, rps_jTxtNome, rps_jTxtSalario, rps_jFmtCpf, rps_jFmtDataContratacao, rps_jFmtEmail, rps_jFmtTelefone);
    }//GEN-LAST:event_rps_jBtnExcluirActionPerformed

    private void rps_jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnConfirmarActionPerformed
        VendedorDAO vendedorDAO = new VendedorDAO();
        RpsVendedor rpsVendedor = viewBean();
        if (incluir == true) {
            vendedorDAO.insert(rpsVendedor);

        } else {
            vendedorDAO.update(rpsVendedor);
        }
        Util.habilitar(false, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jTxtNome, rps_jTxtSalario, rps_jFmtCpf, rps_jFmtDataContratacao, rps_jFmtEmail, rps_jFmtTelefone);
        Util.habilitar(true, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.limpar(rps_jTxtCodigo, rps_jTxtNome, rps_jTxtSalario, rps_jFmtCpf, rps_jFmtDataContratacao, rps_jFmtEmail, rps_jFmtTelefone);
    }//GEN-LAST:event_rps_jBtnConfirmarActionPerformed

    private void rps_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnCancelarActionPerformed
        Util.habilitar(false, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jTxtNome, rps_jTxtSalario, rps_jFmtCpf, rps_jFmtDataContratacao, rps_jFmtEmail, rps_jFmtTelefone);
        Util.habilitar(true, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.limpar(rps_jTxtCodigo, rps_jTxtNome, rps_jTxtSalario, rps_jFmtCpf, rps_jFmtDataContratacao, rps_jFmtEmail, rps_jFmtTelefone);
    }//GEN-LAST:event_rps_jBtnCancelarActionPerformed

    private void rps_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnPesquisarActionPerformed
        JDlgVendedorPesquisar jDlgVendedorPesquisar = new JDlgVendedorPesquisar(null, true);
        jDlgVendedorPesquisar.setTelaAnterior(this);
        jDlgVendedorPesquisar.setVisible(true);
    }//GEN-LAST:event_rps_jBtnPesquisarActionPerformed

    private void rps_jTxtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jTxtSalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jTxtSalarioActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgVendedor dialog = new JDlgVendedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton rps_jBtnAlterar;
    private javax.swing.JButton rps_jBtnCancelar;
    private javax.swing.JButton rps_jBtnConfirmar;
    private javax.swing.JButton rps_jBtnExcluir;
    private javax.swing.JButton rps_jBtnIncluir;
    private javax.swing.JButton rps_jBtnPesquisar;
    private javax.swing.JFormattedTextField rps_jFmtCpf;
    private javax.swing.JFormattedTextField rps_jFmtDataContratacao;
    private javax.swing.JFormattedTextField rps_jFmtEmail;
    private javax.swing.JFormattedTextField rps_jFmtTelefone;
    private javax.swing.JTextField rps_jTxtCodigo;
    private javax.swing.JTextField rps_jTxtNome;
    private javax.swing.JTextField rps_jTxtSalario;
    // End of variables declaration//GEN-END:variables

}
