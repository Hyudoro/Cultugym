
package com.example.application.controleur;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import com.example.application.GestionImages.Gestion_chemins_Images;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.ImageView;
import com.example.application.connexionbdd.Bddco;
import javafx.scene.input.DragEvent;
import javafx.scene.text.Text;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;

import javax.imageio.ImageIO;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Control_Interface_Creation_Client {
    @FXML
    private ImageView logo, image_face, image_derriere, image_profil_droit, image_profil_gauche;

    @FXML
    private Label Numero_dossier;

    @FXML
    private TextArea Description;

    @FXML
    private Spinner<Double> Taille, Poids;

    @FXML
    private Spinner<Integer> age;
    @FXML
    private Text validation_insertion,format_images;

    int Agee;
    private Double hauteurr,poidss;


    @FXML
    private ComboBox<String> Sexe, Experience, Corpulence,niveau_activite;

    @FXML
    private TextField Nom_utilisateur, Adresse, Prenom, Nom, Email,code_postal;

    @FXML
    private Button valider_ajout;


    private final Map<ImageView, File> imagesToSave = new HashMap<>();

    private boolean isImageFile(String fileName) {
        String lowerCaseFileName = fileName.toLowerCase();
        return lowerCaseFileName.endsWith(".png") || lowerCaseFileName.endsWith(".gif") || lowerCaseFileName.endsWith(".jpg") || lowerCaseFileName.endsWith(".jpeg") || lowerCaseFileName.endsWith(".heif");
    }
    private boolean isFileSizeValid(File file) {
        final long maxSizeInBytes = 3 * 1024 * 1024; // 3 Mo en octets
        return file.length() <= maxSizeInBytes;
    }

    private boolean areImageDimensionsValid(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();

            // Vérifier si les dimensions de l'image ne dépassent pas 210x227 pixels
            return width <= 210 && height <= 275;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    String cheminlogo= Gestion_chemins_Images.Gestion_chemins_Images_logo;
    @FXML
    public void initialisation() {

        configureDragAndDrop(image_face);        //insertion des images dans les imageview
        configureDragAndDrop(image_derriere);
        configureDragAndDrop(image_profil_droit);
        configureDragAndDrop(image_profil_gauche);

        SpinnerValueFactory<Double> hauteur = new SpinnerValueFactory.DoubleSpinnerValueFactory(155, 290, 0, 0.5);
        SpinnerValueFactory<Double> kilos = new SpinnerValueFactory.DoubleSpinnerValueFactory(45, 500, 0, 0.5);
        SpinnerValueFactory<Integer> vieillesse = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 100, 0, 1);


        Taille.setValueFactory(hauteur);
        Poids.setValueFactory(kilos);
        age.setValueFactory(vieillesse);
        hauteurr = hauteur.getValue();
        poidss = kilos.getValue();
        Agee = vieillesse.getValue();




        Image image_logo = new Image("file:" + cheminlogo + "logo.png");
        logo.setImage(image_logo);
        valider_ajout.setOnAction(event -> {
            insertion_infos_client();
            sauvegarde_image_face();
            sauvegarde_image_derriere();
            sauvegarde_image_profil_droit();
            sauvegarde_image_profil_gauche();
            numero_dossier();
        });

    }

    public void insertion_infos_client(){
        if (Sexe.getValue() != null && Experience.getValue() != null && Corpulence.getValue() != null && niveau_activite.getValue() !=null &&!Nom_utilisateur.getText().isEmpty() && !Prenom.getText().isEmpty() && !Adresse.getText().isEmpty() && !Nom.getText().isEmpty() && !Email.getText().isEmpty() && hauteurr !=0 && poidss !=0 && Agee !=0 && !Description.getText().isEmpty() && !code_postal.getText().isEmpty()) {
            int code_postal_int = Integer.parseInt(code_postal.getText()); // on met la valeur code postal en entier


            Bddco bdd = new Bddco();
            try(Connection co = bdd.getConnection()){
                String requete = "INSERT INTO client(nom,Prenom,email,nom_utilisateur,age,taille,poids,adresse,code_postal,description,Experience,sexe,corpulence,niveau_activite,nom_Photo_Face,nom_Photo_Derriere,nom_Photo_Droite,nom_Photo_Gauche)"+
                                  "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = co.prepareStatement(requete);
                ps.setString(1,Nom.getText());
                ps.setString(2,Prenom.getText());
                ps.setString(3,Email.getText());
                ps.setString(4,Nom_utilisateur.getText());
                ps.setInt(5,age.getValue());
                ps.setDouble(6,Taille.getValue());
                ps.setDouble(7,Poids.getValue());
                ps.setString(8,Adresse.getText());
                ps.setInt(9,code_postal_int);
                ps.setString(10,Description.getText());
                ps.setString(11,Experience.getValue());
                ps.setString(12,Sexe.getValue());
                ps.setString(13,Corpulence.getValue());
                ps.setString(14,niveau_activite.getValue());

                File fileImageFace = imagesToSave.get(image_face);
                File fileImageDerriere = imagesToSave.get(image_derriere);
                File fileImageProfilDroit = imagesToSave.get(image_profil_droit);
                File fileImageProfilGauche = imagesToSave.get(image_profil_gauche);

                ps.setString(15, fileImageFace != null ? fileImageFace.getName() : null);
                ps.setString(16, fileImageDerriere != null ? fileImageDerriere.getName() : null);
                ps.setString(17, fileImageProfilDroit != null ? fileImageProfilDroit.getName() : null);
                ps.setString(18, fileImageProfilGauche != null ? fileImageProfilGauche.getName() : null);


                int nombre_colone_insert = ps.executeUpdate();
                if (nombre_colone_insert == 18){
                    System.out.println("l'insertion a bien fonctionnée");
                    validation_insertion.setText("Enregistrement réussi !");
                }
                String insertion_nom_utili_informations_bonus = "INSERT INTO informations_bonus(nom_utilisateur,niveau_activite) VALUES (?,?)";
                PreparedStatement ps5 = co.prepareStatement(insertion_nom_utili_informations_bonus);
                ps5.setString(1,Nom_utilisateur.getText());
                ps5.setString(2,niveau_activite.getValue());
                ps5.executeUpdate();

                String client = "SELECT id FROM client WHERE nom_utilisateur = ? ";
                PreparedStatement ps6 = co.prepareStatement(client);
                ps6.setString(1,Nom_utilisateur.getText());
                ResultSet rs6 = ps6.executeQuery();
                if(rs6.next()){
                    int idd = rs6.getInt("id");
                    String insertion_id_utili_informations_bonus = "UPDATE informations_bonus SET id_client = ? WHERE nom_utilisateur = ? ";
                    PreparedStatement ps8 = co.prepareStatement(insertion_id_utili_informations_bonus);
                    ps8.setInt(1,idd);
                    ps8.setString(2,Nom_utilisateur.getText());
                    ps8.executeUpdate();

                }

            }catch(SQLException e ){
                e.printStackTrace();
            }
        }

    }
    private void configureDragAndDrop(ImageView imageView) {
        imageView.setOnDragOver(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        imageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                try {
                    File file = db.getFiles().get(0); // Prendre le premier fichier
                    if (isImageFile(file.getName()) && isFileSizeValid(file) && areImageDimensionsValid(file)) { // Vérifier si le fichier est une image de type et de taille autorisé ou de dimensions autorisé (formats, taille max = 3 Mo)
                        format_images.setText("Le(s) formats sont valides et tailles valides.");
                        Image image = new Image(new FileInputStream(file));
                        imageView.setImage(image); // Mettre à jour l'ImageView avec l'image

                        // Stocker la référence du fichier pour sauvegarde ultérieure
                        imagesToSave.put(imageView, file);

                        success = true;
                    } else if(!isImageFile(file.getName())) {
                        format_images.setText("***Le format d'image n'est pas bon. PNG - JPEG - GIF - HEIF uniquement");
                        System.out.println("Type de fichier non autorisé.");
                    } else if(!isFileSizeValid(file)){
                        format_images.setText("***La taille de l'image est trop grande, Maximum 3Mo");
                        System.out.println("Taille image trop grande");
                    } else if(!areImageDimensionsValid(file)){
                        format_images.setText("***Les dimensiosn de l'image doivent au maximum être 210 x 275");
                        System.out.println("image trop grosse");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }
    private void saveImageForImageView(ImageView imageView, String repertoire_cible) {
        File directory = new File("Images/Images_Client/" + repertoire_cible);

        imagesToSave.entrySet().stream()
                .filter(entry -> entry.getKey().equals(imageView))
                .forEach(entry -> {
                    File file = entry.getValue();
                    File destFile = new File(directory, file.getName());
                    try {
                        Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println(repertoire_cible + " a été sauvegardée avec succès.");
        validation_insertion.setText("enregistrement des données réussie ! ");
    }

    public void sauvegarde_image_face() {
        saveImageForImageView(image_face, "Face");
    }

    public void sauvegarde_image_derriere() {
        saveImageForImageView(image_derriere, "Derriere");
    }

    public void sauvegarde_image_profil_droit() {
        saveImageForImageView(image_profil_droit, "Profil_Droit");
    }

    public void sauvegarde_image_profil_gauche() {
        saveImageForImageView(image_profil_gauche, "Profil_Gauche");
    }

    public void numero_dossier(){
        Bddco bdd = new Bddco();
        try(Connection co = bdd.getConnection()){
            String requete = "SELECT id AS Numero_dossierr FROM client WHERE nom_utilisateur = ? ";
            PreparedStatement ps = co.prepareStatement(requete);
            ps.setString(1,Nom_utilisateur.getText());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Numero_dossier.setText(rs.getString("Numero_dossierr"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
