package com.example.googletranslateapi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import java.util.HashMap;

public class HelloController
{
    @FXML
    private TextField inputField;

    @FXML
    private TextField outputField;

    @FXML
    private ComboBox languageSelectMenu;

    private String target;//Language to translate to

    HashMap<String, String> languageCodes = new HashMap<>();//HashMap to contain codes for supported languages

    ObservableList<String> menuOptions = FXCollections.observableArrayList(//Drop down menu options
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
    protected void onLanguageSelectClick()//Need to fix this so that it is set when program begins, not when the drop-down is clicked
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
        String language = (String) languageSelectMenu.getValue();//Get language to translate to
        target = languageCodes.get(language);//Get code to use in GT API connection
        outputField.setText(translate(inputField.getText()));//Set text of the output to the translation
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
        String ret = response.body();
        ret = ret.substring(ret.indexOf("translatedText")+17, ret.length()-5);//Cut off unnecessary characters in the response. Just want the translation
        return ret;
    }
}