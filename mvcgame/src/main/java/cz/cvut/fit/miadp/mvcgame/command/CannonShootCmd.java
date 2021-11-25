package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonShootCmd extends AbstractGameCommand {
    public CannonShootCmd(IGameModel model) {
        subject = model;
    }

    @Override
    protected void execute() {
        subject.cannonShoot( );
    }
}
