package com.rocketdemo.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by _7208 on 05.10.2017.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){              //constructor creates empty stack
        states = new Stack<State>();
    }

    public void push (State state){         //push element to the top of stack
        states.push(state);
    }

    public void pop () {                    //takes top element and delete it from stack
        states.pop().dispose();
    }

    public void set(State state){           //removes top element in stack and push next element to the top
        states.pop().dispose();
        states.push(state);
    }

    public  void update(float dt){          //refresh current screen
        states.peek().update(dt);           //returns top element in stack but not remove it
    }

    public void render (SpriteBatch sb){
        states.peek().render(sb);
    }
}
