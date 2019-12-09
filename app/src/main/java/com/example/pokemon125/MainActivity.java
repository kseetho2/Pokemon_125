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
        //
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
        //
        Pokemon eevee = new Pokemon("FLUFFY", 314, 229, 218, 207, 251, 229);
        Pokemon garchomp = new Pokemon("GARCHOMP", 420, 482, 361, 372, 317, 311);
        Pokemon greninja = new Pokemon("NARUTO", 348, 317, 256, 335, 265, 377);
        Pokemon voltorb = new Pokemon("VOLTORB", 284, 174, 218, 229, 229, 328);
        Pokemon riolu = new Pokemon("RAMICU", 284, 262, 196, 185, 196, 240);
        Pokemon torterra = new Pokemon("TORTERRA", 394, 348, 273, 339, 295, 232);
        //
        queue.add(eevee.getMoveData1());
        queue.add(eevee.getMoveData2());
        queue.add(eevee.getMoveData3());
        queue.add(eevee.getMoveData4());
        // change

        queue.add(garchomp.getMoveData1());
        queue.add(garchomp.getMoveData2());
        queue.add(garchomp.getMoveData3());
        queue.add(garchomp.getMoveData4());
        //
        queue.add(greninja.getMoveData1());
        queue.add(greninja.getMoveData2());
        queue.add(greninja.getMoveData3());
        queue.add(greninja.getMoveData4());
        //
        queue.add(voltorb.getMoveData1());
        queue.add(voltorb.getMoveData2());
        queue.add(voltorb.getMoveData3());
        queue.add(voltorb.getMoveData4());
        //
        queue.add(riolu.getMoveData1());
        queue.add(riolu.getMoveData2());
        queue.add(riolu.getMoveData3());
        queue.add(riolu.getMoveData4());
        //
        queue.add(torterra.getMoveData1());
        queue.add(torterra.getMoveData2());
        queue.add(torterra.getMoveData3());
        queue.add(torterra.getMoveData4());
        //
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



                               Intent newIntent = new Intent(getApplicationContext(), GameActivity.class);
                               newIntent.putExtra("selectedPokemon", selectedPokemon.getText());
                               startActivity(newIntent);




                           }
                        });
                    }
                });
            }
        });

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

        //noinspection SimplifiableIfStatemen
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
