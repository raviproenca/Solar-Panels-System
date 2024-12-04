package Classes;

import java.util.Objects;

public class Cliente {
    private String nome;
    private String regiao;
    private String telefone;
    private String usuario;
    private String senha;

    // Construtor padrão
    public Cliente() {

    }

    // Construtor atualizado
    public Cliente(String nome, String regiao, String telefone, String usuario, String senha) {
        this.nome = nome;
        this.regiao = regiao;
        this.telefone = telefone;
        this.usuario = usuario;
        this.senha = senha;
    }

    // Getters
    public String getNome() { return nome; }

    public String getRegiao() { return regiao; }

    public String getTelefone() { return telefone; }

    public String getUsuario() { return usuario; }

    public String getSenha() { return senha; }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", regiao='" + regiao + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    // Sobrescrevendo o método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Verifica se são o mesmo objeto
        if (obj == null || getClass() != obj.getClass()) return false;  // Verifica se são da mesma classe
        Cliente cliente = (Cliente) obj;
        return Objects.equals(this.usuario, cliente.usuario);  // Compara pelo campo 'usuario'
    }

    // Sobrescrevendo o método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(usuario);  // Gera o hash a partir do campo 'usuario'
    }
}