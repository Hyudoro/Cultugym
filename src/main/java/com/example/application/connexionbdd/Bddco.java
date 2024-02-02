package com.example.application.connexionbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bddco {
    private static final String URL = "jdbc:mysql://localhost:3306/muscu19";
    private static final String USER = "root";
    private static final String PASSWORD = "Lelascarsurmysql06*";
    private Connection connexion;

    public Bddco() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base de données réussie depuis Bddco !");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connexion;
    }

    public void closeConnection() {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
                System.out.println("Connexion à la base de données fermée avec succès depuis Bddco !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture de la connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
