package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Assets;
import com.mygdx.game.JeddoJump;
import com.mygdx.game.command.Jump;
import com.mygdx.game.command.Smash;
import com.mygdx.game.world.World;

import static com.badlogic.gdx.Gdx.input;

public class Hero extends MovingEntity {
    private static final int[] size = new int[]{50, 50};
    private static final float BASE_VX = 250;
    private static final Vector2 momentum = new Vector2(100, 0);
    private final Smash smash = new Smash(this, 0.3f);
    private final Jump jump = new Jump(this, 0);


    public Hero(Sprite sprite) {
        super(sprite, "HERO");
        velocity.set(BASE_VX, 0);
        state = State.INITIAL;
        sprite.setSize(size[0], size[1]);
        sprite.setOriginCenter();
        System.out.println(getY());

    }

    @Override
    public void update(float delta) {
        checkInput(delta);
        checkState();
        applyForce(delta);
        checkBound();
    }

    //jump.withSpeed(Math.max(350, velocity.y * -1)).execute();
    public void jump(float amount) {
        jump.withSpeed(amount).execute();
        state = State.ASCE;
    }

    public void smash(float amount) {
        if (state == State.ASCE) {
            smash.withSpeed(amount).execute();
            state = State.DESC;
        }
    }

    public void hitNormal() {
        if (state != State.END) {
            state = State.HIT;
            velocity.x = 0;
            sprite.setTexture(Assets.manager.get(Assets.T006));
        }
    }

    private void checkInput(float delta) {
        if (input.isKeyPressed(Input.Keys.SPACE))
            smash(600);
        if (input.isKeyPressed(Input.Keys.A)) {
            move(velocity.x * -1, 0, delta);
            if (momentum.x >= 0) momentum.x = -100;
        } else if (input.isKeyPressed(Input.Keys.D)) {
            move(velocity.x, 0, delta);
            if (momentum.x <= 0) momentum.x = 100;
        }
    }

    private void checkState() {
        if (state == State.INITIAL) {
            jump(400);
            smash.cooldown();
        }
        if (state == State.HIT) {
            setTimer(getTimer() + Gdx.graphics.getDeltaTime());
            velocity.y -= 100 * Gdx.graphics.getDeltaTime();
        }

        if (getTimer() >= 2) {
            state = State.END;
            setTimer(0);
        }
        if (velocity.y >= 0 && state != State.HIT && state != State.END) {
            if (state != State.ASCE) {
                state = State.ASCE;
            }
        }
        if (velocity.y < 0 && state != State.HIT && state != State.END) {
            if (state != State.DESC) {
                state = State.DESC;
            }
        }
    }

    private void applyForce(float delta) {
        velocity.y -= World.GRAVITY;
        if (momentum.x > 0) {
            momentum.x--;
        } else if (momentum.x < 0) {
            momentum.x++;
        }
        move(momentum.x, momentum.y, delta);
        move(0, velocity.y, delta);
    }

    @Override
    public void checkBound() {
        float width = getSprite().getWidth();
        if (getX() + width < 0)
            setX(JeddoJump.GAME_W);
        if (getX() > JeddoJump.GAME_W)
            setX(-width);
    }

    public enum State {
        END, INITIAL, ASCE, DESC, HIT,
    }
}
