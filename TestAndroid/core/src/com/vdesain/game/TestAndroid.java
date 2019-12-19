package com.vdesain.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestAndroid extends ApplicationAdapter {
	SpriteBatch batch;
	Player 		Player;
	Background	Background;
	long		saved;
	long		current;
	int  		stillTouched;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Player = new Player();
		Background = new Background();
		saved = System.currentTimeMillis();
		current = 0;
		stillTouched = 0;
	}

	public void playerMovement()
	{

		if (Player.isJumping() == 0 && (Gdx.input.isButtonJustPressed((Input.Buttons.LEFT))
				|| Gdx.input.isTouched()))
		{
			stillTouched = 1;
			Player.initJump(batch);
		}
		if (Player.isJumping() == 1)
			Player.jump();
		if (Player.isJumping() == 1 && Player.getY() <= 0)
		{
			System.out.printf("JE PASSE LA\n");
			Player.endJump(batch);
			saved = current + 48;
		}
		if (current - 48 > saved || saved == 0)
		{
			saved = current;
			if (Player.isJumping() == 0)
				Player.runRight();
		}
	}

	@Override
	public void render () {
		playerMovement();
		current =  System.currentTimeMillis();
		batch.begin();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		Background.print(batch);
		Player.print(batch);
		batch.end();

	}
	
	@Override
	public void dispose () {

		batch.dispose();
		Background.getForest().dispose();
		Background.getMountains().dispose();
		Background.getGround().dispose();
		Player.getImg().dispose();
	}
}
