package com.vdesain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture mountains;
    private Texture forest;
    private Texture ground;
    private int     backgroundOffset;
    private int     middlegroundOffset;
    private long    backgroundSaved;
    private long    current;
    private int     width;
    private int     height;

    public Background()
    {
        mountains = new Texture("mountains.png");
        forest = new Texture("forest.png");
        ground = new Texture("ground.png");
        backgroundSaved = 0;
        current = 1000;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        backgroundOffset = 0;
        middlegroundOffset = 0;
    }

    public void print(SpriteBatch batch)
    {
        current = System.currentTimeMillis();
        middlegroundOffset += 3;
        if (current - 32 > backgroundSaved)
        {
            backgroundOffset += 1;
            backgroundSaved = current;
        }
        batch.draw(mountains, 0, 0, width, height, backgroundOffset,
                0, width, height, false, false);
        batch.draw(forest, 0, 0, width * 3, 480 * 3, middlegroundOffset,
                0, width, 480, false, false);
        batch.draw(ground, 0, 0, width * 3, 480 * 3, middlegroundOffset,
                0, width, 480, false, false);
    }

    public Texture getForest()
    {
        return forest;
    }

    public Texture getMountains()
    {
        return mountains;
    }

    public Texture getGround()
    {
        return ground;
    }
}
