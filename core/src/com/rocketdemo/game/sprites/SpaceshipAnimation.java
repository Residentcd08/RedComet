package com.rocketdemo.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by _7208 on 12.10.2017.
 */

public class SpaceshipAnimation {
    private Array <TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;                 //number of frames in one texture
    private int frame;

    public SpaceshipAnimation (TextureRegion region, int frameCount, float cycleTime){

        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }               //returns 3 square regions of 1 spaceship texture

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;      //0.5f/3
        frame = 0;

    }
    public void update(float dt){
        currentFrameTime += dt;                     //timer for texture update
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }

        if (frame >= frameCount)
            frame = 0;

    }

    public TextureRegion getFrame(){
        return  frames.get(frame);
    }                                                  //returns spaceship texture
}
