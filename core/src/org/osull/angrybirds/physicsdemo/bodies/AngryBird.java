package org.osull.angrybirds.physicsdemo.bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class AngryBird extends Image  {

    private ParticleEffect effect;
    private Body body;
    private World world;
    private float radius;

    public static final String BODY_ANGRY_BIRD = "angry_bird";


    public AngryBird(World aWorld, float pos_x, float pos_y){
        super(new Texture("SoccerBall.png"));

        this.radius = 1.5f;

        // set dimensions of the image
        this.setWidth(this.radius*2);
        this.setHeight(this.radius*2);
        this.setPosition(pos_x,pos_y);



        world = aWorld;
//        effect = new ParticleEffect();
//        effect.load(Gdx.files.internal("bubbleNote.p"), PhysicsDemo.textureAtlas);
//        effect.start();
//        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(pos_x, pos_y);


        // Create a body in the world using our definition
        body = world.createBody(bodyDef);
        body.setUserData(BODY_ANGRY_BIRD);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution= 0.5f;
        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
        this.setOrigin(this.getWidth()/2,this.getHeight()/2);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        effect.draw(batch);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setRotation(body.getAngle()*  MathUtils.radiansToDegrees);
        this.setPosition(body.getPosition().x-this.getWidth()/2,body.getPosition().y-this.getHeight()/2);
//        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());
//        effect.update(delta);

    }

    public Body getBody() {
        return body;
    }


    public void addListener(ContactListener contactListener) {

    }
}