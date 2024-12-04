package GUI;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.List;
import Classes.*;

public class PainelEmpresaGUI extends JFrame {
    private JButton cadastrarFabricanteButton;
    private JButton cadastrarKitButton;
    private JButton visualizarSolicitacoesButton;
    private JButton gerarRelatorioButton;
    private JButton deslogarButton;
    private List<Classes.Fabricante> fabricantes;
    private List<Classes.Orcamento> orcamentos;
    private List<Classes.Cliente> clientes;
    private List<KitSolar> kits;
    private List<PainelClienteGUI> painelClienteGUIS;
    private MainGUI mainGUI;

    public PainelEmpresaGUI(List<Classes.Fabricante> fabricantes, List<Classes.Orcamento> orcamentos, List<Classes.Cliente> clientes, List<KitSolar> kits, List<PainelClienteGUI> painelClienteGUIS, MainGUI mainGUI) {
        this.fabricantes = fabricantes;
        this.orcamentos = orcamentos;
        this.clientes = clientes;
        this.kits = kits;
        this.mainGUI = mainGUI;
        this.painelClienteGUIS = painelClienteGUIS;

        setTitle("Painel da Empresa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        cadastrarFabricanteButton = new JButton("Cadastrar Fabricante");
        cadastrarKitButton = new JButton("Cadastrar Kit Solar");
        visualizarSolicitacoesButton = new JButton("Gerenciar Solicitações");
        gerarRelatorioButton = new JButton("Gerar Relatórios");
        deslogarButton = new JButton("Deslogar");

        add(cadastrarFabricanteButton);
        add(cadastrarKitButton);
        add(visualizarSolicitacoesButton);
        add(gerarRelatorioButton);
        add(deslogarButton);

        // Ação do botão "Cadastrar Fabricante"
        cadastrarFabricanteButton.addActionListener(e -> {
            CadastroFabricanteGUI cadastroFabricanteGUI = new CadastroFabricanteGUI(fabricantes);
            cadastroFabricanteGUI.setVisible(true);
        });

        // Ação do botão "Cadastrar Kit Solar"
        cadastrarKitButton.addActionListener(e -> {
            CadastroKitGUI cadastroKitGUI = new CadastroKitGUI(fabricantes, kits); // Atualizado para usar kits
            cadastroKitGUI.setVisible(true);
        });

        // Ação do botão "Gerenciar Solicitações"
        visualizarSolicitacoesButton.addActionListener(e -> {
            VisualizarSolicitacoesGUI visualizarSolicitacoesGUI = new VisualizarSolicitacoesGUI(orcamentos, kits, painelClienteGUIS);
            visualizarSolicitacoesGUI.setVisible(true);
        });


        // Ação do botão "Gerar Relatórios"
        gerarRelatorioButton.addActionListener(e -> {
            String[] opcoesRelatorio = {"Relatório de Projetos", "Relatório de Clientes", "Relatório de Equipamentos", "Relatório Financeiro"};
            String opcaoSelecionada = (String) JOptionPane.showInputDialog(null, "Selecione o tipo de relatório:", "Gerar Relatório",
                    JOptionPane.QUESTION_MESSAGE, null, opcoesRelatorio, opcoesRelatorio[0]);

            if (opcaoSelecionada != null) {
                Classes.Relatorio relatorio = new Classes.Relatorio();

                switch (opcaoSelecionada) {
                    case "Relatório de Projetos":
                        JOptionPane.showMessageDialog(null, relatorio.gerarRelatorioProjetos(orcamentos), "Relatório de Projetos", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "Relatório de Clientes":
                        JOptionPane.showMessageDialog(null, relatorio.gerarRelatorioClientes(clientes), "Relatório de Clientes", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "Relatório de Equipamentos":
                        JOptionPane.showMessageDialog(null, relatorio.gerarRelatorioEquipamentos(kits), "Relatório de Equipamentos", JOptionPane.INFORMATION_MESSAGE); // Usando kits
                        break;
                    case "Relatório Financeiro":
                        JOptionPane.showMessageDialog(null, relatorio.gerarRelatorioFinanceiro(orcamentos), "Relatório Financeiro", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
        });

        deslogarButton.addActionListener(e -> {
            dispose();
            mainGUI.setVisible(true);
        });
    }

    public JButton getCadastrarFabricanteButton() {
        return cadastrarFabricanteButton;
    }

    public void setCadastrarFabricanteButton(JButton cadastrarFabricanteButton) {
        this.cadastrarFabricanteButton = cadastrarFabricanteButton;
    }

    public JButton getCadastrarKitButton() {
        return cadastrarKitButton;
    }

    public void setCadastrarKitButton(JButton cadastrarKitButton) {
        this.cadastrarKitButton = cadastrarKitButton;
    }

    public JButton getVisualizarSolicitacoesButton() {
        return visualizarSolicitacoesButton;
    }

    public void setVisualizarSolicitacoesButton(JButton visualizarSolicitacoesButton) {
        this.visualizarSolicitacoesButton = visualizarSolicitacoesButton;
    }

    public JButton getGerarRelatorioButton() {
        return gerarRelatorioButton;
    }

    public void setGerarRelatorioButton(JButton gerarRelatorioButton) {
        this.gerarRelatorioButton = gerarRelatorioButton;
    }

    public JButton getDeslogarButton() {
        return deslogarButton;
    }

    public void setDeslogarButton(JButton deslogarButton) {
        this.deslogarButton = deslogarButton;
    }

    public List<Classes.Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Classes.Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }

    public List<Classes.Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Classes.Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public List<Classes.Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Classes.Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<KitSolar> getKits() {
        return kits;
    }

    public void setKits(List<KitSolar> kits) {
        this.kits = kits;
    }

    public List<PainelClienteGUI> getPainelClienteGUIS() {
        return painelClienteGUIS;
    }

    public void setPainelClienteGUIS(List<PainelClienteGUI> painelClienteGUIS) {
        this.painelClienteGUIS = painelClienteGUIS;
    }

    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
}
