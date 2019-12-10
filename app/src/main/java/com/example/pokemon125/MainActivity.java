package com.example.pokemon125;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
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
    private boolean musicPlaying = true;
    MediaPlayer mp;
    MediaPlayer clickeffect;
    SoundPool click;
    private boolean alreadyStopped = false;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        FrameLayout mainScreen = findViewById(R.id.mainScreenTouch);
        mp = MediaPlayer.create(this, R.raw.opening_demo);
        mp.start();
        click = new SoundPool(1, 3, 0);
        final int value = click.load(this, R.raw.pressing_a, 1);
        clickeffect = MediaPlayer.create(this, R.raw.pressing_a);
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
        Pokemon rayquaza = new Pokemon("MACHINE PROJECT 0", 414, 504, 328, 504, 328, 361);
        Pokemon porygon = new Pokemon("MACHINE PROJECT 1", 334, 240, 262, 295, 273, 196);
        Pokemon entei = new Pokemon("MACHINE PROJECT 2", 434, 361, 295, 306, 273, 328);
        Pokemon mrMime = new Pokemon("MACHINE PROJECT 3", 284, 207, 251, 328, 372, 306);
        Pokemon dialga = new Pokemon("MACHINE PROJECT 4", 404, 372, 372, 438, 328, 306);
        Pokemon mewTwo = new Pokemon("FINAL PROJECT", 416, 350, 306, 447, 306, 394);
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
        queue.add(rayquaza.getMoveData1());
        queue.add(rayquaza.getMoveData2());
        queue.add(rayquaza.getMoveData3());
        queue.add(rayquaza.getMoveData4());
        //
        queue.add(porygon.getMoveData1());
        queue.add(porygon.getMoveData2());
        queue.add(porygon.getMoveData3());
        queue.add(porygon.getMoveData4());
        //
        queue.add(entei.getMoveData1());
        queue.add(entei.getMoveData2());
        queue.add(entei.getMoveData3());
        queue.add(entei.getMoveData4());
        //
        queue.add(mrMime.getMoveData1());
        queue.add(mrMime.getMoveData2());
        queue.add(mrMime.getMoveData3());
        queue.add(mrMime.getMoveData4());
        //
        queue.add(dialga.getMoveData1());
        queue.add(dialga.getMoveData2());
        queue.add(dialga.getMoveData3());
        queue.add(dialga.getMoveData4());
        //
        queue.add(mewTwo.getMoveData1());
        queue.add(mewTwo.getMoveData2());
        queue.add(mewTwo.getMoveData3());
        queue.add(mewTwo.getMoveData4());
        //
        queue.add(stringRequest);
        mainScreen.setOnClickListener(new View.OnClickListener() { //tap anywhere on frame
            public void onClick(View v) {
                click.play(value, 1, 1, 1, 0, 1);
                setContentView(R.layout.exposition); //enter exposition
                mp.stop() ;
                alreadyStopped = true;
                Button enterTeamSelect = findViewById(R.id.enterTeamSelect);
                enterTeamSelect.setOnClickListener(new View.OnClickListener() { //tap button
                    public void onClick(View v) {
                        click.play(value, 1, 1, 1, 0, 1);
                        setContentView(R.layout.switch_pokemon);
                        Button enterFightScene = findViewById(R.id.enterFightScreen);
                        enterFightScene.setOnClickListener(new View.OnClickListener() {
                           public void onClick(View v) {
                               RadioGroup radioGroup = findViewById(R.id.userPokemonList);
                               int radioId = radioGroup.getCheckedRadioButtonId();
                               selectedPokemon = findViewById(radioId);
                               Example data = new Example();
                               data.setmoveArray(array);
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

    @Override
    protected void onStart() {
        super.onStart();
        if(!mp.isPlaying()) {
            mp.setLooping(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (alreadyStopped) {
            mp.stop();
            musicPlaying = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!musicPlaying) {
            mp.start();
        }
    }
}
