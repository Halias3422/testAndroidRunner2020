package com.vdesain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Obstacles
{
        private Random  Random;
        private int     screenWidth;
        private int     x;
        private int     width = 64;
        private int     endX;
        private int     endY;
        private int     height;
        private int     y;

        public Obstacles(Obstacles Prev, Player Player)
        {
                screenWidth = Gdx.graphics.getWidth();
                Random = new Random();
                if (Prev == null)
                {
                       x = Random.nextInt(screenWidth * 2 + 1 - screenWidth) + screenWidth;
                       height = Random.nextInt(Player.getRealWidth() * 2 + 101 - 110) + 110;
                       y = height - 416;
                }
                else
                {
                        x = Prev.getX() + 500;
                        height = Random.nextInt(Player.getRealWidth() * 2 + 101 - 110) + 110;
                        y = height - 416;
                }
                endX = x + width;
        }

        public int getX()
        {
                return (x);
        }

        public int getY()
        {
                return (y);
        }

        public int getEndY()
        {
                return (y + 416);
        }

        public int getEndX()
        {
                return (x + width);
        }

        public int getWidth()
        {
                return (width);
        }

        public int getHeigh()
        {
                return (height);
        }

        public void print(SpriteBatch batch, Texture img)
        {
                batch.draw(img, x, y);
                x -= 10;
        }

        public int setX()
        {
                return (x);
        }
}
