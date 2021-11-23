package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.CannonA;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA.MissileA;

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
    
}
