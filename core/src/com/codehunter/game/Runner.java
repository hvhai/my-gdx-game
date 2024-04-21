package com.codehunter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Runner extends CommonActor {

    public Runner(float x, float y, Stage stage) {
        super(x, y, stage);
        loadAnimationFromSheet("sprite-animation4.png", 5, 6, 1 / 30f, true);

        setAcceleration(400);
        setMaxSpeed(100);
        setDeceleration(400);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && getX() > 0) {
            accelerationAtAngle(180);
        }
        boolean isNotMaxWidth = getX() < (Gdx.graphics.getWidth() - getWidth());
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && isNotMaxWidth) {
            accelerationAtAngle(0);
        }
        boolean isNotMaxHeight = getY() < (Gdx.graphics.getHeight() - getHeight());
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && isNotMaxHeight) {
            accelerationAtAngle(90);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && getY() > 0) {
            accelerationAtAngle(270);
        }

        applyPhysic(delta);

        // setAnimationPaused(!isMoving());

        if (getSpeed() > 0) {
            setRotation(getMotionAngle());
        }
    }
}
