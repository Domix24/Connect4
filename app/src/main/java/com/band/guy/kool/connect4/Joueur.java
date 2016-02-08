package com.band.guy.kool.connect4;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *Classe du joueur
 * @author Dominic
 */
public class Joueur {

    private final String nom;
    private final char nomCourt;
    private final boolean estIA;

    public static final Joueur VIDE = new Joueur("VIDE", '-', false);

    /**
     * Constructeur du joueur
     * @param nom Nom
     * @param nomCourt char representant le joueur
     * @param estIA true = ordinateur false = joueur
     */
    public Joueur(String nom, char nomCourt, boolean estIA) {
        this.nom = nom;
        this.nomCourt = nomCourt;
        this.estIA = estIA;
    }

    /**
     * Retourne le Char qui represente le nom
     * @return Char qui represente le nom
     */
    public char avoirNomCourt() {
        return this.nomCourt;
    }

    /**
     * Retourne le nom
     * @return retourne le nom
     */
    public String avoirNom() {
        return this.nom;
    }

    /**
     * Retourne si le joueur actif est un IA
     * @return bool
     */
    public boolean avoirEstIA() {
        return this.estIA;
    }

    @Override
    public String toString() {
        return "(" + this.nomCourt + ") " + this.nom;
    }

}