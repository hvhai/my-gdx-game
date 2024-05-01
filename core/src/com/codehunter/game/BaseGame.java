package com.codehunter.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.codehunter.game.screen.BaseScreen;

public abstract class BaseGame extends Game {
    private static BaseGame game;

    protected BaseGame() {
        game = this;
    }

    public static void setActiveScreen(BaseScreen screen) {
        game.setScreen(screen);
    }

    @Override
    public void create() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
}
