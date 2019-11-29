package com.example.pokemon125;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Move;
import me.sargunvohra.lib.pokekotlin.model.PokeathlonStat;
import me.sargunvohra.lib.pokekotlin.model.PokemonMove;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

import static com.android.volley.Request.Method.GET;


public class MainActivity extends AppCompatActivity {

    private RadioButton selectedPokemon;
    //private PokeApi pokeAPI;
    //private JSONObject data;

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
                               RadioGroup radioGroup = findViewById(R.id.userPokemonList);
                               int radioId = radioGroup.getCheckedRadioButtonId();
                               selectedPokemon = findViewById(radioId);

                               GameActivity gameLogic = new GameActivity((String) selectedPokemon.getText());

                               setContentView(R.layout.fight_screen);
                               TextView move1 = findViewById(R.id.moveTL);
                               TextView move2 = findViewById(R.id.moveBL);

                               String appName = getResources().getString(R.string.move_3);
                               move1.setText(appName);
                               move1.setText(getResources().getString(R.string.eevee));

                               PokeApi pokeApi = new PokeApiClient();
                               Pokemon eevee = new Pokemon("Fluffy", 314, 229, 218, 207, 251, 229);
                               move2.setText(pokeApi.getMove(247).getName());


                               //gameLogic.initialSetup();


                               /*
                               connect();
                               try {
                                   String url = data.get("pound").toString();
                               } catch (JSONException e) {
                                   Toast.makeText(getApplicationContext(), "Oh no!", Toast.LENGTH_LONG).show();
                               }
                               move2.setText(gameLogic.getMoveName(10));

                                */
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

    /*
    private void connect() {
        String url = "https://pokeapi.co/api/v2/move"; //?limit=746

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        data = response;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Oh no!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public class TestThread implements Runnable {
        public void run() {
            PokeApi poke = new PokeApiClient();
            poke.getMove(10);
        }

    }
    private static class GetAPITask extends AsyncTask<String, Integer, String> {

        private final PokeApi pokeApi;

        GetAPITask(PokeApi api) {
            this.pokeApi = api;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... params) {
            try {
                pokeApi.getMoveList(0,746);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "API Created";
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
     */

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
