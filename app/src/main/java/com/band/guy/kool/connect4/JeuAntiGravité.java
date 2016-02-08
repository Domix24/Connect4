/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.band.guy.kool.connect4;

import java.util.ArrayList;

/**
 *
 * @author Dominic
 */
public class JeuAntiGravité extends Jeu implements IActionsPuissance4 {

    public JeuAntiGravité(IÉvènementsPuissance4 évènementsJeu, int nombreLignes, int nombreColonnes) {
        super(évènementsJeu, nombreLignes, nombreColonnes);
    }
    @Override
    public int jouerIA() { return -1; }
    @Override
    public int jouer(int colonne) {
        int liigne = 0;

        if (this.avoirJoueur(this.nombreLignes - 1, colonne) != null) {
            this.évènementsJeu.positionDéjàOccupée();
            return -1;
        }

        for(int i = this.nombreLignes - 1; i >= 0; i--) {
            this.modifierCase(i - 1, colonne, this.avoirJoueur(i, colonne));
        }

        this.modifierCase(0, colonne, GestionnaireJoueurs.avoirInstance().avoirJoueurActif());
        this.nombreEssais++;

        if (this.estGagnant(0, colonne)) {
            this.évènementsJeu.aGagné();
        } else if (this.estPartieNulle()) {
            this.évènementsJeu.grillePleine();
        } else {
            GestionnaireJoueurs.avoirInstance().prochainJoueur();
            this.évènementsJeu.auTourDe();
        }
        return liigne;
    }

    @Override
    protected boolean estGagnant(int ligne, int colonne) {
        Joueur[] joueursGagnantsHorizontal = vérifierVictoireHorizontale(ligne, colonne);
        Joueur[] joueursGagnantsVertical = vérifierVictoireVerticale(ligne, colonne);

        if (joueursGagnantsHorizontal.length == 1) {
            GestionnaireJoueurs.avoirInstance().changerJoueurActif(joueursGagnantsHorizontal[0].avoirNomCourt());
            return true;
        }
        if (joueursGagnantsVertical.length == 1) {
            GestionnaireJoueurs.avoirInstance().changerJoueurActif(joueursGagnantsVertical[0].avoirNomCourt());
            return true;
        }

        return false;
    }

    private Joueur[] vérifierVictoireHorizontale(int ligne, int colonne) {
        ArrayList<Joueur> joueursGagnants = new ArrayList<>();

        int compteur = 0;
        boolean estDansListe;
        Joueur dernierJoueur, joueurActuel;

        for (int j = 0; j < this.nombreColonnes; j++) {
            compteur = 0;
            dernierJoueur = joueurActuel = null;
            for (int i = 0; i < this.nombreLignes; i++) {
                joueurActuel = this.avoirJoueur(i, j);

                if (joueurActuel == dernierJoueur && dernierJoueur != null) {
                    compteur++;
                } else {
                    compteur = 1;
                }

                if (compteur >= 4) {
                    estDansListe = false;
                    for (Joueur joueur : joueursGagnants) {
                        if (joueur.avoirNomCourt() == dernierJoueur.avoirNomCourt()) {
                            estDansListe = true;
                        }
                    }

                    if (!estDansListe) {
                        joueursGagnants.add(dernierJoueur);
                    }

                    compteur = 0;
                }

                dernierJoueur = joueurActuel;
            }
        }

        return joueursGagnants.toArray(new Joueur[joueursGagnants.size()]);
    }

    private Joueur[] vérifierVictoireVerticale(int ligne, int colonne) {
        ArrayList<Joueur> joueursGagnants = new ArrayList<>();

        int compteur;
        boolean estDansListe;
        Joueur dernierJoueur, joueurActuel;

        for (int j = 0; j < this.nombreLignes; j++) {
            dernierJoueur = joueurActuel = null;
            compteur = 0;
            for (int i = 0; i < this.nombreColonnes; i++) {
                joueurActuel = this.avoirJoueur(j, i);

                if (joueurActuel == dernierJoueur && dernierJoueur != null) {
                    compteur++;
                } else {
                    compteur = 1;
                }

                if (compteur >= 4) {
                    estDansListe = false;
                    for (Joueur joueur : joueursGagnants) {
                        if (joueur.avoirNomCourt() == dernierJoueur.avoirNomCourt()) {
                            estDansListe = true;
                        }
                    }

                    if (!estDansListe) {
                        joueursGagnants.add(dernierJoueur);
                    }

                    compteur = 0;
                }

                dernierJoueur = joueurActuel;
            }
        }

        return joueursGagnants.toArray(new Joueur[joueursGagnants.size()]);
    }
}
