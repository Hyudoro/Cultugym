package com.example.application.controleur.calendrier;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import com.example.application.connexionbdd.Bddco;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.sql.*;

public class Control_Interface_coach_calendrie {

    @FXML
    private ComboBox<String> Lundi_modify, Mardi_modify, Mercredi_modify, Jeudi_modify, Vendredi_modify, Samedi_modify, Dimanche_modify;

    @FXML
    private Label jour_semaine, nom_client, exercices_non_panifie,BMR_client,IMC_client,NEAT_client;
    private final StringProperty Jour = new SimpleStringProperty("");

    @FXML
    private Slider Type_de_programme;

    @FXML
    private TableColumn<String, String> Lundi_id, Mardi_id, Mercredi_id, Jeudi_id, Vendredi_id, Samedi_id, Dimanche_id;

    @FXML
    private Hyperlink mail;


    int valeur_Numero_bouton_clique = 0;
    @FXML
    private Button B_1, B_2, B_3, B_4, B_5, B_6, B_7, vider_programme,actualisation,IMC,BMR,NEAT;
    @FXML
    private Label affichage_programme;

    int jour_entrainement = -1;

    public Control_Interface_coach_calendrie() {
    }

    @FXML
    public void initialisation() {
        actualisation.setOnAction(event -> initialisation());
        client_nom_prenom();

        Type_de_programme.valueProperty().addListener((obs, oldVal, newVal) -> {
            double min = Type_de_programme.getMin(); // Supposons que min = 0
            double max = Type_de_programme.getMax(); // Supposons que max = 100
            double mid = (max - min) / 2 + min; // Correctement calculé comme étant (100 - 0) / 2 + 0 = 50

            double distToMin = Math.abs(newVal.doubleValue() - min);
            double distToMid = Math.abs(newVal.doubleValue() - mid);
            double distToMax = Math.abs(newVal.doubleValue() - max);

            // Ajuster la valeur du Slider à la plus proche parmi min, mid, max.
            if (distToMin < distToMid && distToMin < distToMax) {
                Type_de_programme.setValue(min); // Plus proche de min.
            } else if (distToMax < distToMid) {
                Type_de_programme.setValue(max); // Plus proche de max.
            } else {
                Type_de_programme.setValue(mid); // Sinon, mettre à mid.
            }
            // Création de l'instance et affichage du type de programme sélectionné
        });




        mail.setOnAction(event -> {
            initialisation();
            TextInputDialog demande_mdp = new TextInputDialog();
            demande_mdp.setTitle("Authentification gmail uniquement");
            demande_mdp.setContentText("**veuillez entrer votre mot de passe d'application gmail");
            Optional<String> result = demande_mdp.showAndWait();
            result.ifPresent(password ->{
                System.out.println("Mot de passe d'application saisi: "+ password);
                Send_mail(password);
            });
        });


        valeur_Numero_bouton_clique = 8;
        chargementProgrammeclient(valeur_Numero_bouton_clique);


        B_1.setOnAction(event -> {
            initialisation();
            String jour_transfert = "lundi";
            valeur_Numero_bouton_clique = 1;
            jour_entrainement += 1;
            calendrierclient(valeur_Numero_bouton_clique, jour_entrainement,jour_transfert);
            chargementProgrammeclient(valeur_Numero_bouton_clique);
            Jour.set("lundi"); //affichage du jour concerné de l'ajout
            jour_semaine.textProperty().bind(Jour);


        });
        B_2.setOnAction(event -> {
            initialisation();
            String jour_transfert = "mardi";
            valeur_Numero_bouton_clique = 2;
            jour_entrainement += 1;
            calendrierclient(valeur_Numero_bouton_clique, jour_entrainement,jour_transfert);
            chargementProgrammeclient(valeur_Numero_bouton_clique);
            Jour.set("mardi");
            jour_semaine.textProperty().bind(Jour);



        });
        B_3.setOnAction(event -> {
            initialisation();
            String jour_transfert = "mercredi";
            valeur_Numero_bouton_clique = 3;
            jour_entrainement += 1;
            calendrierclient(valeur_Numero_bouton_clique, jour_entrainement,jour_transfert);
            chargementProgrammeclient(valeur_Numero_bouton_clique);
            Jour.set("mercredi");
            jour_semaine.textProperty().bind(Jour);


        });
        B_4.setOnAction(event -> {
            initialisation();
            String jour_transfert = "jeudi";
            valeur_Numero_bouton_clique = 4;
            jour_entrainement += 1;
            calendrierclient(valeur_Numero_bouton_clique, jour_entrainement,jour_transfert);
            chargementProgrammeclient(valeur_Numero_bouton_clique);
            Jour.set("jeudi");
            jour_semaine.textProperty().bind(Jour);


        });
        B_5.setOnAction(event -> {
            initialisation();
            String jour_transfert = "vendredi";
            valeur_Numero_bouton_clique = 5;
            jour_entrainement += 1;
            calendrierclient(valeur_Numero_bouton_clique, jour_entrainement,jour_transfert);
            chargementProgrammeclient(valeur_Numero_bouton_clique);
            Jour.set("vendredi");
            jour_semaine.textProperty().bind(Jour);


        });
        B_6.setOnAction(event -> {
            initialisation();
            String jour_transfert = "samedi";
            valeur_Numero_bouton_clique = 6;
            jour_entrainement += 1;
            calendrierclient(valeur_Numero_bouton_clique, jour_entrainement,jour_transfert);
            chargementProgrammeclient(valeur_Numero_bouton_clique);
            Jour.set("samedi");
            jour_semaine.textProperty().bind(Jour);



        });
        B_7.setOnAction(event -> {
            initialisation();
            String jour_transfert = "dimanche";
            valeur_Numero_bouton_clique = 7;
            jour_entrainement += 1;
            calendrierclient(valeur_Numero_bouton_clique, jour_entrainement,jour_transfert);
            chargementProgrammeclient(valeur_Numero_bouton_clique);
            Jour.set("dimanche");
            jour_semaine.textProperty().bind(Jour);

        });
        vider_programme.setOnAction(event ->{
            initialisation();
            vider_table_programme();
    });
        IMC.setOnAction(event ->{
            IMC mv = new IMC();
            Double message_validation = mv.Insert_IMC();
            IMC_client.setText(String.valueOf(message_validation));
            System.out.println(message_validation);


        });
        BMR.setOnAction(event ->{
            BMR_ mv = new BMR_();
            Double message_validation = mv.Insert_BMR();
            BMR_client.setText(String.valueOf(message_validation));
            System.out.println(message_validation);


        });
        NEAT.setOnAction(event ->{
            NEAT mv = new NEAT();
            Double message_validation = mv.Insert_NEAT();
            NEAT_client.setText(String.valueOf(message_validation));
            System.out.println(message_validation);
        });


    }

