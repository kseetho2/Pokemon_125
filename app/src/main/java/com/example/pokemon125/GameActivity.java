package com.example.pokemon125;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
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

    private PokeApi pokeApi = new PokeApiClient();

    private TextView message;
    private Button move1;
    private Button move2;
    private Button move3;
    private Button move4;
    private Button fightOption;

    private int dmgSave;
    private String geoffMove1Name;
    private int geoffMove1Dmg;
    private String geoffMove2Name;
    private int geoffMove2Dmg;
    private String geoffMove3Name;
    private int geoffMove3Dmg;
    private String geoffMove4Name;
    private int geoffMove4Dmg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_screen);
        Intent intent = getIntent();
        String currentSelection = intent.getStringExtra("selectedPokemon");
        message = findViewById(R.id.messageBox);
        message.setText("What will \n" + currentSelection + " do?");

        move1 = findViewById(R.id.moveTL);
        move2 = findViewById(R.id.moveTR);
        move3 = findViewById(R.id.moveBL);
        move4 = findViewById(R.id.moveBR);

        //where you would rename the moves:
        //move1.setText("50 dmg");
        move2.setText("100 dmg");
        move3.setText("150 dmg");
        move4.setText("200 dmg");
        fightOption = findViewById(R.id.fight);

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

        fightOption.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message.setVisibility(View.INVISIBLE);
                move1.setVisibility(View.VISIBLE);
                move2.setVisibility(View.VISIBLE);
                move3.setVisibility(View.VISIBLE);
                move4.setVisibility(View.VISIBLE);
            }
        });
    }

    private void fightAction() {
        message.setVisibility(View.INVISIBLE);
        move1.setVisibility(View.VISIBLE);
        move2.setVisibility(View.VISIBLE);
        move3.setVisibility(View.VISIBLE);
        move4.setVisibility(View.VISIBLE);
        move1.setOnClickListener(this);
        move2.setOnClickListener(this);
        move3.setOnClickListener(this);
        move4.setOnClickListener(this);
        int geoffRandomAtk = (int) (Math.random() * 4) + 1;
        System.out.println(geoffRandomAtk);

        if (userCurrent.getSpeed() >= geoffCurrent.getSpeed()) {
        }

        /*
        user chooses move
        if user is first based on speed:
            user attacks
            geoff takes dmg
            >check for geoff faint
                if yes, check if all pokemon are fainted
                    if yes, game over
                else switch to next pokemon

            geoff attacks
            user takes dmg
            >check for user faint
                if yes, check if all pokemon are fainted
                    if yes, game over
                else switch to pokemon selection menu

        if geoff is first based on speed:
            geoff attacks
            user takes dmg (^^)
            user attacks
            geoff takes dmg (^^)

        message board back up, repeat

        pokemon selection menu method:
        update hp of pokemon with current hp
        upon selection, check hp:
            if 0:
                toast message
            else:
                set current pokemon to selected pokemon, copy from onCreate
         */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.moveTL:
                //Move 1 selected
                dmgSave = 50;
                break;
            case R.id.moveTR:
                dmgSave = 100;
                break;
            case R.id.moveBL:
                dmgSave = 150;
                break;
            case R.id.moveBR:
                dmgSave = 200;
                break;
        }
    }

    /**
     * Use this method every time Geoff plays a new Pokemon.
     * Will eventually use the geoff.current Pokemon
     */
    private void setGeoffMoves() {
        geoffMove1Name = "50dmg";
        geoffMove2Name = "100dmg";
        geoffMove3Name = "150dmg";
        geoffMove4Name = "200dmg";
        geoffMove1Dmg = 50;
        geoffMove2Dmg = 100;
        geoffMove3Dmg = 150;
        geoffMove4Dmg = 200;
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
        /*
        ConstraintLayout parent = findViewById(R.id.playerOptions);
        //parent.removeAllViews();
        View messageChunk = getLayoutInflater().inflate(R.layout.chunk_player_options, parent, false);
        TextView move1 = messageChunk.findViewById(R.id.moveTL);
        move1.setText(userCurrent.getMove1());
        TextView move2 = messageChunk.findViewById(R.id.moveTR);
        move2.setText(userCurrent.getMove2());
        TextView move3 = messageChunk.findViewById(R.id.moveBL);
        move3.setText(userCurrent.getMove3());
        TextView move4 = messageChunk.findViewById(R.id.moveBR);
        move4.setText(userCurrent.getMove4());
        parent.addView(messageChunk);
         */
    }
}
