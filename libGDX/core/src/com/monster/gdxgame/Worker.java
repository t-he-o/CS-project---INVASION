package com.monster.gdxgame;

import com.badlogic.gdx.graphics.Texture;

public class Worker extends Unit{
    public Worker(int c, int xPos, int yPos, char o) {
        super(c, xPos, yPos, o);
        type = 'w';
        health = 15;
        attack = 5;
        defence = 5;
        range = 0;
        rangedAttack = 0;
        canWork = true;
        //target needs to be resource tiles
        if (owner == 'p'){
            texture = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\playerWorker.png");
        }else if (owner == 'e'){
            texture = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\enemyWorker.png");
        }
    }
}
