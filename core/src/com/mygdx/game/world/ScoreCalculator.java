package com.mygdx.game.world;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.utils.Timer;

import static com.badlogic.gdx.utils.Timer.Task;

class ScoreCalculator {
    private float prevHeight = 0;
    private double score = 0;
    private double multiplier = 1.0;
    private int combo = 0;
    private Timer mulTimer = new Timer();

    void calculate(float overallHeight) {
        double diff = 0;
        if (overallHeight > prevHeight) {
            diff = overallHeight - prevHeight;
            prevHeight = overallHeight;
        }
        score += diff * multiplier;
    }


    void addMultiplier(double value) {
        multiplier += value;
        combo++;
        mulTimer.clear();
        mulTimer.scheduleTask(new Task() {
            @Override
            public void run() {
                reset();
            }
        }, 3);
    }

    void reset() {
        multiplier = 1.0;
        combo = 0;
    }

    double getScore() {
        return score;
    }

    double getMultiplier() {
        return multiplier;
    }

    int getCombo() {
        return combo;
    }
}
