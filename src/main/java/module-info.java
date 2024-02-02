module com.example.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.application to javafx.fxml;
    exports com.example.application;
    exports com.example.application.connexionbdd;
    opens com.example.application.connexionbdd to javafx.fxml;
    exports com.example.application.identification;
    opens com.example.application.identification to javafx.fxml;
    exports com.example.application.controleur;
    opens com.example.application.controleur to javafx.fxml;

    // Ajoutez votre module personnalisé ici
    exports com.example.application.interactionbdd;
    opens com.example.application.interactionbdd to javafx.fxml;
}
