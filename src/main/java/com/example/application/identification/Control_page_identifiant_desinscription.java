package com.example.application.identification;

import com.example.application.GestionImages.Gestion_chemins_Images;
import com.example.application.connexionbdd.Bddco;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Control_page_identifiant_desinscription {
    @FXML
    private ImageView logo;

    @FXML
    private TextField nom_utilisateur,mot_de_passe;
    @FXML
    private Label emoji2,emoji1,message_erreur,msg1,msg2,message_validation;

    @FXML
    private Button validation;


    String cheminlogo= Gestion_chemins_Images.Gestion_chemins_Images_logo;

    public Control_page_identifiant_desinscription() {
    }

    @FXML
    public void initialisation(){

        validation.setOnAction(event -> connection(nom_utilisateur.getText(),mot_de_passe.getText()));
        Image image_logo = new Image("file:" + cheminlogo + "logo.png");
        logo.setImage(image_logo);



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
            emoji1.setText(emoji_validd);
            String sql = "SELECT mot_de_passe FROM coach WHERE nom_utilisateur = ?";
            PreparedStatement ps = co.prepareStatement(sql);
            ps.setString(1, nom_utili);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("mot_de_passe");
                System.out.println("hashedPassword = " + hashedPassword);
                if (BCrypt.checkpw(mdp, hashedPassword)) {
                    System.out.println("valide");
                    suppresion_coach(nom_utili);
                    emoji1.setText(emoji_validd);
                    emoji2.setText(emoji_validd);
                } else {
                    emoji1.setText(emoji_validd);
                    emoji2.setText(emoji_erreurr);
                    msg2.setText("non reconnu");
                    message_erreur.setText("Vos identifiants ne sont pas enregistrés, désinscription échoué :)");


                }

            }else{
                msg1.setText("non reconnu");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void suppresion_coach(String nom_coach){
        Bddco bdd = new Bddco();
        try (Connection co = bdd.getConnection()){
            String suppresion = "DELETE FROM coach WHERE nom_utilisateur = ? ";
            PreparedStatement ps = co.prepareStatement(suppresion);
            ps.setString(1,nom_coach);
            int lignes_supp = ps.executeUpdate();
            if (lignes_supp> 0) {
                System.out.println("Le coach " + nom_coach + " a été supprimé avec succès.");
                message_validation.setText("Désinscription effectuée");
            } else {

                System.out.println("Aucun coach trouvé avec le nom d'utilisateur : " + nom_coach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}




/* critère minimum 8 caractères , 1 special, 1 majuscule , 1 minuscule, 1 chiffre, critère maximum 19 */
