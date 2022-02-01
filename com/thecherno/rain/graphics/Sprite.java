package com.thecherno.rain.graphics;

import com.thecherno.rain.graphics.SpriteSheet;

public class Sprite {

	private SpriteSheet sheet;

	public final int width, height;
	public int[] pixels;
	
	public static Sprite flights = new Sprite(64, 64, SpriteSheet.flights);
	public static Sprite light_right = new Sprite(37, 47, SpriteSheet.light_right);
	public static Sprite stright_green = new Sprite(8, 7, SpriteSheet.stright_green);
	public static Sprite stright_red = new Sprite(8, 7, SpriteSheet.stright_red);
	public static Sprite small_green = new Sprite(5, 7, SpriteSheet.small_green);
	public static Sprite small_red = new Sprite(5, 7, SpriteSheet.small_red);
	
	public static Sprite car_up = new Sprite(17, 39, SpriteSheet.car_up);
	public static Sprite car_down = new Sprite(17, 39, SpriteSheet.car_down);
	public static Sprite car_side = new Sprite(50, 19, SpriteSheet.car_side);

	public Sprite(int width, int height, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int color) {
		this.width = size;
		this.height = size;
		pixels = new int[size * size];
		setColor(color);
	}
	
	private void setColor(int color) {
		for(int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	private void load() {
				pixels= sheet.pixels;
			}
}
