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
        apple = new Apple(100, 100, mainStage);

        shark = new Shark(0, 0, mainStage);
        Action spin = Actions.rotateBy(180, 5);
        shark.addAction(spin);
        Action move = Actions.moveBy(50, 40, 2);
        shark.addAction(move);
        Action moveAfter = Actions.moveBy(50, 0, 2);
        shark.addAction(Actions.after(moveAfter));

        Texture messageTexture = new Texture("win.png");
        winMessage = new CommonActor(
                (float) Gdx.graphics.getWidth() / 2 - (float) messageTexture.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 2 - (float) messageTexture.getHeight() / 2,
                mainStage);
        winMessage.loadTexture("win.png");
        winMessage.setVisible(false);

        knight = new Knight(50, 200, mainStage);

        runner = new Runner(200, 200, mainStage);

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
