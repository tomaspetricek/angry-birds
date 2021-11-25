package cz.cvut.fit.miadp.mvcgame.model.gameObjects.familyA;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

public class CannonA extends AbsCannon {

    private IGameObjectsFactory goFact;

    private double angle;
    private int power;
    private List<AbsMissile> shootingBatch;

    public CannonA( Position initialPosition, IGameObjectsFactory goFact ){
        this.position = initialPosition;
        this.goFact = goFact;

        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;

        this.shootingBatch = new ArrayList<AbsMissile>( );
        this.shootingMode = AbsCannon.SINGLE_SHOOTING_MODE;
    }

    public void moveUp( ){
        this.move( new Vector(0, -1 * MvcGameConfig.MOVE_STEP ) );
    }

    public void moveDown( ){
        this.move( new Vector(0, MvcGameConfig.MOVE_STEP ) );
    }

    @Override
    public List<AbsMissile> shoot( ) {
        this.shootingBatch.clear( );
        this.shootingMode.shoot( this );
        return this.shootingBatch;
    }

    @Override
    public void primitiveShoot( ) {
        this.shootingBatch.add( this.goFact.createMissile( this.angle, this.power ) );
    }

    @Override
    public void aimUp() {
        if (angle - MvcGameConfig.ANGLE_STEP > -Math.PI/2) {
            this.angle -= MvcGameConfig.ANGLE_STEP;
        }else {
            angle = -Math.PI/2;
        }
    }

    @Override
    public void aimDown() {
        if (angle + MvcGameConfig.ANGLE_STEP < Math.PI/2) {
            this.angle += MvcGameConfig.ANGLE_STEP;
        } else {
            angle = Math.PI/2;
        }
    }

    @Override
    public void powerUp() {
        this.power += MvcGameConfig.POWER_STEP;
    }

    @Override
    public void powerDown() {
        if ( this.power - MvcGameConfig.POWER_STEP > 0 ){
            this.power -= MvcGameConfig.POWER_STEP;
        }  
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public double getAngle() {
        return angle;
    }
}
