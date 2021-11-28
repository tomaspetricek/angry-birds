package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.*;

public class GameObjectsFactoryA implements IGameObjectsFactory {

    private IGameModel model;

    public GameObjectsFactoryA(IGameModel model) {
        this.model = model;
    }

    @Override
    public CannonA createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public MissileA createMissile(double initAngle, int initVelocity) {
        return new MissileA(
                (Position) model.getCannonPosition().clone(),
                initAngle,
                initVelocity,
                this.model.getMovingStrategy(),
                MvcGameConfig.MISSILE_HIT_RADIUS
        );
    }

    @Override
    public AbsEnemy createMinionEnemy(Position pos) {
        return new MinionEnemyA(pos);
    }

    @Override
    public AbsEnemy createForemanEnemy(Position pos) {
        return new ForemanEnemyA(pos);
    }

    @Override
    public AbsGameInfo createGameInfo() {
        return new GameInfoA(
                new Position(
                        MvcGameConfig.INFO_POS_X,
                        MvcGameConfig.INFO_POS_Y
                ),
                model
        );
    }

    @Override
    public AbsMissile createMissileGroup(double initAngle, int initVelocity) {
        return new MissileGroup(
                (Position) model.getCannonPosition().clone(),
                initAngle,
                initVelocity,
                MvcGameConfig.MISSILE_HIT_RADIUS,
                this.model.getMovingStrategy(),
                this
        );
    }
}
