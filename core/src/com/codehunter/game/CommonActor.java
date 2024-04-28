package com.codehunter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class CommonActor extends Actor {

//    private final Rectangle rectangle;

    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;

    private Vector2 velocityVector;
    private Vector2 accelerationVector;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;

    private Polygon boundaryPolygon;

    public CommonActor(float x, float y, Stage stage) {
        super();
//        rectangle = new Rectangle();

        setPosition(x, y);
        stage.addActor(this);

        animation = null;
        elapsedTime = 0;
        animationPaused = false;

        velocityVector = new Vector2(0, 0);
        accelerationVector = new Vector2(0, 0);
        acceleration = 0;
        maxSpeed = 1000;
        deceleration = 0;
    }

//    public Rectangle getRectangle() {
//        rectangle.setPosition(getX(), getY());
//        return rectangle;
//    }

//    public boolean isOverlap(CommonActor otherActor) {
//        return this.getRectangle().overlaps(otherActor.getRectangle());
//    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!animationPaused) {
            elapsedTime += delta;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a);
        if (animation != null && isVisible()) {
            batch.draw(animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

    // --------------------------------------------------
    // Animation method
    // --------------------------------------------------
    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
        TextureRegion animationTextureRegion = animation.getKeyFrame(0);
        float width = animationTextureRegion.getRegionWidth();
        float height = animationTextureRegion.getRegionHeight();
        setSize(width, height);
        setOrigin(width / 2, height / 2);

//        rectangle.setSize(width, height);
        if (boundaryPolygon == null) {
            setBoundaryRectangle();
        }
    }

    public Animation<TextureRegion> loadAnimationFromSheet(String filename, int rows, int cols,
                                                           float frameDuration, boolean loop) {
        Texture texture = new Texture(Gdx.files.internal(filename), true);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;
        TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);
        Array<TextureRegion> textureRegionArray = new Array<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                textureRegionArray.add(temp[row][col]);
            }
        }

        Animation<TextureRegion> ani = new Animation<>(frameDuration, textureRegionArray);
        if (loop) {
            ani.setPlayMode(Animation.PlayMode.LOOP);
        } else {
            ani.setPlayMode(Animation.PlayMode.NORMAL);
        }
        if (animation == null) {
            setAnimation(ani);
        }
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
        if (loop) {
            anim.setPlayMode(Animation.PlayMode.LOOP);
        } else {
            anim.setPlayMode(Animation.PlayMode.NORMAL);
        }
        if (animation == null) {
            setAnimation(anim);
        }
        return anim;
    }

    public Animation<TextureRegion> loadTexture(String fileName) {
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(elapsedTime);
    }

    public void setAnimationPaused(boolean pause) {
        animationPaused = pause;
    }

    // -------------------------------------------------- 
    // Movement
    // --------------------------------------------------
    public void setSpeed(float speed) {
        if (velocityVector.len() == 0) {
            velocityVector.set(speed, 0);
        } else {
            velocityVector.setLength(speed);
        }
    }

    public float getSpeed() {
        return this.velocityVector.len();
    }

    public void setMotionAngle(float angle) {
        velocityVector.setAngleDeg(angle);
    }

    public float getMotionAngle() {
        return this.velocityVector.angleDeg();
    }

    public boolean isMoving() {
        return (getSpeed() > 0);
    }

    public void setAcceleration(float acc) {
        acceleration = acc;
    }

    public void accelerationAtAngle(float angle) {
        accelerationVector.add(new Vector2(acceleration, 0).setAngleDeg(angle));
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    public void applyPhysic(float delta) {
        // apply acceleration
        velocityVector.add(accelerationVector.x * delta, accelerationVector.y * delta);
        float speed = getSpeed();
        // decrease speed when not accelerating
        if (accelerationVector.len() == 0)
            speed -= deceleration * delta;
        // keep speed within set bounds
        speed = MathUtils.clamp(speed, 0, maxSpeed);
        // appy velocity
        moveBy(velocityVector.x * delta, velocityVector.y * delta);
        // reset the acceleration
        accelerationVector.set(0, 0);
    }

    // --------------------------------------------------
    // Collision
    // --------------------------------------------------

    public void setBoundaryRectangle() {
        float width = getWidth();
        float height = getHeight();
        float[] vertices = {0, 0, width, 0, width, height, 0, height};
        boundaryPolygon = new Polygon(vertices);
    }

    public void setBoundaryPolygon(int numberSides) {
        float width = getWidth();
        float height = getHeight();
        float[] vertices = new float[2 * numberSides];
        for (int i = 0; i < numberSides; i++) {
            float angle = i * 6.28f / numberSides;
            // x-coordinate
            vertices[2 * i] = width / 2 * MathUtils.cos(angle) + width / 2;
            vertices[2 * i + 1] = height / 2 * MathUtils.cos(angle) + height / 2;
        }
        boundaryPolygon = new Polygon(vertices);
    }

    public Polygon getBoundaryPolygon() {
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setRotation(getRotation());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        return boundaryPolygon;
    }

    public boolean isOverlap(CommonActor otherActor) {
        Polygon polygon1 = this.getBoundaryPolygon();
        Polygon polygon2 = otherActor.getBoundaryPolygon();
        // quick check for performance
        if (!polygon1.getBoundingRectangle().overlaps(polygon2.getBoundingRectangle()))
            return false;
        return Intersector.overlapConvexPolygons(polygon1, polygon2);
    }

    public void centerAtPosition(float x, float y) {
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
    }

    public void centerAtActor(CommonActor otherActor) {
        centerAtPosition(otherActor.getX() + otherActor.getWidth() / 2,
                otherActor.getY() + otherActor.getHeight() / 2);
    }

    public void setOpacity(float opacity) {
        this.getColor().a = opacity;
    }

    public static ArrayList<CommonActor> getList(Stage stage, String className) {
        ArrayList<CommonActor> list = new ArrayList<CommonActor>();

        Class theClass = null;
        try {
            theClass = Class.forName(className);
        } catch (Exception error) {
            error.printStackTrace();
        }

        for (Actor a : stage.getActors()) {
            if (theClass.isInstance(a))
                list.add((CommonActor) a);
        }

        return list;
    }

    public static int count(Stage stage, String className)
    {
        return getList(stage, className).size();
    }

    public Vector2 preventOverlap(CommonActor otherActor) {
        Polygon polygon1 = this.getBoundaryPolygon();
        Polygon polygon2 = otherActor.getBoundaryPolygon();
        if (!polygon1.getBoundingRectangle().overlaps(polygon2.getBoundingRectangle()))
            return null;
        Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();
        boolean polygonOverlap = Intersector.overlapConvexPolygons(polygon1, polygon2, mtv);
        if (!polygonOverlap) {
            return null;
        }
        this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
        return mtv.normal;
    }
}
