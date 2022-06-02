module com.example.googletranslateapi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;

    opens com.example.googletranslateapi to javafx.fxml;
    exports com.example.googletranslateapi;
}