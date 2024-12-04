package GUI;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import Classes.*;

public class PainelClienteGUI extends JFrame {
    private JLabel mensagemBoasVindas;
    private JTextField consumoMensalField;
    private JButton solicitarButton;
    private JList<Classes.Orcamento> listaOrcamentos;
    private DefaultListModel<Classes.Orcamento> orcamentoListModel;
    private JButton aprovarButton;
    private JButton recusarButton;
    private JButton deslogarButton;
    private JButton atualizarOrcamentosButton;
    private Classes.Cliente cliente;
    private List<Classes.KitSolar> kitsDisponiveis;
    private List<Classes.Orcamento> orcamentosCliente;
    private MainGUI mainGUI;
    private List<Classes.Orcamento> orcamentosGerais;

    public PainelClienteGUI(Classes.Cliente cliente, List<Classes.KitSolar> kits, List<Classes.Orcamento> orcamentosGerais, MainGUI mainGUI) {
        this.cliente = cliente;
        this.kitsDisponiveis = kits;
        this.orcamentosGerais = orcamentosGerais;
        this.orcamentosCliente = new ArrayList<>();

        setTitle("Painel do Cliente");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        mensagemBoasVindas = new JLabel("Bem-vindo, " + cliente.getNome() + "!", SwingConstants.CENTER);
        mensagemBoasVindas.setFont(new Font("Arial", Font.BOLD, 16));
        add(mensagemBoasVindas, BorderLayout.NORTH);

        JPanel centroPanel = new JPanel();
        centroPanel.setLayout(new GridLayout(3, 1));

        JPanel solicitacaoPanel = new JPanel();
        solicitacaoPanel.setBorder(BorderFactory.createTitledBorder("Solicitar Projeto"));
        solicitacaoPanel.setLayout(new FlowLayout());

        consumoMensalField = new JTextField(10);
        solicitarButton = new JButton("Solicitar Orçamentos");

        solicitacaoPanel.add(new JLabel("Consumo Médio Mensal (kWh):"));
        solicitacaoPanel.add(consumoMensalField);
        solicitacaoPanel.add(solicitarButton);

        JPanel orcamentoPanel = new JPanel();
        orcamentoPanel.setBorder(BorderFactory.createTitledBorder("Orçamentos Recebidos"));
        orcamentoPanel.setLayout(new BorderLayout());

        orcamentoListModel = new DefaultListModel<>();
        listaOrcamentos = new JList<>(orcamentoListModel);
        JScrollPane scrollPane = new JScrollPane(listaOrcamentos);

        JPanel botoesOrcamentoPanel = new JPanel();
        aprovarButton = new JButton("Aprovar");
        recusarButton = new JButton("Recusar");

        aprovarButton.setEnabled(false);
        recusarButton.setEnabled(false);

        botoesOrcamentoPanel.add(aprovarButton);
        botoesOrcamentoPanel.add(recusarButton);

        orcamentoPanel.add(scrollPane, BorderLayout.CENTER);
        orcamentoPanel.add(botoesOrcamentoPanel, BorderLayout.SOUTH);

        centroPanel.add(solicitacaoPanel);
        centroPanel.add(orcamentoPanel);
        add(centroPanel, BorderLayout.CENTER);

        deslogarButton = new JButton("Deslogar");
        atualizarOrcamentosButton = new JButton("Atualizar Orçamentos");

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        southPanel.add(atualizarOrcamentosButton);
        southPanel.add(deslogarButton);
        add(southPanel, BorderLayout.SOUTH);

        solicitarButton.addActionListener(e -> {
            try {
                String consumoStr = consumoMensalField.getText().trim();

                if (consumoStr.isEmpty()) {
                    throw new Exception("O campo 'Consumo Médio Mensal' não pode estar vazio.");
                }

                double consumoMensal = Double.parseDouble(consumoStr);

                Classes.Orcamento novoOrcamento = new Classes.Orcamento(cliente, consumoMensal);
                orcamentosGerais.add(novoOrcamento);

                JOptionPane.showMessageDialog(this, "A empresa foi notificada e seus orçamentos ficarão prontos em breve!", "Solicitação Enviada", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        aprovarButton.addActionListener(e -> {
            Classes.Orcamento orcamentoSelecionado = listaOrcamentos.getSelectedValue();
            if (orcamentoSelecionado != null) {
                if (orcamentoSelecionado.getStatus().equals("Aprovado") || orcamentoSelecionado.getStatus().equals("Recusado")) {
                    JOptionPane.showMessageDialog(this, "Esse projeto já foi aprovado/recusado por você.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    orcamentoSelecionado.aprovar();
                    listaOrcamentos.repaint();
                    JOptionPane.showMessageDialog(this, "Orçamento aprovado!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um orçamento.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        recusarButton.addActionListener(e -> {
            Classes.Orcamento orcamentoSelecionado = listaOrcamentos.getSelectedValue();
            if (orcamentoSelecionado != null) {
                if (orcamentoSelecionado.getStatus().equals("Aprovado") || orcamentoSelecionado.getStatus().equals("Recusado")) {
                    JOptionPane.showMessageDialog(this, "Esse projeto já foi aprovado/recusado por você.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    orcamentoSelecionado.recusar();
                    listaOrcamentos.repaint();
                    JOptionPane.showMessageDialog(this, "Orçamento recusado.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um orçamento.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        deslogarButton.addActionListener(e -> {
            dispose();
            mainGUI.setVisible(true);
        });

        // Ação do botão de "Atualizar Orçamentos"
        atualizarOrcamentosButton.addActionListener(e -> {
            List<Classes.Orcamento> orcamentosRecebidos = obterOrcamentosParaCliente(cliente);
            System.out.println("Orçamentos Recebidos " + orcamentosRecebidos);

            // Filtra os orçamentos que não estão com status "Pendente"
            List<Classes.Orcamento> orcamentosProntos = new ArrayList<>();
            for (Classes.Orcamento orcamento : orcamentosRecebidos) {
                if (orcamento.getStatus().equals("Orçamento enviado")) {
                    orcamentosProntos.add(orcamento);
                }
            }

            // Se não houver orçamentos prontos, exibe mensagem
            if (orcamentosProntos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ainda não há orçamentos disponíveis.", "Orçamentos", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe apenas os orçamentos prontos
                receberOrcamentos(orcamentosProntos);
                JOptionPane.showMessageDialog(this, "Orçamentos foram enviados pela empresa!", "Orçamentos Recebidos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void receberOrcamentos(List<Classes.Orcamento> orcamentosRecebidos) {
        orcamentoListModel.clear();
        orcamentosCliente.clear();
        orcamentosCliente.addAll(orcamentosRecebidos);
        for (Classes.Orcamento orc : orcamentosRecebidos) {
            orcamentoListModel.addElement(orc);
        }
        aprovarButton.setEnabled(true);
        recusarButton.setEnabled(true);
    }

    private List<Classes.Orcamento> obterOrcamentosParaCliente(Classes.Cliente cliente) {
        List<Classes.Orcamento> orcamentosParaCliente = new ArrayList<>();
        System.out.println("Orçamentos Gerais " + orcamentosGerais);

        for (Classes.Orcamento orcamento : orcamentosGerais) {
            if (orcamento.getCliente().equals(cliente)) {
                orcamentosParaCliente.add(orcamento);
            }
        }
        System.out.println("Orçamentos para Clientes " + orcamentosParaCliente);
        return orcamentosParaCliente;
    }

    public JLabel getMensagemBoasVindas() {
        return mensagemBoasVindas;
    }

    public void setMensagemBoasVindas(JLabel mensagemBoasVindas) {
        this.mensagemBoasVindas = mensagemBoasVindas;
    }

    public JTextField getConsumoMensalField() {
        return consumoMensalField;
    }

    public void setConsumoMensalField(JTextField consumoMensalField) {
        this.consumoMensalField = consumoMensalField;
    }

    public JButton getSolicitarButton() {
        return solicitarButton;
    }

    public void setSolicitarButton(JButton solicitarButton) {
        this.solicitarButton = solicitarButton;
    }

    public JList<Classes.Orcamento> getListaOrcamentos() {
        return listaOrcamentos;
    }

    public void setListaOrcamentos(JList<Classes.Orcamento> listaOrcamentos) {
        this.listaOrcamentos = listaOrcamentos;
    }

    public DefaultListModel<Classes.Orcamento> getOrcamentoListModel() {
        return orcamentoListModel;
    }

    public void setOrcamentoListModel(DefaultListModel<Classes.Orcamento> orcamentoListModel) {
        this.orcamentoListModel = orcamentoListModel;
    }

    public JButton getAprovarButton() {
        return aprovarButton;
    }

    public void setAprovarButton(JButton aprovarButton) {
        this.aprovarButton = aprovarButton;
    }

    public JButton getRecusarButton() {
        return recusarButton;
    }

    public void setRecusarButton(JButton recusarButton) {
        this.recusarButton = recusarButton;
    }

    public JButton getDeslogarButton() {
        return deslogarButton;
    }

    public void setDeslogarButton(JButton deslogarButton) {
        this.deslogarButton = deslogarButton;
    }

    public JButton getAtualizarOrcamentosButton() {
        return atualizarOrcamentosButton;
    }

    public void setAtualizarOrcamentosButton(JButton atualizarOrcamentosButton) {
        this.atualizarOrcamentosButton = atualizarOrcamentosButton;
    }

    public Classes.Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Classes.Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Classes.KitSolar> getKitsDisponiveis() {
        return kitsDisponiveis;
    }

    public void setKitsDisponiveis(List<Classes.KitSolar> kitsDisponiveis) {
        this.kitsDisponiveis = kitsDisponiveis;
    }

    public List<Classes.Orcamento> getOrcamentosCliente() {
        return orcamentosCliente;
    }

    public void setOrcamentosCliente(List<Classes.Orcamento> orcamentosCliente) {
        this.orcamentosCliente = orcamentosCliente;
    }

    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public List<Classes.Orcamento> getOrcamentosGerais() {
        return orcamentosGerais;
    }

    public void setOrcamentosGerais(List<Classes.Orcamento> orcamentosGerais) {
        this.orcamentosGerais = orcamentosGerais;
    }
}