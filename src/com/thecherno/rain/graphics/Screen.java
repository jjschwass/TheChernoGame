package com.thecherno.rain.graphics;

import java.util.Random;

import com.thecherno.rain.entity.mob.Player;
import com.thecherno.rain.entity.projectile.Projectile;
import com.thecherno.rain.level.tile.Tile;

public class Screen {

        public int width, height;        
        public int[] pixels;
        
        public final int MAP_SIZE = 64;
        public final int MAP_SIZE_MASK = MAP_SIZE - 1;
        
        public int xOffset, yOffset;
        
        public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
        
        private Random random = new Random();
        
        public Screen(int width, int height) {
                this.width = width;
                this.height = height;
                
                pixels = new int[width * height];
                
                for(int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
                        tiles[i] = random.nextInt(0xFFFFFF);
                }
        }
        
        public void clear() {
                for(int i = 0; i < pixels.length; i++) {
                        pixels[i] = 0;
                }
        }
        
        public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
        	if (fixed) {
            	xp -= xOffset;
            	yp -= yOffset;
        	}
        	for (int y = 0; y < sheet.HEIGHT; y++) {
        		int ya = y + yp;
            	for (int x = 0; x < sheet.WIDTH; x++) {
            		int xa = x + xp;
            		if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
            		pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
            	}
        	}
        }
        
        public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
        	if (fixed) {
            	xp -= xOffset;
            	yp -= yOffset;
        	}
        	for (int y = 0; y < sprite.getHeight(); y++) {
        		int ya = y + yp;
            	for (int x = 0; x < sprite.getWidth(); x++) {
            		int xa = x + xp;
            		if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
            		pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
            	}
        	}
        }
        
        
        //xp and yp are X Position and Y Position
        public void renderTile(int xp, int yp, Tile tile)  {
        	xp -= xOffset;
        	yp -= yOffset;
        	for (int y = 0; y < tile.sprite.SIZE; y++) {	//uses tile.sprite.SIZE just in case we decide to change the tile size later on
        		int ya = y + yp; 							//y is the pixel currently being rendered. yp is a placeholder for a variable that includes the offset
        		for (int x = 0; x < tile.sprite.SIZE; x++) {
        			int xa = x + xp; 
        			if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >=height) break;   //avoids arrayindexoutofbounds errors or using up lots of RAM
        			if (xa < 0) xa = 0;
        			pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
        		}
        	}
        }
        
        public void renderPlayer(int xp, int yp, Sprite sprite) {
        	xp -= xOffset;
        	yp -= yOffset;
        	for (int y = 0; y < 32; y++) {	//uses tile.sprite.SIZE just in case we decide to change the tile size later on
        		int ya = y + yp; 							//y is the pixel currently being rendered. yp is a placeholder for a variable that includes the offset
        		for (int x = 0; x < 32; x++) {
        			int xa = x + xp;
        			if (xa < -32 || xa >= width || ya < 0 || ya >=height) break;   //avoids arrayindexoutofbounds errors or using up lots of RAM
        			if (xa < 0) xa = 0;
        			int color = sprite.pixels[x + y * 32]; 						// assign each pixel to a variable for the next step
        			if (color != 0xffff00ff) pixels[xa + ya * width] = color;	// ignores pink pixels on sprite sheet when rendering sprites
        		}
        	}
        }
        
        //Called in Level.render()
        public void setOffset(int xOffset, int yOffset) {
        	this.xOffset = xOffset;
        	this.yOffset = yOffset;
        }

		public void renderProjectile(int xp, int yp, Projectile p) {
			// TODO Auto-generated method stub
        	xp -= xOffset;
        	yp -= yOffset;
        	for (int y = 0; y < p.getSpriteSize(); y++) {
        		int ya = y + yp; 							//y is the pixel currently being rendered. yp is a placeholder for a variable that includes the offset
        		for (int x = 0; x < p.getSpriteSize(); x++) {
        			int xa = x + xp; 
        			if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >=height) break;   //avoids arrayindexoutofbounds errors or using up lots of RAM
        			if (xa < 0) xa = 0;
        			int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
        			if (col != 0xffff00ff)
        			pixels[xa + ya * width] = col;
        		}
        	}
		}
}
