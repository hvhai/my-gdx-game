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

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            accelerationAtAngle(180);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            accelerationAtAngle(0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            accelerationAtAngle(90);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            accelerationAtAngle(270);
        }

        applyPhysic(delta);

        setAnimationPaused(!isMoving());

        if (getSpeed() > 0) {
            setRotation(getMotionAngle());
        }

        boolean isMaxWidth = getX() >= (Gdx.graphics.getWidth() - getWidth());
        boolean isMaxHeight = getY() >= (Gdx.graphics.getHeight() - getHeight());
        boolean isMinWidth = getX() <= 0;
        boolean isMinHeight = getY() <= 0;
        if (isMaxHeight || isMaxWidth || isMinWidth || isMinHeight) {
            setSpeed(0);
        }
    }
}
