package com.androiddev.project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the game loop and entities
        ImageView playerSprite = findViewById(R.id.player);
        ImageView enemySprite = findViewById(R.id.enemy);
        ViewGroup.LayoutParams params = playerSprite.getLayoutParams();
        params.width = 256;
        params.height = 256;
        playerSprite.setLayoutParams(params);
        ViewGroup.LayoutParams Eparams = enemySprite.getLayoutParams();
        Eparams.width = 256;
        Eparams.height = 256;
        enemySprite.setLayoutParams(Eparams);

        ProgressBar playerHealth = findViewById(R.id.playerHealth);
        ProgressBar enemyHealth = findViewById(R.id.enemyHealth);
        ViewGroup.LayoutParams barParam = playerHealth.getLayoutParams();
        barParam.width = 256;
        playerHealth.setLayoutParams(barParam);
        ViewGroup.LayoutParams eBarParam = enemyHealth.getLayoutParams();
        eBarParam.width = 256;
        enemyHealth.setLayoutParams(eBarParam);

        game = new Game(findViewById(R.id.EnemyTextDisplay),findViewById(R.id.PlayerTextDisplay), playerHealth, enemyHealth, this);
        game.GameInitialization(playerSprite, enemySprite);

        //grid initialization
        Grid grid = new Grid(16, Color.WHITE);
        grid.Draw(new Canvas());
    }
    public static class GameSettings{
        static final int GRID_SIZE = 256;
        static final int GRID_COLOR = Color.BLACK;
    }
    public boolean onTouchEvent(MotionEvent event){
        game.Move(event, this);
        return true;
    }
}