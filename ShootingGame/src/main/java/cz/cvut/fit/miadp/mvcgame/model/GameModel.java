package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.Cannon;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;

import java.util.ArrayList;
import java.util.List;


public class GameModel implements IObservable {

    private Cannon cannon;
    private List<IObserver> observers;

    public GameModel() {
        this.cannon = new Cannon(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y));
        this.observers = new ArrayList<IObserver>();
    }

    public Position getCannonPosition() {
        return cannon.getPosition();
    }

    public void moveCannonUp(){
        cannon.moveUp();
        this.notifyObservers();
    }

    public void moveCannonDown(){
        cannon.moveDown();
        this.notifyObservers();
    }

    public void update() {
        //this.moveMissiles( );
        //this.moveCannon( );
    }

    @Override
    public void registerObserver(IObserver obs) {
        if (!this.observers.contains(obs)) {
            observers.add(obs);
        }
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        if (this.observers.contains(obs)) {
            observers.add(obs);
        }
    }

    @Override
    public void notifyObservers() {
        for (IObserver obs : observers) {
            obs.update();
        }
    }
}
