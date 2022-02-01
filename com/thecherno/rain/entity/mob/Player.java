package com.thecherno.rain.entity.mob;

import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.input.Keyboard;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
	}

	public void update() {
		int xa = 0, ya = 0;

		if(anim < 7500) anim++;
		else anim = 0;

		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;

		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;	
		}
	}

	public void render(Screen screen) {
		int flip = 0;

		if(dir == 0) {
			sprite = Sprite.car_down;
		}

		if(dir == 1) {
			sprite = Sprite.car.side;
		}

		if(dir == 2) {
			sprite = Sprite.car_up;
		}

		if(dir == 3) {
			sprite = Sprite.car_side;
			flip = 1;
		}

		screen.renderCar(this, sprite, flip);
	}
}
