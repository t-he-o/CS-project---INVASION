package com.monster.gdxgame;

public class Base {
    String owner;

    int x;
    int y;

    float health;
    int resources;
    public Base(int X, int Y){
        x = X;
        y = Y;
        health = 1;
        resources = 0;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setHealth(float h){
        health = h;
    }

    public float getHealth(){
        return health;
    }

    public void setResources(int r){
        resources = r;
    }

    public int getResources(){
        return resources;
    }


}
