package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;

public interface IVisitor {
    void visitCannon( AbsCannon cannon );
    void visitMissile( AbsMissile missile );
    //TODO: enemies, collisions...
    void visitEnemy(AbsEnemy enemy);
    void visitGameInfo(AbsGameInfo info);
    void visitCollision(AbsCollision collision);
}
