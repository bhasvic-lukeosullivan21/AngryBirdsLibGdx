package org.osull.angrybirds.physicsdemo.bodies;

import com.badlogic.gdx.physics.box2d.World;

public class Plank extends AbstractGameBodyPolygon {

    public static final String BODY_PLANK = "body_plank";
    public Plank(World aWorld, String texture, float width, float height, float pos_x, float pos_y) {
        super(aWorld, texture, width, height, pos_x, pos_y);
        this.body.setUserData(BODY_PLANK);


    }

}
