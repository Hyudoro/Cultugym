package com.example.application.interactionbdd;

import com.example.application.connexionbdd.Bddco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bdd {
    private Bddco bddco = new Bddco(); // Utilisez Bddco pour gérer la connexion

    public ObservableList<String> getExercices() {
        ObservableList<String> exercices = FXCollections.observableArrayList();
        try {
            Connection connection = bddco.getConnection(); // Obtenez la connexion de Bddco
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT nom FROM Liste_exercice";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String exerciseName = resultSet.getString("nom");
                exercices.add(exerciseName);
            }

            resultSet.close();
            statement.close();
            // Ne fermez pas la connexion ici pour permettre sa réutilisation
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exercices;
    }

    // Méthode pour arrêter proprement les connexions ou autres ressources si nécessaire
    public void stop() {
        bddco.closeConnection(); // Fermez la connexion via Bddco
    }
}
