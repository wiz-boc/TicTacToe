package com.codelifestudio.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 is yellow and 1 is red 2 is empty
    int activePlayer = 0;
    private static final String TAG = "MainActivity";

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    android.support.v7.widget.GridLayout gameLayout;

    Button btn_playAgain;
    TextView tv_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gameLayout = findViewById(R.id.GL_board);
        btn_playAgain = findViewById(R.id.btn_restart);
        tv_winner = findViewById(R.id.TV_winner);

    }


    public void dropIn(View v) {
        ImageView counter = (ImageView) v;
        int tagVal = Integer.parseInt(counter.getTag().toString());
        Log.i(TAG, "dropIn: tag is " + tagVal);

        if (gameState[tagVal] == 2 && gameActive == true) {

            counter.setTranslationY(-1500);

            gameState[tagVal] = activePlayer;

            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
            }
            //counter.setImageResource(R.drawable.yellow);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPos : winningPositions) {

                if (gameState[winningPos[0]] == gameState[winningPos[1]] && gameState[winningPos[1]] == gameState[winningPos[2]] && gameState[winningPos[0]] != 2) {

                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 0) {
                        winner = "Red";
                    } else {
                        winner = "Yellow";
                    }
                    Toast.makeText(this, winner + " as won", Toast.LENGTH_LONG).show();


                    tv_winner.setText(winner + " has won!!");
                    tv_winner.setVisibility(View.VISIBLE);
                    btn_playAgain.setVisibility(View.VISIBLE);
                }
            }

        }


    }

    public void playAgain(View v){

        tv_winner.setText("");
        tv_winner.setVisibility(View.INVISIBLE);
        btn_playAgain.setVisibility(View.INVISIBLE );

        for(int i  = 0;i<gameLayout.getChildCount();i++){
            ImageView child = (ImageView) gameLayout.getChildAt(i);
            child.setImageDrawable(null);
            gameState[i] = 2;
        }

        gameActive = true;
        Toast.makeText(this, " New game started!", Toast.LENGTH_LONG).show();
    }


}
