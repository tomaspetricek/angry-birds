package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;

public class Cannon extends GameObject {
    /*
    private int power;
    private int angle;
     */
    public Cannon(Position initPos) {
        this.position = initPos;
    }

    public void moveUp() {
        this.move(new Vector(0, -MvcGameConfig.MOVE_STEP));
    }

    public void moveDown() {
        this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }
}
