package com.monster.gdxgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Gameplay {
	final int rows = 50;
	final int cols = 50;
	final int screenWidth = 1000;
	final int screenHeight = 1000;
	final int tileWidth = screenWidth / cols;
	final int tileHeight = screenHeight / rows;
	String playing;
	// --Commented out by Inspection (08/04/2022 17:59):int code = -1;
	// --Commented out by Inspection (08/04/2022 17:59):int clickNumber = 0;
	boolean click;
	boolean overUnit = false;
	boolean complete;
	boolean actionInProgress = false;
	int mouseX;
	int mouseY;
	int mX;
	int mY;

	Base player = null;
	Base enemy = null;

	int[][] unitMap;
	int[][] playerMap;
	int[][] enemyMap;
	int[][] map;
	Tile[][] tiles = new Tile[rows][cols];
	int moveState = 0;
	int gameState = 0;
	int unitNum = 0;
	int enemyNum = 0;
	String lastPath;
	ArrayList<Unit> playerList;
	// --Commented out by Inspection START (08/04/2022 17:59):
	ArrayList<Unit> enemyList;
	ArrayList<Tile> open = new ArrayList<Tile>();
	//Queue<Tile> q = new PriorityQueue<Tile>();
	ArrayList<Tile> closed = new ArrayList<Tile>();//HashSet?
	final SpriteBatch b = new SpriteBatch();
	// --Commented out by Inspection STOP (08/04/2022 17:59)
	final ShapeRenderer sr = new ShapeRenderer();
	final BitmapFont f = new BitmapFont();
	final Color c = new Color();
	/*final Texture ts1 = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\testsq.png");
    final Texture ts2 = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\testsq2.png");*/
	final Texture blankButton = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\menuButtons\\blankmenubutton.png");

	public boolean init(int[][] m) {
		map = m;
		if (enemy == null){
			enemyPlaceBase();
			return false;
		}else if (player == null){
			placeBase();
			return false;
		}else{
			return true;
		}

		//lastPath = path;
		//System.out.println("marker 1");
		//read(path);
		//System.out.println("marker 2");
		//create();
	}
	private void enemyPlaceBase(){
		Random random = new Random();
		int corner = random.nextInt(4);
		int x = 0;
		int y = 0;
		switch (corner) {
			case 0:
				x = 1;
				y = 1;
				break;
			case 1:
				x = 1;
				y = rows - 2;
				break;
			case 2:
				x = cols - 2;
				y = 1;
				break;
			case 3:
				x = cols - 2;
				y = rows - 2;
				break;
		}
		enemy = new Base(x, y);
	}
	private void placeBase(){
		int x = 0;
		int y = 0;
		player = new Base(x, y);
	}
	public boolean turn(String path, int[][] m, String p, boolean c, int X, int Y) {
		complete = false;
		/*if (!path.equals("x") && !path.equals(lastPath)) {
			load(path,m);
		}*/
     /*if (player.getHealth() == 0 || enemy.getHealth() == 0){
        System.out.println("game ended");
        System.exit(0);
     }*/
		playing = p;
		click = c;
		mouseX = X;
		mouseY = 1000 - Y;
		mX = convertCol(mouseX);
		mY = convertRow(mouseY);
		for (com.monster.gdxgame.Unit u : playerList) {
			u.draw();
		}
		for (com.monster.gdxgame.Unit e : enemyList) {
			e.draw();
		}
		checkMouseTile();
		if (gameState == 0){
			//random enemy base in a corner?
			//place base in an unoccupied corner
		}else if (gameState == 1){
			//enemies spawn in random controlled area around base
			//wait for button input (menu inspiration?)
			//require clicking of button to end placement phase
		}else if (gameState == 2){
			//one turn each
		}
		//checkmousetile will operate on the buttons that i will add
		//if base hasn't been placed, place it first thing
		//option to place units
     /*if (overUnit == true) {
        displayStats();
        overUnit = false;
     }*/
		actions();
		checkComplete();
		return complete;
	}
	public int[][] read(String path) {
		path = path + "\\units.txt";
		unitMap = new int[rows][cols];
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader(path)));
			for (int y = 0; y < rows; y++) {
				String[] line = s.nextLine().trim().split(" ");
				for (int x = 0; x < cols; x++) {
					unitMap[y][x] = Integer.parseInt(line[x]);
					tiles[y][x] = new Tile(y, x);
					//System.out.println("tile generated");
					//tiles[y][x] = new Tile(y, x/*, map[y][x]*/);
				}
			}
			s.close();
			//fileOpened = true;
		} catch (Exception e) {
			System.out.println("error in the try (units)");
		}
		//System.out.println(fileOpened);
		return unitMap;
	}
	private void create() {
		if (playerList != null){
			playerList.clear();
		}
		if (enemyList != null){
			enemyList.clear();
		}
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				if (unitMap[y][x] == 1) {
					unitNum++;
				}
				if (unitMap[y][x] == 2) {
					enemyNum++;
				}
			}
		}
		int[][] unitCoords = new int[unitNum][2];
		int[][] enemyCoords = new int[enemyNum][2];
     /*int nU = 0;
     int nE = 0;*/
		//String type = "soldier";
		int numP = 0;
		int numE = 0;
		playerMap = new int[rows][cols];
		enemyMap = new int[rows][cols];
		playerList = new ArrayList<Unit>(unitNum);
		enemyList = new ArrayList<Unit>(enemyNum);
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				//using map to update tiles
				//generating units
				if (unitMap[y][x] == 0) {
					playerMap[y][x] = -1;
					enemyMap[y][x] = -1;
				}
				if (unitMap[y][x] == 1) {
					Unit u = new Melee(numP, x, y, 'p');
					playerList.add(u);
					playerList.get(numP).draw();
					//tiles[y][x] = new Tile(y, x, map[y][x]);
					tiles[y][x].setOccupantUnit(u);
					playerMap[y][x] = numP;
					numP++;
				}
				if (unitMap[y][x] == 2) {
					Unit e = new Melee(numE, x, y, 'e');
					enemyList.add(e);
					enemyList.get(numE).draw();
					//tiles[y][x] = new Tile(y, x, map[y][x]);
					tiles[y][x].setOccupantUnit(e);
					enemyMap[y][x] = numE;
					numE++;
				}
			}
		}
	}
	public void drawUnits(){
		for (int i = 0; i < playerList.size(); i++) {
			draw(playerList.get(i));
		}
		for (int i = 0; i < enemyList.size(); i++) {
			draw(enemyList.get(i));
		}
	}
	private void draw(Unit u) {
		char owner = u.getOwner();
		boolean selected = u.getSelected();
		int x = u.getX();
		int y = u.getY();
		if (selected) {
			sr.begin(ShapeType.Line);
			c.set(200, 0, 0, 1);
			sr.setColor(c);
			sr.rect(convertX(x) - 5, convertY(y) - 5, tileWidth + 10, tileHeight + 10);
			sr.end();
			//sr.rect(screenWidth - tileWidth, screenHeight - tileHeight, tileWidth, tileHeight);
		}
		Texture t = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\testsq.png");
		if (owner == 'p'){
			if (u.getType() == 'm'){
				t = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\playerMelee.png");
			}else if (u.getType() == 'r'){
				t = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\playerRanged.png");
			}else if (u.getType() == 'w'){
				t = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\playerWorker.png");
			}
		}else if (owner == 'e'){
			if (u.getType() == 'm'){
				t = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\enemyMelee.png");
			}else if (u.getType() == 'r'){
				t = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\enemyRanged.png");
			}else if (u.getType() == 'w'){
				t = new Texture("C:\\Users\\theob\\OneDrive\\Documents\\assets\\tiles\\enemyWorker.png");
			}
		}
		//trying to et textures to work for units like they do for tiles
		b.begin();
		b.draw(t, convertX(x), convertY(y), tileWidth, tileHeight);
		b.end();
	}
	private int convertX(int n) {
		int x;
		x = (n * tileWidth);
		return x;
	}
	private int convertY(int n) {
		int y;
		y = ((rows - (n + 1)) * tileHeight);
		return y;
	}
	private int convertRow(int n) {
		int r;
		r = (n / tileHeight);
		return r;
	}
	private int convertCol(int n) {
		int c;
		c = (n / tileWidth);

		return c;
	}
	public void resetUnits() {
		//System.out.println("reset");
		if (playing.equals("p")) {
			//System.out.println("reset player");
			for (com.monster.gdxgame.Unit u: playerList) {
				u.setMove(true);
				u.setStamina(u.getMaxStamina());
				u.setHealth(u.getHealth() + 5);
				if (u.getWorking()) {
					//resources ++
				}
			}
			//player.setResources(player.getResources() + 100);
		}
		if (playing.equals("e")) {
			//System.out.println("reset enemy");
			for (com.monster.gdxgame.Unit e : enemyList) {
				e.setMove(true);
				e.setStamina(e.getMaxStamina());
				e.setHealth(e.getHealth() + 5);
				if (e.getWorking()){
					//resources ++
				}
			}
			//enemy.setResources(enemy.getResources() + 100);
		}
	}
	private void actions() {
		int nextX = mX;
		int nextY = mY;
		int[] coords = new int[2];
		boolean combat = false;
		//System.out.println(state);
		//problem: sometimes cycles through 2 before chance to move, click happens too fast
		//MOVEMENT WORKS, SOMETIMES 1 CLICK, SOMETIMES 2 CLICKS?
		if (playing.equals("e")) {
			for (com.monster.gdxgame.Unit e : enemyList) {
				if (e.canMove){
					enemyMap[e.getY()][e.getX()] = -1;
					targetChoice(e);
					Tile t = e.getTarget();
					pathfinding(e, t.getX(), t.getY(), t.getOccupantType());
					e.setMove(false);
					enemyMap[e.getY()][e.getX()] = e.getCode();
				}

              /*if (checkMove(e, coords)) {
                 if (e.canMove() && e.getSelected()) {
                    e.setSelected(false);
                    e.setMove(false);
                    enemyMap[e.getCoords()[1]][e.getCoords()[0]] = -1;
                    enemyMap[nextY][nextX] = e.getCode();
                    e.setCoords(coords);
                 }
              } else {
                 System.out.println("invalid enemy move");
                 e.setSelected(false);
              }*/
			}
		}
		if (playing.equals("p")){
			if (moveState == 2) {
				click = false;
				for (com.monster.gdxgame.Unit u : playerList) {
					if (checkReachable(u.getX(), u.getY(), nextX, nextY, u)) {
						//if (!checkForCombat(u, nextX, nextY)) {
						if (u.canMove() && u.getSelected()) {
							u.setSelected(false);
							playerMap[u.getCoords()[1]][u.getCoords()[0]] = -1;

							pathfinding(u, nextX, nextY, tiles[nextX][nextY].getOccupantType());
							playerMap[nextY][nextX] = u.getCode();
							u.setMove(false);
						}
						//}
					} else {
						u.setSelected(false);
					}
				}
				moveState = 0;
				actionInProgress = false;
			}
			if (moveState == 1) {
				b.begin();
				b.draw(blankButton, ((screenWidth / 2) - tileWidth), 0, tileWidth * 2, tileHeight);
				//ranged attack button
				//fortify button
				//if fortified on resource tile, gain resource?
				//make chek loop for turn events: gain health, gain resources
				b.end();
			}
			if (click) {
				click = false;
				if (moveState == 1) {
					if (!overUnit) {
						moveState = 2;
					}
					//cancel button
					if (((mX >= (convertX(screenWidth / 2) - tileWidth) && mX < (convertX(screenWidth / 2) + tileWidth)) && (mY >= 0 && mY < convertY(tileHeight)))) {
						moveState = 0;
						System.out.println("cancelled");
					}//////////////////////////////////////////////////////////////////////
				}else if (moveState == 0 && !actionInProgress) {
					//check for selected, if one is selected, set state to 1
					if (playing.equals("p")) {
						for (com.monster.gdxgame.Unit u : playerList) {
							coords = u.getCoords();
							if (((mX >= coords[0]) && (mX < (coords[0] + 1))) && ((mY >= coords[1]) && (mY < (coords[1] + 1)))) {
								if (u.canMove()) {
									actionInProgress = true;
									u.setSelected(true);
									moveState = 1;
									actions();
								}
							}
						}
					}
           /*if (playing.equals("e")) {
              for (com.monster.gdxgame.unit e : enemyList) {
                 coords = e.getCoords();
                 if (((mX >= coords[0]) && (mX < (coords[0] + 1))) && ((mY >= coords[1]) && (mY < (coords[1] + 1)))) {
                    if (e.canMove()) {
                       actionInProgress = true;
                       e.setSelected(true);
                       state = 1;
                       actions();
                    }
                 }
              }
           }*/
				}
			}
		}

	}
	private Path generatePath(Unit u, int targetX, int targetY){
		/*NOTES:
		-Because of the for loop that selectes the lowest in OPEN to be CURRENT, certain movement is favoured if equal: down, left, up, right
		-will attack if next to a unit (decision making, unless it will die?)
		QUESTIONS:
		-would a queue be appropriate for open?
		-
		 */
		int[] coords = new int[2];
		//neighbours.clear();
		open.clear();
		closed.clear();
		int startX = u.getCoords()[0];
		int startY = u.getCoords()[1];
		Tile current = tiles[startY][startX];
		Tile target = tiles[targetY][targetX];
		Path path = new Path();
		current.setCost(0);
		current.setHeuristic(targetX, targetY);
		open.add(current);
		boolean targetAdjacent = false;
		int traversed = 0;
		int buffer = 1;
		if (playing.equals("p")){
			buffer = 0;
		}
		while (open.size() != 0 && current.getHeuristic() > buffer && traversed <= rows+cols ) {
			//System.out.println(traversed);
			//System.out.println(open.size());
			/*System.out.println(targetX);
			System.out.println(targetY);*/
			traversed++;
			//get lowest cost from OPEN, may need to decide here between nodes that have equal costs- continue same direction? what if same direction is blocked?
			for (int i = 0; i < open.size(); i++) {
				//current = open.get(i);
				if (open.get(i).getHeuristic() == current.getHeuristic()) {
					if (open.get(i).getMovementCost() < current.getMovementCost()) {
						current = open.get(i);
					}
				}
				if (open.get(i).getHeuristic() < current.getHeuristic()) {
					current = open.get(i);
				}
			}
			//System.out.println(current.getHeuristic());
			open.remove(current);
			closed.add(current);
			//move current to searched
			int x = current.getX();
			int y = current.getY();
			if (current.getHeuristic() > -1){
				//create neighbours list
				for (int i = 0; i < 4; i++){
					//System.out.println(i);
					x = current.getX();
					y = current.getY();
					switch (i) {
						case 0:
							x = current.getX() + 1;
							break;
						case 1:
							y = current.getY() + 1;
							break;
						case 2:
							x = current.getX() - 1;
							break;
						case 3:
							y = current.getY() - 1;
							break;
					}
					if (x == targetX && y == targetY){
						target.setParent(current);
						System.out.println("found target");
						break;
					}
					if (checkReachable(current.getX(), current.getY(), x, y, u)){
						int nextStepCost = current.getCost() + tiles[y][x].getMovementCost();
						Tile neighbour = tiles[y][x];
						if (nextStepCost < neighbour.getCost()){
							if (open.contains(neighbour)) {
								open.remove(neighbour);
							}
							if (closed.contains(neighbour)) {
								closed.remove(neighbour);
							}
						}
						if (!open.contains(neighbour) && !(closed.contains(neighbour))){
							neighbour.setCost(nextStepCost);
							neighbour.setParent(current);
							neighbour.setHeuristic(targetX, targetY);
							//System.out.println("adding to open");
							/*System.out.print(x);
							System.out.print(", ");
							System.out.println(y);*/
							sr.begin(ShapeType.Filled);
							c.set(50, 10, 10, 0);
							sr.setColor(c);
							sr.rect(convertX(x), convertY(y), tileWidth, tileHeight);
							sr.end();
							open.add(neighbour);
						}
					}else{
						System.out.println("obstacle not added to open");
					}
				}
			}
			path.appendStep(current);
		}
		//System.out.println(path.size());
		if (target.getParent() != null) {
			path.clear();
			System.out.println("path generated");
			while (target.getParent() != tiles[startY][startX]) {
				target = target.getParent();
				path.prependStep(target);
				sr.begin(ShapeType.Filled);
				c.set(50, 0, 0, 0);
				sr.setColor(c);
				sr.rect(convertX(target.getX()), convertY(target.getY()), tileWidth, tileHeight);
				sr.end();
			}
			targetAdjacent = true;
			//path.prependStep(tiles[startY][startX]);
		}
		return path;
	}
	private void pathfinding(Unit u, int targetX, int targetY, char targetType) {
     /*NOTES:
     -Because of the for loop that selectes the lowest in OPEN to be CURRENT, certain movement is favoured if equal: down, left, up, right
     -will attack if next to a unit (decision making, unless it will die?)
     QUESTIONS:
     -would a queue be appropriate for open?
     -
      */
		int[] coords = new int[2];
		Path path = generatePath(u,targetX,targetY);
		System.out.println(path.size());
		Tile prevStep = tiles[u.getY()][u.getX()];
		Tile step = prevStep;
		//System.out.println(path.size());
		int s = 0;
		while(s < path.size() && u.getStamina() > 0){
			prevStep = step;
			step = path.getStep(s);
			u.setStamina(u.getStamina()- step.getMovementCost());
			if (playing.equals("e")){
				System.out.println(u.getStamina());
			}
        /*System.out.print(step.getX());
        System.out.print(", ");
        System.out.println(step.getY());*/
		s++;
		}
		coords[0] = step.getX();
		coords[1] = step.getY();
		/*System.out.println("FINAL STEP COORDS:");
		System.out.println(coords[0]);
		System.out.println(coords[1]);*/
		if (playing.equals("p")){
			if (targetType == 'u') {
				if (!checkForCombat(u, coords[0], coords[1], path)) {
					coords[0] = prevStep.getX();
					coords[1] = prevStep.getY();
				}
				tiles[u.getY()][u.getX()].clearOccupant();
				u.setCoords(coords);
			}else if (targetType == 'b') {
				if (coords[0] == enemy.getX() && coords[1] == enemy.getY()) {
					coords[0] = prevStep.getX();
					coords[1] = prevStep.getY();
					if (u.getType() == 'r'){
						enemy.setHealth(enemy.getHealth() - u.getRangedAttack());
					}else{
						enemy.setHealth(enemy.getHealth() - u.getAttack());
					}
					u.setMove(false);
					u.setStamina(0);
				}
				tiles[u.getY()][u.getX()].clearOccupant();
				u.setCoords(coords);
			}else if (targetType == 'x') {
				tiles[u.getY()][u.getX()].clearOccupant();
				u.setCoords(coords);
			}
		}else if (playing.equals("e")){
			if (!decisionMaking(u, targetX, targetY, targetType, path, step, prevStep, coords)){
				tiles[u.getY()][u.getX()].clearOccupant();
				u.setCoords(coords);
			}
		}

		//combatLogic(u, t, path);
		//use path length and movement for combat, enemy combat method?
	}
	private boolean decisionMaking(Unit u, int targetX, int targetY, char targetType, Path path, Tile step, Tile prevStep, int[] coords) {
     /*
     -Take target x/y
     -Receive type of target
     -If unit, combatLogic
     -if normal tile, no logic
     -if resource tile, fortify/work (health decison managed in targetchoice)
     -if base, if within range, attack blindly? don't need to be too detailed
     */
		if (targetType != 'x') {
			if (targetType == 'u') {
				if (!checkForCombat(u, coords[0], coords[1], path)) {
					coords[0] = prevStep.getX();
					coords[1] = prevStep.getY();
				}
				u.setCoords(coords);
				return true;
			} else if (targetType == 'b') {
				if (coords[0] == enemy.getX() && coords[1] == enemy.getY()) {
					coords[0] = prevStep.getX();
					coords[1] = prevStep.getY();
					if (u.getType() == 'r') {
						enemy.setHealth(enemy.getHealth() - u.getRangedAttack());
					} else {
						enemy.setHealth(enemy.getHealth() - u.getAttack());
					}
					u.setMove(false);
					u.setStamina(0);
				}
				u.setCoords(coords);
				return true;
			}
		}
		return false;
	}
	private void combatLogic(Unit u, Unit t, Path path){
     /*
     how do i want combat to happen?
     -if the target coordinates are occupied by something that is attackable, and the coordinates are reachable by melee or by range (if by range, prefer this option), initiate combat
     -recalculate target every round (very important)
     -
      */
		if (u.getType() == 'r' && u.getRange() >= path.size()){
			System.out.println("enemy ranged attack");
			combat(u, t, 'r');
		}/*else if (path.size() > 1){
        if ()
     }*/else if (path.size() <= 1){
			System.out.println("enemy melee attack");
			combat(u, t, 'm');
		}
	}
	private void targetChoice(Unit u){
		//closest target?
		//redo so that distacne is used here, and health/ attack is used in combat logic
		//workers target resource tiles if high enough health
		//rangers want to stay within attack range, but out of distance
		//target base?
		//System.out.println("enemy choosing target");
		Tile t = null;
		if (u.getType() == 'm' || u.getType() == 'r'){
			Unit p = null;
			int distance = generatePath(u,playerList.get(0).getX(),playerList.get(0).getY()).size();
			int lD = distance;
			Unit closestUnit = null;
			Unit bestUnit = playerList.get(0);
			for (int i = 0; i < playerList.size(); i++){
				p = playerList.get(i);
				p.setScore(0);
				int score = 0;
				distance = generatePath(u,p.getX(),p.getY()).size();
				if(distance < u.getStamina()){
					score++;
					if(p.getType() == 'm'){
						score += 2;
					}else if(p.getType() == 'r'){
						score += 3;
					}else if(p.getType() == 'w'){
						score += 4;
					}
					if(p.getHealth() <= (u.getAttack() - p.getDefence())){
						score += 5;
					}else if (u.getType() == 'r'){
						if(p.getHealth() <= (u.getRangedAttack() - p.getDefence())){
							score += 5;
						}
					}
					p.setScore(score);
				}else{
					if (distance < lD){
						lD = distance;
						closestUnit = playerList.get(i);
					}
				}
				if (p.getScore() > bestUnit.getScore()){
					bestUnit = p;
				}
			}
			if(bestUnit == playerList.get(0) && closestUnit != null){
				t = tiles[closestUnit.getY()][closestUnit.getX()];
			}else{
				t = tiles[bestUnit.getY()][bestUnit.getX()];
			}
		}else if(u.getType() == 'w'){
			//go through resourceTileList
		}
		u.setTarget(t);
	}
	private boolean checkReachable(int cX, int cY, int nX, int nY, Unit u) {
		/*boolean n = false;
		boolean s = false;
		boolean e = false;
		boolean w = false;*/
		if ((nY > 0 && nY < rows-1) && (nX > 0 && nX < cols-1)){
			int diffX = nX - cX;
			int diffY = nY - cY;
			/*System.out.print(cX);
			System.out.print(", ");
			System.out.print(nX);
			System.out.print(", ");
			System.out.println(diffX);

			System.out.print(cY);
			System.out.print(", ");
			System.out.print(nY);
			System.out.print(", ");
			System.out.println(diffY);*/

			if (diffX < 0) {
				diffX = -diffX;
				//w = true;
			}else{
				//e = true;
			}
			if (diffY < 0) {
				diffY = -diffY;
				//n = true;
			}else{
				//s = true;
			}
			int distance = diffX + diffY;
			/*if (playing.equals("p")){
				if (enemyMap[nY][nX] != -1){
					System.out.println("blocked by enemy");
					return false;
				}
			}else if (playing.equals("e")){
				if (playerMap[nY][nX] != -1){
					System.out.println("blocked by player");
					return false;
				}
			}*/
			if (/*map[nY][nX] == 0*/tiles[nY][nX].getOccupied()){
				System.out.println("blocked by:");
				if(tiles[nY][nX].getOccupantUnit() == u){
					System.out.println("self");
				}else{
					System.out.print("Owner: ");
					System.out.println(tiles[nY][nX].getOccupantUnit().getOwner());
				}
				/*System.out.print("Type: ");
				System.out.println(tiles[nY][nX].getOccupantUnit().getType());*/
				return false;
			}
			if (tiles[nY][nX].getBlocked()) {
				System.out.println("obstruction target");
				return false;
			}
			/*else {
				if (w) {
				   for (int i = 1; i <= diffX; i++) {
					  if (cX - i > 0) {
						 if (map[cY][cX - i] == 0) {
							System.out.println("obstruction west");
							return false;
						 }
					  }
				   }
				}
				if (e) {
				   for (int i = 1; i <= diffX; i++) {
					  if (cX + i < cols - 1) {
						 if (map[cY][cX + i] == 0) {
							System.out.println("obstruction east");
							return false;
						 }
					  }
				   }
				}
				if (n) {
				   for (int i = 1; i <= diffY; i++) {
					  if (cY - i > 0) {
						 if (map[cY - i][cX] == 0) {
							System.out.println("obstruction north");
							return false;
						 }
					  }
				   }
				}
				if (s) {
				   for (int i = 1; i <= diffY; i++) {
					  if (cY + i < rows - 1) {
						 if (map[cY + i][cX] == 0) {
							System.out.println("obstruction south");
							return false;
						 }
					  }
				   }
				}
			 }*/
			if (distance > u.getStamina()) {
				System.out.println("distance too large");
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	private void checkComplete() {
		int movable = 0;
		if (playing.equals("p")) {
			for (com.monster.gdxgame.Unit u : playerList) {
				if (u.canMove()) {
					movable++;
				}
			}
			if (movable == 0 /*|| (click == true && ((mouseX >= ((screenWidth/2) - tileWidth) && mouseX < ((screenWidth/2) + tileWidth)) && (mouseY >= 0 && mouseY < tileHeight)))*/) {
				complete = true;
			}
		}

		if (playing.equals("e")) {
			for (com.monster.gdxgame.Unit e : enemyList) {
				if (e.canMove()) {
					movable++;
				}
			}
			if (movable == 0) {
				complete = true;
			}
		}
	}
	//draw highlights around selected unit and available areas////////////////////////////////
	private boolean checkForCombat(Unit u, int nX, int nY, Path path) {
		if (playing.equals("p")) {
			if (enemyMap[nY][nX] > -1) {
				System.out.println("player attack");
				combatLogic(u,enemyList.get(enemyMap[nY][nX]), path);
				return true;
			}
		}else if (playing.equals("e")) {
			if (playerMap[nY][nX] > -1) {
				combatLogic(u, playerList.get(playerMap[nY][nX]), path);
				return true;
			}
		}
		return false;
	}
	private void combat(Unit att, Unit def, char type) {
		float attHP = att.getHealth();
		float defHP = def.getHealth();
		if (type == 'r'){
			float damage = (att.getRangedAttack() - def.getDefence()) ;
			def.setHealth(defHP - damage);
			//reduce defender's attack and defence
			def.setAttack(def.getRangedAttack() - (damage/5));
			if (def.getAttack() < 1) {
				def.setAttack(1);
			}
			def.setDefence((float) (def.getDefence() - ((0.5 * damage)/5)));

			att.setSelected(false);
			att.setMove(false);
			if (defHP <= 0) {
				//remove from enemyList
				if (playing.equals("p")) {
					enemyList.remove(enemyMap[def.getCoords()[1]][def.getCoords()[0]]);
					enemyMap[def.getCoords()[1]][def.getCoords()[0]] = -1;
				}
				if (playing.equals("e")) {
					playerList.remove(playerMap[def.getCoords()[1]][def.getCoords()[0]]);
					playerMap[def.getCoords()[1]][def.getCoords()[0]] = -1;
				}
			}
		}else{
			//attacker first
			float damage = (att.getAttack() - def.getDefence()) ;
			def.setHealth(defHP - damage);
			//reduce defender's attack and defence
			def.setAttack(def.getAttack() - (damage/5));
			if (def.getAttack() < 1) {
				def.setAttack(1);
			}
			def.setDefence((float) (def.getDefence() - ((0.5 * damage)/5)));

			att.setSelected(false);
			att.setMove(false);
			att.setStamina(0);
			if (defHP <= 0) {
				//remove from enemyList
				if (playing.equals("p")) {
					enemyList.remove(enemyMap[def.getCoords()[1]][def.getCoords()[0]]);
					enemyMap[def.getCoords()[1]][def.getCoords()[0]] = -1;
				}
				if (playing.equals("e")) {
					playerList.remove(playerMap[def.getCoords()[1]][def.getCoords()[0]]);
					playerMap[def.getCoords()[1]][def.getCoords()[0]] = -1;
				}
				att.setMove(true);
				playerMap[att.getCoords()[1]][att.getCoords()[0]] = -1;
				playerMap[def.getCoords()[1]][def.getCoords()[0]] = att.getCode();
				att.setCoords(def.getCoords());
				att.setMove(false);
				att.setSelected(false);
				att.setStamina(0);
				att.draw();
			}else{
				//defender attacks the attacker
				damage = (def.getAttack() - att.getDefence()) ;
				att.setHealth(attHP - damage);
				//reduce defender's attack and defence
				att.setAttack(att.getAttack() - (damage/5));
				if (att.getAttack() < 1) {
					att.setAttack(1);
				}
				att.setDefence((float) (att.getDefence() - ((0.5 * damage)/5)));
				//then attacker
				if (attHP <= 0) {
					//remove from playerList
					if (playing.equals("e")) {
						for (int e = 0; e < enemyList.size(); e++) {
							if (def.getCode() == e) {
								enemyList.remove(e);
							}
						}
					}
					if (playing.equals("p")) {
						for (int p = 0; p < playerList.size(); p++) {
							if (def.getCode() == p) {
								playerList.remove(p);
							}
						}
					}
				}
			}
		}
		System.out.println("attacker:");
		System.out.print("health:");
		System.out.println(att.getHealth());
		System.out.print("attack:");
		System.out.println(att.getAttack());
		System.out.print("defence:");
		System.out.println(att.getDefence());

		System.out.println("defender:");
		System.out.print("health:");
		System.out.println(def.getHealth());
		System.out.print("attack:");
		System.out.println(def.getAttack());
		System.out.print("defence:");
		System.out.println(def.getDefence());
	}
	private void checkMouseTile() {
		//COULD use to find the tile being hovered over, and add more properties to tile such as blocked, occupied, essentially what is on that tile. would simplify everything, but may be unnecessary
		//mouseTile =  tiles[mX][mY];
		if (playing.equals("p")) {
			if (playerMap[mY][mX] > -1){
				overUnit = true;
			}else{
				overUnit = false;
			}
		}else if (playing.equals("e")) {
			if (enemyMap[mY][mX] > -1){
				overUnit = true;
			}else{
				overUnit = false;
			}
		}
// --Commented out by Inspection START (08/04/2022 17:59):
		//could put here: get current unit, whether over button//////////////////////////////////////////
		//System.out.println(overUnit);
	}
	private void displayStats(){
		Unit u = null;
		if (playing.equals("p")) {
			u = playerList.get(playerMap[mY][mX]);
		}else if (playing.equals("e")) {
			u = enemyList.get(enemyMap[mY][mX]);
		}
		//text
     /*b.begin();
     //b.draw(blankMouseButton,);
     f.getData().setScale(4,4);
     f.setColor(250,0,0,0);
     f.draw(b,"texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext", mX, mY);
     b.end();*/
		//System.out.println("should be drawing text");
		System.out.print("Owner: ");
		System.out.println(u.getOwner());
		System.out.print("Code: ");
		System.out.println(u.getCode());
		System.out.print("Type: ");
		System.out.println(u.getType());
		System.out.print("Health: ");
		System.out.println(u.getHealth());
		System.out.print("Damage: ");
		System.out.println(u.getAttack());
		System.out.print("Range: ");
		System.out.println(u.getRange());
		System.out.print("Movement: ");
		System.out.println(u.getStamina());
// --Commented out by Inspection STOP (08/04/2022 17:59)
		System.out.print("Selected: ");
		System.out.println(u.getSelected());
	}
}
//https://www.redblobgames.com/pathfinding/grids/algorithms.html
     /*
     -select a target
     -distance to target
     -for each surrounding tile:
        -distance to unit
        -distance to target
        -sum of two = cost
        -as new tiles are examined, tiles adjacent to them should be updated for new shortest path
        -total costs should not increase, as that means you are further away
           -if total cost increases, search for lower costs.
        -with obstacles:
           -need to store costs of passed over tiles (queue/stack?)
           -examine lowest-cost tile each time
           -
     STRATEGY:
     -LIST: to be examined OPEN
     -LIST: alrady examined CLOSED
     -start coords node to OPEN
     -LOOP:
        -VARIABLE current: node in OPEN with lowest cost
        -move current from OPEN to CLOSED
        -IF current = target: exit loop
        -ELSE:
           -FOR each neighbour of current
           -IF neighbour is blocked or in CLOSED:
              -skip to next neighbour
           -ELSE:
              -IF new path to neighbour is shorter than current OR neighbour not in OPEN:
                 -update cost to tile
                 -set parent of tile to current
                 -IF neighbour not in OPEN:
                    -add neighbour to OPEN*/
