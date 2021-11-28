package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

import java.util.ArrayList;
import java.util.List;


public class MissileGroup extends AbsMissile {
    private IMovingStrategy movingStrategy;
    List<AbsMissile> missiles = new ArrayList<>();

    protected MissileGroupA(Position initialPosition, double initAngle, int initVelocity, int hitRadius,  IMovingStrategy movingStrategy, int nMissiles) {
        super(initialPosition, initAngle, initVelocity, hitRadius);
        this.movingStrategy = movingStrategy;
        addMissiles(nMissiles);
    }

    private void addMissiles(int nMissiles) {

    }

    @Override
    public void move() {

    }

    @Override
    public boolean insideHitRadius(Position pos) {
        return false;
    }
}
