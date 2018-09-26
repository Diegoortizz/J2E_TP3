/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tp3;

import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;

import java.util.Arrays;

//import bowling.SinglePlayerGame;
/**
 *
 * @author Utilisateur
 */
public class MultiPlayer implements MultiPlayerGame {

    SinglePlayerGame[] SPG;
    String[] PlayerNames;
    int indiceJoueurTour;
    int nbJoueur;

    public MultiPlayer() {
        SPG = null;
        indiceJoueurTour = 0;
    }

    @Override
    public String startNewGame(String[] playerName) throws Exception {
        nbJoueur = playerName.length;
        if (playerName.length == 0) {
            throw new UnsupportedOperationException("Il faut au moins un joueur");
        } else {
            SPG = new SinglePlayerGame[nbJoueur];
            PlayerNames = new String[nbJoueur];
            for (int i = 0; i < nbJoueur; i++) {
                SPG[i] = new SinglePlayerGame();
                PlayerNames[i] = playerName[i];
            }

            String intro = String.join(",", playerName);
            return "Entrée en jeu de" + " " + intro;
        }

    }

    public String Affichage(String nom, int tour, int boule) {
        return String.format("Prochain tir : joueur %s, tour n° %d, boule n° %d", nom, tour, boule);
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if (SPG == null) {
            throw new UnsupportedOperationException("La partie n'a pas commencé");
        }

        if (SPG[indiceJoueurTour].getCurrentFrame().isFinished()) {
            indiceJoueurTour = (indiceJoueurTour + 1) % nbJoueur;
//            System.out.println("\n");
//        System.out.println("!!!!!!!!!!" + " " + ((SPG[indiceJoueurTour].getCurrentFrame().frameNumber == 10) && (SPG[indiceJoueurTour].getCurrentFrame().isFinished())));
        }

        SPG[indiceJoueurTour].lancer(nombreDeQuillesAbattues);
        return Affichage(PlayerNames[indiceJoueurTour], SPG[indiceJoueurTour].getCurrentFrame().getFrameNumber(), SPG[indiceJoueurTour].getCurrentFrame().getBallsThrown());

    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        if (!(Arrays.stream(PlayerNames).anyMatch(playerName::equals))) {
            throw new UnsupportedOperationException("Ce joueur n'existe pas"); //To change body of generated methods, choose Tools | Templates.
        }
        return SPG[Arrays.asList(PlayerNames).indexOf(playerName)].score();

    }

}
