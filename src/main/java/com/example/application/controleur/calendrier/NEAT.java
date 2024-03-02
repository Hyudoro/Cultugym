package com.example.application.controleur.calendrier;

import com.example.application.connexionbdd.Bddco;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.Objects;

public class NEAT {
    public Double Insert_NEAT() {
        Bddco bdd = new Bddco();
        double NEAT = 0;
        try (Connection co = bdd.getConnection()) {
            String query = "SELECT id_client FROM client_selection";
            Statement statement = co.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                int id_client = rs.getInt("id_client");
                String query12 = "SELECT BMR,niveau_activite FROM informations_bonus WHERE id_client = ? ";
                PreparedStatement ps1 = co.prepareStatement(query12);
                ps1.setInt(1, id_client);
                ResultSet rs12 = ps1.executeQuery();
                if (rs12.next()) {
                    double na;
                    double BMR = rs12.getDouble("BMR");
                    String niveau_activite = rs12.getString("niveau_activite");
                    if (niveau_activite.equals("Sédentaire")) {
                        na = 1.3;
                    } else if (niveau_activite.equals("Activitè restreinte")) {
                        na = 1.6;
                    } else if (niveau_activite.equals("Activité Modérée")) {
                        na = 1.9;
                    } else {
                        na = 2.1;
                    }
                    NEAT = BMR * na;    //Calcul du NEAT
                    String query2 = "UPDATE informations_bonus SET NEAT = ? WHERE id_client = ? ";
                    PreparedStatement ps2 = co.prepareStatement(query2);
                    ps2.setDouble(1, NEAT);
                    ps2.setInt(2, id_client);
                    ps2.executeUpdate();
                    return NEAT;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return NEAT;
    }


    public double Affichage_NEAT(){
        double NEAT;
        Bddco bdd = new Bddco();
        try(Connection co = bdd.getConnection()){
            String query1 = "SELECT id_client from client_selection";
            Statement statement1 = co.createStatement();
            ResultSet rs1 = statement1.executeQuery(query1);
            if(rs1.next()) {
                int id_client = rs1.getInt("id_client");
                String query2 = "SELECT NEAT FROM informations_bonus WHERE id_client = ? ";
                PreparedStatement ps = co.prepareStatement(query2);
                ps.setInt(1, id_client);
                ResultSet rs2 = ps.executeQuery();
                if (rs2.next()) {
                    NEAT = rs2.getDouble("NEAT");
                    return NEAT;

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}
