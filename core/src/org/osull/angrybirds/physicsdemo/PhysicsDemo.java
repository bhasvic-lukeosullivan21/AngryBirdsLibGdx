package org.osull.angrybirds.physicsdemo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import org.osull.angrybirds.physicsdemo.screens.TitleScreen;

public class PhysicsDemo extends Game {
    static public Skin skin;
    static public TextureAtlas textureAtlas;
    public final static String LOG_TAG="LUKE";

    @Override
    public void create () {
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        textureAtlas = new TextureAtlas();
        textureAtlas.addRegion("note",new TextureRegion(new Texture("note.png")));
        this.setScreen(new TitleScreen(this));

    }

    @Override
    public void render () {
        super.render();

    }

    public void dispose () {
        skin.dispose();

    }

}