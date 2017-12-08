package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.JeddoJump;

public class MainMenuScreen extends ScreenAdapter {

    private final JeddoJump game;
    private final OrthographicCamera cam;


    public MainMenuScreen(JeddoJump game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, JeddoJump.GAME_W, JeddoJump.GAME_H);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        game.getBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) game.setScreen(new GameScreen(game));
    }


}
