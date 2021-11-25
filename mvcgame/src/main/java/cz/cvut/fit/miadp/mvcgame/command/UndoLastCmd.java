package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class UndoLastCmd extends AbstractGameCommand {
    public UndoLastCmd(IGameModel model) {
        subject = model;
    }

    @Override
    protected void execute() {
        subject.undoLastCommand();
    }
}
