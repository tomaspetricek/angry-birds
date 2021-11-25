package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonPowerUpCmd extends AbstractGameCommand {
    public CannonPowerUpCmd(IGameModel model) {
        subject = model;
    }

    @Override
    protected void execute() {
        subject.cannonPowerUp();
    }
}
