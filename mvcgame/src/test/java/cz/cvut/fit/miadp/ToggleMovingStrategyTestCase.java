package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.command.MoveCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.command.ToggleMovingStrategyCmd;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class ToggleMovingStrategyTestCase {
    @Test
    public void toggleMovingStrategy() {
        IGameModel model = new GameModel();
        IMovingStrategy initStrategy = model.getMovingStrategy();
        model.registerCommand(new ToggleMovingStrategyCmd(model));
        model.update();
        model.registerCommand(new ToggleMovingStrategyCmd(model));
        model.update();
        model.registerCommand(new ToggleMovingStrategyCmd(model));
        model.update();
        IMovingStrategy afterThreeToggles = model.getMovingStrategy();
        Assert.assertEquals(initStrategy.getClass(), afterThreeToggles.getClass());
    }
}
