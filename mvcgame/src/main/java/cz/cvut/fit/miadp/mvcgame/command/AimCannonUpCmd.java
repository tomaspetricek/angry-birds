package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class AimCannonUpCmd extends AbstractGameCommand {
    public AimCannonUpCmd(IGameModel model) {
        subject = model;
    }

    @Override
    protected void execute() {
        subject.aimCannonUp();
    }
}
