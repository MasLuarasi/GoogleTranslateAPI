package com.example.googletranslateapi;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import java.net.URI;

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

//    private String inputText;
//    private String outputText;

    @FXML
    protected void onTranslateButtonClick() throws Exception
    {
        outputField.setText(translate(inputField.getText()));
    }

    protected String translate(String original)throws Exception
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "application/gzip")
                .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "4125e08ab5msh1e35e54946ee894p1de70djsn0b70aa45221f")
                .method("POST", HttpRequest.BodyPublishers.ofString("q=" +
                        original +
                        "&format=text" +
                        "&target=es" +
                        "&source=en"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String ret = response.body();
        ret = ret.substring(ret.indexOf("translatedText")+17, ret.length()-5);
        return ret;
    }
}