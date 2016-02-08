package com.band.guy.kool.connect4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

/**Classe qui gère l'ìnterface du jeu
 * Created by Michael on 2/5/2016.
 */
public class interfaceJeu extends Activity {

    private Integer[] mThumbIds = {
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,
            R.drawable.videvide,

    };
    Puissance4 puissance4;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        GridView gridview = (GridView) findViewById(R.id.gridFriends);
        gridview.setAdapter(new MonAdaptateur(this));
        gridview.setNumColumns(7);
        Bundle bundle = getIntent().getExtras();
        String modeJeu = bundle.getString("modeJeu");
        puissance4 = new Puissance4(modeJeu);

    }

    /**
     * Méthode qui gère quand le joueur joue
     * @param v vue
     */
    public void jouer(View v)
    {
        if(!puissance4.partieTerminée) {

            int position = puissance4.lireChoixColonne(v);

            if (position < 0) {
                String texte = puissance4.positionDéjàOccupée();

                Toast.makeText(this, texte, Toast.LENGTH_SHORT).show();
                return;
            }
            if (GestionnaireJoueurs.avoirInstance().avoirJoueurActif() == GestionnaireJoueurs.avoirInstance().avoirJoueurAvecNomCourt('r')) {
                mThumbIds[position] = R.drawable.bleu;
            } else if (GestionnaireJoueurs.avoirInstance().avoirJoueurActif() == GestionnaireJoueurs.avoirInstance().avoirJoueurAvecNomCourt('b')) {
                mThumbIds[position] = R.drawable.rouge;
            }

            GridView gv = (GridView) findViewById(R.id.gridFriends);
            System.out.println(v.getTag());
            MonAdaptateur adapteur = new MonAdaptateur(this);
            adapteur.notifyDataSetChanged();
            gv.setAdapter(adapteur);


            if (puissance4.partieTerminée) {
                if (puissance4.aGagne) {
                    Toast.makeText(this, puissance4.aGagné(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, puissance4.grillePleine(), Toast.LENGTH_SHORT).show();
                }
            }
            else if(GestionnaireJoueurs.avoirInstance().avoirJoueurActif().avoirEstIA())
            {
                int positionIA = puissance4.jouerIA();
                if (GestionnaireJoueurs.avoirInstance().avoirJoueurActif() == GestionnaireJoueurs.avoirInstance().avoirJoueurAvecNomCourt('r')) {
                    mThumbIds[positionIA] = R.drawable.bleu;
                } else if (GestionnaireJoueurs.avoirInstance().avoirJoueurActif() == GestionnaireJoueurs.avoirInstance().avoirJoueurAvecNomCourt('b')) {
                    mThumbIds[positionIA] = R.drawable.rouge;
                }

                adapteur.notifyDataSetChanged();
                gv.setAdapter(adapteur);


                if (puissance4.partieTerminée) {
                    if (puissance4.aGagne) {
                        Toast.makeText(this, puissance4.aGagné(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, puissance4.grillePleine(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    /**
     * Classe interne pour grid view personaliser
     */
        public class MonAdaptateur extends BaseAdapter {
        private Context mContext;

        /**
         * Constructeur de la classe
         * @param c
         */
            public MonAdaptateur(Context c) {
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
