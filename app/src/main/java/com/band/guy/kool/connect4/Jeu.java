package com.band.guy.kool.connect4;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dominic
 */
public class Jeu implements ActionsPuissance4 {

    int nombreLignes;
    int nombreColonnes;
    int nombreEssais;

    Joueur[][] cases;
    Joueur joueurActif;

    ÉvènementsPuissance4 évènementsJeu;

    public Jeu(ÉvènementsPuissance4 évènementsJeu, Joueur joueurActif, int nombreLignes, int nombreColonnes) {
        this.joueurActif = joueurActif;
        this.évènementsJeu = évènementsJeu;

        this.nombreLignes = nombreLignes;
        this.nombreColonnes = nombreColonnes;
        this.nombreEssais = 0;

        this.cases = new Joueur[this.nombreLignes][this.nombreColonnes];

        for (int i = 0; i < this.nombreLignes; i++) {
            for (int j = 0; j < this.nombreColonnes; j++) {
                this.cases[i][j] = Joueur.Aucun;
            }
        }
    }

    @Override
    public Joueur avoirJoueur(int ligne, int colonne) {
        if (ligne >= 0 && ligne < this.nombreLignes && colonne >= 0 && colonne < this.nombreColonnes) {
            return cases[ligne][colonne];
        }
        return Joueur.Aucun;
    }

    @Override
    public int jouer(int colonne) {

        int ligne = 0;

        if (this.avoirJoueur(0, colonne) != Joueur.Aucun) {
            return -1;
        }

        for (int i = 1; i < this.nombreLignes; i++) {
            if (this.avoirJoueur(i, colonne) == Joueur.Aucun) {
                ligne = i;
            }
        }

        cases[ligne][colonne] = joueurActif;

        this.nombreEssais++;

        if (this.vérifierVictoire(colonne, ligne)) {
            this.évènementsJeu.aGagné(joueurActif);
        } else if (this.nombreEssais == this.nombreColonnes * this.nombreLignes) {
            this.évènementsJeu.grillePleine();
        } else {
            joueurActif = joueurActif == Joueur.Noir ? Joueur.Blanc : Joueur.Noir;
            this.évènementsJeu.auTourDe(joueurActif);
        }
        return  ligne;
    }

    private boolean vérifierVictoire(int colonne, int ligne) {
        return vérifierVerticale(colonne) || vérifierHorizontal(ligne) || vérifierDiagonale(ligne, colonne);
    }

    private boolean vérifierVerticale(int colonne) {
        int compteur = 0;
        Joueur derniereCase = Joueur.Aucun;

        for (int i = 0; i < this.nombreLignes && compteur < 4; i++) {
            if (this.avoirJoueur(i, colonne) == derniereCase && derniereCase != Joueur.Aucun) {
                compteur++;
            } else {
                compteur = 1;
            }

            derniereCase = this.avoirJoueur(i, colonne);
        }

        return compteur == 4;
    }

    private boolean vérifierHorizontal(int ligne) {
        int compteur = 0;
        Joueur derniereCase = Joueur.Aucun;

        for (int i = 0; i < this.nombreColonnes && compteur < 4; i++) {
            if (this.avoirJoueur(ligne, i) == derniereCase && derniereCase != Joueur.Aucun) {
                compteur++;
            } else {
                compteur = 1;
            }

            derniereCase = this.avoirJoueur(ligne, i);
        }

        return compteur == 4;
    }

    private boolean vérifierDiagonale(int ligne, int colonne) {
        int compteur1 = 0;
        int compteur2 = 0;
        for (int i = -3; i < 4; i++) {
            if (avoirJoueur(ligne + i, colonne + i) == joueurActif) {
                compteur1++;
            } else {
                compteur1 = 0;
            }

            if (avoirJoueur(ligne + i, colonne - i) == joueurActif) {
                compteur2++;
            } else {
                compteur2 = 0;
            }

            if (compteur1 == 4 || compteur2 == 4) {
                return true;
            }
        }
        return false;
    }
}
