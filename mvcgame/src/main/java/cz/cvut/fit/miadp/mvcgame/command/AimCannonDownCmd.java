package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class AimCannonDownCmd extends AbstractGameCommand {
    public AimCannonDownCmd(IGameModel model) {
        subject = model;
    }

    @Override
    protected void execute() {
        subject.aimCannonDown();
    }
}
