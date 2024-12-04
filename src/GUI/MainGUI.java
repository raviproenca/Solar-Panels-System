package GUI;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import Classes.*;

public class MainGUI extends JFrame {
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton sairButton;
    private CadastroClienteGUI cadastroClienteGUI;
    private PainelClienteGUI painelClienteGUI;
    private PainelEmpresaGUI painelEmpresaGUI;
    private List<Classes.Cliente> listaClientes;
    private List<Classes.Fabricante> listaFabricantes;
    private List<KitSolar> listaKits;
    private List<Classes.Orcamento> listaOrcamentos;
    private List<PainelClienteGUI> painelClienteGUIS;

    public MainGUI() {
        listaClientes = new ArrayList<>();
        listaFabricantes = new ArrayList<>();
        listaKits = new ArrayList<>();
        listaOrcamentos = new ArrayList<>();
        painelClienteGUIS = new ArrayList<>();

        carregarDados(); // Carregar dados ao iniciar a aplicação

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        usuarioField = new JTextField();
        senhaField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Registrar");
        sairButton = new JButton("Sair");

        add(new JLabel("Usuário:"));
        add(usuarioField);
        add(new JLabel("Senha:"));
        add(senhaField);
        add(loginButton);
        add(registerButton);
        add(sairButton);

        // Ação do botão de login
        loginButton.addActionListener(e -> {
            String usuario = usuarioField.getText();
            String senha = new String(senhaField.getPassword());

            if (usuario.equals("admin") && senha.equals("admin")) {
                JOptionPane.showMessageDialog(null, "Bem-vindo, Admin!");
                painelEmpresaGUI = new PainelEmpresaGUI(listaFabricantes, listaOrcamentos, listaClientes, listaKits, painelClienteGUIS, this);
                painelEmpresaGUI.setVisible(true);
                dispose();
                return;
            }

            boolean loginSucesso = false;

            // Verifica se o usuário e senha correspondem a algum cliente cadastrado
            for (Classes.Cliente cliente : listaClientes) {
                if (cliente.getUsuario().equals(usuario) && cliente.getSenha().equals(senha)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                    painelClienteGUI = new PainelClienteGUI(cliente, listaKits, listaOrcamentos, this);
                    painelClienteGUI.setVisible(true);
                    loginSucesso = true;
                    painelClienteGUIS.add(painelClienteGUI);
                    dispose();
                    break;
                }
            }

            if (!loginSucesso) {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação do botão de registrar
        registerButton.addActionListener(e -> {
            cadastroClienteGUI = new CadastroClienteGUI(listaClientes);
            cadastroClienteGUI.setVisible(true);
        });

        // Ação do botão de sair
        sairButton.addActionListener(e -> {
            salvarDados();
            System.exit(0);
        });
    }

    // Método para salvar dados em um arquivo JSON
    private void salvarDados() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("dados.json"),
                    new Dados(listaClientes, listaKits, listaOrcamentos));
            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!", "Salvar Dados", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para carregar dados de um arquivo JSON
    private void carregarDados() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File arquivo = new File("dados.json");
            if (arquivo.exists()) {
                Dados dados = objectMapper.readValue(arquivo, Dados.class);
                listaClientes = dados.getClientes();
                listaKits = dados.getKits();
                listaOrcamentos = dados.getOrcamentos();
                JOptionPane.showMessageDialog(this, "Dados carregados com sucesso!", "Carregar Dados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum dado encontrado para carregar.", "Carregar Dados", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JTextField getUsuarioField() {
        return usuarioField;
    }

    public void setUsuarioField(JTextField usuarioField) {
        this.usuarioField = usuarioField;
    }

    public JPasswordField getSenhaField() {
        return senhaField;
    }

    public void setSenhaField(JPasswordField senhaField) {
        this.senhaField = senhaField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public JButton getSairButton() {
        return sairButton;
    }

    public void setSairButton(JButton sairButton) {
        this.sairButton = sairButton;
    }

    public CadastroClienteGUI getCadastroClienteGUI() {
        return cadastroClienteGUI;
    }

    public void setCadastroClienteGUI(CadastroClienteGUI cadastroClienteGUI) {
        this.cadastroClienteGUI = cadastroClienteGUI;
    }

    public PainelClienteGUI getPainelClienteGUI() {
        return painelClienteGUI;
    }

    public void setPainelClienteGUI(PainelClienteGUI painelClienteGUI) {
        this.painelClienteGUI = painelClienteGUI;
    }

    public PainelEmpresaGUI getPainelEmpresaGUI() {
        return painelEmpresaGUI;
    }

    public void setPainelEmpresaGUI(PainelEmpresaGUI painelEmpresaGUI) {
        this.painelEmpresaGUI = painelEmpresaGUI;
    }

    public List<Classes.Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Classes.Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Classes.Fabricante> getListaFabricantes() {
        return listaFabricantes;
    }

    public void setListaFabricantes(List<Classes.Fabricante> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;
    }

    public List<KitSolar> getListaKits() {
        return listaKits;
    }

    public void setListaKits(List<KitSolar> listaKits) {
        this.listaKits = listaKits;
    }

    public List<Classes.Orcamento> getListaOrcamentos() {
        return listaOrcamentos;
    }

    public void setListaOrcamentos(List<Classes.Orcamento> listaOrcamentos) {
        this.listaOrcamentos = listaOrcamentos;
    }

    public List<PainelClienteGUI> getPainelClienteGUIS() {
        return painelClienteGUIS;
    }

    public void setPainelClienteGUIS(List<PainelClienteGUI> painelClienteGUIS) {
        this.painelClienteGUIS = painelClienteGUIS;
    }
}