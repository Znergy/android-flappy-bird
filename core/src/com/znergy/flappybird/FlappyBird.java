package com.znergy.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background; // just an image texture = image
	Texture[] birds;
	int flapState;

	/** Happens on load */
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
	}

	/** Happens continously as the app runs
	 *  batch.draw(image, x coor, y coor, width, height)
	 *  Repeatedly display the background
	 */
	@Override
	public void render () {
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if(flapState == 0) {
			batch.draw(birds[flapState], (Gdx.graphics.getWidth() / 2) - (birds[flapState].getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (birds[flapState].getHeight() / 2));
			flapState = 1;
		} else {
			batch.draw(birds[flapState], (Gdx.graphics.getWidth() / 2) - (birds[flapState].getWidth() / 2), (Gdx.graphics.getHeight() /2) - (birds[flapState].getHeight() / 2));
			flapState = 0;
		}
		batch.end();
	}
}
