package com.rocketdemo.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by _7208 on 05.10.2017.
 */

public class Spaceship {
    public int gravity = 0;
    private Vector3 position;
    private Vector3 velocity;
    private SpaceshipAnimation spaceshipAnimation;
    private Texture texture;


    public Spaceship(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
//        spaceship = new Texture("ship_1.png");
        texture = new Texture("ShipAnimated.fw.png");
        spaceshipAnimation = new SpaceshipAnimation(new TextureRegion(texture),3,0.5f);
    }

    public void update(float dt){
        spaceshipAnimation.update(dt);
        if ( position.x > 0 || position.x < 210)
            velocity.add (gravity, 0, 0);

        velocity.scl (dt);
        position.add(velocity.x, 0, 0);
        velocity.scl(1 / dt);

        if (position.x <0)
            position.x = 0;
        if (position.x > 210)
            position.x = 210;
    }

    public void thrust_right(){
        velocity.x = 50;

    }

    public void thrust_left(){
        velocity.x = -50;
    }
    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getSpaceship() {
        return spaceshipAnimation.getFrame();
    }
}