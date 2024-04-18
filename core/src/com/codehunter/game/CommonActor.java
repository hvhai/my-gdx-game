package com.codehunter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class CommonActor extends Actor {
//    private final TextureRegion textureRegion;
    private final Rectangle rectangle;

    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;

    public CommonActor(Texture texture, float x, float y, Stage stage) {
        super();
        rectangle = new Rectangle();
        rectangle.setSize(texture.getWidth(), texture.getHeight());

//        textureRegion = new TextureRegion(texture, texture.getWidth(), texture.getHeight());
//        setSize(texture.getWidth(), texture.getHeight());
        setPosition(x, y);
        stage.addActor(this);

        animation = null;
        elapsedTime = 0;
        animationPaused = false;
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
        if (!animationPaused)
            elapsedTime += delta;
//        if ( animation != null && isAnimationFinished() )
//            remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a);
//        if (isVisible())
        if (animation != null && isVisible())
            batch.draw(animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
//            batch.draw(textureRegion,
//                    getX(), getY(), getOriginX(), getOriginY(),
//                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
        TextureRegion animationTextureRegion = animation.getKeyFrame(0);
        float width = animationTextureRegion.getRegionWidth();
        float height = animationTextureRegion.getRegionHeight();
        setSize(width, height);
        setOrigin(width / 2, height / 2);
    }

    public Animation<TextureRegion> loadAnimationFromSheet(String filename, int rows, int cols,
                                                           float frameDuration, boolean loop) {
        Texture texture = new Texture(Gdx.files.internal(filename), true);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;
        TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);
        Array<TextureRegion> textureRegionArray = new Array<>();
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                textureRegionArray.add(temp[row][col]);

        Animation<TextureRegion> ani = new Animation<>(frameDuration, textureRegionArray);
        if (loop)
            ani.setPlayMode(Animation.PlayMode.LOOP);
        else
            ani.setPlayMode(Animation.PlayMode.NORMAL);
        if (animation == null)
            setAnimation(ani);
        return ani;
    }

    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames,
                                                           float frameDuration, boolean loop) {
        int fileCount = fileNames.length;
        Array<TextureRegion> textureArray = new Array<TextureRegion>();
        for (int n = 0; n < fileCount; n++) {
            String fileName = fileNames[n];
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            textureArray.add(new TextureRegion(texture));
        }
        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);
        if (loop)
            anim.setPlayMode(Animation.PlayMode.LOOP);
        else
            anim.setPlayMode(Animation.PlayMode.NORMAL);
        if (animation == null)
            setAnimation(anim);
        return anim;
    }

    public Animation<TextureRegion> loadTexture(String fileName) {
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }
    public boolean isAnimationFinished()
    {
        return animation.isAnimationFinished(elapsedTime);
    }
    public void setAnimationPaused(boolean pause)
    {
        animationPaused = pause;
    }
}
