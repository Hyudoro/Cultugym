package com.example.application.controleur.calendrier;

import com.example.application.connexionbdd.Bddco;

import java.sql.*;

public class type_de_programme {

    public String min_max_mid(double valeur) {
        Bddco bdd = new Bddco();
        String nom_type_programme;
        if (valeur == 0) {
            nom_type_programme = "Split";
        }
        else if(valeur == 50) {
            nom_type_programme = "Full body";
        } else {
            nom_type_programme = "Cardio";

        }
        try(Connection co = bdd.getConnection()){
            String client = "SELECT id_client from client_selection";
            Statement s = co.createStatement();
            ResultSet rs = s.executeQuery(client);
            if(rs.next()) {
                int client_concerne = rs.getInt("id_client");
                String update = "UPDATE informations_bonus SET type_de_programme = ? WHERE id_client = ? ";
                PreparedStatement ps = co.prepareStatement(update);
                ps.setString(1,nom_type_programme);
                ps.setInt(2,client_concerne);
                ps.executeUpdate();
                return nom_type_programme;
            }else{System.out.println("aucun client trouvé");}

        }catch (SQLException e){
            e.printStackTrace();
        }
    return " type_de_programme non spécifié par le coach ";}
}
