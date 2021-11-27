package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject {

    protected AbsEnemy(Position pos) {
        position = pos;
    }

    // wasHit (Line line)
    // timeHit
    // lifeExpired

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
