package com.example.application;

import com.example.application.interactionbdd.Bdd;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interface.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Cultugym");
        stage.setScene(scene);

        ComboBox<String> comboBox = (ComboBox<String>) scene.lookup("#comboboxid");
        if (comboBox != null) {
            comboBox.setItems(bdd.getExercices());
        } else {
            System.out.println("ComboBox not found");
        }

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        bdd.stop(); // Appelle la méthode stop de Bdd pour fermer la connexion
    }












































































}
