package Classes;

public class Fabricante {
    private String nome;

    // Construtor padrão
    public Fabricante() {

    }

    public Fabricante(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    @Override
    public String toString() {
        return "Fabricante: " + nome;
    }
}