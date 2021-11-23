package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

public interface IVisitor {
    void visitCannon( AbsCannon cannon );
    void visitMissile( AbsMissile missile );
    //TODO: enemies, collisions...
    void visitEnemy(AbsEnemy enemy);
}
