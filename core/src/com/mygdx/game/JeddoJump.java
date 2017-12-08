package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.system.Player;


public class JeddoJump extends Game {
    public static final int GAME_W = 480;
    public static final int GAME_H = 640;
    private SpriteBatch batch;
    private static Assets assets;
    private static Player player;

    @Override
    public void create() {
        player = new Player("JEDDO");
        batch = new SpriteBatch();
        assets = new Assets();
        assets.load();
        assets.await();
        setScreen(new MainMenuScreen(this));
    }


    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        assets.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Player getPlayer() {
        return player;
    }
}
