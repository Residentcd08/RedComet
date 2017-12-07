package com.rocketdemo.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by _7208 on 05.10.2017.
 */

public class Spaceship {
    private static final int flying = 50;
    public int gravity = 0;
    public int lgravity = -1;
    public int rgravity = 1;
    private Vector3 position;
    private Vector3 velocity;
    private SpaceshipAnimation spaceshipAnimation;
    private Rectangle collision;
    private Texture texture;


    public Spaceship(int x, int y){                 //spaceship with size X/Y
        position = new Vector3(x, y, 0);            //z = 0 because it's 2 dimension
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("ShipAnimated.fw.png");
        spaceshipAnimation = new SpaceshipAnimation(new TextureRegion(texture),3,0.5f);
        collision = new Rectangle(x, y, 30, 30);    // spaceship collision size
    }

    public void update(float dt){
        spaceshipAnimation.update(dt);
        if ( position.x > 0 || position.x < 210)
            velocity.add (gravity, 0, 0);           // gravitation

        velocity.scl (dt);
        position.add(velocity.x, flying * dt, 0);
        velocity.scl(1 / dt);

        collision.setPosition(position.x, position.y);  //setting collision on top of ship

        if (position.x <0)
            position.x = 0;                             //stops ship X = 0

        if (position.x < 105)                           //ship will be dragged left if
            gravity = lgravity;                         //it's on left side of screen

        if (position.x >= 105)                          //ship will be dragged left if
            gravity = rgravity;                         //it's on left side of screen

        if (position.x > 210)
            position.x = 210;                           //stops ship X = 210
    }

    public void thrust_right(){                         //push ship right
        velocity.x = 50;

    }

    public void thrust_left(){                          //push ship left

        velocity.x = -50;
    }

    public Rectangle getCollision(){
        return collision;
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