package com.codehunter.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Knight extends CommonActor {
    public Knight(float x, float y, Stage stage) {
        super(x, y, stage);
        loadAnimationFromSheet("knight-run.png", 1, 7, 1 / 7f, true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isAnimationFinished())
            remove();
    }
}
