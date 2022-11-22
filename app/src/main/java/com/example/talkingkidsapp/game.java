package com.example.talkingkidsapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
//arreglo para agregar palabras

public class game extends AppCompatActivity {

    private String[] words;
    private Random random;
    private String currWord;
    private TextView[]charViews;
    private LinearLayout wordLayout;
    private LetterAdaptar adapter;
    private GridView gridView;
    private int numCorr;
    private int numChars;
    private ImageView[]parts;
    private int SizeParts=6;
    private int currParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        words=getResources().getStringArray(R.array.words);
        wordLayout=findViewById(R.id.words);
        gridView=findViewById(R.id.letters);
        random=new Random();


        parts=new ImageView[SizeParts];
        parts[0]=findViewById(R.id.head);
        parts[1]=findViewById(R.id.body);
        parts[2]=findViewById(R.id.arm_Right);
        parts[3]=findViewById(R.id.arm_left);
        parts[4]=findViewById(R.id.leg_Left);
        parts[5]=findViewById(R.id.leg_right);


        playGame();
    }
    //OBTENER PALABRAS DENTRO DE UN RANGO EN ESPECIFICO
    //PAR AQUE CUANDO ESTE SALGA DEL RANGO MARQUE COMO ERROR
    private void playGame(){
        String newWord=words[random.nextInt(words.length)];

        //ciclo para no repetir palabra
        while(newWord.equals(currWord))newWord=words[random.nextInt(words.length)];

        //remplazar palabra actual por palabra nueva
        currWord=newWord;

        //divir letras \
        charViews=new TextView[currWord.length()];

        wordLayout.removeAllViews();

        for (int i=0; i<currWord.length(); i++){
            charViews[i]=new TextView(this);
            charViews[i].setText(""+currWord.charAt(i));
            charViews[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charViews[i].setGravity(Gravity.CENTER);
            charViews[i].setTextColor(Color.WHITE);
            charViews[i].setBackgroundResource(R.drawable.letter_bg);
            wordLayout.addView(charViews[i]);

        }
        adapter=new LetterAdaptar(this);
        gridView.setAdapter(adapter);
        numCorr=0;
        currParts=0;
        numChars=(currWord.length());

        for(int i=0; i<SizeParts; i++){
            parts[i].setVisibility(View.INVISIBLE);
        }
    }
    public void letterPressed(View view){
        String letter=((TextView)view).getText().toString();
        char letterChar=letter.charAt(0);

        view.setEnabled(false);

        boolean correct=false;

        //nos ayudara a comprobar que la palabra este dentro de las que se registraron
        for (int i=0; i<currWord.length(); i++){
            if (currWord.charAt(i)==letterChar){
                correct=true;
                numCorr++;
                charViews[i].setTextColor(Color.BLACK);
            }
        }

        if(correct){
            if(numCorr==numChars){
                disableButtons();
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("GANASTE");
                builder.setMessage("FELICIDADES!\n\n RESPUESTA CORRECTA \n\n" +currWord);
                builder.setPositiveButton("JUGAR OTRA VEZ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       game.this.playGame();
                    }
                });

                builder.setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        game.this.finish();
                    }
                });
                builder.show();



            }
        }else if(currParts<SizeParts){
            parts[currParts].setVisibility(View.VISIBLE);
            currParts++;

        }else {
            disableButtons();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("PERDISTE");
            builder.setMessage("TU PERDISTE!\n\n LA RESPUESTA ERA \n\n" +currWord);
            builder.setPositiveButton("JUGAR OTRA VEZ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    game.this.playGame();
                }
            });

            builder.setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    game.this.finish();
                }
            });
            builder.show();
        }
    }
    public void disableButtons(){
        for (int i=0; i<gridView.getChildCount();i++){
            gridView.getChildAt(i).setEnabled(false);
        }
    }
}