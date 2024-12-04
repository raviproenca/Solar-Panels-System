package Classes;

public class Orcamento {
    private Cliente cliente;
    private double consumoMensal;
    private String status;
    private double custoTotal;
    private double lucro;

    // Construtor padrão
    public Orcamento() {
        this.status = "Pendente";
        this.custoTotal = 0.0;
    }

    public Orcamento(Cliente cliente, double consumoMensal) {
        this.cliente = cliente;
        this.consumoMensal = consumoMensal;
        this.status = "Pendente";
        this.custoTotal = 0.0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getConsumoMensal() {
        return consumoMensal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public double getLucro() {
        return lucro;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public void aprovar() {
        this.status = "Aprovado";
    }

    public void recusar() {
        this.status = "Recusado";
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNome() + " - Consumo: " + consumoMensal + " kWh - Status: " + status + " - Custo Total: R$ " + String.format("%.2f", custoTotal);
    }
}