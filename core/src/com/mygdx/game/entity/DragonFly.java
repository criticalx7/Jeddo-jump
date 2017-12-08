package com.mygdx.game.entity;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.JeddoJump;

public class DragonFly extends MovingEntity {
    private static final int[] size = new int[]{105, 52};
    private static final float BASE_VX = 200;
    private static final float BASE_VY = 100;

    public DragonFly(Sprite sprite) {
        super(sprite, "ENEMY");
        velocity.set(BASE_VX, BASE_VY);
        sprite.setSize(size[0], size[1]);
        sprite.setOriginCenter();
    }


    @Override
    public void update(float delta) {
        move(velocity.x, velocity.y, delta);
        checkBound();
        if (getTimer() > 7) {
            velocity.y *= -1;
            setTimer(0);
        }
        setTimer(getTimer() + 1);
    }

    @Override
    public void checkBound() {
        if (sprite.getX() < 0) {
            setX(0);
            velocity.x *= -1;
            sprite.flip(true, false);
        } else if (sprite.getX() > JeddoJump.GAME_W) {
            setX(JeddoJump.GAME_W);
            velocity.x *= -1;
            sprite.flip(true, false);
        }
    }

    public enum State {
        END, MOVE
    }

}
