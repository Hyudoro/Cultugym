package com.example.application;

import com.example.application.connexionbdd.Bddco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Monapplication extends Application {
    private Bddco bddco; // Déclaration de la connexion à la base de données
    private boolean applicationClosed = false; // Variable de contrôle

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Monapplication.class.getResource("/interface.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Titre de Votre Application");
        stage.setScene(scene);
        stage.show();

        // Test de la connexion à la base de données
        bddco = new Bddco();

        // Gestionnaire d'événements pour la fermeture de la fenêtre principale
        stage.setOnCloseRequest((WindowEvent we) -> {
            // Fermeture de la connexion à la base de données uniquement si l'application se ferme intentionnellement
            if (!applicationClosed) {
                System.out.println("Connexion à la base de données fermée avec succès !");
                bddco.closeConnection();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        applicationClosed = true;
        super.stop();
    }
}
