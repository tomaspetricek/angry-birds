package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCollision;

public class CollisionA extends AbsCollision {
    public CollisionA(Position pos) {
        super(pos);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (CollisionA) super.clone();
    }
}