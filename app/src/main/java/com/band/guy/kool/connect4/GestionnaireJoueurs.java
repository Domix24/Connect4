package com.band.guy.kool.connect4;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Dominic
 */
public class GestionnaireJoueurs {

    private static GestionnaireJoueurs instance = null;

    private int joueurActif;
    
    private final ArrayList<Joueur> joueurs;

    protected GestionnaireJoueurs() {
        joueurs = new ArrayList<>();
    }

    public static GestionnaireJoueurs avoirInstance() {
        if (instance == null) {
            instance = new GestionnaireJoueurs();
        }

        return instance;
    }

    public void ajouterJoueur(String nom, char nomCourt) {
        if(avoirJoueurAvecNomCourt(nomCourt) == null) {
            joueurs.add(new Joueur(nom, nomCourt, false));
        }
    }
    
    public void ajouterOrdinateur(String nom, char nomCourt) {
        if(avoirJoueurAvecNomCourt(nomCourt) == null) {
            joueurs.add(new Joueur(nom, nomCourt, true));
        }
    }
    
    public int nombreJoueurs() {
        return this.joueurs.size();
    }
    
    public void prochainJoueur() {
        if(++this.joueurActif >= this.joueurs.size()) {
            this.joueurActif = 0;
        }
    }
    
    public Joueur avoirJoueurActif() {
        return joueurs.get(this.joueurActif);
    }
    
    public void changerJoueurActif(char nomCourt) {
        for(int i = 0; i < this.joueurs.size(); i++) {
            if(this.joueurs.get(i).avoirNomCourt() == nomCourt) {
                this.joueurActif = i;
            }
        }
    }
    
    public Joueur avoirJoueurAvecNomCourt(char nomCourt) {
        for(Joueur joueur : this.joueurs) {
            if(nomCourt == joueur.avoirNomCourt()) {
                return joueur;
            }
        }
        return null;
    }

    public void viderListe()
    {
        joueurs.clear();
    }
}
