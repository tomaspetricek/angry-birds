package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public class AbsEnemy extends LifetimeLimitedGameObject {
    
    protected AbsEnemy(Position initPos) {
        super(initPos);
    }

    // wasHit (Line line)
    // timeHit
    // lifeExpired

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
