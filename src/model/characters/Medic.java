package model.characters;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;

public class Medic extends Hero {

	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);

	}

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		super.useSpecial();
		if (this.getTarget() instanceof Zombie )
			throw new InvalidTargetException();
		if (getTarget() == null )
			throw new InvalidTargetException();
		if(!(isAdj(this.getTarget()))) {
			throw new InvalidTargetException();
		}

		Hero h = (Hero) getTarget();
		if (isSpecialAction()) {

			if (getTarget() == this)
				this.setCurrentHp(getMaxHp());
			else
				h.setCurrentHp(h.getMaxHp());

		}
	
	}

}
