package com.mygdx.game.system;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

public class Player {
    private String name;
    private int highScore = 0;
    private int cube = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getCube() {
        return cube;
    }

    public void setCube(int cube) {
        this.cube = cube;
    }
}
