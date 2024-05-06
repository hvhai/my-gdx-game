package com.codehunter.game.actor;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Laser extends BaseActor {
    public Laser(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("laser.png");

        addAction(Actions.delay(1));
        addAction(Actions.after(Actions.fadeOut(0.5f)));
        addAction(Actions.after(Actions.removeActor()));

        setSpeed(400);
        setMaxSpeed(400);
        setAcceleration(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        applyPhysic(delta);
        wrapAroundWorld();
    }
}
