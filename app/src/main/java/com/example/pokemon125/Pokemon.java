package com.example.pokemon125;

import android.util.Log;
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
public class Pokemon {
    private int id;
    private static boolean FluffyCheck = false;
    private static boolean NarutoCheck = false;
    private static boolean VoltorbCheck = false;
    private static boolean RamicuCheck = false;
    private static boolean TorterraCheck = false;
    private static boolean GarchompCheck = false;
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

    private static JSONObject pokeObject = new JSONObject();
    private static boolean initialSetup = false;
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
        if (input.equals("VOLTORB")) {
            moveOneId = 435; //Discharge 435 good
            moveTwoId = 153; // Explosion 153 good
            moveThreeId = 205; // Roll-out 205 good
            moveFourId = 129; // Swift 129 good
        } else if (input.equals("GARCHOMP")) {
            moveOneId = 407; //Dragon-Rush 407
            moveTwoId = 242; //Crunch 242
            moveThreeId = 126; //Fire Blast 126
            moveFourId = 434; //Draco-Meteor 434
        } else if (input.equals("FLUFFY")) { // Eevee
            moveOneId = 247; //Shadow-Ball 247
            moveTwoId = 130; //Skull-Bash 130
            moveThreeId = 44; //Bite 44
            moveFourId = 63; //Hyper-Beam 63
        } else if (input.equals("NARUTO")) { //Greninja
            moveOneId = 416; //Giga-Impact 416
            moveTwoId = 56; //Hydro-Pump 56
            moveThreeId = 400; //Night-Slash 400
            moveFourId = 58; //Ice-Beam 58
        } else if (input.equals("RAMICU")) { //Riolu
            moveOneId = 398; //Poison-Jab 398
            moveTwoId = 157; //Rock-Slide 157
            moveThreeId = 411; //Focus-Blast 411
            moveFourId = 89; //Earthquake 89
        } else if (input.equals("TORTERRA")) {
            moveOneId = 276; // Superpower 276
            moveTwoId = 34; //Body-slam 34
            moveThreeId = 338; // Frenzy plant 338
            moveFourId = 442; // Iron head 442
        }
        if (initialSetup) {
            JSONArray myArray = new JSONArray();
            try {
                pokeObject.put("FLUFFY", myArray);
                pokeObject.put("VOLTORB", myArray);
                pokeObject.put("GARCHOMP", myArray);
                pokeObject.put("NARUTO", myArray);
                pokeObject.put("RIMACU", myArray);
                pokeObject.put("TORTERRA", myArray);
            } catch (Exception e) {
                System.out.println("error pokemon initial set up");
            }
            initialSetup = true;
        }
    }

    //Pokemon Related Methods
    public void setCurrentHealth(int input) { currentHealth = input; }
    public int getCurrentHealth() { return currentHealth; }
    public int getTotalHealth() { return totalHealth; }
    public int getAttack() { return attack; }
    public int getSpecialAttack() { return specialAttack; }
    public int getDefense() { return defense; }
    public int getSpecialDefense() { return specialDefense; }
    public int getSpeed() { return speed; }
    public String getName() { return name; }

    //Move Related Methods

    public String getMove1() { return findMove(moveOneId - 1); }
    public int getMoveOnePower() {
        try {
            return pokeObject.getInt("move1");
        } catch (Exception e) {
            System.out.println("did not work");
        }
        return 0;
    }

    public int getMoveOneAcc() { return moveOneAcc; }
    public int getMoveOnePP() { return moveOnePP; }
    public void setMoveOnePower(int power) {moveOnePower = power; }
    public void setMoveOneAcc(int accuracy){moveOneAcc = accuracy; }
    public void setMoveOnePP(int pp) {moveOnePP = pp; }


    public String getMove2() { return findMove(moveTwoId - 1); }
    public int getMoveTwoPower() {
        try {
            return pokeObject.getInt("move2");
        } catch (Exception e) {
            System.out.println("did not work");
        }
        return 0;
    }
    public int getMoveTwoAcc() { return moveTwoAcc; }
    public int getMoveTwoPP() { return moveTwoPP; }

    public String getMove3() { return findMove(moveThreeId - 1); }
    public int getMoveThreePower() {
        try {
            return pokeObject.getInt("move3");
        } catch (Exception e) {
            System.out.println("did not work");
        }
        return 0;
    }
    public int getMoveThreeAcc() { return moveThreeAcc; }
    public int getMoveThreePP() { return moveThreePP; }

    public String getMove4() { return findMove(moveFourId - 1); }
    public int getMoveFourPower() {
        try {
            return pokeObject.getInt("move4");
        } catch (Exception e) {
            System.out.println("did not work");
        }
        return 0;
    }
    public int getMoveFourAcc() { return moveFourAcc; }
    public int getMoveFourPP() { return moveFourPP; }

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
    public JsonObjectRequest getMoveData1() {
        String url = "https://pokeapi.co/api/v2/move/" + moveOneId;
        JsonObjectRequest movedata1 = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            moveOnePower = response.getInt("power");
                            pokeObject.put("move1", moveOnePower);
                            //moveOneAcc = response.getInt("accuracy");
                            //moveOnePP = response.getInt("pp");
                            //pokeObject.getJSONArray(currentPokemon).put(1, moveOneAcc);
                            //pokeObject.getJSONArray(currentPokemon).put(2, moveOnePP);
                        } catch (Exception e) {
                            Log.e("Poke 125", "move 1 data request failed for " + getName());

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("NO Wifi or Did not finish in Time for " + getName());

                    }
                });
        return movedata1;
    }
    //change
    public JsonObjectRequest getMoveData2() {
        String url = "https://pokeapi.co/api/v2/move/" + moveTwoId;
        JsonObjectRequest movedata2 = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            moveTwoPower = response.getInt("power");
                            pokeObject.put("move2", moveTwoPower);
                            //moveTwoAcc = response.getInt("accuracy");
                            //moveTwoPP = response.getInt("pp");
                        } catch (Exception e) {
                            Log.e("Poke 125", "move 2 data request failed far" + getName());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("NO Wifi or Did not finish in Time for " + getName());

                    }
                });
        return movedata2;
    }
    public JsonObjectRequest getMoveData3() {
        String url = "https://pokeapi.co/api/v2/move/" + moveThreeId;
        JsonObjectRequest movedata3 = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            moveThreePower = response.getInt("power");
                            pokeObject.put("move3", moveThreePower);
                            //moveThreeAcc = response.getInt("accuracy");
                            //moveThreePP = response.getInt("pp");
                        } catch (Exception e) {
                            Log.e("Poke 125", "move 3 data request failed for " + getName());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("NO Wifi or Did not finish in Time for " + getName());

                    }
                });
        return movedata3;
    }
    public JsonObjectRequest getMoveData4() {
        String url = "https://pokeapi.co/api/v2/move/" + moveFourId;
        JsonObjectRequest movedata4 = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            moveFourPower = response.getInt("power");
                            pokeObject.put("move4", moveFourPower);
                            //moveFourAcc = response.getInt("accuracy");
                            //moveFourPP = response.getInt("pp");
                        } catch (Exception e) {
                            Log.e("Poke 125", "move 4 data request failed for " + getName());
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("NO Wifi or Did not finish in Time for " + getName());


                    }
                });
        return movedata4;
    }
}
