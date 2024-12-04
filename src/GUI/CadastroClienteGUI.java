package GUI;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.List;
import Classes.Cliente;

public class CadastroClienteGUI extends JFrame {
    private JTextField nomeField;
    private JComboBox<String> regiaoComboBox;
    private JTextField telefoneField;
    private JTextField usuarioField;
    private JPasswordField senhaField;
    public JButton cadastrarButton;
    private List<Cliente> listaClientes;

    public CadastroClienteGUI(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;

        setTitle("Cadastro de Clientes");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        nomeField = new JTextField();
        regiaoComboBox = new JComboBox<>(new String[] {"Norte", "Nordeste", "Centro-Oeste", "Sudeste", "Sul"});
        telefoneField = new JTextField();
        usuarioField = new JTextField();
        senhaField = new JPasswordField();
        cadastrarButton = new JButton("Cadastrar");

        add(new JLabel("Nome Completo:"));
        add(nomeField);
        add(new JLabel("Região:"));
        add(regiaoComboBox);
        add(new JLabel("Telefone:"));
        add(telefoneField);
        add(new JLabel("Usuário:"));
        add(usuarioField);
        add(new JLabel("Senha:"));
        add(senhaField);
        add(cadastrarButton);

        cadastrarButton.addActionListener(e -> {
            try {
                validarCampos();
                Cliente cliente = new Cliente(
                        nomeField.getText(),
                        regiaoComboBox.getSelectedItem().toString(),
                        telefoneField.getText(),
                        usuarioField.getText(),
                        new String(senhaField.getPassword())
                );
                listaClientes.add(cliente);
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void validarCampos() throws Exception {
        String nome = nomeField.getText();
        String telefone = telefoneField.getText();
        String usuario = usuarioField.getText();
        String senha = new String(senhaField.getPassword());

        // Verificação de campos vazios
        if (nome.isEmpty()) {
            throw new Exception("O campo 'Nome Completo' não pode estar vazio.");
        }
        if (telefone.isEmpty()) {
            throw new Exception("O campo 'Telefone' não pode estar vazio.");
        }
        if (usuario.isEmpty()) {
            throw new Exception("O campo 'Usuário' não pode estar vazio.");
        }
        if (senha.isEmpty()) {
            throw new Exception("O campo 'Senha' não pode estar vazio.");
        }

        // Verificação de formato de nome
        if (!nome.matches("[a-zA-Z ]+")) {
            throw new Exception("O nome não pode conter números.");
        }

        // Verificação de formato de telefone
        if (!telefone.matches("\\d+")) {
            throw new Exception("O telefone deve conter apenas números.");
        }

        // Verificação de tamanho do telefone
        if (telefone.length() != 11) {
            throw new Exception("O número de telefone deve ter 11 dígitos contando com o DDD.");
        }

        // Verificação de tamanho de usuário
        if (usuario.length() < 3) {
            throw new Exception("O usuário deve conter pelo menos 3 caracteres.");
        }

        // Verificação de tamanho da senha
        if (senha.length() < 3) {
            throw new Exception("A senha deve conter pelo menos 3 caracteres.");
        }

        // Verificação se o usuário já existe
        for (Cliente cliente : listaClientes) {
            if (cliente.getUsuario().equals(usuario)) {
                throw new Exception("O nome de usuário já está em uso. Por favor, escolha outro.");
            }
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        regiaoComboBox.setSelectedIndex(0);
        telefoneField.setText("");
        usuarioField.setText("");
        senhaField.setText("");
    }

    public JTextField getNomeField() {
        return nomeField;
    }

    public void setNomeField(JTextField nomeField) {
        this.nomeField = nomeField;
    }

    public JTextField getTelefoneField() {
        return telefoneField;
    }

    public void setTelefoneField(JTextField telefoneField) {
        this.telefoneField = telefoneField;
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
}