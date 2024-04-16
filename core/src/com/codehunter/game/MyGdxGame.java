package com.codehunter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture appleTexture;
    float appleX;
    float appleY;
    Rectangle appleRectangle;

    Texture sharkTexture;
    float sharkX;
    float sharkY;
    Rectangle sharkRectangle;

    Texture winTexture;
    boolean win;

    @Override
    public void create() {
        batch = new SpriteBatch();
        appleTexture = new Texture("apple.png");
        appleX = 60;
        appleY = 60;
        appleRectangle = new Rectangle(appleX, appleY, appleTexture.getWidth(), appleTexture.getHeight());

        sharkTexture = new Texture("shark.png");
        sharkX = 0;
        sharkY = 0;
        sharkRectangle = new Rectangle(sharkX, sharkY, sharkTexture.getWidth(), sharkTexture.getHeight());

        winTexture = new Texture("win.png");
    }

    @Override
    public void render() {
        // check user input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && sharkX > 0) {
            sharkX--;
        }
        boolean isMaxWidth = sharkX < Gdx.graphics.getWidth() - sharkTexture.getWidth();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && isMaxWidth) {
            sharkX++;
        }
        boolean isMaxHeight = sharkY < Gdx.graphics.getHeight() - sharkTexture.getHeight();
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && isMaxHeight) {
            sharkY++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && sharkY > 0) {
            sharkY--;
        }
        sharkRectangle.setPosition(sharkX, sharkY);
        if (sharkRectangle.overlaps(appleRectangle)) {
            win = true;
        }


        // build screen
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        if (win) {
            batch.draw(winTexture, appleX, appleY);
        } else {
            batch.draw(sharkTexture, sharkX, sharkY);
            batch.draw(appleTexture, appleX, appleY);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        appleTexture.dispose();
        sharkTexture.dispose();
    }
}
