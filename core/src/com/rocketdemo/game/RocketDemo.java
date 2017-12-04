package com.rocketdemo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketdemo.game.states.GameStateManager;
import com.rocketdemo.game.states.MainMenuState;

public class RocketDemo extends ApplicationAdapter {
	private GameStateManager gsm;
	private SpriteBatch batch;
	Texture img;
	public static final int HEIGHT = 800;
	public static final int WIDTH = 480;

	public static final String TITLE = "RED COMET";

	private Music backgroundMusic;



	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MainMenuState(gsm));
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(0.2f);
		backgroundMusic.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		backgroundMusic.dispose();
	}
}
