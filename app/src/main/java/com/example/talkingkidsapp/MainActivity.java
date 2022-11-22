package com.example.talkingkidsapp;




import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean Jugador1Turno = true;

    private int roundCount;

    private int Jugador1Puntos;
    private int Jugador2Puntos;

    private TextView textViewJugador1;
    private TextView textViewJugador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewJugador1 = findViewById(R.id.text_view_p1);
        textViewJugador2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (Jugador1Turno) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (Jugador1Turno) {
                Jugador1Gana();
            } else {
                Jugador2Gana();
            }
        } else if (roundCount == 9) {
            EMPATE();
        } else {
            Jugador1Turno = !Jugador1Turno;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void Jugador1Gana() {
        Jugador1Puntos++;
        Toast.makeText(this, "Jugador 1 Gana!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void Jugador2Gana() {
        Jugador2Puntos++;
        Toast.makeText(this, "Jugador 2 Gana!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void EMPATE() {
        Toast.makeText(this, "EMPATE!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewJugador1.setText("Jugador 1: " + Jugador1Puntos);
        textViewJugador2.setText("Jugador 2: "+ Jugador2Puntos);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        Jugador1Turno = true;
    }

    private void resetGame() {
        Jugador1Puntos = 0;
        Jugador2Puntos = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", Jugador1Puntos);
        outState.putInt("player2Points", Jugador2Puntos);
        outState.putBoolean("player1Turn", Jugador1Turno);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        Jugador1Puntos = savedInstanceState.getInt("Jugador1Puntos");
        Jugador2Puntos = savedInstanceState.getInt("Jugador2Puntos");
        Jugador1Turno = savedInstanceState.getBoolean("Jugador1Turno");
    }
}