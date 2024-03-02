package com.example.application;

import com.example.application.identification.Control_page_identifiant;

import com.example.application.interactionbdd.Bdd;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Monapplication extends Application {
    private Bdd bdd; // Assurez-vous que Bdd est correctement initialisé

    @Override
    public void init() throws Exception {
        super.init();
        bdd = new Bdd();
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Interface_identifiant.fxml"));
        Parent root = fxmlLoader.load();
        Control_page_identifiant controller = fxmlLoader.getController();
        controller.initialisation();

        Scene scene = new Scene(root);
        stage.setTitle("Cultugym [connexion]");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        bdd.stop(); // Appelle la méthode stop de Bdd pour fermer la connexion
    }
}
