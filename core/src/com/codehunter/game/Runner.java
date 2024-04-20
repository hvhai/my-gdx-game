package com.codehunter.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Runner extends CommonActor {
    public Runner(float x, float y, Stage stage) {
        super(x, y, stage);
        loadAnimationFromSheet("sprite-animation4.png", 5, 6, 1 / 30f, true);
    }
}
