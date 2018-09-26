/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tp3;

import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.HashMap;

/**
 *
 * @author Diego
 */
public class MultiPlayerHM implements MultiPlayerGame {

    HashMap<String, SinglePlayerGame> Players = null;
    HashMap<String, String> NextPlayer = null;
    int numberPlayers;
    String JoueurCourrant;

    @Override
    public String startNewGame(String[] playerName) throws Exception {
        if (playerName.length == 0) {
            throw new UnsupportedOperationException("Il faut au moins un joueur");
        } 
        numberPlayers = playerName.length;
        JoueurCourrant = playerName[0];

        Players = new HashMap<>();
        NextPlayer = new HashMap<>();
        for (int i = 0; i < numberPlayers - 1; i++) {
            Players.put(playerName[i], new SinglePlayerGame());
            NextPlayer.put(playerName[i], playerName[i + 1]);
        }
        Players.put(playerName[numberPlayers - 1], new SinglePlayerGame());
        NextPlayer.put(playerName[numberPlayers - 1], playerName[0]);
        String intro = String.join(",", playerName);
        return "Entrée en jeu de" + " " + intro;

    }

    public String Affichage(String nom, int tour, int boule) {
        return String.format("Prochain tir : joueur %s, tour n° %d, boule n° %d", nom, tour, boule);
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if (Players == null) {
            throw new UnsupportedOperationException("La partie n'a pas commencé");
        }

        if (Players.get(JoueurCourrant).getCurrentFrame().isFinished()) {
            JoueurCourrant = NextPlayer.get(JoueurCourrant);
//        System.out.println("!!!!!!!!!!" + " " + ((SPG[indiceJoueurTour].getCurrentFrame().frameNumber == 10) && (SPG[indiceJoueurTour].getCurrentFrame().isFinished())));
        }
        System.out.println(Players);
        Players.get(JoueurCourrant).lancer(nombreDeQuillesAbattues);
        return Affichage(JoueurCourrant, Players.get(JoueurCourrant).getCurrentFrame().getFrameNumber(), Players.get(JoueurCourrant).getCurrentFrame().getBallsThrown());

    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        if (!Players.containsKey(playerName)) {
            throw new UnsupportedOperationException("Ce joueur n'existe pas"); //To change body of generated methods, choose Tools | Templates.

        }
        return Players.get(playerName).score();

    }

}
