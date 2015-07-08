package com.thecherno.rain.entity.projectile;

import com.thecherno.rain.entity.spawner.ParticleSpawner;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;

public class HeroProjectile extends Projectile {

	public static final int FIRE_RATE = 15;
	
	public HeroProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.projectile_hero;
		nx = speed * Math.cos(angle); //We're using vectors (angle, length) using sin and cos to work out x and y. I don't quite get it.
		ny = speed * Math.sin(angle);
	}

	public void update() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 7, 5, 4)){
			level.add(new ParticleSpawner((int) x, (int) y, 100, 50, level));
			remove();
		}
		move();
	}
	
	public void move() {
			x += nx;
			y += ny;
		if (distance() > range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin -y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int)x - 12, (int)y - 2, this);  // these are being cast as integers in order to make the function work
	}													//but the original vars are doubles so that they create more precise angles
	
}
