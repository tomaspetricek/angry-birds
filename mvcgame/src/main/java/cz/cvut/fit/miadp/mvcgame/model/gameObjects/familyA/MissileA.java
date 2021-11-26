package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public class MissileA extends AbsMissile {

    private IMovingStrategy movingStrategy;

    public MissileA(Position initialPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy, int hitRadius) {
        super(initialPosition, initAngle, initVelocity, hitRadius);
        this.movingStrategy = movingStrategy;
    }

    @Override
    public void move() {
        this.movingStrategy.updatePosition(this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (MissileA) super.clone();
    }
}
