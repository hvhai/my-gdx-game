package com.codehunter.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseScreen implements Screen {

    Stage mainStage;
    Stage uiStage;

    protected BaseScreen() {
        mainStage = new Stage();
        uiStage = new Stage();
        initialize();
    }

    protected abstract void initialize();

    protected abstract void update(float delta);

    @Override
    public void render(float delta) {
        mainStage.act(delta);
        uiStage.act(delta);

        update(delta);

        // clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // draw graphics
        mainStage.draw();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
