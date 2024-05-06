package com.codehunter.game.actor;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Rock extends BaseActor {
    public Rock(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("rock.png");

        float random = MathUtils.random(30);
        addAction(Actions.forever(Actions.rotateBy(30 + random, 1)));
        setSpeed(50 + random);
        setMaxSpeed(50 + random);
        setDeceleration(0);
        setMotionAngle(MathUtils.random(360));

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        applyPhysic(delta);
        wrapAroundWorld();
    }
}
