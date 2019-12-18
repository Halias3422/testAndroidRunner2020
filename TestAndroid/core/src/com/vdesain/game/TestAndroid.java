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
	long		saved;
	long		current;
	int  		stillTouched;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Player = new Player();
		saved = System.currentTimeMillis();
		current = 0;
		stillTouched = 0;
	}

	public void playerMovement()
	{

		if (Player.isJumping() == 0 && (Gdx.input.isButtonJustPressed((Input.Buttons.LEFT))
				|| Gdx.input.isTouched())) {
			stillTouched = 1;
			Player.initJump();
		}
		else if (Player.isJumping() == 1 && Player.getY() == 0)
			Player.endJump();
//		System.out.printf("JUMP FORCE = %d stilltouched = %d\n", Player.getJumpForce(), stillTouched);

		if (current - 32 > saved || saved == 0)
		{
			saved = current;
			if (Player.getJumpForce() > 2 && (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isTouched()) && stillTouched == 1)
				Player.increaseJumpForce(15);
			else {
			    if (Player.getVelocity() > 0)
					System.out.printf("jumpforce = %d\n", Player.getJumpForce());
				stillTouched = 0;
			}
			if (Player.isJumping() == 0)
				Player.runRight();
			else if (Player.isJumping() == 1 && Player.getY() > 0) {
				Player.jump();
			}
		}
	}

	@Override
	public void render () {
		playerMovement();
		current =  System.currentTimeMillis();
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
