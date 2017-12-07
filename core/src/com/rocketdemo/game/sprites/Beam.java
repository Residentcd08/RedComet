package com.rocketdemo.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by _7208 on 12.11.2017.
 */

public class Beam {

    public static final int BeamHeight = 52;

    public static final int Wave = 148;
    public static final int Beam_Space = 100;
    public static final int Lowest_Space = 70;

    private Texture leftBeam, rightBeam;
    private Vector2 gpsLeftBeam, gpsRightBeam;
    private Random random;
    private Rectangle collisionLeft,collisionRight;         //collisions made with invisible rectangles

    public Beam (float y) {

        leftBeam = new Texture("BeamLeft.fw.png");          //setting textures
        rightBeam = new Texture("BeamnRight.fw.png");
        random = new Random ();

        gpsLeftBeam = new Vector2(random.nextInt(Wave)-(Wave)-(Wave/2), y);     //position for left beam
        gpsRightBeam = new Vector2(gpsLeftBeam.x + leftBeam.getWidth() + Lowest_Space, y);//position for right beam

        collisionLeft = new Rectangle(gpsLeftBeam.x, gpsLeftBeam.y, leftBeam.getWidth(),leftBeam.getHeight());
        collisionRight = new Rectangle(gpsRightBeam.x, gpsRightBeam.y, rightBeam.getWidth(),rightBeam.getHeight()); //collision for beams
    }


    public void moveBeam (float y){
        gpsLeftBeam.set(random.nextInt(Wave)-(Wave)-(Wave/2), y);
        gpsRightBeam.set(gpsLeftBeam.x + leftBeam.getWidth() + Lowest_Space, y);

        collisionLeft.setPosition(gpsLeftBeam.x, gpsLeftBeam.y);
        collisionRight.setPosition(gpsRightBeam.x, gpsRightBeam.y);
    }

    public boolean collision(Rectangle comet){
        return  comet.overlaps(collisionLeft) || comet.overlaps(collisionRight);
    }           //collision check



    public Texture getLeftBeam() {
        return leftBeam;
    }

    public Texture getRightBeam() {
        return rightBeam;
    }

    public Vector2 getGpsLeftBeam() {
        return gpsLeftBeam;
    }

    public Vector2 getGpsRightBeam() {
        return gpsRightBeam;
    }
}
