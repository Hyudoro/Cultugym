package com.example.application.controleur.calendrier;

import com.example.application.connexionbdd.Bddco;

import java.sql.*;

public class IMC {
    public double Insert_IMC() {
        Bddco bdd = new Bddco();
        double IMC = 0;
        try (Connection co = bdd.getConnection()) {
            String query = "SELECT taille,poids,id_client FROM client_selection";
            Statement statement = co.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                int id_client = rs.getInt("id_client");
                double poids = rs.getInt("poids");
                double Taille = (double) (rs.getInt("taille")) / 100;//conversion de centimètres à mètres pour bien utiliser la formule
                IMC = poids / Math.pow(Taille, 2);
                String query2 = "UPDATE informations_bonus SET imc = ? WHERE id_client = ? ";
                PreparedStatement ps = co.prepareStatement(query2);
                ps.setDouble(1, IMC);
                ps.setInt(2, id_client);
                ps.executeUpdate();
                return IMC;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return IMC;
    }

    public double Affichage_IMC(){
        Double IMC;
        Bddco bdd = new Bddco();
        try(Connection co = bdd.getConnection()){
        String query1 = "SELECT id_client from client_selection";
        Statement statement1 = co.createStatement();
        ResultSet rs1 = statement1.executeQuery(query1);
        if(rs1.next()) {
            int id_client = rs1.getInt("id_client");
            String query2 = "SELECT imc FROM informations_bonus WHERE id_client = ? ";
            PreparedStatement ps = co.prepareStatement(query2);
            ps.setInt(1, id_client);
            ResultSet rs2 = ps.executeQuery();
            if (rs2.next()) {
                IMC = rs2.getDouble("imc");
                return IMC;

            }
        }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}

        
        

    

    

