package com.znergy.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background; // just an image texture = image
	Texture[] birds;
	Texture topTube;
	Texture bottomTube;
	int flapState = 0;
	float birdY = 0;
	float velocity = 0;

	int gameState = 0;
	float gravity = 1.7f;

	/** Happens on load */
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");

		birdY = (Gdx.graphics.getHeight() /2) - (birds[0].getHeight() / 2);
	}

	/** Happens continuously as the app runs
	 *  batch.draw(image, x coor, y coor, width, height)
	 *  Repeatedly display the background
	 */
	@Override
	public void render () {

		if(Gdx.input.justTouched()) {
			gameState = 1;
		}

		/** When user touches screen the height of bird flys up and birdY drops down
		 * this happens at an accelerated rate..
		 * gameState is active (meaning user already clicked screen, the bird will
		 * move upwards then drop, if the gameState isn't active then if the user clicks then
		 * the game will be active..
		 */
		if(gameState != 0) {

			/** Controls how high the bird will jump (higher neg. = higher jump) */
			if(Gdx.input.justTouched()) {
				velocity = -28;
			}
			/** Prevents from dropping off screen (reset to start pos. if falls off) */
			if(birdY > 0) {
				velocity += gravity;
				birdY -= velocity;
			} else {
				birdY = (Gdx.graphics.getHeight() /2) - (birds[0].getHeight() / 2);
				gameState = 0;
			}

		} else {

			if(Gdx.input.justTouched()) {
				gameState = 1;
			}
		}

		/** flapState switches continuously
		 * this gives the appearance of the bird flapping it's wings
		 * the current value (0 or 1) is then passed into birds array
		 */
		if (flapState == 0) {
			flapState = 1;
		} else {
			flapState = 0;
		}

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(topTube, Gdx.graphics.getWidth() - 350, Gdx.graphics.getHeight() - 850);
		batch.draw(bottomTube, Gdx.graphics.getWidth() - 350, -250);
		batch.draw(birds[flapState], (Gdx.graphics.getWidth() / 2) - (birds[flapState].getWidth() / 2), birdY);
		batch.end();
	}
}
