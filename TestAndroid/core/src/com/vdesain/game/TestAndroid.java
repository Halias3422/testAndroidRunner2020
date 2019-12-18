package com.vdesain.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestAndroid extends ApplicationAdapter {
	SpriteBatch batch;
	Player 		Player;
	static int	saved;
	static int	current;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Player = new Player();
		saved = 0;
		current = 0;
	}

	@Override
	public void render () {

		if (saved == 0)
			saved = (int) System.currentTimeMillis();
		if (Player.isJumping() == 0 && Gdx.input.isButtonJustPressed((Input.Buttons.LEFT)))
			Player.initJump();
		else if (Player.isJumping() == 1 && Player.getVelocity() == -30)
			Player.endJump();
		if (current - 32 > saved || saved == 0)
		{
			saved = current;
			if (Player.isJumping() == 0)
				Player.runRight();
			else if (Player.isJumping() == 1 && Player.getVelocity() > -30) {
				Player.addToVelocity(-2);
				Player.setY(Player.getY() + Player.getVelocity());
			}

		}
		current = (int) System.currentTimeMillis();
		batch.begin();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		Player.print(batch);
		batch.end();

	}
	
	@Override
	public void dispose () {

		batch.dispose();
		Player.getImg().dispose();
	}
}
