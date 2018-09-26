/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.tp3.MultiPlayer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego
 */
public class MultiPlayerTestUnit {

    public MultiPlayerTestUnit() {
    }

    private MultiPlayer game;

    @Before
    public void setUp() {
        game = new MultiPlayer();
    }

    @Test
    public void InitialisationJoueurs() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        assertEquals(game.startNewGame(L), "Entrée en jeu de Benjamin,Pierre,Diego,Matthias,Maxime");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void InitialisationJoueursVide() throws Exception {
        String[] L = {};
        game.startNewGame(L);
    }

    @Test
    public void FonctionAffichage() {
        assertEquals(game.Affichage("Diego", 7, 2), "Prochain tir : joueur Diego, tour n° 7, boule n° 2");
    }

    @Test
    public void ProchainLanceurProchaineBoule() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);
        game.lancer(8);
        game.lancer(1);

        game.lancer(1);
        assertEquals(game.lancer(2), "Prochain tir : joueur Pierre, tour n° 1, boule n° 2");
    }

    @Test
    public void ProchainLanceur() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);
        assertEquals(game.lancer(10), "Prochain tir : joueur Benjamin, tour n° 1, boule n° 1");
    }

    @Test
    public void ProchainLanceurProchaineBoule2() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);
        game.lancer(5);
        game.lancer(5);

        game.lancer(5);
        game.lancer(5);

        game.lancer(5);
        game.lancer(5);

        game.lancer(5);
        assertEquals(game.lancer(5), "Prochain tir : joueur Matthias, tour n° 1, boule n° 2");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ProchainLanceurSansInitialisation() throws Exception {
        game.lancer(5);

    }

    @Test
    public void ProchainLancéStrike() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);
        game.lancer(10);
        game.lancer(10);
        game.lancer(10);
        game.lancer(10);
        assertEquals(game.lancer(10), "Prochain tir : joueur Maxime, tour n° 1, boule n° 1");
    }

    @Test
    public void ProchainTour() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);

        game.lancer(10);

        game.lancer(10);

        game.lancer(5);
        game.lancer(2);

        game.lancer(8);
        game.lancer(2);

        game.lancer(10);

        game.lancer(8);
        game.lancer(2);

        game.lancer(1);
        game.lancer(0);

        game.lancer(8);
        game.lancer(0);

        game.lancer(4);
        game.lancer(4);

        assertEquals(game.lancer(10), "Prochain tir : joueur Maxime, tour n° 2, boule n° 1");
    }

    @Test
    public void JeuFiniJeuParfait() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);
        rollMany(59, 10); // 12* le nombre de joueur = 12 * 5 = 60 (on retire 1 pour pouvoir afficher le dernier coup)
        assertEquals(game.lancer(10), "Prochain tir : joueur Maxime, tour n° 10, boule n° 3");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void JeuFini() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);
        rollMany(60, 10);
        game.lancer(10);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ScorePersonnePasPresente() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);
        rollMany(60, 10);
        game.scoreFor("Jean");
    }

    @Test
    public void ScoreParfaitToutJoueurs() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);
        rollMany(60, 10);
        for (int i = 0; i < L.length; i++) {
            assertEquals(game.scoreFor(L[i]), 300);
        }
    }

    @Test
    public void StrikeToutlesCoups() throws Exception {
        String[] L = {"Benjamin", "Pierre", "Diego", "Matthias", "Maxime"};
        game.startNewGame(L);
        rollMany(105, 5); // 21 * 5 = 105
        for (int i = 0; i < L.length; i++) {
            assertEquals(game.scoreFor(L[i]), 150);
        }
    }

    private void rollMany(int n, int pins) throws Exception {
        for (int i = 0; i < n; i++) {
            game.lancer(pins);
        }
    }

}
