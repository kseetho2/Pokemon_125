package com.example.pokemon125;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.FrameMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Move;
import me.sargunvohra.lib.pokekotlin.model.PokemonMove;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

public class MainActivity extends AppCompatActivity {

    private RadioButton selectedPokemon;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
                               GameActivity gameLogic = new GameActivity("Fluffy");
                               ConstraintLayout lay = findViewById(R.id.playerOptions);
                               setContentView(R.layout.fight_screen);


                               TextView move1 = findViewById(R.id.moveTL);
                               TextView move2 = findViewById(R.id.moveBL);

                               String appName = getResources().getString(R.string.move_3);
                               move1.setText(appName);
                               move1.setText(getResources().getString(R.string.eevee));
                               //Pokemon eevee = new Pokemon("Fluffy", 314, 229, 218, 207, 251, 229);
                               //move1.setText(eevee.getMove1());

                               //if you comment out the stuff below this it'll work still
                               PokeApi pokeApi = new PokeApiClient();
                               Move move5 = pokeApi.getMove(1);
                               String what = move5.getName();
                               move2.setText(what);


                               //String move = eevee.getMove1();


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
