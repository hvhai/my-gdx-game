package com.codehunter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MenuScreen extends BaseScreen {
    @Override
    protected void initialize() {
        CommonActor ocean = new CommonActor(0, 0, mainStage);
        ocean.loadTexture("water.jpg");
        ocean.setSize(800, 600);
        CommonActor title = new CommonActor(0, 0, mainStage);
        title.loadTexture("starfish-collector.png");
        title.centerAtPosition(400, 300);
        title.moveBy(0, 100);
        CommonActor start = new CommonActor(0, 0, mainStage);
        start.loadTexture("message-start.png");
        start.centerAtPosition(400, 300);
        start.moveBy(0, -100);
    }

    @Override
    protected void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            MyGdxGame.setActiveScreen(new LevelScreen());
    }
}
