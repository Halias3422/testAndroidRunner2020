package com.vdesain.game;

import com.vdesain.game.Background;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Forest extends Background
{
    private int forestSpeed;
    public Forest(String textName)
    {
        super(textName);
        screenPrintedHeight = 480;
        textPrintedHeight = screenPrintedHeight;
        screenPrintedHeight *= 3;
        screenPrintedWidth *= 3;
        forestSpeed = 16;
    }

    public void moveTextSrcX()
    {
        if (currentTime - forestSpeed > lastMoved)
        {
            srcX += 2;
            lastMoved = currentTime;
        }
    }

    public void print(SpriteBatch batch)
    {
        super.print(batch);
        moveTextSrcX();
    }

    public void increaseSpeed()
    {
        if (forestSpeed > 1)
            forestSpeed -= 2;
    }
}