    public void client_nom_prenom() {
        Bddco bdd = new Bddco();
        try (Connection co = bdd.getConnection()) {
            String query2 = "SELECT  client_selection.Prenom, client_selection.nom " +
                    "FROM client_selection " +
                    "JOIN client ON client.id = client_selection.id ";

            PreparedStatement ps3 = co.prepareStatement(query2);
            ResultSet rs6 = ps3.executeQuery();
            if (rs6.next()) {
                String prenom = rs6.getString("Prenom");
                String nom = rs6.getString("nom");
                nom_client.setText(nom + " " + prenom);
            } else {
                System.out.println("Client non trouvé pour l'ID spécifié.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                bdd.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void chargementProgrammeclient(int valeur_bouton) {
        System.out.println("valeur du bouton" + valeur_bouton);

        // Création de variables pour chaque colonne de la requête SQL
        if (valeur_bouton != 0) { // Vérifie que valeur_bouton est différent de zéro
            Bddco bdd = new Bddco();
            StringBuilder contenu = new StringBuilder();
            try (Connection connection1 = bdd.getConnection()) {
                int client_actuel = 0;
                String sous_requete = "SELECT id_client FROM client_selection";
                Statement statement = connection1.createStatement();
                ResultSet rs = statement.executeQuery(sous_requete);
                if (rs.next()) {
                    client_actuel = rs.getInt("id_client");
                }
                if (valeur_bouton == 8) {
                    String requete =
                            "SELECT * FROM programme " +
                                    "WHERE programme.id_client = ? " +
                                    "AND nom_exercice IS NOT NULL " +
                                    "AND repetitions IS NOT NULL " +
                                    "AND tempsDeRepos IS NOT NULL " +
                                    "AND SeriesEchauffement IS NOT NULL " +
                                    "AND degreDEchec IS NOT NULL " +
                                    "AND percentRepMax IS NOT NULL ";
                    PreparedStatement ps = connection1.prepareStatement(requete);
                    ps.setInt(1, client_actuel);
                    ResultSet rs45 = ps.executeQuery();
                    while (rs45.next()) {
                        // Initialisation des variables
                        String nom_exercice = rs45.getString("nom_exercice");
                        String repetitions = rs45.getString("repetitions");
                        String series = rs45.getString("series");
                        String tempsDeRepos = rs45.getString("tempsDeRepos");
                        String SeriesEchauffement = rs45.getString("SeriesEchauffement");
                        String degreDEchec = rs45.getString("degreDEchec");
                        String percentRepMax = rs45.getString("percentRepMax");
                        contenu.append(nom_exercice).append(", ");
                        contenu.append("REP: ").append(repetitions).append(", ");
                        contenu.append("S: ").append(series).append(", ");
                        contenu.append("Zzz: ").append(tempsDeRepos).append(" minutes, ");
                        contenu.append("SE: ").append(SeriesEchauffement).append(", ");
                        contenu.append("DE: ").append(degreDEchec).append(", ");
                        contenu.append("PRM: ").append(percentRepMax).append(", ").append("\n");
                    }
                    exercices_non_panifie.setText(contenu.toString());

                }
                if (valeur_bouton == 1) {
                    String requete1 = "SELECT * FROM lundi where id_client = ?";
                    PreparedStatement ps1 = connection1.prepareStatement(requete1);
                    ps1.setInt(1, client_actuel);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        // Initialisation des variables
                        String nom_exercice = rs1.getString("nom_exercice");
                        String repetitions = rs1.getString("repetitions");
                        String series = rs1.getString("series");
                        String tempsDeRepos = rs1.getString("tempsDeRepos");
                        String SeriesEchauffement = rs1.getString("SeriesEchauffement");
                        String degreDEchec = rs1.getString("degreDEchec");
                        String percentRepMax = rs1.getString("percentRepMax");
                        contenu.append(nom_exercice).append(", ");
                        contenu.append("Reps: ").append(repetitions).append(", ");
                        contenu.append("S: ").append(series).append(", ");
                        contenu.append("Repos: ").append(tempsDeRepos).append(" minutes, ");
                        contenu.append("SE: ").append(SeriesEchauffement).append(", ");
                        contenu.append("DE: ").append(degreDEchec).append(", ");
                        contenu.append("PRM:").append(percentRepMax).append("\n");
                    }
                    affichage_programme.setText(contenu.toString());
                }
                if (valeur_bouton == 2) {
                    String requete1 = "SELECT * FROM mardi where id_client = ?";
                    PreparedStatement ps1 = connection1.prepareStatement(requete1);
                    ps1.setInt(1, client_actuel);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        // Initialisation des variables
                        String nom_exercice = rs1.getString("nom_exercice");
                        String repetitions = rs1.getString("repetitions");
                        String series = rs1.getString("series");
                        String tempsDeRepos = rs1.getString("tempsDeRepos");
                        String SeriesEchauffement = rs1.getString("SeriesEchauffement");
                        String degreDEchec = rs1.getString("degreDEchec");
                        String percentRepMax = rs1.getString("percentRepMax");
                        contenu.append(nom_exercice).append(", ");
                        contenu.append("Reps: ").append(repetitions).append(", ");
                        contenu.append("S: ").append(series).append(", ");
                        contenu.append("Repos: ").append(tempsDeRepos).append(" minutes, ");
                        contenu.append("SE: ").append(SeriesEchauffement).append(", ");
                        contenu.append("DE: ").append(degreDEchec).append(", ");
                        contenu.append("PRM:").append(percentRepMax).append("\n");
                    }
                    affichage_programme.setText(contenu.toString());
                }
                if (valeur_bouton == 3) {
                    String requete1 = "SELECT * FROM mercredi where id_client = ?";
                    PreparedStatement ps1 = connection1.prepareStatement(requete1);
                    ps1.setInt(1, client_actuel);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        // Initialisation des variables
                        String nom_exercice = rs1.getString("nom_exercice");
                        String repetitions = rs1.getString("repetitions");
                        String series = rs1.getString("series");
                        String tempsDeRepos = rs1.getString("tempsDeRepos");
                        String SeriesEchauffement = rs1.getString("SeriesEchauffement");
                        String degreDEchec = rs1.getString("degreDEchec");
                        String percentRepMax = rs1.getString("percentRepMax");
                        contenu.append(nom_exercice).append(", ");
                        contenu.append("Reps: ").append(repetitions).append(", ");
                        contenu.append("S: ").append(series).append(", ");
                        contenu.append("Repos: ").append(tempsDeRepos).append(" minutes, ");
                        contenu.append("SE: ").append(SeriesEchauffement).append(", ");
                        contenu.append("DE: ").append(degreDEchec).append(", ");
                        contenu.append("PRM:").append(percentRepMax).append("\n");
                    }
                    affichage_programme.setText(contenu.toString());
                }
                if (valeur_bouton == 4) {
                    String requete1 = "SELECT * FROM jeudi where id_client = ?";
                    PreparedStatement ps1 = connection1.prepareStatement(requete1);
                    ps1.setInt(1, client_actuel);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        // Initialisation des variables
                        String nom_exercice = rs1.getString("nom_exercice");
                        String repetitions = rs1.getString("repetitions");
                        String series = rs1.getString("series");
                        String tempsDeRepos = rs1.getString("tempsDeRepos");
                        String SeriesEchauffement = rs1.getString("SeriesEchauffement");
                        String degreDEchec = rs1.getString("degreDEchec");
                        String percentRepMax = rs1.getString("percentRepMax");
                        contenu.append(nom_exercice).append(", ");
                        contenu.append("Reps: ").append(repetitions).append(", ");
                        contenu.append("S: ").append(series).append(", ");
                        contenu.append("Repos: ").append(tempsDeRepos).append(" minutes, ");
                        contenu.append("SE: ").append(SeriesEchauffement).append(", ");
                        contenu.append("DE: ").append(degreDEchec).append(", ");
                        contenu.append("PRM:").append(percentRepMax).append("\n");
                    }
                    affichage_programme.setText(contenu.toString());
                }
                if (valeur_bouton == 5) {
                    String requete1 = "SELECT * FROM vendredi where id_client = ?";
                    PreparedStatement ps1 = connection1.prepareStatement(requete1);
                    ps1.setInt(1, client_actuel);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        // Initialisation des variables
                        String nom_exercice = rs1.getString("nom_exercice");
                        String repetitions = rs1.getString("repetitions");
                        String series = rs1.getString("series");
                        String tempsDeRepos = rs1.getString("tempsDeRepos");
                        String SeriesEchauffement = rs1.getString("SeriesEchauffement");
                        String degreDEchec = rs1.getString("degreDEchec");
                        String percentRepMax = rs1.getString("percentRepMax");
                        contenu.append(nom_exercice).append(", ");
                        contenu.append("Reps: ").append(repetitions).append(", ");
                        contenu.append("S: ").append(series).append(", ");
                        contenu.append("Repos: ").append(tempsDeRepos).append(" minutes, ");
                        contenu.append("SE: ").append(SeriesEchauffement).append(", ");
                        contenu.append("DE: ").append(degreDEchec).append(", ");
                        contenu.append("PRM:").append(percentRepMax).append("\n");
                    }
                    affichage_programme.setText(contenu.toString());
                }
                if (valeur_bouton == 6) {
                    String requete1 = "SELECT * FROM samedi where id_client = ?";
                    PreparedStatement ps1 = connection1.prepareStatement(requete1);
                    ps1.setInt(1, client_actuel);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        // Initialisation des variables
                        String nom_exercice = rs1.getString("nom_exercice");
                        String repetitions = rs1.getString("repetitions");
                        String series = rs1.getString("series");
                        String tempsDeRepos = rs1.getString("tempsDeRepos");
                        String SeriesEchauffement = rs1.getString("SeriesEchauffement");
                        String degreDEchec = rs1.getString("degreDEchec");
                        String percentRepMax = rs1.getString("percentRepMax");
                        contenu.append(nom_exercice).append(", ");
                        contenu.append("Reps: ").append(repetitions).append(", ");
                        contenu.append("S: ").append(series).append(", ");
                        contenu.append("Repos: ").append(tempsDeRepos).append(" minutes, ");
                        contenu.append("SE: ").append(SeriesEchauffement).append(", ");
                        contenu.append("DE: ").append(degreDEchec).append(", ");
                        contenu.append("PRM:").append(percentRepMax).append("\n");
                    }
                    affichage_programme.setText(contenu.toString());
                }
                if (valeur_bouton == 7) {
                    String requete1 = "SELECT * FROM dimanche where id_client = ?";
                    PreparedStatement ps1 = connection1.prepareStatement(requete1);
                    ps1.setInt(1, client_actuel);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        // Initialisation des variables
                        String nom_exercice = rs1.getString("nom_exercice");
                        String repetitions = rs1.getString("repetitions");
                        String series = rs1.getString("series");
                        String tempsDeRepos = rs1.getString("tempsDeRepos");
                        String SeriesEchauffement = rs1.getString("SeriesEchauffement");
                        String degreDEchec = rs1.getString("degreDEchec");
                        String percentRepMax = rs1.getString("percentRepMax");
                        contenu.append(nom_exercice).append(", ");
                        contenu.append("Reps: ").append(repetitions).append(", ");
                        contenu.append("S: ").append(series).append(", ");
                        contenu.append("Repos: ").append(tempsDeRepos).append(" minutes, ");
                        contenu.append("SE: ").append(SeriesEchauffement).append(", ");
                        contenu.append("DE: ").append(degreDEchec).append(", ");
                        contenu.append("PRM:").append(percentRepMax).append("\n");
                    }
                    affichage_programme.setText(contenu.toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bdd.closeConnection();
            }
        }
    }


    public void calendrierclient(int jour_semaine, int jour_entrainement_ou_pause,String jour_transfert) {

            while (jour_entrainement_ou_pause > 1) {
                System.out.println("nombre d'itérations : " + jour_entrainement_ou_pause);
                jour_entrainement_ou_pause = jour_entrainement_ou_pause % 2;
            }
            switch (jour_semaine) { // Switch déterminant le jour de la semaine
                case 1: // Lundi
                    switch (jour_entrainement_ou_pause) {
                        case 0:
                            Lundi_id.setStyle("-fx-background-color: red;"); // Jour de pause
                            System.out.println("Lundi, c'est un jour de pause ");
                            transfer_programme_x_jour(jour_transfert,0);
                            break;
                        case 1:
                            Lundi_id.setStyle("-fx-background-color: green;"); // Jour d'entraînement
                            System.out.println("Ha bah dis donc, on s'entraîne à ce que je vois :)");
                            transfer_programme_x_jour(jour_transfert,1);
                            break;
                    }
                    break;
                case 2: // Mardi
                    switch (jour_entrainement_ou_pause) {
                        case 1:
                            Mardi_id.setStyle("-fx-background-color: red;"); // Jour de pause
                            System.out.println("Mardi, c'est un jour de pause ");
                            transfer_programme_x_jour(jour_transfert,0);
                            break;
                        case 0:
                            Mardi_id.setStyle("-fx-background-color: green;"); // Jour d'entraînement
                            System.out.println("Ha bah dis donc, on s'entraîne à ce que je vois :)");
                            transfer_programme_x_jour(jour_transfert,1);
                            break;
                    }
                    break;
                case 3: // Mercredi
                    switch (jour_entrainement_ou_pause) {
                        case 1:
                            Mercredi_id.setStyle("-fx-background-color: red;"); // Jour de pause
                            System.out.println("Mercredi, c'est un jour de pause ");
                            transfer_programme_x_jour(jour_transfert,0);
                            break;
                        case 0:
                            Mercredi_id.setStyle("-fx-background-color: green;"); // Jour d'entraînement
                            transfer_programme_x_jour(jour_transfert,1);
                            break;
                    }
                    break;
                case 4: // Jeudi
                    switch (jour_entrainement_ou_pause) {
                        case 1:
                            Jeudi_id.setStyle("-fx-background-color: red;"); // Jour de pause
                            System.out.println("Jeudi, c'est un jour de pause ");
                            transfer_programme_x_jour(jour_transfert,0);
                            break;
                        case 0:
                            Jeudi_id.setStyle("-fx-background-color: green;"); // Jour d'entraînement
                            transfer_programme_x_jour(jour_transfert,1);
                            break;
                    }
                    break;
                case 5: // Vendredi
                    switch (jour_entrainement_ou_pause) {
                        case 1:
                            Vendredi_id.setStyle("-fx-background-color: red;"); // Jour de pause
                            System.out.println("Vendredi, c'est un jour de pause ");
                            transfer_programme_x_jour(jour_transfert,0);
                            break;
                        case 0:
                            Vendredi_id.setStyle("-fx-background-color: green;"); // Jour d'entraînement
                            transfer_programme_x_jour(jour_transfert,1);
                            break;
                    }
                    break;
                case 6: // Samedi
                    switch (jour_entrainement_ou_pause) {
                        case 1:
                            Samedi_id.setStyle("-fx-background-color: red;"); // Jour de pause
                            System.out.println("Samedi, c'est un jour de pause ");
                            transfer_programme_x_jour(jour_transfert,0);
                            break;
                        case 0:
                            Samedi_id.setStyle("-fx-background-color: green;"); // Jour d'entraînement
                            transfer_programme_x_jour(jour_transfert,1);
                            break;
                    }
                    break;
                case 7: // Dimanche
                    switch (jour_entrainement_ou_pause) {
                        case 1:
                            Dimanche_id.setStyle("-fx-background-color: red;"); // Jour de pause
                            System.out.println("Dimanche, c'est un jour de pause ");
                            transfer_programme_x_jour(jour_transfert,0);
                            break;
                        case 0:
                            Dimanche_id.setStyle("-fx-background-color: green;"); // Jour d'entraînement
                            transfer_programme_x_jour(jour_transfert,1);
                            break;
                    }
                    break;
            }


    }
    //public void changement_programme(ComboBox<String> n){
    //if


    //}
    @FXML
    public void Combobox_calendrier() {
        Lundi_modify.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int a;
            if (newValue.equals("Tout supprimer")) {
                a = 1;
                initialisation();
                Suppression_Suppression_complete(a, "lundi");
            } else if (newValue.equals("Supprimer")) {
                a = 2;
                initialisation();
                Suppression_Suppression_complete(a, "lundi");
            }
            else if (newValue.equals("^")){
                System.out.println("passerelle pour pouvoir supprimer le dernier élément de lundi activée rappuyer sur supprimer");
            }
            else {
                System.out.println("vous n'avez pas selectionné d'action sur Lundi");
            }


        });

        Mardi_modify.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            int a;
            if (newValue.equals("Tout supprimer")) {
                a = 1;
                Suppression_Suppression_complete(a, "mardi");
                initialisation();
            } else if (newValue.equals("Supprimer")) {
                a = 2;
                Suppression_Suppression_complete(a, "mardi");
                initialisation();
            }
            else if (newValue.equals("^")){
                System.out.println("passerelle pour pouvoir supprimer le dernier élément de lundi activée rappuyer sur supprimer");
            }
            else {
                System.out.println("vous n'avez pas selectionné d'action sur Mardi");
            }


        });

        Mercredi_modify.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            int a;
            if (newValue.equals("Tout supprimer")) {
                a = 1;
                Suppression_Suppression_complete(a, "mercredi");
                initialisation();
            } else if (newValue.equals("Supprimer")) {
                a = 2;
                Suppression_Suppression_complete(a, "mercredi");
                initialisation();
            }
            else if (newValue.equals("^")){
                System.out.println("passerelle pour pouvoir supprimer le dernier élément de lundi activée rappuyer sur supprimer");
            }
            else {
                System.out.println("vous n'avez pas selectionné d'action sur Mercredi");
            }

        });

        Jeudi_modify.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            int a;
            if (newValue.equals("Tout supprimer")) {
                a = 1;
                Suppression_Suppression_complete(a, "jeudi");
                initialisation();
            } else if (newValue.equals("Supprimer")) {
                a = 2;
                Suppression_Suppression_complete(a, "jeudi");
                initialisation();
            }
            else if (newValue.equals("^")){
                System.out.println("passerelle pour pouvoir supprimer le dernier élément de lundi activée rappuyer sur supprimer");
            }
            else {
                System.out.println("vous n'avez pas selectionné d'action sur Jeudi");
            }
        });

        Vendredi_modify.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            int a;
            if (newValue.equals("Tout supprimer")) {
                a = 1;
                Suppression_Suppression_complete(a, "vendredi");
                initialisation();
            } else if (newValue.equals("Supprimer")) {
                a = 2;
                Suppression_Suppression_complete(a, "vendredi");
                initialisation();
            }
            else if (newValue.equals("^")){
                System.out.println("passerelle pour pouvoir supprimer le dernier élément de lundi activée rappuyer sur supprimer");
            }
            else {
                System.out.println("vous n'avez pas selectionné d'action sur Vendredi");
            }

        });

        Samedi_modify.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int a;
            if (newValue.equals("Tout supprimer")) {
                a = 1;
                Suppression_Suppression_complete(a, "samedi");
                initialisation();
            } else if (newValue.equals("Supprimer")) {
                a = 2;
                Suppression_Suppression_complete(a, "samedi");
                initialisation();
            }
            else if (newValue.equals("^")){
                System.out.println("passerelle pour pouvoir supprimer le dernier élément de lundi activée rappuyer sur supprimer");
            }
            else {
                System.out.println("vous n'avez pas selectionné d'action sur Semedi");
            }
        });

        Dimanche_modify.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int a;
            if (newValue.equals("Tout supprimer")) {
                a = 1;
                Suppression_Suppression_complete(a, "dimanche");
                initialisation();
            } else if (newValue.equals("Supprimer")) {
                a = 2;
                Suppression_Suppression_complete(a, "dimanche");
                initialisation();
            }
            else if (newValue.equals("^")){
                System.out.println("passerelle pour pouvoir supprimer le dernier élément de lundi activée rappuyer sur supprimer");
            }
            else {
                System.out.println("vous n'avez pas selectionné d'action sur Dimanche");
            }

        });


    }

    public void Suppression_Suppression_complete(int option, String Journee) {
        Bddco bdd = new Bddco();
        String Tout_supprimer;
        try (Connection connection1 = bdd.getConnection()) {
            if (option == 1) {
                if (Journee.equals("lundi")) {
                    Tout_supprimer = "TRUNCATE lundi";
                    Statement ps = connection1.createStatement();

                    {
                        int rowsAffected = ps.executeUpdate(Tout_supprimer);
                        System.out.println(rowsAffected);
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 1);
                        initialisation();
                    }
                } else if (Journee.equals("mardi")) {
                    Tout_supprimer = "TRUNCATE mardi";
                    Statement ps = connection1.createStatement();
                    {
                        int rowsAffected = ps.executeUpdate(Tout_supprimer);
                        System.out.println(rowsAffected);
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 2);
                        initialisation();
                    }
                } else if (Journee.equals("mercredi")) {
                    Tout_supprimer = "TRUNCATE mercredi";
                    Statement ps = connection1.createStatement();
                    {
                        int rowsAffected = ps.executeUpdate(Tout_supprimer);
                        System.out.println(rowsAffected);
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 3);
                        initialisation();
                    }
                } else if (Journee.equals("jeudi")) {
                    Tout_supprimer = "TRUNCATE jeudi";
                    Statement ps = connection1.createStatement();
                    {
                        int rowsAffected = ps.executeUpdate(Tout_supprimer);
                        System.out.println(rowsAffected);
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 4);
                        initialisation();
                    }
                } else if (Journee.equals("vendredi")) {
                    Tout_supprimer = "TRUNCATE vendredi";
                    Statement ps = connection1.createStatement();
                    {
                        int rowsAffected = ps.executeUpdate(Tout_supprimer);
                        System.out.println(rowsAffected);
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 5);
                        initialisation();
                    }
                } else if (Journee.equals("samedi_modify")) {
                    Tout_supprimer = "TRUNCATE samedi";
                    Statement ps = connection1.createStatement();
                    {
                        int rowsAffected = ps.executeUpdate(Tout_supprimer);
                        System.out.println(rowsAffected);
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 6);
                        initialisation();
                    }
                } else if (Journee.equals("dimanche")) {
                    Tout_supprimer = "TRUNCATE dimanche";
                    Statement ps = connection1.createStatement();
                    {
                        int rowsAffected = ps.executeUpdate(Tout_supprimer);
                        System.out.println(rowsAffected);
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 7);
                        initialisation();
                    }
                }
            } else if (option == 2) {
                if (Journee.equals("lundi")) {
                    String idQuery = "SELECT id_client AS dernier_exo FROM client_selection";
                    Statement statement2 = connection1.createStatement();
                    ResultSet resultat = statement2.executeQuery(idQuery);
                    if (resultat.next()) {
                        int exo_recent = resultat.getInt("dernier_exo");

                        String idQuery2 = "SELECT id FROM lundi ORDER BY id DESC LIMIT 1";
                        Statement statement = connection1.createStatement();
                        ResultSet resultSet = statement.executeQuery(idQuery2);
                        if (resultSet.next()) {
                            int idPourSuppression = resultSet.getInt("id");

                            String supprimer = "DELETE FROM lundi WHERE id_client = ? AND id = ?";
                            PreparedStatement ps = connection1.prepareStatement(supprimer);
                            ps.setInt(1, exo_recent);
                            ps.setInt(2, idPourSuppression);
                            int rowsAffected = ps.executeUpdate();
                            System.out.println(rowsAffected + " rows deleted.");
                            resultSet.close();
                            statement.close();
                            ps.close();
                        }
                        resultat.close();
                        statement2.close();
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 1);
                        initialisation();
                    }
                }else if (Journee.equals("mardi")) {
                    String idQuery = "SELECT id_client AS dernier_exo FROM client_selection";
                    Statement statement2 = connection1.createStatement();
                    ResultSet resultat = statement2.executeQuery(idQuery);
                    if (resultat.next()) {
                        int exo_recent = resultat.getInt("dernier_exo");

                        String idQuery2 = "SELECT id FROM mardi ORDER BY id DESC LIMIT 1";
                        Statement statement = connection1.createStatement();
                        ResultSet resultSet = statement.executeQuery(idQuery2);
                        if (resultSet.next()) {
                            int idPourSuppression = resultSet.getInt("id");

                            String supprimer = "DELETE FROM mardi WHERE id_client = ? AND id = ?";
                            PreparedStatement ps = connection1.prepareStatement(supprimer);
                            ps.setInt(1, exo_recent);
                            ps.setInt(2, idPourSuppression);
                            int rowsAffected = ps.executeUpdate();
                            System.out.println(rowsAffected + " rows deleted.");
                            resultSet.close();
                            statement.close();
                            ps.close();
                        }
                        resultat.close();
                        statement2.close();
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 2);
                        initialisation();
                    }
                }else if (Journee.equals("mercredi")) {
                    String idQuery = "SELECT id_client AS dernier_exo FROM client_selection";
                    Statement statement2 = connection1.createStatement();
                    ResultSet resultat = statement2.executeQuery(idQuery);
                    if (resultat.next()) {
                        int exo_recent = resultat.getInt("dernier_exo");

                        String idQuery2 = "SELECT id FROM mercredi ORDER BY id DESC LIMIT 1";
                        Statement statement = connection1.createStatement();
                        ResultSet resultSet = statement.executeQuery(idQuery2);
                        if (resultSet.next()) {
                            int idPourSuppression = resultSet.getInt("id");

                            String supprimer = "DELETE FROM mercredi WHERE id_client = ? AND id = ?";
                            PreparedStatement ps = connection1.prepareStatement(supprimer);
                            ps.setInt(1, exo_recent);
                            ps.setInt(2, idPourSuppression);
                            int rowsAffected = ps.executeUpdate();
                            System.out.println(rowsAffected + " rows deleted.");
                            resultSet.close();
                            statement.close();
                            ps.close();
                        }
                        resultat.close();
                        statement2.close();
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 3);
                        initialisation();
                    }
                }//////


                else if (Journee.equals("jeudi")) {
                    String idQuery = "SELECT id_client AS dernier_exo FROM client_selection";
                    Statement statement2 = connection1.createStatement();
                    ResultSet resultat = statement2.executeQuery(idQuery);
                    if (resultat.next()) {
                        int exo_recent = resultat.getInt("dernier_exo");

                        String idQuery2 = "SELECT id FROM jeudi ORDER BY id DESC LIMIT 1";
                        Statement statement = connection1.createStatement();
                        ResultSet resultSet = statement.executeQuery(idQuery2);
                        if (resultSet.next()) {
                            int idPourSuppression = resultSet.getInt("id");

                            String supprimer = "DELETE FROM jeudi WHERE id_client = ? AND id = ?";
                            PreparedStatement ps = connection1.prepareStatement(supprimer);
                            ps.setInt(1, exo_recent);
                            ps.setInt(2, idPourSuppression);
                            int rowsAffected = ps.executeUpdate();
                            System.out.println(rowsAffected + " rows deleted.");
                            resultSet.close();
                            statement.close();
                            ps.close();
                        }
                        resultat.close();
                        statement2.close();
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 4);
                        initialisation();
                    }
                }else if (Journee.equals("vendredi")) {
                    String idQuery = "SELECT id_client AS dernier_exo FROM client_selection";
                    Statement statement2 = connection1.createStatement();
                    ResultSet resultat = statement2.executeQuery(idQuery);
                    if (resultat.next()) {
                        int exo_recent = resultat.getInt("dernier_exo");

                        String idQuery2 = "SELECT id FROM vendredi ORDER BY id DESC LIMIT 1";
                        Statement statement = connection1.createStatement();
                        ResultSet resultSet = statement.executeQuery(idQuery2);
                        if (resultSet.next()) {
                            int idPourSuppression = resultSet.getInt("id");

                            String supprimer = "DELETE FROM vendredi WHERE id_client = ? AND id = ?";
                            PreparedStatement ps = connection1.prepareStatement(supprimer);
                            ps.setInt(1, exo_recent);
                            ps.setInt(2, idPourSuppression);
                            int rowsAffected = ps.executeUpdate();
                            System.out.println(rowsAffected + " rows deleted.");
                            resultSet.close();
                            statement.close();
                            ps.close();
                        }
                        resultat.close();
                        statement2.close();
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 5);
                        initialisation();
                    }
                }else if (Journee.equals("samedi")) {
                    String idQuery = "SELECT id_client AS dernier_exo FROM client_selection";
                    Statement statement2 = connection1.createStatement();
                    ResultSet resultat = statement2.executeQuery(idQuery);
                    if (resultat.next()) {
                        int exo_recent = resultat.getInt("dernier_exo");

                        String idQuery2 = "SELECT id FROM samedi ORDER BY id DESC LIMIT 1";
                        Statement statement = connection1.createStatement();
                        ResultSet resultSet = statement.executeQuery(idQuery2);
                        if (resultSet.next()) {
                            int idPourSuppression = resultSet.getInt("id");

                            String supprimer = "DELETE FROM samedi WHERE id_client = ? AND id = ?";
                            PreparedStatement ps = connection1.prepareStatement(supprimer);
                            ps.setInt(1, exo_recent);
                            ps.setInt(2, idPourSuppression);
                            int rowsAffected = ps.executeUpdate();
                            System.out.println(rowsAffected + " rows deleted.");
                            resultSet.close();
                            statement.close();
                            ps.close();
                        }
                        resultat.close();
                        statement2.close();
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 6);
                        initialisation();
                    }
                }else if (Journee.equals("dimanche")) {
                    String idQuery = "SELECT id_client AS dernier_exo FROM client_selection";
                    Statement statement2 = connection1.createStatement();
                    ResultSet resultat = statement2.executeQuery(idQuery);
                    if (resultat.next()) {
                        int exo_recent = resultat.getInt("dernier_exo");

                        String idQuery2 = "SELECT id FROM dimanche ORDER BY id DESC LIMIT 1";
                        Statement statement = connection1.createStatement();
                        ResultSet resultSet = statement.executeQuery(idQuery2);
                        if (resultSet.next()) {
                            int idPourSuppression = resultSet.getInt("id");

                            String supprimer = "DELETE FROM dimanche WHERE id_client = ? AND id = ?";
                            PreparedStatement ps = connection1.prepareStatement(supprimer);
                            ps.setInt(1, exo_recent);
                            ps.setInt(2, idPourSuppression);
                            int rowsAffected = ps.executeUpdate();
                            System.out.println(rowsAffected + " rows deleted.");
                            resultSet.close();
                            statement.close();
                            ps.close();
                        }
                        resultat.close();
                        statement2.close();
                        chargementProgrammeclient(valeur_Numero_bouton_clique = 7);
                        initialisation();
                    }
                }//////
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bdd.closeConnection();
        }
    }

    public void transfer_programme_x_jour(String jour, int condition) {
        Bddco bdd = new Bddco();
        try (Connection co = bdd.getConnection()) {
           if (condition ==1){
            if (jour.equals("lundi")) {
                String requete1 = "SELECT id_client FROM client_selection";
                Statement statement = co.createStatement();
                ResultSet transfert = statement.executeQuery(requete1);
                if (transfert.next()) {
                    int id_client_concerne = transfert.getInt("id_client");




                    String truncate_lundi = "DELETE FROM lundi WHERE nom_exercice = ? AND id_client = ?";
                    PreparedStatement pstruncate_lundi = co.prepareStatement(truncate_lundi);
                    pstruncate_lundi.setString(1, "Jour de Pause");
                    pstruncate_lundi.setInt(2, id_client_concerne);
                    int rowsDeleted = pstruncate_lundi.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println(rowsDeleted + " lignes supprimées.");
                    }




                    String requete2 = "SELECT nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client " +
                            "FROM programme WHERE programme.id_client = ?";
                    PreparedStatement ps5 = co.prepareStatement(requete2);
                    ps5.setInt(1, id_client_concerne);
                    ResultSet resultat = ps5.executeQuery();


                    String requete3 = "INSERT INTO lundi(nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                    PreparedStatement insertStatement = co.prepareStatement(requete3);

                    while (resultat.next()) {
                        insertStatement.setString(1, resultat.getString("nom_exercice"));
                        insertStatement.setInt(2, resultat.getInt("repetitions"));
                        insertStatement.setInt(3, resultat.getInt("series"));
                        insertStatement.setInt(4, resultat.getInt("tempsDeRepos"));
                        insertStatement.setInt(5, resultat.getInt("SeriesEchauffement"));
                        insertStatement.setInt(6, resultat.getInt("degreDEchec"));
                        insertStatement.setInt(7, resultat.getInt("percentRepMax"));
                        insertStatement.setInt(8, id_client_concerne);

                        insertStatement.executeUpdate();
                    }
                }

            }
            if (jour.equals("mardi")) {
                String requete1 = "SELECT id_client FROM client_selection";
                Statement statement = co.createStatement();
                ResultSet transfert = statement.executeQuery(requete1);
                if (transfert.next()) {
                    int id_client_concerne = transfert.getInt("id_client");



                    String truncate_mardi = "DELETE FROM mardi WHERE nom_exercice = ? AND id_client = ?";
                    PreparedStatement pstruncate_lundi = co.prepareStatement(truncate_mardi);
                    pstruncate_lundi.setString(1, "Jour de Pause");
                    pstruncate_lundi.setInt(2, id_client_concerne);
                    int rowsDeleted = pstruncate_lundi.executeUpdate();
                    if (rowsDeleted > 1) {
                        System.out.println(rowsDeleted + " lignes supprimées.");
                    }

                    String requete2 = "SELECT nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client " +
                            "FROM programme WHERE programme.id_client = ?";
                    PreparedStatement ps5 = co.prepareStatement(requete2);
                    ps5.setInt(1, id_client_concerne);
                    ResultSet resultat = ps5.executeQuery();


                    String requete3 = "INSERT INTO mardi(nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                    PreparedStatement insertStatement = co.prepareStatement(requete3);

                    while (resultat.next()) {
                        insertStatement.setString(1, resultat.getString("nom_exercice"));
                        insertStatement.setInt(2, resultat.getInt("repetitions"));
                        insertStatement.setInt(3, resultat.getInt("series"));
                        insertStatement.setInt(4, resultat.getInt("tempsDeRepos"));
                        insertStatement.setInt(5, resultat.getInt("SeriesEchauffement"));
                        insertStatement.setInt(6, resultat.getInt("degreDEchec"));
                        insertStatement.setInt(7, resultat.getInt("percentRepMax"));
                        insertStatement.setInt(8, id_client_concerne);

                        insertStatement.executeUpdate();
                    }
                }

            }
            if (jour.equals("mercredi")) {
                String requete1 = "SELECT id_client FROM client_selection";
                Statement statement = co.createStatement();
                ResultSet transfert = statement.executeQuery(requete1);
                if (transfert.next()) {
                    int id_client_concerne = transfert.getInt("id_client");

                    String truncate_mercredi = "DELETE FROM mercredi WHERE nom_exercice = ? AND id_client = ?";
                    PreparedStatement pstruncate_lundi = co.prepareStatement(truncate_mercredi);
                    pstruncate_lundi.setString(1, "Jour de Pause");
                    pstruncate_lundi.setInt(2, id_client_concerne);
                    int rowsDeleted = pstruncate_lundi.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println(rowsDeleted + " lignes supprimées.");
                    }

                    String requete2 = "SELECT nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client " +
                            "FROM programme WHERE programme.id_client = ?";
                    PreparedStatement ps5 = co.prepareStatement(requete2);
                    ps5.setInt(1, id_client_concerne);
                    ResultSet resultat = ps5.executeQuery();


                    String requete3 = "INSERT INTO mercredi(nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                    PreparedStatement insertStatement = co.prepareStatement(requete3);

                    while (resultat.next()) {
                        insertStatement.setString(1, resultat.getString("nom_exercice"));
                        insertStatement.setInt(2, resultat.getInt("repetitions"));
                        insertStatement.setInt(3, resultat.getInt("series"));
                        insertStatement.setInt(4, resultat.getInt("tempsDeRepos"));
                        insertStatement.setInt(5, resultat.getInt("SeriesEchauffement"));
                        insertStatement.setInt(6, resultat.getInt("degreDEchec"));
                        insertStatement.setInt(7, resultat.getInt("percentRepMax"));
                        insertStatement.setInt(8, id_client_concerne);

                        insertStatement.executeUpdate();
                    }
                }

            }
            if (jour.equals("jeudi")) {
                String requete1 = "SELECT id_client FROM client_selection";
                Statement statement = co.createStatement();
                ResultSet transfert = statement.executeQuery(requete1);
                if (transfert.next()) {
                    int id_client_concerne = transfert.getInt("id_client");

                    String truncate_jeudi = "DELETE FROM jeudi WHERE nom_exercice = ? AND id_client = ?";
                    PreparedStatement pstruncate_lundi = co.prepareStatement(truncate_jeudi);
                    pstruncate_lundi.setString(1, "Jour de Pause");
                    pstruncate_lundi.setInt(2, id_client_concerne);
                    int rowsDeleted = pstruncate_lundi.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println(rowsDeleted + " lignes supprimées.");
                    }

                    String requete2 = "SELECT nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client " +
                            "FROM programme WHERE programme.id_client = ?";
                    PreparedStatement ps5 = co.prepareStatement(requete2);
                    ps5.setInt(1, id_client_concerne);
                    ResultSet resultat = ps5.executeQuery();


                    String requete3 = "INSERT INTO jeudi(nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                    PreparedStatement insertStatement = co.prepareStatement(requete3);

                    while (resultat.next()) {
                        insertStatement.setString(1, resultat.getString("nom_exercice"));
                        insertStatement.setInt(2, resultat.getInt("repetitions"));
                        insertStatement.setInt(3, resultat.getInt("series"));
                        insertStatement.setInt(4, resultat.getInt("tempsDeRepos"));
                        insertStatement.setInt(5, resultat.getInt("SeriesEchauffement"));
                        insertStatement.setInt(6, resultat.getInt("degreDEchec"));
                        insertStatement.setInt(7, resultat.getInt("percentRepMax"));
                        insertStatement.setInt(8, id_client_concerne);

                        insertStatement.executeUpdate();
                    }
                }

            }
            if (jour.equals("vendredi")) {
                String requete1 = "SELECT id_client FROM client_selection";
                Statement statement = co.createStatement();
                ResultSet transfert = statement.executeQuery(requete1);
                if (transfert.next()) {
                    int id_client_concerne = transfert.getInt("id_client");

                    String truncate_vendredi = "DELETE FROM vendredi WHERE nom_exercice = ? AND id_client = ?";
                    PreparedStatement pstruncate_lundi = co.prepareStatement(truncate_vendredi);
                    pstruncate_lundi.setString(1, "Jour de Pause");
                    pstruncate_lundi.setInt(2, id_client_concerne);
                    int rowsDeleted = pstruncate_lundi.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println(rowsDeleted + " lignes supprimées.");
                    }

                    String requete2 = "SELECT nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client " +
                            "FROM programme WHERE id_client = ?";
                    PreparedStatement statement1 = co.prepareStatement(requete2);
                    statement1.setInt(1, id_client_concerne);
                    ResultSet resultat = statement1.executeQuery();

                    String requete3 = "INSERT INTO vendredi(nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                    PreparedStatement insertStatement = co.prepareStatement(requete3);

                    while (resultat.next()) {
                        insertStatement.setString(1, resultat.getString("nom_exercice"));
                        insertStatement.setInt(2, resultat.getInt("repetitions"));
                        insertStatement.setInt(3, resultat.getInt("series"));
                        insertStatement.setInt(4, resultat.getInt("tempsDeRepos"));
                        insertStatement.setInt(5, resultat.getInt("SeriesEchauffement"));
                        insertStatement.setInt(6, resultat.getInt("degreDEchec"));
                        insertStatement.setInt(7, resultat.getInt("percentRepMax"));
                        insertStatement.setInt(8, id_client_concerne);

                        insertStatement.executeUpdate();
                    }
                }

            }
            if (jour.equals("samedi")) {
                String requete1 = "SELECT id_client FROM client_selection";
                Statement statement = co.createStatement();
                ResultSet transfert = statement.executeQuery(requete1);
                if (transfert.next()) {
                    int id_client_concerne = transfert.getInt("id_client");

                    String truncate_samedi = "DELETE FROM samedi WHERE nom_exercice = ? AND id_client = ?";
                    PreparedStatement pstruncate_lundi = co.prepareStatement(truncate_samedi);
                    pstruncate_lundi.setString(1, "Jour de Pause");
                    pstruncate_lundi.setInt(2, id_client_concerne);
                    int rowsDeleted = pstruncate_lundi.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println(rowsDeleted + " lignes supprimées.");
                    }

                    String requete2 = "SELECT nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client " +
                            "FROM programme WHERE id_client = ?";
                    PreparedStatement statement1 = co.prepareStatement(requete2);
                    statement1.setInt(1, id_client_concerne);
                    ResultSet resultat = statement1.executeQuery();

                    String requete3 = "INSERT INTO samedi(nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                    PreparedStatement insertStatement = co.prepareStatement(requete3);

                    while (resultat.next()) {
                        insertStatement.setString(1, resultat.getString("nom_exercice"));
                        insertStatement.setInt(2, resultat.getInt("repetitions"));
                        insertStatement.setInt(3, resultat.getInt("series"));
                        insertStatement.setInt(4, resultat.getInt("tempsDeRepos"));
                        insertStatement.setInt(5, resultat.getInt("SeriesEchauffement"));
                        insertStatement.setInt(6, resultat.getInt("degreDEchec"));
                        insertStatement.setInt(7, resultat.getInt("percentRepMax"));
                        insertStatement.setInt(8, resultat.getInt("id_client"));

                        insertStatement.executeUpdate();
                    }
                }

            }
            if (jour.equals("dimanche")) {
                String requete1 = "SELECT id_client FROM client_selection";
                Statement statement = co.createStatement();
                ResultSet transfert = statement.executeQuery(requete1);
                if (transfert.next()) {
                    int id_client_concerne = transfert.getInt("id_client");


                    String truncate_dimanche = "DELETE FROM dimanche WHERE nom_exercice = ? AND id_client = ?";
                    PreparedStatement pstruncate_lundi = co.prepareStatement(truncate_dimanche);
                    pstruncate_lundi.setString(1, "Jour de Pause");
                    pstruncate_lundi.setInt(2, id_client_concerne);
                    int rowsDeleted = pstruncate_lundi.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println(rowsDeleted + " lignes supprimées.");
                    }

                    String requete2 = "SELECT nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client " +
                            "FROM programme WHERE id_client = ?";
                    PreparedStatement statement1 = co.prepareStatement(requete2);
                    statement1.setInt(1, id_client_concerne);
                    ResultSet resultat = statement1.executeQuery();

                    String requete3 = "INSERT INTO dimanche(nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                    PreparedStatement insertStatement = co.prepareStatement(requete3);

                    while (resultat.next()) {
                        insertStatement.setString(1, resultat.getString("nom_exercice"));
                        insertStatement.setInt(2, resultat.getInt("repetitions"));
                        insertStatement.setInt(3, resultat.getInt("series"));
                        insertStatement.setInt(4, resultat.getInt("tempsDeRepos"));
                        insertStatement.setInt(5, resultat.getInt("SeriesEchauffement"));
                        insertStatement.setInt(6, resultat.getInt("degreDEchec"));
                        insertStatement.setInt(7, resultat.getInt("percentRepMax"));
                        insertStatement.setInt(8, id_client_concerne);

                        insertStatement.executeUpdate();
                    }
                }

            }
           } if(condition ==0){
                if (jour.equals("lundi")) {
                    String requete1 = "SELECT id_client FROM client_selection";
                    Statement statement = co.createStatement();
                    ResultSet transfert = statement.executeQuery(requete1);
                    if (transfert.next()) {
                        int id_client_concerne = transfert.getInt("id_client");

                        String requete3 = "UPDATE lundi SET nom_exercice = ?,repetitions = ?,series = ?,tempsDeRepos = ?,SeriesEchauffement = ?,degreDEchec = ?,percentRepMax= ? WHERE  id_client = ? ";
                        // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                        PreparedStatement UpdateStatement = co.prepareStatement(requete3);
                        UpdateStatement.setInt(8,id_client_concerne);
                        UpdateStatement.setString(1,"Jour de Pause");
                        UpdateStatement.setInt(2,0);
                        UpdateStatement.setInt(3,0);
                        UpdateStatement.setInt(4,0);
                        UpdateStatement.setInt(5,0);
                        UpdateStatement.setInt(6,0);
                        UpdateStatement.setInt(7,0);
                        int lignes_touches_lundi = UpdateStatement.executeUpdate();
                        if (lignes_touches_lundi>0){
                            System.out.println("nom de lignes dans lundi changées : "+lignes_touches_lundi);
                        }else{
                            System.out.println("aucune ligne existante, insertion activée");
                            String requete3bis = "INSERT INTO lundi (nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax,id_client) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                            PreparedStatement re_requete3bis = co.prepareStatement(requete3bis);
                            re_requete3bis.setInt(8,id_client_concerne);
                            re_requete3bis.setString(1,"Jour de Pause");
                            re_requete3bis.setInt(2,0);
                            re_requete3bis.setInt(3,0);
                            re_requete3bis.setInt(4,0);
                            re_requete3bis.setInt(5,0);
                            re_requete3bis.setInt(6,0);
                            re_requete3bis.setInt(7,0);
                            re_requete3bis.executeUpdate();


                        }


                    }

                } /////////
                if (jour.equals("mardi")) {
                    String requete1 = "SELECT id_client FROM client_selection";
                    Statement statement = co.createStatement();
                    ResultSet transfert = statement.executeQuery(requete1);
                    if (transfert.next()) {
                        int id_client_concerne = transfert.getInt("id_client");

                        String requete3 = "UPDATE mardi SET nom_exercice = ?,repetitions = ?,series = ?,tempsDeRepos = ?,SeriesEchauffement = ?,degreDEchec = ?,percentRepMax= ? WHERE  id_client = ? ";
                        // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                        PreparedStatement UpdateStatement = co.prepareStatement(requete3);
                        UpdateStatement.setInt(8,id_client_concerne);
                        UpdateStatement.setString(1,"Jour de Pause");
                        UpdateStatement.setInt(2,0);
                        UpdateStatement.setInt(3,0);
                        UpdateStatement.setInt(4,0);
                        UpdateStatement.setInt(5,0);
                        UpdateStatement.setInt(6,0);
                        UpdateStatement.setInt(7,0);
                        int lignes_touches_mardi = UpdateStatement.executeUpdate();
                        if (lignes_touches_mardi>0){
                            System.out.println("nom de lignes dans mardi changées : "+lignes_touches_mardi);
                        }else{
                            System.out.println("aucune ligne existante, insertion activée");
                            String requete3bis = "INSERT INTO mardi (nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax,id_client) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                            PreparedStatement re_requete3bis = co.prepareStatement(requete3bis);
                            re_requete3bis.setInt(8,id_client_concerne);
                            re_requete3bis.setString(1,"Jour de Pause");
                            re_requete3bis.setInt(2,0);
                            re_requete3bis.setInt(3,0);
                            re_requete3bis.setInt(4,0);
                            re_requete3bis.setInt(5,0);
                            re_requete3bis.setInt(6,0);
                            re_requete3bis.setInt(7,0);
                            re_requete3bis.executeUpdate();


                        }


                    }

                }
                if (jour.equals("mercredi")) {
                    String requete1 = "SELECT id_client FROM client_selection";
                    Statement statement = co.createStatement();
                    ResultSet transfert = statement.executeQuery(requete1);
                    if (transfert.next()) {
                        int id_client_concerne = transfert.getInt("id_client");

                        String requete3 = "UPDATE mercredi SET nom_exercice = ?,repetitions = ?,series = ?,tempsDeRepos = ?,SeriesEchauffement = ?,degreDEchec = ?,percentRepMax= ? WHERE  id_client = ? ";
                        // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                        PreparedStatement UpdateStatement = co.prepareStatement(requete3);
                        UpdateStatement.setInt(8,id_client_concerne);
                        UpdateStatement.setString(1,"Jour de Pause");
                        UpdateStatement.setInt(2,0);
                        UpdateStatement.setInt(3,0);
                        UpdateStatement.setInt(4,0);
                        UpdateStatement.setInt(5,0);
                        UpdateStatement.setInt(6,0);
                        UpdateStatement.setInt(7,0);
                        int lignes_touches_mercredi = UpdateStatement.executeUpdate();
                        if (lignes_touches_mercredi>0){
                            System.out.println("nom de lignes dans lundi changées : "+lignes_touches_mercredi);
                        }else{
                            System.out.println("aucune ligne existante, insertion activée");
                            String requete3bis = "INSERT INTO mercredi (nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax,id_client) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                            PreparedStatement re_requete3bis = co.prepareStatement(requete3bis);
                            re_requete3bis.setInt(8,id_client_concerne);
                            re_requete3bis.setString(1,"Jour de Pause");
                            re_requete3bis.setInt(2,0);
                            re_requete3bis.setInt(3,0);
                            re_requete3bis.setInt(4,0);
                            re_requete3bis.setInt(5,0);
                            re_requete3bis.setInt(6,0);
                            re_requete3bis.setInt(7,0);
                            re_requete3bis.executeUpdate();


                        }


                    }

                }
                if (jour.equals("jeudi")) {
                    String requete1 = "SELECT id_client FROM client_selection";
                    Statement statement = co.createStatement();
                    ResultSet transfert = statement.executeQuery(requete1);
                    if (transfert.next()) {
                        int id_client_concerne = transfert.getInt("id_client");

                        String requete3 = "UPDATE jeudi SET nom_exercice = ?,repetitions = ?,series = ?,tempsDeRepos = ?,SeriesEchauffement = ?,degreDEchec = ?,percentRepMax= ? WHERE  id_client = ? ";
                        // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                        PreparedStatement UpdateStatement = co.prepareStatement(requete3);
                        UpdateStatement.setInt(8,id_client_concerne);
                        UpdateStatement.setString(1,"Jour de Pause");
                        UpdateStatement.setInt(2,0);
                        UpdateStatement.setInt(3,0);
                        UpdateStatement.setInt(4,0);
                        UpdateStatement.setInt(5,0);
                        UpdateStatement.setInt(6,0);
                        UpdateStatement.setInt(7,0);
                        int lignes_touches_jeudi = UpdateStatement.executeUpdate();
                        if (lignes_touches_jeudi>0){
                            System.out.println("nom de lignes dans lundi changées : "+lignes_touches_jeudi);
                        }else{
                            System.out.println("aucune ligne existante, insertion activée");
                            String requete3bis = "INSERT INTO jeudi (nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax,id_client) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                            PreparedStatement re_requete3bis = co.prepareStatement(requete3bis);
                            re_requete3bis.setInt(8,id_client_concerne);
                            re_requete3bis.setString(1,"Jour de Pause");
                            re_requete3bis.setInt(2,0);
                            re_requete3bis.setInt(3,0);
                            re_requete3bis.setInt(4,0);
                            re_requete3bis.setInt(5,0);
                            re_requete3bis.setInt(6,0);
                            re_requete3bis.setInt(7,0);
                            re_requete3bis.executeUpdate();


                        }


                    }

                }
                if (jour.equals("vendredi")) {
                    String requete1 = "SELECT id_client FROM client_selection";
                    Statement statement = co.createStatement();
                    ResultSet transfert = statement.executeQuery(requete1);
                    if (transfert.next()) {
                        int id_client_concerne = transfert.getInt("id_client");

                        String requete3 = "UPDATE vendredi SET nom_exercice = ?,repetitions = ?,series = ?,tempsDeRepos = ?,SeriesEchauffement = ?,degreDEchec = ?,percentRepMax= ? WHERE  id_client = ? ";
                        // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                        PreparedStatement UpdateStatement = co.prepareStatement(requete3);
                        UpdateStatement.setInt(8,id_client_concerne);
                        UpdateStatement.setString(1,"Jour de Pause");
                        UpdateStatement.setInt(2,0);
                        UpdateStatement.setInt(3,0);
                        UpdateStatement.setInt(4,0);
                        UpdateStatement.setInt(5,0);
                        UpdateStatement.setInt(6,0);
                        UpdateStatement.setInt(7,0);
                        int lignes_touches_vendredi = UpdateStatement.executeUpdate();
                        if (lignes_touches_vendredi>0){
                            System.out.println("nom de lignes dans lundi changées : "+lignes_touches_vendredi);
                        }else{
                            System.out.println("aucune ligne existante, insertion activée");
                            String requete3bis = "INSERT INTO vendredi (nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax,id_client) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                            PreparedStatement re_requete3bis = co.prepareStatement(requete3bis);
                            re_requete3bis.setInt(8,id_client_concerne);
                            re_requete3bis.setString(1,"Jour de Pause");
                            re_requete3bis.setInt(2,0);
                            re_requete3bis.setInt(3,0);
                            re_requete3bis.setInt(4,0);
                            re_requete3bis.setInt(5,0);
                            re_requete3bis.setInt(6,0);
                            re_requete3bis.setInt(7,0);
                            re_requete3bis.executeUpdate();


                        }


                    }

                }
                if (jour.equals("samedi")) {
                    String requete1 = "SELECT id_client FROM client_selection";
                    Statement statement = co.createStatement();
                    ResultSet transfert = statement.executeQuery(requete1);
                    if (transfert.next()) {
                        int id_client_concerne = transfert.getInt("id_client");

                        String requete3 = "UPDATE samedi SET nom_exercice = ?,repetitions = ?,series = ?,tempsDeRepos = ?,SeriesEchauffement = ?,degreDEchec = ?,percentRepMax= ? WHERE  id_client = ? ";
                        // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                        PreparedStatement UpdateStatement = co.prepareStatement(requete3);
                        UpdateStatement.setInt(8,id_client_concerne);
                        UpdateStatement.setString(1,"Jour de Pause");
                        UpdateStatement.setInt(2,0);
                        UpdateStatement.setInt(3,0);
                        UpdateStatement.setInt(4,0);
                        UpdateStatement.setInt(5,0);
                        UpdateStatement.setInt(6,0);
                        UpdateStatement.setInt(7,0);
                        int lignes_touches_samedi = UpdateStatement.executeUpdate();
                        if (lignes_touches_samedi>0){
                            System.out.println("nom de lignes dans lundi changées : "+lignes_touches_samedi);
                        }else{
                            System.out.println("aucune ligne existante, insertion activée");
                            String requete3bis = "INSERT INTO samedi (nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax,id_client) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                            PreparedStatement re_requete3bis = co.prepareStatement(requete3bis);
                            re_requete3bis.setInt(8,id_client_concerne);
                            re_requete3bis.setString(1,"Jour de Pause");
                            re_requete3bis.setInt(2,0);
                            re_requete3bis.setInt(3,0);
                            re_requete3bis.setInt(4,0);
                            re_requete3bis.setInt(5,0);
                            re_requete3bis.setInt(6,0);
                            re_requete3bis.setInt(7,0);
                            re_requete3bis.executeUpdate();


                        }


                    }

                }
                if (jour.equals("dimanche")) {
                    String requete1 = "SELECT id_client FROM client_selection";
                    Statement statement = co.createStatement();
                    ResultSet transfert = statement.executeQuery(requete1);
                    if (transfert.next()) {
                        int id_client_concerne = transfert.getInt("id_client");

                        String requete3 = "UPDATE dimanche SET nom_exercice = ?,repetitions = ?,series = ?,tempsDeRepos = ?,SeriesEchauffement = ?,degreDEchec = ?,percentRepMax= ? WHERE  id_client = ? ";
                        // Préparation de la requête d'insertion pour exécuter pour chaque ligne du résultat
                        PreparedStatement UpdateStatement = co.prepareStatement(requete3);
                        UpdateStatement.setInt(8,id_client_concerne);
                        UpdateStatement.setString(1,"Jour de Pause");
                        UpdateStatement.setInt(2,0);
                        UpdateStatement.setInt(3,0);
                        UpdateStatement.setInt(4,0);
                        UpdateStatement.setInt(5,0);
                        UpdateStatement.setInt(6,0);
                        UpdateStatement.setInt(7,0);
                        int lignes_touches_dimanche = UpdateStatement.executeUpdate();
                        if (lignes_touches_dimanche>0){
                            System.out.println("nom de lignes dans lundi changées : "+lignes_touches_dimanche);
                        }else{
                            System.out.println("aucune ligne existante, insertion activée");
                            String requete3bis = "INSERT INTO dimanche (nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax,id_client) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                            PreparedStatement re_requete3bis = co.prepareStatement(requete3bis);
                            re_requete3bis.setInt(8,id_client_concerne);
                            re_requete3bis.setString(1,"Jour de Pause");
                            re_requete3bis.setInt(2,0);
                            re_requete3bis.setInt(3,0);
                            re_requete3bis.setInt(4,0);
                            re_requete3bis.setInt(5,0);
                            re_requete3bis.setInt(6,0);
                            re_requete3bis.setInt(7,0);
                            re_requete3bis.executeUpdate();


                        }


                    }

                }
           }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void vider_table_programme() {
        Bddco bdd = new Bddco();
        try (Connection supprimer_table_programme = bdd.getConnection()) {
            int client_Numero;
            String client = "SELECT id_client FROM client_selection";
            Statement s = supprimer_table_programme.createStatement();
            ResultSet rs = s.executeQuery(client);
            if (rs.next()) {
                client_Numero = rs.getInt("id_client");
                String suppression_table_programme = "DELETE FROM programme WHERE id_client = ? ";
                PreparedStatement psp = supprimer_table_programme.prepareStatement(suppression_table_programme);
                psp.setInt(1, client_Numero);
                int lignes_touches = psp.executeUpdate();
                System.out.println(lignes_touches);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Send_mail(String mdp) {
        Bddco bdd = new Bddco();
        try (Connection co = bdd.getConnection()) {
            // Initialisation des variables pour les emails
            String email_destinataire = null;
            String email_expediteur = null;

            // Récupération de l'email du destinataire
            String recup_mail = "SELECT email FROM client_selection";
            try (Statement statement = co.createStatement(); ResultSet resultat = statement.executeQuery(recup_mail)) {
                if (resultat.next()) {
                    email_destinataire = resultat.getString("email");
                    System.out.println("Envoi de l'email à :" + email_destinataire);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Récupération de l'email de l'expéditeur
            String recup_mail2 = "SELECT email FROM coach_actuel";
            try (Statement statement2 = co.createStatement(); ResultSet resultat2 = statement2.executeQuery(recup_mail2)) {
                if (resultat2.next()) {
                    email_expediteur = resultat2.getString("email"); // Correction ici, utilisez resultat2 au lieu de resultat
                    System.out.println("Email de l'expéditeur :" + email_expediteur);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Vérifie si les emails du destinataire et de l'expéditeur ont bien été récupérés avant d'envoyer l'email
            if (email_destinataire != null && email_expediteur != null) {
                 sendEmail(email_destinataire, email_expediteur, mdp); // Assurez-vous que la méthode sendEmail est correctement définie ailleurs

            } else {
                System.out.println("Impossible d'envoyer l'email, adresse manquante.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendEmail(String emailDestinataire, String email_Expediteur, String mdp) {
        final String username = email_Expediteur; // Remplacez par votre adresse Gmail
        final String password = mdp; // Remplacez par votre mot de passe d'application
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");  //connection au serveur SMTP
        props.put("mail.smtp.starttls.enable", "true"); //passage sur un canal dit sécurisé STARTTLS
        props.put("mail.smtp.host", "smtp.gmail.com"); //Adresse du serveur SMTP
        props.put("mail.smtp.port", "587"); // Port à utiliser pour STARTTLS, un autre 465 est utilisable également

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinataire));
            message.setSubject("programme sur 1 semaine"); // Personnalisez votre sujet


            Bddco bdd = new Bddco();
            StringBuilder contenu = new StringBuilder();
            try (Connection co = bdd.getConnection()) {

                IMC Imc_obtain1 = new IMC();                                 // IMC
                double IMC = Imc_obtain1.Affichage_IMC();

               BMR_ Imc_obtain2 = new BMR_();                                 // BMR
               double BMR = Imc_obtain2.Affichage_BMR();

                NEAT Imc_obtain3 = new NEAT();                                 // NEAT
                double NEAT = Imc_obtain3.Affichage_NEAT();

                type_de_programme nom_type_programe = new type_de_programme(); // type de programme
                double selectedValue = Type_de_programme.getValue();
                String type_de_progra = nom_type_programe.min_max_mid(selectedValue);


                prenom_nom_email Fulll_name = new prenom_nom_email(); //prenom et nom
                String Full_name = Fulll_name.prenom_nom_classe();

                contenu = new StringBuilder();
                String programme_semaine = "SELECT *, 'Lundi' AS jour FROM lundi\n" +
                        "UNION ALL\n" +
                        "SELECT *, 'Mardi' AS jour FROM mardi\n" +
                        "UNION ALL\n" +
                        "SELECT *, 'Mercredi' AS jour FROM mercredi\n" +
                        "UNION ALL\n" +
                        "SELECT *, 'Jeudi' AS jour FROM jeudi\n" +
                        "UNION ALL\n" +
                        "SELECT *, 'Vendredi' AS jour FROM vendredi\n" +
                        "UNION ALL\n" +
                        "SELECT *, 'Samedi' AS jour FROM samedi\n" +
                        "UNION ALL\n" +
                        "SELECT *, 'Dimanche' AS jour FROM dimanche ";
                Statement statement = co.createStatement();
                ResultSet rs = statement.executeQuery(programme_semaine);
                contenu.append("Bonjour " + Full_name +"," +"\n\n" +"Voice votre programme de type "+ type_de_progra
                        +" sur une semaine, adapté à votre morphologie votre taille ainsi que votre expérience :"+"\n\n");
                while (rs.next()){
                    if ("Lundi".equals(rs.getString("jour"))){
                        contenu.append("Lundi : ")
                                .append(rs.getString("nom_exercice")).append(" REP :").append(rs.getString("repetitions"))
                                .append(" S :").append(rs.getString("series")).append(" R :").append(rs.getString("tempsDeRepos")).append(" SE :")
                                .append(rs.getString("SeriesEchauffement")).append(" PR : ").append(rs.getString("percentRepMax")).append("\n");

                    }
                    if ("Mardi".equals(rs.getString("jour"))){
                        contenu.append("Mardi : ")
                                .append(rs.getString("nom_exercice")).append(" REP :").append(rs.getString("repetitions"))
                                .append(" S :").append(rs.getString("series")).append(" R :").append(rs.getString("tempsDeRepos")).append(" SE :")
                                .append(rs.getString("SeriesEchauffement")).append(" PR : ").append(rs.getString("percentRepMax")).append("\n");

                    }
                    if ("Mercredi".equals(rs.getString("jour"))){
                        contenu.append("Mercredi : ")
                                .append(rs.getString("nom_exercice")).append(" REP :").append(rs.getString("repetitions"))
                                .append(" S :").append(rs.getString("series")).append(" R :").append(rs.getString("tempsDeRepos")).append(" SE :")
                                .append(rs.getString("SeriesEchauffement")).append(" PR : ").append(rs.getString("percentRepMax")).append("\n");

                    }
                    if ("Jeudi".equals(rs.getString("jour"))){
                        contenu.append("Jeudi : ")
                                .append(rs.getString("nom_exercice")).append(" REP :").append(rs.getString("repetitions"))
                                .append(" S :").append(rs.getString("series")).append(" R :").append(rs.getString("tempsDeRepos")).append(" SE :")
                                .append(rs.getString("SeriesEchauffement")).append(" PR : ").append(rs.getString("percentRepMax")).append("\n");

                    }
                    if ("Vendredi".equals(rs.getString("jour"))){
                        contenu.append("Vendredi : ")
                                .append(rs.getString("nom_exercice")).append(" REP :").append(rs.getString("repetitions"))
                                .append(" S :").append(rs.getString("series")).append(" R :").append(rs.getString("tempsDeRepos")).append(" SE :")
                                .append(rs.getString("SeriesEchauffement")).append(" PR : ").append(rs.getString("percentRepMax")).append("\n");

                    }
                    if ("Samedi".equals(rs.getString("jour"))){
                        contenu.append("Samedi : ")
                                .append(rs.getString("nom_exercice")).append(" REP :").append(rs.getString("repetitions"))
                                .append(" S :").append(rs.getString("series")).append(" R :").append(rs.getString("tempsDeRepos")).append(" SE :")
                                .append(rs.getString("SeriesEchauffement")).append(" PR : ").append(rs.getString("percentRepMax")).append("\n");

                    }
                    if ("Dimanche".equals(rs.getString("jour"))){
                        contenu.append("Dimanche : ")
                                .append(rs.getString("nom_exercice")).append(" REP :").append(rs.getString("repetitions"))
                                .append(" S :").append(rs.getString("series")).append(" R :").append(rs.getString("tempsDeRepos")).append(" SE :")
                                .append(rs.getString("SeriesEchauffement")).append(" PR : ").append(rs.getString("percentRepMax")).append("\n");


                   }

                }
                contenu.append("\n\n").append("REP = Répétitions, S = Séries, R = Temps de repos (minutes), PR = difficulté par rapport à la répétition maximale."+"\n\n");
                contenu.append("De plus, il est nécessaire de comprendre qu'il faut un échauffement adapté à votre programme avant chaque entraînement. "+"\n\n");
                ///ajouter la partie échauffement pour le client ici
                contenu.append("Nous avons quelques petites informations complémentaires à vous donner dont votre imc, votre dépense calorique en fonction de votre objectif et de vos objectifs : \n ");
                ///ajouter les variables correspondant aux petites indications bonus que je peux donner ici
                contenu.append("IMC(indice de masse corporel) : "+IMC +", BMR(taux métabolique basal) : "+BMR +", NEAT(thermogenèse de l'activité indépendante du spor) : "+NEAT+" " );

            } catch (SQLException e) {
                e.printStackTrace();
            }
            String contenuEmail = contenu.toString();

// Taille en nombre de caractères
            int tailleCaracteres = contenuEmail.length();

// Taille en octets en UTF-8
            int tailleOctets = contenuEmail.getBytes(StandardCharsets.UTF_8).length;

            System.out.println("Taille du contenu en caractères: " + tailleCaracteres);
            System.out.println("Taille du contenu en octets (UTF-8): " + tailleOctets);

            message.setText(contenu.toString()); // Personnalisez le corps de l'email

            Transport.send(message);

            System.out.println("Email envoyé avec succès à " + emailDestinataire);
            vidage_jours_demaines();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private void vidage_jours_demaines(){
        Bddco bdd = new Bddco();
        try(Connection co = bdd.getConnection()) {
                for (int i = 0; i < 7; i++) {
                    String day = switch (i) {
                        case 0 -> "lundi";
                        case 1 -> "mardi";
                        case 2 -> "mercredi";
                        case 3 -> "jeudi";
                        case 4 -> "vendredi";
                        case 5 -> "samedi";
                        case 6 -> "dimanche";
                        default -> ""; // Initialiser la variable jour
                    };
                    String queryDay = "TRUNCATE TABLE " + day + ";";
                    Statement psDay = co.createStatement();
                    psDay.executeUpdate(queryDay);
                }
            }

        catch(SQLException e){
            e.printStackTrace();
        }

    }



}




