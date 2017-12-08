package com.mygdx.game.command;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.mygdx.game.entity.MovingEntity;

public class Smash extends Ability {
    private final MovingEntity entity;
    private float speed = 0;

    public Smash(MovingEntity entity, float cooldown) {
        super(cooldown);
        this.entity = entity;
    }

    @Override
    public void execute() {
        if (ready) {
            entity.getVelocity().y = speed * -1;
            cooldown();
        }
    }

    public Ability withSpeed(float speed) {
        this.speed = speed;
        return this;
    }
}
