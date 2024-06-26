package com.codehunter.game.screen;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.codehunter.game.actor.CommonActor;
import com.codehunter.game.actor.Rock;
import com.codehunter.game.actor.StarFish;
import com.codehunter.game.actor.Turtle;
import com.codehunter.game.actor.Whirlpool;

public class LevelScreen extends BaseScreen {
    private Turtle turtle;
    private boolean win;


    @Override
    protected void initialize() {
        CommonActor ocean = new CommonActor(0, 0, mainStage);
        ocean.loadTexture("water-border.jpg");
        // set world bound by ocean
        CommonActor.setWorldBounds(ocean);

        new StarFish(400, 400, mainStage);
        new StarFish(500, 100, mainStage);
        new StarFish(100, 450, mainStage);

        new Rock(200, 150, mainStage);
        new Rock(100, 300, mainStage);
        new Rock(300, 350, mainStage);
        new Rock(450, 200, mainStage);

        turtle = new Turtle(20, 20, mainStage);
    }


    @Override
    protected void update(float delta) {
        for (CommonActor starfishActor : CommonActor.getList(mainStage, StarFish.class.getName())) {
            StarFish starfish = (StarFish) starfishActor;
            if (turtle.isOverlap(starfish) && !starfish.isCollected()) {
                starfish.collect();

                Whirlpool whirl = new Whirlpool(0, 0, mainStage);
                whirl.centerAtActor(starfish);
                whirl.setOpacity(0.25f);
            }
        }

        for (CommonActor rockActor : CommonActor.getList(mainStage, Rock.class.getName())) {
            Rock rock = (Rock) rockActor;
            turtle.preventOverlap(rock);
        }

        if (CommonActor.count(mainStage, StarFish.class.getName()) == 0 && !win) {
            win = true;
            CommonActor youWinMessage = new CommonActor(0, 0, mainStage);
            youWinMessage.loadTexture("assets/you-win.png");
            youWinMessage.centerAtPosition(400, 300);
            youWinMessage.setOpacity(0);
            youWinMessage.addAction(Actions.delay(1));
            youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
        }
    }
}
