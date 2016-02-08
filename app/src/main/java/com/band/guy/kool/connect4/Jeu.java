package com.band.guy.kool.connect4;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe abstraite qui définie les mode de jeu
 * @author Dominic
 */
public abstract class Jeu implements IActionsPuissance4 {

    int nombreLignes;
    int nombreColonnes;
    int nombreEssais;

    char[][] cases;

    IÉvènementsPuissance4 évènementsJeu;

    /**
     * Constructeur de la classe
     * @param évènementsJeu
     * @param nombreLignes
     * @param nombreColonnes
     */
    public Jeu(IÉvènementsPuissance4 évènementsJeu, int nombreLignes, int nombreColonnes) {
        this.évènementsJeu = évènementsJeu;

        this.nombreLignes = nombreLignes;
        this.nombreColonnes = nombreColonnes;
        this.nombreEssais = 0;

        this.cases = new char[this.nombreLignes][this.nombreColonnes];

        for (int i = 0; i < this.nombreLignes; i++) {
            for (int j = 0; j < this.nombreColonnes; j++) {
                this.cases[i][j] = Joueur.VIDE.avoirNomCourt();
            }
        }

        this.évènementsJeu.auTourDe();
    }

    @Override
    public Joueur avoirJoueur(int ligne, int colonne) {
        if (ligne >= 0 && ligne < this.nombreLignes && colonne >= 0 && colonne < this.nombreColonnes && cases[ligne][colonne] != Joueur.VIDE.avoirNomCourt()) {
            return GestionnaireJoueurs.avoirInstance().avoirJoueurAvecNomCourt(cases[ligne][colonne]);
        }
        return null;
    }

    /**
     * Va placer la position jouer dans l array
     * @param ligne position
     * @param colonne position
     * @param joueur joueur qui joue
     */
    protected void modifierCase(int ligne, int colonne, Joueur joueur) {
        if (ligne >= 0 && ligne < this.nombreLignes && colonne >= 0 && colonne < this.nombreColonnes) {
            cases[ligne][colonne] = joueur.avoirNomCourt();
        }
    }

    @Override
    public abstract int jouer(int colonne);

    protected boolean estPartieNulle() {
        return this.nombreEssais >= this.nombreColonnes * this.nombreLignes;
    }

    protected abstract boolean estGagnant(int ligne, int colonne);
}