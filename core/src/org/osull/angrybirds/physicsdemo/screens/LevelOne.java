package org.osull.angrybirds.physicsdemo.screens;

import static org.osull.angrybirds.physicsdemo.PhysicsDemo.LOG_TAG;
import static org.osull.angrybirds.physicsdemo.bodies.AngryBird.BODY_ANGRY_BIRD;
import static org.osull.angrybirds.physicsdemo.bodies.Plank.BODY_PLANK;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import org.osull.angrybirds.physicsdemo.bodies.BadPiggieEnemy;
import org.osull.angrybirds.physicsdemo.bodies.ContactListenerClass;
import org.osull.angrybirds.physicsdemo.bodies.AngryBird;
import org.osull.angrybirds.physicsdemo.bodies.Floor;
import org.osull.angrybirds.physicsdemo.bodies.Plank;


public class LevelOne implements Screen {
    private final ExtendViewport viewport;
    private AngryBird missile;
    private Stage stage;
    private Game game;
    private World world;
    private BadPiggieEnemy piggieEnemy;
    private Box2DDebugRenderer debugRenderer;
    private float accumulator = 0;
    private int screenWidth;
    private int screenHeight;
    private float touchDownX;
    private float touchDownY;
    private float touchUpX;
    private float touchUpY;
    private float touchDisplacementX;
    private float touchDisplacementY;

    private ContactListenerClass listener = new ContactListenerClass();


    float WORLD_WIDTH = 50;
    float WORLD_HEIGHT = 40;
    private boolean collisionBool;

    public LevelOne(Game aGame) {

        game = aGame;
        this.viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);
        stage.setViewport(viewport);

        Gdx.input.setInputProcessor(stage);

        debugRenderer = new Box2DDebugRenderer();
        Vector2 gravity = new Vector2(0.0f, -9.8f);
        world = new World(gravity, true);

        //world.setContactListener(this.listener);
        world.setContactListener(new ContactListener() {
            public Vector2 getFixtureLinVelocity(Fixture fixture){
                return fixture.getBody().getLinearVelocity();
            }


            @Override
            public void beginContact(Contact contact) {

            }
            @Override
            public void endContact(Contact contact) {

                if (contact.getFixtureA().getBody().getUserData()!=null &&
                        contact.getFixtureA().getBody().getUserData().equals(BODY_ANGRY_BIRD)) {
                    //Gdx.app.log(LOG_TAG,(BODY_ANGRY_BIRD, contact.getFixtureA());
                }
                if (contact.getFixtureB().getBody().getUserData()!=null &&
                        contact.getFixtureB().getBody().getUserData().equals(BODY_ANGRY_BIRD)) {
                   // Gdx.app.log(LOG_TAG,(BODY_ANGRY_BIRD, contact.getFixtureB());
                }

                if (contact.getFixtureA().getBody().getUserData()!=null &&
                        contact.getFixtureA().getBody().getUserData().equals(BODY_PLANK)) {
                    //checks if contact is made with the plank

                    //Gdx.app.log(LOG_TAG, BODY_PLANK, contact.getFixtureA());

                    // TODO improve the comparision of the linear velocity vector
                    if (getFixtureLinVelocity(contact.getFixtureA()).x >= 5 ||
                            getFixtureLinVelocity(contact.getFixtureA()).y >= 5) {
                        //checks if the linear velocity is fast enough to "break" what it connects with

                        //contact.getFixtureA().getBody().getUserData();
                        Gdx.app.log(LOG_TAG, "Hit with linear velocity >= 5");
                        setCollisionBool(true);
                        //sets collision to true

                    }
                }
                if (getCollisionBool() ) {
                    world.destroyBody(contact.getFixtureA().getBody());
                    //destroys body on contact


                }

            }




            @Override
            public void postSolve(Contact arg0, ContactImpulse arg1) {

            }

            @Override
            public void preSolve(Contact arg0, Manifold arg1) {

            }
        });


        Plank plank1 = new Plank(world, "wood.jpg", 1.2f, 8, 40, 10);
        stage.addActor(plank1);
        Plank plank2 = new Plank(world, "wood.jpg",1.2f, 8, 40+10, 10);
        stage.addActor(plank2);
        Plank plank3 = new Plank(world, "wood.jpg",12, 1.2f, 40+5, 20);
        stage.addActor(plank3);
        //Planks 1-3 bottom structure
        BadPiggieEnemy piggieEnemy = new BadPiggieEnemy(world, "pig.png", 3,3,40+5,22);
         stage.addActor(piggieEnemy);
        //adds a pig which will be the objective
        Plank plank4 = new Plank(world, "wood.jpg",1.2f, 5, 40+2, 23);
        stage.addActor(plank4);
        Plank plank5 = new Plank(world, "wood.jpg",1.2f, 5, 40+8, 23);
        stage.addActor(plank5);
        Plank plank6 = new Plank(world, "wood.jpg",8, 1.2f, 40+5, 25);
        stage.addActor(plank6);
        //4-6 top structure
        // creates a structure with planks

