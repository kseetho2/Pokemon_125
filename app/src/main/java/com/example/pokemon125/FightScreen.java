package com.example.pokemon125;

import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebResourceRequest;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.StringRequest;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Move;
import me.sargunvohra.lib.pokekotlin.model.PokemonMove;

public class FightScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_screen);    //This line add the view to your activity

    }
    public int setMoveToButton(int move) {
        PokeApi request = new PokeApiClient();
        PokemonMove attack = request.getMove();
        Button moveTL;
        Button moveTR;
        Button moveBL;
        Button moveBR;
        moveTL = findViewById(R.id.moveTL);
        moveTR = findViewById(R.id.moveTR);
        moveBL = findViewById(R.id.moveBL);
        moveBR = findViewById(R.id.moveBR);
    }

    /**
     * This method uses a volley web request to retrieve the move with a certain name,
     * also works for numbers as well, the move is based on the pokemon
     * @return
     */
    public int getMove() {
        PokeApi request = new PokeApiClient();
        Move retrieve = request.getMove(1);
    }
}
