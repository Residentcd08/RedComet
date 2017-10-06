package com.rocketdemo.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rocketdemo.game.RocketDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = RocketDemo.WIDTH;        //setting width, heigh and title for desktop window
		config.height = RocketDemo.HEIGHT;
		config.title = RocketDemo.TITLE;

		new LwjglApplication(new RocketDemo(), config);
	}
}
