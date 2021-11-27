package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.command.MoveCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.command.ToggleMovingStrategyCmd;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticSineMovingStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class ToggleMovingStrategyTestCase {
    @Test
    public void toggleMovingStrategy() {
        IGameModel model = new GameModel();
        IMovingStrategy initStrategy = model.getMovingStrategy();

        // toggle moving strategy
        model.registerCommand(new ToggleMovingStrategyCmd(model));
        model.update();
        IMovingStrategy afterOneToggle = model.getMovingStrategy();

        // toggle moving strategy
        model.registerCommand(new ToggleMovingStrategyCmd(model));
        model.update();
        IMovingStrategy afterTwoToggle = model.getMovingStrategy();

        // toggle moving strategy
        model.registerCommand(new ToggleMovingStrategyCmd(model));
        model.update();
        IMovingStrategy afterThreeToggles = model.getMovingStrategy();

        // perform assertions
        Assert.assertEquals(RealisticMovingStrategy.class, afterOneToggle.getClass());
        Assert.assertEquals(RealisticSineMovingStrategy.class, afterTwoToggle.getClass());
        Assert.assertEquals(initStrategy.getClass(), afterThreeToggles.getClass());
    }
}
