package com.codehunter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MyGdxGame extends BaseGame {

    Shark shark;
    CommonActor apple;
    CommonActor winMessage;

    @Override
    protected void initialize() {
        apple = new CommonActor(new Texture("apple.png"), 60, 60, mainStage);
        shark = new Shark(new Texture("shark.png"), 0, 0, mainStage);
        winMessage = new CommonActor(new Texture("win.png"),
                (float) Gdx.graphics.getWidth() / 2 - winMessage.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 2 - winMessage.getHeight() / 2,
                mainStage);
        winMessage.setVisible(false);
        mainStage.addActor(winMessage);

        apple.setDebug(true);
        shark.setDebug(true);
        winMessage.setDebug(true);
    }


    @Override
    protected void update(float delta) {
        if (shark.isOverlap(apple)) {
            apple.remove();
            shark.remove();
            winMessage.setVisible(true);
        }
    }
}
