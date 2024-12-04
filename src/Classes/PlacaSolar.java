package Classes;

public class PlacaSolar {
    private String modelo;
    private double capacidade;
    private double preco;
    private Fabricante fabricante;

    // Construtor padrão
    public PlacaSolar() {
        this.modelo = "";
        this.capacidade = 0.0;
        this.preco = 0.0;
        this.fabricante = null;
    }

    // Construtor com parâmetros
    public PlacaSolar(String modelo, double capacidade, double preco, Fabricante fabricante) {
        this.modelo = modelo;
        this.capacidade = capacidade;
        this.preco = preco;
        this.fabricante = fabricante;
    }

    public String getModelo() { return modelo; }

    public double getCapacidade() { return capacidade; }

    public double getPreco() { return preco; }

    public Fabricante getFabricante() { return fabricante; }

    @Override
    public String toString() {
        return "Placa Solar{" +
                "modelo='" + modelo + '\'' +
                ", capacidade=" + capacidade +
                ", preco=R$" + preco +
                ", fabricante=" + (fabricante != null ? fabricante.getNome() : "Desconhecido") +
                '}';
    }
}