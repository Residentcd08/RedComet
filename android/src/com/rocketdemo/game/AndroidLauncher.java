package com.rocketdemo.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.rocketdemo.game.RocketDemo;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.useCompass = false;         // turning off compass and accelerometer for battery usage optimisation
		config.useAccelerometer = false;

		initialize(new RocketDemo(), config);
	}
}
