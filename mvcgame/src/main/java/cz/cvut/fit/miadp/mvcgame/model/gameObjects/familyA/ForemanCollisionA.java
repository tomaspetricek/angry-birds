package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCollision;

public class ForemanCollisionA extends AbsCollision {
    public ForemanCollisionA(Position pos) {
        super(pos);
    }

    @Override
    public String getPath() {
        return "images/collision2.png";
    }
}
