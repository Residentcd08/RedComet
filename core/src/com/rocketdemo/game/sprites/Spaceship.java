package com.rocketdemo.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by _7208 on 05.10.2017.
 */

public class Spaceship {
    public int gravity = 1;
    private Vector3 position;
    private Vector3 velocity;

    private Texture spaceship;


    public Spaceship(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        spaceship = new Texture("ship_1.png");
    }

    public void update(float dt){
        velocity.add (0, gravity, 0);
        velocity.scl (dt);

        position.add(0, velocity.y, 0);
        velocity.scl(1 / dt);

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getSpaceship() {
        return spaceship;
    }
}