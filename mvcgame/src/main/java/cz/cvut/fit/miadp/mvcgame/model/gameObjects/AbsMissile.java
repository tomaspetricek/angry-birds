package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsMissile extends LifetimeLimitedGameObject {
    protected double initAngle;
    protected int initVelocity;
    protected int hitRadius;

    protected AbsMissile(Position initialPosition, double initAngle, int initVelocity, int hitRadius) {
        super(initialPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
        this.hitRadius = hitRadius;
    }

    public abstract void move();

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }

    public int getInitVelocity() {
        return this.initVelocity;
    }

    public double getInitAngle() {
        return this.initAngle;
    }

    protected boolean insideCircle(Position center, int radius, Position point) {
        double squareDist = (Math.pow(center.getX() - point.getX(), 2) + Math.pow(center.getY() - point.getY(), 2));
        return squareDist <= Math.pow(radius, 2);
    }

    public abstract boolean insideHitRadius(Position pos);

}
