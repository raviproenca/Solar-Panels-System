package GUI;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Classes.*;

public class VisualizarSolicitacoesGUI extends JFrame {
    private JList<String> listaSolicitacoes;
    private DefaultListModel<String> modeloLista;
    private List<Classes.Orcamento> orcamentos;
    private List<KitSolar> kits;
    private List<PainelClienteGUI> paineisClientes;
    public JButton dimensionarButton;

    public VisualizarSolicitacoesGUI(List<Classes.Orcamento> orcamentos, List<KitSolar> kits, List<PainelClienteGUI> paineisClientes) {
        this.orcamentos = orcamentos;
        this.kits = kits;
        this.paineisClientes = paineisClientes;

        setTitle("Visualizar Solicitações");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        dimensionarButton = new JButton("Dimensionar, orçar e notificar");
        modeloLista = new DefaultListModel<>();
        listaSolicitacoes = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaSolicitacoes);
        add(scrollPane, BorderLayout.CENTER);

        atualizarListaSolicitacoes();

        dimensionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica se há uma solicitação selecionada
                int indiceSelecionado = listaSolicitacoes.getSelectedIndex();
                if (indiceSelecionado == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione uma solicitação primeiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    Classes.Orcamento orcamentoSelecionado = orcamentos.get(indiceSelecionado);

                    // Verifica se a solicitação está pendente
                    if (!orcamentoSelecionado.getStatus().equals("Pendente")) {
                        JOptionPane.showMessageDialog(null, "A solicitação do cliente " + orcamentoSelecionado.getCliente().getNome() + " já foi dimensionada.",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        dimensionarSistemaFotovoltaico(orcamentoSelecionado);
                    }
                }
            }
        });
        add(dimensionarButton, BorderLayout.SOUTH);
    }

    public void atualizarListaSolicitacoes() {
        modeloLista.clear();
        for (Classes.Orcamento orcamento : orcamentos) {
            modeloLista.addElement("Cliente: " + orcamento.getCliente().getNome() + " - Consumo: " + orcamento.getConsumoMensal() + " kWh - Status: " + orcamento.getStatus());
        }
    }

    // Método para dimensionar o sistema fotovoltaico para um orçamento específico
    private void dimensionarSistemaFotovoltaico(Classes.Orcamento orcamento) {
        Classes.Cliente cliente = orcamento.getCliente();
        double consumoMensal = orcamento.getConsumoMensal();
        String regiao = cliente.getRegiao(); // Usar a região do cliente
        double irradiacao = obterIrradiacaoSolarPorRegiao(regiao);

        System.out.println("Iniciando dimensionamento para o cliente: " + cliente.getNome());

        KitSolar kitSelecionado = selecionarKit();
        if (kitSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Nenhum kit selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Dimensionamento abortado: Nenhum kit foi selecionado.");
            return;
        }

        System.out.println("Kit selecionado: " + kitSelecionado.getPlacaSolar().getModelo());

        // Cálculo do número de painéis necessários
        double potenciaPainel = kitSelecionado.getPlacaSolar().getCapacidade();
        System.out.println("consumo mensal: " + consumoMensal + "  irradiacao: " + irradiacao + "  potencia do painel: " + potenciaPainel + "Preço do painel: " + kitSelecionado.getPlacaSolar().getPreco() + "Preço do inversor " + kitSelecionado.getInversor().getPreco());
        int numeroPaineis = (int) Math.ceil(consumoMensal / (irradiacao * (potenciaPainel/1000) * 30));
        System.out.println("numero de paineis: " + numeroPaineis);

        // Cálculo do orçamento
        double precoEquipamentos = (numeroPaineis * kitSelecionado.getPlacaSolar().getPreco()) + kitSelecionado.getInversor().getPreco();
        double lucro = precoEquipamentos * 0.2;
        double custoTotal = lucro + precoEquipamentos + 600; // 600 é o valor da mão de obra

        // Atualiza o orçamento com o custo calculado
        orcamento.setCustoTotal(custoTotal);
        orcamento.setLucro(lucro);
        orcamento.setStatus("Orçamento enviado");

        System.out.println("Orçamento calculado: R$ " + custoTotal);

        PainelClienteGUI painelCliente = encontrarPainelCliente(cliente);
        if (painelCliente != null) {
            painelCliente.receberOrcamentos(List.of(orcamento));
        }

        // notificação
        JOptionPane.showMessageDialog(null, "Sistema dimensionado para o cliente " + cliente.getNome() +
                        ". Nº de painéis: " + numeroPaineis + ". Orçamento: R$ " + String.format("%.2f", custoTotal) + ".",
                "Orçamento Enviado", JOptionPane.INFORMATION_MESSAGE);

        // Atualiza a lista de solicitações
        atualizarListaSolicitacoes();
    }

    // Método para obter a irradiação solar com base na região do cliente
    private double obterIrradiacaoSolarPorRegiao(String regiao) {
        switch (regiao) {
            case "Norte":
                return 4.7;
            case "Nordeste":
                return 6.0;
            case "Centro-Oeste":
                return 5.25;
            case "Sudeste":
                return 5.0;
            case "Sul":
                return 4.5;
            default:
                return 0;
        }
    }

    // Método para permitir a seleção de um KitSolar
    private KitSolar selecionarKit() {
        String[] nomesKits = new String[kits.size()];
        for (int i = 0; i < kits.size(); i++) {
            String modeloPlaca = kits.get(i).getPlacaSolar().getModelo();
            double potenciaInversor = kits.get(i).getInversor().getPotencia();
            nomesKits[i] = modeloPlaca + " (Inversor: " + potenciaInversor + " W)";
        }

        String kitEscolhido = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o kit solar para o dimensionamento:",
                "Seleção de Kit Solar",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nomesKits,
                nomesKits[0]
        );

        if (kitEscolhido != null) {
            for (KitSolar kit : kits) {
                // Verifica se o modelo do kit corresponde ao escolhido
                if (kit.getPlacaSolar().getModelo().equals(kitEscolhido.split(" \\(")[0])) {
                    return kit;
                }
            }
        }

        return null; // Retorna null se nenhum kit for selecionado
    }

    // Método para encontrar o painel do cliente correspondente
    private PainelClienteGUI encontrarPainelCliente(Classes.Cliente cliente) {
        for (PainelClienteGUI painel : paineisClientes) {
            if (painel.getCliente().equals(cliente)) {
                return painel;
            }
        }
        return null;
    }

    public JList<String> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public void setListaSolicitacoes(JList<String> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
    }

    public void setModeloLista(DefaultListModel<String> modeloLista) {
        this.modeloLista = modeloLista;
    }

    public List<Classes.Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Classes.Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public List<KitSolar> getKits() {
        return kits;
    }

    public void setKits(List<KitSolar> kits) {
        this.kits = kits;
    }

    public List<PainelClienteGUI> getPaineisClientes() {
        return paineisClientes;
    }

    public void setPaineisClientes(List<PainelClienteGUI> paineisClientes) {
        this.paineisClientes = paineisClientes;
    }

    public JButton getDimensionarButton() {
        return dimensionarButton;
    }

    public void setDimensionarButton(JButton dimensionarButton) {
        this.dimensionarButton = dimensionarButton;
    }
}