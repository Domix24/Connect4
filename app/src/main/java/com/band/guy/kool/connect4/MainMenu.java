package com.band.guy.kool.connect4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    public void OnClick(View v){
        if(v.getId() == R.id.btnLocal)
        {
            Intent intent = new Intent(this,interfaceJeu.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.btnHote)
        {
            Toast.makeText(MainMenu.this,
                    "Mik est gay!", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.btnRejoindre)
        {

            Toast.makeText(MainMenu.this,
                    "Max est gay!", Toast.LENGTH_SHORT).show();
        }

    }
}
