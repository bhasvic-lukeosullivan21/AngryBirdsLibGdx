package org.osull.angrybirds.physicsdemo.bodies;


import static org.osull.angrybirds.physicsdemo.PhysicsDemo.LOG_TAG;
import static org.osull.angrybirds.physicsdemo.bodies.AngryBird.BODY_ANGRY_BIRD;
import static org.osull.angrybirds.physicsdemo.bodies.Plank.BODY_PLANK;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import org.osull.angrybirds.physicsdemo.bodies.Plank;

public class ContactListenerClass implements ContactListener {
    private Vector2 MaxCollisionVel = new Vector2(0f,3f);
    @Override
    public void endContact(Contact contact) {

        if (contact.getFixtureA().getBody().getUserData()!=null &&
                contact.getFixtureA().getBody().getUserData().equals(BODY_ANGRY_BIRD)) {
            this.logData(BODY_ANGRY_BIRD, contact.getFixtureA());
        }
        if (contact.getFixtureB().getBody().getUserData()!=null &&
                contact.getFixtureB().getBody().getUserData().equals(BODY_ANGRY_BIRD)) {
            this.logData(BODY_ANGRY_BIRD, contact.getFixtureB());
        }

        if (contact.getFixtureA().getBody().getUserData()!=null &&
                contact.getFixtureA().getBody().getUserData().equals(BODY_PLANK)) {

            this.logData(BODY_PLANK, contact.getFixtureA());

            // TODO improve the comparision of the linear velocity vector
            if (getFixtureLinVelocity(contact.getFixtureA()).x >= 3 ||
                    getFixtureLinVelocity(contact.getFixtureA()).y >= 3) {
                contact.getFixtureA().getBody().setLinearVelocity(500f,500f);

            }
        }

    }

    private void logData(String type, Fixture fixture) {
        Gdx.app.log(LOG_TAG, type + "End Contact ");
//        Gdx.app.log(LOG_TAG, fixture.getBody().);
        Gdx.app.log(LOG_TAG, type+" linear vel "+fixture.getBody().getLinearVelocity());
        Gdx.app.log(LOG_TAG, type + " angular vel "+fixture.getBody().getAngularVelocity());
        Gdx.app.log(LOG_TAG, type + " angle "+fixture.getBody().getAngle());

    }
    public Vector2 getFixtureLinVelocity(Fixture fixture){
        return fixture.getBody().getLinearVelocity();
    }
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public void beginContact(Contact contact) {
       // Gdx.app.log(LOG_TAG, "Begin Contact");

    }
    public  void getLinearVelocity(){

    }
}
