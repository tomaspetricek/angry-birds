package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;

public class GroupShootingMode implements IShootingMode {
    @Override
    public String getName() {
        return "Group";
    }

    @Override
    public void shoot(AbsCannon cannon) {
        cannon.groupShoot();
    }
}
