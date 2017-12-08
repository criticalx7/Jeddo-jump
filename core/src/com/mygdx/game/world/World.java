package com.mygdx.game.world;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Assets;
import com.mygdx.game.JeddoJump;
import com.mygdx.game.entity.*;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.system.Player;

import java.util.ArrayList;

import static com.mygdx.game.JeddoJump.GAME_W;

public class World {
    public static float GRAVITY = 7;
    private static final int CUBE_COMBO = 8;
    private final JeddoJump game;
    private final ArrayList<Entity> platforms;
    private final ArrayList<Entity> cubes;
    private final ArrayList<Entity> enemies;
    private final EntityGenerator generator;
    private final ScoreCalculator calculator;
    private final Hero hero;
    private float overallHeight = 0;


    public World(JeddoJump game) {
        this.game = game;
        hero = createHero();
        platforms = new ArrayList<>();
        cubes = new ArrayList<>();
        enemies = new ArrayList<>();

        generator = new EntityGenerator(this);
        calculator = new ScoreCalculator();
        platforms.add(generator.generateSinglePlatform(hero.getX(), hero.getY() + 100));
        platforms.addAll(generator.generatePlatform(10));
    }

    private Hero createHero() {
        Texture texture = Assets.manager.get(Assets.T001);
        Sprite sprite = new Sprite(texture);
        Hero hero = new Hero(sprite);
        hero.getSprite().setCenter(GAME_W / 2, 0);
        return new Hero(sprite);
    }

    public void run(float delta) {
        delta = Math.min(delta, 0.16f);
        //Ability.cooldownAll(delta);
        generatePlatform(5);
        Entity e = generator.generateEnemy();
        if (e != null) enemies.add(e);
        updateAll(delta);
        checkCollisions();
        if (hero.getState() == Hero.State.END) {
            if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
                game.setScreen(new MainMenuScreen(game));
        } else if (hero.getState() != Hero.State.HIT) {
            checkGameOver();
        }
    }

    private void generatePlatform(int amount) {
        if (overallHeight > generator.getGeneratePoint()) {
            platforms.addAll(generator.generatePlatform(amount));
        }
    }

    private void updateAll(float delta) {
        updateHero(delta);
        updateEnemy(delta);
        updatePlatform(delta);
        updateCube(delta);
    }

    private void updateHero(float delta) {
        hero.update(delta);
        overallHeight = Math.max(overallHeight, hero.getY());
        calculator.calculate(overallHeight);
    }

    private void updatePlatform(float delta) {
        for (int i = platforms.size() - 1; i >= 0; i--) {
            Entity platform = platforms.get(i);
            platform.update(delta);
            if (platform.getState() == Platform.State.END || platform.getY() < hero.getY() - 250) {
                platforms.remove(i);
            }
        }
    }

    private void updateEnemy(float delta) {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Entity e = enemies.get(i);
            e.update(delta);
            if (e.getState() == DragonFly.State.END) {
                cubes.remove(i);
            }
        }
    }

    private void updateCube(float delta) {
        for (int i = cubes.size() - 1; i >= 0; i--) {
            Entity cube = cubes.get(i);
            cube.update(delta);
            if (cube.getState() == Cube.State.END) {
                cubes.remove(i);
                game.getPlayer().setCube(game.getPlayer().getCube() + 1);
            }
        }
    }

    private void checkCollisions() {
        checkPlatformCollision();
        checkEnemyCollision();
        checkCubeCollision();
    }

    private void checkPlatformCollision() {
        for (int i = platforms.size() - 1; i >= 0; i--) {
            Entity platform = platforms.get(i);
            Rectangle rHero = hero.getRect();
            Rectangle rPlat = platform.getRect();
            if (hero.getState() == Hero.State.DESC) {
                if (rHero.overlaps(rPlat)) {
                    if (rHero.y + (rHero.height / 2) > rPlat.y + (rPlat.height / 2)) {
                        if (hero.getVelocity().y <= -500 && hero.getState() != Hero.State.HIT) {
                            hero.jump(500);
                            calculator.addMultiplier(0.05);
                            cubes.addAll(generator.generateCube(MathUtils.random(1, 2), hero));
                            platform.setState(Platform.State.END);
                        } else {
                            hero.jump(350);
                            calculator.reset();
                        }
                        break;
                    }
                }
            }
        }
    }

    private void checkEnemyCollision() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Entity e = enemies.get(i);
            Rectangle rHero = hero.getRect();
            Rectangle rEner = e.getRect();
            if (rHero.overlaps(rEner)) {
                if (e.getType().equals("BLACKHOLE") && e.getState() != Blackhole.State.SUCK) {
                    Blackhole hole = (Blackhole) e;
                    hole.suck(hero);
                } else {
                    hero.hitNormal();

                }
            }
        }
    }

    private void checkCubeCollision() {
        cubes.stream()
                .filter(c -> c.getRect().overlaps(hero.getRect()) && c.getState() != Cube.State.INITIAL)
                .forEach(c -> c.setState(Cube.State.END));
    }

    private void checkGameOver() {
        if (overallHeight - 800 > hero.getY()) {
            hero.hitNormal();
        }
    }

    // ------------------------ ACCESSOR -------------------------


    Hero getHero() {
        return hero;
    }

    ArrayList<Entity> getPlatforms() {
        return platforms;
    }

    ArrayList<Entity> getCubes() {
        return cubes;
    }

    ArrayList<Entity> getEnemies() {
        return enemies;
    }

    ScoreCalculator getCalculator() {
        return calculator;
    }

    Player getPlayer() {
        return game.getPlayer();
    }
}
