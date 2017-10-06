package com.rocketdemo.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by _7208 on 05.10.2017.
 */

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;


    public State (GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void HandleInput();  //abstract method for handling user input
    public abstract void update(float dt);  // refresh screen
    public abstract void render(SpriteBatch sb); // drawing a screen. SpriteBatch - textures/coordinates
    public abstract void dispose(); //cleaning unused resources
}
