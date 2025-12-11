package view;

import bean.RpsProdutos;
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
import dao.ProdutosDAO;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class JDlgProdutos extends javax.swing.JDialog {

    class ImageComboBoxRenderer extends JLabel implements ListCellRenderer<String> {

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list,
                String value, int index,
                boolean isSelected,
                boolean cellHasFocus) {

            // ESSA É A LINHA IMPORTANTE - Verifica se value não é null
            if (value == null) {
                setText("");
                setIcon(null);
                return this;
            }

            setText(value);

            try {
                // Define o ícone baseado no texto - com try-catch para evitar erros
                if (value.toLowerCase().contains("simulação")) {
                    setIcon(carregarIcone("/img/simulação.png"));
                } else if (value.toLowerCase().contains("aventura")) {
                    setIcon(carregarIcone("/img/aventura.png"));
                } else if (value.toLowerCase().contains("mmo")) {
                    setIcon(carregarIcone("/img/mmo.png"));
                } else if (value.toLowerCase().contains("stealth")) {
                    setIcon(carregarIcone("/img/stealth.png"));
                } else if (value.toLowerCase().contains("puzzle")) {
                    setIcon(carregarIcone("/img/puzzle.png"));
                } else if (value.toLowerCase().contains("sandbox")) {
                    setIcon(carregarIcone("/img/sandbox.png"));
                } else if (value.toLowerCase().contains("moba")) {
                    setIcon(carregarIcone("/img/moba.png"));
                } else if (value.toLowerCase().contains("battle royale")) {
                    setIcon(carregarIcone("/img/battle.png"));
                } else if (value.toLowerCase().contains("ação")) {
                    setIcon(carregarIcone("/img/acao.png"));
                } else if (value.toLowerCase().contains("estratégia")) {
                    setIcon(carregarIcone("/img/estratégia.png"));
                } else if (value.toLowerCase().contains("esportes")) {
                    setIcon(carregarIcone("/img/esportes.png"));
                } else if (value.toLowerCase().contains("rpg")) {
                    setIcon(carregarIcone("/img/rpg.png"));
                } else if (value.toLowerCase().contains("corrida")) {
                    setIcon(carregarIcone("/img/corrida.png"));
                } else if (value.toLowerCase().contains("tiro") || value.toLowerCase().contains("shooter")) {
                    setIcon(carregarIcone("/img/tiro.png"));
                } else if (value.toLowerCase().contains("terror") || value.toLowerCase().contains("horror")) {
                    setIcon(carregarIcone("/img/terror.png"));
                } else if (value.toLowerCase().contains("luta") || value.toLowerCase().contains("fighting")) {
                    setIcon(carregarIcone("/img/luta.png"));
                } else if (value.toLowerCase().contains("pc")) {
                    setIcon(carregarIcone("/img/pc.png"));
                } else if (value.toLowerCase().contains("console")) {
                    setIcon(carregarIcone("/img/console.png"));
                } else if (value.toLowerCase().contains("mobile")) {
                    setIcon(carregarIcone("/img/mobile.png"));
                } else if (value.toLowerCase().contains("plataforma")) {
                    setIcon(carregarIcone("/img/platform.png"));
                } else if (value.toLowerCase().contains("navegador")) {
                    setIcon(carregarIcone("/img/navegador.png"));
                } else if (value.toLowerCase().contains("arcade")) {
                    setIcon(carregarIcone("/img/arcade.png"));
                } else if (value.toLowerCase().contains("vr")) {
                    setIcon(carregarIcone("/img/VR.png"));
                } else if (value.toLowerCase().contains("nuvem")) {
                    setIcon(carregarIcone("/img/Nuvem.png"));
                } else if (value.toLowerCase().contains("Cross-Platform (Multiplataforma)")) {
                    setIcon(carregarIcone("/img/plataform.png"));
                } else {
                    // Se não encontrar imagem, não coloca ícone
                    setIcon(carregarIcone("/img/caixa.png"));
                }
            } catch (Exception e) {
                // Em caso de erro, apenas não mostra ícone
                setIcon(null);
            }

            // Configurações visuais
            if (isSelected) {
                setBackground(new Color(200, 220, 255));
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }

            setOpaque(true);
            setFont(new Font("Segoe UI", Font.PLAIN, 12));
            setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            return this;
        }

        private ImageIcon carregarIcone(String caminho) {
            try {
                java.net.URL imgURL = getClass().getResource(caminho);
                if (imgURL != null) {
                    ImageIcon icon = new ImageIcon(imgURL);
                    // Redimensiona para 20x20 pixels
                    Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    return new ImageIcon(img);
                }
            } catch (Exception e) {
                // Ignora erro - retorna null
            }
            return null;
        }
    }
    private boolean incluir;
    private MaskFormatter mascaraDataNasc;

    public JDlgProdutos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        try {
            rps_jCboGeneroJogo.setRenderer(new ImageComboBoxRenderer());
            rps_jCboPlataforma.setRenderer(new ImageComboBoxRenderer());
            // Ajusta a altura dos itens
            rps_jCboGeneroJogo.setMaximumRowCount(8);
            rps_jCboPlataforma.setMaximumRowCount(8);
        } catch (Exception e) {
            System.err.println("Erro ao configurar combo boxes: " + e.getMessage());
            // Se der erro, continua sem o renderizador
        }
        jLabel1.setForeground(Color.BLACK);
        jLabel2.setForeground(Color.BLACK);
        jLabel3.setForeground(Color.BLACK);
        jLabel4.setForeground(Color.BLACK);
        jLabel5.setForeground(Color.BLACK);
        jLabel6.setForeground(Color.BLACK);
        jLabel7.setForeground(Color.BLACK);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/img/telaProduto.jpg"));
        Image image = backgroundIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(background);
        getContentPane().setComponentZOrder(background, getContentPane().getComponentCount() - 1);
        Util.habilitar(false, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigoJogo, rps_jTxtNomeJogo, rps_jTxtQuantEstoque, rps_jFmtAnoLançamento, rps_jFmtValor, rps_jCboGeneroJogo, rps_jCboPlataforma);
        try {
            mascaraDataNasc = new MaskFormatter("##/##/####");
            rps_jFmtAnoLançamento.setFormatterFactory(new DefaultFormatterFactory(mascaraDataNasc));
        } catch (ParseException ex) {
            Logger.getLogger(JDlgProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }

        // === FORMATAÇÃO AUTOMÁTICA DE SALDO COM R$ ===
        rps_jFmtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                // Remove tudo que não for número
                String texto = rps_jFmtValor.getText().replaceAll("[^0-9]", "");

                if (texto.isEmpty()) {
                    rps_jFmtValor.setText("R$ 0,00");
                    return;
                }

                // Garante pelo menos 3 dígitos (para manter 2 casas decimais)
                while (texto.length() < 3) {
                    texto = "0" + texto;
                }

                try {
                    double valor = Double.parseDouble(texto) / 100.0;
                    java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
                    rps_jFmtValor.setText("R$ " + df.format(valor));
                } catch (NumberFormatException e) {
                    // em caso de erro de conversão, reseta o campo
                    rps_jFmtValor.setText("R$ 0,00");
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
        rps_jFmtValor.setText("R$ 0,00");
        rps_jFmtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        rps_jFmtValor.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        rps_jFmtValor.setForeground(new java.awt.Color(34, 139, 34)); // verde "saldo positivo"

        iniciarRelogio("Cadastro de Produtos"); // coloque o nome do usuário logado aqui
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

    public void beanView(RpsProdutos produtos) {
        rps_jTxtCodigoJogo.setText(Util.intToStr(produtos.getRpsIdJogo()));
        rps_jTxtNomeJogo.setText(produtos.getRpsNome());
        rps_jTxtQuantEstoque.setText(Util.intToStr(produtos.getRpsQuantEstoque()));
        rps_jFmtValor.setText(Util.doubleToStr(produtos.getRpsValor()));
        rps_jFmtAnoLançamento.setText(Util.dateToStr(produtos.getRpsAnoLancamento()));
        rps_jCboGeneroJogo.setSelectedIndex(produtos.getRpsGenero());
        rps_jCboPlataforma.setSelectedIndex(produtos.getRpsPlataforma());
    }

    public RpsProdutos viewBean() {
        RpsProdutos Rpsprodutos = new RpsProdutos();
        int cod = Util.strToInt(rps_jTxtCodigoJogo.getText());
        Rpsprodutos.setRpsIdJogo(cod);
        Rpsprodutos.setRpsNome(rps_jTxtNomeJogo.getText());
        int quantidade = Util.strToInt(rps_jTxtQuantEstoque.getText());
        Rpsprodutos.setRpsQuantEstoque(quantidade);
        Rpsprodutos.setRpsValor(Util.strToDouble(rps_jFmtValor.getText()));
        Rpsprodutos.setRpsAnoLancamento(Util.strToDate(rps_jFmtAnoLançamento.getText()));
        Rpsprodutos.setRpsGenero(rps_jCboGeneroJogo.getSelectedIndex());
        Rpsprodutos.setRpsPlataforma(rps_jCboPlataforma.getSelectedIndex());
        return Rpsprodutos;
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
        jLabel1 = new javax.swing.JLabel();
        rps_jTxtCodigoJogo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rps_jTxtNomeJogo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rps_jCboGeneroJogo = new javax.swing.JComboBox<>();
        rps_jCboPlataforma = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        rps_jFmtAnoLançamento = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rps_jTxtQuantEstoque = new javax.swing.JTextField();
        rps_jFmtValor = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jLabel1.setDisplayedMnemonic('c');
        jLabel1.setText("Código do Jogo");

        rps_jTxtCodigoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jTxtCodigoJogoActionPerformed(evt);
            }
        });

        jLabel2.setDisplayedMnemonic('n');
        jLabel2.setText("Nome do Jogo");

        rps_jTxtNomeJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jTxtNomeJogoActionPerformed(evt);
            }
        });

        jLabel3.setDisplayedMnemonic('g');
        jLabel3.setText("Gênero do Jogo");

        jLabel4.setDisplayedMnemonic('p');
        jLabel4.setText("Plataforma");

        rps_jCboGeneroJogo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ação", "Aventura", "RPG (Role-Playing Game)", "Tiro (Shooter)", "Plataforma", "Corrida", "Luta (Fighting)", "Estratégia", "Esportes", "Simulação", "Terror (Horror)", "Puzzle / Quebra-cabeça", "Sandbox / Mundo Aberto", "MOBA (Multiplayer Online Battle Arena)", "Battle Royale", "MMO (Massively Multiplayer Online)", "Stealth (Furtividade)" }));
        rps_jCboGeneroJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jCboGeneroJogoActionPerformed(evt);
            }
        });

        rps_jCboPlataforma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PC", "Consoles", "Mobile", "Navegador", "Arcade", "VR (Realidade Virtual)", "Nuvem (Cloud Gaming)", "Cross-Platform (Multiplataforma)" }));

        jLabel5.setDisplayedMnemonic('a');
        jLabel5.setText("Ano de Lançamento");

        rps_jFmtAnoLançamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jFmtAnoLançamentoActionPerformed(evt);
            }
        });

        jLabel6.setDisplayedMnemonic('q');
        jLabel6.setText("Quantidade no Estoque");

        jLabel7.setDisplayedMnemonic('v');
        jLabel7.setText("Valor");

        rps_jFmtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rps_jFmtValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rps_jTxtNomeJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rps_jBtnIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rps_jBtnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rps_jBtnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rps_jBtnConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rps_jBtnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rps_jBtnPesquisar))
                    .addComponent(rps_jTxtCodigoJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(rps_jFmtAnoLançamento, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rps_jCboPlataforma, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4)
                            .addComponent(rps_jCboGeneroJogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(rps_jTxtQuantEstoque)
                            .addComponent(rps_jFmtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rps_jTxtCodigoJogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rps_jTxtNomeJogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jCboGeneroJogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jTxtQuantEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rps_jCboPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rps_jFmtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rps_jFmtAnoLançamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void rps_jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnIncluirActionPerformed
        Util.habilitar(true, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigoJogo, rps_jTxtNomeJogo, rps_jTxtQuantEstoque, rps_jFmtAnoLançamento, rps_jFmtValor, rps_jCboGeneroJogo, rps_jCboPlataforma);
        Util.limpar(rps_jTxtCodigoJogo, rps_jTxtNomeJogo, rps_jTxtQuantEstoque, rps_jFmtAnoLançamento, rps_jFmtValor, rps_jCboGeneroJogo, rps_jCboPlataforma);
        Util.habilitar(false, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        rps_jTxtCodigoJogo.grabFocus();
        incluir = true;
    }//GEN-LAST:event_rps_jBtnIncluirActionPerformed

    private void rps_jTxtCodigoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jTxtCodigoJogoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jTxtCodigoJogoActionPerformed

    private void rps_jTxtNomeJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jTxtNomeJogoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jTxtNomeJogoActionPerformed

    private void rps_jCboGeneroJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jCboGeneroJogoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jCboGeneroJogoActionPerformed

    private void rps_jFmtAnoLançamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jFmtAnoLançamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jFmtAnoLançamentoActionPerformed

    private void rps_jFmtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jFmtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rps_jFmtValorActionPerformed

    private void rps_jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnConfirmarActionPerformed
        ProdutosDAO produtosDAO = new ProdutosDAO();
        RpsProdutos Rpsprodutos = viewBean();
        if (incluir == true) {
            produtosDAO.insert(Rpsprodutos);

        } else {
            produtosDAO.update(Rpsprodutos);
        }
        Util.habilitar(false, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigoJogo, rps_jTxtNomeJogo, rps_jTxtQuantEstoque, rps_jFmtAnoLançamento, rps_jFmtValor, rps_jCboGeneroJogo, rps_jCboPlataforma);
        Util.habilitar(true, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.limpar(rps_jTxtCodigoJogo, rps_jTxtNomeJogo, rps_jTxtQuantEstoque, rps_jFmtAnoLançamento, rps_jFmtValor, rps_jCboGeneroJogo, rps_jCboPlataforma);
    }//GEN-LAST:event_rps_jBtnConfirmarActionPerformed

    private void rps_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnCancelarActionPerformed
        Util.habilitar(false, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigoJogo, rps_jTxtNomeJogo, rps_jTxtQuantEstoque, rps_jFmtAnoLançamento, rps_jFmtValor, rps_jCboGeneroJogo, rps_jCboPlataforma);
        Util.habilitar(true, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.limpar(rps_jTxtCodigoJogo, rps_jTxtNomeJogo, rps_jTxtQuantEstoque, rps_jFmtAnoLançamento, rps_jFmtValor, rps_jCboGeneroJogo, rps_jCboPlataforma);
    }//GEN-LAST:event_rps_jBtnCancelarActionPerformed

    private void rps_jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnAlterarActionPerformed
        if (rps_jTxtCodigoJogo.getText().trim().isEmpty()) {
            Util.mensagem("Pesquise um Produto antes de Alterar");
            return;
        }
        Util.habilitar(true, rps_jBtnConfirmar, rps_jBtnCancelar, rps_jTxtCodigoJogo, rps_jTxtNomeJogo, rps_jTxtQuantEstoque, rps_jFmtAnoLançamento, rps_jFmtValor, rps_jCboGeneroJogo, rps_jCboPlataforma);
        Util.habilitar(false, rps_jBtnIncluir, rps_jBtnExcluir, rps_jBtnAlterar, rps_jBtnPesquisar);
        Util.habilitar(false, rps_jTxtCodigoJogo);
        rps_jTxtNomeJogo.grabFocus();
        incluir = false;
    }//GEN-LAST:event_rps_jBtnAlterarActionPerformed

    private void rps_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnPesquisarActionPerformed
        JDlgProdutosPesquisar jDlgProdutosPesquisar = new JDlgProdutosPesquisar(null, true);
        jDlgProdutosPesquisar.setTelaAnterior(this);
        jDlgProdutosPesquisar.setVisible(true);
    }//GEN-LAST:event_rps_jBtnPesquisarActionPerformed

    private void rps_jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rps_jBtnExcluirActionPerformed
        if (rps_jTxtCodigoJogo.getText().trim().isEmpty()) {
            Util.mensagem("Pesquise um Produto antes de Excluir");
            return;
        }

        if (Util.pergunta("Deseja excluir ?") == true) {
            ProdutosDAO produtosDAO = new ProdutosDAO();
            RpsProdutos rpsProdutos = viewBean();
            produtosDAO.delete(rpsProdutos);
        }
        Util.limpar(rps_jTxtCodigoJogo, rps_jTxtNomeJogo, rps_jTxtQuantEstoque, rps_jFmtAnoLançamento, rps_jFmtValor, rps_jCboGeneroJogo, rps_jCboPlataforma);
    }//GEN-LAST:event_rps_jBtnExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgProdutos dialog = new JDlgProdutos(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> rps_jCboGeneroJogo;
    private javax.swing.JComboBox<String> rps_jCboPlataforma;
    private javax.swing.JFormattedTextField rps_jFmtAnoLançamento;
    private javax.swing.JFormattedTextField rps_jFmtValor;
    private javax.swing.JTextField rps_jTxtCodigoJogo;
    private javax.swing.JTextField rps_jTxtNomeJogo;
    private javax.swing.JTextField rps_jTxtQuantEstoque;
    // End of variables declaration//GEN-END:variables

}
