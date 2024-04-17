package com.codehunter.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CommonActor extends Actor {
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;

    public CommonActor(Texture texture) {
        super();
        rectangle = new Rectangle();
        rectangle.setSize(texture.getWidth(), texture.getHeight());

        textureRegion = new TextureRegion(texture, texture.getWidth(), texture.getHeight());
        setSize(texture.getWidth(), texture.getHeight());
    }

    public Rectangle getRectangle() {
        rectangle.setPosition(getX(), getY());
        return rectangle;
    }

    public boolean isOverlap(CommonActor otherActor) {
        return this.getRectangle().overlaps(otherActor.getRectangle());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a);
        if (isVisible()) {
            batch.draw(textureRegion,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

}
