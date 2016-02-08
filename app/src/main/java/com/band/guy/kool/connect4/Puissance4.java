package com.band.guy.kool.connect4;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.text.InputType;
import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

/**
 *
 * @author Dominic
 */
public class Puissance4 implements IÉvènementsPuissance4 {

    boolean partieTerminée;
    IActionsPuissance4 actionsJeu;
    public boolean aGagne;

    public final int NOMBRE_LIGNES = 6;
    public final int NOMBRE_COLONNES = 7;

    public Puissance4(String modeJeu) {
        GestionnaireJoueurs.avoirInstance().viderListe();
        if(modeJeu.contains("normal")) {
            GestionnaireJoueurs.avoirInstance().ajouterJoueur("Bleu", 'r');
            GestionnaireJoueurs.avoirInstance().ajouterJoueur("Rouge", 'b');
            actionsJeu = new JeuGravité(this, NOMBRE_LIGNES, NOMBRE_COLONNES);
        }
        else if(modeJeu.contains("vsAI"))
        {
            GestionnaireJoueurs.avoirInstance().ajouterJoueur("Bleu", 'r');
            GestionnaireJoueurs.avoirInstance().ajouterOrdinateur("Rouge", 'b');
            actionsJeu = new JeuGravité(this, NOMBRE_LIGNES, NOMBRE_COLONNES);
        }
        else if(modeJeu.contains("antiGravite"))
        {
            GestionnaireJoueurs.avoirInstance().ajouterJoueur("Bleu", 'r');
            GestionnaireJoueurs.avoirInstance().ajouterJoueur("Rouge", 'b');

        }


        GestionnaireJoueurs.avoirInstance().changerJoueurActif('r');
        partieTerminée = false;
        aGagne = false;
    }



    public int jouerIA(){
        return actionsJeu.jouerIA();
    }

    public int lireChoixColonne(View v) {
        int colonne =  ((int)v.getTag()%7);
        int ligne = actionsJeu.jouer(colonne);
        if(ligne < 0)
            return ligne;
        return ligne*7+colonne;
    }



    @Override
    public String aGagné() {
        this.partieTerminée = true;
        this.aGagne = true;
       Joueur joueur = GestionnaireJoueurs.avoirInstance().avoirJoueurActif();
       return ""+ joueur + " a gagné.";

    }

    @Override
    public String grillePleine() {
        this.partieTerminée = true;
        return "La partie est terminée.";

    }

    @Override
    public String auTourDe() {
        Joueur joueur = GestionnaireJoueurs.avoirInstance().avoirJoueurActif();
        return "C'est au tour de: " + joueur;
    }

    @Override
    public String positionDéjàOccupée() {
        return "La position est déjà occupée";
    }
}
