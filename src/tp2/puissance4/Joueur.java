/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.puissance4;

/**
 *
 * @author Dominic
 */
public class Joueur {

    private final String nom;
    private final char nomCourt;
    private final boolean estIA;
    
    public static final Joueur VIDE = new Joueur("VIDE", '-', false);

    public Joueur(String nom, char nomCourt, boolean estIA) {
        this.nom = nom;
        this.nomCourt = nomCourt;
        this.estIA = estIA;
    }

    public char avoirNomCourt() {
        return this.nomCourt;
    }

    public String avoirNom() {
        return this.nom;
    }
    
    public boolean avoirEstIA() {
        return this.estIA;
    }

    @Override
    public String toString() {
        return "(" + this.nomCourt + ") " + this.nom;
    }

}
