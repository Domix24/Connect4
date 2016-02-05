/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.puissance4;

import java.awt.Point;

/**
 *
 * @author Dominic
 */
public class JeuGravité extends Jeu implements IActionsPuissance4 {
    
    IntelligenceArtificielle ia;
    
    public JeuGravité(IÉvènementsPuissance4 évènementsJeu, int nombreLignes, int nombreColonnes) {
        super(évènementsJeu, nombreLignes, nombreColonnes);
        
        
        ia = new IntelligenceArtificielle(nombreColonnes,nombreLignes);
    }

    @Override
    public void jouer(int colonne) {
        int ligne = 0;
        
        if(this.avoirJoueur(ligne, colonne) != null) {
            this.évènementsJeu.positionDéjàOccupée(ligne, colonne);
            return;
        }
        
        for(int i = ligne + 1; i < this.nombreLignes; i++) {
            ligne = this.avoirJoueur(i, colonne) == null ? i : ligne;
        }
        
        this.modifierCase(ligne, colonne, GestionnaireJoueurs.avoirInstance().avoirJoueurActif());
        this.nombreEssais++;
        
        if(this.estGagnant(ligne, colonne)) {
            this.évènementsJeu.aGagné(GestionnaireJoueurs.avoirInstance().avoirJoueurActif());
        }
        else if(this.estPartieNulle()) {
            this.évènementsJeu.grillePleine();
        }
        else {
            Joueur dernierJoueur = GestionnaireJoueurs.avoirInstance().avoirJoueurActif();
            
            GestionnaireJoueurs.avoirInstance().prochainJoueur();
            
            if(GestionnaireJoueurs.avoirInstance().avoirJoueurActif().avoirEstIA()) {
                Point position = ia.jouer(cases, dernierJoueur);
                jouer(position.y);
                return;
            }
            
            this.évènementsJeu.auTourDe(GestionnaireJoueurs.avoirInstance().avoirJoueurActif());
        }
    }
    
    private boolean vérifierVictoireVerticale(int colonne) {
        int compteur = 0;
        Joueur derniereCase = null;

        for (int i = 0; i < this.nombreLignes && compteur < 4; i++) {
            if (this.avoirJoueur(i, colonne) == derniereCase && derniereCase != null) {
                compteur++;
            } else {
                compteur = 1;
            }

            derniereCase = this.avoirJoueur(i, colonne);
        }

        return compteur == 4;
    }
    
    private boolean vérifierVictoireHorizontale(int ligne) {
        int compteur = 0;
        Joueur derniereCase = null;

        for (int i = 0; i < this.nombreColonnes && compteur < 4; i++) {
            if (this.avoirJoueur(ligne, i) == derniereCase && derniereCase != null) {
                compteur++;
            } else {
                compteur = 1;
            }

            derniereCase = this.avoirJoueur(ligne, i);
        }

        return compteur == 4;
    }
    
    private boolean vérifierVictoireDiagonale(int ligne, int colonne) {
        int compteur1 = 0;
        int compteur2 = 0;
        for (int i = -3; i < 4; i++) {
            if (avoirJoueur(ligne + i, colonne + i) == GestionnaireJoueurs.avoirInstance().avoirJoueurActif()) {
                compteur1++;
            } else {
                compteur1 = 0;
            }

            if (avoirJoueur(ligne + i, colonne - i) == GestionnaireJoueurs.avoirInstance().avoirJoueurActif()) {
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

    @Override
    protected boolean estGagnant(int ligne, int colonne) {
        return vérifierVictoireVerticale(colonne) || vérifierVictoireHorizontale(ligne) || vérifierVictoireDiagonale(ligne,colonne);
    }
    
}
