package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsGameInfo;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.*;

public class GameObjectsFactoryA implements IGameObjectsFactory {

    private IGameModel model;

    public GameObjectsFactoryA( IGameModel model ){
        this.model = model;
    }


    @Override
    public CannonA createCannon( ) {
        return new CannonA( new Position( MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y ), this );
    }

    @Override
    public MissileA createMissile( double initAngle, int initVelocity ) {
        return new MissileA( 
            new Position( 
                model.getCannonPosition( ).getX(), 
                model.getCannonPosition().getY( ) 
            ), 
            initAngle,
            initVelocity, 
            this.model.getMovingStrategy( )
        );
    }

    @Override
    public AbsEnemy createEnemy(Position pos) {
        return new EnemyA(pos);
    }

    @Override
    public AbsCollision createCollision(Position pos) {
        return new CollisionA(pos);
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
}
