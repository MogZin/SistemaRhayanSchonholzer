package view;

import bean.RpsClientes;
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
import dao.ClientesDAO;

public class JDlgClientes extends javax.swing.JDialog {

    private boolean incluir;
    private MaskFormatter mascaraCpf, mascaraTel, mascaraCep, mascaraDataNasc, mascaraDataCad;

    public JDlgClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        jLabel1.setForeground(Color.BLACK);
        jLabel2.setForeground(Color.BLACK);
        jLabel3.setForeground(Color.BLACK);
        jLabel4.setForeground(Color.BLACK);
        jLabel5.setForeground(Color.BLACK);
        jLabel6.setForeground(Color.BLACK);
        jLabel7.setForeground(Color.BLACK);
        jLabel8.setForeground(Color.BLACK);
        jLabel9.setForeground(Color.BLACK);
        jLabel10.setForeground(Color.BLACK);
        jLabel11.setForeground(Color.BLACK);
        jLabel12.setForeground(Color.BLACK);
        jLabel13.setForeground(Color.BLACK);
        jLabel14.setForeground(Color.BLACK);
        jLabel15.setForeground(Color.BLACK);
        rps_jChbAtivo.setForeground(Color.BLACK);
        rps_jCboEstado.setForeground(Color.BLACK);
        rps_jCboTipoCliente.setForeground(Color.BLACK);
        rps_jCboGenero.setForeground(Color.BLACK);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/img/telaCliente.jpg"));
        Image image = backgroundIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(background);
        getContentPane().setComponentZOrder(background, getContentPane().getComponentCount() - 1);
        Util.habilitar(false, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jTxtNome, rps_jTxtBairro, rps_jTxtCidade, rps_jFmtCpf, rps_jFmtDataNascimento, rps_jFmtCep, rps_jFmtDataCadastro, rps_jFmtEmail, rps_jFmtSaldoConta, rps_jFmtTelefone, rps_jCboEstado, rps_jCboGenero, rps_jCboTipoCliente, rps_jChbAtivo, rps_jPwdSenha);
        try {
            mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraTel = new MaskFormatter("(##)#####-####");
            mascaraCep = new MaskFormatter("#####-###");
            mascaraDataNasc = new MaskFormatter("##/##/####");
            mascaraDataCad = new MaskFormatter("##/##/####");
            rps_jFmtCpf.setFormatterFactory(new DefaultFormatterFactory(mascaraCpf));
            rps_jFmtTelefone.setFormatterFactory(new DefaultFormatterFactory(mascaraTel));
            rps_jFmtCep.setFormatterFactory(new DefaultFormatterFactory(mascaraCep));
            rps_jFmtDataNascimento.setFormatterFactory(new DefaultFormatterFactory(mascaraDataNasc));
            rps_jFmtDataCadastro.setFormatterFactory(new DefaultFormatterFactory(mascaraDataCad));
        } catch (ParseException ex) {
            Logger.getLogger(JDlgClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        // === FORMATAÇÃO AUTOMÁTICA DE SALDO COM R$ ===
        rps_jFmtSaldoConta.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                // Remove tudo que não for número
                String texto = rps_jFmtSaldoConta.getText().replaceAll("[^0-9]", "");

                if (texto.isEmpty()) {
                    rps_jFmtSaldoConta.setText("R$ 0,00");
                    return;
                }

                // Garante pelo menos 3 dígitos (para manter 2 casas decimais)
                while (texto.length() < 3) {
                    texto = "0" + texto;
                }

                try {
                    double valor = Double.parseDouble(texto) / 100.0;
                    java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
                    rps_jFmtSaldoConta.setText("R$ " + df.format(valor));
                } catch (NumberFormatException e) {
                    // em caso de erro de conversão, reseta o campo
                    rps_jFmtSaldoConta.setText("R$ 0,00");
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
        rps_jFmtSaldoConta.setText("R$ 0,00");
        rps_jFmtSaldoConta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        rps_jFmtSaldoConta.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        rps_jFmtSaldoConta.setForeground(new java.awt.Color(34, 139, 34)); // verde "saldo positivo"

        iniciarRelogio("Cadastro de Clientes"); // coloque o nome do usuário logado aqui
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

    public void beanView(RpsClientes clientes) {
        rps_jTxtCodigo.setText(Util.intToStr(clientes.getRpsIdclientes()));
        rps_jTxtNome.setText(clientes.getRpsNome());
        rps_jTxtBairro.setText(clientes.getRpsBairro());
        rps_jTxtCidade.setText(clientes.getRpsCidade());
        rps_jFmtCpf.setText(clientes.getRpsCpf());
        rps_jFmtCep.setText(clientes.getRpsCep());
        rps_jFmtEmail.setText(clientes.getRpsEmail());
        rps_jFmtSaldoConta.setText(Util.doubleToStr(clientes.getRpsSaldoConta()));
        rps_jFmtTelefone.setText(clientes.getRpsTelefone());
        rps_jFmtDataCadastro.setText(Util.dateToStr(clientes.getRpsDataCadastro()));
        rps_jFmtDataNascimento.setText(Util.dateToStr(clientes.getRpsDataNascimento()));
        rps_jPwdSenha.setText(clientes.getRpsSenha());
        rps_jCboEstado.setSelectedIndex(clientes.getRpsEstado());
        rps_jCboGenero.setSelectedIndex(clientes.getRpsGenero());
        rps_jCboTipoCliente.setSelectedIndex(clientes.getRpsTipoCliente());
        if (clientes.getRpsAtivo().equals("S") == true) {
            rps_jChbAtivo.setSelected(true);
        } else {
            rps_jChbAtivo.setSelected(false);
        }
    }

    public RpsClientes viewBean() {
        RpsClientes Rpsclientes = new RpsClientes();
        int cod = Util.strToInt(rps_jTxtCodigo.getText());
        Rpsclientes.setRpsIdclientes(cod);
        Rpsclientes.setRpsNome(rps_jTxtNome.getText());
        Rpsclientes.setRpsBairro(rps_jTxtBairro.getText());
        Rpsclientes.setRpsCidade(rps_jTxtCidade.getText());
        Rpsclientes.setRpsCpf(rps_jFmtCpf.getText());
        Rpsclientes.setRpsCep(rps_jFmtCep.getText());
        Rpsclientes.setRpsEmail(rps_jFmtEmail.getText());
        Rpsclientes.setRpsSaldoConta(Util.strToDouble(rps_jFmtSaldoConta.getText()));
        Rpsclientes.setRpsTelefone(rps_jFmtTelefone.getText());

        Rpsclientes.setRpsDataCadastro(Util.strToDate(rps_jFmtDataCadastro.getText()));
        Rpsclientes.setRpsDataNascimento(Util.strToDate(rps_jFmtDataNascimento.getText()));

        Rpsclientes.setRpsSenha(rps_jPwdSenha.getText());
        Rpsclientes.setRpsEstado(rps_jCboEstado.getSelectedIndex());
        Rpsclientes.setRpsGenero(rps_jCboGenero.getSelectedIndex());
        Rpsclientes.setRpsTipoCliente(rps_jCboTipoCliente.getSelectedIndex());

        if (rps_jChbAtivo.isSelected() == true) {
            Rpsclientes.setRpsAtivo("S");
        } else {
            Rpsclientes.setRpsAtivo("N");
        }
        return Rpsclientes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rps_jBtnCancelar = new javax.swing.JButton();
        rps_jBtnPesquisar = new javax.swing.JButton();
        rps_jBtnIncluir = new javax.swing.JButton();
        rps_jBtnAlterar = new javax.swing.JButton();
        rps_jBtnExcluir = new javax.swing.JButton();
        rps_jBtnConfirmar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rps_jTxtCodigo = new javax.swing.JTextField();
        rps_jFmtDataNascimento = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rps_jTxtNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rps_jFmtCpf = new javax.swing.JFormattedTextField();
        rps_jFmtTelefone = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        rps_jCboGenero = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        rps_jCboTipoCliente = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        rps_jTxtBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rps_jTxtCidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rps_jFmtCep = new javax.swing.JFormattedTextField();
        rps_jFmtSaldoConta = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        rps_jPwdSenha = new javax.swing.JPasswordField();
        rps_jChbAtivo = new javax.swing.JCheckBox();
        rps_jFmtEmail = new javax.swing.JFormattedTextField();
        rps_jFmtDataCadastro = new javax.swing.JFormattedTextField();
        rps_jCboEstado = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        jLabel3.setDisplayedMnemonic('c');
        jLabel3.setText("Cpf");

        jLabel4.setDisplayedMnemonic('t');
        jLabel4.setText("Telefone");

        jLabel1.setDisplayedMnemonic('c');
        jLabel1.setText("Codigo ");

        jLabel5.setDisplayedMnemonic('d');
        jLabel5.setText("Data de Nascimento");

        rps_jTxtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jTxtCodigoActionPerformed(evt);
            }
        });

        rps_jFmtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jFmtDataNascimentoActionPerformed(evt);
            }
        });

        jLabel6.setDisplayedMnemonic('e');
        jLabel6.setText("E-mail");

        jLabel2.setDisplayedMnemonic('n');
        jLabel2.setText("Nome");

        rps_jTxtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jTxtNomeActionPerformed(evt);
            }
        });

        jLabel7.setDisplayedMnemonic('d');
        jLabel7.setText("Data de Cadastro");

        jLabel8.setDisplayedMnemonic('g');
        jLabel8.setText("Gênero");

        rps_jCboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        jLabel9.setDisplayedMnemonic('t');
        jLabel9.setText("Tipo de Cliente");

        rps_jCboTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente VIP", "Cliente Regular", "Cliente Ocasional", "Cliente Novo", "Cliente Inativo", "Cliente Perdido" }));

        jLabel10.setDisplayedMnemonic('b');
        jLabel10.setText("Bairro");

        jLabel11.setDisplayedMnemonic('c');
        jLabel11.setText("Cidade");

        rps_jTxtCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jTxtCidadeActionPerformed(evt);
            }
        });

        jLabel12.setDisplayedMnemonic('s');
        jLabel12.setText("Saldo da Conta");

        jLabel13.setDisplayedMnemonic('c');
        jLabel13.setText("Cep");

        rps_jFmtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jFmtCepActionPerformed(evt);
            }
        });

        jLabel14.setDisplayedMnemonic('e');
        jLabel14.setText("Estado");

        jLabel15.setDisplayedMnemonic('s');
        jLabel15.setText("Senha");

        rps_jChbAtivo.setMnemonic('a');
        rps_jChbAtivo.setText("Ativo");
        rps_jChbAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jChbAtivoActionPerformed(evt);
            }
        });

        rps_jFmtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jFmtEmailActionPerformed(evt);
            }
        });

        rps_jFmtDataCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jFmtDataCadastroActionPerformed(evt);
            }
        });

        rps_jCboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC  ", "AL  ", "AP  ", "AM  ", "BA  ", "CE  ", "DF  ", "ES  ", "GO  ", "MA  ", "MT  ", "MS  ", "MG  ", "PA  ", "PB  ", "PR  ", "PE  ", "PI  ", "RJ  ", "RN  ", "RS  ", "RO  ", "RR  ", "SC  ", "SP  ", "SE  ", "TO" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rps_jBtnIncluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rps_jBtnAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rps_jBtnExcluir))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rps_jFmtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(rps_jPwdSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(rps_jTxtCidade, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rps_jTxtBairro, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(rps_jFmtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(rps_jCboTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rps_jCboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rps_jCboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(rps_jFmtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(rps_jChbAtivo)
                                        .addComponent(rps_jFmtSaldoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rps_jBtnConfirmar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rps_jBtnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rps_jBtnPesquisar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rps_jFmtEmail)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rps_jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rps_jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(rps_jFmtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(rps_jFmtTelefone)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel1)
                    .addContainerGap(617, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(rps_jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rps_jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jFmtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jFmtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jFmtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jFmtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jFmtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jCboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jCboTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jTxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jTxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jFmtSaldoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jFmtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jCboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jPwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jChbAtivo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jBtnIncluir)
                    .addComponent(rps_jBtnAlterar)
                    .addComponent(rps_jBtnExcluir)
                    .addComponent(rps_jBtnConfirmar)
                    .addComponent(rps_jBtnCancelar)
                    .addComponent(rps_jBtnPesquisar))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(581, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rps_jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnIncluirActionPerformed
        Util.habilitar(true, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jTxtNome, rps_jTxtBairro, rps_jTxtCidade, rps_jFmtCpf, rps_jFmtDataNascimento, rps_jFmtCep, rps_jFmtDataCadastro, rps_jFmtEmail, rps_jFmtSaldoConta, rps_jFmtTelefone, rps_jCboEstado, rps_jCboGenero, rps_jCboTipoCliente, rps_jChbAtivo, rps_jPwdSenha);
        Util.limpar(rps_jTxtCodigo, rps_jTxtNome, rps_jTxtBairro, rps_jTxtCidade, rps_jFmtCpf, rps_jFmtDataNascimento, rps_jFmtCep, rps_jFmtDataCadastro, rps_jFmtEmail, rps_jFmtSaldoConta, rps_jFmtTelefone, rps_jCboEstado, rps_jCboGenero, rps_jCboTipoCliente, rps_jChbAtivo, rps_jPwdSenha);
        Util.habilitar(false, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        rps_jTxtCodigo.grabFocus();
        incluir = true;
    }//GEN-LAST:event_rps_jBtnIncluirActionPerformed

    private void rps_jTxtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jTxtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jTxtCodigoActionPerformed

    private void rps_jFmtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jFmtDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jFmtDataNascimentoActionPerformed

    private void rps_jTxtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jTxtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jTxtNomeActionPerformed

    private void rps_jTxtCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jTxtCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jTxtCidadeActionPerformed

    private void rps_jFmtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jFmtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jFmtCepActionPerformed

    private void rps_jChbAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jChbAtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jChbAtivoActionPerformed

    private void rps_jFmtDataCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jFmtDataCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jFmtDataCadastroActionPerformed

    private void rps_jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnConfirmarActionPerformed
        ClientesDAO clientesDAO = new ClientesDAO();
        RpsClientes rpsClientes = viewBean();
        if (incluir == true) {
            clientesDAO.insert(rpsClientes);

        } else {
            clientesDAO.update(rpsClientes);
        }
        Util.habilitar(false, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jTxtNome, rps_jTxtBairro, rps_jTxtCidade, rps_jFmtCpf, rps_jFmtDataNascimento, rps_jFmtCep, rps_jFmtDataCadastro, rps_jFmtEmail, rps_jFmtSaldoConta, rps_jFmtTelefone, rps_jCboEstado, rps_jCboGenero, rps_jCboTipoCliente, rps_jChbAtivo, rps_jPwdSenha);
        Util.habilitar(true, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.limpar(rps_jTxtCodigo, rps_jTxtNome, rps_jTxtBairro, rps_jTxtCidade, rps_jFmtCpf, rps_jFmtDataNascimento, rps_jFmtCep, rps_jFmtDataCadastro, rps_jFmtEmail, rps_jFmtSaldoConta, rps_jFmtTelefone, rps_jCboEstado, rps_jCboGenero, rps_jCboTipoCliente, rps_jChbAtivo, rps_jPwdSenha);
    }//GEN-LAST:event_rps_jBtnConfirmarActionPerformed

    private void rps_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnCancelarActionPerformed
        Util.habilitar(false, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jTxtNome, rps_jTxtBairro, rps_jTxtCidade, rps_jFmtCpf, rps_jFmtDataNascimento, rps_jFmtCep, rps_jFmtDataCadastro, rps_jFmtEmail, rps_jFmtSaldoConta, rps_jFmtTelefone, rps_jCboEstado, rps_jCboGenero, rps_jCboTipoCliente, rps_jChbAtivo, rps_jPwdSenha);
        Util.habilitar(true, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.limpar(rps_jTxtCodigo, rps_jTxtNome, rps_jTxtBairro, rps_jTxtCidade, rps_jFmtCpf, rps_jFmtDataNascimento, rps_jFmtCep, rps_jFmtDataCadastro, rps_jFmtEmail, rps_jFmtSaldoConta, rps_jFmtTelefone, rps_jCboEstado, rps_jCboGenero, rps_jCboTipoCliente, rps_jChbAtivo, rps_jPwdSenha);
    }//GEN-LAST:event_rps_jBtnCancelarActionPerformed

    private void rps_jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnAlterarActionPerformed
        if (rps_jTxtCodigo.getText().trim().isEmpty()) {
            Util.mensagem("Pesquise um cliente antes de Alterar");
            return;
        }
        Util.habilitar(true, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jTxtNome, rps_jTxtBairro, rps_jTxtCidade, rps_jFmtCpf, rps_jFmtDataNascimento, rps_jFmtCep, rps_jFmtDataCadastro, rps_jFmtEmail, rps_jFmtSaldoConta, rps_jFmtTelefone, rps_jCboEstado, rps_jCboGenero, rps_jCboTipoCliente, rps_jChbAtivo, rps_jPwdSenha);
        Util.habilitar(false, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.habilitar(false, rps_jTxtCodigo);
        rps_jTxtNome.grabFocus();
        incluir = false;
    }//GEN-LAST:event_rps_jBtnAlterarActionPerformed

    private void rps_jFmtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jFmtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jFmtEmailActionPerformed

    private void rps_jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnExcluirActionPerformed
        if (rps_jTxtCodigo.getText().trim().isEmpty()) {
            Util.mensagem("Pesquise um cliente antes de Excluir");
            return;
        }

        if (Util.pergunta("Deseja excluir ?") == true) {
            ClientesDAO clientesDAO = new ClientesDAO();
            RpsClientes ppsClientes = viewBean();
            clientesDAO.delete(ppsClientes);
        }
        Util.limpar(rps_jTxtCodigo, rps_jTxtNome, rps_jTxtBairro, rps_jTxtCidade, rps_jFmtCpf, rps_jFmtDataNascimento, rps_jFmtCep, rps_jFmtDataCadastro, rps_jFmtEmail, rps_jFmtSaldoConta, rps_jFmtTelefone, rps_jCboEstado, rps_jCboGenero, rps_jCboTipoCliente, rps_jChbAtivo, rps_jPwdSenha);
    }//GEN-LAST:event_rps_jBtnExcluirActionPerformed

    private void rps_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnPesquisarActionPerformed
        JDlgClientesPesquisar jDlgClientesPesquisar = new JDlgClientesPesquisar(null, true);
        jDlgClientesPesquisar.setTelaAnterior(this);
        jDlgClientesPesquisar.setVisible(true);
    }//GEN-LAST:event_rps_jBtnPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgClientes dialog = new JDlgClientes(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton rps_jBtnAlterar;
    private javax.swing.JButton rps_jBtnCancelar;
    private javax.swing.JButton rps_jBtnConfirmar;
    private javax.swing.JButton rps_jBtnExcluir;
    private javax.swing.JButton rps_jBtnIncluir;
    private javax.swing.JButton rps_jBtnPesquisar;
    private javax.swing.JComboBox<String> rps_jCboEstado;
    private javax.swing.JComboBox<String> rps_jCboGenero;
    private javax.swing.JComboBox<String> rps_jCboTipoCliente;
    private javax.swing.JCheckBox rps_jChbAtivo;
    private javax.swing.JFormattedTextField rps_jFmtCep;
    private javax.swing.JFormattedTextField rps_jFmtCpf;
    private javax.swing.JFormattedTextField rps_jFmtDataCadastro;
    private javax.swing.JFormattedTextField rps_jFmtDataNascimento;
    private javax.swing.JFormattedTextField rps_jFmtEmail;
    private javax.swing.JFormattedTextField rps_jFmtSaldoConta;
    private javax.swing.JFormattedTextField rps_jFmtTelefone;
    private javax.swing.JPasswordField rps_jPwdSenha;
    private javax.swing.JTextField rps_jTxtBairro;
    private javax.swing.JTextField rps_jTxtCidade;
    private javax.swing.JTextField rps_jTxtCodigo;
    private javax.swing.JTextField rps_jTxtNome;
    // End of variables declaration//GEN-END:variables

}