        Floor platform1 = new Floor(world,5,6,3,8,0);
        stage.addActor(platform1);
        //creates the bottom floor
        newAngryBird(world,6.5f,WORLD_HEIGHT);
        missile.setTouchable(Touchable.enabled);

        missile.addListener(new InputListener() {

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                Gdx.app.log(LOG_TAG, "touchDragged X:" + x + " Y:" + y);
            }


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(LOG_TAG, "TouchDown X:" + x + " Y:" + y);
                touchDownX = x;
                touchDownY = y;
                // the values of x and y for when mouse or a finger is pressed
                missile.setTouchable(Touchable.disabled);
                //Makes the missile that has just been 'flicked' not touchable as an attempt to make it one 'flick' per missile

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(LOG_TAG, "TouchUp X:" + x + " Y:" + y);
                touchUpX = x;
                touchUpY = y;
                // the values of x and y for when mouse or a finger is released
                touchDisplacementX = touchUpX - touchDownX;
                touchDisplacementY = touchUpY - touchDownY;
                //This code calculates the displacement of the drag or 'flick' to calculate the strength of the impulse applied to the AngryBird
                //touchDisplacementX = touchDownX - touchUpX;
                //touchDisplacementY = touchDownY - touchUpY;
                //Flip touch up and down to simulate angry birds slingshot mechanism
                //Currently not simulating slingshot as it is counterintuitive unless there is an explanation or slingshot image
                Gdx.app.log(LOG_TAG, "TouchDisplacementX:" + touchDisplacementX);
                Gdx.app.log(LOG_TAG, "TouchDisplacementY:" + touchDisplacementY);

                //Logs the displacments to see in the logcat
                Vector2 pos = missile.getBody().getPosition();
                //gets Vector2 position of the AngryBird
                missile.getBody().applyLinearImpulse(touchDisplacementX * 100f, touchDisplacementY * 100f, pos.x, pos.y, true);

                //applies impulse (push of movement) to the missile


            }

        });
//        piggieEnemy.addListener(new InputListener() {
//
//            public void touchDragged (InputEvent event, float x, float y, int pointer) {
//            }
//
//
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                Gdx.app.log(LOG_TAG, "piggie touched down");
//
//                return true;
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//
//            }
//
//        });


        stage.addActor(new Floor(world,0,2,WORLD_WIDTH*2,5,0));
        //adds the floor to the level environment

    }
    public void setCollisionBool(boolean collisionBool) {
        this.collisionBool = collisionBool;
        //sets the collisionBool value
    }
    public boolean getCollisionBool(){
        return this.collisionBool;
        //gets the collisionBool value
    }

    @Override
    public void show() {
        Gdx.app.log("MainScreen","show");
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.viewport.update(this.screenWidth, this.screenHeight);

        stage.act();
        stage.draw();
        debugRenderer.render(world, stage.getCamera().combined);

        Gdx.graphics.setTitle("FPS: "+Gdx.graphics.getFramesPerSecond());
        doPhysicsStep(delta);
    }
    public void newAngryBird(World world, float xPos, float yPos){
        this.missile = new AngryBird(world,6.5f, WORLD_HEIGHT);
        stage.addActor(missile);
        missile.setTouchable(Touchable.enabled);

    }

    private static final int VELOCITY_ITERATIONS = 8;
    private static final int POSITION_ITERATIONS = 4;

    private void doPhysicsStep(float deltaTime) {
//         fixed time step
//         max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= deltaTime) {
            world.step(deltaTime,VELOCITY_ITERATIONS,POSITION_ITERATIONS);
            accumulator -= deltaTime;
        }
    }



    @Override
    public void resize(int width, int height)
    {
        //you need this function to keep screen size updated
        this.screenWidth = width;
        this.screenHeight = height;
//        this.viewport.update(this.screenWidth, this.screenHeight);
//        this.stage.getViewport().update(this.screenWidth, this.screenHeight);

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
        stage.dispose();
    }


}