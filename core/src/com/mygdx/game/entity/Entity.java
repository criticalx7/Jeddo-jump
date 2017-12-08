package com.mygdx.game.entity;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
    Sprite sprite;
    Enum state;
    private String type;
    private float timer;

    Entity(Sprite sprite, String type) {
        this.sprite = sprite;
        this.type = type;
    }

    public abstract void update(float delta);

    public String getType() {
        return type;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getY() {
        return sprite.getY();
    }

    public void setY(float value) {
        sprite.setY(value);
    }

    public float getX() {
        return sprite.getX();
    }

    public void setX(float value) {
        sprite.setX(value);
    }

    public Rectangle getRect() {
        return sprite.getBoundingRectangle();
    }

    public Enum getState() {
        return state;
    }

    public void setState(Enum e) {
        this.state = e;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }
}
