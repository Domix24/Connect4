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

    public final int NOMBRE_LIGNES = 6;
    public final int NOMBRE_COLONNES = 7;

    public Puissance4() {
        GestionnaireJoueurs.avoirInstance().ajouterJoueur("Rouge", 'r');
        GestionnaireJoueurs.avoirInstance().ajouterJoueur("Bleu", 'b');

        GestionnaireJoueurs.avoirInstance().changerJoueurActif('r');

        actionsJeu = new JeuGravité(this, NOMBRE_LIGNES, NOMBRE_COLONNES);

        partieTerminée = false;
    }





    public int lireChoixColonne(View v) {
        int colonne =  ((int)v.getTag()/7);
        int ligne = actionsJeu.jouer(colonne);
        return ligne*7+colonne;
    }


    @Override
    public String aGagné(Joueur joueur) {
        this.partieTerminée = true;
       return ""+ joueur + " a gagné.";

    }

    @Override
    public String grillePleine() {
        this.partieTerminée = true;
        return "La partie est terminée.";

    }

    @Override
    public String auTourDe(Joueur joueur) {
        return "C'est au tour de: " + joueur;
    }

    @Override
    public String positionDéjàOccupée(int ligne, int colonne) {
        return "La position " + ligne + ", " + colonne + " est déjà occupée";
    }
}
