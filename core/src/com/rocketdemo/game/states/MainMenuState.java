package com.rocketdemo.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketdemo.game.RocketDemo;

/**
 * Created by _7208 on 05.10.2017.
 */

public class MainMenuState extends State {

    private Texture background;
    private  Texture startbutton;
    private Texture RedCometLogo;
    private Texture AboutBtn;
    private Texture shipBg;
    private Sound click;



    public MainMenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, RocketDemo.WIDTH / 2, RocketDemo.HEIGHT / 2);
        background = new Texture("background_top.fw.png");
        startbutton = new Texture("Start_Game_Green.fw.png");
        RedCometLogo = new Texture("RedCometLogoTemp.fw.png");
        shipBg = new Texture("shipBg.fw.png");
        AboutBtn = new Texture("About.fw.png");
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
            click.play();           //if Play button is pushed - turn on playstate
        }
        if((Gdx.input.justTouched())
                && ((Gdx.input.getX() >= 105))
                && ((Gdx.input.getX() <= 375))
                && ((Gdx.input.getY() >= 610))
                && ((Gdx.input.getY() <= 660))){
            gsm.set (new EndCreditsState(gsm));
            click.play();           //about state if button pushed
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
        sb.draw(shipBg, 0, 0, 240,400);
        sb.draw(RedCometLogo, camera.position.x - RedCometLogo.getWidth()/4, camera.position.y + 50, 188/2, 123/2);
        sb.draw(startbutton, camera.position.x - startbutton.getWidth()/4, camera.position.y/2, 270/2, 50/2);
        sb.draw(AboutBtn, camera.position.x - AboutBtn.getWidth()/4, camera.position.y/2 - 30, 270/2, 50/2);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        startbutton.dispose();
    }
}
