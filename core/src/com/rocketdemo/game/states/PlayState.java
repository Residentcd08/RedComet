package com.rocketdemo.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.rocketdemo.game.RocketDemo;
import com.rocketdemo.game.sprites.Beam;
import com.rocketdemo.game.sprites.Spaceship;

/**
 * Created by _7208 on 05.10.2017.
 */

public class PlayState extends State {
    public static final int beamGap = 125;
    public static final int beamFalls = 4;

    private Spaceship spaceship;
    private Texture background;

    private Array<Beam> beams;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        spaceship = new Spaceship(105, 60);
        camera.setToOrtho(false, RocketDemo.WIDTH / 2, RocketDemo.HEIGHT / 2);
        background = new Texture("background.fw.png");

        beams = new Array<Beam>();

        for (int i = 0; i < beamFalls; i++){
            beams.add(new Beam(i * (beamGap + Beam.BeamHeight)));

        }
    }

    @Override
    protected void HandleInput() {
        if ((Gdx.input.justTouched()) && (Gdx.input.getX() <= 240))
            spaceship.thrust_right();
        if ((Gdx.input.justTouched()) && (Gdx.input.getX() > 241))
            spaceship.thrust_left();

    }

    @Override
    public void update(float dt) {
        HandleInput();
        spaceship.update(dt);
        camera.position.y = spaceship.getPosition().y + 100;

        for (Beam beam : beams) {
            if (camera.position.y - (camera.viewportHeight / 2) > (beam.getGpsLeftBeam().y + beam.getLeftBeam().getWidth())){
               beam.moveBeam(beam.getGpsLeftBeam().y + ((Beam.BeamHeight + beamGap)*beamFalls));
               System.out.println(beam.getGpsLeftBeam().y + beam.getLeftBeam().getWidth());
            }

            if (beam.collision(spaceship.getCollision()))
                gsm.set(new PlayState(gsm));
        }
        camera.update();


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
//        sb.draw(background,0,0, RocketDemo.WIDTH/2, RocketDemo.HEIGHT/2);
        sb.draw(background, 0, camera.position.y - (camera.viewportHeight / 2));
        sb.draw(spaceship.getSpaceship(), spaceship.getPosition().x, spaceship.getPosition().y, 30,40);
        for (Beam beam : beams) {
            sb.draw(beam.getLeftBeam(), beam.getGpsLeftBeam().x, beam.getGpsLeftBeam().y);
            System.out.println(beam.getGpsLeftBeam().x);
            sb.draw(beam.getRightBeam(), beam.getGpsRightBeam().x, beam.getGpsRightBeam().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
