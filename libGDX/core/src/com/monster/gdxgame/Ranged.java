package com.monster.gdxgame;

import com.badlogic.gdx.graphics.Texture;

public class Ranged extends Unit{
    public Ranged(int c, int xPos, int yPos, char o) {
        super(c, xPos, yPos, o);
        type = 'r';
        health = 10;
        attack = 3;
        defence = 3;
        range = 20;
        rangedAttack = 14;
        maxStamina = 15;
        if (owner == 'p'){
            texture = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\playerRanged.png");
        }else if (owner == 'e'){
            texture = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\enemyRanged.png");
        }
    }
}
