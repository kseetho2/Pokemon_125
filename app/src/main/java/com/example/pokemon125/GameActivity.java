package com.example.pokemon125;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;



import java.util.Arrays;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import pl.droidsonroids.gif.GifImageView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private Pokemon eevee;
    private Pokemon garchomp;
    private Pokemon greninja;
    private Pokemon voltorb;
    private Pokemon riolu;
    private Pokemon torterra;
    private List<Pokemon> userLineup;
    private Pokemon userCurrent;

    private Pokemon rayquaza;
    private Pokemon porygon;
    private Pokemon entei;
    private Pokemon mrMime;
    private Pokemon dialga;
    private Pokemon mewTwo;
    private List<Pokemon> geoffLineup;
    private int geoffPokeCount;
    private Pokemon geoffCurrent;
    private int userFaintCount;

    private PokeApi pokeApi = new PokeApiClient();

    private TextView message;
    private Button move1;
    private Button move2;
    private Button move3;
    private Button move4;
    private int[] movelist;
    Example data = new Example();
    private Button fightOption;
    private Button cheatOption;
    private Button switchPokemonOption;
    private Button runOption;

    private String userDamageName;
    private int userDamage;

    private String geoffMove1Name;
    private int geoffMove1Dmg;
    private String geoffMove2Name;
    private int geoffMove2Dmg;
    private String geoffMove3Name;
    private int geoffMove3Dmg;
    private String geoffMove4Name;
    private int geoffMove4Dmg;
    private String geoffDamageName;
    private int geoffDamage;

    private TextView geoffFirstRow;
    private TextView geoffSecondRow;
    private TextView playerFirstRow;
    private TextView playerSecondRow;
    private GifImageView geoffSprite; //7up
    private GifImageView userSprite; //7up
    private TextView switchPokeMessage; //7up

    boolean switchedByChoice = false;

    private ViewFlipper viewFlipper;
    MediaPlayer mp;
    MediaPlayer victory;
    MediaPlayer defeat;
    private boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp = MediaPlayer.create(this, R.raw.battle_champion);
        victory = MediaPlayer.create(this, R.raw.fortnite_dance);
        defeat = MediaPlayer.create(this, R.raw.sad_violin_mlg);
        mp.setLooping(true);
        mp.start();
        eevee = new Pokemon("FLUFFY", 314, 229, 218, 207, 251, 229);
        garchomp = new Pokemon("GARCHOMP", 420, 482, 361, 372, 317, 311);
        greninja = new Pokemon("NARUTO", 348, 317, 256, 335, 265, 377);
        voltorb = new Pokemon("VOLTORB", 284, 174, 218, 229, 229, 328);
        riolu = new Pokemon("RAMICU", 284, 262, 196, 185, 196, 240);
        torterra = new Pokemon("TORTERRA", 394, 348, 273, 339, 295, 232);
        //
        userLineup = Arrays.asList(eevee, garchomp, greninja, voltorb, riolu, torterra);
        userFaintCount = 0;
        //
        rayquaza = new Pokemon("MACHINE PROJECT 0", 414, 504, 328, 504, 328, 361);
        porygon = new Pokemon("MACHINE PROJECT 1", 334, 240, 262, 295, 273, 196);
        entei = new Pokemon("MACHINE PROJECT 2", 434, 361, 295, 306, 273, 328);
        mrMime = new Pokemon("MACHINE PROJECT 3", 284, 207, 251, 328, 372, 306);
        dialga = new Pokemon("MACHINE PROJECT 4", 404, 372, 372, 438, 328, 306);
        mewTwo = new Pokemon("FINAL PROJECT", 416, 350, 306, 447, 306, 394);
        //
        geoffLineup = Arrays.asList(rayquaza, porygon, entei, mrMime, dialga, mewTwo);
        geoffPokeCount = 0;
        geoffCurrent = geoffLineup.get(geoffPokeCount);
        //
        setContentView(R.layout.switch_and_fight);
        viewFlipper = findViewById(R.id.view_flipper);
        move1 = findViewById(R.id.moveTL);
        move2 = findViewById(R.id.moveTR);
        move3 = findViewById(R.id.moveBL);
        move4 = findViewById(R.id.moveBR);
        geoffFirstRow = findViewById(R.id.geoffFirstRow);
        geoffSecondRow = findViewById(R.id.geoffSecondRow);
        playerFirstRow = findViewById(R.id.playerFirstRow);
        playerSecondRow = findViewById(R.id.playerSecondRow);
        geoffSprite = findViewById(R.id.geoffSprite); //7up
        userSprite = findViewById(R.id.userSprite); //7up
        switchPokeMessage = findViewById(R.id.switchPokeMessage); //7up

        Intent intent = getIntent();
        String currentSelection = intent.getStringExtra("selectedPokemon");
        message = findViewById(R.id.messageBox);
        message.setText("What will \n" + currentSelection + " do?");



        fightOption = findViewById(R.id.fight);
        cheatOption = findViewById(R.id.cheat);
        switchPokemonOption = findViewById(R.id.switchP);
        runOption = findViewById(R.id.run);

        setText(currentSelection);
        setPokeStats(geoffFirstRow, geoffSecondRow, geoffCurrent);
        setPokeStats(playerFirstRow, playerSecondRow, userCurrent);

        playerOptions();
    }

    private void playerOptions() {
        if (switchedByChoice) {
            message.setText("You switched Pokemon!");
        } else {
            message.setText("What will \n" + userCurrent.getName() + " do?");
        }
        showMessage();
        showOptions();
        fightOption.setOnClickListener(this);
        cheatOption.setOnClickListener(this);
        switchPokemonOption.setOnClickListener(this);
        runOption.setOnClickListener(this);
    }

    public void igPokemonClicked(View v) {
        RadioGroup radioGroup = findViewById(R.id.igUserPokemonList);
        int radioId = radioGroup.getCheckedRadioButtonId();

        RadioButton selectedPokemon = findViewById(radioId);

        boolean available = false;
        Pokemon tempCheck;
        if (selectedPokemon.getText().equals("FLUFFY")) {
            tempCheck = eevee;
            if (eevee.getCurrentHealth() > 0) {
                available = true;
                userCurrent = tempCheck;
                userSprite.setImageResource(R.drawable.eevee_back); //7up
            }
        } else if (selectedPokemon.getText().equals("GARCHOMP")) {
            tempCheck = garchomp;
            if (garchomp.getCurrentHealth() > 0) {
                available = true;
                userCurrent = tempCheck;
                userSprite.setImageResource(R.drawable.garchomp_back); //7up
            }
        } else if (selectedPokemon.getText().equals("NARUTO")) {
            tempCheck = greninja;
            if (greninja.getCurrentHealth() > 0) {
                available = true;
                userCurrent = tempCheck;
                userSprite.setImageResource(R.drawable.greninja_back); //7up
            }
        } else if (selectedPokemon.getText().equals("VOLTORB")) {
            tempCheck = voltorb;
            if (voltorb.getCurrentHealth() > 0) {
                available = true;
                userCurrent = tempCheck;
                userSprite.setImageResource(R.drawable.voltorb_back); //7up
            }
        } else if (selectedPokemon.getText().equals("RAMICU")) {
            tempCheck = riolu;
            if (riolu.getCurrentHealth() > 0) {
                available = true;
                userCurrent = tempCheck;
                userSprite.setImageResource(R.drawable.riolu_back); //7up
            }
        } else if (selectedPokemon.getText().equals("TORTERRA")) {
            tempCheck = torterra;
            if (torterra.getCurrentHealth() > 0) {
                available = true;
                userCurrent = tempCheck;
                userSprite.setImageResource(R.drawable.torterra_back); //7up
            }
        }
        if (available) {
            Toast.makeText(this, "Selected Pokemon: " + selectedPokemon.getText(), Toast.LENGTH_SHORT).show();
            setText(selectedPokemon.getText().toString());
            viewFlipper.showNext();
            setPokeStats(geoffFirstRow, geoffSecondRow, geoffCurrent);
            setPokeStats(playerFirstRow, playerSecondRow, userCurrent);
            playerOptions();
        } else {
            Toast.makeText(this, "That staff member's Pokemon has fainted!", Toast.LENGTH_SHORT).show();
        }

    }
    public void setText(String pokemonName) {

        if (pokemonName.equals("FLUFFY")) {
            userCurrent = eevee;
            userSprite.setImageResource(R.drawable.eevee_back); //7up
        }
        if (pokemonName.equals("GARCHOMP")) {
            userCurrent = garchomp;
            userSprite.setImageResource(R.drawable.garchomp_back); //7up
        }
        if (pokemonName.equals("NARUTO")) {
            userCurrent = greninja;
            userSprite.setImageResource(R.drawable.greninja_back); //7up
        }
        if (pokemonName.equals("VOLTORB")) {
            userCurrent = voltorb;
            userSprite.setImageResource(R.drawable.voltorb_back); //7up
        }
        if (pokemonName.equals("RAMICU")) {
            userCurrent = riolu;
            userSprite.setImageResource(R.drawable.riolu_back); //7up
        }
        if (pokemonName.equals("TORTERRA")) {
            userCurrent = torterra;
            userSprite.setImageResource(R.drawable.torterra_back); //7up
        }
        movelist = data.moveList(userCurrent.getName());
        move1.setText(data.findMove(movelist[0]) + "\n(" + userCurrent.getMoveOnePower() + ")");
        move2.setText(data.findMove(movelist[1])+ "\n(" + userCurrent.getMoveTwoPower() + ")");
        move3.setText(data.findMove(movelist[2])+ "\n(" + userCurrent.getMoveThreePower() + ")");
        move4.setText(data.findMove(movelist[3])+ "\n(" + userCurrent.getMoveFourPower() + ")");
    }
    @Override
    public void onClick(View v) {
        boolean wasMove = false;
        final LinearLayout wholeScreen = findViewById(R.id.fightScreen);
        movelist = data.moveList(userCurrent.getName());
        switch (v.getId()) {
            case R.id.moveTL:
                //Move 1 selected
                int type = moveTypeCheck(movelist[0]);
                if (type == 1) {
                    userDamage = damageCalculator(userCurrent.getMoveOnePower(), userCurrent.getAttack(), geoffCurrent.getDefense());
                } else {
                    userDamage = damageCalculator(userCurrent.getMoveOnePower(), userCurrent.getSpecialAttack(), geoffCurrent.getSpecialDefense());
                }
                userDamageName = data.findMove(movelist[0]).toUpperCase();
                wasMove = true;
                break;
            case R.id.moveTR:
                //Move 2 selected
                int type1 = moveTypeCheck(movelist[1]);
                if (type1 == 1) {
                    userDamage = damageCalculator(userCurrent.getMoveTwoPower(), userCurrent.getAttack(), geoffCurrent.getDefense());
                } else {
                    userDamage = damageCalculator(userCurrent.getMoveTwoPower(), userCurrent.getSpecialAttack(), geoffCurrent.getSpecialDefense());
                }
                userDamageName = data.findMove(movelist[1]).toUpperCase();
                wasMove = true;
                break;
            case R.id.moveBL:
                //Move 3 selected
                int type2 = moveTypeCheck(movelist[2]);
                if (type2 == 1) {
                    userDamage = damageCalculator(userCurrent.getMoveThreePower(), userCurrent.getAttack(), geoffCurrent.getDefense());
                } else {
                    userDamage = damageCalculator(userCurrent.getMoveThreePower(), userCurrent.getSpecialAttack(), geoffCurrent.getSpecialDefense());
                }
                userDamageName = data.findMove(movelist[2]).toUpperCase();
                wasMove = true;
                break;
            case R.id.moveBR:
                //Move 4 selected
                int type3 = moveTypeCheck(movelist[3]);
                if (type3 == 1) {
                    userDamage = damageCalculator(userCurrent.getMoveFourPower(), userCurrent.getAttack(), geoffCurrent.getDefense());
                } else {
                    userDamage = damageCalculator(userCurrent.getMoveFourPower(), userCurrent.getSpecialAttack(), geoffCurrent.getSpecialDefense());
                }
                userDamageName = data.findMove(movelist[3]).toUpperCase();
                wasMove = true;
                break;
            case R.id.fight:
                fightAction();
                break;
            case R.id.cheat:
                message.setText("Please refer to the cheating policies in the SYLLABUS.");
                hideOptions();
                wholeScreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        playerOptions();
                    }
                });
                break;
            case R.id.switchP:
                viewFlipper.showPrevious();
                updatePokemonSelection();
                String pokeName = "";
                if (geoffPokeCount == 0) {
                    pokeName = "RAYQUAZA";
                } else if (geoffPokeCount == 1) {
                    pokeName = "PORYGON";
                } else if (geoffPokeCount == 2) {
                    pokeName = "ENTEI";
                } else if (geoffPokeCount == 3) {
                    pokeName = "MR. MIME";
                } else if (geoffPokeCount == 4) {
                    pokeName = "DIALGA";
                } else if (geoffPokeCount == 5) {
                    pokeName = "MEWTWO";
                }
                switchPokeMessage.setText("\nTHE 'CHALLEN'GER WILL BE USING " + geoffCurrent.getName() + " ( " + pokeName + "). \n\nWHICH POKEMON WILL YOU CHOOSE?");
                break;
            case R.id.run:
                message.setText("Don't run! You can do it! The CS 125 staff believes in you!");
                hideOptions();
                wholeScreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        playerOptions();
                    }
                });
                break;
                //push
        }

        if (switchedByChoice) {
            message.setText( "You switched to " + userCurrent.getName() + "!");
            int geoffRandomAtk = (int) (Math.random() * 4) + 1;
            setGeoffMoves();
            if (geoffRandomAtk == 1) {
                int type = moveTypeCheck(movelist[0]);
                if (type == 1) {
                    geoffDamage = damageCalculator(geoffMove1Dmg, geoffCurrent.getAttack(), userCurrent.getDefense());
                } else {
                    geoffDamage = damageCalculator(geoffMove1Dmg, geoffCurrent.getSpecialAttack(), userCurrent.getSpecialDefense());
                }
                geoffDamageName = geoffMove1Name;
            } else if (geoffRandomAtk == 2) {
                int type = moveTypeCheck(movelist[1]);
                if (type == 1) {
                    geoffDamage = damageCalculator(geoffMove2Dmg, geoffCurrent.getAttack(), userCurrent.getDefense());
                } else {
                    geoffDamage = damageCalculator(geoffMove2Dmg, geoffCurrent.getSpecialAttack(), userCurrent.getSpecialDefense());
                }
                geoffDamageName = geoffMove2Name;
            } else if (geoffRandomAtk == 3) {
                int type = moveTypeCheck(movelist[2]);
                if (type == 1) {
                    geoffDamage = damageCalculator(geoffMove3Dmg, geoffCurrent.getAttack(), userCurrent.getDefense());
                } else {
                    geoffDamage = damageCalculator(geoffMove3Dmg, geoffCurrent.getSpecialAttack(), userCurrent.getSpecialDefense());
                }
                geoffDamageName = geoffMove3Name;
            } else if (geoffRandomAtk == 4) {
                int type = moveTypeCheck(movelist[0]);
                if (type == 1) {
                    geoffDamage = damageCalculator(geoffMove4Dmg, geoffCurrent.getAttack(), userCurrent.getDefense());
                } else {
                    geoffDamage = damageCalculator(geoffMove4Dmg, geoffCurrent.getSpecialAttack(), userCurrent.getSpecialDefense());
                }
                geoffDamageName = geoffMove4Name;
            }

            wholeScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userCurrent.setCurrentHealth(userCurrent.getCurrentHealth() - geoffDamage);
                    if (checkUserFaint()) {
                        if (userFaintCount == 6) {
                            message.setText(geoffCurrent.getName() + " used " + geoffDamageName + "!");
                            wholeScreen.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    message.setText("Oh no! All your staff members have fainted!");
                                    wholeScreen.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mp.stop();
                                            defeat.start();
                                            defeat.setLooping(true);
                                            setContentView(R.layout.end_screen);
                                            TextView endText = findViewById(R.id.endScreenText);
                                            endText.setTextSize(50);
                                            endText.setText("You lost to the 'CHALLEN'GER! Do some more PrairieLearn problems and try again!");
                                        }
                                    });
                                }
                            });
                        } else {
                            setPokeStats(playerFirstRow, playerSecondRow, userCurrent);
                            message.setText(geoffCurrent.getName() + " used " + geoffDamageName + "!");
                            wholeScreen.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    message.setText(userCurrent.getName() + " took " + geoffDamage + " damage!");
                                    wholeScreen.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            message.setText(userCurrent.getName() + " has fainted! Choose your next staff member!");
                                            wholeScreen.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    viewFlipper.showPrevious();
                                                    updatePokemonSelection();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                            switchedByChoice = false;
                        }
                    } else {
                        message.setText(geoffCurrent.getName() + " used " + geoffDamageName + "!");
                        setPokeStats(playerFirstRow, playerSecondRow, userCurrent);
                        wholeScreen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                message.setText(userCurrent.getName() + " took " + geoffDamage + " damage!");
                                wholeScreen.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        playerOptions();
                                    }
                                });
                            }
                        });
                        switchedByChoice = false;
                    }
                }
            });
        }

        if (wasMove) {
            geoffCurrent.setCurrentHealth(geoffCurrent.getCurrentHealth() - userDamage);
            if (checkGeoffFaint()) {
                setPokeStats(geoffFirstRow, geoffSecondRow, geoffCurrent);
                message.setText(userCurrent.getName() + " used " + userDamageName + "! ");
                showMessage();
                hideOptions();
                wholeScreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        message.setText(geoffCurrent.getName() + " fainted!");
                        if (!checkUserWinner()) {
                            geoffCurrent = geoffLineup.get(geoffPokeCount); //new Pokemon
                            wholeScreen.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    message.setText("The 'CHALLEN'GER will now use " + geoffCurrent.getName() + "!");
                                    //7up
                                    if (geoffPokeCount == 0) {
                                        geoffSprite.setImageResource(R.drawable.rayquaza);
                                    } else if (geoffPokeCount == 1) {
                                        geoffSprite.setImageResource(R.drawable.porygon);
                                    } else if (geoffPokeCount == 2) {
                                        geoffSprite.setImageResource(R.drawable.entei);
                                    } else if (geoffPokeCount == 3) {
                                        geoffSprite.setImageResource(R.drawable.mr_mime);
                                    } else if (geoffPokeCount == 4) {
                                        geoffSprite.setImageResource(R.drawable.dialga);
                                    } else if (geoffPokeCount == 5) {
                                        geoffSprite.setImageResource(R.drawable.mewtwo);
                                    }
                                    setPokeStats(geoffFirstRow, geoffSecondRow, geoffCurrent);
                                    wholeScreen.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            playerOptions();
                                        }
                                    });
                                }
                            });
                        } else {
                            //WIN CONDITION
                            wholeScreen.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mp.setLooping(false);
                                    mp.stop();
                                    victory.setLooping(true);
                                    victory.start();
                                    setContentView(R.layout.end_screen);
                                }
                            });
                        }
                    }
                });
            } else { //battle continues
                setPokeStats(geoffFirstRow, geoffSecondRow, geoffCurrent);
                message.setText(userCurrent.getName() + " used " + userDamageName + "!");
                showMessage();
                hideOptions();
                wholeScreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        message.setText(geoffCurrent.getName() + " took " + userDamage + " damage!");
                        int geoffRandomAtk = (int) (Math.random() * 4) + 1;
                        setGeoffMoves();
                        if (geoffRandomAtk == 1) {
                            int type = moveTypeCheck(movelist[0]);
                            if (type == 1) {
                                geoffDamage = damageCalculator(geoffMove1Dmg, geoffCurrent.getAttack(), userCurrent.getDefense());
                            } else {
                                geoffDamage = damageCalculator(geoffMove1Dmg, geoffCurrent.getSpecialAttack(), userCurrent.getSpecialDefense());
                            }
                            geoffDamageName = geoffMove1Name;
                        } else if (geoffRandomAtk == 2) {
                            int type = moveTypeCheck(movelist[1]);
                            if (type == 1) {
                                geoffDamage = damageCalculator(geoffMove2Dmg, geoffCurrent.getAttack(), userCurrent.getDefense());
                            } else {
                                geoffDamage = damageCalculator(geoffMove2Dmg, geoffCurrent.getSpecialAttack(), userCurrent.getSpecialDefense());
                            }
                            geoffDamageName = geoffMove2Name;
                        } else if (geoffRandomAtk == 3) {
                            int type = moveTypeCheck(movelist[2]);
                            if (type == 1) {
                                geoffDamage = damageCalculator(geoffMove3Dmg, geoffCurrent.getAttack(), userCurrent.getDefense());
                            } else {
                                geoffDamage = damageCalculator(geoffMove3Dmg, geoffCurrent.getSpecialAttack(), userCurrent.getSpecialDefense());
                            }
                            geoffDamageName = geoffMove3Name;
                        } else if (geoffRandomAtk == 4) {
                            int type = moveTypeCheck(movelist[0]);
                            if (type == 1) {
                                geoffDamage = damageCalculator(geoffMove4Dmg, geoffCurrent.getAttack(), userCurrent.getDefense());
                            } else {
                                geoffDamage = damageCalculator(geoffMove4Dmg, geoffCurrent.getSpecialAttack(), userCurrent.getSpecialDefense());
                            }
                            geoffDamageName = geoffMove4Name;
                        }

                        wholeScreen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userCurrent.setCurrentHealth(userCurrent.getCurrentHealth() - geoffDamage);
                                if (checkUserFaint()) {
                                    if (userFaintCount == 6) {
                                        message.setText(geoffCurrent.getName() + " used " + geoffDamageName + "!");
                                        wholeScreen.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                message.setText("Oh no! All your staff members have fainted!");
                                                wholeScreen.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        setContentView(R.layout.end_screen);
                                                        TextView endText = findViewById(R.id.endScreenText);
                                                        endText.setTextSize(50);
                                                        endText.setText("You lost to the 'CHALLEN'GER! Do some more PrairieLearn problems and try again!");
                                                    }
                                                });
                                            }
                                        });
                                    } else {
                                        setPokeStats(playerFirstRow, playerSecondRow, userCurrent);
                                        message.setText(geoffCurrent.getName() + " used " + geoffDamageName + "!");
                                        wholeScreen.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                message.setText(userCurrent.getName() + " took " + geoffDamage + " damage!");
                                                wholeScreen.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        message.setText(userCurrent.getName() + " has fainted! Choose your next staff member!");
                                                        wholeScreen.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                viewFlipper.showPrevious();
                                                                updatePokemonSelection();
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                } else {
                                    message.setText(geoffCurrent.getName() + " used " + geoffDamageName + "!");
                                    setPokeStats(playerFirstRow, playerSecondRow, userCurrent);
                                    wholeScreen.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            message.setText(userCurrent.getName() + " took " + geoffDamage + " damage!");
                                            wholeScreen.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    playerOptions();
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        });
                    }
                });

            }
        }
    }

    private boolean checkGeoffFaint() {
        if (geoffCurrent.getCurrentHealth() <= 0) {
            geoffCurrent.setCurrentHealth(0);
            geoffPokeCount++;
            return true;
        }
        return false;
    }

    private boolean checkUserWinner() {
        if (geoffPokeCount == 6) {
            return true;
        }
        return false;
    }

    private boolean checkUserFaint() {
        if (userCurrent.getCurrentHealth() <= 0) {
            userCurrent.setCurrentHealth(0);
            userFaintCount++;
            return true;
        }
        return false;
    }

    private void fightAction() {
        showMoves();
        move1.setOnClickListener(this);
        move2.setOnClickListener(this);
        move3.setOnClickListener(this);
        move4.setOnClickListener(this);
    }

    private void showMessage() {
        message.setVisibility(View.VISIBLE);
        move1.setVisibility(View.INVISIBLE);
        move2.setVisibility(View.INVISIBLE);
        move3.setVisibility(View.INVISIBLE);
        move4.setVisibility(View.INVISIBLE);
    }

    private void showMoves() {
        message.setVisibility(View.INVISIBLE);
        move1.setVisibility(View.VISIBLE);
        move2.setVisibility(View.VISIBLE);
        move3.setVisibility(View.VISIBLE);
        move4.setVisibility(View.VISIBLE);
        fightOption.setVisibility(View.INVISIBLE);
        cheatOption.setVisibility(View.INVISIBLE);
        switchPokemonOption.setVisibility(View.INVISIBLE);
        runOption.setVisibility(View.INVISIBLE);
    }

    private void showOptions() {
        fightOption.setVisibility(View.VISIBLE);
        cheatOption.setVisibility(View.VISIBLE);
        switchPokemonOption.setVisibility(View.VISIBLE);
        runOption.setVisibility(View.VISIBLE);
    }

    private void hideOptions() {
        fightOption.setVisibility(View.INVISIBLE);
        cheatOption.setVisibility(View.INVISIBLE);
        switchPokemonOption.setVisibility(View.INVISIBLE);
        runOption.setVisibility(View.INVISIBLE);
    }

    private void setPokeStats(TextView text1, TextView text2, Pokemon poke) {
        text1.setText(poke.getName() + " :L100");
        text2.setText("HP: " + poke.getCurrentHealth() + "/" + poke.getTotalHealth());
    }

    /**
     * TO DO: need to edit this method a LOT! create a new layout specifically for THIS class!!
     */
    private void updatePokemonSelection() {
        RadioGroup pokeSelection = findViewById(R.id.userPokemonList);
        pokeSelection.setVisibility(View.INVISIBLE);
        RadioGroup newPokeSelection = findViewById(R.id.igUserPokemonList);
        newPokeSelection.setVisibility(View.VISIBLE);
        Button enterButton = findViewById(R.id.enterFightScreen);
        enterButton.setVisibility(View.INVISIBLE);
        TextView HP1 = findViewById(R.id.HP1);
        HP1.setText("HP: " + eevee.getCurrentHealth() + "/" + eevee.getTotalHealth());
        TextView HP2 = findViewById(R.id.HP2);
        HP2.setText("HP: " + garchomp.getCurrentHealth() + "/" + garchomp.getTotalHealth());
        TextView HP3 = findViewById(R.id.HP3);
        HP3.setText("HP: " + greninja.getCurrentHealth() + "/" + greninja.getTotalHealth());
        TextView HP4 = findViewById(R.id.HP4);
        HP4.setText("HP: " + voltorb.getCurrentHealth() + "/" + voltorb.getTotalHealth());
        TextView HP5 = findViewById(R.id.HP5);
        HP5.setText("HP: " + riolu.getCurrentHealth() + "/" + riolu.getTotalHealth());
        TextView HP6 = findViewById(R.id.HP6);
        HP6.setText("HP: " + torterra.getCurrentHealth() + "/" + torterra.getTotalHealth());
    }

    /**
     * Use this method every time Geoff plays a new Pokemon.
     * Will eventually use the geoff.current Pokemon
     */
    private void setGeoffMoves() {
        movelist = data.moveList(geoffCurrent.getName());
        geoffMove1Name = data.findMove(movelist[0]).toUpperCase();
        geoffMove2Name = data.findMove(movelist[1]).toUpperCase();
        geoffMove3Name = data.findMove(movelist[2]).toUpperCase();
        geoffMove4Name = data.findMove(movelist[3]).toUpperCase();
        geoffMove1Dmg = geoffCurrent.getMoveOnePower();
        geoffMove2Dmg = geoffCurrent.getMoveTwoPower();
        geoffMove3Dmg = geoffCurrent.getMoveThreePower();
        geoffMove4Dmg = geoffCurrent.getMoveFourPower();
    }
    public int moveTypeCheck(int input) {
        int[] specialMoves = new int[]{434, 128, 125, 433, 246, 62, 55,
                57, 410, 337, 351, 413, 160, 283, 325, 93, 92, 458, 245, 407, 247, 395};
        for (int i = 0; i < specialMoves.length; i++) {
            if (specialMoves[i] == input) {
                return 1;
            }
        }
        return 0;
    }
    public int damageCalculator(int moveDamage, int offense, int defense) {
        int damage;
        int var1 = 42;
        int var2 = moveDamage * offense / defense;
        damage = ((var1 * var2) / 50) + 2;
        return damage;
    }

    /**
     * Set the name of the moves based on the current Pokemon selected. Not actually used yet LOL
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

    @Override
    protected void onStop() {
        super.onStop();
        if (isPlaying) {
            mp.pause();
            isPlaying = false;
        }
        /**
        if (victory.isPlaying()) {
            victory.stop();
        }
        if (defeat.isPlaying()) {
            defeat.stop();
        }
         */
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isPlaying) {
            mp.start();
        }
        /**
        if (!victory.isPlaying()) {
            victory.start();
        }
        if (!defeat.isPlaying()) {
            defeat.start();
        }
         */
    }

}
