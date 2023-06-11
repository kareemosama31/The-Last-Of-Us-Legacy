package engine;

import static org.junit.Assume.assumeNoException;

import java.awt.Point;
import java.awt.datatransfer.FlavorListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import model.characters.Medic;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Explorer;

public class Game {
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	public static final int BOARDHEIGHT = 15;
	public static final int BOARDWIDTH = 15;
	public static Cell[][] map = new Cell[BOARDHEIGHT][BOARDWIDTH];
	public static int vaccineUsed = 0;
	private static Hero chosenHero;

	public static void setChosenHero(Hero chosenHero) {
		Game.chosenHero = chosenHero;
	}
	public static Cell[][] getMap() {
		return map;
	}
	public Hero getChosenHero() {
	    return chosenHero;
	}
	public static  void setHero(Hero hero) {
	   chosenHero = hero;
	}
	public Hero getHeroByName(String name) {
	      for (Hero hero : heroes) {
	         if (hero.getName().equals(name)) {
	            return hero; 
	         }    
	      }    
	      return null;
	   }   

	public static ArrayList<Hero> getAvailableHeroes() {
		return availableHeroes;
		
	}

	public static void reset() {
		for (Hero h : heroes) {
			h.setActionsAvailable(h.getMaxActions());
			h.setSpecialAction(false);
			h.setTarget(null);

		}
		for (Zombie z : zombies) {
			z.setTarget(null);
		}
	}

