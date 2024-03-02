package com.example.application.interactionbdd;

import com.example.application.connexionbdd.Bddco;


public class Bdd {
    private Bddco bddco = new Bddco();
    public void stop() {
        bddco.closeConnection(); // Fermez la connexion via Bddco
    }
}
