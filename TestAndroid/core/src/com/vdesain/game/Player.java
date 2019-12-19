package com.vdesain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    private Texture img;
    private int x;
    private int y;
    private double vspeed;
    private double maxvspeed;
    private int jumping;
    private long startedJump;
    private long currentJump;
    private double maxJumpHeight;
    private int width;
    private int realWidth;
    private int height;
    private int realHeight;
    private int jumpHighest;
    private int add_altitude;

    public Player()
    {
        img = new Texture("runner1.png");
        x = 64;
        jumping = 0;
        startedJump = 0;
        y = 0;
        vspeed = 0;
        maxvspeed = 50;
        width = 32;
        height = 55;
        realHeight = height * 5;
        realWidth = width * 5;
        maxJumpHeight = 2 * realHeight;
        jumpHighest = 0;
        add_altitude = 0;
    }

    public int getWidth()
    {
        return (width);
    }

    public int getHeight()
    {
        return (height);
    }

    public int getX()
    {
        return (x);
    }

    public int getY()
    {
        return (y);
    }

    public double getVspeed()
    {
        return (vspeed);
    }

    public Texture getImg()
    {
        return (img);
    }

    public int isJumping()
    {
        return (jumping);
    }

    public void setY(int newY)
    {
        y = newY;
        if (y < 0)
            y = 0;
    }

    public void setX(int newX)
    {
        x = newX;
    }

    public void initJump(SpriteBatch batch)
    {
        jumping = 1;
        startedJump = System.currentTimeMillis();
        jumpHighest = 0;
        x = 448;
        batch.begin();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        print(batch);
        batch.end();
    }

    public void jump() {
        currentJump = System.currentTimeMillis();
        if (x == 448 && currentJump - 48 > startedJump)
            x = 480;
        if (jumpHighest == 0)
            vspeed = (maxvspeed - (y / maxJumpHeight * maxvspeed) + add_altitude) * 1.5;
        else
        {
            vspeed = -(maxvspeed - (y / maxJumpHeight * maxvspeed) + add_altitude) * 1.5;
        }
        if (vspeed <= 5 && vspeed >= -2)
            x = 512;
        if (vspeed >= 0 && vspeed <= 5)
        {
            jumpHighest = 1;
            y = y - 1;
        }
        if (vspeed < -10)
            x = 544;
        y += vspeed;
    }

    public void endJump(SpriteBatch batch)
    {
        x = 576;
        batch.begin();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        print(batch);
        batch.end();
        y = 0;
        jumping = 0;
        jumpHighest = 0;
        startedJump = 0;
    }

    public void runRight()
    {
        x += 32;
        if (x > 225)
            x = 64;
    }

    public void print(SpriteBatch batch)
    {
        batch.draw(img, Gdx.graphics.getWidth() / 5 - width, y, realWidth, realHeight, x, 0, width, height, false, false);
    }
}
