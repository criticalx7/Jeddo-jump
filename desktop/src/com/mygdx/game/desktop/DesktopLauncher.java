package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.JeddoJump;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Jeddo Smash";
		config.width = 480;
		config.height = 640;
		config.y = 20;
		config.foregroundFPS = 60;

		new LwjglApplication(new JeddoJump(), config);
	}
}
