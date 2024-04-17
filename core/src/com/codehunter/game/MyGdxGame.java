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
        Texture messageTexture = new Texture("win.png");
        winMessage = new CommonActor(messageTexture,
                (float) Gdx.graphics.getWidth() / 2 - (float) messageTexture.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 2 - (float) messageTexture.getHeight() / 2,
                mainStage);
        winMessage.setVisible(false);

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
