package com.example.colortitles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int darkColor;
    int brightColor;
    int brightBlock;
    int darkBlock;
    View[][] tiles;

    void randomBlock(){
        brightBlock = 0;
        darkBlock = 0;

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(Math.random() > 0.5){
                    tiles[i][j].setBackgroundColor(brightColor);
                    brightBlock++;
                } else {
                    tiles[i][j].setBackgroundColor(darkColor);
                    darkBlock++;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resources = getResources();
        darkColor = resources.getColor(R.color.dark);
        brightColor = resources.getColor(R.color.bright);
        tiles = new View[4][4];

        tiles[0][0] = findViewById(R.id.t00);
        tiles[0][1] = findViewById(R.id.t01);
        tiles[0][2] = findViewById(R.id.t02);
        tiles[0][3] = findViewById(R.id.t03);
        tiles[1][0] = findViewById(R.id.t10);
        tiles[1][1] = findViewById(R.id.t11);
        tiles[1][2] = findViewById(R.id.t12);
        tiles[1][3] = findViewById(R.id.t13);
        tiles[2][0] = findViewById(R.id.t20);
        tiles[2][1] = findViewById(R.id.t21);
        tiles[2][2] = findViewById(R.id.t22);
        tiles[2][3] = findViewById(R.id.t23);
        tiles[3][0] = findViewById(R.id.t30);
        tiles[3][1] = findViewById(R.id.t31);
        tiles[3][2] = findViewById(R.id.t32);
        tiles[3][3] = findViewById(R.id.t33);

        randomBlock();

    }
    public void changeColor(View v) {
        ColorDrawable d = (ColorDrawable) v.getBackground();
        if (d.getColor() == brightColor) {
            v.setBackgroundColor(darkColor);
            brightBlock--;
            darkColor++;
        } else {
            v.setBackgroundColor(brightColor);
            brightBlock++;
            darkColor--;
        }
    }
    public void onClick(View v) {
        // получаем тэг на кнопке
        String tag = v.getTag().toString();
        int x = Integer.parseInt("" + tag.charAt(0)), y = Integer.parseInt("" + tag.charAt(1) ); // координаты тайла и строки вида "00"

        // изменить цвет на самом тайле и всех тайлах
        // с таким же x и таким же y
        changeColor(v);
        for (int i = 0; i < 4; i++) {
            changeColor(tiles[x][i]);
            changeColor(tiles[i][y]);
        }



        if(brightBlock == 0 || darkBlock == 0){
            String msg = "You win!";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }


    }
}