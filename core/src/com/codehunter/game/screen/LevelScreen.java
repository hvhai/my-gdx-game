package com.codehunter.game.screen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.codehunter.game.actor.BaseActor;
import com.codehunter.game.actor.Explosion;
import com.codehunter.game.actor.Laser;
import com.codehunter.game.actor.Rock;
import com.codehunter.game.actor.SpaceShip;

public class LevelScreen extends BaseScreen {
    private SpaceShip spaceship;
    private boolean gameOver;


    @Override
    protected void initialize() {
        gameOver = false;

        BaseActor space = new BaseActor(0, 0, mainStage);
        space.loadTexture("assets/space.png");
        space.setSize(800, 600);
        BaseActor.setWorldBounds(space);
        spaceship = new SpaceShip(400, 300, mainStage);

        new Rock(600, 500, mainStage);
        new Rock(600, 300, mainStage);
        new Rock(600, 100, mainStage);
        new Rock(400, 100, mainStage);
        new Rock(200, 100, mainStage);
        new Rock(200, 300, mainStage);
        new Rock(200, 500, mainStage);
        new Rock(400, 500, mainStage);
    }


    @Override
    protected void update(float delta) {

        for (BaseActor rockActor : BaseActor.getList(mainStage, Rock.class.getName())) {
            if (rockActor.isOverlap(spaceship)) {
                if (spaceship.shieldPower <= 0) {
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(spaceship);
                    spaceship.remove();
                    spaceship.setPosition(-1000, -1000);

                    BaseActor messageLose = new BaseActor(0, 0, uiStage);
                    messageLose.loadTexture("message-lose.png");
                    messageLose.centerAtPosition(400, 300);
                    messageLose.setOpacity(0);
                    messageLose.addAction(Actions.fadeIn(1));
                    gameOver = true;


                } else {
                    spaceship.shieldPower -= 34;
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(rockActor);
                    rockActor.remove();
                }
            }

            for (BaseActor laserActor : BaseActor.getList(mainStage, Laser.class.getName())) {
                if (laserActor.isOverlap(rockActor)) {
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(rockActor);
                    laserActor.remove();
                    rockActor.remove();
                }
            }
        }

        // Handle endgame
        if (!gameOver && BaseActor.count(mainStage, Rock.class.getName()) == 0) {
            BaseActor messageWin = new BaseActor(0, 0, uiStage);
            messageWin.loadTexture("message-win.png");
            messageWin.centerAtPosition(400, 300);
            messageWin.setOpacity(0);
            messageWin.addAction(Actions.fadeIn(1));
            gameOver = true;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.X)
            spaceship.warp();
        if (keycode == Input.Keys.SPACE)
            spaceship.shoot();
        return false;
    }
}
