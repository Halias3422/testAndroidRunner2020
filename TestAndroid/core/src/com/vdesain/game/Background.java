package com.vdesain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background
{
   public Texture text;
   public String  name;
   public long    lastMoved;
   public long    currentTime;
   public int     screenTotalWidth;
   public int     screenTotalHeight;
   public int     screenPrintedWidth;
   public int     screenPrintedHeight;
   public int     textPrintedWidth;
   public int     textPrintedHeight;
   public int     textTotalWidth;
   public int     textTotalHeight;
   public int     srcX;
   public int     srcY;
   public int     tmpWidth;
   public int     screenTmpWidth;

    public Background(String textName)
    {
        name = textName;
        text = new Texture (name + ".png");
        lastMoved = 0;
        currentTime = 0;
        screenTotalWidth= Gdx.graphics.getWidth();
        screenTotalHeight = Gdx.graphics.getHeight();
        screenPrintedWidth = screenTotalWidth;
        screenPrintedHeight = screenTotalHeight;
        textPrintedWidth = screenTotalWidth;
        textTotalWidth = text.getWidth();
        textTotalHeight = text.getHeight();
        textPrintedHeight = textTotalHeight;
        srcX = 0;
        srcY = 0;
    }

    public void print(SpriteBatch batch)
    {
        currentTime = System.currentTimeMillis();
        if (srcX + textPrintedWidth > textTotalWidth)
        {
            tmpWidth = (textTotalWidth - (srcX + textPrintedWidth)) * -1;
            screenTmpWidth = tmpWidth;
            if (name.equals("forest"))
                screenTmpWidth *= 3;
            batch.draw(text, 0, 0, screenPrintedWidth - screenTmpWidth, screenPrintedHeight,
                    srcX, srcY, textPrintedWidth - tmpWidth, textPrintedHeight, false, false);
            batch.draw(text,screenPrintedWidth - screenTmpWidth, 0, screenTmpWidth, screenPrintedHeight,
                    0, srcY, tmpWidth, textPrintedHeight, false, false);
        }
        else
        {
            batch.draw(text, 0, 0, screenPrintedWidth, screenPrintedHeight,
                    srcX, srcY, textPrintedWidth, textPrintedHeight, false, false);
        }
        if (srcX >= textTotalWidth)
            srcX = 0;
    }

    public Texture getText()
    {
        return (text);
    }
}

