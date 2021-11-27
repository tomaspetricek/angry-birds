package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsCollision extends LifetimeLimitedGameObject {

    protected AbsCollision(Position pos) {
        super(pos);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCollision(this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
