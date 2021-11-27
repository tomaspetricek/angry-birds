package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;

public class ForemanEnemyA extends AbsEnemy {

    public ForemanEnemyA(Position pos) {
        super(pos);
    }

    @Override
    public AbsCollision createCollision() {
        return new ForemanCollisionA(position);
    }

    @Override
    public String getPath() {
        return "images/enemy2.png";
    }
}
