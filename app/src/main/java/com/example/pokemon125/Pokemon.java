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



    private int moveTwoId;
    private int moveTwoPower;


    private int moveThreeId;
    private int moveThreePower;


    private int moveFourId;
    private int moveFourPower;

    // Data Retrieval Related Variables
    private static JSONObject pokeObject = new JSONObject();
    private static boolean initialSetup = false;
    private JSONArray temp = new JSONArray();
    private JSONObject dataFromVollyRq;

    //Collection of Planner to use variables but unable to due to time constraints or complexity
    /*
    private int moveOneAcc;
    private int moveOnePP;
    private int moveTwoAcc;
    private int moveTwoPP;
    private int moveThreeAcc;
    private int moveThreePP;
    private int moveFourAcc;
    private int moveFourPP;
     */


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
        } else if (input.equals("MACHINE PROJECT 0")) {
            moveOneId = 352; // Water pulse
            moveTwoId = 398; // Iron Tail
            moveThreeId = 620; // Dragons Ascent
            moveFourId = 414; // Earth Power
        } else if (input.equals("MACHINE PROJECT 1")) {
            moveOneId = 63; // Hyper Beam
            moveTwoId = 161; // Tri Attack
            moveThreeId = 60; // Psybeam
            moveFourId = 33; // Tackle
        } else if (input.equals("MACHINE PROJECT 2")) {
            moveOneId = 284; // Eruption
            moveTwoId = 326; // Extrasensory
            moveThreeId = 23; // Stomp
            moveFourId = 44; // Bite
        } else if (input.equals("MACHINE PROJECT 3")) {
            moveOneId = 94; // Psychic
            moveTwoId = 60; // Psybeam
            moveThreeId = 93; // Confusion
            moveFourId = 1; // Pound
        } else if (input.equals("MACHINE PROJECT 4")) {
            moveOneId = 459; // Roar of Time
            moveTwoId = 414; // Earth Power
            moveThreeId = 246; // Ancient Power
            moveFourId = 408; // Power Gem
        } else if (input.equals("FINAL PROJECT")) {
            moveOneId = 93; // Confusion
            moveTwoId = 248; //Future Sight
            moveThreeId = 427; // Psycho Cut
            moveFourId = 396; // Aura-Sphere
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
    public String getMove1() { return findMove(moveOneId); }
    public int getMoveOnePower() {
        try {

            return pokeObject.getJSONArray(getName()).getInt(0);
        } catch (Exception e) {
            System.out.println("did not work");
        }
        return 0;
    }
    //public int getMoveOneAcc() { return moveOneAcc; }
    //public int getMoveOnePP() { return moveOnePP; }

    public String getMove2() { return findMove(moveTwoId); }
    public int getMoveTwoPower() {
        try {
            return pokeObject.getJSONArray(getName()).getInt(1);
        } catch (Exception e) {
            System.out.println("did not work");
        }
        return 0;
    }
    //public int getMoveTwoAcc() { return moveTwoAcc; }
    //public int getMoveTwoPP() { return moveTwoPP; }

    public String getMove3() { return findMove(moveThreeId); }
    public int getMoveThreePower() {
        try {
            return pokeObject.getJSONArray(getName()).getInt(2);
        } catch (Exception e) {
            System.out.println("did not work");
        }
        return 0;
    }
    //public int getMoveThreeAcc() { return moveThreeAcc; }
    //public int getMoveThreePP() { return moveThreePP; }

    public String getMove4() { return findMove(moveFourId); }
    public int getMoveFourPower() {
        try {
            return pokeObject.getJSONArray(getName()).getInt(3);
        } catch (Exception e) {
            System.out.println("did not work");
        }
        return 0;
    }
    //public int getMoveFourAcc() { return moveFourAcc; }
    //public int getMoveFourPP() { return moveFourPP; }

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
                            temp.put(0, moveOnePower);
                            pokeObject.put(getName(), temp);
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
                            temp.put(1, moveTwoPower);
                            pokeObject.put(getName(), temp);
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
                            temp.put(2, moveThreePower);
                            pokeObject.put(getName(), temp);
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
                            temp.put(3, moveFourPower);
                            pokeObject.put(getName(), temp);
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
