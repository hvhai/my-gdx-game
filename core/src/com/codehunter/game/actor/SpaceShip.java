package com.codehunter.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SpaceShip extends BaseActor{
    public SpaceShip(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("spaceship.png");
        setBoundaryPolygon(8);

        setAcceleration(100);
        setMaxSpeed(200);
        setDeceleration(100);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        float degreesPerSecond = 120; // rotation speed
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            rotateBy(degreesPerSecond * delta);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            rotateBy(-degreesPerSecond * delta);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            accelerationAtAngle( getRotation() );

        applyPhysic(delta);
        wrapAroundWorld();
    }
}
