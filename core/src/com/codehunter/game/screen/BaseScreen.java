package com.codehunter.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseScreen implements Screen, InputProcessor {

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
        uiStage.draw();
    }


    // --------------------------------------------------
    // Screen methods
    // --------------------------------------------------

    @Override
    public void show() {
        InputMultiplexer inputMultiplexer = (InputMultiplexer)Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(mainStage);

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
        InputMultiplexer inputMultiplexer = (InputMultiplexer)Gdx.input.getInputProcessor();
        inputMultiplexer.removeProcessor(this);
        inputMultiplexer.removeProcessor(uiStage);
        inputMultiplexer.removeProcessor(mainStage);
    }

    @Override
    public void dispose() {

    }

    // --------------------------------------------------
    // Input processor methods
    // --------------------------------------------------

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
