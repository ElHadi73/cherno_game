package com.thecherno.rain.level;

import com.thecherno.rain.graphics.Screen;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
	}

	protected void generateLevel() {
	}

	protected void loadLevel(String path) {	

	}

	private void time() {
	}

	public void update() {
	}
}
