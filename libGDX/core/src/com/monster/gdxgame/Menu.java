package com.monster.gdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;

public class Menu {
	// --Commented out by Inspection (08/04/2022 17:59):Map map = new Map();
	final SpriteBatch b = new SpriteBatch();
// --Commented out by Inspection START (08/04/2022 17:59):
	boolean click;
	int mouseX;
	int mouseY;
// --Commented out by Inspection STOP (08/04/2022 17:59)
	int level;
	boolean mousePressed = false;
	boolean playPressed = false;
	boolean guidePressed = false;
	boolean loadPressed = false;
	String filePath = "x";
	final Texture mainMenu = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\INVASIONtitlepage.png");
	final Texture play = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\playbutton.png");
	final Texture guide = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\guidebutton.png");
	final Texture close = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\closebutton.png");
	final Texture resume = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\resumebutton.png");
	final Texture load = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\loadbutton.png");
	final Texture start = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\newbutton.png");
	final Texture delete = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\deletebutton.png");
	final Texture controls = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\controlbutton.png");
	final Texture overview = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\tipsbutton.png");
	final Texture story = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\storybutton.png");
	final Texture saves = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\savesScreen.png");
	final Texture one = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\saveOne.png");
	final Texture two = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\saveTwo.png");
	final Texture three = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\saveThree.png");

	public int updateMain(int l, boolean c, int X, int Y) {
		//System.out.println(loadPressed);
		click = c;
		mouseX = X;
		mouseY = Y;
		level = l;//removal?
		drawMain();
		String option = buttonsDictionary();
		level = buttonsActions(option);
		if (loadPressed) {
			int save = showSaves();
			String path = loadOption(save);
			if (!path.equals("x")) {
				filePath = path;
				level++;
				//map.updateMap(path, level);
			}
		}
		return level;
	}

	public String getPath() {
		return filePath;
	}

	private void drawMain() {
		// Y IS INVERTED HERE, CAN BE FIXED
		b.begin();
		b.draw(mainMenu, 0, 0, 1000, 1000);
		b.draw(play, 530, 570, 100, 50);
		b.draw(guide, 530, 470, 100, 50);
		b.draw(close, 530, 370, 100, 50);
		//if (mousePressed == true) {}
		int Y = 1000 - mouseY;
		if (((mouseX >= 530 && mouseX <= 630) && (Y > 370 && Y <= 420)) || (playPressed)) {
			guidePressed = false;
			if (click) {
				playPressed = true;
				guidePressed = false;
			}
			b.draw(resume, 680, 570, 100, 50);
			b.draw(load, 680, 470, 100, 50);
			b.draw(start, 680, 370, 100, 50);
			b.draw(delete, 680, 270, 100, 50);
		}
		if (((mouseX >= 530 && mouseX <= 630) && (Y > 470 && Y <= 520)) || (guidePressed)) {
			playPressed = false;
			if (click) {
				guidePressed = true;
				playPressed = false;
			}
			b.draw(controls, 680, 570, 100, 50);
			b.draw(overview, 680, 470, 100, 50);
			b.draw(story, 680, 370, 100, 50);
		}

		// && (loadPressed = false)
		if ((((mouseX >= 530 && mouseX <= 630) && (Y > 570 && Y <= 620)))) {
			playPressed = false;
			guidePressed = false;
			//loadPressed = false;
			if (click && (!loadPressed)) {
				//System.out.println("closed by main menu");
				b.end();
				b.dispose();
				System.exit(0);
			}
		}
		b.end();
	}

	private String buttonsDictionary() {
		String pressed = "";
		int Y = 1000 - mouseY;
		if (playPressed && click) {
			if ((mouseX >= 680 && mouseX <= 780) && (Y > 370 && Y <= 420)) {
				pressed = "resume";
			}
			if ((mouseX >= 680 && mouseX <= 780) && (Y > 470 && Y <= 520)) {
				pressed = "load";
			}
			if ((mouseX >= 680 && mouseX <= 780) && (Y > 570 && Y <= 620)) {
				pressed = "start";
			}
			if ((mouseX >= 680 && mouseX <= 780) && (Y > 670 && Y <= 720)) {
				pressed = "delete";
			}
		}
		if (guidePressed) {
			if ((mouseX >= 680 && mouseX <= 780) && (Y > 370 && Y <= 420)) {
				pressed = "controls";
			}
			if ((mouseX >= 680 && mouseX <= 780) && (Y > 470 && Y <= 520)) {
				pressed = "tips";
			}
			if ((mouseX >= 680 && mouseX <= 780) && (Y > 570 && Y <= 620)) {
				pressed = "story";
			}
		}
		return pressed;
	}

	private int buttonsActions(String button) {
		if (level == 0) {
			switch (button) {
				case "resume":
					level++;
				case "load":
					loadPressed = true;
				case "start":
				case "delete":

				case "controls":
					//add after gameplay
				case "tips":
					//add once gameplay is mostly done
				case "story":
					//Going to be one of the last things in, purely a QOL
			}
		}
		return level;
	}

	private int showSaves() {
		b.begin();
		b.draw(saves, 100, 100, 800, 800);
		b.draw(close, 450, 150, 100, 50);
		b.draw(one, 330, 550, 345, 100);
		b.draw(two, 330, 400, 345, 100);
		b.draw(three, 330, 250, 345, 100);
		b.end();
		File options = new File("C:\\Users\\theob\\OneDrive\\Documents\\assets\\saves\\");
		//will retrieve save data from here and display it---------------------------
		int save = -1;
		if (click) {
     /*538
     407
     error in the try (units)*/
			if ((mouseX >= 330 && mouseX <= 675) && (mouseY >= 550 && mouseY <= 650)) {
				save = 1;
			}
			if ((mouseX >= 330 && mouseX <= 675) && (mouseY >= 400 && mouseY <= 500)) {
				save = 2;
			}
			if ((mouseX >= 330 && mouseX <= 675) && (mouseY >= 250 && mouseY <= 350)) {
				save = 3;
			}
			if ((mouseX >= 450 && mouseX <= 550) && (mouseY >= 150 && mouseY <= 200)) {
				loadPressed = false;
			}
		}
		return save;
	}

	private String loadOption(int save) {
		if (save == -1) {
			return "x";
		} else {
			String base = "C:\\Users\\theob\\OneDrive\\Documents\\assets\\saves\\";
			String num = String.valueOf(save);
			String path = base + num;
			return path;
		}
	}

	public void resetButtons() {
		playPressed = false;
		guidePressed = false;
		loadPressed = false;
	}
}
///////////////////////////////////////////////////////////////////////////////BIG NOTE TO SELF, MAKE BUTTONS WORK WHEN WINDOW IS RESIZED!
/*File[] optionList = options.listFiles();
String[] optionStrings = new String[3];
for (int i = 0; i < optionList.length; i++) {
  optionStrings[i] = Files.readString(Paths.get(optionList[i]));
}
(optionList);*/
/*for (int i = 0; i < optionList.length; i++) {
 if (optionList[i].isFile()) {
   System.out.println("File " + optionList[i].getName());
 } else if (optionList[i].isDirectory()) {
   System.out.println("Directory " + optionList[i].getName());
 }
}*/
/*for (int i = 0; i < a.length(); i++) {
c += a.charAt(i);
if (i == 28) {
   c = b;
}
}*/
//optionList[0].renameTo(n);
