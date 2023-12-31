package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameRenderer implements IVisitor {

    private IGameGraphics gr;

    public void setGraphicContext(IGameGraphics gr) {
        this.gr = gr;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        this.gr.drawImage("images/cannon.png", cannon.getPosition());
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        this.gr.drawImage("images/missile.png", missile.getPosition());
    }

    @Override
    public void visitEnemy(AbsEnemy enemy) {
        this.gr.drawImage(enemy.getPath(), enemy.getPosition());
    }

    @Override
    public void visitGameInfo(AbsGameInfo info) {
        gr.drawText(info.getText(), info.getPosition());
    }

    @Override
    public void visitCollision(AbsCollision collision) {
        this.gr.drawImage(collision.getPath(), collision.getPosition());
    }
}
