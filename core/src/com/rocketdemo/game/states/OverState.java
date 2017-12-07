package com.rocketdemo.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.rocketdemo.game.RocketDemo;

/**
 * Created by _7208 on 05.10.2017.
 */

public class OverState extends State {

    private Texture background;
    private  Texture TryAgainBtn;
    private Texture GameOverLogo;
    private Texture ExitBtn;
    private Sound click;



    public OverState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, RocketDemo.WIDTH / 2, RocketDemo.HEIGHT / 2);
        background = new Texture("background.fw.png");
        TryAgainBtn = new Texture("GameOverBtn.fw.png");
        GameOverLogo = new Texture("GameOver.fw.png");
        ExitBtn = new Texture("MainMenuBtn.fw.png");
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
    }

    @Override
    protected void HandleInput() {
        if((Gdx.input.justTouched())
                && ((Gdx.input.getX() >= 105))
                && ((Gdx.input.getX() <= 375))
                && ((Gdx.input.getY() >= 550))
                && ((Gdx.input.getY() <= 600))){
            gsm.set (new PlayState(gsm));
            click.play();
        }
        if((Gdx.input.justTouched())
                && ((Gdx.input.getX() >= 105))
                && ((Gdx.input.getX() <= 375))
                && ((Gdx.input.getY() >= 610))
                && ((Gdx.input.getY() <= 660))){
            gsm.set (new MainMenuState(gsm));
            click.play();
        }

    }

    @Override
    public void update(float dt) {
        HandleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(GameOverLogo, camera.position.x - GameOverLogo.getWidth()/4, camera.position.y, 188/2, 123/2);
        sb.draw(TryAgainBtn, camera.position.x - TryAgainBtn.getWidth()/4, camera.position.y/2, 270/2, 50/2);
        sb.draw(ExitBtn, camera.position.x - ExitBtn.getWidth()/4, camera.position.y/2 - 30, 270/2, 50/2);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        TryAgainBtn.dispose();

    }
}
