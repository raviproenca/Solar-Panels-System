package Classes;

public class Inversor {
    private String modelo;
    private double potencia;
    private double preco;
    private Fabricante fabricante;

    // Construtor padrão
    public Inversor() {
        this.modelo = "";
        this.potencia = 0.0;
        this.preco = 0.0;
        this.fabricante = null;
    }

    // Construtor com parâmetros
    public Inversor(String modelo, double potencia, double preco, Fabricante fabricante) {
        this.modelo = modelo;
        this.potencia = potencia;
        this.preco = preco;
        this.fabricante = fabricante;
    }

    // Getters
    public String getModelo() { return modelo; }
    public double getPotencia() { return potencia; }
    public double getPreco() { return preco; }
    public Fabricante getFabricante() { return fabricante; }

    @Override
    public String toString() {
        String fabricanteNome = (fabricante != null) ? fabricante.getNome() : "Desconhecido";
        return "Inversor{" +
                "modelo='" + modelo + '\'' +
                ", potencia=" + potencia + " W" +
                ", preco=R$" + preco +
                ", fabricante='" + fabricanteNome + '\'' +
                '}';
    }
}