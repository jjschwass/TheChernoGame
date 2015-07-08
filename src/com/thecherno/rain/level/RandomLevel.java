package com.thecherno.rain.level;

import java.util.Random;

public class RandomLevel extends Level{

	private static final Random random = new Random();
	
	public RandomLevel(int width, int height) {
		super(width, height);   //super means that it will execute the constructor in the Level.java class
	}
	
	//Protected means that if a RandomLevel is specified when generating a level, this code will override the code in Level.java
	protected void generateLevel()  {
		for (int y = 0; y < height; y++)  {
			for (int x = 0; x < width; x++)  {
				tilesInt[x + y * width] = random.nextInt(4); //Generate a 0, 1, 2, or 3
			}
		}
	}
	
}
