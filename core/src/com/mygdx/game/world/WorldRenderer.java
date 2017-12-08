package com.mygdx.game.world;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Assets;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Hero;

import static com.mygdx.game.JeddoJump.GAME_H;
import static com.mygdx.game.JeddoJump.GAME_W;

public class WorldRenderer {
    private final World world;
    private final Batch batch;
    private final ShapeRenderer shapeDebugger;
    private final OrthographicCamera cam;
    private final SimpleUI ui;
    private Sprite bg = new Sprite(Assets.manager.get(Assets.BG01));
    private int camType = 1;

    public WorldRenderer(World world, Batch batch) {
        this.world = world;
        this.batch = batch;
        shapeDebugger = new ShapeRenderer();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, GAME_W, GAME_H);
        ui = new SimpleUI(world, cam);
        bg.setSize(GAME_W, GAME_H);
        bg.setOriginCenter();

    }


    public void render() {
        updateCam();
        clean();
        draw();
    }

    //24047172 - libgdx-camera-smooth-translation
    private void updateCam() {
        Sprite sHero = world.getHero().getSprite();
        Vector3 target;
        float fiction;
        if (camType == 0) {
            target = new Vector3(sHero.getX(), sHero.getY() + sHero.getHeight() / 2 + 150, 0);
            fiction = 0.1f;
        } else {
            target = new Vector3(sHero.getX(), sHero.getY() + sHero.getHeight() / 2 + 200, 0);
            fiction = Gdx.graphics.getDeltaTime();
        }

        final float interpolation = 1.0f - fiction;

        Vector3 cameraPosition = cam.position; // store camera previous position
        cameraPosition.scl(interpolation);     // scale it by interpolation speed
        target.scl(fiction);                   // scale cam target by actual speed
        cameraPosition.add(target);            // add them together
        cam.position.set(cameraPosition);
        cam.update();
        bg.setPosition(cam.position.x - GAME_W / 2, cam.position.y - GAME_H / 2);
    }

//    private void updateCam() {
//        Sprite h = world.getHero().getSprite();
//        cam.position.set(GAME_W / 2, h.getY() + h.getHeight()/2 +150, 0);
//        cam.update();
//    }

    private void clean() {
        // Clear the bg with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Update batch matrix projection
        batch.setProjectionMatrix(cam.combined);
    }

    private void draw() {
        drawBackground();
        drawObject();
        ui.drawAll(batch );
        drawGround();
    }

    private void drawBackground() {
        batch.disableBlending();
        batch.begin();
        bg.draw(batch);
        batch.end();

    }

    private void drawObject() {
        batch.enableBlending();
        batch.begin();
        world.getCubes().stream().map(Entity::getSprite).forEach(s -> s.draw(batch));
        world.getEnemies().stream().map(Entity::getSprite).forEach(s -> s.draw(batch));
        world.getHero().getSprite().draw(batch);
        world.getPlatforms().stream().map(Entity::getSprite).forEach(s -> s.draw(batch));
        batch.end();
    }


    private void drawGround() {
        // SOME TEMPORARY VISUAL DEBUG
        shapeDebugger.setProjectionMatrix(cam.combined);
        shapeDebugger.begin(ShapeRenderer.ShapeType.Line);
        shapeDebugger.setColor(0, 1, 0, 1);
        shapeDebugger.line(0, 20, GAME_W, 20);
        shapeDebugger.end();
    }


    public void dispose() {
        shapeDebugger.dispose();
    }

}
