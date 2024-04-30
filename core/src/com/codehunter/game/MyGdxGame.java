package com.codehunter.game;

import com.codehunter.game.screen.MenuScreen;

public class MyGdxGame extends BaseGame {
    @Override
    public void create() {
        setActiveScreen(new MenuScreen());
    }
}
