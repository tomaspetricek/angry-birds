package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.command.CannonPowerDownCmd;
import cz.cvut.fit.miadp.mvcgame.command.CannonPowerUpCmd;
import cz.cvut.fit.miadp.mvcgame.command.ToggleMovingStrategyCmd;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import org.junit.Assert;
import org.junit.Test;

public class ChangeCannonPowerTestCase {
    @Test
    public void changeCannonPower() {
        IGameModel model = new GameModel();
        int initPower = model.getCannonPower();

        // increase power
        model.registerCommand(new CannonPowerUpCmd(model));
        model.update();
        int powerAfterIncrease = model.getCannonPower();

        // decrease power
        model.registerCommand(new CannonPowerDownCmd(model));
        model.update();
        int powerAfterDecrease = model.getCannonPower();

        // perform tests
        Assert.assertEquals(initPower + MvcGameConfig.POWER_STEP, powerAfterIncrease);
        Assert.assertEquals(initPower, powerAfterDecrease);
    }
}
