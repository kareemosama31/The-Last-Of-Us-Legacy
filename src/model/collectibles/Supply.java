package model.collectibles;

import engine.Game;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;
import model.world.CharacterCell;
import model.world.CollectibleCell;

public class Supply implements Collectible {
	public Supply() {
	}


	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);

	}

	public void use(Hero h) throws NoAvailableResourcesException {

		h.getSupplyInventory().remove(0);
		h.setSpecialAction(true);

	}
}
