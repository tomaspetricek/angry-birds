package cz.cvut.fit.miadp.mvcgame.model;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;

public class GameModel implements IGameModel {

    private int score;
    private AbsCannon cannon;
    private AbsGameInfo info;
    private List<AbsEnemy> enemies;
    private List<AbsMissile> missiles;
    private List<AbsCollision> collisions;
    private List<IObserver> observers;
    private IGameObjectsFactory goFact;
    private Timer timer;
    private IMovingStrategy movingStrategy;

    private Queue<AbstractGameCommand> unexecuteCmds = new LinkedBlockingQueue<AbstractGameCommand>( );
    private Stack<AbstractGameCommand> executedCmds = new Stack<AbstractGameCommand>();

    public GameModel( ){
        this.goFact = new GameObjectsFactoryA( this );
        this.cannon = this.goFact.createCannon( );
        this.enemies = new ArrayList<AbsEnemy>();
        this.missiles = new ArrayList<AbsMissile>();
        this.collisions = new ArrayList<AbsCollision>();
        this.info = goFact.createGameInfo();
        this.observers = new ArrayList<IObserver>();
        this.score = 0;
        this.movingStrategy = new SimpleMovingStrategy( );
    }

    private void initTimer() {
        timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        timeTick();
                    }
                }, 0, MvcGameConfig.TIME_TICK_PERIOD
        );
    }

    public void timeTick() {
        // TODO implement
    }

    public Position getCannonPosition( ){
        return this.cannon.getPosition( );
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObservers( );
    }

    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyObservers( );
    }

    public void aimCannonUp( ){
        this.cannon.aimUp( );
        this.notifyObservers( );
    }

    public void aimCannonDown( ){
        this.cannon.aimDown( );
        this.notifyObservers( );
    }

    public void cannonPowerUp( ){
        this.cannon.powerUp( );
        this.notifyObservers( );
    }

    public void cannonPowerDown( ){
        this.cannon.powerDown( );
        this.notifyObservers( );
    }

    public void update( ) {
        this.executeCmds( );
        this.moveMissiles( );
    }

    private void executeCmds( ) {
        while( !this.unexecuteCmds.isEmpty( ) ){
            AbstractGameCommand cmd = this.unexecuteCmds.poll( );
            cmd.doExecute( );
            this.executedCmds.push( cmd );
        }
    }

    private void moveMissiles( ){
        for( AbsMissile missile : this.missiles ){
            missile.move( );
        }
        this.destroyMissiles();
        this.notifyObservers( );
    }

    private void destroyMissiles( ){
        List<AbsMissile> toRemove = new ArrayList<AbsMissile>();
        for( AbsMissile missile : this.missiles ){
            if ( missile.getPosition( ).getX( ) > MvcGameConfig.MAX_X ){
                toRemove.add( missile );
            }
        }
        this.missiles.removeAll( toRemove );
    }

    private void moveEnemies() {
        // TODO implement
    }

    @Override
    public void registerObserver(IObserver obs) {
        if( !this.observers.contains( obs ) ){
            this.observers.add( obs );
        }
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        if( this.observers.contains( obs ) ){
            this.observers.remove( obs );
        }
        
    }

    @Override
    public void notifyObservers() {
        for( IObserver obs: this.observers){
            obs.update( );
        }
        
    }

    public void cannonShoot( ){
        this.missiles.addAll( cannon.shoot( ) );
        this.notifyObservers( );
    }

    public List<AbsMissile> getMissiles( ) {
        return this.missiles;
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> go = new ArrayList<GameObject>();
        go.addAll( this.missiles );
        go.add( this.cannon );
        return go;
    }

    public IMovingStrategy getMovingStrategy( ){
        return this.movingStrategy;
    }

    public void toggleMovingStrategy( ){
        if ( this.movingStrategy instanceof SimpleMovingStrategy ){
            this.movingStrategy = new RealisticMovingStrategy( );
        }
        else if ( this.movingStrategy instanceof RealisticMovingStrategy ){
            this.movingStrategy = new SimpleMovingStrategy( );
        }
        else {
            //Another strategy
        }
    }

    public void toggleShootingMode( ){
        this.cannon.toggleShootingMode( );
    }

    private class Memento {
        private int score;
        private int cannonX;
        private int cannonY;
        //TODO GameModel state snapshot
    }

    public Object createMemento( ) {
        Memento m = new Memento( );
        m.score = this.score;
        m.cannonX = this.getCannonPosition( ).getX( );
        m.cannonY = this.getCannonPosition( ).getY( );
        return m;
    }

    public void setMemento( Object memento ) {
        Memento m = (Memento)memento;
        this.score = m.score;
        this.cannon.getPosition( ).setX( m.cannonX );
        this.cannon.getPosition( ).setY( m.cannonY );
    }

    @Override
    public void registerCommand(AbstractGameCommand cmd) {
        this.unexecuteCmds.add( cmd );
    }

    @Override
    public void undoLastCommand( ) {
        if( !this.executedCmds.isEmpty( ) ){
            AbstractGameCommand cmd = this.executedCmds.pop( );
            cmd.unExecute( );
            this.notifyObservers( );
        }
    }

}
