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
public interface ÉvènementsPuissance4 {
    public void aGagné(Joueur joueur);
    public void grillePleine();
    public void auTourDe(Joueur joueur);
    public void positionDéjàOccupée(int ligne, int colonne);
}
