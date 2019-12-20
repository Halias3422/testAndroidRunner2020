package com.vdesain.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.LinkedList;
import java.util.ListIterator;

public class TestAndroid extends ApplicationAdapter {
	SpriteBatch batch;
	Player 		Player;
	Background  Background;
	LinkedList<Obstacles> 	listObstacles;
	ListIterator<Obstacles> it;
	Texture		obst1;
	long		saved;
	long		current;
	int  		stillTouched;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Player = new Player();
		Background = new Background();
		listObstacles = new LinkedList<Obstacles>();
		initObstacles();
		saved = System.currentTimeMillis();
		current = 0;
		stillTouched = 0;
	}

	public void initObstacles()
	{
		obst1 = new Texture("ground2.png");

		listObstacles.add(new Obstacles(null, Player));
		for (int i = 1; i < 10; i++)
			listObstacles.addLast(new Obstacles(listObstacles.getLast(), Player));
	}

	public LinkedList updatePlatforms(LinkedList<Obstacles> listObstacles)
	{
		if (listObstacles.getFirst().getEndX() < 0)
		{
		    System.out.printf("Je Passe la\n");
			listObstacles.removeFirst();
			listObstacles.addLast(new Obstacles(listObstacles.getLast(), Player));
		}
		return (listObstacles);
	}

	public void playerMovement(LinkedList<Obstacles> listObstacles)
	{

		if (Player.isJumping() == 0 && (Gdx.input.isButtonJustPressed((Input.Buttons.LEFT))
				|| Gdx.input.isTouched()))
		{
			stillTouched = 1;
			Player.initJump(batch);
		}
		if (Player.getY() < Player.getRealWidth() * 4 && (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isTouched()) && stillTouched == 1)
			Player.setTest(6);
		else
			stillTouched = 0;
		if (Player.isJumping() == 1)
			Player.jump();
		if (Player.isJumping() == 1 && Player.getY() <= 100)
		{
			Player.endJump(batch);
			saved = current + 64;
		}
		if (current - 48 > saved || saved == 0)
		{
			saved = current;
			if (Player.isJumping() == 0)
				Player.runRight();
		}
		Player.check_collision(listObstacles);
	}

	@Override
	public void render () {

		listObstacles = updatePlatforms(listObstacles);
		playerMovement(listObstacles);
		it = listObstacles.listIterator();
		current =  System.currentTimeMillis();
		batch.begin();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		Background.print(batch);
		while (it.hasNext())
		{
			Obstacles Current = it.next();
			Current.print(batch, obst1);
		}
		Player.print(batch);
		batch.end();

	}
	
	@Override
	public void dispose () {

		batch.dispose();
		Background.getForest().dispose();
		Background.getMountains().dispose();
		Background.getGround().dispose();
		obst1.dispose();
		Player.getImg().dispose();
	}
}
