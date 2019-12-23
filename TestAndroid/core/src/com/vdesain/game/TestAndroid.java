package com.vdesain.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.LinkedList;
import java.util.ListIterator;

public class TestAndroid extends ApplicationAdapter {
	SpriteBatch batch;
	Player 		Player;
	Mountains	Mountains;
	Forest		Forest;
	Ground		Ground;
	LinkedList<Obstacles> 	listObstacles;
	ListIterator<Obstacles> it;
	Texture		obst1;
	long		saved;
	long		current;
	int  		stillTouched;
	int			obstaclesSpeed;
	int			obstacleOffsetMax;
	private int	score;
	private int levelCap;
	private BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Player = new Player();
		Mountains = new Mountains("mountains");
		Forest = new Forest("forest");
		Ground = new Ground("ground");
		font = new BitmapFont();
		listObstacles = new LinkedList<Obstacles>();
		initObstacles();
		saved = System.currentTimeMillis();
		current = 0;
		stillTouched = 0;
		score = 0;
		levelCap = 10;
		obstaclesSpeed = 10;
		obstacleOffsetMax = 900;
	}

	public void initObstacles()
	{
		obst1 = new Texture("ground2.png");

		listObstacles.add(new Obstacles(null, Player, obstacleOffsetMax));
		for (int i = 1; i < 10; i++)
			listObstacles.addLast(new Obstacles(listObstacles.getLast(), Player, obstacleOffsetMax));
	}

	public LinkedList updatePlatforms(LinkedList<Obstacles> listObstacles)
	{
		if (listObstacles.getFirst().getEndX() < 0)
		{
			listObstacles.removeFirst();
			listObstacles.addLast(new Obstacles(listObstacles.getLast(), Player, obstacleOffsetMax));
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

	public void checkIfLevelPassed()
	{
		if (score > levelCap)
		{
			Mountains.increaseSpeed();
			Forest.increaseSpeed();
			if (obstacleOffsetMax > 400)
			obstacleOffsetMax -= 50;
			obstaclesSpeed += 1;
			levelCap += 10;
		}

	}

	@Override
	public void render () {

		listObstacles = updatePlatforms(listObstacles);
		playerMovement(listObstacles);
		if (Player.getLife() <= 0)
		{
			dispose();
			create();
		}
		it = listObstacles.listIterator();
		current =  System.currentTimeMillis();
		batch.begin();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		Mountains.print(batch);
		Forest.print(batch);
		while (it.hasNext())
		{
			Obstacles Current = it.next();
			if (Current.getCrossed() == 0)
				score += Current.checkIfCrossed(Player);
			Current.print(batch, obst1, obstaclesSpeed);
		}
		Ground.print(batch);
		Player.print(batch);
		font.getData().setScale(4, 4);
		font.draw(batch, "SCORE : " + score, 10, Gdx.graphics.getHeight() - 100);
		batch.end();
		checkIfLevelPassed();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		Mountains.getText().dispose();
		Forest.getText().dispose();
		Ground.getText().dispose();
		font.dispose();
		obst1.dispose();
		Player.getImg().dispose();
	}
}
