package com.thecherno.rain.entity.mob;

import com.thecherno.rain.Game;
import com.thecherno.rain.entity.projectile.HeroProjectile;
import com.thecherno.rain.entity.projectile.Projectile;
import com.thecherno.rain.graphics.AnimatedSprite;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.SpriteSheet;
import com.thecherno.rain.input.Keyboard;
import com.thecherno.rain.input.Mouse;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private Sprite animSpriteTest;
	private int anim;
	private boolean walking = false; 
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	private AnimatedSprite test = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	//DEBUG FUNCTION-----------------
	private void testAnimSprite(AnimatedSprite testSprite){
		if (testSprite.pixels == null) System.out.println(testSprite + "pixels[] is NULL");
	}
	//DEBUG FUNCTION-----------------
	Projectile p;
	private int fireRate = 0;
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward;
		animSprite = down;
		}
	
	//Use if the player needs to spawn at a specific location
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_back;
		fireRate = HeroProjectile.FIRE_RATE;
	}
	
	public void update() {
		test.update();
		if (fireRate > 0) fireRate--;
		int xa = 0, ya = 0;
		if (anim < 7500) anim++;
		else anim = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
		clear();
		updateShooting();
	}
	
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
		
	}

	//Calculates the angle between the player and the pointer and then shoots at that angle
	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth()/2;	//mouse location on the screen minus center of screen (player location). Player offsets may be dealt with later.
			double dy = Mouse.getY() - Game.getWindowHeight()/2;
			double dir = Math.atan2(dy, dx); 	//Arc Tangent. I'm not 100% on the math here.
			
			shoot(x, y, dir);
			fireRate = HeroProjectile.FIRE_RATE;
		}
		
	}

	// if statements pull correct sprites for each direction. if (walking) adds animation by pulling different walking sprites
	public void render(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim %20 > 10) {
					sprite = Sprite.player_forward_1;
				}
				else {
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_left;
			if (walking) {
				if (anim %20 > 10) {
					sprite = Sprite.player_left_1;
				}
				else {
					sprite = Sprite.player_left_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_back;
			if (walking) {
				if (anim %20 > 10) {
					sprite = Sprite.player_back_1;
				}
				else {
					sprite = Sprite.player_back_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_right;
			if (walking) {
				if (anim %20 > 10) {
					sprite = Sprite.player_right_1;
				}
				else {
					sprite = Sprite.player_right_2;
				}
			}
		}
//		test = test.getSprite();
//		testAnimSprite(down);
		screen.renderPlayer(x - 16, y - 16, sprite);

	}
	
}
