package com.rocketdemo.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by _7208 on 05.10.2017.
 */

public class Spaceship {
    public int gravity = 0;
    private Vector3 position;
    private Vector3 velocity;

    private Texture spaceship;


    public Spaceship(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        spaceship = new Texture("ship_1.png");
    }

    public void update(float dt){
        if ( position.x > -5 || position.x < 192)
            velocity.add (gravity, 0, 0);

        velocity.scl (dt);
        position.add(velocity.x, 0, 0);
        velocity.scl(1 / dt);

        if (position.x <-5)
            position.x = -5;
        if (position.x > 192)
            position.x = 192;
    }

    public void thrust_right(){
        velocity.x = 30;

    }

    public void thrust_left(){
        velocity.x = -30;
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

    public Texture getSpaceship() {
        return spaceship;
    }
}