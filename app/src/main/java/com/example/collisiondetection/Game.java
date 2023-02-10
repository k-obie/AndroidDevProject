package com.example.collisiondetection;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;

public class Game {
    private float x1, x2, y1, y2;

    private Entity player;
    private Entity enemy;
    ImageView enemySprite;
    TextView playerTextDisplay;
    ProgressBar playerHealth;
    TextView enemyTextDisplay;
    ProgressBar enemyHealth;
    Context context;
    public Game(TextView enemyTextDisplay,TextView playerTextDisplay,ProgressBar playerHealth, ProgressBar enemyHealth, Context context){
        this.enemyTextDisplay = enemyTextDisplay;
        this.playerTextDisplay = playerTextDisplay;
        this.context = context;
        this.playerHealth = playerHealth;
        this.enemyHealth = enemyHealth;
    }

    public void GameInitialization(ImageView playerSprite, ImageView enemySprite){
        player = new Entity("Player", 20, 20, 10, playerSprite);
        enemy = new Entity("Enemy", 15, 15, 15, enemySprite);
        playerSprite.setVisibility(View.VISIBLE);
        enemySprite.setVisibility(View.VISIBLE);
        enemySprite.setX(512);
        enemySprite.setY(512);
        int[] Playerlocation = new int[2];
        player.getSprite().getLocationOnScreen(Playerlocation);
        playerHealth.setX(Playerlocation[0]);
        playerHealth.setY(Playerlocation[1] + 20);
        int[] Enemylocation = new int[2];
        enemy.getSprite().getLocationOnScreen(Enemylocation);
        enemyHealth.setX(Enemylocation[0]);
        enemyHealth.setY(Enemylocation[1] + 20);
        playerHealth.setMax((int)player.getMaxHP());
        enemyHealth.setMax((int)enemy.getMaxHP());

    }

