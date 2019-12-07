package com.example.pokemon125;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

public class Example extends AppCompatActivity {
    private static JSONArray array;
    Example(JSONArray input) {
        try {
            array = input;
        } catch (Exception e) {
            System.out.println("did not work");
        }
        if (array != null) {

        }
    }
    public String findMove(int id) {
        String move = "move not found";
        try {
            move = array.getJSONObject(id).getString("name");
        } catch (Exception e) {
            System.out.println("couldn't find move");
        }
        return move;
    }
    public void setMoveText(String move1, String move2, String move3, String move4) {
        setContentView(R.layout.fight_screen);
        Button moveTL = findViewById(R.id.moveTL);
        Button moveTR = findViewById(R.id.moveTR);
        Button moveBL = findViewById(R.id.moveBL);
        Button moveBR = findViewById(R.id.moveBR);
        moveTL.setText(move1);
        moveTR.setText(move2);
        moveBL.setText(move3);
        moveBR.setText(move4);
    }
}