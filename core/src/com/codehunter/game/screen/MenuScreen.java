package com.codehunter.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.codehunter.game.actor.CommonActor;

import static com.codehunter.game.BaseGame.setActiveScreen;

public class MenuScreen extends BaseScreen {
    @Override
    protected void initialize() {
    }

    @Override
    protected void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            setActiveScreen(new LevelScreen());
    }
}
