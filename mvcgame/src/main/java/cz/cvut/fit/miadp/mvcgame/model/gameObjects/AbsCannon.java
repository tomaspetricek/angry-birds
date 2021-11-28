package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import java.util.List;

import cz.cvut.fit.miadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.GroupShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsCannon extends GameObject {

    protected IShootingMode shootingMode;
    protected static IShootingMode SINGLE_SHOOTING_MODE = new SingleShootingMode();
    protected static IShootingMode DOUBLE_SHOOTING_MODE = new DoubleShootingMode();
    protected static IShootingMode GROUP_SHOOTING_MODE = new GroupShootingMode();

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void aimUp();

    public abstract void aimDown();

    public abstract void powerUp();

    public abstract void powerDown();

    public abstract List<AbsMissile> shoot();

    public abstract void primitiveShoot();

    public abstract void groupShoot();

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }

    public void toggleShootingMode() {
        if (this.shootingMode instanceof SingleShootingMode) {
            this.shootingMode = DOUBLE_SHOOTING_MODE;
        } else if (this.shootingMode instanceof DoubleShootingMode) {
            this.shootingMode = GROUP_SHOOTING_MODE;
        } else if (this.shootingMode instanceof GroupShootingMode) {
            this.shootingMode = SINGLE_SHOOTING_MODE;
        }
    }

    public IShootingMode getShootingMode() {
        return shootingMode;
    }

    public void setShootingMode(IShootingMode shootingMode) {
        this.shootingMode = shootingMode;
    }

    public abstract int getPower();

    public abstract double getAngle();
}
