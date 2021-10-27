package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;

public abstract class GameObject {
    protected Position position;

    public void move(Vector v) {
        this.position.add(v);
    }

    public Position getPosition() {
        return position;
    }
}
