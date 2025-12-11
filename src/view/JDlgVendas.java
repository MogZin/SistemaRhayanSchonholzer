package view;

import bean.RpsClientes;
import bean.RpsVendas;
import bean.RpsVendasProdutos;
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
import dao.VendasDAO;
import dao.ClientesDAO;
import dao.VendasProdutosDAO;
import dao.VendedorDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

public class JDlgVendas extends javax.swing.JDialog {

    ControllerVendasProdutos controllerVenProd;
    private boolean incluir;
    boolean pesquisado = false;
    private MaskFormatter mascaraDataVenda;

    public JDlgVendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Cadastro de Vendas");
        setLocationRelativeTo(null);

        ClientesDAO clientesDAO = new ClientesDAO();
        List lista = (List) clientesDAO.listAll();
        for (int i = 0; i < lista.size(); i++) {
            rps_jCboClientes.addItem((RpsClientes) lista.get(i));
        }

        VendedorDAO vendedorDAO = new VendedorDAO();
        List lista2 = (List) vendedorDAO.listAll();
        for (int i = 0; i < lista2.size(); i++) {
            rps_jCboVendedor.addItem((RpsVendedor) lista2.get(i));
        }
        controllerVenProd = new ControllerVendasProdutos();
        controllerVenProd.setList(new ArrayList());
        jTable1.setModel(controllerVenProd);
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setForeground(Color.WHITE);
        jLabel5.setForeground(Color.WHITE);
        jLabel6.setForeground(Color.WHITE);
        jLabel7.setForeground(Color.WHITE);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/img/Vendas.jpg"));
        Image image = backgroundIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(background);
        getContentPane().setComponentZOrder(background, getContentPane().getComponentCount() - 1);

