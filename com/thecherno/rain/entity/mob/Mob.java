package com.thecherno.rain.entity.mob;

import java.util.ArrayList;

import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.entity.mob.Car;
import com.thecherno.rain.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite_up = Sprite.car_up;
	protected Sprite sprite_down = Sprite.car_down;
	protected Sprite sprite_side = Sprite.car_side;

	protected int dir = 0;
	protected boolean moving = false;
	protected ArrayList<Car> cars_list;

	public void move(int xa, int ya) {
		if(ya < 0) dir = 0;
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;

		if(!collision(xa, ya, dir)) {
			x += xa;
			y = y + ya;
			if(ya == -1)
			System.out.println(y);
		}
	}

	public void update() {
	}

	public void render() {
	}

	private boolean collision(int xa, int ya, int dir) {
		Sprite current_sprite;
		Mob test_car_col ;
		for(int counter = 0; counter < this.cars_list.size(); counter++){
			test_car_col = cars_list.get(counter);
			if(dir == 1 || dir == 3)
				current_sprite = cars_list.get(counter).sprite_side;
			else if(dir == 2)
				current_sprite = cars_list.get(counter).sprite_down;
			else
				current_sprite = cars_list.get(counter).sprite_up;
			if( (xa+x)<=(test_car_col.x+ current_sprite.width) && (xa+x)>=(test_car_col.x) && (ya+y)<=(test_car_col.y+current_sprite.height) && (ya+y)>=test_car_col.y)
				return true;
		}
		return false;
	}
}