	public static void loadHeroes(String filePath) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String temp = br.readLine();
		while (temp != null) {
			String[] splitted = temp.split(",");
			if (splitted[1].equals("FIGH")) {
				Fighter kareem = new Fighter(splitted[0], Integer.parseInt(splitted[2]), Integer.parseInt(splitted[4]),
						Integer.parseInt(splitted[3]));
				availableHeroes.add(kareem);
			}
			if (splitted[1].equals("MED")) {
				Medic ibrahim = new Medic(splitted[0], Integer.parseInt(splitted[2]), Integer.parseInt(splitted[4]),
						Integer.parseInt(splitted[3]));
				availableHeroes.add(ibrahim);
			}
			if (splitted[1].equals("EXP")) {
				Explorer sara = new Explorer(splitted[0], Integer.parseInt(splitted[2]), Integer.parseInt(splitted[4]),
						Integer.parseInt(splitted[3]));
				availableHeroes.add(sara);
			}
			temp = br.readLine();
		}
		br.close();
	}

	public static void zombieCreator() {
		int i = 0;
		while (i < 10) {
			int xposition = (int) (Math.random() * 15);
			int yposition = (int) (Math.random() * 15);
			if (map[xposition][yposition] instanceof CharacterCell) {

				if (((CharacterCell) map[xposition][yposition]).getCharacter() == null) {
					Zombie z = new Zombie();
					z.setLocation(new Point(xposition, yposition));

					((CharacterCell)map[xposition][yposition]).setCharacter(z);
					zombies.add(z);

				}
			}
		}
	}

	public static void updateVisibility(Hero h) {

		
		Point p = h.getLocation();
		int heroLocx = p.x;
		int heroLocy = p.y;
		CharacterCell myCell = (CharacterCell) map[heroLocx][heroLocy];

		myCell.setVisible(true);
		if (heroLocx + 1 < BOARDHEIGHT ) {
	
			Cell up = map[heroLocx + 1][heroLocy];
			up.setVisible(true);
		
		}
		if (heroLocx - 1 >= 0 ) {
		
			Cell down = map[heroLocx - 1][heroLocy];
			down.setVisible(true);

		}
		if (heroLocy - 1 >= 0 ) {
		
			Cell left = map[heroLocx][heroLocy - 1];
			left.setVisible(true);
		

		}
		if (heroLocy + 1 < BOARDHEIGHT ) {
		
			Cell right = map[heroLocx][heroLocy + 1];
			right.setVisible(true);
		
		}
		if (heroLocx + 1 < BOARDHEIGHT && heroLocy + 1 < BOARDWIDTH ) {
			
			Cell upRight = map[heroLocx + 1][heroLocy + 1];
			upRight.setVisible(true);
			
		}
		if (heroLocx + 1 < BOARDHEIGHT && heroLocy - 1 >= 0) {
			
			Cell upLeft = map[heroLocx + 1][heroLocy - 1];
			upLeft.setVisible(true);
	
		}
		if (heroLocx - 1 >= 0 && heroLocy - 1 >= 0) {
		
			Cell downLeft = map[heroLocx - 1][heroLocy - 1];
			downLeft.setVisible(true);
		
		}
		if (heroLocx - 1 >= 0 && heroLocy + 1 < BOARDHEIGHT) {
		
			Cell downRight = map[heroLocx - 1][heroLocy + 1];
			downRight.setVisible(true);
	

		}
	}
	
		
	public static void startGame(Hero h) {
		
		for (int i = 0; i < BOARDHEIGHT; i++) {
			for (int j = 0; j < BOARDWIDTH; j++) {
				Game.map[i][j] = new CharacterCell(null);
Game.map[i][j].setVisible(false);
			}
		}
	
		
		map[0][0].setVisible(true);
		map[1][0].setVisible(true);
		map[0][1].setVisible(true);
		map[1][1].setVisible(true);

	
		// Step 1 add the main hero bottom left

		// Step 2 remove it
		
		availableHeroes.remove(h);
		heroes.add(h);
		
		((CharacterCell)map[0][0]).setCharacter(h);
		h.setLocation(new Point(0, 0));
		

//		}
		for (int i = 0; i < 5; i++) {
			int xposition = (int) (Math.random() * 15);
			int yposition = (int) (Math.random() * 15);
			
			if (map[xposition][yposition] instanceof CharacterCell) {
				if (((CharacterCell) map[xposition][yposition]).getCharacter() == null) {
					map[xposition][yposition] = new CollectibleCell(new Vaccine());
					if((xposition==0&&yposition==1)||(xposition==1&&yposition==1)||(xposition==1&&yposition==0))
						map[xposition][yposition].setVisible(true);
				}
				else
					i--;
			}
			else
				i--;
		}
		for (int i = 0; i < 5; i++) {
			int xposition = (int) (Math.random() * 15);
			int yposition = (int) (Math.random() * 15);
			
			if (map[xposition][yposition] instanceof CharacterCell) {
				if (((CharacterCell) map[xposition][yposition]).getCharacter() == null) {
					map[xposition][yposition] = new CollectibleCell(new Supply());
					if((xposition==0&&yposition==1)||(xposition==1&&yposition==1)||(xposition==1&&yposition==0))
						map[xposition][yposition].setVisible(true);
				}
				else
					i--;
			}
			else
				i--;
		}		
		for (int i = 0; i < 5; i++) {
			int xposition = (int) (Math.random() * 15);
			int yposition = (int) (Math.random() * 15);
			
			if (map[xposition][yposition] instanceof CharacterCell) {
				if (((CharacterCell) map[xposition][yposition]).getCharacter() == null) {
					map[xposition][yposition] = new TrapCell();
					if((xposition==0&&yposition==1)||(xposition==1&&yposition==1)||(xposition==1&&yposition==0))
						map[xposition][yposition].setVisible(true);
				}
				else
					i--;
			}
			else
				i--;
		}

		// Zombies Creator
		int i = 0;
		while (i < 10) {
			int xposition = (int) (Math.random() * 15);
			int yposition = (int) (Math.random() * 15);
			if (map[xposition][yposition] instanceof CharacterCell) {

				if (((CharacterCell) map[xposition][yposition]).getCharacter() == null) {
					Zombie z = new Zombie();
					z.setLocation(new Point(xposition, yposition));

					((CharacterCell)map[xposition][yposition]).setCharacter(z);
					zombies.add(z);
					i++;
				}
			}
		}

	}

	public static boolean checkWin() {
		// loop on heros and check if their vaccineInverties is empty

		for (Hero h : heroes)
			if (!h.getVaccineInventory().isEmpty())
				return false;
		// loop on the map and check if their is any vaccines not collected
		for (int i = 0; i < Game.map.length; i++) {
			for (int j = 0; j < Game.map[i].length; j++) {
				if (Game.map[i][j] instanceof CollectibleCell
						&& ((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Vaccine)
					return false;
			}
		}

		if (heroes.size() > 4)
			return true;
		else
			return false;
	}
	 
	public static boolean checkGameOver() {

		if(heroes.size()==0)
			return true;
		else {
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[i].length;j++) {
					if(map[i][j] instanceof CollectibleCell && ((CollectibleCell)map[i][j]).getCollectible() instanceof Vaccine)
						return false;
				}
			}
			for(int i=0;i<heroes.size();i++) {
				if(!heroes.get(i).getVaccineInventory().isEmpty())
					return false;
			}
			return true;
		}
	}

	public static void endTurn() throws InvalidTargetException, NotEnoughActionsException {
		
		for(Zombie z:zombies) {
			
	
			z.attack();
			
		}
	     
		
		for (int i = 0; i < Game.map.length; i++) {
			for (int j = 0; j < Game.map[i].length; j++) {

				Game.map[i][j].setVisible(false);
			}

		}
		
		ArrayList<Hero> dead=new ArrayList<Hero>();
		for (Hero h : heroes) {
			if(h.checkDeath())
				dead.add(h);
			
			
			else {
				updateVisibility(h);
				h.setActionsAvailable(h.getMaxActions());
			}
		}
		for(Hero d:dead) {
			d.onCharacterDeath();
		}
		ArrayList<Zombie> deadz=new ArrayList<Zombie>();
		for (Zombie z : zombies) {
			if(z.checkDeath())
				deadz.add(z);
			
		
		}
		for(Zombie dd:deadz) {
			dd.onCharacterDeath();
		}
		reset();
		Zombie.respawnZombie();
		

	}
	

	public static void main(String[] args) 
			throws Exception {

		
				loadHeroes(System.getProperty("user.dir") + "////" + "HEROS.CSV");

		startGame(availableHeroes.get(0));

	
	
		
		System.out.println(heroes.size());
printGame();
	}
	public static void printGame() {
		for (Cell[] x : map) {
			for (Cell y : x) {
				if (y instanceof CharacterCell) {
					if (((CharacterCell) y).getCharacter() == null) {
						if (((CharacterCell) y).isVisible())
							System.err.print("-" + "  ");
						else
							System.out.print("-" + "  ");

					} else {
						if (((CharacterCell) y).isVisible())
							System.err.print(((CharacterCell) y).getCharacter().getName().substring(0, 1) + "  ");
						else
							System.out.print(((CharacterCell) y).getCharacter().getName().substring(0, 1) + "  ");

					}

				}

				else if (y instanceof CollectibleCell) {
					if (((CollectibleCell) y).getCollectible() instanceof Supply) {
						if (((CollectibleCell) y).isVisible())
							System.err.print("S" + "  ");
						else
							System.out.print("S" + "  ");

					}
					if (((CollectibleCell) y).getCollectible() instanceof Vaccine) {
						if (((CollectibleCell) y).isVisible())
							System.err.print("V" + "  ");
						else
							System.out.print("V" + "  ");
					}

				}

				else if (y instanceof TrapCell) {
					if (((TrapCell) y).isVisible())
						System.err.print("T" + "  ");
					else
						System.out.print("T" + "  ");

				}

			}
			System.out.println();
		}
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||");

	}
}