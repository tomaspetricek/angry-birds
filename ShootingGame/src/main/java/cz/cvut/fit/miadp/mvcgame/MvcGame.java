package cz.cvut.fit.miadp.mvcgame;

import java.util.List;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
// in future, use Bridge to remove this dependency
import cz.cvut.fit.miadp.mvcgame.view.GameView;
import javafx.scene.canvas.GraphicsContext;

public class MvcGame
{
    private GameModel model;
    private GameView view;
    private GameController controller;

    public void init()
    {
        model = new GameModel();
        view = new GameView(model);
        controller = view.getController();
    }

    public void processPressedKeys(List<String> pressedKeysCodes)
    {
        controller.processPressedKeys(pressedKeysCodes);
    }

    public void render(GraphicsContext gr)
    {
        this.view.setGraphicsContext(gr);
    }

    public void update() {
        this.model.update();
    }

    public String getWindowTitle()
    {
        return "The MI-ADP.16 MvcGame";
    }

    public int getWindowWidth()
    {
        return MvcGameConfig.MAX_X;
    }

    public int getWindowHeight()
    {
        return  MvcGameConfig.MAX_Y;
    }
}