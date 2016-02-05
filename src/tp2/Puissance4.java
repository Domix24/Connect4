/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import tp2.puissance4.Joueur;
import java.util.Scanner;
import tp2.puissance4.GestionnaireJoueurs;
import tp2.puissance4.IActionsPuissance4;
import tp2.puissance4.IÉvènementsPuissance4;
import tp2.puissance4.JeuGravité;

/**
 *
 * @author Dominic
 */
public class Puissance4 implements IÉvènementsPuissance4 {

    boolean partieTerminée;
    IActionsPuissance4 actionsJeu;

    public final int NOMBRE_LIGNES = 6;
    public final int NOMBRE_COLONNES = 7;

    private Puissance4() {   
        GestionnaireJoueurs.avoirInstance().ajouterJoueur("Rouge", 'r');
        GestionnaireJoueurs.avoirInstance().ajouterOrdinateur("Bleu", 'b');
        
        GestionnaireJoueurs.avoirInstance().changerJoueurActif('r');
        
        actionsJeu = new JeuGravité(this, NOMBRE_LIGNES, NOMBRE_COLONNES);
        
        partieTerminée = false;
    }

    public static void main(String[] args) {
        Puissance4 puissance4 = new Puissance4();

        do {
            puissance4.afficherGrille();
            puissance4.jouerColonne(puissance4.lireChoixColonne());
        } while (!puissance4.partieTerminée);
        
        puissance4.afficherGrille();
    }

    private void afficherGrille() {
        Joueur joueur;
        String contenu;
        
        for(int i  = 0; i < this.NOMBRE_LIGNES; i++) {
            System.out.print("|");
            for(int j = 0; j < this.NOMBRE_COLONNES; j++) {
                joueur = actionsJeu.avoirJoueur(i,j);
                contenu = ""+ (joueur == null ? "-" : joueur.avoirNomCourt());
                System.out.print(" "+contenu + " |");
            }
            System.out.println();
        }
    }
    
    private int lireChoixColonne() {
        int choix = 0;
        
        do
        {
            try {
                Scanner clavier = new Scanner(System.in);
                System.out.print("Entrez un chiffre, puis ENTER pour jouer: ");
                choix = clavier.nextInt();
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        } while(choix < 1 || choix > NOMBRE_COLONNES);
        
        return choix;
    }
    
    private void jouerColonne(int colonne) {
        actionsJeu.jouer(colonne - 1);
    }

    @Override
    public void aGagné(Joueur joueur) {
        System.out.println(joueur + " a gagné.");
        this.partieTerminée = true;
    }

    @Override
    public void grillePleine() {
        System.out.println("La partie est terminée.");
        this.partieTerminée = true;
    }

    @Override
    public void auTourDe(Joueur joueur) {
        System.out.println("C'est au tour de: " + joueur);
    }

    @Override
    public void positionDéjàOccupée(int ligne, int colonne) {
        System.out.println("La position " + ligne + ", " + colonne + " est déjà occupée");
    }
}
