package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameView implements IObserver {

    private GameController controller;
    private GameModel model;
    private GraphicsContext gr;
    private int updateCounter;

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        this.updateCounter = 1;
        this.model.registerObserver(this);
    }

    public GameController getController() {
        return controller;
    }

    public void render() {
        if (this.gr == null) {
            return;
        }

        if (this.updateCounter > 0) {
            // Clear the canvas
            this.gr.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
            this.drawCannon(gr);
        }
        this.updateCounter = 0;
    }

    private void drawCannon(GraphicsContext gr) {
        gr.drawImage(new Image("images/cannon.png"), model.getCannonPosition().getX(), model.getCannonPosition().getY());
    }

    public void setGraphicsContext(GraphicsContext gr) {
        this.gr = gr;
        this.render();
    }

    @Override
    public void update() {
        updateCounter++;
    }
}
