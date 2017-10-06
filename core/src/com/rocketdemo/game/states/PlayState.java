package com.rocketdemo.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketdemo.game.RocketDemo;
import com.rocketdemo.game.sprites.Spaceship;

/**
 * Created by _7208 on 05.10.2017.
 */

public class PlayState extends State {
    private Spaceship spaceship;
    private Texture background;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        spaceship = new Spaceship(92, 20);
        camera.setToOrtho(false, RocketDemo.WIDTH / 2, RocketDemo.HEIGHT / 2);
        background = new Texture("background.fw.png");
    }

    @Override
    protected void HandleInput() {

    }

    @Override
    public void update(float dt) {
        spaceship.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth /2), 0);
        sb.draw(spaceship.getSpaceship(), spaceship.getPosition().x, spaceship.getPosition().y, 50,50);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
