package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.visitor.GameRenderer;
// in future, use Bridge to remove this dependency
import javafx.scene.canvas.GraphicsContext;

public class GameView implements IObserver {

    private GameController controller;
    private IGameModel model;
    private IGameGraphics gr;
    private int updateCnt;
    private GameRenderer render;

    public GameView(IGameModel model) {
        this.model = model;
        this.controller = new GameController(model);
        this.updateCnt = 1;
        this.model.registerObserver(this);
        this.render = new GameRenderer();
    }

    public GameController getController() {
        return this.controller;
    }

    public void render() {
        if (this.gr == null) {
            return;
        }
        if (this.updateCnt > 0) {
            // Clear the canvas
            this.gr.clear();
            for (GameObject go : this.model.getGameObjects()) {
                go.acceptVisitor(this.render);
            }
            this.updateCnt = 0;
        }
    }

    public void setGraphicContext(IGameGraphics gr) {
        this.gr = gr;
        this.render.setGraphicContext(gr);
    }

    @Override
    public void update() {
        this.updateCnt++;
        this.render();
    }
}
