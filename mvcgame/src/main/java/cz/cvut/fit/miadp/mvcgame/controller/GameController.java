package cz.cvut.fit.miadp.mvcgame.controller;

import java.util.List;

import cz.cvut.fit.miadp.mvcgame.command.*;
import cz.cvut.fit.miadp.mvcgame.memento.Caretaker;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class GameController {

    public IGameModel model;

    public GameController(IGameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for (String code : pressedKeysCodes) {
            switch (code) {
                case "UP":
                    this.model.registerCommand(new MoveCannonUpCmd(this.model));
                    break;
                case "DOWN":
                    this.model.registerCommand(new MoveCannonDownCmd(this.model));
                    break;
                case "SPACE":
                    model.registerCommand(new CannonShootCmd(model));
                    break;
                case "A":
                    model.registerCommand(new AimCannonUpCmd(model));
                    break;
                case "Y":
                    model.registerCommand(new AimCannonDownCmd(model));
                    break;
                case "F":
                    model.registerCommand(new CannonPowerUpCmd(model));
                    break;
                case "D":
                    model.registerCommand(new CannonPowerDownCmd(model));
                    break;
                case "M":
                    model.registerCommand(new ToggleMovingStrategyCmd(model));
                    break;
                case "N":
                    model.registerCommand(new ToggleShootingModeCmd(model));
                    break;
                case "Z":
                    model.registerCommand(new UndoLastCmd(model));

                default:
                    //nothing
            }
        }
    }
}
