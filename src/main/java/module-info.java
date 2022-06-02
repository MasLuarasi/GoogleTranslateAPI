module com.example.googletranslateapi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.googletranslateapi to javafx.fxml;
    exports com.example.googletranslateapi;
}