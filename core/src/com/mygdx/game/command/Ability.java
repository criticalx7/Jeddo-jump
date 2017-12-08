package com.mygdx.game.command;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.utils.Timer;

import java.util.HashMap;

public abstract class Ability {
    boolean ready;
    private float cooldown;
    private Timer timer = new Timer();


    Ability(float cooldown) {
        this.cooldown = cooldown;
        ready = true;
    }

    public abstract void execute();

    public void cooldown() {
        ready = false;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ready = true;
            }
        }, cooldown);
    }

    public boolean isReady() {
        return ready;
    }
}
