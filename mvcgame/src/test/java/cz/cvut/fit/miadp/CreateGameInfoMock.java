package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsGameInfo;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateGameInfoMock {
    @Test
    public void createGameInfo() {
        IGameModel model = mock(GameModel.class);
        double angle = 0.5;
        int power = 10;
        int score = 100;
        IShootingMode mode = new SingleShootingMode();
        IMovingStrategy strategy = new SimpleMovingStrategy();
        String expectedText = String.format("Power: %d, Angle: %.1f, Score: %d\nShooting mode: %s, Moving strategy: %s",
                power, angle, score, mode.getName(), strategy.getName());

        // prepare mocking
        when(model.getCannonAngle()).thenReturn(angle);
        when(model.getCannonPower()).thenReturn(power);
        when(model.getScore()).thenReturn(score);
        when(model.getCannonShootingMode()).thenReturn(mode);
        when(model.getMovingStrategy()).thenReturn(strategy);

        // create info
        IGameObjectsFactory goFact = new GameObjectsFactoryA(model);
        AbsGameInfo info = goFact.createGameInfo();

        // perform assertions
        Assert.assertEquals(expectedText, info.getText());
    }
}
