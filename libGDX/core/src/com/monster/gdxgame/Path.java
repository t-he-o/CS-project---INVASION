package com.monster.gdxgame;

import java.util.ArrayList;

public class Path {
    int length;
    ArrayList<Tile> steps = new ArrayList<Tile>();
    public Path(){

    }
    public int size(){
        return length;
    }
    public void prependStep(Tile s){
        steps.add(0, s);
        length++;
    }
    public void appendStep(Tile s){
        steps.add(s);
        length++;
    }
    public void clear(){
        length = 0;
        steps.clear();
    }
    public Tile getStep(int index){
        return steps.get(index);
    }
    public boolean contains(Tile t){
        boolean contains = false;
        if (steps.contains(t)){
            contains = true;
        }
        return contains;
    }
}
