package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

public interface IGameObjectsFactory {
    AbsCannon createCannon( );
    AbsMissile createMissile( double initAngle, int initVelocity );
    //TODO: enemies, gameInfo, collisions
    
}
