package com.monster.gdxgame;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
    /*final int rows = 50;
    final int cols = 50;
    final int screenWidth = 1000;
    final int screenHeight = 1000;
    final int tileWidth = screenWidth / cols;
    final int tileHeight = screenHeight / rows;*/
    int row;
    int col;
    Tile parent;
    Tile target;
    int toStart;
    int toTarget;
    int cost;
    int movementCost;
    int heuristic;
    char occupantType;
    boolean blocked;
    boolean occupied;
    Unit occupant;
    int type;
    Texture texture;
    public Tile(int r, int c/*, int t*/){
        row = r;
        col = c;
        occupant = null;
        occupantType = 'x';
        //type = t;
        /*if (type == 0) {
            blocked = true;
            occupied = false;
            if ((row == 0 || row == rows) && (col == 0 || col == cols)) {
                texture = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\ocean.png");
            }else{
                texture = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\rubble.png");
            }
        }else if (type == 1) {
            blocked = false;
            movementCost = 1;
            texture = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\plains.png");
        }else if (type == 2) {
            blocked = false;
            movementCost = 2;
            texture = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\desert.png");
        } else if (type == 3) {
            blocked = false;
            movementCost = 1;
            texture = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\gold.png");
        }*/
        /*parent = p;
        target = t;
        toStart = trav;
        toTarget = calcToTarget();*/
    }
    public int getX (){
        return col;
    }
    public int getY (){
        return row;
    }
    public boolean getBlocked(){return blocked;}
    public boolean getOccupied() {
        return occupied;
    }
    public void setOccupantUnit(Unit o){
        occupied = true;
        occupant = o;
        occupantType = 'u';
    }
    public void setOccupantBase(){
        occupied = true;
        occupantType = 'b';
    }
    public char getOccupantType() {
        return occupantType;
    }
    public Unit getOccupantUnit(){
        return occupant;}
    public void clearOccupant(){
        occupant = null;
        occupantType = 'x';
        occupied = false;
    }
    public void setCost(int c){
        cost = c;
    }
    public void setHeuristic(int tX, int tY) {
        //distance to target
        int diffX = tX - col;
        if (diffX < 0) {
            diffX = -diffX;
        }
        int diffY = tY - row;
        if (diffY < 0) {
            diffY = -diffY;
        }
        heuristic = diffX + diffY;

    }
    public int getHeuristic(){
        return heuristic;
    }
    public int getCost(){
        return cost;
    }
    public void setMovementCost(int m){
        movementCost = m;
    }
    public int getMovementCost(){
        return movementCost;
    }
    public void setParent(Tile p){
        parent = p;
    }
    public Tile getParent(){
        return parent;
    }
    public Texture getTexture(){return texture;}
}
