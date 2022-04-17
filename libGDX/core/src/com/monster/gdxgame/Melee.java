package com.monster.gdxgame;

import com.badlogic.gdx.graphics.Texture;

public class Melee extends Unit{
    public Melee(int c, int xPos, int yPos, char o) {
        super(c, xPos, yPos, o);
        type = 'm';
        health = 20;
        attack = 10;
        defence = 5;
        range = 0;
        rangedAttack = 0;
    }
}
