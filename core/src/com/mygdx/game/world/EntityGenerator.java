package com.mygdx.game.world;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Assets;
import com.mygdx.game.JeddoJump;
import com.mygdx.game.entity.Cube;
import com.mygdx.game.entity.DragonFly;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Platform;

import java.util.ArrayList;

class EntityGenerator {
    private World world;
    private float generatePoint = 0;
    private int spawnHeight = 0;
    private boolean spawn = true;

    EntityGenerator(World world) {
        this.world = world;
    }

    Entity generateEnemy() {
        Entity entity = null;
        int random = MathUtils.random(100);
        if (random > 90 && spawn && generatePoint > 5000) {
            Sprite s = new Sprite(Assets.manager.get(Assets.E001));
            entity = new DragonFly(s);
            int x = MathUtils.random(20, JeddoJump.GAME_W - 20);
            int y = MathUtils.random(spawnHeight + 100, spawnHeight + 200);
            entity.setX(x);
            entity.setY(y);
            spawn = false;
            Timer.instance().scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    spawn = true;
                }
            }, 2);
        }
        return entity;
    }


    ArrayList<Entity> generatePlatform(int amount) {
        final ArrayList<Entity> entities = new ArrayList<>();
        //float width = Math.max(TRUE_WIDTH / 3, TRUE_WIDTH - (world.getOverallHeight() / 100));

        int random = MathUtils.random(100);
        if (random > 90) {
            generateTowerSeries(entities);
        } else {
            generateNormalSeries(amount, entities);
        }

        System.out.printf("next generatePlatform: %s%n", generatePoint);
        System.out.printf("next height: %d%n", spawnHeight);
        System.out.printf("size: %d%n%n", world.getPlatforms().size() + world.getCubes().size());

        return entities;
    }


    private Platform createStaticPlatform() {
        final Texture texture = Assets.manager.get(Assets.P001);
        Sprite s = new Sprite(texture);
        return new Platform(s);
    }

    private Platform createMovingPlatform(float sx, float sy) {
        final Texture texture = Assets.manager.get(Assets.P002);
        Sprite s = new Sprite(texture);
        Platform platform = new Platform(s);
        platform.getVelocity().set(sx, sy);
        return platform;
    }

    private void generateNormalSeries(int amount, ArrayList<Entity> source) {
        for (int i = 0; i < amount; i++) {
            int x = MathUtils.random(20, JeddoJump.GAME_W - 20);
            int y = MathUtils.random(spawnHeight + 100, spawnHeight + 200);
            Platform platform;

            if (generatePoint >= 2000 && MathUtils.random(100) > 70) {
                platform = createMovingPlatform(100, 0);
            } else {
                platform = createStaticPlatform();
            }
            platform.setX(x);
            platform.setY(y);
            source.add(platform);
            spawnHeight += 100;
        }
        generatePoint = spawnHeight - JeddoJump.GAME_W;
    }

    private void generateTowerSeries(ArrayList<Entity> source) {
        int x = MathUtils.random(20, JeddoJump.GAME_W - 20);
        for (int i = 0; i < 5; i++) {
            Platform platform = createStaticPlatform();
            platform.setX(x);
            platform.setY(spawnHeight + 100);
            source.add(platform);
            spawnHeight += 100;
        }
        generatePoint = spawnHeight - JeddoJump.GAME_W;
    }

    public Platform generateSinglePlatform(float x, float y) {
        Platform platform = createStaticPlatform();
        platform.setX(x);
        platform.setY(y);
        return platform;
    }

    public ArrayList<Entity> generateCube(int amount, Entity target) {
        ArrayList<Entity> entities = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Texture texture = MathUtils.random() > 0.8 ? Assets.manager.get(Assets.C002) : Assets.manager.get(Assets.C001);
            Sprite sprite = new Sprite(texture);
            Cube cube = new Cube(sprite, target);

            cube.setX(target.getX());
            cube.setY(target.getY() - 20);
            cube.getVelocity().set(MathUtils.random(-amount * 50, amount * 50), -MathUtils.random(-100, 100));
            entities.add(cube);
        }
        return entities;
    }

    float getGeneratePoint() {
        return generatePoint;
    }


}
