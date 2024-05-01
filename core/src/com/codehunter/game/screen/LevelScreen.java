package com.codehunter.game.screen;

import com.badlogic.gdx.Input;
import com.codehunter.game.actor.BaseActor;
import com.codehunter.game.actor.SpaceShip;

public class LevelScreen extends BaseScreen {
    private SpaceShip spaceship;


    @Override
    protected void initialize() {

        BaseActor space = new BaseActor(0, 0, mainStage);
        space.loadTexture("assets/space.png");
        space.setSize(800, 600);
        BaseActor.setWorldBounds(space);
        spaceship = new SpaceShip(400, 300, mainStage);
    }


    @Override
    protected void update(float delta) {
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.X)
            spaceship.warp();
        return false;
    }
}
