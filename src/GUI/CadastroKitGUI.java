package GUI;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.List;
import Classes.*;

public class CadastroKitGUI extends JFrame {
    private JTextField modeloPlacaField;
    private JTextField capacidadePlacaField;
    private JTextField precoPlacaField;
    private JComboBox<Classes.Fabricante> fabricantePlacaComboBox;

    private JTextField modeloInversorField;
    private JTextField potenciaInversorField;
    private JTextField precoInversorField;
    private JComboBox<Classes.Fabricante> fabricanteInversorComboBox;

    private JButton cadastrarKitButton;
    private List<KitSolar> listaKits;
    private List<Classes.Fabricante> listaFabricantes;

    public CadastroKitGUI(List<Classes.Fabricante> listaFabricantes, List<KitSolar> listaKits) {
        this.listaFabricantes = listaFabricantes;
        this.listaKits = listaKits;

        setTitle("Cadastro de Kit (Placa + Inversor)");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(9, 2));

        // Campos para a placa solar
        modeloPlacaField = new JTextField();
        capacidadePlacaField = new JTextField();
        precoPlacaField = new JTextField();
        fabricantePlacaComboBox = new JComboBox<>(listaFabricantes.toArray(new Classes.Fabricante[0]));

        // Campos para o inversor
        modeloInversorField = new JTextField();
        potenciaInversorField = new JTextField();
        precoInversorField = new JTextField();
        fabricanteInversorComboBox = new JComboBox<>(listaFabricantes.toArray(new Classes.Fabricante[0]));

        cadastrarKitButton = new JButton("Cadastrar Kit");

        // Adicionando os campos para a placa solar
        add(new JLabel("Modelo Placa Solar:"));
        add(modeloPlacaField);
        add(new JLabel("Capacidade (W):"));
        add(capacidadePlacaField);
        add(new JLabel("Preço Placa Solar:"));
        add(precoPlacaField);
        add(new JLabel("Fabricante Placa:"));
        add(fabricantePlacaComboBox);

        // Adicionando os campos para o inversor
        add(new JLabel("Modelo Inversor:"));
        add(modeloInversorField);
        add(new JLabel("Potência Inversor (W):"));
        add(potenciaInversorField);
        add(new JLabel("Preço Inversor:"));
        add(precoInversorField);
        add(new JLabel("Fabricante Inversor:"));
        add(fabricanteInversorComboBox);

        add(cadastrarKitButton);

        // Ação do botão cadastrar
        cadastrarKitButton.addActionListener(e -> {
            try {
                // placa solar
                String modeloPlaca = modeloPlacaField.getText();
                double capacidadePlaca = Double.parseDouble(capacidadePlacaField.getText());
                double precoPlaca = Double.parseDouble(precoPlacaField.getText());
                Classes.Fabricante fabricantePlaca = (Classes.Fabricante) fabricantePlacaComboBox.getSelectedItem();
                PlacaSolar placa = new PlacaSolar(modeloPlaca, capacidadePlaca, precoPlaca, fabricantePlaca);

                // inversor
                String modeloInversor = modeloInversorField.getText();
                double potenciaInversor = Double.parseDouble(potenciaInversorField.getText());
                double precoInversor = Double.parseDouble(precoInversorField.getText());
                Classes.Fabricante fabricanteInversor = (Classes.Fabricante) fabricanteInversorComboBox.getSelectedItem();
                Inversor inversor = new Inversor(modeloInversor, potenciaInversor, precoInversor, fabricanteInversor);

                // KitSolar
                KitSolar kit = new KitSolar(inversor, placa);
                listaKits.add(kit);

                JOptionPane.showMessageDialog(null, "Kit Placa + Inversor cadastrado com sucesso!");

                modeloPlacaField.setText("");
                capacidadePlacaField.setText("");
                precoPlacaField.setText("");
                modeloInversorField.setText("");
                potenciaInversorField.setText("");
                precoInversorField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JTextField getModeloPlacaField() {
        return modeloPlacaField;
    }

    public void setModeloPlacaField(JTextField modeloPlacaField) {
        this.modeloPlacaField = modeloPlacaField;
    }

    public JTextField getCapacidadePlacaField() {
        return capacidadePlacaField;
    }

    public void setCapacidadePlacaField(JTextField capacidadePlacaField) {
        this.capacidadePlacaField = capacidadePlacaField;
    }

    public JTextField getPrecoPlacaField() {
        return precoPlacaField;
    }

    public void setPrecoPlacaField(JTextField precoPlacaField) {
        this.precoPlacaField = precoPlacaField;
    }

    public JComboBox<Classes.Fabricante> getFabricantePlacaComboBox() {
        return fabricantePlacaComboBox;
    }

    public void setFabricantePlacaComboBox(JComboBox<Classes.Fabricante> fabricantePlacaComboBox) {
        this.fabricantePlacaComboBox = fabricantePlacaComboBox;
    }

    public JTextField getModeloInversorField() {
        return modeloInversorField;
    }

    public void setModeloInversorField(JTextField modeloInversorField) {
        this.modeloInversorField = modeloInversorField;
    }

    public JTextField getPotenciaInversorField() {
        return potenciaInversorField;
    }

    public void setPotenciaInversorField(JTextField potenciaInversorField) {
        this.potenciaInversorField = potenciaInversorField;
    }

    public JTextField getPrecoInversorField() {
        return precoInversorField;
    }

    public void setPrecoInversorField(JTextField precoInversorField) {
        this.precoInversorField = precoInversorField;
    }

    public JComboBox<Classes.Fabricante> getFabricanteInversorComboBox() {
        return fabricanteInversorComboBox;
    }

    public void setFabricanteInversorComboBox(JComboBox<Classes.Fabricante> fabricanteInversorComboBox) {
        this.fabricanteInversorComboBox = fabricanteInversorComboBox;
    }

    public JButton getCadastrarKitButton() {
        return cadastrarKitButton;
    }

    public void setCadastrarKitButton(JButton cadastrarKitButton) {
        this.cadastrarKitButton = cadastrarKitButton;
    }

    public List<KitSolar> getListaKits() {
        return listaKits;
    }

    public void setListaKits(List<KitSolar> listaKits) {
        this.listaKits = listaKits;
    }

    public List<Classes.Fabricante> getListaFabricantes() {
        return listaFabricantes;
    }

    public void setListaFabricantes(List<Classes.Fabricante> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;
    }
}
