package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.world.Cell;
import model.world.CharacterCell;

public class Explorer extends Hero {

	public Explorer(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);

	}

	@Override
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		super.useSpecial();
		

		for(Cell[] rows:Game.map) {
			for(Cell column:rows) {
				column.setVisible(true);
			}
		}
	}

}
