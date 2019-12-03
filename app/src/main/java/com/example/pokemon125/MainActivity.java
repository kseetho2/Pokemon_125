package com.example.pokemon125;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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
    private PokeApi pokeAPI;
    private JSONObject object;
    //private JSONObject data;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue queue = Volley.newRequestQueue(this);
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

                               //GameActivity gameLogic = new GameActivity();

                               //PokeApi pokeApi = new PokeApiClient();
                               Pokemon eevee = new Pokemon("Fluffy", 314, 229, 218, 207, 251, 229);
                               //connect();
                               //move2.setText(pokeAPI.getMove(247).getName());

                               String url = "https://pokeapi.co/api/v2/move/1";
                               JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,  new Response.Listener<JSONObject>() {
                                   public void onResponse(JSONObject response) {
                                       object = response;
                                   }
                               }, new Response.ErrorListener() {
                                   public void onErrorResponse(VolleyError error) {
                                       System.out.println("That didn't work!");
                                   }
                               });
                               queue.add(stringRequest);

                               Intent newIntent = new Intent(getApplicationContext(), GameActivity.class);
                               newIntent.putExtra("selectedPokemon", selectedPokemon.getText());
                               startActivity(newIntent);

                               /*
                               try {
                                   int acc = (int) object.get("accuracy");
                                   System.out.println(acc);

                               } catch (JSONException e) {
                                   //some exception handler code.
                                   System.out.println("it didn't work :(");
                               }
                                */

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
