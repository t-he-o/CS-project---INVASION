package com.monster.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player {
	public int[] movement (int Px, int Py, int[] wallx, int[] wally) {
		int[] P=new int[2];
		P[0]=Px;
		P[1]=Py;
		boolean check=false;
			if (Gdx.input.isKeyJustPressed(Keys.D)) {
				Px++;
			}
			if (Gdx.input.isKeyJustPressed(Keys.A)) {
				Px--;
			}
			if (Gdx.input.isKeyJustPressed(Keys.W)) {
				Py++;
			}
			if (Gdx.input.isKeyJustPressed(Keys.S)) {
				Py--;
			}
			if (Px<1) {
				
			}
			if (Px>10) {
				
			}
			if (Py<1) {
				
			}
			if (Py>10) {
				
			}
		if (check==true) {
			return P;
		}
		else {
			int[] oP=new int[2];
			oP[0]=Px;
			oP[1]=Py;
			return oP;
		}
	}
	public boolean getEnd(boolean endscreen) {
			return endscreen;
	}
}
