package com.example.application.identification;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.mindrot.jbcrypt.BCrypt;
//importation de la librairie Jbcryp qui est une librairie de hashage de donnée
import com.example.application.GestionImages.Gestion_chemins_Images;
import com.example.application.connexionbdd.Bddco;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Control_page_identifiant_inscription {

    @FXML
    private TextField Nom_utilisateur,Email,Mot_de_passe;
    @FXML
    private Label emoji1,emoji2,emoji3;
    @FXML
    private Hyperlink connection;
    @FXML
    private Text message_erreur1,message_erreur2,message_erreur3;
    @FXML
    private ImageView logo;

    @FXML
    private Button validation;

    String cheminlogo= Gestion_chemins_Images.Gestion_chemins_Images_logo;


    @FXML
    public void initialisation3(){
        connection.setOnMouseClicked(event-> lancement_fenetre_coach());
        validation.setOnAction(event -> identifiants());
        Image image_logo = new Image("file:" + cheminlogo + "logo.png");
        logo.setImage(image_logo);


    }@FXML
    public void identifiants() {
        byte[] code_emoji_erreur = new byte[]{(byte)0xE2, (byte)0x9D,(byte)0x97};         //transferer un emoji avec son format utf-8 Bytes.
        byte[] code_emoji_valid = new byte[]{(byte)0xE2, (byte)0x9C,(byte)0x85};
        String emoji_erreur =  new String(code_emoji_erreur, StandardCharsets.UTF_8);
        String emoji_valid =  new String(code_emoji_valid, StandardCharsets.UTF_8);
        boolean condition1 = false;
        boolean condition2 = false;
        boolean condition3 = false;
        Bddco bdd = new Bddco();
        if (!Mot_de_passe.getText().isEmpty()){ // si c'est vide
            if (Mot_de_passe.getText().length() >=8 ){  //8 lettres minimum
                System.out.println("condition1-8-cara-minimum valide");
                if (Mot_de_passe.getText().matches(".*[^a-zA-Z0-9].*")){  //1 caractère spécial
                    System.out.println("condition2-1-cara_spe-minimum valide");
                    if (Mot_de_passe.getText().matches(".*[a-z].*")) {  //1 caractère minuscule
                        System.out.println("condition3-1-cara_minus-minimum valide");
                        if (Mot_de_passe.getText().matches(".*[A-Z].*")) {  //1 caractère majuscule
                            System.out.println("condition4-1-cara_maj-minimum valide");
                            if (Mot_de_passe.getText().length() <=19) {  //19 caractère min
                                System.out.println("condition5-19-cara-maximum valide");
                                if (Mot_de_passe.getText().matches(".*\\d.*")) {  // 1 caractère numéro minimum
                                    System.out.println("condition6-1-cara_num-minimum valide");
                                    condition1 = true;
                                    emoji3.setText(emoji_valid);
                                    message_erreur3.setText("");
                                } else {
                                    message_erreur3.setText("manque un chiffre");
                                    emoji3.setText(emoji_erreur);
                                }
                            } else {
                                message_erreur3.setText("Mot de passe trop grand");
                                emoji3.setText(emoji_erreur);
                            }
                        } else {
                            message_erreur3.setText("rajoutez une majuscule");
                            emoji3.setText(emoji_erreur);
                        }
                    } else {
                        message_erreur3.setText("rajoutez une minuscule");
                        emoji3.setText(emoji_erreur);
                    }
                } else {
                    message_erreur3.setText("caractère spécial nécessaire");
                    emoji3.setText(emoji_erreur);
                }
            } else {
                message_erreur3.setText("Mot de passe trop petit");
                emoji3.setText(emoji_erreur);
            }
        } else {
            message_erreur3.setText("Mot de passe vide");
            emoji3.setText(emoji_erreur);
        }


        if (Nom_utilisateur.getText().matches("[a-zA-Z]+")) {
            if (Nom_utilisateur.getText().length() <20){
                emoji1.setText(emoji_valid);
                condition2 = true;
                message_erreur1.setText("");
            }else if(Nom_utilisateur.getText().length()>=20){
                emoji1.setText(emoji_erreur);
                message_erreur1.setText("Trop grand");

            }
        } else if (Nom_utilisateur.getText().isEmpty()) {
                 message_erreur1.setText("vous n'avez rien rentré");
                 emoji1.setText(emoji_erreur);
        }else{
            message_erreur1.setText("Doit contenir uniquement des lettres");
            emoji1.setText(emoji_erreur);
        }


        if (Email.getText().contains("@")){
                emoji2.setText(emoji_valid);
                condition3 = true;
                message_erreur2.setText("");
        }
        else if (Email.getText().isEmpty()){
                emoji2.setText(emoji_erreur);
                message_erreur2.setText("vous n'avez rien rentré");
        }
        else if (!Email.getText().contains("@")){
                emoji2.setText(emoji_erreur);
                message_erreur2.setText("Votre email n'est pas reconnue");
        }

        if (condition1 && condition2 && condition3) {
            System.out.println("Toutes les conditions sont remplies.");
            try (Connection co = bdd.getConnection()) {
                // Utilisation d'une requête pour vérifier si le nom d'utilisateur existe déjà
                String verif_nom_utili_bdd = "SELECT COUNT(*) AS compte FROM coach WHERE nom_utilisateur = ?";
                PreparedStatement ps = co.prepareStatement(verif_nom_utili_bdd);
                ps.setString(1, Nom_utilisateur.getText());
                ResultSet rs = ps.executeQuery();

                // Procéder uniquement si le ResultSet renvoie une ligne
                if (rs.next()) {
                    int compte = rs.getInt("compte"); // Obtient le nombre d'occurrences du nom d'utilisateur
                    if (compte == 0) {
                        // Le nom d'utilisateur n'existe pas, il est donc unique
                        System.out.println("Le nom d'utilisateur est unique.");
                        Insertion_identifiants_bdd(Mot_de_passe.getText(), Nom_utilisateur.getText(), Email.getText());
                        lancement_fenetre_coach();
                    } else {
                        // Le nom d'utilisateur existe déjà dans la base de données
                        System.out.println("Ce nom d'utilisateur existe déjà.");
                        message_erreur1.setText("Ce nom d'utilisateur existe déjà");
                        emoji1.setText(emoji_erreur);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la vérification de l'unicité du nom d'utilisateur : ");
                e.printStackTrace();
            }
        } else {
            System.out.println("Une ou plusieurs conditions de validation ne sont pas remplies.");
        }
    }


    public void lancement_fenetre_coach() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface_identifiant.fxml"));
            Parent Interfaceidentifiant = loader.load();
            Control_page_identifiant controller = loader.getController();
            controller.initialisation();

            Scene secondaryScene = new Scene(Interfaceidentifiant);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Cultugym [connexion]");
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Insertion_identifiants_bdd(String mdp, String nom_utilisateur, String email) {
        Bddco bddco = new Bddco();
        String requete;
        String mdp_hash = BCrypt.hashpw(mdp,BCrypt.gensalt(4));
        System.out.println("le mot de passe hashé = "+ mdp_hash);
        try (Connection co = bddco.getConnection()) {
            requete = "INSERT INTO coach(nom_utilisateur, mot_de_passe, email)" + "VALUES (?, ?, ?)";
            PreparedStatement ps = co.prepareStatement(requete);
            ps.setString(1, nom_utilisateur);
            ps.setString(2, mdp_hash);
            ps.setString(3, email);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Insertion-identifiants réussie");
            } else {
                System.out.println("Insertion-identifiants raté");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}