package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

public class RealisticSineMovingStrategy implements IMovingStrategy {

    @Override
    public void updatePosition(AbsMissile missile) {
        long time = missile.getAge() / 100;

        double period = 10;
        double amplitude = 20;
        double angularFrequency = 2 * Math.PI / period;
        double oscillationPhase = angularFrequency * time;

        int dX = (int) time;
        int dY = - (int) (amplitude * Math.sin(oscillationPhase));
        missile.move(new Vector(dX, dY));
    }
}
