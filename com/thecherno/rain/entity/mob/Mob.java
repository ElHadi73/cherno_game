package com.thecherno.rain.entity.mob;

import java.util.ArrayList;

import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite_up;
	protected Sprite sprite_down;
	protected Sprite sprite_right;
	protected Sprite sprite_left;

	protected int dir = 0;
	protected boolean moving = false;
	protected ArrayList<Mob> cars_list;

	public void move(int xa, int ya) {
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;

		if(!collision(xa, ya, dir)) {
			x += xa;
			y += ya;
		}
	}

	public void update() {
	}

	public void render() {
	}

	private boolean collision(int xa, int ya, int dir) {
		for(int counter = 0; counter < this.cars_list.size(); counter++){
			if(dir == 0)
				Sprite current_sprite = cars_list.get(counter).sprite_up;
			else if(dir == 1)
				Sprite current_sprite = cars_list.get(counter).sprite_right;
			else if(dir == 2)
				Sprite current_sprite = cars_list.get(counter).sprite_down;
			else if(dir == 4)
				Sprite current_sprite = cars_list.get(counter).sprite_left;
			if( (xa+x)<=(car.x+ current_sprite.width) && (xa+x)>=(car.x) && (ya+y)<=(car.y+current_sprite.height) && (ya+y)>=car.y)
				return true;
		}
		return false;
	}
}
