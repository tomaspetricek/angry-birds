package cz.cvut.fit.miadp.mvcgame.model;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.command.UndoLastCmd;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticSineMovingStrategy;
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

    private Queue<AbstractGameCommand> unexecuteCmds = new LinkedBlockingQueue<AbstractGameCommand>();
    private Stack<AbstractGameCommand> executedCmds = new Stack<AbstractGameCommand>();

    public GameModel() {
        this.goFact = new GameObjectsFactoryA(this);
        this.cannon = this.goFact.createCannon();
        this.enemies = new ArrayList<AbsEnemy>();
        this.missiles = new ArrayList<AbsMissile>();
        this.collisions = new ArrayList<AbsCollision>();
        this.info = goFact.createGameInfo();
        this.observers = new ArrayList<IObserver>();
        this.score = 0;
        this.movingStrategy = new SimpleMovingStrategy();
        spawnEnemies(MvcGameConfig.ENEMIES_CNT);
        initTimer();
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private Position getRandomEnemyPosition() {
        int x = getRandomNumber(0, MvcGameConfig.MAX_X);
        int y = getRandomNumber(0, MvcGameConfig.MAX_Y);
        return new Position(x, y);
    }

    private void spawnEnemies(int nEnemies) {
        AbsEnemy enemy;

        for (int i = 0; i < nEnemies; i++) {
            enemy = goFact.createEnemy(getRandomEnemyPosition());
            enemies.add(enemy);
        }
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

    public void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public void timeTick() {
        removeCollisions();
    }

    private void removeCollisions() {
        if (collisions.isEmpty()) return;

        List<AbsCollision> toRemove = new ArrayList<>();

        for (AbsCollision collision : collisions) {
            if (collision.getAge() > MvcGameConfig.ENEMY_MAX_AGE) {
                toRemove.add(collision);
            }
        }

        int nEnemies = toRemove.size();

        if (nEnemies > 0) {
            collisions.removeAll(toRemove);
            spawnEnemies(nEnemies);
            notifyObservers();
        }
    }

    public Position getCannonPosition() {
        return this.cannon.getPosition();
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObservers();
    }

    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyObservers();
    }

    public void aimCannonUp() {
        this.cannon.aimUp();
        this.notifyObservers();
    }

    public void aimCannonDown() {
        this.cannon.aimDown();
        this.notifyObservers();
    }

    public void cannonPowerUp() {
        this.cannon.powerUp();
        this.notifyObservers();
    }

    public void cannonPowerDown() {
        this.cannon.powerDown();
        this.notifyObservers();
    }

    public void update() {
        this.executeCmds();
        this.resolveCollisions();
        this.moveMissiles();
    }

    private void resolveCollisions() {
        List<AbsMissile> missilesToRemove = new ArrayList<AbsMissile>();
        List<AbsEnemy> enemiesToRemove = new ArrayList<AbsEnemy>();
        boolean notify = false;

        for (AbsMissile missile : missiles) {
            for (AbsEnemy enemy : enemies) {
                if (missile.insideHitRadius(enemy.getPosition())) {
                    missilesToRemove.add(missile);
                    enemiesToRemove.add(enemy);
                    score++;

                    Position enemyPos = enemy.getPosition();
                    collisions.add(goFact.createCollision(
                            new Position(
                                    enemyPos.getX(),
                                    enemyPos.getY()
                            )
                    ));
                    notify = true;
                }
            }
        }

        missiles.removeAll(missilesToRemove);
        enemies.removeAll(enemiesToRemove);

        if (notify) {
            notifyObservers();
        }
    }

    private void executeCmds() {
        while (!this.unexecuteCmds.isEmpty()) {
            AbstractGameCommand cmd = this.unexecuteCmds.poll();
            cmd.doExecute();

            if (!(cmd instanceof UndoLastCmd)) {
                this.executedCmds.push(cmd);
            }
        }
    }

    private void moveMissiles() {
        for (AbsMissile missile : this.missiles) {
            missile.move();
        }
        this.destroyMissiles();
        this.notifyObservers();
    }

    private void destroyMissiles() {
        List<AbsMissile> toRemove = new ArrayList<AbsMissile>();
        for (AbsMissile missile : this.missiles) {
            if (missile.getPosition().getX() > MvcGameConfig.MAX_X) {
                toRemove.add(missile);
            }
        }
        this.missiles.removeAll(toRemove);
    }

    @Override
    public void registerObserver(IObserver obs) {
        if (!this.observers.contains(obs)) {
            this.observers.add(obs);
        }
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        if (this.observers.contains(obs)) {
            this.observers.remove(obs);
        }
    }

    @Override
    public void notifyObservers() {
        for (IObserver obs : this.observers) {
            obs.update();
        }

    }

    public void cannonShoot() {
        this.missiles.addAll(cannon.shoot());
        this.notifyObservers();
    }

    public List<AbsMissile> getMissiles() {
        return this.missiles;
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> go = new ArrayList<GameObject>();
        go.addAll(this.missiles);
        go.add(this.cannon);
        go.add(info);
        go.addAll(enemies);
        go.addAll(collisions);
        return go;
    }

    public IMovingStrategy getMovingStrategy() {
        return this.movingStrategy;
    }

    public void toggleMovingStrategy() {
        if (this.movingStrategy instanceof SimpleMovingStrategy) {
            this.movingStrategy = new RealisticMovingStrategy();
        } else if (this.movingStrategy instanceof RealisticMovingStrategy) {
            this.movingStrategy = new RealisticSineMovingStrategy();
        } else if (this.movingStrategy instanceof RealisticSineMovingStrategy){
            this.movingStrategy = new SimpleMovingStrategy();
        }
    }

    public void toggleShootingMode() {
        this.cannon.toggleShootingMode();
    }

    private class Memento {
        private int score;
        private int cannonX;
        private int cannonY;
        private List<AbsEnemy> enemies;
        private IMovingStrategy movingStrategy;
    }

    public Object createMemento() {
        Memento m = new Memento();
        m.score = this.score;
        m.cannonX = this.getCannonPosition().getX();
        m.cannonY = this.getCannonPosition().getY();
        m.enemies = new ArrayList<AbsEnemy>();
        m.enemies.addAll(this.enemies);
        m.movingStrategy = this.movingStrategy;
        return m;
    }

    public void setMemento(Object memento) {
        Memento m = (Memento) memento;
        this.score = m.score;
        this.cannon.getPosition().setX(m.cannonX);
        this.cannon.getPosition().setY(m.cannonY);
        this.missiles.clear();
        this.collisions.clear();
        this.enemies.clear();
        this.enemies.addAll(m.enemies);
        this.movingStrategy = m.movingStrategy;
    }

    @Override
    public void registerCommand(AbstractGameCommand cmd) {
        this.unexecuteCmds.add(cmd);
    }

    @Override
    public void undoLastCommand() {
        if (!this.executedCmds.isEmpty()) {
            AbstractGameCommand cmd = this.executedCmds.pop();
            cmd.unExecute();
            this.notifyObservers();
        }
    }

    @Override
    public int getCannonPower() {
        return cannon.getPower();
    }

    @Override
    public double getCannonAngle() {
        return cannon.getAngle();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public List<AbsCollision> getCollisions() {
        return collisions;
    }
}
