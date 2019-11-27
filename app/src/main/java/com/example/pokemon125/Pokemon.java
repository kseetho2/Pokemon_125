package com.example.pokemon125;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.PokemonMove;


/**
 * this class will be used to set up the pokemon that we will premake based on responses from the TA/CA's
 * if we don't have enough we will come up with our own.
 */
public class Pokemon {
    private int id;
    private int speed;
    private int health;
    private int moveOneId;
    private int moveTwoId;
    private int moveThreeId;
    private int moveFourId;
    private Pokemon one;
    private JSONObject data;
    private String pokemon;
    Pokemon(String input) {
        pokemon = input;
        if (input.equals("Voltorb")) {
            moveOneId = 435; //Discharge 435
            moveTwoId = 153; // Explosion 153
            moveThreeId = 205; // Roll-out 205
            moveFourId = 129; // Swift 129
        } else if (input.equals("Garchomp")) {
            moveOneId = 407; //Dragon-Rush 407
            moveTwoId = 242; //Crunch 242
            moveThreeId = 126; //Fire Blast 126
            moveFourId = 434; //Draco-Meteor 434
        } else if (input.equals("Fluffy")) { // Eevee
            moveOneId = 247; //Shadow-Ball 247
            moveTwoId = 130; //Skull-Bash 130
            moveThreeId = 44; //Bite 44
            moveFourId = 63; //Hyper-Beam 63
        } else if (input.equals("Naruto")) { //Greninja
            moveOneId = 416; //Giga-Impact 416
            moveTwoId = 56; //Hydro-Pump 56
            moveThreeId = 400; //Night-Slash 400
            moveFourId = 58; //Ice-Beam 58
        } else if (input.equals("Rimacu")) { //Riolu
            moveOneId = 398; //Poison-Jab 398
            moveTwoId = 157; //Rock-Slide 157
            moveThreeId = 411; //Focus-Blast 411
            moveFourId = 89; //Earthquake 89
        }
    }
    public void Pokemon(int number, int move1, int move2, int move3, int move4) {

    }
    public String getMove1() {
        return findMove(moveOneId);

    }
    public String getMove2() {
        return findMove(moveTwoId);
    }
    public String getMove3() {
        return findMove(moveThreeId);
    }
    public String getMoveFourId() {
        return findMove(moveFourId);
    }
    public String findMove(int input) {
        JSONArray array;
        try {
            array = data.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                if (i == input) {
                    JSONArray x = array.getJSONArray(input);
                    String name = x.getString(0);
                    return name;
                }
            }
        } catch (JSONException e) {
            Log.e("MYApp", "unexpected JSON exception", e);
        }
        return "Move";
    }
    public void getData() {
        String url = "https://pokeapi.co/api/v2/move";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        data = response;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
    }
}
