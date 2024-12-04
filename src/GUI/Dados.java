package GUI;

import java.util.ArrayList;
import java.util.List;
import Classes.*;

public class Dados {
    private List<Classes.Cliente> clientes;
    private List<KitSolar> kits;
    private List<Classes.Orcamento> orcamentos;

    // Construtor padrão (sem argumentos)
    public Dados() {
        this.clientes = new ArrayList<>();
        this.kits = new ArrayList<>();
        this.orcamentos = new ArrayList<>();
    }

    public Dados(List<Classes.Cliente> clientes, List<KitSolar> kits, List<Classes.Orcamento> orcamentos) {
        this.clientes = clientes;
        this.kits = kits;
        this.orcamentos = orcamentos;
    }

    public List<Classes.Cliente> getClientes() {
        return clientes;
    }

    public List<KitSolar> getKits() {
        return kits;
    }

    public List<Classes.Orcamento> getOrcamentos() {
        return orcamentos;
    }
}
