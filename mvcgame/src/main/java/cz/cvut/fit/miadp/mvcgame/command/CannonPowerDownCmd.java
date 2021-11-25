package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonPowerDownCmd extends AbstractGameCommand {
    public CannonPowerDownCmd(IGameModel model) {
        subject = model;
    }

    @Override
    protected void execute() {
        subject.cannonPowerDown();
    }
}
