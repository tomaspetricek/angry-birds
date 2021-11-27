package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;

public interface IGameObjectsFactory {
    AbsCannon createCannon();

    AbsMissile createMissile(double initAngle, int initVelocity);

    AbsEnemy createMinionEnemy(Position pos);

    AbsEnemy createForemanEnemy(Position pos);

    AbsGameInfo createGameInfo();

    //TODO: enemies, gameInfo, collisions

}
