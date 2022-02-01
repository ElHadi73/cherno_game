package com.thecherno.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public int[] pixels;
	public int width;
	private int height;

	public static SpriteSheet flights = new SpriteSheet("/res/textures/carrfor/4lights.png", 64, 64);
	public static SpriteSheet light_right = new SpriteSheet("/res/textures/carrfor/light_right.png", 37, 47);
	public static SpriteSheet stright_green = new SpriteSheet("/res/textures/carrfor/green.png", 8, 7);
	public static SpriteSheet stright_red = new SpriteSheet("/res/textures/carrfor/red.png", 8, 7);
	public static SpriteSheet small_green = new SpriteSheet("/res/textures/carrfor/green_small.png", 5, 7);
	public static SpriteSheet small_red = new SpriteSheet("/res/textures/carrfor/red_small.png", 5, 7);

	public static SpriteSheet car_up = new SpriteSheet("/res/textures/carrfor/blue_bottom_to_top.png", 17, 39);
	public static SpriteSheet car_down = new SpriteSheet("/res/textures/carrfor/blue_top_to_bottom.png", 17, 39);
	public static SpriteSheet car_side = new SpriteSheet("/res/textures/carrfor/blue_side.png", 17, 39);

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		load();
	}


	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			System.out.println(path);
			e.printStackTrace();
		}
	}

}

