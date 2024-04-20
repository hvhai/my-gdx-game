package com.codehunter.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Apple extends CommonActor{
    public Apple(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("apple.png");
    }
}
