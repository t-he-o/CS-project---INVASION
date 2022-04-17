package com.monster.gdxgame;

import com.badlogic.gdx.graphics.Texture;

public class Unit {
	float health;
	float attack;
	float defence;
	int range;
	int rangedAttack;
	int maxStamina;
	int stamina;
	int targetScore;
	final int code;
	char type;
	final char owner;
	int x;
	int y;
	int sX;
	int sY;
	int nX;
	int nY;
	Texture texture;
	boolean canMove;
	boolean canWork;
	boolean isWorking;
	boolean fortified;
	boolean selected;
	Tile target;

	public Unit(int c, int xPos, int yPos, char o) {
		code = c;
		owner = o;
		sX = xPos;
		sY = yPos;
		x = sX;
		y = sY;

		health = 10;
		maxStamina = 10;
		stamina = maxStamina;
		attack = 4;
		defence = 4;
		range = 4;
		rangedAttack = 4;

		canMove = true;
		selected = false;
		canWork = false;
		fortified = false;
		draw();
	}

	public int getCode() {
		return code;
	}

	public char getType() {
		return type;
	}

	public char getOwner() {
		return owner;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float h) {
		health = h;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public void setStamina(int m) {
		stamina = m;
	}

	public int getStamina() {
		return stamina;
	}

	public void setAttack(float a) {
		attack = a;
	}

	public float getAttack() {
		return attack;
	}

	public void setDefence(float d) {
		defence = d;
	}

	public float getDefence() {
		return defence;
	}

	public int getRangedAttack() {
		return rangedAttack;
	}

	public int getRange() {
		return range;
	}

	public int getStartX(){
		return sX;
	}

	public int getStartY(){
		return sY;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setX(int nx){
		x = nx;
	}

	public void setY(int ny){
		y = ny;
	}

	public int[] getCoords() {
		int[] coords = new int[2];
		coords[0] = x;
		coords[1] = y;
		return coords;
	}

	public void setCoords(int[] coords) {
		x = coords[0];
		y = coords[1];
		setMove(false);
    /*System.out.println("coords changed:");
    System.out.println(x);
    System.out.println(y);*/
		draw();
	}

	public void setMove(boolean m) {
		canMove = m;
	}

	public boolean canMove() {
		return canMove;
	}

	public boolean getSelected() {
		return selected;
	}

	public void setSelected(boolean s) {
		selected = s;
	}

	public boolean getWorking(){return isWorking;}

	public void draw() {
		Gameplay u = new Gameplay();
		//u.draw(this, owner, x, y, selected);
	}

	public void setScore(int d){
		targetScore = d;
	}

	public int getScore(){return targetScore;}

	public void setTarget(Tile t){
		target = t;
	}

	public Tile getTarget(){
		return target;
	}

	public Texture getTexture(){return texture;}
}
