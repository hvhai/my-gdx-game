package com.codehunter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MyGdxGame extends BaseGame {

    Shark shark;
    CommonActor apple;
    CommonActor winMessage;

    CommonActor knight;
    CommonActor runner;

    @Override
    protected void initialize() {

        apple = new CommonActor(new Texture("apple.png"), 100, 100, mainStage);
        apple.loadTexture("apple.png");

        shark = new Shark(new Texture("shark.png"), 0, 0, mainStage);
        shark.loadTexture("shark.png");
        Action spin = Actions.rotateBy(180, 5);
        shark.addAction(spin);
        Action move = Actions.moveBy(50, 40, 2);
        shark.addAction(move);
        Action moveAfter = Actions.moveBy(50, 0, 2);
        shark.addAction(Actions.after(moveAfter));


        Texture messageTexture = new Texture("win.png");
        winMessage = new CommonActor(messageTexture,
                (float) Gdx.graphics.getWidth() / 2 - (float) messageTexture.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 2 - (float) messageTexture.getHeight() / 2,
                mainStage);
        winMessage.loadTexture("win.png");
        winMessage.setVisible(false);

        knight = new CommonActor(new Texture("knight-run.png"), 50, 200, mainStage);
        knight.loadAnimationFromSheet("knight-run.png", 1, 7, 1/7f,true);

        runner = new CommonActor(new Texture("sprite-animation4.png"), 200, 200, mainStage);
        runner.loadAnimationFromSheet("sprite-animation4.png", 5, 6, 1/30f,true);

        apple.setDebug(true);
        shark.setDebug(true);
        winMessage.setDebug(true);
        knight.setDebug(true);
        runner.setDebug(true);
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
