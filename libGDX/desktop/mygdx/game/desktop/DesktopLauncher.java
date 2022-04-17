package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.monster.gdxgame.MyGdxGame;

public class DesktopLauncher extends MyGdxGame{
    public static void main (String[] arg) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "project";
        cfg.width = 1000;
        cfg.height = 1000;
        new LwjglApplication(new MyGdxGame (), cfg);
}}

//file paths:
//college: H:\\Project
//home: C:\\Users\\theob\\OneDrive\\Documents

/*Texture mainMenu = new Texture("H:\\Project\\assets\\INVASIONtitlepage.png");
Texture play = new Texture("H:\\Project\\assets\\menuButtons\\playbutton.png");
Texture guide = new Texture("H:\\Project\\assets\\menuButtons\\guidebutton.png");
Texture close = new Texture("H:\\Project\\assets\\menuButtons\\closebutton.png");
Texture resume = new Texture("H:\\Project\\assets\\menuButtons\\resumebutton.png");
Texture load = new Texture("H:\\Project\\assets\\menuButtons\\loadbutton.png");
Texture start = new Texture("H:\\Project\\assets\\menuButtons\\newbutton.png");
Texture delete = new Texture("H:\\Project\\assets\\menuButtons\\deletebutton.png");
Texture controls = new Texture("H:\\Project\\assets\\menuButtons\\controlbutton.png");
Texture overview = new Texture("H:\\Project\\assets\\menuButtons\\tipsbutton.png");
Texture story = new Texture("H:\\Project\\assets\\menuButtons\\storybutton.png");
Texture saves = new Texture("H:\\Project\\assets\\menuButtons\\savesScreen.png");
Texture one = new Texture("H:\\Project\\assets\\menuButtons\\saveOne.png");
Texture two = new Texture("H:\\Project\\assets\\menuButtons\\saveTwo.png");
Texture three = new Texture("H:\\Project\\assets\\menuButtons\\saveThree.png");*/