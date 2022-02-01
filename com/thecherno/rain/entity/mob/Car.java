package com.thecherno.rain.entity.mob;

import java.util.ArrayList;

import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.Screen;

public class Car extends Mob implements Runnable {

	private Sprite sprite;
	private boolean moving = false;
	private Move move;

	public Car (ArrayList<Car> cars_list){
		this.move = new Move();
		this.cars_list = cars_list;
	}

	public void update() {
		int xa = 0;
		int ya = 0;

		if(this.move.up) ya--;
		if(this.move.down) ya++;
		if(this.move.left) xa--;
		if(this.move.right) xa++;

		if(xa != 0 || ya != 0) {
			move(xa, ya);
			moving = true;
		} else {
			moving = false;
		}
	}

	public void render(Screen screen) {
		int flip = 0;

		if(dir == 0) {
			sprite = Sprite.car_up;
		}
		if(dir == 1) {
			sprite = Sprite.car_side;
		}
		if(dir == 2) {
			sprite = sprite.car_down;
		}
		if(dir == 3) {
			sprite = sprite.car_side;
			flip = 1;
		}
		screen.renderCar(this, sprite, flip);
	}

	public void moveCar(int enter,int leave) {
		boolean onTown = true;
		if(enter == 0) {//this indicate that it enteres from the top
			this.x = 250;
			this.y = 0;
			do {
				this.move.setMove(false, true, false, false);
				this.update();
				if(y > 507)
					onTown = false;
			}while(onTown);
		} else if(enter == 1) {//this indicate that it enteres from the right
			this.x = 571;
			this.y = 250;
			do {
				this.move.setMove(false, false, false, true);
				this.update();	
				if(x < 0)
					onTown = false;
			}while(onTown);
		} else if(enter == 2) {//this indicate that it enteres from the bottom
			this.x = 250;
			this.y = 0;
			do {
				this.move.setMove(true, false, false, false);
				this.update();	
				if(y < 0)
					onTown = false;
			}while(onTown);
		} else if(enter == 3) {//this indicate that it enteres from the right
			this.x = 0;
			this.y = 250;
			do {
				this.move.setMove(false, false, true, false);
				this.update();	
				if(x > 507)
					onTown = false;
			}while(onTown);
		}
	}

	public void run () {
		int entre = random.nextInt(3);
		int leave;
		do {
			leave = random.nextInt(3);
		}while(entre == leave); 
		moveCar(entre,leave);
		System.out.println("destroyed");
	}
} 
class Move { 
	public  boolean up; 
	public  boolean down; 
	public  boolean right; 
	public  boolean left; 

	public void setMove(boolean up, boolean down, boolean right, boolean left){
		this.up = up;
		this.down = down;
		this.right = right;
		this.left = left;
	} 
}
