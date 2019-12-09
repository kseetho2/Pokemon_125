package com.example.pokemon125;

import android.content.Intent;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

import org.json.JSONArray;

import org.json.JSONObject;



import me.sargunvohra.lib.pokekotlin.client.PokeApi;


public class MainActivity extends AppCompatActivity {

    private RadioButton selectedPokemon;
    private PokeApi pokeAPI;
    private JSONObject object;
    private JSONArray array;
    private boolean dataReceived = false;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        FrameLayout mainScreen = findViewById(R.id.mainScreenTouch);
        String url = "https://pokeapi.co/api/v2/move?offset=0&limit=700";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    array = response.getJSONArray("results");
                } catch (Exception e) {
                    Log.e("I wanna", "die");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
        queue.add(stringRequest);
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
                               String test = selectedPokemon.getText().toString();
                               //GameActivity gameLogic = new GameActivity();

                               //PokeApi pokeApi = new PokeApiClient();
                               Pokemon eevee = new Pokemon("Fluffy", 314, 229, 218, 207, 251, 229);
                               //connect();
                               //move2.setText(pokeAPI.getMove(247).getName());

                               //String url = "https://pokeapi.co/api/v2/move?offset=0&limit=700";


                               setContentView(R.layout.fight_screen);
                               Button moveTL = findViewById(R.id.moveTL);
                               Button moveTR = findViewById(R.id.moveTR);
                               Button moveBL = findViewById(R.id.moveBL);
                               Button moveBR = findViewById(R.id.moveBR);
                               //moveTL.setVisibility(View.VISIBLE);
                               //moveTR.setVisibility(View.VISIBLE);
                               //moveBL.setVisibility(View.VISIBLE);
                               //moveBR.setVisibility(View.VISIBLE);
                               Example data = new Example();
                               data.setmoveArray(array);
                               //int[] one = data.moveList(selectedPokemon.getText().toString());
                               //moveTL.setText(data.findMove(one[0]));
                               //moveTR.setText(data.findMove(one[1]));
                               //moveBL.setText(data.findMove(one[2]));
                               //moveBR.setText(data.findMove(one[3]));
                               //moveTL.setText(test.findMove(246));
                               //moveTR.setText(test.findMove(129));
                               //moveBL.setText(test.findMove(43));
                               //moveBR.setText(test.findMove(62));

                               //test.setMoveText(test.findMove(246), test.findMove(129), test.findMove(43), test.findMove(62));
                               /*
                               try {
                                   array = object.getJSONArray("results");
                                   move1 = array.getString(0);
                               } catch (JSONException e) {
                                   Log.e("MYApp", "didn't work", e);
                               }
                               TextView moveTL = findViewById(R.id.moveTL);
                               moveTL.setText(move1);
                                */

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
