package com.codehunter.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Apple extends CommonActor {
    private boolean isCollected;

    public Apple(float x, float y, Stage stage) {
        super(x, y, stage);
        isCollected = false;
        loadTexture("apple.png");
        setBoundaryPolygon(8);
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void collect() {
        isCollected = true;
        clearActions();
        addAction(Actions.fadeOut(1));
        addAction(Actions.after(Actions.removeActor()));
    }
}