        Util.habilitar(false, rps_jBtnAlterarProd, rps_jBtnExcluirProd, rps_jBtnIncluirProd, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jFmtDataVenda, rps_jCboClientes, rps_jTxtDesconto, rps_jTxtTotal, rps_jCboVendedor, rps_jCboFormaPagamento);
        try {
            mascaraDataVenda = new MaskFormatter("##/##/####");
            rps_jFmtDataVenda.setFormatterFactory(new DefaultFormatterFactory(mascaraDataVenda));
        } catch (ParseException ex) {
            Logger.getLogger(JDlgVendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        // === FORMATAÇÃO AUTOMÁTICA DE SALDO COM R$ ===
        rps_jTxtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                // Remove tudo que não for número
                String texto = rps_jTxtTotal.getText().replaceAll("[^0-9]", "");

                if (texto.isEmpty()) {
                    rps_jTxtTotal.setText("R$ 0,00");
                    return;
                }

                // Garante pelo menos 3 dígitos (para manter 2 casas decimais)
                while (texto.length() < 3) {
                    texto = "0" + texto;
                }

                try {
                    double valor = Double.parseDouble(texto) / 100.0;
                    java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
                    rps_jTxtTotal.setText("R$ " + df.format(valor));
                } catch (NumberFormatException e) {
                    // em caso de erro de conversão, reseta o campo
                    rps_jTxtTotal.setText("R$ 0,00");
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
        rps_jTxtTotal.setText("R$ 0,00");
        rps_jTxtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        rps_jTxtTotal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        rps_jTxtTotal.setForeground(new java.awt.Color(34, 139, 34)); // verde "saldo positivo"

        // inicializa o campo bonitinho
        rps_jTxtDesconto.setText("R$ 0,00");
        rps_jTxtDesconto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        rps_jTxtDesconto.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        rps_jTxtDesconto.setForeground(new java.awt.Color(34, 139, 34)); // verde "saldo positivo"

        // === FORMATAÇÃO AUTOMÁTICA DE SALDO COM R$ ===
        rps_jTxtDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                // Remove tudo que não for número
                String texto = rps_jTxtDesconto.getText().replaceAll("[^0-9]", "");

                if (texto.isEmpty()) {
                    rps_jTxtDesconto.setText("R$ 0,00");
                    return;
                }

                // Garante pelo menos 3 dígitos (para manter 2 casas decimais)
                while (texto.length() < 3) {
                    texto = "0" + texto;
                }

                try {
                    double valor = Double.parseDouble(texto) / 100.0;
                    java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
                    rps_jTxtDesconto.setText("R$ " + df.format(valor));
                } catch (NumberFormatException e) {
                    // em caso de erro de conversão, reseta o campo
                    rps_jTxtDesconto.setText("R$ 0,00");
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
        rps_jTxtDesconto.setText("R$ 0,00");
        rps_jTxtDesconto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        rps_jTxtDesconto.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        rps_jTxtDesconto.setForeground(new java.awt.Color(34, 139, 34)); // verde "saldo positivo"

        iniciarRelogio("Cadastro de Vendas"); // coloque o nome do usuário logado aqui
    }

    public JTable getjTable1() {
        return jTable1;
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

    private void calcularTotal() {
        double total = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            RpsVendasProdutos produto = controllerVenProd.getBean(i);
            total += produto.getRpsQuantidade() * produto.getRpsValorUnitario();
        }

        // Aplica desconto se tiver
        double desconto = Util.strToDouble(rps_jTxtDesconto.getText());
        total = total - desconto;
        if (total < 0) {
            total = 0;
        }

        rps_jTxtTotal.setText("R$ " + String.format("%,.2f", total));
    }

    public RpsVendas viewBean() {
        RpsVendas rpsVendas = new RpsVendas();
        rpsVendas.setRpsIdVendas(Util.strToInt(rps_jTxtCodigo.getText()));
        rpsVendas.setRpsDataVenda(Util.strToDate(rps_jFmtDataVenda.getText()));
        rpsVendas.setRpsTotal(Util.strToDouble(rps_jTxtTotal.getText()));
        rpsVendas.setRpsDesconto(Util.strToDouble(rps_jTxtDesconto.getText()));
        rpsVendas.setRpsClientes((RpsClientes) rps_jCboClientes.getSelectedItem());
        rpsVendas.setRpsVendedor((RpsVendedor) rps_jCboVendedor.getSelectedItem());
        rpsVendas.setRpsFormaPagamento(rps_jCboFormaPagamento.getSelectedIndex());
        return rpsVendas;
    }

    public void beanView(RpsVendas rpsVendas) {
        rps_jTxtCodigo.setText(Util.intToStr(rpsVendas.getRpsIdVendas()));
        rps_jTxtDesconto.setText(Util.doubleToStr(rpsVendas.getRpsDesconto()));
        rps_jTxtTotal.setText(Util.doubleToStr(rpsVendas.getRpsTotal()));
        rps_jFmtDataVenda.setText(Util.dateToStr(rpsVendas.getRpsDataVenda()));
        rps_jCboFormaPagamento.setSelectedIndex(rpsVendas.getRpsFormaPagamento());
        rps_jCboClientes.setSelectedItem(rpsVendas.getRpsClientes());
        rps_jCboVendedor.setSelectedItem(rpsVendas.getRpsVendedor());
        VendasProdutosDAO vendasProdutosDAO = new VendasProdutosDAO();
        List lista = (List) vendasProdutosDAO.listProdutos(rpsVendas);
        controllerVenProd.setList(lista);
        calcularTotal(); // ADICIONA NO FINAL
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
        rps_jFmtDataVenda = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        rps_jCboFormaPagamento = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rps_jTxtTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rps_jTxtDesconto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        rps_jBtnIncluir = new javax.swing.JButton();
        rps_jBtnAlterar = new javax.swing.JButton();
        rps_jBtnExcluir = new javax.swing.JButton();
        rps_jBtnConfirmar = new javax.swing.JButton();
        rps_jBtnCancelar = new javax.swing.JButton();
        rps_jBtnPesquisar = new javax.swing.JButton();
        rps_jBtnIncluirProd = new javax.swing.JButton();
        rps_jBtnAlterarProd = new javax.swing.JButton();
        rps_jBtnExcluirProd = new javax.swing.JButton();
        rps_jCboClientes = new javax.swing.JComboBox<RpsClientes>();
        rps_jCboVendedor = new javax.swing.JComboBox<RpsVendedor>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setDisplayedMnemonic('C');
        jLabel1.setText("Código");

        jLabel2.setDisplayedMnemonic('D');
        jLabel2.setText("Data da Venda");

        jLabel7.setDisplayedMnemonic('F');
        jLabel7.setText("Forma de Pagamento");

        rps_jCboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Cartão de Crédito", "Cartão de Dédito", "Pix" }));

        jLabel3.setDisplayedMnemonic('C');
        jLabel3.setText("Cliente");

        jLabel4.setDisplayedMnemonic('U');
        jLabel4.setText("Vendedor");

        jLabel5.setDisplayedMnemonic('T');
        jLabel5.setText("Total");

        jLabel6.setDisplayedMnemonic('D');
        jLabel6.setText("Desconto");

        rps_jTxtDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jTxtDescontoActionPerformed(evt);
            }
        });
        rps_jTxtDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rps_jTxtDescontoKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

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

        rps_jBtnIncluirProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/incluir.png"))); // NOI18N
        rps_jBtnIncluirProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnIncluirProdActionPerformed(evt);
            }
        });

        rps_jBtnAlterarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/alterar.png"))); // NOI18N
        rps_jBtnAlterarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnAlterarProdActionPerformed(evt);
            }
        });

        rps_jBtnExcluirProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Excluir.png"))); // NOI18N
        rps_jBtnExcluirProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jBtnExcluirProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rps_jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel3)
                                .addGap(69, 69, 69))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rps_jFmtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rps_jCboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rps_jCboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(81, 81, 81)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rps_jTxtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(rps_jCboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rps_jBtnIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rps_jBtnAlterar)
                        .addGap(12, 12, 12)
                        .addComponent(rps_jBtnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rps_jBtnConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rps_jBtnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rps_jBtnPesquisar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rps_jBtnIncluirProd)
                            .addComponent(rps_jBtnAlterarProd, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(rps_jBtnExcluirProd, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rps_jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rps_jFmtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rps_jCboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rps_jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rps_jTxtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rps_jCboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rps_jCboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rps_jBtnIncluirProd)
                        .addGap(4, 4, 4)
                        .addComponent(rps_jBtnAlterarProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rps_jBtnExcluirProd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rps_jBtnExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rps_jBtnPesquisar)
                        .addComponent(rps_jBtnCancelar)
                        .addComponent(rps_jBtnConfirmar)
                        .addComponent(rps_jBtnAlterar)
                        .addComponent(rps_jBtnIncluir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rps_jTxtDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jTxtDescontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jTxtDescontoActionPerformed

    private void rps_jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnIncluirActionPerformed
        Util.habilitar(true, rps_jBtnAlterarProd, rps_jBtnExcluirProd, rps_jBtnIncluirProd, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jFmtDataVenda, rps_jCboClientes, rps_jTxtDesconto, rps_jTxtTotal, rps_jCboVendedor, rps_jCboFormaPagamento);
        Util.limpar(rps_jTxtCodigo, rps_jFmtDataVenda, rps_jCboClientes, rps_jTxtDesconto, rps_jTxtTotal, rps_jCboVendedor, rps_jCboFormaPagamento);
        Util.habilitar(false, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        rps_jTxtCodigo.grabFocus();
        incluir = true;
        controllerVenProd.setList(new ArrayList());
    }//GEN-LAST:event_rps_jBtnIncluirActionPerformed

    private void rps_jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnAlterarActionPerformed
        if (rps_jTxtCodigo.getText().trim().isEmpty()) {
            Util.mensagem("Pesquise uma Vendas antes de Alterar");
            return;
        }
        Util.habilitar(true, rps_jBtnAlterarProd, rps_jBtnExcluirProd, rps_jBtnIncluirProd, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jFmtDataVenda, rps_jCboClientes, rps_jTxtDesconto, rps_jTxtTotal, rps_jCboVendedor, rps_jCboFormaPagamento);
        Util.habilitar(false, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.habilitar(false, rps_jTxtCodigo);
        rps_jFmtDataVenda.grabFocus();
        incluir = false;
    }//GEN-LAST:event_rps_jBtnAlterarActionPerformed

    private void rps_jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnExcluirActionPerformed
        if (rps_jTxtCodigo.getText().trim().isEmpty()) {
            Util.mensagem("Pesquise uma Venda antes de Excluir");
            return;
        }

        if (Util.pergunta("Deseja excluir ?") == true) {
            VendasDAO rpsVendasDAO = new VendasDAO();
            VendasProdutosDAO vendasProdutosDAO = new VendasProdutosDAO();
            for (int ind = 0; ind < jTable1.getRowCount(); ind++) {
                RpsVendasProdutos rpsVendasProdutos = controllerVenProd.getBean(ind);
                vendasProdutosDAO.delete(rpsVendasProdutos);
            }
            rpsVendasDAO.delete(viewBean());
        }
        Util.limpar(rps_jTxtCodigo, rps_jFmtDataVenda, rps_jCboClientes, rps_jTxtDesconto, rps_jTxtTotal, rps_jCboVendedor, rps_jCboFormaPagamento);
        controllerVenProd.setList(new ArrayList());
    }//GEN-LAST:event_rps_jBtnExcluirActionPerformed

    private void rps_jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnConfirmarActionPerformed
        // TODO add your handling code here:
        VendasDAO vendasDAO = new VendasDAO();
        VendasProdutosDAO vendasProdutosDAO = new VendasProdutosDAO();
        RpsVendas rpsVendas = viewBean();
        if (incluir == true) {
            vendasDAO.insert(rpsVendas);
            for (int ind = 0; ind < jTable1.getRowCount(); ind++) {
                RpsVendasProdutos rpsVendasProdutos = controllerVenProd.getBean(ind);
                rpsVendasProdutos.setRpsVendas(rpsVendas);
                vendasProdutosDAO.insert(rpsVendasProdutos);
            }
        } else {
            vendasDAO.update(rpsVendas);
            //excluo todos os pedidos produtos do pedido
            vendasProdutosDAO.deleteProdutos(rpsVendas);
            //incluo os pedidos produtos
            for (int ind = 0; ind < jTable1.getRowCount(); ind++) {
                RpsVendasProdutos rpsVendasProdutos = controllerVenProd.getBean(ind);
                rpsVendasProdutos.setRpsVendas(rpsVendas);
                vendasProdutosDAO.insert(rpsVendasProdutos);
            }
        }
        Util.habilitar(false, rps_jBtnAlterarProd, rps_jBtnExcluirProd, rps_jBtnIncluirProd, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jFmtDataVenda, rps_jCboClientes, rps_jTxtDesconto, rps_jTxtDesconto, rps_jTxtTotal, rps_jCboVendedor, rps_jCboFormaPagamento);
        Util.habilitar(true, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.limpar(rps_jTxtCodigo, rps_jFmtDataVenda, rps_jCboClientes, rps_jTxtDesconto, rps_jTxtTotal, rps_jCboVendedor, rps_jCboFormaPagamento);
        controllerVenProd.setList(new ArrayList());
    }//GEN-LAST:event_rps_jBtnConfirmarActionPerformed

    private void rps_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnCancelarActionPerformed
        Util.habilitar(false, rps_jBtnAlterarProd, rps_jBtnExcluirProd, rps_jBtnIncluirProd, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigo, rps_jFmtDataVenda, rps_jCboClientes, rps_jTxtDesconto, rps_jTxtTotal, rps_jCboVendedor, rps_jCboFormaPagamento);
        Util.habilitar(true, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.limpar(rps_jTxtCodigo, rps_jFmtDataVenda, rps_jCboClientes, rps_jTxtDesconto, rps_jTxtTotal, rps_jCboVendedor, rps_jCboFormaPagamento);
        controllerVenProd.setList(new ArrayList());
    }//GEN-LAST:event_rps_jBtnCancelarActionPerformed

    private void rps_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnPesquisarActionPerformed
        JDlgVendasPesquisar jDlgVendasPesquisar = new JDlgVendasPesquisar(null, true);
        jDlgVendasPesquisar.setTelaAnterior(this);
        jDlgVendasPesquisar.setVisible(true);
        pesquisado = true;
    }//GEN-LAST:event_rps_jBtnPesquisarActionPerformed

    private void rps_jBtnIncluirProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnIncluirProdActionPerformed
        JDlgVendasProdutos jDlgVendasProdutos = new JDlgVendasProdutos(null, true);
        jDlgVendasProdutos.setTelaAnterior(this, null);
        jDlgVendasProdutos.setVisible(true);
        calcularTotal(); // ADICIONA AQUI
    }//GEN-LAST:event_rps_jBtnIncluirProdActionPerformed

    private void rps_jBtnAlterarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnAlterarProdActionPerformed
        // TODO add your handling code here:
        JDlgVendasProdutos jDlgVendasProdutos = new JDlgVendasProdutos(null, true);
        RpsVendasProdutos rpsVendasProdutos = controllerVenProd.getBean(jTable1.getSelectedRow());
        jDlgVendasProdutos.setTelaAnterior(this, rpsVendasProdutos);
        jDlgVendasProdutos.setVisible(true);
    }//GEN-LAST:event_rps_jBtnAlterarProdActionPerformed

    private void rps_jBtnExcluirProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnExcluirProdActionPerformed
        if (jTable1.getSelectedRow() == -1) {
            Util.mensagem("Precisa selecionar uma linha!");
        } else {
            if (Util.pergunta("Deseja excluir o produto ?") == true) {
                controllerVenProd.removeBean(jTable1.getSelectedRow());
                calcularTotal(); // ADICIONA AQUI
            }
        }
    }//GEN-LAST:event_rps_jBtnExcluirProdActionPerformed

    private void rps_jTxtDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rps_jTxtDescontoKeyReleased
        calcularTotal(); // ADICIONA NO FINAL
    }//GEN-LAST:event_rps_jTxtDescontoKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            rps_jBtnAlterarProdActionPerformed(null);
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgVendas dialog = new JDlgVendas(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton rps_jBtnAlterar;
    private javax.swing.JButton rps_jBtnAlterarProd;
    private javax.swing.JButton rps_jBtnCancelar;
    private javax.swing.JButton rps_jBtnConfirmar;
    private javax.swing.JButton rps_jBtnExcluir;
    private javax.swing.JButton rps_jBtnExcluirProd;
    private javax.swing.JButton rps_jBtnIncluir;
    private javax.swing.JButton rps_jBtnIncluirProd;
    private javax.swing.JButton rps_jBtnPesquisar;
    private javax.swing.JComboBox<RpsClientes> rps_jCboClientes;
    private javax.swing.JComboBox<String> rps_jCboFormaPagamento;
    private javax.swing.JComboBox<RpsVendedor> rps_jCboVendedor;
    private javax.swing.JFormattedTextField rps_jFmtDataVenda;
    private javax.swing.JTextField rps_jTxtCodigo;
    private javax.swing.JTextField rps_jTxtDesconto;
    private javax.swing.JTextField rps_jTxtTotal;
    // End of variables declaration//GEN-END:variables
}
