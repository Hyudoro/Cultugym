package com.example.application.controleur;

import com.example.application.GestionImages.Gestion_chemins_Images;
import com.example.application.connexionbdd.Bddco;

import com.example.application.controleur.calendrier.Control_Interface_coach_calendrie;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Control_Interface {
    @FXML
    private TextField client_id; // Pour l'ID du client

    // Déclaration des labels pour les informations du client
    @FXML
    private Label niveau_activite,prenom_id, nom_utilisateur_id,agee, email_id, code_postal_id, adresse_id, nom_id, sexe_id, taille_id, poids_id, corpulence_id, experience_id, description_id,nombre_client_total;

    @FXML
    private Spinner<Integer> spinner1, spinner2, spinner3, spinner4, spinner6;
    @FXML
    private Spinner<Double> spinner5;
    @FXML
    private ComboBox<String> comboboxid;


    @FXML
    private Button fenetre_ajout_client,Ajout,suppression,lancement_fenetre_affichage_client;
    String cheminlogo= Gestion_chemins_Images.Gestion_chemins_Images_logo;
    String cheminClientFace = Gestion_chemins_Images.Gestion_chemins_Images_Face;
    String cheminClientProfilDroit = Gestion_chemins_Images.Gestion_chemins_Images_Droite;
    String cheminClientProfilGauche = Gestion_chemins_Images.Gestion_chemins_Images_gauche;
    String cheminClientDerriere = Gestion_chemins_Images.Gestion_chemins_Images_Derriere;

    @FXML
    private ImageView photo_face, photo_derriere, photo_droite, photo_gauche,logo;

    private Integer repetitions;
    private Integer series;
    private Double tempsDeRepos;
    private Integer seriesEchauffement;
    private Integer degreDEchec;
    private Integer percentRepMax;

    @FXML
    public void initialize() {
        fenetre_ajout_client.setOnAction(event -> lancement_fenetre_creation_client());
        Image image_logo = new Image("file:" + cheminlogo + "logo.png");
        logo.setImage(image_logo);

        lancement_fenetre_affichage_client.setOnAction(event -> lancement_fenetre_affichage_clientt());


        nombre_client();
        suppression.setOnAction(event -> suppression_client());
        liste_exos();

        client_id.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                chargementDesDonneesClient();
            }
        });
        SpinnerValueFactory<Integer> repetitionsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 31, 0, 1);
        SpinnerValueFactory<Integer> seriesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9, 0, 1);
        SpinnerValueFactory<Double> tempsDeReposValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 16, 0, 0.5);
        SpinnerValueFactory<Integer> seriesEchauffementValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 11, 0, 1);
        SpinnerValueFactory<Integer> degreDEchecValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 11, 0, 1);
        SpinnerValueFactory<Integer> percentRepMaxValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 101, 0, 1);

        spinner1.setValueFactory(seriesEchauffementValueFactory);
        spinner2.setValueFactory(seriesValueFactory);
        spinner3.setValueFactory(repetitionsValueFactory);
        spinner4.setValueFactory(percentRepMaxValueFactory);
        spinner5.setValueFactory(tempsDeReposValueFactory); // seul valeur float (double)
        spinner6.setValueFactory(degreDEchecValueFactory);

        repetitions = repetitionsValueFactory.getValue();
        series = seriesValueFactory.getValue();
        tempsDeRepos = tempsDeReposValueFactory.getValue();
        seriesEchauffement = seriesEchauffementValueFactory.getValue();
        degreDEchec = degreDEchecValueFactory.getValue();
        percentRepMax = percentRepMaxValueFactory.getValue();

        Ajout.setOnAction(event -> Programmation());
    }

    // Ajouter une méthode pour charger les données du client lorsque l'ID est saisi
    @FXML
    public void chargementDesDonneesClient() {
        if (!client_id.getText().isEmpty()) {
            int clientId = Integer.parseInt(client_id.getText());
            Bddco bdd = new Bddco();
            try (Connection connection = bdd.getConnection()) {


                String suppression_client_selection = "truncate client_selection";
                Statement ss = connection.prepareStatement(suppression_client_selection);
                {
                    int suppression_colonnes = ss.executeUpdate(suppression_client_selection);
                    System.out.println(suppression_colonnes);
                }
                String query2 = "SELECT nom, Prenom, email, nom_utilisateur, taille, poids, adresse, code_postal, description, Experience, sexe, corpulence, age, niveau_activite FROM client WHERE id = ? ";
                PreparedStatement pss = connection.prepareStatement(query2);
                pss.setInt(1,clientId);
                ResultSet rss = pss.executeQuery();
                if (rss.next()){
                    String query3 = "INSERT INTO client_selection(id_client, nom, Prenom, email, nom_utilisateur, taille, poids, adresse, code_postal, description, Experience, sexe, corpulence,age,niveau_activite) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
                    PreparedStatement insertion = connection.prepareStatement(query3);
                    insertion.setInt(1,clientId);
                    insertion.setString(2, rss.getString("nom"));
                    insertion.setString(3, rss.getString("Prenom"));
                    insertion.setString(4, rss.getString("email"));
                    insertion.setString(5, rss.getString("nom_utilisateur"));
                    insertion.setInt(6, rss.getInt("taille"));
                    insertion.setInt(7, rss.getInt("poids"));
                    insertion.setString(8, rss.getString("adresse"));
                    insertion.setInt(9, rss.getInt("code_postal"));
                    insertion.setString(10, rss.getString("description"));
                    insertion.setString(11, rss.getString("Experience"));
                    insertion.setString(12, rss.getString("sexe"));
                    insertion.setString(13, rss.getString("corpulence"));
                    insertion.setString(14, rss.getString("age"));
                    insertion.setString(15, rss.getString("niveau_activite"));
                    insertion.executeUpdate();
                } else{System.out.println("insertion vers client_selection ratée");}



                // Récupération et affichage des données du client spécifié par 'clientId'
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
                    agee.setText(rs.getString("age"));
                    niveau_activite.setText(rs.getString("niveau_activite"));

                    // Récupération des photos associées au client
                    String query4 = "SELECT nom_Photo_Face, nom_Photo_Derriere, nom_Photo_Droite, nom_Photo_Gauche FROM client WHERE id = ?";
                    PreparedStatement ps2 = connection.prepareStatement(query4);
                    ps2.setInt(1, clientId);
                    ResultSet rs2 = ps2.executeQuery();

                    if (rs2.next()) {
                        // Affichage des images
                        Image imageFace = new Image("file:" + cheminClientFace + rs2.getString("nom_Photo_Face"));
                        Image imageDerriere = new Image("file:" + cheminClientDerriere + rs2.getString("nom_Photo_Derriere"));
                        Image imageDroite = new Image("file:" + cheminClientProfilDroit + rs2.getString("nom_Photo_Droite"));
                        Image imageGauche = new Image("file:" + cheminClientProfilGauche + rs2.getString("nom_Photo_Gauche"));

                        photo_face.setImage(imageFace);
                        photo_derriere.setImage(imageDerriere);
                        photo_droite.setImage(imageDroite);
                        photo_gauche.setImage(imageGauche);
                    }
                } else {
                    System.out.println("Client non trouvé pour l'ID: " + clientId);
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
    }


    private void Programmation() {
        int clientId = Integer.parseInt(client_id.getText());
        Bddco bdd2 = new Bddco();
        try (Connection connection2 = bdd2.getConnection()) {
            // Préparation de la requête SQL avec des paramètres
            if (repetitions != null && series != null && tempsDeRepos != null && seriesEchauffement != null && degreDEchec != null && percentRepMax != null && comboboxid.getValue() != null) {
                String query = "INSERT INTO Programme (nom_exercice, repetitions, series, tempsDeRepos, SeriesEchauffement, degreDEchec, percentRepMax, id_client) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps1 = connection2.prepareStatement(query);
                String nomExercice = comboboxid.getValue() != null ? comboboxid.getValue() : null;
                ps1.setString(1, nomExercice);
                ps1.setInt(2, spinner3.getValue()); // repetitions
                ps1.setInt(3, spinner2.getValue()); // series
                ps1.setDouble(4, spinner5.getValue()); // tempsDeRepos
                ps1.setInt(5, spinner1.getValue()); // SeriesEchauffement
                ps1.setInt(6, spinner6.getValue()); // degreDEchec
                ps1.setInt(7, spinner4.getValue()); // percentRepMax
                ps1.setInt(8, clientId);
                int affectedRows = ps1.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Insertion réussie.");
                } else {
                    System.out.println("Aucune ligne insérée.");
                }
            }

            // Requête de mise à jour de la connexion
            String requete2 =
                    "UPDATE programme " +
                            "SET connexion = TRUE " +
                            "WHERE nom_exercice IS NULL" +
                            "    AND repetitions = 0 " +
                            "    AND tempsDeRepos = 0 " +
                            "    AND SeriesEchauffement = 0 " +
                            "    AND degreDEchec = 0 " +
                            "    AND percentRepMax = 0";

            PreparedStatement ps2 = connection2.prepareStatement(requete2);

            // Exécution de la requête de mise à jour de la connexion
            int affectedRows2 = ps2.executeUpdate();
            if (affectedRows2 > 0) {
                System.out.println("Mise à jour réussie.");
            } else {
                System.out.println("Aucune ligne mise à jour.");
            }

            if (comboboxid.getValue() == null && spinner1.getValue() == 0 && spinner2.getValue() == 0 && spinner3.getValue() == 0 && spinner4.getValue() == 0 && spinner5.getValue() == 0 && spinner6.getValue() == 0) {
                System.out.println("Tentative de connexion.");
            } else {
                System.out.println("Des données ont été entrées dans la base de données programmation sauf si vous n'avez pas mentionné l'exercice.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Ouverture_deuxieme_fenetre() {
        try {
            // Charger le FXML pour la deuxième fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface_coach_calendrie.fxml"));
            Parent interfaceCoachCalendrie = loader.load();
            Control_Interface_coach_calendrie controller = loader.getController();
            controller.initialisation();
            controller.Combobox_calendrier();


            Scene secondaryScene = new Scene(interfaceCoachCalendrie);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Cultugym [emploi du temps]");
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void nombre_client(){
        Bddco bdd = new Bddco();
        try (Connection connection = bdd.getConnection()) {
            String affichage_nombre_client = "SELECT count(*) AS compteur FROM client";
            PreparedStatement requete = connection.prepareStatement(affichage_nombre_client);
            ResultSet rs = requete.executeQuery();
            if (rs.next()){
                nombre_client_total.setText(rs.getString("compteur"));
            }else{System.out.println("aucun client");}
    }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                bdd.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void suppression_client() {
        if (!client_id.getText().isEmpty()) {
            int clientId = Integer.parseInt(client_id.getText());
            Bddco bdd = new Bddco();
            try (Connection connection = bdd.getConnection()) {
                String image_face;
                String image_derriere;
                String image_profil_droit;
                String image_profil_gauche;
                String query0 = "SELECT nom_Photo_Face,nom_Photo_Derriere,nom_Photo_Droite,nom_Photo_Gauche FROM client WHERE client.id = ?"; // on va supprimer les photos ici
                PreparedStatement ps0 = connection.prepareStatement(query0);
                ps0.setInt(1,clientId);
                ResultSet rs0 = ps0.executeQuery();
                if(rs0.next()){
                    image_face = rs0.getString("nom_Photo_Face");
                    image_derriere = rs0.getString("nom_Photo_Derriere");
                    image_profil_droit = rs0.getString("nom_Photo_Droite");
                    image_profil_gauche = rs0.getString("nom_Photo_Gauche");
                    String chemin_IMG_FACE = "Images/Images_Client/Face/" +  image_face;
                    String chemin_IMG_BACK= "Images/Images_Client/Derriere/" + image_derriere;
                    String chemin_IMG_RIGHT = "Images/Images_Client/Profil_Droit/" + image_profil_droit;
                    String chemin_IMG_LEFT = "Images/Images_Client/Profil_Gauche/" + image_profil_gauche;
                    File FichierImage_Face = new File(chemin_IMG_FACE);
                    File FichierImage_Back = new File(chemin_IMG_BACK);
                    File FichierImage_Right = new File(chemin_IMG_RIGHT);
                    File FichierImage_Left = new File(chemin_IMG_LEFT);
                    if (FichierImage_Face.exists() &&  FichierImage_Face.delete()) {
                        System.out.println("Image FACE supprimée avec succès.");
                    } else {
                        System.out.println("Échec de la suppression de l'image FACE. Le fichier n'existe peut-être pas ou un problème de permission.");
                    }

                    if (FichierImage_Back.exists() &&  FichierImage_Back.delete()) {
                        System.out.println("Image BACK supprimée avec succès.");
                    } else {
                        System.out.println("Échec de la suppression de l'image BACK. Le fichier n'existe peut-être pas ou un problème de permission.");
                    }

                    if (FichierImage_Right.exists() &&  FichierImage_Right.delete()) {
                        System.out.println("Image RIGHT supprimée avec succès.");
                    } else {
                        System.out.println("Échec de la suppression de l'image RIGHT. Le fichier n'existe peut-être pas ou un problème de permission.");
                    }

                    if (FichierImage_Left.exists() && FichierImage_Left.delete()) {
                        System.out.println("Image LEFT supprimée avec succès.");
                    } else {
                        System.out.println("Échec de la suppression de l'image LEFT. Le fichier n'existe peut-être pas ou un problème de permission.");
                    }
                }
                String query = "DELETE FROM client WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, clientId);
                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    String query2 = "DELETE FROM programme WHERE id_client = ?";
                    PreparedStatement ps2 = connection.prepareStatement(query2);
                    ps2.setInt(1, clientId);
                    int lignes_supp = ps2.executeUpdate();
                    if (lignes_supp > 0) {
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
                            String queryDay = "DELETE FROM " + day + " WHERE id_client = ?";
                            PreparedStatement psDay = connection.prepareStatement(queryDay);
                            psDay.setInt(1, clientId);
                            int lignes_supp_jour = psDay.executeUpdate();
                            if (lignes_supp_jour > 0) {
                                System.out.println("Les lignes de " + day + " correspondant à " + clientId + " ont bien été supprimées.");
                            }
                        }
                        System.out.println("Suppression dans table programme réussie.");
                    } else {
                        System.out.println("Aucune ligne supprimée.");
                    }
                    System.out.println("Client supprimé avec succès.");
                } else {
                    System.out.println("Suppression impossible, ID client non trouvée.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void liste_exos(){
        Bddco bdd = new Bddco();
        try(Connection co = bdd.getConnection()){
            String exos = "SELECT nom FROM liste_exercice";
            Statement s = co.createStatement();
            ResultSet rs = s.executeQuery(exos);
            while(rs.next()){
                String nom_exercice = rs.getString("nom");
                comboboxid.getItems().add(nom_exercice);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void lancement_fenetre_creation_client(){
        try {
            // Charger le FXML pour la deuxième fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface_Client_Creation.fxml"));
            Parent interfaceprogresclient= loader.load();
            Control_Interface_Creation_Client controller = loader.getController();
            controller.initialisation();
            Scene secondaryScene = new Scene(interfaceprogresclient);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Cultugym [Creation client]");
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void lancement_fenetre_affichage_clientt(){
        try {
            // Charger le FXML pour la deuxième fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface_clients_affichage.fxml"));
            Parent interfaceprogresclient= loader.load();
            Control_interface_affichage_client controller = loader.getController();
            controller.initialisation();
            Scene secondaryScene = new Scene(interfaceprogresclient);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Cultugym [Affichage client]");
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
