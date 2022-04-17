package com.monster.gdxgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Map {
	final SpriteBatch b = new SpriteBatch();
	final int rows = 50;
	final int cols = 50;
	final int screenWidth = 1000;
	final int screenHeight = 1000;
	final int tileWidth = screenWidth / cols;
	final int tileHeight = screenHeight / rows;
	//final ShapeRenderer sr = new ShapeRenderer();
	//final Color c = new Color();
	int[][] baseMap = new int[rows][cols];
	Tile[][] tiles = new Tile[rows][cols];
	boolean fileOpened = false;
	/*final Texture desert = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\desert.png");
	final Texture plains = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\plains.png");*/
	final Texture mainMenu = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\INVASIONtitlepage.png");
	Texture t = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\testsq2.png");
	final Texture ocean = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\ocean.png");
	final Texture rubble = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\rubble.png");
	final Texture plains = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\plains.png");
	final Texture desert = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\desert.png");
	final Texture gold = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\gold.png");

	public int[][] updateMap(String path) {
		path = path + "\\map.txt";
		fileOpened = false;
		readFile(path);
		drawMap();
		return baseMap;
	}

	private void readFile(String path) {
		//int[][] baseMap = new int[rows][cols];
		if (!fileOpened) {
			try {
				Scanner s = new Scanner(new BufferedReader(new FileReader(path)));
				for (int i = 0; i < rows; i++) {
					String[] line = s.nextLine().trim().split(" ");
					//System.out.println("\n");
					for (int j = 0; j < cols; j++) {
						//System.out.print(Integer.parseInt(line[j]));
						baseMap[i][j] = Integer.parseInt(line[j]);
						//tiles[i][j] = new Tile(i, j, baseMap[i][j]);
					}
				}
				s.close();
				fileOpened = true;
			} catch (Exception e) {
				System.out.println("error in the try (map)");
			}
		}
		//System.out.println(fileOpened);
		/*for (int y = 0; y < rows; y++){
			for (int x = 0; x < cols; x++){
				//tiles[y][x] = new Tile(y, x, baseMap[y][x], null);
			}
		}*/
	}

	public void drawMap() {
     /*for (int i = 0; i < rows; i++) {
        System.out.println();
        for (int j = 0; j < cols; j++) {
           System.out.print(baseMap[i][j]);
     }*/
		b.begin();
		//sr.begin(ShapeType.Filled);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int x = convertX(j);
				int y = convertY(i);
				if (i == 0 && j == 0) {
					b.draw(mainMenu, x, y, tileWidth, tileHeight);
				}else{
					if (baseMap[i][j] == 0) {
						if ((j == 0 || j == rows-1) || (i == 0 || i == cols-1)) {
							t = ocean;
						}else{
							t = rubble;
						}
					}
					if (baseMap[i][j] == 1) {
						t = plains;
					}
					if (baseMap[i][j] == 2) {
						t = desert;
					}
					if (baseMap[i][j] == 3) {
						t = gold;
					}
					b.draw(t, x, y, tileWidth, tileHeight);
				}

				/*if (tiles[i][j] == 0) {
					c.set(0, 0, 100, 0);
					sr.setColor(c);
					if (i == 0 && j == 0) {
						b.draw(mainMenu, x, y, tileWidth, tileHeight);
					} else {
						sr.rect(x, y, tileWidth, tileHeight);
					}
				}
				if (baseMap[i][j] == 1) {
					b.draw(desert, x, y, tileWidth, tileHeight);
				}
				if (baseMap[i][j] == 2) {
					b.draw(plains, x, y, tileWidth, tileHeight);
				}*/
			}
		}
		b.end();
		//System.out.println("marker");
		//sr.end();
	}

	private int convertX(int n) {
		int x;
		x = (n * tileWidth);
		return x;
	}

	private int convertY(int n) {
		int y;
		y = (screenHeight - tileHeight) - n * tileHeight;
		return y;
	}
}
/*(screenHeight-tileHeight) - */
///////////////////////////////////////////////////////////////////could restructure something in GdxGame so that whenever I click, it converst to grid coordinates and performs action based on where I click
//grid in from text file
// how detailed? 1000x1000 into blocks of 10x10?
// 100x100 grid
//0 is empty space, 1 is ______ etc.
//player and enemy positions, items interacted with etc. updated
//no written back into file, only stored as a current (volatile) variable.

//for (int i = rows-1; i > -1; i--) {
//for (int i = 0; i < rows; i++) {
