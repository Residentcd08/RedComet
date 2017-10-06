package com.rocketdemo.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketdemo.game.RocketDemo;

/**
 * Created by _7208 on 05.10.2017.
 */

public class MainMenuState extends State {

    private Texture background;
    private  Texture startbutton;



    public MainMenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.fw.png");
        startbutton = new Texture("Start_Game_Green.fw.png");

    }

    @Override
    protected void HandleInput() {
        if(Gdx.input.justTouched()){
            gsm.set (new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        HandleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, RocketDemo.WIDTH, RocketDemo.HEIGHT);
        sb.draw(startbutton, (RocketDemo.WIDTH / 2) - (startbutton.getWidth() / 2), RocketDemo.HEIGHT / 2);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        startbutton.dispose();

    }
}
