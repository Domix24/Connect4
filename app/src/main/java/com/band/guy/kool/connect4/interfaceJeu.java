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

/**
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

        puissance4 = new Puissance4();

    }

    public void jouer(View v)
    {
        int position = puissance4.lireChoixColonne(v);
        if(position == -1)
        {
            return;
        }
        if(GestionnaireJoueurs.avoirInstance().avoirJoueurActif() == GestionnaireJoueurs.avoirInstance().avoirJoueurAvecNomCourt('N'))
        {
            mThumbIds[position] = R.drawable.bleu;
        }
        else if(GestionnaireJoueurs.avoirInstance().avoirJoueurActif() == GestionnaireJoueurs.avoirInstance().avoirJoueurAvecNomCourt('B'))
        {
            mThumbIds[position] = R.drawable.rouge;
        }

        GridView gv = (GridView)findViewById(R.id.gridFriends);
        System.out.println(v.getTag());
        MonAdaptateur adapteur = new MonAdaptateur(this);
        adapteur.notifyDataSetChanged();
        gv.setAdapter(adapteur);
    }

        public class MonAdaptateur extends BaseAdapter {
            private Context mContext;

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
