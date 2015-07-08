package com.thecherno.rain.level;

import java.util.ArrayList;
import java.util.List;

import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.entity.particle.Particle;
import com.thecherno.rain.entity.projectile.Projectile;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Particle> particles = new ArrayList<Particle>();
	
	public static Level spawn = new SpawnLevel("/levels/spawn.png");
	
	public Level(int width, int height)  {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path)  {
		loadLevel(path);
		generateLevel();
	
	}
	
	protected void generateLevel() {
	}
	
	protected void loadLevel(String path)  {
	}
	
	public void update()  {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles; 
	}
	
	//For changing day to night and so on
	private void time()  {
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {   // x and y are the position of the entity that will collide with the tile. The offsets are there because the projectile is in the middle of the tile/
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4; // >> 4 is the same as / 16
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	//adds the "corner pins". Decides what to render.
	public void render(int xScroll, int yScroll, Screen screen)  {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x,y).render(x, y, screen);
				
				//tiles[x + y * 16].render(x, y, screen);
				
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else {
			entities.add(e);
		}
	}
	
	//Get and return a tile - See Sprite class
	// Grass = 0xFF00FF00
	// Flower = 0xFFFFFF00
	// Rock 0xFF7F7F00
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.COL_SPAWN_FLOOR) return Tile.spawn_floor;
		if (tiles[x + y * width] == Tile.COL_SPAWN_GRASS) return Tile.spawn_grass;
		if (tiles[x + y * width] == Tile.COL_SPAWN_FLOWER) return Tile.flower;
		if (tiles[x + y * width] == Tile.COL_SPAWN_ROCK) return Tile.spawn_rock;
		if (tiles[x + y * width] == Tile.COL_SPAWN_BRICK) return Tile.spawn_brick;
		if (tiles[x + y * width] == Tile.COL_SPAWN_CBRICK) return Tile.spawn_cbrick;
		if (tiles[x + y * width] == Tile.COL_SPAWN_WATER) return Tile.spawn_water;
		return Tile.voidTile;
	}
}
