package com.band.guy.kool.connect4;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/** Classe qui gère les joueurs
 *
 * @author Dominic
 */
public class GestionnaireJoueurs {

    private static GestionnaireJoueurs instance = null;

    private int joueurActif;
    
    private final ArrayList<Joueur> joueurs;

    /**
     * Constructeur de la classe
     */
    protected GestionnaireJoueurs() {
        joueurs = new ArrayList<>();
    }
    /*
     * Retourne l'instance ou crée une instance si null
     */
    public static GestionnaireJoueurs avoirInstance() {
        if (instance == null) {
            instance = new GestionnaireJoueurs();
        }

        return instance;
    }
    /*
     Ajoute un joueur
     */
    public void ajouterJoueur(String nom, char nomCourt) {
        if(avoirJoueurAvecNomCourt(nomCourt) == null) {
            joueurs.add(new Joueur(nom, nomCourt, false));
        }
    }
    /*
    Ajoute un ordinateur
     */
    public void ajouterOrdinateur(String nom, char nomCourt) {
        if(avoirJoueurAvecNomCourt(nomCourt) == null) {
            joueurs.add(new Joueur(nom, nomCourt, true));
        }
    }
    /*
    Retourne le nombre de joueur courrant
     */
    public int nombreJoueurs() {
        return this.joueurs.size();
    }
    /*
    Retourne le joueur suivant
     */
    public void prochainJoueur() {
        if(++this.joueurActif >= this.joueurs.size()) {
            this.joueurActif = 0;
        }
    }
    /*
    Retourne le joueur actif
     */
    public Joueur avoirJoueurActif() {
        return joueurs.get(this.joueurActif);
    }
    /*
    Change le joueur actif pour le prochain
     */
    public void changerJoueurActif(char nomCourt) {
        for(int i = 0; i < this.joueurs.size(); i++) {
            if(this.joueurs.get(i).avoirNomCourt() == nomCourt) {
                this.joueurActif = i;
            }
        }
    }
    /*
    Retourne le nom du joueur selon le char associer
     */
    public Joueur avoirJoueurAvecNomCourt(char nomCourt) {
        for(Joueur joueur : this.joueurs) {
            if(nomCourt == joueur.avoirNomCourt()) {
                return joueur;
            }
        }
        return null;
    }
    /*
    Vide la liste
     */
    public void viderListe()
    {
        joueurs.clear();
    }
}
