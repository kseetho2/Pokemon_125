package com.example.pokemon125;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private Pokemon eevee;
    private Pokemon garchomp;
    private Pokemon greninja;
    private Pokemon voltorb;
    private Pokemon riolu;
    private Pokemon six; //TO BE DETERMINED
    private List<Pokemon> userLineup;
    private List<Pokemon> geoffLineup;
    private Pokemon userCurrent;
    private Pokemon geoffCurrent;

    public GameActivity(String currentSelection) {
        eevee = new Pokemon("Fluffy", 314, 229, 218, 207, 251, 229);
        garchomp = new Pokemon("Garchomp", 420, 482, 361, 372, 317, 311);
        greninja = new Pokemon("Naruto", 348, 317, 256, 335, 265, 377);
        voltorb = new Pokemon("Voltorb", 284, 174, 218, 229, 229, 328);
        riolu = new Pokemon("Ramicu", 284, 262, 196, 185, 196, 240);
        six = new Pokemon("Fluffy", 420, 229, 218, 207, 251, 229); // NEED TO EDIT
        userLineup = Arrays.asList(eevee, garchomp, greninja, voltorb, riolu, six);
        geoffLineup = userLineup; //TO BE DETERMINED
        if (currentSelection.equals("Fluffy")) {
            userCurrent = eevee;
        }
        if (currentSelection.equals("Garchomp")) {
            userCurrent = garchomp;
        }
        if (currentSelection.equals("Naruto")) {
            userCurrent = greninja;
        }
        if (currentSelection.equals("Voltorb")) {
            userCurrent = voltorb;
        }
        if (currentSelection.equals("Ramicu")) {
            userCurrent = riolu;
        }
        if (currentSelection.equals("Pokemon 6")) { //TO BE DETERMINED
            userCurrent = eevee;
        }
        geoffCurrent = eevee; //TO BE DETERMINED
    }

    /**
     * Set the name of the moves based on the current Pokemon selected.
     */
    public void initialSetup() {
        TextView move1 = findViewById(R.id.moveTL);
        move1.setText(userCurrent.getMove1());
        TextView move2 = findViewById(R.id.moveTR);
        move2.setText(userCurrent.getMove2());
        TextView move3 = findViewById(R.id.moveBL);
        move3.setText(userCurrent.getMove3());
        TextView move4 = findViewById(R.id.moveBR);
        move4.setText(userCurrent.getMove4());
    }


}
