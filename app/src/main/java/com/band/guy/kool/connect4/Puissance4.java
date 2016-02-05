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
public class Puissance4 extends Activity implements ÉvènementsPuissance4 {
    TextView grille;
    Puissance4 puissance4;
    private Integer[] mThumbIds = {
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,
            R.drawable.gris,

    };


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        GridView gridview = (GridView) findViewById(R.id.gridFriends);
        gridview.setAdapter(new MonAdapteur(this));
        gridview.setNumColumns(NOMBRE_COLONNES);
                puissance4 = new Puissance4();
    }

    boolean partieTerminée;
    ActionsPuissance4 actionsJeu;

    public final int NOMBRE_LIGNES = 6;
    public final int NOMBRE_COLONNES = 7;

    public Puissance4() {
        actionsJeu = new Jeu(this, Joueur.Noir, NOMBRE_LIGNES, NOMBRE_COLONNES);
        partieTerminée = false;
    }



    private String afficherGrille()
    {
        Joueur joueur;
        String contenu = "";
        
        for(int i  = 0; i < this.NOMBRE_LIGNES; i++) {
            contenu += ("| ");
            for(int j = 0; j < this.NOMBRE_COLONNES; j++) {
                joueur = actionsJeu.avoirJoueur(i,j);

                contenu += "" + this.avoirJoueurCourt(joueur) + (" | ");
            }
            contenu +=("\n");


        }
        return contenu;
    }
    
    public void lireChoixColonne(View v)
    {
        int colonne = (int)v.getTag()%7;
        int ligne = actionsJeu.jouer(colonne);
        if(joueurCourrant == Joueur.Blanc)
        {
            mThumbIds[(colonne+ligne*7)] = R.drawable.bleu2;
        }
        else if(joueurCourrant == Joueur.Noir)
        {
            mThumbIds[(colonne+7*ligne)] = R.drawable.rouge2;
        }
        GridView gv = (GridView)findViewById(R.id.gridFriends);
        System.out.println(v.getTag());
        MonAdapteur adapteur = new MonAdapteur(this);
        adapteur.notifyDataSetChanged();
        gv.setAdapter(adapteur);


    }




    private int jouerColonne(int colonne)
    {
        return actionsJeu.jouer(colonne);

    }

    @Override
    public void aGagné(Joueur joueur) {
        System.out.println("Le joueur " + transformerJoueurEnTexte(joueur) + " a gagné.");
        this.partieTerminée = true;
    }

    @Override
    public void grillePleine() {
        System.out.println("La partie est terminée.");
        this.partieTerminée = true;
    }
    private Joueur joueurCourrant;
    @Override
    public void auTourDe(Joueur joueur) {
        System.out.println("C'est au tour du joueur " + transformerJoueurEnTexte(joueur));
        joueurCourrant = joueur;
    }

    @Override
    public void positionDéjàOccupée(int ligne, int colonne) {
        System.out.println("La position " + ligne + ", " + colonne + " est déjà occupée");
    }

    private String transformerJoueurEnTexte(Joueur joueur) {        
        return joueur == Joueur.Noir ? "Noir" : "Blanc";
    }
    
    private String avoirJoueurCourt(Joueur joueur) {
        if(joueur == Joueur.Aucun)
            return "-";
        else if(joueur == Joueur.Blanc)
            return "B";
        
        return "N";
    }
    public class MonAdapteur extends BaseAdapter {
        private Context mContext;

        public MonAdapteur(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int arg0) {
            return mThumbIds[arg0];
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View grid;

            if (convertView == null) {
                grid = new View(mContext);
                LayoutInflater inflater = getLayoutInflater();
                grid = inflater.inflate(R.layout.grid_items, parent, false);
            } else {
                grid = (View) convertView;
            }

            ImageButton imagebtn = (ImageButton) grid.findViewById(R.id.imgbtnCase);
            imagebtn.setImageResource(mThumbIds[position]);
            imagebtn.setTag(Integer.valueOf(position));

            return grid;
        }
    }
}
