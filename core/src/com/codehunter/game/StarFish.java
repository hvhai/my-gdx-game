package com.codehunter.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class StarFish extends CommonActor {
    private boolean isCollected = false;

    public StarFish(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("starfish.png");
        Action spin = Actions.rotateBy(30, 1);
        this.addAction(Actions.forever(spin));

        setBoundaryPolygon(8);

        isCollected = false;
    }

    public boolean isCollected() {
        return this.isCollected;
    }

    public void collect() {
        isCollected = true;
        clearActions();
        addAction(Actions.fadeOut(1));
        addAction(Actions.after(Actions.removeActor()));
    }
}
