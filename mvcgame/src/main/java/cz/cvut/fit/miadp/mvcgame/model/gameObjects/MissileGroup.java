package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;


public class MissileGroup extends AbsMissile {
    private IMovingStrategy movingStrategy;
    private IGameObjectsFactory goFact;
    private List<AbsMissile> missiles;
    private static final int SPACE_BETWEEN_MISSILES = 50;

    public MissileGroup(Position initialPosition, double initAngle, int initVelocity, int hitRadius,
                        IMovingStrategy movingStrategy, IGameObjectsFactory goFact) {
        super(initialPosition, initAngle, initVelocity, hitRadius);
        this.movingStrategy = movingStrategy;
        this.goFact = goFact;
        missiles = new ArrayList<>();
        addMissiles();
    }

    private void addMissiles() {
        AbsMissile m1 = goFact.createMissile(initAngle, initVelocity);
        m1.move(new Vector(0, -SPACE_BETWEEN_MISSILES));
        missiles.add(m1);
        AbsMissile m2 = goFact.createMissile(initAngle, initVelocity);
        missiles.add(m2);
        AbsMissile m3 = goFact.createMissile(initAngle, initVelocity);
        m3.move(new Vector(0, SPACE_BETWEEN_MISSILES));
        missiles.add(m3);
    }

    @Override
    public void move() {
        for(AbsMissile missile : missiles)
            this.movingStrategy.updatePosition(missile);
    }

    @Override
    public boolean insideHitRadius(Position pos) {
        for (AbsMissile missile : missiles) {
            if (missile.insideHitRadius(pos)) return true;
        }
        return false;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        for (AbsMissile missile : missiles) {
            visitor.visitMissile(missile);
        }
    }
}
