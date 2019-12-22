package com.vdesain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background
{
    private Texture text;
    private String  name;
    private long    lastMoved;
    private long    currentTime;
    private int     screenWidth;
    private int     screenHeight;
    private int     textWidth;
    private int     textHeight;
    private int     srcX;
    private int     srcY;
    private int     tmpWidth;

    public Background(String textName)
    {
        name = textName;
        text = new Texture (name + ".png");
        lastMoved = 0;
        currentTime = 0;
        screenWidth= Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        textWidth = text.getWidth();
        textHeight = text.getHeight();
        srcX = 0;
        srcY = 0;
    }

    private void moveTextSrcX()
    {
        if (name == "mountains")
        {
            if (currentTime - 32 > lastMoved)
            {
                srcX += 1;
                lastMoved = currentTime;
            }
        }
        else if (name == "forest")
            srcX += 2;
    }

    public void print(SpriteBatch batch)
    {
        currentTime = System.currentTimeMillis();
        if (srcX + screenWidth > textWidth)
        {
            tmpWidth = (textWidth - (srcX + screenWidth)) * -1;
            batch.draw(text, 0, 0, screenWidth - tmpWidth, screenHeight,
                    srcX, srcY, screenWidth - tmpWidth, textHeight, false, false);
            batch.draw(text, screenWidth - tmpWidth, 0, tmpWidth, screenHeight,
                    0, srcY, tmpWidth, textHeight, false, false);
        }
        else
        {
            batch.draw(text, 0, 0, screenWidth, screenHeight,
                    srcX, srcY, screenWidth, textHeight, false, false);
        }
        moveTextSrcX();
        if (srcX >= textWidth)
            srcX = 0;
    }

    public Texture getText()
    {
        return (text);
    }
}

