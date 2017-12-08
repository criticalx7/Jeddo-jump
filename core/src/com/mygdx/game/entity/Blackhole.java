package com.mygdx.game.entity;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Objects;

public class Blackhole extends MovingEntity {
    private static final int[] size = new int[]{140, 100};
    private static final float BASE_VX = 0;
    private static final float BASE_VY = 100;
    private MovingEntity target;

    public Blackhole(Sprite sprite) {
        super(sprite, "BLACKHOLE");
        velocity.set(BASE_VX, BASE_VY);
        sprite.setSize(size[0], size[1]);
        sprite.setOriginCenter();
        sprite.getBoundingRectangle().height -= 60;
        sprite.getBoundingRectangle().width -= 60;
    }

    @Override
    public void update(float delta) {
        move(velocity.x, velocity.y, delta);
        if (getTimer() > 7) {
            velocity.y *= -1;
            setTimer(0);
        }
        if (state == State.SUCK) {
            suck(target);
        }
        sprite.rotate(2000 * delta);
        setTimer(getTimer() + 1);
    }

    public void suck(MovingEntity entity) {
        if (Objects.nonNull(entity)) {
            target = entity;
            state = State.SUCK;
            target.getVelocity().set(0, 0);
        }

        float dx =  getX() + sprite.getWidth()/2 - target.getX();
        float dy = getY() + sprite.getHeight()/2 - target.getY();
        double distance = Math.hypot(dx, dy);
        if (distance <= 0) {
            return;
        } else {
            dx = (float) (dx / distance);
            dy = (float) (dy / distance);
            target.move(dx *  100, dy *100, Gdx.graphics.getDeltaTime());
            target.sprite.rotate(2000 * Gdx.graphics.getDeltaTime());
            //velocity.add(200, 200);
        }

    }

    public enum State {
        END, SUCK
    }
}
