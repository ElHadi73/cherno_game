package com.thecherno.rain.entity;

import java.util.ArrayList;

import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.Game;
import com.thecherno.rain.entity.Sphr;
import java.util.Random;

public class Car implements Runnable {

	private Sprite sprite;
	private boolean moving = false;
	private Move move;
	private Thread thread;
	public int enter;

	public int x, y;
	protected final Random random = new Random();

	protected Sprite sprite_up = Sprite.car_up;
	protected Sprite sprite_down = Sprite.car_down;
	protected Sprite sprite_side = Sprite.car_side;

	protected int dir = 0;
	protected ArrayList<Car>[] cars_list;


	public Car (ArrayList<Car>[] cars_list,int enter){
		super();
		this.move = new Move();
		this.cars_list = cars_list;
		this.enter = enter;
	}
	public void move(int xa, int ya) {
		if(ya < 0) dir = 0;
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;

		if(!collision(xa, ya, dir)) {
			x += xa;
			y += ya;
		}
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
		flip = set_sprite();
		screen.renderCar(this, sprite, flip);
	}

	private boolean collision(int xa, int ya, int dir) {
		this.set_sprite();
		Sprite current_sprite;
		Car test_car_col ;
		for(int counter = 0; counter < this.cars_list[this.enter].size(); counter++){
			test_car_col = cars_list[this.enter].get(counter);
			if(test_car_col == this) continue;
			if(enter == 3 && (this.x <= test_car_col.x)){
				current_sprite = test_car_col.sprite_side;
				if((xa+x+3+this.sprite.width)>=(test_car_col.x))
					return true;
			}else if(enter == 1 && (this.x >= test_car_col.x)){
				current_sprite = test_car_col.sprite_side;
				if((xa+x-3)<=(test_car_col.x+current_sprite.width))
					return true;
			}else if(enter == 0 && (this.y <= test_car_col.y)){
				current_sprite = test_car_col.sprite_down;
				if((ya+y+3+this.sprite.height)>=(test_car_col.y))
					return true;
			}else if(enter == 2 && (this.y >= test_car_col.y)){
				current_sprite = test_car_col.sprite_up;
				if((ya+y-3)<=(test_car_col.y+current_sprite.height))
					return true;
			}
		}
		return false;
	}


	public int set_sprite() {
		int flip = 0;
		if(dir == 0) {
			sprite = Sprite.car_up;
		}
		if(dir == 1) {
			sprite = Sprite.car_side;
			flip = 1;
		}
		if(dir == 2) {
			sprite = sprite.car_down;
		}
		if(dir == 3) {
			sprite = sprite.car_side;
		}
		return flip;
	}


	public void setthread(Thread thread){
		this.thread = thread;
	}

	private void time_wait(){
		try {
			thread.sleep(10);
		} catch(InterruptedException ex){
			Thread.currentThread().interrupt();
		}
	}

	public void moveCar(int enter,int leave) {
		int ins_hw = 120;
		int t = 0;
		boolean onTown = true;
		if(enter == 0) {//this indicate that it enteres from the top
			this.x = 229;
			this.y = -39;
			do {
				this.move.setMove(false, true, false, false);
				time_wait();
				this.update();
				if(y == 158){
					try {
						Sphr.Feu11.acquire();
					} catch (InterruptedException e) {
					} finally {
						for(int i=0;i<ins_hw;i++){
							this.move.setMove(false, true, false, false);
							time_wait();
							this.update();
						}
						Sphr.Feu11.release();
					}
				}
				if(y > 571)
					onTown = false;
			}while(onTown);
		} else if(enter == 1) {//this indicate that it enteres from the right
			this.x = 571;
			this.y = 230;
			do {
				this.move.setMove(false, false, false, true);
				time_wait();
				this.update();	
				if(x == 310){
					try {
						Sphr.Feu21.acquire();
					} catch (InterruptedException e) {
					} finally {
						for(int i=0;i<ins_hw;i++){
							this.move.setMove(false, false, false, true);
							time_wait();
							this.update();
						}
						Sphr.Feu21.release();
					}
				}
				if(x < -sprite.width)
					onTown = false;
			}while(onTown);
		} else if(enter == 2) {//this indicate that it enteres from the bottom
			this.x = 255;
			this.y = 571;
			do {
				this.move.setMove(true, false, false, false);
				time_wait();
				this.update();	
				if(y == 307){
					try {
						Sphr.Feu12.acquire();
					} catch (InterruptedException e) {
					} finally {
						for(int i=0;i<ins_hw;i++){
							this.move.setMove(true, false, false, false);
							time_wait();
							this.update();
						}
						Sphr.Feu12.release();
					}
				}
				if(y < -sprite.height)
					onTown = false;
			}while(onTown);
		} else if(enter == 3) {//this indicate that it enteres from the right
			this.x = -39;
			this.y = 250;
			do {
				this.move.setMove(false, false, true, false);
				time_wait();
				this.update();
				if(x == 160){
					try {
						Sphr.Feu22.acquire();
					} catch (InterruptedException e) {
					} finally {
						for(int i=0;i<ins_hw;i++){
							this.move.setMove(false, false, true, false);
							time_wait();
							this.update();
						}
						Sphr.Feu22.release();
					}
				}
				if(x > 571)
					onTown = false;
			}while(onTown);
		}
	}

	public void run () {
		int leave;
		do {
			leave = random.nextInt(4);
		}while(this.enter == leave); 
		moveCar(this.enter,leave);
		cars_list[enter].remove(this);
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