    public void Move(MotionEvent event, MainActivity context) {
        float MIN_DISTANCE = 150;
        float[] oldPOS = new float[2];
        //check for swipe, depending on the direction, it will decide where the entity moves to
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX > MIN_DISTANCE) {
                        moveRight(player);

                    } else if (deltaX < MIN_DISTANCE * -1) {
                        moveLeft(player);
                    }
                } else if (Math.abs(deltaX) < Math.abs(deltaY)) {
                    if (deltaY > MIN_DISTANCE) {
                        moveDown(player);
                    } else if (deltaY < MIN_DISTANCE * -1) {
                        moveUp(player);
                    }
                }
                //enemy movement
                moveEnemy();
                // Update textbox location to be relative to player sprite

                int[] Playerlocation = new int[2];
                player.getSprite().getLocationOnScreen(Playerlocation);
                playerTextDisplay.setX((Playerlocation[0] + player.getSprite().getWidth())- (int)(MainActivity.GameSettings.GRID_SIZE/2));
                playerTextDisplay.setY(Playerlocation[1] - playerTextDisplay.getHeight() - (int)(MainActivity.GameSettings.GRID_SIZE + 40));

                playerHealth.setX(Playerlocation[0]);
                playerHealth.setY(Playerlocation[1] -40);
                //relative to enemy sprite

                int[] Enemylocation = new int[2];
                enemy.getSprite().getLocationOnScreen(Enemylocation);
                enemyTextDisplay.setX(Enemylocation[0] + enemy.getSprite().getWidth()- (int)(MainActivity.GameSettings.GRID_SIZE/2));
                enemyTextDisplay.setY(Enemylocation[1] - enemyTextDisplay.getHeight()- (int)(MainActivity.GameSettings.GRID_SIZE + 40));

                enemyHealth.setX(Enemylocation[0]);
                enemyHealth.setY(Enemylocation[1] - 40);

                playerHealth.setProgress((int)player.getCurrentHP());
                enemyHealth.setProgress((int)enemy.getCurrentHP());
        }
    }

    private void moveUp(Entity entity) {

        if((player.sprite.getY() + MainActivity.GameSettings.GRID_SIZE == enemy.sprite.getY()) &&
            (player.sprite.getX() == enemy.sprite.getX()) ||
            (enemy.sprite.getY() + MainActivity.GameSettings.GRID_SIZE == player.sprite.getY()) &&
            (enemy.sprite.getX() == player.sprite.getX())){
            Combat();
        }else if(!((entity.sprite.getY()) <= 0)) {
            entity.sprite.setY(entity.sprite.getY() - MainActivity.GameSettings.GRID_SIZE);
        }
    }
    private void moveDown(Entity entity) {

        if((player.sprite.getY() - MainActivity.GameSettings.GRID_SIZE == enemy.sprite.getY()) &&
            (player.sprite.getX() == enemy.sprite.getX()) ||
            (enemy.sprite.getY() - MainActivity.GameSettings.GRID_SIZE == player.sprite.getY())&&
            (enemy.sprite.getX() == player.sprite.getX())){

            Combat();
        }else if(!((entity.sprite.getY()) >= 1280)) {
            entity.sprite.setY(entity.sprite.getY() + MainActivity.GameSettings.GRID_SIZE);
        }
    }
    private void moveRight(Entity entity) {
        if((player.sprite.getX() + MainActivity.GameSettings.GRID_SIZE == enemy.sprite.getX()) &&
            (player.sprite.getY() == enemy.sprite.getY()) ||
            (enemy.sprite.getX() + MainActivity.GameSettings.GRID_SIZE == player.sprite.getX()) &&
            (player.sprite.getY() == enemy.sprite.getY()))
        {
            Combat();
        }else if(!(entity.sprite.getX() >=768)) {
            entity.sprite.setX(entity.sprite.getX() + MainActivity.GameSettings.GRID_SIZE);
        }
    }

    private void moveLeft(Entity entity) {

        if((player.sprite.getX() - MainActivity.GameSettings.GRID_SIZE == enemy.sprite.getX()) &&
            (player.sprite.getY() == enemy.sprite.getY()) ||
            (enemy.sprite.getX() - MainActivity.GameSettings.GRID_SIZE == player.sprite.getX()) &&
            (player.sprite.getY() == enemy.sprite.getY()))
        {

            Combat();
        }else if(!(entity.sprite.getX() <= 0)) {
            entity.sprite.setX(entity.sprite.getX() - MainActivity.GameSettings.GRID_SIZE);
        }
    }

    private void moveEnemy() {
        Random rand = new Random();
        int choice = rand.nextInt(5);
        boolean check = false;
        while(!check) {
            switch (choice) {
                case 0://up
                    moveUp(enemy);
                    check = true;
                    break;
                case 1://down
                    moveDown(enemy);
                    check = true;
                    break;
                case 2://left
                    moveLeft(enemy);
                    check = true;
                    break;
                case 3://right
                    moveRight(enemy);
                    check = true;
                    break;
                case 4://don't move
                    check=true;
                    break;
            }
            System.out.println("Enemy X = " + enemy.sprite.getX() + ", Enemy Y = " + enemy.sprite.getY());
        }

    }


    private void Combat(){
        System.out.println(("Combat initialized!"));
        Random random = new Random();

        float playerDamage = Math.round(((player.getStrength() - enemy.getDefense()) / 2f) *(0.8f + (1.2f - 0.8f) * random.nextFloat()));
        float enemyDamage = Math.round(((enemy.getStrength() - player.getDefense()) /2f) *(0.8f + (1.2f - 0.8f) * random.nextFloat()));

        enemy.setCurrentHP(enemy.getCurrentHP() - playerDamage);
        player.setCurrentHP(player.getCurrentHP() - enemyDamage);
        System.out.println("Player Damage = " + playerDamage);
        System.out.println("Enemy Damage = " + enemyDamage);
        String playerDamageText = String.valueOf(playerDamage * -1);
        String enemyDamageText = String.valueOf(enemyDamage * -1);

        playerTextDisplay.setText(enemyDamageText);
        playerTextDisplay.setTextColor(Color.RED);
        playerTextDisplay.setVisibility(View.VISIBLE);

        enemyTextDisplay.setText(playerDamageText);
        enemyTextDisplay.setTextColor(Color.RED);
        enemyTextDisplay.setVisibility(View.VISIBLE);

        playerTextDisplay.postDelayed(new Runnable(){
            @Override
                    public void run(){
                playerTextDisplay.setVisibility(View.INVISIBLE);
                enemyTextDisplay.setVisibility(View.INVISIBLE);

            }
        }, 2000);
        /*
        if(enemy.getCurrentHP() <= 0){
            enemy = new Entity("Enemy", 15, 10, 5, enemySprite);

        }
        */
    }
}
