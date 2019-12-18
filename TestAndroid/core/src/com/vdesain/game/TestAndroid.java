package com.vdesain.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestAndroid extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int x;
	int	saved;
	int	current;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("runner1.png");
		x = 64;
		saved = 0;
		current = 0;
	}

	@Override
	public void render () {
		if (saved == 0)
			saved = (int) System.currentTimeMillis();
		if (current - 48 > saved) {
			saved = current;
			x = x + 32;
			if (x > 225)
				x = 64;
			System.out.print("coucou");
		}
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, Gdx.graphics.getWidth() / 2 - 32, 0, 64, 110, x, 0, 32, 55, false, false);
		batch.end();

		current = (int) System.currentTimeMillis();
		System.out.println(current);
		System.out.print(" = current --- saved = ");
		System.out.println(saved);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
