package com.codehunter.game;

import com.codehunter.game.screen.LevelScreen;

public class MyGdxGame extends BaseGame {
    @Override
    public void create() {
        super.create();
        setActiveScreen(new LevelScreen());
    }
}
