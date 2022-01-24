package com.thecherno.rain.graphics;

import java.util.Random;

import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.level.OneMapLevel;

public class Screen {

	public int width, height;	
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];

	}

	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	private void renderOneMap (OneMapLevel Map) {
		int width = Map.getWidth();
		int height = Map.getHeight();
		for(int y = 0; y < width; y++) {
			for(int x = 0; x < height; x++) {
				pixels[x + y * width] = Map.pixels[x + y * width]; 
			}
		}
	}

	public void renderAll (OneMapLevel Map) {
		renderOneMap(Map);
	}
}
