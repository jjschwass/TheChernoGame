package com.thecherno.rain.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.entity.particle.Particle;
import com.thecherno.rain.entity.projectile.HeroProjectile;
import com.thecherno.rain.entity.projectile.Projectile;
import com.thecherno.rain.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		//System.out.println("Size: " + level.getProjectiles().size());
		if (xa !=0 && ya !=0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa < 0) dir = 1;
		if (xa > 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0; 
		
		/*			0
		 * 		3		1
		 * 			2
		 * 
		 * Cardinal directions based upon numbers going clockwise.
		 */
		
		//if there's no collision, adjust x and y -1, 0, or 1 to determine move direction
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}
	
	public void update() {
	}
	
	protected void shoot(int x, int y, double dir){
		//dir *= 180 / Math.PI;
		//System.out.println("Angle: " + dir);
		Projectile p = new HeroProjectile(x, y, dir);
		level.add(p);
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 9 - 5) /16;
			int yt = ((y + ya) + c / 2 * 4 + 11) /16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render() {
	}

}
