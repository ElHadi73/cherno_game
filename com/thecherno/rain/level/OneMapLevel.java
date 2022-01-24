package com.thecherno.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OneMapLevel {

	private String path;
	protected int width, height;
	public int[] pixels;

	public OneMapLevel (String path) {
		this.path = path;
		loadLevel(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(OneMapLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			pixels = new int[w * h];
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch(IOException ex) {
			ex.printStackTrace();
			System.out.println("Exception Could not load level file!");
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
