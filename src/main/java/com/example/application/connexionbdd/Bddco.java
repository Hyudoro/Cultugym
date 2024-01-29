package com.example.application.connexionbdd;
import java.sql.*;

public class Bddco {
    private static final String URL = "jdbc:mysql://localhost:3306/muscu19";
    private static final String USER = "root";
    private static final String PASSWORD = "Lelascarsurmysql06*";
    private Connection connexion;

    public Bddco() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Vous pouvez ajouter des méthodes pour exécuter des opérations SQL avec la connexion.
    // Par exemple, une méthode pour exécuter des requêtes SELECT.

    public Connection getConnection() {
        return connexion;
    }


    public void closeConnection() {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
