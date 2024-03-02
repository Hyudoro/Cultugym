package com.example.application.controleur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.example.application.connexionbdd.Bddco;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import java.io.File;

public class Control_interface_affichage_client {
    @FXML
    private Label affichage,message_avertissement;
    @FXML
    private Button Afficher,Suppression_client;

    double compteur_message_de_verification_pour_tout_supprimer;
    @FXML
    public void initialisation(){

        Afficher.setOnAction(event -> Affiche());
        Suppression_client.setOnAction(event ->{
            compteur_message_de_verification_pour_tout_supprimer += 1;
            if (compteur_message_de_verification_pour_tout_supprimer%2 ==0){
                suppression_totale();
                message_avertissement.setText("L'intégralité de votre clientèle vient d'être supprimée");
                compteur_message_de_verification_pour_tout_supprimer = 0;
            }else{
                message_avertissement.setText("Êtes-vous sûr de vouloir supprimer l'intégralité de votre clientèle ? ");
            }
        });

    }
    public void Affiche(){
        Bddco bdd = new Bddco();
        StringBuilder resultat_affichage = new StringBuilder();
        try(Connection co = bdd.getConnection()) {
            String requete = "SELECT id, nom, Prenom, email, nom_utilisateur, taille, poids, adresse, code_postal, description, Experience, sexe, corpulence FROM client";
            Statement s = co.createStatement();
            ResultSet r = s.executeQuery(requete);
            while(r.next()){
                resultat_affichage.append("ID: ").append(r.getInt("id"))
                        .append(", Nom: ").append(r.getString("nom"))
                        .append(", Prénom: ").append(r.getString("Prenom"))
                        .append(", Email: ").append(r.getString("email"))
                        .append(", Nom d'utilisateur: ").append(r.getString("nom_utilisateur"))
                        .append(", Taille: ").append(r.getInt("taille"))
                        .append(", Poids: ").append(r.getInt("poids"))
                        .append(", Adresse: ").append(r.getString("adresse"))
                        .append(", Code postal: ").append(r.getInt("code_postal"))
                        .append(", Description: ").append(r.getString("description"))
                        .append(", Expérience: ").append(r.getString("Experience"))
                        .append(", Sexe: ").append(r.getString("sexe"))
                        .append(", Corpulence: ").append(r.getString("corpulence"))
                        .append("\n");
            }
            affichage.setText(resultat_affichage.toString());
    }

        catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void suppression_totale() {
        Bddco bdd = new Bddco();
        try (Connection connection = bdd.getConnection()) {
            Statement statement = connection.createStatement();

            // Suppression de tous les clients
            String queryClient = "TRUNCATE TABLE client";
            statement.execute(queryClient);
            System.out.println("Tous les clients ont été supprimés.");

            // Suppression de tous les programmes
            String queryProgramme = "TRUNCATE TABLE programme";
            statement.execute(queryProgramme);
            System.out.println("Tous les programmes ont été supprimés.");

            // Suppression pour chaque jour de la semaine
            String[] jours = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"};
            for (String jour : jours) {
                String queryJour = "TRUNCATE TABLE " + jour;
                statement.execute(queryJour);
                System.out.println("Les données de la table " + jour + " ont été supprimées.");
            }
            List<Path> directories = Arrays.asList(
                    Paths.get("Images/Images_client/Face"),
                    Paths.get("Images/Images_client/Derriere"),
                    Paths.get("Images/Images_client/Profil_Droit"),
                    Paths.get("Images/Images_client/Profil_Gauche")
            );
            directories.forEach(directory -> {
                try (Stream<Path> paths = Files.walk(directory)) {
                    paths.sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(file -> {
                                if (file.isDirectory()) {
                                } else {
                                    if (!file.delete()) {
                                        System.err.println("Échec de la suppression de : " + file);
                                    }
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }



