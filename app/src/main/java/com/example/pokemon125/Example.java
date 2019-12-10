package com.example.pokemon125;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;


public class Example extends AppCompatActivity {
    private static JSONArray array;
    Example() {
    }
    public void setmoveArray(JSONArray input) {
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
    /*
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
        //change
    }
     */
    /**
     * All move ids here should be one less than their actual ids
     */
    public int[] moveList(String pokemonName) {
        int[] moveIds = new int[4];
        if (pokemonName.equals("FLUFFY")) {
            moveIds = new int[]{246, 129, 43, 62};
        } else if (pokemonName.equals("NARUTO")) {
            moveIds = new int[]{415, 55, 399, 57};
        } else if (pokemonName.equals("GARCHOMP")) {
            moveIds = new int[]{406, 241, 125, 433};
        } else if (pokemonName.equals("VOLTORB")) {
            moveIds = new int[]{434, 152, 204, 128};
        } else if (pokemonName.equals("RAMICU")) {
            moveIds = new int[]{397, 156, 410, 88};
        } else if (pokemonName.equals("TORTERRA")) {
            moveIds = new int[]{275, 33, 337, 441};
        } else if (pokemonName.equals("MACHINE PROJECT 0")) {//Rayquaza
            moveIds = new int[]{351, 230, 619, 413};
        } else if (pokemonName.equals("MACHINE PROJECT 1")) {//Porygon
            moveIds = new int[]{62, 160, 59, 32};
        } else if (pokemonName.equals("MACHINE PROJECT 2")) {//Entei
            moveIds = new int[]{283, 325, 22, 43};
        } else if (pokemonName.equals("MACHINE PROJECT 3")) {//Mr.Mime
            moveIds = new int[]{93, 59, 92, 0};
        } else if (pokemonName.equals("MACHINE PROJECT 4")) {//Dialga
            moveIds = new int[]{458, 413, 245, 407};
        } else if (pokemonName.equals("FINAL PROJECT")) {
            moveIds = new int[]{92, 247, 426, 395};
        }
        return moveIds;
    }
}