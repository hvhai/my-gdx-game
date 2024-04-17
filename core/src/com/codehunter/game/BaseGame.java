package com.codehunter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseGame extends ApplicationAdapter {

    Stage mainStage;

    @Override
    public void create() {
        mainStage = new Stage();
        initialize();
    }

    protected abstract void initialize();

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

    protected abstract void update(float delta);

    @Override
    public void dispose() {
        super.dispose();
        mainStage.dispose();
    }
}
