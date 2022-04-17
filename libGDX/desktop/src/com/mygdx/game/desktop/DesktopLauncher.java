package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.monster.gdxgame.MyGdxGame;

public class DesktopLauncher extends MyGdxGame{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "project";
		cfg.width = 1000;
		cfg.height = 1000;
		new LwjglApplication(new MyGdxGame (), cfg);
	}}
//H:\\Project
//C:\\Users\\theob\\OneDrive\\Documents

/*
PLAN:
-change output of unit stats to system.out.println /
-investigate selection logic /
-test combat; enemy combat does not work

-converting to using grid more
	-have passed map to units/
	-change all references of map (currently checkmoce only)/
	-look at unitMap (currently read, will need to change to place them)/
	-ask about that
	-write unit code into unitMap, so can reference the correct unit
	-will help combat -- need to update playerMap upon move
	-need to rethink how the logic of addressing a grid using mouse x and y coordinates works (should it go grid[x][y], or [y][x]. should the y be inverted?)

-movement logic
-email sir for pathfinding complication (after planning)
-terrain
	-change black squares into oceans or mountains
	-change map process from literal designations into directions as to whether something can be placed there
	-allows for some random generation
-pathfinding
	-grid-based
	-
* */