package model.characters;

import java.awt.Point;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Random;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public abstract class Hero extends Character {
	private int actionsAvailable;
	private int maxActions;
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
		if (actionsAvailable <= 0) {
			this.actionsAvailable = 0;
		} else
			this.actionsAvailable = actionsAvailable;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}

	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;// IDKKKKK MAYBEEE//
		this.vaccineInventory = new ArrayList<Vaccine>();
		this.supplyInventory = new ArrayList<Supply>();

	}
	public String InventoryToString() {
		String s;
		s=(this.getSupplyInventory().size() +" Supply " +"\n"+this.getVaccineInventory().size()+" Vaccine");
		return s;
		}
	

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		super.attack();
		if ((this instanceof Medic || this instanceof Explorer) && getActionsAvailable() == 0) {
			throw new NotEnoughActionsException();
		}
		
		Zombie z = (Zombie) getTarget();
		int targetx = z.getLocation().x;
		int targety = z.getLocation().y;

		int myx = getLocation().x;
		int myy = getLocation().y;
		if (targetx == myx + 1 && targety == myy) {
			z.setCurrentHp(z.getCurrentHp() - getAttackDmg());
		} else if (targetx == myx - 1 && targety == myy) {
			z.setCurrentHp(z.getCurrentHp() - getAttackDmg());
		} else if (targetx == myx && targety == myy + 1) {
			z.setCurrentHp(z.getCurrentHp() - getAttackDmg());
		} else if (targetx == myx && targety == myy - 1) {
			z.setCurrentHp(z.getCurrentHp() - getAttackDmg());
		} else if (targetx == myx + 1 && targety == myy + 1) {
			z.setCurrentHp(z.getCurrentHp() - getAttackDmg());
		} else if (targetx == myx + 1 && targety == myy - 1) {
			z.setCurrentHp(z.getCurrentHp() - getAttackDmg());
		} else if (targetx == myx - 1 && targety == myy + 1) {
			z.setCurrentHp(z.getCurrentHp() - getAttackDmg());
		} else if (targetx == myx - 1 && targety == myy - 1) {
			z.setCurrentHp(z.getCurrentHp() - getAttackDmg());
		} else
			throw new InvalidTargetException();
	
		
			
		
	
		if (this instanceof Fighter && this.isSpecialAction()) {
			z.defend(this);
			
			return;
		}
		
		else if( this instanceof Fighter && getActionsAvailable()==0) {
			throw new InvalidTargetException();
		}
		
		
			z.defend(this);
			setActionsAvailable(getActionsAvailable() - 1);
		
			if (z.checkDeath()) {
				z.onCharacterDeath();
				
				
			}
		
				
				
				if(this.checkDeath()) {
					this.onCharacterDeath();
					return;
				}
	}
		

	public void move(Direction d) throws MovementException, NotEnoughActionsException {
		if (getActionsAvailable() == 0) {
			throw new NotEnoughActionsException();
		}
		int xpos = (int) this.getLocation().getX();
		int ypos = (int) this.getLocation().getY();
		int newx = xpos;
		int newy = ypos;

		if (d == Direction.UP) {
			newx = xpos + 1;
			if (xpos == 14)
				throw new MovementException();
			if (Game.map[newx][newy] instanceof TrapCell) {
				TrapCell t = (TrapCell) Game.map[newx][newy];
				setCurrentHp(getCurrentHp() - t.getTrapDamage());

			} else if (Game.map[newx][newy] instanceof CollectibleCell) {
				CollectibleCell pickme = (CollectibleCell) Game.map[newx][newy];
				Collectible pickmeup = pickme.getCollectible();
				pickmeup.pickUp(this);

			} else if (((CharacterCell) Game.map[newx][newy]).getCharacter() != null) {
				throw new MovementException("Cell Occupied");

			}
		} else if (d == Direction.DOWN) {
			newx = xpos - 1;
			newy = ypos;
			if (xpos == 0)
				throw new MovementException();

			if (Game.map[newx][newy] instanceof TrapCell) {
				TrapCell t = (TrapCell) Game.map[newx][newy];
				setCurrentHp(getCurrentHp() - t.getTrapDamage());

			} else if (Game.map[newx][newy] instanceof CollectibleCell) {
				CollectibleCell pickme = (CollectibleCell) Game.map[newx][newy];
				Collectible pickmeup = pickme.getCollectible();
				pickmeup.pickUp(this);

			} else if (((CharacterCell) Game.map[newx][newy]).getCharacter() != null) {
				throw new MovementException("Cell Occupied");

			}
		}

		else if (d == Direction.RIGHT) {
			newx = xpos;
			newy = ypos + 1;

			if (ypos == 14)
				throw new MovementException();
			if (Game.map[newx][newy] instanceof TrapCell) {
				TrapCell t = (TrapCell) Game.map[newx][newy];
				setCurrentHp(getCurrentHp() - t.getTrapDamage());
				
			} else if (Game.map[newx][newy] instanceof CollectibleCell) {
				CollectibleCell pickme = (CollectibleCell) Game.map[newx][newy];
				Collectible pickmeup = pickme.getCollectible();
				pickmeup.pickUp(this);
			} else if (((CharacterCell) Game.map[newx][newy]).getCharacter() != null) {
				throw new MovementException("Cell Occupied");
			}
		} else if (d == Direction.LEFT) {
			newx = xpos;
			newy = ypos - 1;

			if (ypos == 0)
				throw new MovementException();

			if (Game.map[newx][newy] instanceof TrapCell) {
				TrapCell t = (TrapCell) Game.map[newx][newy];
				setCurrentHp(getCurrentHp() - t.getTrapDamage());
			} else if (Game.map[newx][newy] instanceof CollectibleCell) {
				CollectibleCell pickme = (CollectibleCell) Game.map[newx][newy];
				Collectible pickmeup = pickme.getCollectible();
				pickmeup.pickUp(this);
			} else if (((CharacterCell) Game.map[newx][newy]).getCharacter() != null) {
				throw new MovementException("Cell Occupied");
			}
		}

		if (checkDeath()) {
			this.onCharacterDeath();
			((CharacterCell)Game.map[xpos][ypos] ).setCharacter(null);
		} else {
			((CharacterCell)Game.map[xpos][ypos] ).setCharacter(null);
			Game.map[newx][newy] = new CharacterCell(this);
			setLocation(new Point(newx, newy));

			Game.updateVisibility(this);
			setActionsAvailable(getActionsAvailable() - 1);

		}
	}

	public void useSpecial() throws InvalidTargetException, NoAvailableResourcesException, NotEnoughActionsException {
		System.out.println("here");
		if(this instanceof Medic && getTarget() instanceof Zombie)
			throw new InvalidTargetException();
		
		if (getSupplyInventory().isEmpty()) {
			this.setSpecialAction(false);
			
			throw new NoAvailableResourcesException();
			
			
		}
		else
		{
			System.out.println("s"+getSupplyInventory().size());
			this.setSpecialAction(true);
			this.getSupplyInventory().remove(0);
			
		}
		
		
		
		
	}

	public void cure() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		if (getVaccineInventory().isEmpty()) {
			throw new NoAvailableResourcesException();
		}
		if (getActionsAvailable() == 0) {
			throw new NotEnoughActionsException();
		}

		

		if (getTarget() instanceof Hero)
			throw new InvalidTargetException();
		if (getTarget() == null)
			throw new InvalidTargetException();
		

		// Use on target and removing the vaccine
		if (!this.isAdj(this.getTarget())) {
			throw new InvalidTargetException("not adj");
		} else {
			this.getVaccineInventory().get(0).use(this);
			this.setActionsAvailable(this.getActionsAvailable() - 1);
		}


	}
	

	public boolean checkDeath() {
		if (this.getCurrentHp() == 0)
			return true;
		return false;

	}
	public String toString() {
		
	String s;
	if(this instanceof Fighter) {
	s=( "Fighter" +" "+this.getName()+'\n'+" MaxHp: " + getMaxHp()+'\n' +   +'\n' +" AttackDamage: " +getAttackDmg()+'\n'+ " Avaliable actions: " + getMaxActions()+'\n');
	return s;
	}
	else if(this instanceof Medic) {
		s=( "Medic" +" "+this.getName()+'\n'+" MaxHp: " + getMaxHp()+'\n' +   +'\n' +" AttackDamage: " +getAttackDmg()+'\n'+ " Available actions: " + getMaxActions()+'\n');
		return s;
	}
	else 
		 s = ("Explorer"+ " "+getName()+'\n'+" MaxHp: " + getMaxHp()+'\n' +   +'\n' +" AttackDamage: " +getAttackDmg()+'\n'+"Available actions:"+ getMaxActions());
	return s;
}


public String CurrenttoString() {
	
	String s;
	if(this instanceof Fighter) {
	s=( "Fighter" +" "+this.getName()+'\n'+" HP: " + getCurrentHp()+'\n' +   +'\n' +" AttackDamage: " +getAttackDmg()+'\n'+ " Avaliable actions: " + getActionsAvailable()+'\n');
	return s;
	}
	else if(this instanceof Medic) {
		s=( "Medic" +" "+this.getName()+'\n'+" MaxHp: " + getCurrentHp()+'\n' +   +'\n' +" AttackDamage: " +getAttackDmg()+'\n'+ " Available actions: " + getActionsAvailable()+'\n');
		return s;
	}
	else 
		 s = ("Explorer"+ " "+getName()+'\n'+" MaxHp: " + getCurrentHp()+'\n' +   +'\n' +" AttackDamage: " +getAttackDmg()+'\n'+"Available actions:"+ getActionsAvailable()+"\n");
	return s;
}
}
