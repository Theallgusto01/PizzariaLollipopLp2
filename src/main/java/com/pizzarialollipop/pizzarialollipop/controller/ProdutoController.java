package com.pizzarialollipop.pizzarialollipop.controller;

import com.pizzarialollipop.pizzarialollipop.model.dao.impl.ProdutoDAOImpl;
import com.pizzarialollipop.pizzarialollipop.model.entities.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ProdutoController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    private final ProdutoDAOImpl produtoDAO = new ProdutoDAOImpl();

    @FXML
    private void handleSaveProduct() {
        String name = nameField.getText();
        double price;

        try {
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            showAlert("Erro", "O preço deve ser um número válido.", Alert.AlertType.ERROR);
            return;
        }

        Produto produto = new Produto(0, price, name); // O ID será gerado automaticamente
        produtoDAO.create(produto);

        showAlert("Sucesso", "Produto cadastrado com sucesso!", Alert.AlertType.INFORMATION);
        clearFields();
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        nameField.clear();
        priceField.clear();
    }
}
