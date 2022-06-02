package com.example.googletranslateapi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController
{
    @FXML
    private Label inputLabel;

    @FXML
    private Label outputLabel;

    @FXML
    private TextField inputField;

    @FXML
    private TextField outputField;

    private String inputText;
    private String outputText;

    @FXML
    protected void onTranslateButtonClick()
    {
        inputText = inputField.getText();
        outputText = inputText;
        outputField.setText(outputText);
    }
}