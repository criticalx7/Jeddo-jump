package com.mygdx.game.world;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Assets;
import com.mygdx.game.entity.Hero;

import static com.mygdx.game.JeddoJump.GAME_H;
import static com.mygdx.game.JeddoJump.GAME_W;

public class SimpleUI {
    private BitmapFont font;
    private World world;
    private OrthographicCamera cam;
    private Sprite cube = new Sprite(Assets.manager.get(Assets.C002));

    public SimpleUI(World world, OrthographicCamera cam) {
        this.world = world;
        this.cam = cam;
        this.font = font;
        cube.setSize(30, 30);
        font = Assets.manager.get(Assets.F001, BitmapFont.class);
        font.setFixedWidthGlyphs("0123456789");
    }

    private void updateCube() {
        cube.setPosition(cam.position.x - GAME_W / 2 + 20, cam.position.y + GAME_H / 2 - 90);
        cube.setOriginCenter();
        cube.rotate(1);
    }

    void drawAll(Batch batch) {
        updateCube();
        batch.begin();
        drawWorldUI(batch);
        if (world.getHero().getState() == Hero.State.END) drawDead(batch);
        batch.end();
    }

    void drawWorldUI(Batch batch) {
        font.draw(batch,
                String.format("score: %.0f", world.getCalculator().getScore()),
                cam.position.x - GAME_W / 2 + 20, cam.position.y + GAME_H / 2 - 15);
        cube.draw(batch);
        font.draw(batch,
                String.format(" %d", world.getPlayer().getCube()),
                cam.position.x - GAME_W / 2 + 60, cam.position.y + GAME_H / 2 - 65);
    }

    void drawDead(Batch batch) {
        font.draw(batch,
                "DEAD EIEI",
                cam.position.x - 70, cam.position.y + 25);
        font.draw(batch,
                "PRESS ANY KEY TO CONTINUE !!!  ",
                cam.position.x - 190, cam.position.y);
    }


    void drawVisualDebug(Batch batch) {
        Hero hero = world.getHero();
        font.draw(batch,
                String.format("X: %.2f", hero.getSprite().getX()),
                cam.position.x - GAME_W / 2, cam.position.y + GAME_H / 2 - 10);
        font.draw(batch,
                String.format("Y: %.2f", hero.getSprite().getY()),
                cam.position.x - GAME_W / 2 + 100, cam.position.y + GAME_H / 2 - 10);
        font.draw(batch,
                String.format("SX: %.0f", hero.getVelocity().x),
                cam.position.x - GAME_W / 2, cam.position.y + GAME_H / 2 - 40);
        font.draw(batch,
                String.format("SY: %.0f", hero.getVelocity().y),
                cam.position.x - GAME_W / 2 + 100, cam.position.y + GAME_H / 2 - 40);
        font.draw(batch,
                String.format("ST: %s", hero.getState()),
                cam.position.x - GAME_W / 2, cam.position.y + GAME_H / 2 - 70);
        font.draw(batch,
                String.format("Score: %.0f", world.getCalculator().getScore()),
                cam.position.x - GAME_W / 2, cam.position.y + GAME_H / 2 - 100);
        font.draw(batch,
                String.format("Multiplier: %.2f", world.getCalculator().getMultiplier()),
                cam.position.x - GAME_W / 2, cam.position.y + GAME_H / 2 - 130);
        font.draw(batch,
                String.format("Combo: %d", world.getCalculator().getCombo()),
                cam.position.x - GAME_W / 2, cam.position.y + GAME_H / 2 - 160);
    }
}
