package com.vdesain.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mountains extends Background
{
    private int mountainsSpeed;

    public Mountains(String textName)
    {
        super(textName);
        while (textPrintedHeight < screenTotalHeight)
            textPrintedHeight += 2;
        mountainsSpeed = 32;
    }

    private void moveTextSrcX()
    {
        if (currentTime - mountainsSpeed > lastMoved)
        {
            srcX += 1;
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
        if (mountainsSpeed > 1)
            mountainsSpeed -= 2;
    }
}
