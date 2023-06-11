package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.Cell;
import model.world.CharacterCell;

public class Zombie extends Character{
 private static int ZOMBIES_COUNT=0;
	public Zombie() {
		super("Zombie " + ++ZOMBIES_COUNT, 40, 10);
		

}
	

	
	public void attack() throws InvalidTargetException, NotEnoughActionsException   {
		
		int myx=(int) getLocation().getX();
		int myy=(int) getLocation().getY();
		if(myx!=0 && myy!=0) {//DOWNLEFT
			if(Game.map[myx-1][myy-1] instanceof CharacterCell) {
				if(((CharacterCell)Game.map[myx-1][myy-1]).getCharacter() instanceof Hero) {
					Hero h=(Hero)((CharacterCell)Game.map[myx-1][myy-1]).getCharacter();
					h.setCurrentHp(h.getCurrentHp()-10);
					h.defend(this);
				
					return;
				}
			}
		}//DOWN
		if(myx!=0) {
			if(Game.map[myx-1][myy] instanceof CharacterCell) {
				if(((CharacterCell)Game.map[myx-1][myy]).getCharacter() instanceof Hero) {
					Hero h=(Hero)((CharacterCell)Game.map[myx-1][myy]).getCharacter();
					h.setCurrentHp(h.getCurrentHp()-10);
					h.defend(this);
				
					return;
					
				}
			}
		}//DOWNRIGHT
		if(myx!=0 && myy!=14) {
			if(Game.map[myx-1][myy+1] instanceof CharacterCell) {
				if(((CharacterCell)Game.map[myx-1][myy+1]).getCharacter() instanceof Hero) {
					Hero h=(Hero)((CharacterCell)Game.map[myx-1][myy+1]).getCharacter();
					h.setCurrentHp(h.getCurrentHp()-10);
					h.defend(this);
			
					return;
				}
			}
		}//LEFT
		if(myy!=0) {
			if(Game.map[myx][myy-1] instanceof CharacterCell) {
				if(((CharacterCell)Game.map[myx][myy-1]).getCharacter() instanceof Hero) {
					Hero h=(Hero)((CharacterCell)Game.map[myx][myy-1]).getCharacter();
					h.setCurrentHp(h.getCurrentHp()-10);
					h.defend(this);

					return;
				}
			}
		}//RIGHT
		if(myy!=14) {
			if(Game.map[myx][myy+1] instanceof CharacterCell) {
				if(((CharacterCell)Game.map[myx][myy+1]).getCharacter() instanceof Hero) {
					Hero h=(Hero)((CharacterCell)Game.map[myx][myy+1]).getCharacter();
					h.setCurrentHp(h.getCurrentHp()-10);
					h.defend(this);

					return;
					
				}
			}
		}//UP
		if(myx!=14) {
			if(Game.map[myx+1][myy] instanceof CharacterCell) {
				if(((CharacterCell)Game.map[myx+1][myy]).getCharacter() instanceof Hero) {
					Hero h=(Hero)((CharacterCell)Game.map[myx+1][myy]).getCharacter();
					h.setCurrentHp(h.getCurrentHp()-10);
					h.defend(this);

					return;
					
					
				}
			}
		}
		
		
		//UPRIGHT
		
		if(myx!=14 && myy!=14) {
			if(Game.map[myx+1][myy+1] instanceof CharacterCell) {
				if(((CharacterCell)Game.map[myx+1][myy+1]).getCharacter() instanceof Hero) {
					Hero h=(Hero)((CharacterCell)Game.map[myx+1][myy+1]).getCharacter();
					h.setCurrentHp(h.getCurrentHp()-10);
					h.defend(this);

					return;
				}
			}
		}//UPLEFT
		if(myx!=14 && myy!=0) {
			if(Game.map[myx+1][myy-1] instanceof CharacterCell) {
				if(((CharacterCell)Game.map[myx+1][myy-1]).getCharacter() instanceof Hero) {
					Hero h=(Hero)((CharacterCell)Game.map[myx+1][myy-1]).getCharacter();
					h.setCurrentHp(h.getCurrentHp()-10);
					h.defend(this);

					return;
				}
			}
		}
		
		
		
		

	}
		
	



			           
						

	
	

	
	
	
	public static void respawnZombie() {

		  int xposition = (int) (Math.random() * 15);
		  int yposition = (int) (Math.random() * 15);
		  while (!(Game.map[xposition][yposition] instanceof CharacterCell) || ((CharacterCell) Game.map[xposition][yposition]).getCharacter() != null) {

					 xposition = (int) (Math.random() * 15);
					 yposition = (int) (Math.random() * 15);
				}
		  Zombie z=new Zombie();
				Game.zombies.add(z);
				
				  ((CharacterCell)Game.map[xposition][yposition]).setCharacter(z);
				  z.setLocation(new Point(xposition,yposition));
		
	}

	public boolean checkDeath() {
		
			if(getCurrentHp()==0)
	    		return true;
	    	return false;
	    			
	    }



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	}
	

