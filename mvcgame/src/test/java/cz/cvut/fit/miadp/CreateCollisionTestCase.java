package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateCollisionTestCase {
    @Test
    public void createCollision() {
        /*
        IGameModel model = new GameModel();
        model.cannonShoot();

        missile.move(new Vector(100, 0));
        AbsEnemy enemy = goFact.createEnemy(new Position(missile.getPosition().getX(), missile.getPosition().getY()));
        model.update();
        List<AbsCollision> collisions = model.getCollisions();
        Assert.assertEquals(model.getCollisions().size(), 1);
         */
    }
}
