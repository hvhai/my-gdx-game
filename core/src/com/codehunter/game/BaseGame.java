package com.codehunter.game;

import com.badlogic.gdx.Game;

public abstract class BaseGame extends Game {
    private static BaseGame game;

    protected BaseGame() {
        game = this;
    }

    public static void setActiveScreen(BaseScreen screen) {
        game.setScreen(screen);
    }

}
