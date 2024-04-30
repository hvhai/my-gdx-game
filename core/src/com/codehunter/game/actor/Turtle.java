package com.codehunter.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Turtle extends CommonActor {
    public Turtle(float x, float y, Stage stage) {
        super(x, y, stage);
        String[] assetFileList = {"turtle-1.png", "turtle-2.png", "turtle-3.png",
                "turtle-4.png", "turtle-5.png", "turtle-6.png",};
        loadAnimationFromFiles(assetFileList, 1 / 6f, true);
        setAcceleration(400);
        setMaxSpeed(100);
        setDeceleration(400);

        setBoundaryPolygon(8);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            accelerationAtAngle(180);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            accelerationAtAngle(0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            accelerationAtAngle(90);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            accelerationAtAngle(270);

        applyPhysic(delta);

        setAnimationPaused(!isMoving());

        if (getSpeed() > 0)
            setRotation(getMotionAngle());

        // Make sure turtle always in the world
        boundToWorld();
        alignCamera();
    }
}
