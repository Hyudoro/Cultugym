package com.example.application.identification;
import com.example.application.connexionbdd.Bddco;
import com.example.application.GestionImages.Gestion_chemins_Images;
import com.example.application.controleur.Control_Interface;
import com.mysql.cj.jdbc.result.UpdatableResultSet;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.fxml.FXML;
import org.mindrot.jbcrypt.BCrypt;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;

public class Control_page_identifiant {
    @FXML
    private ImageView logo;

    @FXML
    private TextField nom_utilisateur,mot_de_passe;
    @FXML
    private Label emoji2,emoji1,message_erreur,msg1,msg2;
    @FXML
    private Hyperlink inscription,desinscription;

    @FXML
    private Button validation;


    String cheminlogo= Gestion_chemins_Images.Gestion_chemins_Images_logo;

    @FXML
    public void initialisation(){

        validation.setOnAction(event -> connection(nom_utilisateur.getText(),mot_de_passe.getText()));
        Image image_logo = new Image("file:" + cheminlogo + "logo.png");
        logo.setImage(image_logo);
        inscription.setOnMouseClicked(event -> lancement_fenetre_inscription());
        desinscription.setOnMouseClicked(event -> lancement_fenetre_desinscription());



    }


    public void connection(String nom_utili, String mdp) {
        byte[] code_emoji_erreur = new byte[]{(byte)0xE2, (byte)0x9D,(byte)0x97};         //transferer un emoji avec son format utf-8 Bytes.
        byte[] code_emoji_valid = new byte[]{(byte)0xE2, (byte)0x9C,(byte)0x85};
        String emoji_erreurr =  new String(code_emoji_erreur, StandardCharsets.UTF_8);
        String emoji_validd =  new String(code_emoji_valid, StandardCharsets.UTF_8);
        Bddco bdd = new Bddco();
        System.out.println("mdp = " + mdp);
        System.out.println("nom utilisateur = " + nom_utili);
        try (Connection co = bdd.getConnection()) {
            String sql = "SELECT mot_de_passe FROM coach WHERE nom_utilisateur = ?";
            PreparedStatement ps = co.prepareStatement(sql);
            ps.setString(1, nom_utili);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("mot_de_passe");
                System.out.println("hashedPassword = " + hashedPassword);
                if (BCrypt.checkpw(mdp, hashedPassword)) {
                    System.out.println("valide");
                    lancement_fenetre_client();
                    emoji2.setText(emoji_validd);
                    emoji1.setText(emoji_validd);

                    String insert_coach_actuel = "SELECT * FROM coach WHERE nom_utilisateur = ?";  // insertion dans la table coach_actuel pour savoir qui est le coach connecté et pouvoir reutiliser ces données
                    PreparedStatement pspp =  co.prepareStatement(insert_coach_actuel);            // pour le moment ou il faut envoyer un mail notament pour l'envoie du programme à x client.
                    pspp.setString(1,nom_utili);
                    ResultSet rsss = pspp.executeQuery();
                    if(rsss.next()){
                        String suppression_coach_avant = "truncate coach_actuel";
                        Statement statement = co.createStatement();
                        statement.executeUpdate(suppression_coach_avant);

                        String nom_utilisateur_coach_actuel = rsss.getString("nom_utilisateur");
                        String mdp_coach_actuel = rsss.getString("mot_de_passe");
                        String email = rsss.getString("email");
                        String id = rsss.getString("id");



                        String sql2 = "INSERT INTO coach_actuel(nom_utilisateur,mot_de_passe,email,id_coach)" +
                                "VALUES" +
                                "(?,?,?,?)";
                        PreparedStatement insertion_donnes_coach_actuel = co.prepareStatement(sql2);
                        insertion_donnes_coach_actuel.setString(1,nom_utilisateur_coach_actuel);
                        insertion_donnes_coach_actuel.setString(2,mdp_coach_actuel);
                        insertion_donnes_coach_actuel.setString(3,email);
                        insertion_donnes_coach_actuel.setString(4,id);
                        insertion_donnes_coach_actuel.executeUpdate();
                    }

                } else {
                    message_erreur.setText("votre mot de passe est incorrecte");
                    emoji2.setText(emoji_erreurr);
                }
            } else {
                emoji1.setText(emoji_erreurr);
                emoji2.setText(emoji_erreurr);
                msg1.setText("non reconnu");
                msg2.setText("non reconnu");
                message_erreur.setText("Vos identifiants ne sont pas enregistrés, veuillez réessayer ou créer votre compte.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lancement_fenetre_client(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface.fxml"));
            Parent Interfaceinterface = loader.load();
            Control_Interface controller = loader.getController();
            controller.initialize();

            Scene secondaryScene = new Scene(Interfaceinterface);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Cultugym [client_informations]");
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void lancement_fenetre_inscription() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface_identifiant_inscription.fxml"));
            Parent Interfaceidentifiantinscription = loader.load();
            Control_page_identifiant_inscription controller = loader.getController();
            controller.initialisation3();

            Scene secondaryScene = new Scene(Interfaceidentifiantinscription);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Cultugym [inscription]");
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void lancement_fenetre_desinscription(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface_identifiant_desinscription.fxml"));
            Parent Interfaceidentifiantidentifiant = loader.load();
            Control_page_identifiant_desinscription controller = loader.getController();
            controller.initialisation();

            Scene secondaryScene = new Scene(Interfaceidentifiantidentifiant);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Cultugym [desinscription]");
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




/* critère minimum 8 caractères , 1 special, 1 majuscule , 1 minuscule, 1 chiffre, critère maximum 19 */
