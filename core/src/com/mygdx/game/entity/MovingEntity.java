package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.JeddoJump;

public abstract class MovingEntity extends Entity {
    Vector2 velocity = new Vector2(0, 0);
    float rotation = 10;


    MovingEntity(Sprite sprite, String type) {
        super(sprite, type);
    }

    public void move(float x, float y, float delta) {
        sprite.translate(x * delta, y * delta);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getRotation() {
        return rotation;
    }

    public void checkBound() {
        if (sprite.getX() < 0) {
            setX(0);
            velocity.x *= -1;
        } else if (sprite.getX() + sprite.getWidth() > JeddoJump.GAME_W) {
            setX(JeddoJump.GAME_W - sprite.getWidth());
            velocity.x *= -1;
        }
    }
}
