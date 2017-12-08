package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.JeddoJump;

public class Platform extends MovingEntity {
    private static final int[] size = new int[]{100, 20};
    public static final String TYPE = "PLATFORM";

    public Platform(Sprite sprite) {
        super(sprite, TYPE);
        state = State.INITIAL;
        sprite.setSize(size[0], size[1]);
        sprite.setOriginCenter();
    }

    @Override
    public void update(float delta) {
        move(velocity.x, velocity.y, delta);
        checkBound();
    }


    public enum State {
        END, INITIAL
    }
}
