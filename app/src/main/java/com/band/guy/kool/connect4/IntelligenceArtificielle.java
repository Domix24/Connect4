package com.band.guy.kool.connect4;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import android.graphics.Point;

import com.band.guy.kool.connect4.Joueur;


import java.util.Random;

/**
 *
 * @author Christophe
 */
public class IntelligenceArtificielle {

    char[][] cases;
    boolean aJoué = false;
    int nombrecolonnes;
    int nombrelignes;

    public IntelligenceArtificielle(int nombrecolonnes, int nombrelignes) {
        this.nombrecolonnes = nombrecolonnes;
        this.nombrelignes = nombrelignes;
    }
    
    public Point jouer(char[][] cases, Joueur dernierJoueur) {
        this.cases = cases;
        Point p;
        //On vérifie si on peut gagner
        p = vérifierHorizontal(GestionnaireJoueurs.avoirInstance().avoirJoueurActif());
        if (p.x == -1) {
            p = vérifierVertical(GestionnaireJoueurs.avoirInstance().avoirJoueurActif());
        }

        //Sinon on vérifie si on est sur le point de perdre
        if (p.x == -1) {
            p = vérifierHorizontal(dernierJoueur);
        }
        if (p.x == -1) {
            p = vérifierVertical(dernierJoueur);
        }

        //Sinon on joue Random
        if (p.x == -1) {
            p = jouerAléatoirement();
        }
        aJoué = false;
        return p;
    }

    private Point vérifierHorizontal(Joueur position) {
        int compteur;
        for (int j = nombrelignes - 1; j >= 0; j--) {
            compteur = 0;
            for (int i = 0; i < nombrecolonnes - 1; i++) {
                              
                if (cases[j][i] == position.avoirNomCourt()) {
                    compteur++;
                } else {
                    compteur = 0;
                }

                if (compteur == 3) {
                    if (cases[j][i + 1] == Joueur.VIDE.avoirNomCourt()) {
                        if (j == 5 || (cases[j + 1][i + 1] != Joueur.VIDE.avoirNomCourt())) {
                            //cases[j][i + 1] = GestionnaireJoueurs.avoirInstance().avoirJoueurActif().avoirNomCourt();
                            aJoué = true;
                            return new Point(j, i + 1);
                        }
                    } else if (i - 3 >= 0) {
                        if (cases[j][i - 3] == Joueur.VIDE.avoirNomCourt()) {
                            if (j == 5 || (cases[j + 1][i + 1] != Joueur.VIDE.avoirNomCourt())) {
                                //cases[j][i - 3] = GestionnaireJoueurs.avoirInstance().avoirJoueurActif().avoirNomCourt();
                                aJoué = true;
                                return new Point(j, i - 3);
                            }
                        }
                    }
                }
            }
        }
        return new Point(-1, -1);
    }

    private Point vérifierVertical(Joueur position) {
        int compteur;
        for (int i = 0; i < nombrecolonnes - 1; i++) {
            compteur = 0;
            for (int j = nombrelignes - 1; j >= 0; j--) {
                if (cases[j][i] == position.avoirNomCourt()) {
                    compteur++;
                } else {
                    compteur = 0;
                }

                if (compteur == 3) {
                    if (cases[j - 1][i] == Joueur.VIDE.avoirNomCourt() && j != 0) {
                        aJoué = true;
                        return new Point(j - 1, i);
                    }
                }
            }
        }
        return new Point(-1, -1);
    }

    private Point jouerAléatoirement() {
        int ligne = 0;
        int colonne = 0;
        while (!aJoué) {
            Random rand = new Random();
            colonne = rand.nextInt(nombrecolonnes);
            if (cases[0][colonne] != Joueur.VIDE.avoirNomCourt()) {
                return new Point(-1, -1);
            }

            for (int i = 1; i < nombrelignes; i++) {
                if (cases[i][colonne] == Joueur.VIDE.avoirNomCourt()) {
                    ligne = i;
                    aJoué = true;
                }
            }
        }
        
        return new Point(ligne, colonne);
    }    
}