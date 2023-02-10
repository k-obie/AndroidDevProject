package com.example.collisiondetection;

import android.widget.ImageView;

public class Entity
{
    ImageView sprite;
    private String name;
    private float maxHP;
    private float currentHP;
    private float strength;
    private float defense;

    public Entity(){
        name = "DEFAULT";
        maxHP = -1;
        currentHP = maxHP;
        strength = -1;
        defense = -1;
    }
    public Entity(String name, float maxHP, float strength, float defense, ImageView sprite){
        setName(name);
        setMaxHP(maxHP);
        setCurrentHP(maxHP);
        setStrength(strength);
        setDefense(defense);
        setSprite(sprite);
    }
    public void setName(String input){this.name = input;}
    public String getName(){return this.name;}
    public void setMaxHP(float input){this.maxHP = input;}
    public float getMaxHP(){return this.maxHP;}
    public void setCurrentHP(float input){this.currentHP = input;}
    public float getCurrentHP(){return this.currentHP;}
    public void setStrength(float input){this.strength = input;}
    public float getStrength(){return this.strength;}
    public void setDefense(float input){this.defense = input;}
    public float getDefense(){return this.defense;}
    public void setSprite(ImageView input){this.sprite = input;}
    public ImageView getSprite(){return this.sprite;}

}
