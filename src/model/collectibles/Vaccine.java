package model.collectibles;

import java.awt.Point;
import java.io.NotActiveException;
import java.util.Random;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;
import model.world.CollectibleCell;

public class Vaccine implements Collectible {

	public Vaccine() {

	}

	public void pickUp(Hero h) {
	
		h.getVaccineInventory().add(this);
	}
	
	public void use(Hero h) throws InvalidTargetException, NoAvailableResourcesException, NotEnoughActionsException {

		h.getVaccineInventory().remove(this);
		Game.zombies.remove(h.getTarget());
		Random rn =new Random();
		int random1 = rn.nextInt(Game.availableHeroes.size());
		Hero Newhero = Game.availableHeroes.remove(random1);
		Game.heroes.add(Newhero);
		int x=h.getTarget().getLocation().x;
		int y=h.getTarget().getLocation().y;
		((CharacterCell)Game.map[x][y]).setCharacter(Newhero);
		Newhero.setLocation(new Point(x,y));
		//Game.vaccineUsed++;
	}
}