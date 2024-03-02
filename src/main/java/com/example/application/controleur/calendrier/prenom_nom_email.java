package com.example.application.controleur.calendrier;

import com.example.application.connexionbdd.Bddco;

import java.sql.*;

public class prenom_nom_email {
    public String prenom_nom_classe() {
        Bddco bdd = new Bddco();
        try (Connection co = bdd.getConnection()) {
            String recup_id = "SELECT id_client FROM client_selection";
            Statement statement = co.createStatement();
            ResultSet rs = statement.executeQuery(recup_id);
            if (rs.next()) {
                int client_concerne = rs.getInt("id_client");
                String nom_prenom = "SELECT nom,Prenom FROM client_selection WHERE id_client = ?";
                PreparedStatement ps = co.prepareStatement(nom_prenom);
                ps.setInt(1, client_concerne);
                ResultSet rs2 = ps.executeQuery();
                if (rs2.next()) {
                    return rs2.getString("Prenom") + " " + rs2.getString("nom");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur de connexion");
        }
        System.out.println("aucune client selectionné");
        return null;
    }
}


