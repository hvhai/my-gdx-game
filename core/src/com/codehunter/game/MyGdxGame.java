package com.codehunter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyGdxGame extends ApplicationAdapter {

    Stage mainStage;
    Shark shark;
    CommonActor apple;
    CommonActor winMessage;

    @Override
    public void create() {
        mainStage = new Stage();
        Gdx.input.setInputProcessor(mainStage);
        initialize();

        apple.setDebug(true);
        shark.setDebug(true);
        winMessage.setDebug(true);
    }

    private void initialize() {
        apple = new CommonActor(new Texture("apple.png"));
        apple.setPosition(60, 60);
        mainStage.addActor(apple);

        shark = new Shark(new Texture("shark.png"));
        shark.setPosition(0, 0);
        mainStage.addActor(shark);

        winMessage = new CommonActor(new Texture("win.png"));
        winMessage.setPosition((float) Gdx.graphics.getWidth() / 2 - winMessage.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 2 - winMessage.getHeight() / 2);
        winMessage.setVisible(false);
        mainStage.addActor(winMessage);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        mainStage.act(delta);

        update(delta);

        // clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // draw graphics
        mainStage.draw();
    }

    private void update(float delta) {
        if (shark.isOverlap(apple)) {
            apple.remove();
            shark.remove();
            winMessage.setVisible(true);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        mainStage.dispose();
    }
}
