package com.example.pokemon125;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.FrameMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton selectedPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout mainScreen = findViewById(R.id.mainScreenTouch);
        mainScreen.setOnClickListener(new View.OnClickListener() { //tap anywhere on frame
            public void onClick(View v) {
                setContentView(R.layout.exposition); //enter exposition
                Button enterTeamSelect = findViewById(R.id.enterTeamSelect);
                enterTeamSelect.setOnClickListener(new View.OnClickListener() { //tap button
                    public void onClick(View v) {
                        setContentView(R.layout.switch_pokemon);

                        Button enterFightScene = findViewById(R.id.enterFightScreen);
                        enterFightScene.setOnClickListener(new View.OnClickListener() {
                           public void onClick(View v) {
                               setContentView(findViewById(R.id.exposition));
                           }
                        });
                    }
                });
            }
        });
        /*
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         */
    }

    public void pokemonClicked(View v) {
        RadioGroup radioGroup = findViewById(R.id.userPokemonList);
        int radioId = radioGroup.getCheckedRadioButtonId();

        selectedPokemon = findViewById(radioId);

        Toast.makeText(this, "Selected Pokemon: " + selectedPokemon.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
