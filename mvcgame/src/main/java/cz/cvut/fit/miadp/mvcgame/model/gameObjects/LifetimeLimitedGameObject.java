package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import cz.cvut.fit.miadp.mvcgame.model.Position;

public abstract class LifetimeLimitedGameObject extends GameObject implements Cloneable {

    private LocalDateTime bornAt;

    protected LifetimeLimitedGameObject( Position position ){
        this.position = position;
        this.bornAt = LocalDateTime.now( );
    }

    public long getAge( ){
        return ChronoUnit.MILLIS.between( this.bornAt, LocalDateTime.now( ) );
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (LifetimeLimitedGameObject) super.clone();
    }
}
