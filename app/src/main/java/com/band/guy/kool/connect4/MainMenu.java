package com.band.guy.kool.connect4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Classe qui gère l'interface du menu principal
 */
public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    /**
     * Méthode qui gère l'évènement on click
     * @param v view
     */
    public void OnClick(View v){
        if(v.getId() == R.id.btnLocal)
        {
            Intent intent = new Intent(this,interfaceJeu.class);
            intent.putExtra("modeJeu","normal");
            startActivity(intent);
        }
        else if(v.getId() == R.id.btnHote)
        {
            Intent intent = new Intent(this,interfaceJeu.class);
            intent.putExtra("modeJeu","vsAI");
            startActivity(intent);
        }
        else if(v.getId() == R.id.btnRejoindre)
        {
            Intent intent = new Intent(this,interfaceJeu.class);
            intent.putExtra("modeJeu","antiGravite");
            startActivity(intent);
        }

    }
}
