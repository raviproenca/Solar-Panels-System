package GUI;

import javax.swing.*;
import java.awt.FlowLayout;
import java.util.List;
import Classes.Fabricante;

public class CadastroFabricanteGUI extends JFrame {
    private JTextField nomeField;
    private JButton cadastrarButton;
    private List<Fabricante> listaFabricantes;

    public CadastroFabricanteGUI(List<Fabricante> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;

        setTitle("Cadastro de Fabricantes");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new FlowLayout());

        nomeField = new JTextField(20);
        cadastrarButton = new JButton("Cadastrar");

        add(new JLabel("Nome:"));
        add(nomeField);
        add(cadastrarButton);

        cadastrarButton.addActionListener(e -> {
            try {
                validarCampos();
                Fabricante fabricante = new Fabricante(nomeField.getText());
                listaFabricantes.add(fabricante);
                JOptionPane.showMessageDialog(this, "Fabricante cadastrado com sucesso!");

                // Limpar o campo após o cadastro
                nomeField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Método para validar os campos
    private void validarCampos() throws Exception {
        String nome = nomeField.getText().trim(); // Remove espaços em branco

        // Verificação de campo vazio
        if (nome.isEmpty()) {
            throw new Exception("O campo 'Nome' não pode estar vazio.");
        }

        if (nome.length() < 3) {
            throw new Exception("O nome do fabricante deve conter pelo menos 3 letras.");
        }
    }

    public JTextField getNomeField() {
        return nomeField;
    }

    public void setNomeField(JTextField nomeField) {
        this.nomeField = nomeField;
    }

    public JButton getCadastrarButton() {
        return cadastrarButton;
    }

    public void setCadastrarButton(JButton cadastrarButton) {
        this.cadastrarButton = cadastrarButton;
    }

    public List<Fabricante> getListaFabricantes() {
        return listaFabricantes;
    }

    public void setListaFabricantes(List<Fabricante> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;
    }
}