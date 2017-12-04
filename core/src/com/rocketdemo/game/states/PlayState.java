package com.rocketdemo.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.rocketdemo.game.RocketDemo;
import com.rocketdemo.game.sprites.Beam;
import com.rocketdemo.game.sprites.Spaceship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by _7208 on 05.10.2017.
 */

public class PlayState extends State {
    public static final int beamGap = 125;
    public static final int beamFalls = 4;

    private Spaceship spaceship;
    private Texture background;
    private Texture pauseBg;
    private Texture pause;

    private Texture score1;
    private Texture score2;
    private Texture score3;
    private Texture score4;
    private Texture score5;
    private Texture scoreBg;

    private int Score = 0;
    private static boolean switchState = false;
    private float TimeDiff = 0;

    private Music rocketSound;
    private Sound explosion;
    private Sound click;
    private Sound beep;

    private Array<Beam> beams;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        spaceship = new Spaceship(105, 60);
        camera.setToOrtho(false, RocketDemo.WIDTH / 2, RocketDemo.HEIGHT / 2);
        background = new Texture("background.fw.png");
        pauseBg = new Texture("pauseBg.fw.png");
        pause = new Texture("pause.fw.png");

        score1 = new Texture("0.fw.png");
        score2 = new Texture("0.fw.png");
        score3 = new Texture("0.fw.png");
        score4 = new Texture("0.fw.png");
        score5 = new Texture("0.fw.png");

        scoreBg = new Texture("scoreBg.fw.png");

        beams = new Array<Beam>();
        for (int i = 0; i < beamFalls; i++){
            beams.add(new Beam(i * (beamGap + Beam.BeamHeight)));

        }

        rocketSound = Gdx.audio.newMusic(Gdx.files.internal("rocketSound.mp3"));
        rocketSound.setLooping(true);
        rocketSound.setVolume(0.1f);
        rocketSound.play();

        explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
        beep = Gdx.audio.newSound(Gdx.files.internal("beep.ogg"));

    }

    @Override
    protected void HandleInput() {
        if ((Gdx.input.justTouched()) && (Gdx.input.getX() <= 240) && (Gdx.input.getY() >= 100) && !switchState)
            spaceship.thrust_right();
        if ((Gdx.input.justTouched()) && (Gdx.input.getX() > 241) && (Gdx.input.getY() >= 100) && !switchState)
            spaceship.thrust_left();                        //Ship control

        if((Gdx.input.justTouched())                        //pause button
                && ((Gdx.input.getX() >= 30))
                && ((Gdx.input.getX() <= 70))
                && ((Gdx.input.getY() >= 20))
                && ((Gdx.input.getY() <= 60))){
            switchState = true;
            click.play();
            rocketSound.stop();
        }
        if((Gdx.input.justTouched() && switchState)                        //resume button
                && ((Gdx.input.getX() >= 105))
                && ((Gdx.input.getX() <= 380))
                && ((Gdx.input.getY() >= 500))
                && ((Gdx.input.getY() <= 550))){
            switchState = false;
            click.play();
            rocketSound.play();
        }
        System.out.println("x = " + Gdx.input.getX());
        System.out.println("y = " + Gdx.input.getY());
    }

    @Override
    public void update(float dt) {
        HandleInput();
        if (!switchState){
        spaceship.update(dt);
        camera.position.y = spaceship.getPosition().y + 100;

        for (Beam beam : beams) {
            if (camera.position.y - (camera.viewportHeight / 2) > (beam.getGpsLeftBeam().y + beam.getLeftBeam().getWidth())){
               beam.moveBeam(beam.getGpsLeftBeam().y + ((Beam.BeamHeight + beamGap)*beamFalls));
            }

            if (beam.collision(spaceship.getCollision())){
                rocketSound.stop();
                gsm.set(new OverState(gsm));
                explosion.play();}           // Game Over Screen


            TimeDiff += dt;                             // Score Counter
            if (TimeDiff > 9){ //more score (default: 9)
                TimeDiff = 0;
                Score++;
                beep.play();
            }

            System.out.println(Score + " " + TimeDiff + " " + ScoreNumber(Score).get(0)); //temp
        }

            if (ScoreNumber(Score).size() >= 1) {           //Check for score number

                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 1) == 1) {
                    score5 = new Texture("1.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 1) == 2) {
                    score5 = new Texture("2.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -1) == 3) {
                    score5 = new Texture("3.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -1) == 4) {
                    score5 = new Texture("4.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -1) == 5) {
                    score5 = new Texture("5.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -1) == 6) {
                    score5 = new Texture("6.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -1) == 7) {
                    score5 = new Texture("7.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -1) == 8) {
                    score5 = new Texture("8.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -1) == 9) {
                    score5 = new Texture("9.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -1) == 0) {
                    score5 = new Texture("0.fw.png");
                }
            }


            if (ScoreNumber(Score).size() >= 2) {

                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 2) == 1) {
                    score4 = new Texture("1.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 2) == 2) {
                    score4 = new Texture("2.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -2) == 3) {
                    score4 = new Texture("3.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -2) == 4) {
                    score4 = new Texture("4.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -2) == 5) {
                    score4 = new Texture("5.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -2) == 6) {
                    score4 = new Texture("6.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -2) == 7) {
                    score4 = new Texture("7.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -2) == 8) {
                    score4 = new Texture("8.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -2) == 9) {
                    score4 = new Texture("9.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -2) == 0) {
                    score4 = new Texture("0.fw.png");
                }
            }

            if (ScoreNumber(Score).size() >= 3) {

                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 3) == 1) {
                    score3 = new Texture("1.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 3) == 2) {
                    score3 = new Texture("2.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -3) == 3) {
                    score3 = new Texture("3.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -3) == 4) {
                    score3 = new Texture("4.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -3) == 5) {
                    score3 = new Texture("5.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -3) == 6) {
                    score3 = new Texture("6.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -3) == 7) {
                    score3 = new Texture("7.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -3) == 8) {
                    score3 = new Texture("8.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -3) == 9) {
                    score3 = new Texture("9.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -3) == 0) {
                    score3 = new Texture("0.fw.png");
                }
            }

            if (ScoreNumber(Score).size() >= 4) {

                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 4) == 1) {
                    score2 = new Texture("1.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 4) == 2) {
                    score2 = new Texture("2.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -4) == 3) {
                    score2 = new Texture("3.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -4) == 4) {
                    score2 = new Texture("4.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -4) == 5) {
                    score2 = new Texture("5.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -4) == 6) {
                    score2 = new Texture("6.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -4) == 7) {
                    score2 = new Texture("7.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -4) == 8) {
                    score2 = new Texture("8.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -4) == 9) {
                    score2 = new Texture("9.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -4) == 0) {
                    score2 = new Texture("0.fw.png");
                }
            }


            if (ScoreNumber(Score).size() >= 5) {

                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 5) == 1) {
                    score1 = new Texture("1.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() - 5) == 2) {
                    score1 = new Texture("2.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -5) == 3) {
                    score1 = new Texture("3.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -5) == 4) {
                    score1 = new Texture("4.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -5) == 5) {
                    score1 = new Texture("5.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -5) == 6) {
                    score1 = new Texture("6.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -5) == 7) {
                    score1 = new Texture("7.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -5) == 8) {
                    score1 = new Texture("8.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -5) == 9) {
                    score1 = new Texture("9.fw.png");
                }
                if (ScoreNumber(Score).get(ScoreNumber(Score).size() -5) == 0) {
                    score1 = new Texture("0.fw.png");
                }
            }
                                                        //end of check
                                                        //optimize!







       camera.update();} else {}



    }

    public static List<Integer> ScoreNumber(int number) {

        String n = Integer.toString(number);
        char[] charArray = n.toCharArray();
        List<Integer> cia = new ArrayList<Integer>();
        for (int i = 0;i<charArray.length; i++){
            int c = Character.getNumericValue(charArray[i]);
            cia.add(c);
        }return cia;
    }



    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        sb.draw(background, 0, camera.position.y - (camera.viewportHeight / 2));
        sb.draw(spaceship.getSpaceship(), spaceship.getPosition().x, spaceship.getPosition().y, 30,40);
        for (Beam beam : beams) {
            sb.draw(beam.getLeftBeam(), beam.getGpsLeftBeam().x, beam.getGpsLeftBeam().y);
            //System.out.println(beam.getGpsLeftBeam().x);
            sb.draw(beam.getRightBeam(), beam.getGpsRightBeam().x, beam.getGpsRightBeam().y);
        }
        sb.draw(pause, camera.position.x - 105, camera.position.y + 170, 20, 20);

        sb.draw(scoreBg, camera.position.x + 25, camera.position.y + 170, 88, 20);
        sb.draw(score1, camera.position.x +25, camera.position.y + 170, 17, 20);
        sb.draw(score2, camera.position.x +42, camera.position.y + 170, 17, 20);
        sb.draw(score3, camera.position.x +60, camera.position.y + 170, 17, 20);
        sb.draw(score4, camera.position.x +78, camera.position.y + 170, 17, 20);
        sb.draw(score5, camera.position.x +96, camera.position.y + 170, 17, 20);

        sb.end();

        if (switchState == true) {
            sb.begin();
            sb.draw(pauseBg, camera.position.x - pauseBg.getWidth()/4,camera.position.y - pauseBg.getHeight()/4, pauseBg.getWidth()/2, pauseBg.getHeight()/2);
            sb.end();
        }

    }


    @Override
    public void dispose() {

    }
}
