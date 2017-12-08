package com.mygdx.game.screens;


import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.game.JeddoJump;
import com.mygdx.game.world.World;
import com.mygdx.game.world.WorldRenderer;


public class GameScreen extends ScreenAdapter {
    private World world;
    private WorldRenderer renderer;


    public GameScreen(JeddoJump game) {
        world = new World(game);
        renderer = new WorldRenderer(world, game.getBatch());
    }


    @Override
    public void render(float delta) {
        world.run(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }


}
