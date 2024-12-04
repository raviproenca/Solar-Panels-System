package Classes;

import java.util.List;

public class Relatorio {

    // Construtor padrão
    public Relatorio() {

    }

    public String gerarRelatorioProjetos(List<Orcamento> orcamentos) {
        StringBuilder sb = new StringBuilder();
        sb.append("Relatório de Projetos:\n");
        for (Orcamento orcamento : orcamentos) {
            sb.append("Cliente: ").append(orcamento.getCliente().getNome()).append("\n");
            sb.append("Consumo: ").append(orcamento.getConsumoMensal()).append(" kWh\n");
            sb.append("Custo Total: R$").append(orcamento.getCustoTotal()).append("\n");
            sb.append("Status: ").append(orcamento.getStatus()).append("\n\n");
        }
        return sb.toString();
    }

    public String gerarRelatorioClientes(List<Cliente> clientes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Relatório de Clientes:\n");
        for (Cliente cliente : clientes) {
            sb.append("Nome: ").append(cliente.getNome()).append("\n");
            sb.append("Região: ").append(cliente.getRegiao()).append("\n");
            sb.append("Telefone: ").append(cliente.getTelefone()).append("\n\n");
        }
        return sb.toString();
    }

    public String gerarRelatorioEquipamentos(List<KitSolar> kits) {
        StringBuilder sb = new StringBuilder();
        sb.append("Relatório de Equipamentos:\n\n");

        for (KitSolar kit : kits) {
            sb.append("Kit:\n");
            sb.append("Placa Solar: ").append(kit.getPlacaSolar().getModelo()).append("\n");
            sb.append("Potência da Placa: ").append(kit.getPlacaSolar().getCapacidade()).append(" W\n");
            sb.append("Preço da Placa: R$").append(kit.getPlacaSolar().getPreco()).append("\n");
            sb.append("Inversor: ").append(kit.getInversor().getModelo()).append("\n");
            sb.append("Potência do Inversor: ").append(kit.getInversor().getPotencia()).append(" W\n");
            sb.append("Preço do Inversor: R$").append(kit.getInversor().getPreco()).append("\n\n");
        }

        return sb.toString();
    }

    public String gerarRelatorioFinanceiro(List<Orcamento> orcamentos) {
        StringBuilder sb = new StringBuilder();
        sb.append("Relatório Financeiro:\n\n");

        for (Orcamento orcamento : orcamentos) {
            if (orcamento.getStatus().equals("Aprovado")) {
                sb.append("Cliente: ").append(orcamento.getCliente().getNome()).append("\n");
                sb.append("Custo Total: R$").append(orcamento.getCustoTotal()).append("\n");
                double lucro = orcamento.getLucro();
                sb.append("Lucro: R$").append(lucro).append("\n\n");
            }
        }
        return sb.toString();
    }
}