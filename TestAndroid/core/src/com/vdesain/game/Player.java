package com.vdesain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    private Texture img;
    private int x;
    private int y;
    private int velocity;

    private int jumping;
    private int width;
    private int height;

    public Player()
    {
        img = new Texture("runner1.png");
        x = 64;
        jumping = 0;
        y = 0;
        velocity = 0;
        width = 32;
        height = 55;
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

    public int getVelocity()
    {
        return (velocity);
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

    public void addToVelocity(int add)
    {
        velocity += add;
    }

    public void initJump()
    {
        System.out.print("JUMP INIT\n");
        jumping = 1;
        velocity = 30;
        x = 448;
    }

    public void endJump()
    {
        System.out.print("JUMP ENDING\n");
        jumping = 0;
        y = 0;
        velocity = 0;
        x = 64;
    }

    public void runRight()
    {
        x += 32;
        if (x > 225)
            x = 64;
    }

    public void print(SpriteBatch batch)
    {
        batch.draw(img, Gdx.graphics.getWidth() / 5 - width, y, width * 3, height * 3, x, 0, width, height, false, false);
    }
}
