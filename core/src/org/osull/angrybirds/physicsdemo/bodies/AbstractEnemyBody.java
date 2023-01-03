package org.osull.angrybirds.physicsdemo.bodies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class AbstractEnemyBody extends AbstractGameBodyPolygon {
    public AbstractEnemyBody(World aWorld, String texture, float width, float height, float pos_x, float pos_y) {
        super(aWorld, texture, width, height, pos_x, pos_y);

    }
}