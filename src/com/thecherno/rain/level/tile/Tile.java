package com.thecherno.rain.level.tile;

import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.level.tile.spawn_level.SpawnFloorTile;
import com.thecherno.rain.level.tile.spawn_level.SpawnFlowerTile;
import com.thecherno.rain.level.tile.spawn_level.SpawnGrassTile;
import com.thecherno.rain.level.tile.spawn_level.SpawnRockTile;
import com.thecherno.rain.level.tile.spawn_level.SpawnWallTile;
import com.thecherno.rain.level.tile.spawn_level.SpawnWaterTile;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_flower = new SpawnFlowerTile(Sprite.spawn_flower);
	public static Tile spawn_rock = new SpawnRockTile(Sprite.spawn_rock);
	public static Tile spawn_cbrick = new SpawnWallTile(Sprite.spawn_cbrick);
	public static Tile spawn_brick = new SpawnWallTile(Sprite.spawn_brick);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
	
	public final static int COL_SPAWN_GRASS = 0xff00ff00;
	public final static int COL_SPAWN_FLOWER = 0; //unused
	public final static int COL_SPAWN_ROCK = 0; //unused
	public final static int COL_SPAWN_CBRICK = 0; //unused
	public final static int COL_SPAWN_BRICK = 0xff808080;
	public final static int COL_SPAWN_WATER = 0xff0026FF;
	public final static int COL_SPAWN_FLOOR = 0xff7f6a00;
	
	public Tile(Sprite sprite)  {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen)  {
	}
	
	//For collision. By default, all tiles will not be solid. All tiles that the player should collide with require this method to be overridden.
	public boolean solid()  {
		return false;
	}
	
}
