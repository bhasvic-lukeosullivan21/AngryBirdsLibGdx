package org.osull.angrybirds.physicsdemo;


import static org.osull.angrybirds.physicsdemo.PhysicsDemo.LOG_TAG;
import static org.osull.angrybirds.physicsdemo.bodies.AngryBird.ANGRY_BIRD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ListenerClass implements ContactListener {
    
    @Override
    public void endContact(Contact contact) {

        if (contact.getFixtureA().getBody().getUserData()!=null &&
                contact.getFixtureA().getBody().getUserData().equals(ANGRY_BIRD)) {
            this.logData(contact.getFixtureA());
        }
        if (contact.getFixtureB().getBody().getUserData()!=null &&
                contact.getFixtureB().getBody().getUserData().equals(ANGRY_BIRD)) {
            this.logData(contact.getFixtureB());
        }
    }

    private void logData(Fixture fixture) {
        Gdx.app.log(LOG_TAG, "End Contact ");
//        Gdx.app.log(LOG_TAG, fixture.getBody().);
        Gdx.app.log(LOG_TAG, ""+fixture.getBody().getLinearVelocity());
        Gdx.app.log(LOG_TAG, ""+fixture.getBody().getAngularVelocity());
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
};
