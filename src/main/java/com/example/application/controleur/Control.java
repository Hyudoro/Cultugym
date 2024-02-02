package com.example.application.controleur;

import com.example.application.connexionbdd.Bddco;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Control {
    @FXML
    private TextField client_id; // Pour l'ID du client

    // Déclaration des labels pour les informations du client
    @FXML
    private Label prenom_id, nom_utilisateur_id, email_id, code_postal_id, adresse_id, nom_id, sexe_id, taille_id, poids_id, corpulence_id, experience_id,description_id;

    @FXML
    private Spinner<Integer> spinner1,spinner2,spinner3,spinner4,spinner6;
    @FXML
    private Spinner<Double> spinner5;
    @FXML
    private ComboBox comboboxid;
   @FXML
    private Button Ajout;
   @FXML
   private ImageView photo_droite,photo_gauche,photo_face,photo_derriere;




    @FXML
    private void initialize() {
        client_id.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                chargementdesdonneesclient();
            }
        });
        SpinnerValueFactory<Integer> repetitions = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 31, 0, 1);
        SpinnerValueFactory<Integer> series = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9, 1, 1);
        SpinnerValueFactory<Double> tempsDeRepos = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 16, 0, 0.5);
        SpinnerValueFactory<Integer> seriesEchauffement = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 11, 0, 1);
        SpinnerValueFactory<Integer> degreDEchec = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 11, 0, 1);
        SpinnerValueFactory<Integer> percentRepMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 101, 0, 1);

        spinner1.setValueFactory(seriesEchauffement);
        spinner2.setValueFactory(series);
        spinner3.setValueFactory(repetitions);
        spinner4.setValueFactory(percentRepMax);
        spinner5.setValueFactory(tempsDeRepos); //seul valeur float (double)
        spinner6.setValueFactory(degreDEchec);

        Ajout.setOnAction(event -> {
            Programmation();
        });





    }

    // Ajouter une méthode pour charger les données du client lorsque l'ID est saisi
    @FXML
    private void chargementdesdonneesclient() {
        if (!client_id.getText().isEmpty()) {
            int clientId = Integer.parseInt(client_id.getText());
            Bddco bdd = new Bddco();
            try (Connection connection = bdd.getConnection()) {
                String query = "SELECT * FROM client WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, clientId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    description_id.setText(rs.getString("description"));
                    prenom_id.setText(rs.getString("prenom"));
                    nom_utilisateur_id.setText(rs.getString("nom_utilisateur"));
                    email_id.setText(rs.getString("email"));
                    code_postal_id.setText(rs.getString("code_postal"));
                    adresse_id.setText(rs.getString("adresse"));
                    nom_id.setText(rs.getString("nom"));
                    sexe_id.setText(rs.getString("sexe"));
                    taille_id.setText(rs.getString("taille"));
                    poids_id.setText(rs.getString("poids"));
                    corpulence_id.setText(rs.getString("corpulence"));
                    experience_id.setText(rs.getString("experience"));
                } else {
                    // Effacer les labels ou afficher un message si l'ID n'est pas trouvé
                    System.out.println("Client non trouvé pour l'ID: " + clientId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bdd.closeConnection();
            }
        }
    }
    private void Programmation() {
        if (comboboxid.getValue() != null && spinner1.getValue() != null && spinner2.getValue() != null && spinner3.getValue() != null && spinner4.getValue() != null && spinner5.getValue() != null && spinner6.getValue() != null) {
            // Logique à exécut

            Bddco bdd2 = new Bddco();
            try (Connection connection = bdd2.getConnection()) {
                // Préparation de la requête SQL avec des paramètres
                String query = "INSERT INTO Programme (nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps1 = connection.prepareStatement(query);

                // Affectation des valeurs aux paramètres à partir des contrôles JavaFX.
                ps1.setString(1, comboboxid.getValue().toString()); // Convertit en String
                ps1.setInt(2, spinner3.getValue()); // repetitions
                ps1.setInt(3, spinner2.getValue()); // series
                ps1.setDouble(4, spinner5.getValue()); // tempsDeRepos
                ps1.setInt(5, spinner1.getValue()); // SeriesEchauffement
                ps1.setInt(6, spinner6.getValue()); // degreDEchec
                ps1.setInt(7, spinner4.getValue()); // percentRepMax

                // Exécution de la requête d'insertion
                int affectedRows = ps1.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Insertion réussie.");
                } else {
                    System.out.println("Aucune ligne insérée.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Ici, vous pouvez gérer le cas où comboboxid.getValue() est null
            // Par exemple, afficher un message d'erreur à l'utilisateur
            System.out.println("Aucune valeur sélectionnée dans le ComboBox.");
        }

    }
}
