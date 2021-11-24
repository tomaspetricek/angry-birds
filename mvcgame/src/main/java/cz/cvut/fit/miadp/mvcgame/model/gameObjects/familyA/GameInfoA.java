package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsGameInfo;


public class GameInfoA extends AbsGameInfo {

    public GameInfoA(Position pos, IGameModel model) {
        super(pos, model);
    }

    @Override
    public String getText() {
        return null;
    }
}
