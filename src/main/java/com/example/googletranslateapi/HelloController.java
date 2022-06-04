package com.example.googletranslateapi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import java.util.HashMap;

public class HelloController
{
    @FXML
    private Label warning;
    @FXML
    private TextArea inputField;

    @FXML
    private TextArea outputField;

    @FXML
    private ComboBox languageSelectMenu;

    private String target;//Language to translate to

    HashMap<String, String> languageCodes;

    public HelloController()
    {
        target = "";
        languageCodes = new HashMap<>();
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
    }

    @FXML
    protected void onTranslateButtonClick() throws Exception {
        String inputText = inputField.getText();
        if (inputText.equals(""))
        {
            warning.setText("CANNOT TRANSLATE EMPTY TEXT");
            warning.setVisible(true);
        }
        else
        {
            String language = (String) languageSelectMenu.getValue();//Get language to translate to
            if (language == null)
            {
                warning.setText("NO LANGUAGE SELECTED");
            }
            else
            {
                warning.setVisible(false);
                target = languageCodes.get(language);//Get code to use in GT API connection
                System.out.println(target);
                outputField.setText(translate(inputText));//Set text of the output to the translation
            }
        }
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
                        original +//Input text,
                        "&format=text" +
                        "&target=" + target +//Language to translate to
                        "&source=en"))//Language of input text. Only supporting English at the moment.
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String outputText = response.body();
        outputText = outputText.substring(outputText.indexOf("translatedText")+17, outputText.length()-5);//Cut off unnecessary characters in the response. Just want the translation
        return outputText;
    }
}