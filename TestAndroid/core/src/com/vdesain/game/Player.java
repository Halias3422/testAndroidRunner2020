package com.vdesain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    private Texture img;
    private int x;
    private int y;
    private int jumpForce;
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
        jumpForce = 20;
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

    public int getJumpForce()
    {
        return (jumpForce);
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

    public void increaseJumpForce(int add)
    {
        System.out.printf("JE passe la\n");
        jumpForce -= add;
        if (jumpForce < 2)
            jumpForce = 2;
    }

    public void initJump()
    {
        jumping = 1;
        velocity = 40;
        y += 1;
        x = 448;
    }

    public void jump() {
        y += velocity;
        velocity -= jumpForce;
        if (velocity < 0)
            jumpForce += 2;
        if (y < 0)
                y = 0;
    }

    public void endJump()
    {
        jumping = 0;
        jumpForce = 20;
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
        batch.draw(img, Gdx.graphics.getWidth() / 5 - width, y, width * 5, height * 5, x, 0, width, height, false, false);
    }
}
