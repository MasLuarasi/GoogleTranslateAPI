package com.example.googletranslateapi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import java.util.HashMap;

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

    @FXML
    private ComboBox languageSelectMenu;

    private String target;

    HashMap<String, String> languageCodes = new HashMap<String,String>();

    ObservableList<String> menuOptions = FXCollections.observableArrayList(
            "Albanian",
            "Arabic",
            "Dutch",
            "French",
            "German",
            "Greek",
            "Hindi",
            "Italian",
            "Japanese",
            "Korean",
            "Russian",
            "Spanish"
    );

    @FXML
    protected void onLanguageSelectClick()
    {
        languageCodes.put("Albanian", "sq");
        languageCodes.put("Arabic", "ar");
        languageCodes.put("Dutch", "nl");
        languageCodes.put("French", "fr");
        languageCodes.put("German", "de");
        languageCodes.put("Greek", "el");
        languageCodes.put("Hindi", "hi");
        languageCodes.put("Italian", "it");
        languageCodes.put("Japanese", "ja");
        languageCodes.put("Korean", "ko");
        languageCodes.put("Russian", "ru");
        languageCodes.put("Spanish", "es");
        languageSelectMenu.setItems(menuOptions);
    }


    @FXML
    protected void onTranslateButtonClick() throws Exception
    {
        String language = (String) languageSelectMenu.getValue();
        target = languageCodes.get(language);
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
                        "&target=" + target +
                        "&source=en"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String ret = response.body();
        ret = ret.substring(ret.indexOf("translatedText")+17, ret.length()-5);
        return ret;
    }
}