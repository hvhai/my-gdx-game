package com.codehunter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Shark extends CommonActor {
    public Shark(Texture texture, float x, float y, Stage stage) {
        super(texture, x, y, stage);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && getX() > 0) {
            this.moveBy(-1, 0);
        }
        boolean isNotMaxWidth = getX() < Gdx.graphics.getWidth() - getWidth();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && isNotMaxWidth) {
            this.moveBy(1, 0);
        }
        boolean isNotMaxHeight = getX() < Gdx.graphics.getHeight() - getHeight();
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && isNotMaxHeight) {
            this.moveBy(0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && getY() > 0) {
            this.moveBy(0, -1);
        }
    }
}