package com.example.application.controleur.calendrier;

import com.example.application.connexionbdd.Bddco;

import java.sql.*;
import java.util.Objects;

public class BMR_ {
    public Double Insert_BMR() {
        Bddco bdd = new Bddco();
        double BMR = 0;
        try (Connection co = bdd.getConnection()) {
            String query = "SELECT taille,poids,age,id_client,sexe FROM client_selection";
            Statement statement = co.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                int h_f = 0;
                int id_client = rs.getInt("id_client");


                double poids = rs.getInt("poids");
                int age_personne = rs.getInt("age");
                double Taille = rs.getInt("taille");
                String sxee = rs.getString("sexe");
                if (Objects.equals(sxee, "M")) {
                    h_f = 5;
                } else if (Objects.equals(sxee, "F")) {
                    h_f = -161;
                }
                BMR = (10 * poids) + (6.25 * Taille) - (5 * age_personne) + h_f;
                String query2 = "UPDATE informations_bonus SET BMR = ? WHERE id_client = ? ";
                PreparedStatement ps = co.prepareStatement(query2);
                ps.setDouble(1, BMR);
                ps.setInt(2, id_client);
                ps.executeUpdate();
                return BMR;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return BMR;
    }

    public double Affichage_BMR(){
        Double BMR;
        Bddco bdd = new Bddco();
        try(Connection co = bdd.getConnection()){
            String query1 = "SELECT id_client from client_selection";
            Statement statement1 = co.createStatement();
            ResultSet rs1 = statement1.executeQuery(query1);
            if(rs1.next()) {
                int id_client = rs1.getInt("id_client");
                String query2 = "SELECT BMR FROM informations_bonus WHERE id_client = ? ";
                PreparedStatement ps = co.prepareStatement(query2);
                ps.setInt(1, id_client);
                ResultSet rs2 = ps.executeQuery();
                if (rs2.next()) {
                    BMR = rs2.getDouble("BMR");
                    return BMR;

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

}
