package cz.cvut.fit.miadp.mvcgame.model.gameObjects;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;


public abstract class AbsGameInfo extends GameObject {
    protected IGameModel model;

    protected AbsGameInfo(Position pos, IGameModel model){
        this.position = pos;
        this.model = model;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitGameInfo(this);
    }

    public abstract String getText();
}
