package com.mygdx.game;

	public class DrawingFunctions {
		public void drawWalls() {
			sr.setColor(Color.WHITE);
			sr.rectangle(wallx, wally, 100,100);
			sr.setColor(Color.BLACK);
			sr.circle((wallx+50),(wally+50),25);
		}
		public void drawGems() {
			sr.setColor(Color.CYAN);
			sr.polygon((gemx+50,gemy),(gemx,gemy+50),(gemx+100,gemy+50),(gemx+20,gemy+80),(gemx+80,gemy+80));
			sr.setColor(Color.BLACK);
			sr.line((gemx+50,gemy));
		}
	}
