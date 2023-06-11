package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.Cell;
import model.world.CharacterCell;

public abstract class Character {
	private String name;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Point location;
	private Character target;

	public Character(String name, int maxHp, int attackDmg) {
		this.name = name;
		this.maxHp = maxHp;
		this.attackDmg = attackDmg;
		this.currentHp = maxHp;

	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if (currentHp >= maxHp) {
			this.currentHp = maxHp;

		} else if (currentHp < 0) {
			this.currentHp = 0;
		} else
			this.currentHp = currentHp;

	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		if ((this instanceof Hero && target instanceof Hero) || (this instanceof Zombie && target instanceof Zombie)
				|| (this instanceof Hero && target == null)) {
			throw new InvalidTargetException("Can't attack a friendly character");
		}
	}
	public abstract String toString() ;


	public void defend(Character c) {
		c.setCurrentHp(c.getCurrentHp() - getAttackDmg() / 2);
	}

	public abstract boolean checkDeath();

	public void onCharacterDeath() {
		
		if (this instanceof Hero) {
			int x=(int) this.getLocation().getX();
			int y = (int) this.getLocation().getY();
			if(this.getCurrentHp()==0)
			Game.heroes.remove(this);
			
			CharacterCell myCell=((CharacterCell)Game.map[x][y]);
			myCell.setCharacter(null);
			myCell.setVisible(false);
			if (x + 1 < Game.BOARDHEIGHT && Game.map[x + 1][y]!=null) {
				
				Cell up = Game.map[x + 1][y];
				up.setVisible(false);
			}
			if (x - 1 >= 0 && Game.map[x - 1][y]!=null) {
				
				Cell down = Game.map[x - 1][y];
				down.setVisible(false);
			}
			if (y - 1 >= 0 && Game.map[x ][y-1]!=null) {

				Cell left = Game.map[x][y - 1];
				left.setVisible(false);

			}
			if (y + 1 < Game.BOARDHEIGHT && Game.map[x ][y+1]!=null) {
				
				Cell right = Game.map[x][y + 1];
				right.setVisible(false);
			}
			if (x + 1 < Game.BOARDHEIGHT && y + 1 < Game.BOARDWIDTH && Game.map[x + 1][y+1]!=null) {
				
				Cell upRight = Game.map[x + 1][y + 1];
				upRight.setVisible(false);
			}
			if (x + 1 < Game.BOARDHEIGHT && y - 1 >= 0 && Game.map[x + 1][y-1]!=null) {
				
				Cell upLeft = Game.map[x + 1][y - 1];
				upLeft.setVisible(false);
			}
			if (x - 1 >= 0 && y - 1 >= 0 && Game.map[x - 1][y-1]!=null) {
				
				Cell downLeft = Game.map[x - 1][y - 1];
				downLeft.setVisible(false);
			}
			if (x - 1 >= 0 && y + 1 < Game.BOARDHEIGHT && Game.map[x - 1][y+1]!=null) {
				// System.out.println();
				// else {
				Cell downRight = Game.map[x - 1][y + 1];
				downRight.setVisible(false);

			}
			this.setLocation(null);
		}
		if(this instanceof Zombie) {
			if(this.getCurrentHp()==0) {
			int x=(int) this.getLocation().getX();
			int y = (int) this.getLocation().getY();
			Game.zombies.remove(this);
			this.setLocation(null);
			CharacterCell myCell=((CharacterCell)Game.map[x][y]);
			myCell.setCharacter(null);
        	myCell.setVisible(false);
			Zombie.respawnZombie();
			
			}}
		}


	public boolean CheckAdjacent() {
		Point p = getLocation();
		int xpos = p.x;
		int ypos = p.y;
		CharacterCell myCell = (CharacterCell) Game.map[xpos][ypos];
		if(xpos!=14) {
		Cell up = Game.map[xpos + 1][ypos];
		if(up instanceof CharacterCell)
			return true;
		}
		if(xpos!=0) {
		Cell down = Game.map[xpos - 1][ypos];
		if(down instanceof CharacterCell)
			return true;
		}
		if(ypos!=14) {
			Cell left = Game.map[xpos][ypos-1];
			if(left instanceof CharacterCell)
				return true;
			}
		if(ypos!=0) {
			Cell right = Game.map[xpos][ypos+1];
			if(right instanceof CharacterCell)
				return true;
			}
		if(xpos!=14 && ypos!=14) {
			Cell upRight = Game.map[xpos +1][ypos+1];
			if(upRight instanceof CharacterCell)
				return true;
			}
		if(xpos !=14 && ypos!=0) {
			Cell upLeft = Game.map[xpos + 1][ypos - 1];
			if(upLeft instanceof CharacterCell)
				return true;
		}
		if(xpos !=0 && ypos!=0) {
			Cell downLeft = Game.map[xpos + 1][ypos - 1];
			if(downLeft instanceof CharacterCell)
				return true;
		}
		if(xpos !=0 && ypos!=14) {
			Cell downRight = Game.map[xpos - 1][ypos + 1];
			if(downRight instanceof CharacterCell)
				return true;
		}
		
		
			return false;

	}
	public boolean isAdj(Character c) {
		int myx= this.getLocation().x;
		int myy= this.getLocation().y;
		
		int tx= c.getLocation().x;
		int ty= c.getLocation().y;
		if((tx==myx+1 && ty==myy) || (tx==myx-1 && ty==myy) || (tx==myx && ty==myy+1) || (tx==myx && ty==myy-1) || (tx==myx+1 && ty==myy+1)|| (tx==myx+1 && ty==myy-1) || (tx==myx-1 && ty==myy+1) || (tx==myx-1 && ty==myy-1))
			return true;
		return false;

}

	public void targetSetter() {

		int xpos = getLocation().x;
		int ypos = getLocation().y;

		for (Hero h : Game.heroes) {
			int XofTarget = h.getLocation().x;
			int YofTarget = h.getLocation().y;
			if (xpos + 1 == XofTarget && ypos == YofTarget) {
				target = h;
				break;
			} else if (xpos - 1 == XofTarget && ypos == YofTarget) {
				target = h;
				break;
			} else if (xpos == XofTarget && ypos + 1 == YofTarget) {
				target = h;
				break;
			} else if (xpos == XofTarget && ypos - 1 == YofTarget) {
				target = h;
				break;
			} else if (xpos + 1 == XofTarget && ypos + 1 == YofTarget) {
				target = h;
				break;
			} else if (xpos - 1 == XofTarget && ypos + 1 == YofTarget) {
				target = h;
				break;
			} else if (xpos + 1 == XofTarget && ypos - 1 == YofTarget) {
				target = h;
				break;
			} else if (xpos - 1 == XofTarget && ypos - 1 == YofTarget) {
				target = h;
				break;
			}

		}

		CharacterCell myCell = (CharacterCell) Game.map[xpos][ypos];
		Cell up = Game.map[xpos + 1][ypos];
		Cell down = Game.map[xpos - 1][ypos];
		Cell left = Game.map[xpos][ypos - 1];
		Cell right = Game.map[xpos][ypos + 1];
		Cell upRight = Game.map[xpos + 1][ypos + 1];
		Cell upLeft = Game.map[xpos + 1][ypos - 1];
		Cell downLeft = Game.map[xpos - 1][ypos - 1];
		Cell downRight = Game.map[xpos - 1][ypos + 1];
		

	}
	
	


}
