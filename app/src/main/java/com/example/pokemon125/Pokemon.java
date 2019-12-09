package com.example.pokemon125;


import android.util.Log;

import androidx.annotation.MainThread;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




/**
 * this class will be used to set up the pokemon that we will premake based on responses from the TA/CA's
 * if we don't have enough we will come up with our own.
 */
public class Pokemon extends MainActivity{
    private int id;
    //Pokemon Related Variables
    private String name;
    private int speed;
    private int totalHealth;
    private int currentHealth;
    private int attack;
    private int specialAttack;
    private int defense;
    private int specialDefense;
    //Pokemon Move Related Variables
    private int moveOneId;
    private int moveOnePower;
    private int moveOneAcc;
    private int moveOnePP;


    private int moveTwoId;
    private int moveTwoPower;
    private int moveTwoAcc;
    private int moveTwoPP;

    private int moveThreeId;
    private int moveThreePower;
    private int moveThreeAcc;
    private int moveThreePP;

    private int moveFourId;
    private int moveFourPower;
    private int moveFourAcc;
    private int moveFourPP;

    private JSONObject move1Object;
    private JSONObject move2Object;
    private JSONObject move3Object;
    private JSONObject move4Object;
    private JSONObject dataFromVollyRq;


    /**
     * Constructor for the Pokemon.
     * @param input Pokemon Name
     * @param healthInput Pokemon HP
     * @param attackInput Pokemon ATK
     * @param spAttackInput Pokemon Special ATK
     * @param defenseInput Pokemon DEF
     * @param spDefenseInput Pokemon Special DEF
     * @param speedInput Pokemon SPD
     */
    Pokemon(String input, int healthInput, int attackInput, int spAttackInput,
            int defenseInput, int spDefenseInput, int speedInput) {
        speed = speedInput;
        currentHealth = healthInput;
        totalHealth = healthInput;
        attack = attackInput;
        specialAttack = spAttackInput;
        defense = defenseInput;
        specialDefense = spDefenseInput;
        name = input;
        if (input.equals("Voltorb")) {
            moveOneId = 434; //Discharge 435
            moveTwoId = 152; // Explosion 153
            moveThreeId = 204; // Roll-out 205
            moveFourId = 128; // Swift 129
        } else if (input.equals("Garchomp")) {
            moveOneId = 406; //Dragon-Rush 407
            moveTwoId = 241; //Crunch 242
            moveThreeId = 125; //Fire Blast 126
            moveFourId = 433; //Draco-Meteor 434
        } else if (input.equals("Fluffy")) { // Eevee
            moveOneId = 246; //Shadow-Ball 247
            moveTwoId = 129; //Skull-Bash 130
            moveThreeId = 43; //Bite 44
            moveFourId = 62; //Hyper-Beam 63
        } else if (input.equals("Naruto")) { //Greninja
            moveOneId = 415; //Giga-Impact 416
            moveTwoId = 55; //Hydro-Pump 56
            moveThreeId = 399; //Night-Slash 400
            moveFourId = 57; //Ice-Beam 58
        } else if (input.equals("Rimacu")) { //Riolu
            moveOneId = 397; //Poison-Jab 398
            moveTwoId = 156; //Rock-Slide 157
            moveThreeId = 410; //Focus-Blast 411
            moveFourId = 88; //Earthquake 89
        } else if (input.equals("Torterra")) {
            moveOneId = 275; // Superpower 276
            moveTwoId = 33; //Body-slam 34
            moveThreeId = 337; // Frenzy plant 338
            moveFourId = 441; // Iron head 442
        }
    }
    public void Pokemon(int number, int move1, int move2, int move3, int move4) {

    }
    public String getMove1() {
        return findMove(moveOneId); }
    public String getMove2() {
        return findMove(moveTwoId);
    }
    public String getMove3() {
        return findMove(moveThreeId);
    }
    public String getMove4() {
        return findMove(moveFourId);
    }

    public void setCurrentHealth(int input) { currentHealth = input; }
    public int getCurrentHealth() { return currentHealth; }
    public int getTotalHealth() { return totalHealth; }
    public int getAttack() { return attack; }
    public int getSpecialAttack() { return specialAttack; }
    public int getDefense() { return defense; }
    public int getSpecialDefense() { return specialDefense; }
    public int getSpeed() { return speed; }
    public String getName() { return name; }

    public String findMove(int input) {
        JSONArray array;
        try {
            array = dataFromVollyRq.getJSONArray("results");
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
    public void getMoveData() {
        final String move1URL = "https://pokeapi.co/api/v2/move" + moveOneId;
        final String move2URL = "https://pokeapi.co/api/v2/move" + moveTwoId;
        String move3URL = "https://pokeapi.co/api/v2/move" + moveThreeId;
        String move4URL = "https://pokeapi.co/api/v2/move" + moveFourId;
        JsonObjectRequest movedata1 = new JsonObjectRequest
                (Request.Method.GET, move1URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            moveOnePower = response.getInt("power");
                            moveOneAcc = response.getInt("accuracy");
                            moveOnePP = response.getInt("pp");
                        } catch (Exception e) {
                            Log.e("Poke 125", "move 1 data request failed");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("NO Wifi or Did not finish in Time");

                    }
                });
        JsonObjectRequest movedata2 = new JsonObjectRequest
                (Request.Method.GET, move2URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                         try {
                             moveTwoPower = response.getInt("power");
                             moveTwoAcc = response.getInt("accuracy");
                             moveTwoPP = response.getInt("pp");
                         } catch (Exception e) {
                             Log.e("Poke 125", "move 2 data request failed");
                         }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("NO Wifi or Did not finish in Time");

                    }
                });
        JsonObjectRequest movedata3 = new JsonObjectRequest
                (Request.Method.GET, move3URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            moveThreePower = response.getInt("power");
                            moveThreeAcc = response.getInt("accuracy");
                            moveThreePP = response.getInt("pp");
                        } catch (Exception e) {
                            Log.e("Poke 125", "move 3 data request failed");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("NO Wifi or Did not finish in Time");

                    }
                });
        JsonObjectRequest movedata4 = new JsonObjectRequest
                (Request.Method.GET, move4URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            moveFourPower = response.getInt("power");
                            moveFourAcc = response.getInt("accuracy");
                            moveFourPP = response.getInt("pp");
                        } catch (Exception e) {
                            Log.e("Poke 125", "move 4 data request failed");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("NO Wifi or Did not finish in Time");

                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(movedata1);
        queue.add(movedata2);
        queue.add(movedata3);
        queue.add(movedata4);
    }
}
