package com.mygdx.game.entity;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

public class Cube extends MovingEntity {
    private static final String TYPE = "CUBE";
    private static final int[] size = new int[]{20, 20};
    private static final Vector2 CHASE_SPEED = new Vector2(1000, 1000);
    private static final float CUBE_ROTATION = 2;
    private static final float CHASE_TIME_TRIGGER = 0.4f;
    private final Entity target;

    public Cube(Sprite sprite, Entity target) {
        super(sprite, TYPE);
        this.target = target;
        state = State.INITIAL;
        rotation = CUBE_ROTATION;
        sprite.setSize(size[0], size[1]);
        sprite.setOriginCenter();
    }

    @Override
    public void update(float delta) {
        //handle state
        if (state == State.CHASE) {
            chase(delta);
        } else {
            move(velocity.x, velocity.y, delta);
        }
        sprite.rotate(rotation);

        // handle state change to chase
        Timer.instance().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                state = State.CHASE;
                velocity.set(CHASE_SPEED.x, CHASE_SPEED.y);
            }
        }, CHASE_TIME_TRIGGER);
    }


    /**
     * Chase the target each frame using normalizing vector calculation
     *
     * @param delta a Time between frame
     */
    private void chase(float delta) {
        float dx = target.getX() + target.getSprite().getWidth()/2 - getX();
        float dy = target.getY() + target.getSprite().getHeight()/2 - getY();
        double distance = Math.hypot(dx, dy);
        if (distance <= 0) {
            state = State.END;
        } else {
            dx = (float) (dx / distance);
            dy = (float) (dy / distance);
            move(dx * velocity.x, dy * velocity.y, delta);
            velocity.add(200, 200);
        }
    }

    public enum State {
        END, INITIAL, CHASE
    }
}
