package com.vdesain.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.LinkedList;
import java.util.ListIterator;


public class Player
{

    private Texture img;
    private int x;
    private int y;
    private int screenX;
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
    private int addAltitude;
    private int life;

    public Player()
    {
        img = new Texture("runner1.png");
        x = 64;
        screenX = Gdx.graphics.getWidth() / 5 - width;
        jumping = 0;
        startedJump = 0;
        y = 100;
        vspeed = 0;
        maxvspeed = 30;
        width = 32;
        height = 55;
        realHeight = height * 3;
        realWidth = width * 3;
        maxJumpHeight = realHeight;
        jumpHighest = 0;
        addAltitude = 0;
        life = 1;
    }

    public int getWidth()
    {
        return (width);
    }

    public int getRealWidth()
    {
        return (realWidth);
    }

    public int getRealHeight()
    {
        return (realHeight);
    }

    public int getHeight()
    {
        return (height);
    }

    public int getX()
    {
        return (x);
    }

    public int getScreenX()
    {
        return (screenX);
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
        if (y < 100)
            y = 100;
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        print(batch);
        batch.end();
    }

    public void setTest(int add)
    {
        addAltitude += add;
    }

    public void jump()
    {
        currentJump = System.currentTimeMillis();
        if (x == 448 && currentJump - 48 > startedJump)
            x = 480;
        if (jumpHighest == 0)
            vspeed = (maxvspeed - (y / maxJumpHeight * maxvspeed) + addAltitude);
        else
        {
            vspeed = -(maxvspeed - (y / maxJumpHeight * maxvspeed) + addAltitude) / 1.5;
            vspeed = vspeed > maxvspeed ? maxvspeed : vspeed;
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
        if (y < 100)
            y = 100;
    }

    public void endJump(SpriteBatch batch)
    {
        x = 576;
        y = 100;
        batch.begin();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        print(batch);
        batch.end();
        jumping = 0;
        jumpHighest = 0;
        startedJump = 0;
        addAltitude = 0;
    }

    public void runRight()
    {
        x += 32;
        if (x > 225)
            x = 64;
    }

    public void print(SpriteBatch batch)
    {
        batch.draw(img, screenX, y, realWidth, realHeight, x, 0, width, height, false, false);
    }

    public double getMaxJumpHeight()
    {
        return maxJumpHeight;
    }

 /*
    dst.x = screenX
    dst.y = y
    dst.w = realWidth;
    dst.h = realHeight;
    plat_dst =
    plat_dst.x = Current.x
    plat_dst.y = Current.y
    plat_dst.w = Current.width
    plat_dst.h = Current.height

 */

    public void check_collision_horizontal(LinkedList<Obstacles> listObstacles)
    {
        ListIterator<Obstacles> it = listObstacles.listIterator();
        int PLAYER_Y = y;
        int PLAYER_X = screenX;
        int PLAYER_WIDTH = realWidth;
        //   int PLAYER_HEIGHT = realHeight;
        int PLAYER_END_X = screenX + realWidth;
        int PLAYER_END_Y = y + realHeight;
        //horizontal

        while (it.hasNext())
        {
            Obstacles Current = it.next();
            int OBJ_X = Current.getX();
            int OBJ_Y = Current.getY();
            int OBJ_END_Y = Current.getEndY();
            int OBJ_END_X = Current.getEndX();
            //    int OBJ_WIDTH = Current.getWidth();
            //    int OBJ_HEIGHT = Current.getHeigh();

            if (PLAYER_Y < OBJ_END_Y || PLAYER_END_Y < OBJ_Y) //if control pb vertical
            {
                if (PLAYER_END_X > OBJ_X && PLAYER_END_X < OBJ_END_X) //if control droite joueur
                {
                    screenX = OBJ_X - PLAYER_WIDTH;
                }
            }
        }
    }


    public void check_collision_vertical(LinkedList<Obstacles> listObstacles)
    {
        int check = 0;
        ListIterator<Obstacles> it = listObstacles.listIterator();
        int PLAYER_Y = y;
        int PLAYER_X = screenX;
        //    int PLAYER_WIDTH = realWidth;
        int PLAYER_HEIGHT = realHeight;
        int PLAYER_END_X = screenX + realWidth;
        int PLAYER_END_Y = y + realHeight;

        while (it.hasNext())
        {
            Obstacles Current = it.next();
            int OBJ_X = Current.getX();
            int OBJ_Y = Current.getY();
            int OBJ_END_Y = Current.getEndY();
            int OBJ_END_X = Current.getEndX();
            //       int OBJ_WIDTH = Current.getWidth();
            //       int OBJ_HEIGHT = Current.getHeigh();

            if ((PLAYER_X < OBJ_X && PLAYER_END_X > OBJ_X) || (PLAYER_X < OBJ_END_X && PLAYER_X > OBJ_X) ||
                    (PLAYER_END_X > OBJ_X && PLAYER_END_X < OBJ_END_X)) //if control pb vertical
            {
                if (PLAYER_Y <= OBJ_Y && PLAYER_Y >= OBJ_END_Y) //if control gauche joueur
                {
                    y = OBJ_END_Y;
                    check++;
                }
                if (PLAYER_END_Y <= OBJ_Y && PLAYER_END_Y >= OBJ_END_Y) //if control droite joueur
                {
                    check++;
                    PLAYER_Y = OBJ_Y - PLAYER_HEIGHT;
                    life -= 1;
                    if (life > 0)
                        ;//reborn(platform, obj_list);
                }
                jumping = 0;
                vspeed = 0;
            }
        }
    }

    public void check_collision(LinkedList<Obstacles> listObstacles)
    {
        check_collision_horizontal(listObstacles);
 //       check_collision_vertical((listObstacles));
    }
}
