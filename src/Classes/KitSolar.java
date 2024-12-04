package Classes;

public class KitSolar {
    private Inversor inversor;
    private PlacaSolar placaSolar;
    private double preco;

    // Construtor padrão
    public KitSolar() {
        this.inversor = null;
        this.placaSolar = null;
        this.preco = 0.0;
    }

    // Construtor com parâmetros
    public KitSolar(Inversor inversor, PlacaSolar placaSolar) {
        this.inversor = inversor;
        this.placaSolar = placaSolar;
        this.preco = calcularPrecoTotal();
    }

    private double calcularPrecoTotal() {
        return (inversor != null ? inversor.getPreco() : 0) +
                (placaSolar != null ? placaSolar.getPreco() : 0);
    }

    // Getters
    public Inversor getInversor() {
        return inversor;
    }

    public PlacaSolar getPlacaSolar() {
        return placaSolar;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Kit Solar{" +
                "inversor=" + inversor +
                ", placaSolar=" + placaSolar +
                ", precoTotal=R$" + preco +
                '}';
    }
}