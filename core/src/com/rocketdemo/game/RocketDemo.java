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
	public static final int HEIGHT = 800;					//screen Height
	public static final int WIDTH = 480;					//screen Width

	public static final String TITLE = "RED COMET";			//app title name

	private Music backgroundMusic;



	
	@Override
	public void create () {									//on create events
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MainMenuState(gsm));


		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
		backgroundMusic.setLooping(true);					//looping true/false
		backgroundMusic.setVolume(0.1f);					//volume 10% (default 0.1f)
		backgroundMusic.play();								//background looping music
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);			//clear screen
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {								//clear resources
		batch.dispose();
		img.dispose();
		backgroundMusic.dispose();
	}
}
