package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import javafx.geometry.Pos;


public class MinionEnemyA extends AbsEnemy {

    public MinionEnemyA(Position pos) {
        super(pos);
    }

    @Override
    public AbsCollision createCollision() {
        return new MinionCollisionA(position);
    }
}