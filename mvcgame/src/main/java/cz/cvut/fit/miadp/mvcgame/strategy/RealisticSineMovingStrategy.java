package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

public class RealisticSineMovingStrategy implements IMovingStrategy {

    @Override
    public void updatePosition(AbsMissile missile) {
        long time = missile.getAge() / 100;
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();

        double period = 10;
        double amplitude = 20;
        double angularFrequency = 2 * Math.PI / period;
        double oscillationPhase = angularFrequency * time;

        int dX = (int) (initVelocity * time);
        int dY = - (int) (amplitude * Math.sin(oscillationPhase));
        missile.move(new Vector(dX, dY));
    }

    @Override
    public String getName() {
        return "RealisticSine";
    }
}
