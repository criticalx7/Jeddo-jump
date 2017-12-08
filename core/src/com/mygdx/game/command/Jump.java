package com.mygdx.game.command;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */


import com.mygdx.game.entity.MovingEntity;

public class Jump extends Ability {
    private final MovingEntity entity;
    private float speed = 400;

    public Jump(MovingEntity entity, float cooldown) {
        super(cooldown);
        this.entity = entity;
    }

    @Override
    public void execute() {
        if (ready) entity.getVelocity().y = speed;
    }

    public Ability withSpeed(float speed) {
        this.speed = Math.abs(speed);
        return this;
    }
}
