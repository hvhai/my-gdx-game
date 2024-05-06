package com.codehunter.game.actor;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Explosion extends BaseActor {
    public Explosion(float x, float y, Stage stage) {
        super(x, y, stage);
        loadAnimationFromSheet("assets/explosion.png", 6, 6, 1 / 36f, false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isAnimationFinished())
            remove();
    }
}
