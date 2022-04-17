package com.monster.gdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	//ShapeRenderer sr;
//Color c;
	SpriteBatch b;
	Map map;
	Menu menu;
	Gameplay game;
	//BitmapFont font;
//int x = 0;
//int y = 0;
//int[] p = {x, y};
	int state = 0;
	String path = "x";
	// --Commented out by Inspection (08/04/2022 17:59):int completedLevels = 0;
	final int screenWidth = 1000;
	final int screenHeight = 1000;
	final int rows = 50;
	final int cols = 50;
	final int tileWidth = screenWidth / cols;
	final int tileHeight = screenHeight / rows;
	int[][] terrainMap;
	boolean loop = false;
	boolean loaded = false;
	boolean nextTurn = false;
	String playing = "p";
	boolean click = false;
	int mouseX = 0;
	int mouseY = 0;

	@Override
	public void create() {
//sr = new ShapeRenderer();
//c= new Color();
		b = new SpriteBatch();
//img = new Texture("\\\\FILE\\UserFolders$\\Students\\Intake2020\\BurrowsT20\\Documents\\libGDX\\theoburrows-project-core\\assets\\sausageroll.png");
//font= new BitmapFont(); //or use alex answer to use custom font
		map = new Map();
		menu = new Menu();
		game = new Gameplay();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		//DETECT MOUSE CLIKC ONCE, HERE, AND PASS TO MOVEMENT. DETECT CURRENT STATE (0- DO REST OF LOGIC, 1- DO NOTHING AS WAITING FOR CLICK AND SELECT UNIT, 2- SET NEXTPOS X AND Y TO CURRENT MOUSE COORDS, PASS TO UPDATE SELECTED UNIT
		//RESET TO 0 AS SSON AS NEW POSITION HAS BEEN SET
		//TRACK WHAT TURN IT IS, SO YOU CAN RESET WHETER A UNIT CAN MOVE OR NOT
		if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
			click = true;
		}
		mouseX = Gdx.input.getX();
		mouseY = 1000 - Gdx.input.getY();
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			state++;
		}
		if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE)) {
			state--;
		}
		////NOTE: Menu follows original, non inverted Y (where 0,0 is top left)
 /*System.out.println("X:");
 System.out.println(mouseX);
 System.out.println("Y:");
 System.out.println(mouseY);*/
		if (state == 0) {
			menu.updateMain(state, click, mouseX, mouseY);
			click = false;
			path = menu.getPath();
			if (!path.equals("x")){
				state = 1;
				menu.resetButtons();
			}
		}else{
			if (click && ((mouseX >= 0) && (mouseX <= tileWidth)) && ((mouseY >= (1000 - tileHeight)) && (mouseY <= 1000))) {
				state = 0;
			}
			if (state == 1) {
				if (loaded == false) {
					terrainMap = map.updateMap(path);
					loaded = game.init(terrainMap);
				}

			}
		}

		if ((state > 0) && (!path.equals("x"))) {
    /*Texture mainMenu = new Texture("H:\\Project\\assets\\INVASIONtitlepage.png");
    b.begin();
    b.draw(mainMenu, 0, 950, 50, 50);
    b.end();*/
       /*System.out.println(mouseX);
       System.out.println(mouseY);*/
			/*if (click && ((mouseX >= 0) && (mouseX <= tileWidth)) && ((mouseY >= (1000 - tileHeight)) && (mouseY <= 1000))) {
				state = 0;
			}*/

			/*if (loaded == 0) {
				terrainMap = map.updateMap(path);
				//System.out.println("before");
				game.load(path, terrainMap);//very laggy if repeated
				//System.out.println("after");
				loaded++;
			}*/
			//System.out.println(playing);
			if (nextTurn) {
				System.out.println("next turn");
			}
			//if (inTurn == false) {

			map.drawMap();
			game.drawUnits();

			if (playing.equals("p")) {
				//player turn
				//System.out.println("player");
				if (nextTurn) {
					nextTurn = false;
					game.resetUnits();
					playing = "e";
				} else {
					playerTurn();
				}
			}
			if (playing.equals("e")) {
				//enemy turn
				//System.out.println("enemy");
				if (nextTurn) {
					nextTurn = false;
					game.resetUnits();
					playing = "p";
				} else {
					enemyTurn();
				}
			}
			//}
			//enemyTurn();
			click = false;
		}
		/*
		potential main restructuring:
		state = 0
		-menu, returns path
		state = 1
		-draw map
		-placing bases
		state = 2
		-planning phase
		state = 3
		-actions
		 */
	}

	//messing around with resume/load, resumed in and a unit was selected
//clicked on player unit, drew an enemy over the top and enemy remained in the corner
/*e
e
invalid enemy move
#
# A fatal error has been detected by the Java Runtime Environment:
#
#  EXCEPTION_ACCESS_VIOLATION (0xc0000005) at pc=0x00007ffd931d44d3, pid=18580, tid=17176
#
# JRE version: Java(TM) SE Runtime Environment (15.0.1+9) (build 15.0.1+9-18)
# Java VM: Java HotSpot(TM) 64-Bit Server VM (15.0.1+9-18, mixed mode, sharing, tiered, compressed oops, g1 gc, windows-amd64)
# Problematic frame:
# C  [msvcrt.dll+0x744d3]
#
# No core dump will be written. Minidumps are not enabled by default on client versions of Windows
#
# An error report file with more information is saved as:
# C:\Users\theob\OneDrive\Documents\School\Sir Isaac\CS\Java\libGDX\desktop\hs_err_pid18580.log
#
# If you would like to submit a bug report, please visit:
#   https://bugreport.java.com/bugreport/crash.jsp
# The crash happened outside the Java Virtual Machine in native code.
# See problematic frame for where to report the bug.
#*/
	private void playerTurn() {
		//System.out.println("player turn");
		nextTurn = game.turn(path, terrainMap,"p", click, mouseX, mouseY);
	}

	private void enemyTurn() {
		//System.out.println("enemy turn");
		nextTurn = game.turn(path, terrainMap,"e", click, mouseX, mouseY);
	}
 /*if (loop == true){
    map.updateMap(path);
    if (loaded == 0) {
       units.load(path);
       loaded++;
    }
    //player turn
    level = units.turn(click, mouseX, mouseY);
    click = false;
 }*/
	//move(p);
 /*int mouseX = Gdx.input.getX();
 System.out.println(mouseX);
 int mouseY = Gdx.input.getY();
 System.out.println(mouseY);*/

	//batch.begin();
	//batch.draw(img, p[0], p[1], 100, 100);
	//font.draw(batch, "Hello World!", 10, 10);
//batch.end();

	@Override
	public void dispose() {
//sr.dispose();
		b.dispose();
	}
/*private int[] move(int [] p) {
 if(Gdx.input.isKeyJustPressed(Keys.A)) {
    x-=100;
 }
 if(Gdx.input.isKeyJustPressed(Keys.D)) {
    x+=100;
 }
 if(Gdx.input.isKeyJustPressed(Keys.S)) {
    y-=100;
 }
 if(Gdx.input.isKeyJustPressed(Keys.W)) {
    y+=100;
 }
 p[0] = x;
 p[1] = y;
 return p;
}*/
}
