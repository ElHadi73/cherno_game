package com.thecherno.rain.graphics;

import java.util.Random;
import java.util.ArrayList;

import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.level.OneMapLevel;
import com.thecherno.rain.entity.Lights;
import com.thecherno.rain.entity.mob.Car;

public class Screen {

	public int width, height;	
	public int[] pixels;
	public ArrayList<Car> cars;

	public Screen(int width, int height,ArrayList<Car> cars) {
		this.width = width;
		this.height = height;
		this.cars = cars;

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

	public void renderLights(Lights lights) {
		int xs_1 = 255;
		int ys_1 = 245;
		int xs_2 = 272;
		int ys_2 = 260;
		for(int y = 0; y < lights.flights.height; y++) {
			for(int x = 0; x < lights.flights.width; x++) {
				if(lights.flights.pixels[x+y*lights.flights.width] != 0xffff00ff)
					pixels[xs_1+x + (ys_1 + y) * width] = lights.flights.pixels[x + y * lights.flights.width];
				if(x < 37 && y < 47 && lights.light_right.pixels[x+y*lights.light_right.width] != 0xffff00ff) {
					pixels[270+x+ ( y+170 ) * width] = lights.light_right.pixels[x + y * lights.light_right.width];
				}
			}
		}
		if(lights.light_1){
			for(int y = 0; y < lights.stright_green.height; y++)
				for(int x = 0; x < lights.stright_green.width; x++)
					if(lights.stright_green.pixels[x+y*lights.stright_green.width] != 0xffff00ff) {
						pixels[xs_2+x + (ys_2 + y) * width] = lights.stright_green.pixels[x + y * lights.stright_green.width];
						pixels[xs_2+x+ 24+ (ys_2 + y + 23) * width] = lights.stright_green.pixels[x + y * lights.stright_green.width];
						if(x < 5 && lights.small_red.pixels[x+y*lights.small_red.width] != 0xffff00ff) {
							pixels[xs_2+x -7+ (ys_2 + y - 10) * width] = lights.small_red.pixels[x + y * lights.small_red.width];
							pixels[x+ 302 + (y + 193) * width] = lights.small_red.pixels[x + y * lights.small_red.width];
						}
					}
			return;
		}
		for(int y = 0; y < lights.stright_red.height; y++)
			for(int x = 0; x < lights.stright_red.width; x++)
				if(lights.stright_red.pixels[x+y*lights.stright_red.width] != 0xffff00ff) {
					pixels[xs_2+x + (ys_2 + y - 8) * width] = lights.stright_red.pixels[x + y * lights.stright_red.width];
					pixels[xs_2+x+ 24+ (ys_2 + y + 15) * width] = lights.stright_red.pixels[x + y * lights.stright_red.width];
					if(x < 5 && lights.small_green.pixels[x+y*lights.small_green.width] != 0xffff00ff) {
						pixels[xs_2+x -7+ (ys_2 + y - 2) * width] = lights.small_green.pixels[x + y * lights.small_green.width];
						pixels[x+ 302 + (y + 200) * width] = lights.small_green.pixels[x + y * lights.small_green.width];
					}
				}
	}

	public void renderAll(OneMapLevel Map,Lights lights) {
		renderOneMap(Map);
		renderCars();
		renderLights(lights);
	}

	public void renderCars() {
		for(int counter = 0; counter < this.cars.size(); counter++){
			this.cars.get(counter).render(this);
		}
	}

	public void renderCar(Car car, Sprite sprite, int flip) {
		int xs;
		for(int y = 0; y < sprite.height; y++) {
			if((car.y+y) < 0 || (car.y+y) >= 507)
				continue;
			for(int x = 0; x < sprite.width; x++){
				xs = x;
				if(flip == 1)
					xs = 38 - x;
				if( (car.x+xs) < 0 || (car.x+xs) >= 507)
					continue;
				int col = sprite.pixels[x + y * sprite.width];
				if(col != 0xffff00ff)
					pixels[car.x +xs + (car.y+y) * width] = col;
			}
		}
	}
}
