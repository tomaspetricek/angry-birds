package cz.cvut.fit.miadp.mvcgame;

import java.util.List;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.memento.Caretaker;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.proxy.GameModelProxy;
import cz.cvut.fit.miadp.mvcgame.view.GameView;
// in future, use Bridge to remove this dependency
import javafx.scene.canvas.GraphicsContext;

public class MvcGame
{
    private IGameModel model;
    private GameView view;
    private GameController controller;

    public void init( ) {
        this.model = new GameModelProxy( new GameModel( ) );
        this.view = new GameView( model );
        controller = view.getController( );

        Caretaker.getInstance( ).setModel( model );
    }

    public void processPressedKeys(List<String> pressedKeysCodes)
    {
        this.controller.processPressedKeys(pressedKeysCodes);
    }

    public void update()
    {
        this.model.update( );
    }

    public void stop() {
        model.stopTimer();
    }

    public void render(IGameGraphics gr)
    {
        this.view.setGraphicContext( gr );
    }

    public String getWindowTitle()
    {
        return "The NI-ADP MvcGame";
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