package com.thecherno.rain.entity.mob;

class Car extends Mob implements Runnable {

	private Sprite sprite;
	private boolean moving = false;
	private Move move;

	public Car (){
		this.move = Move;
	}

	public void update() {
		xa = 0, ya = 0;

		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;

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
			sprite = Sprite.car_forward;
		}
		if(dir == 1) {
			sprite = Sprite.car_side;
		}
		if(dir == 2) {
			sprite = sprite.car_back;
		}
		if(dir == 3) {
			sprite = sprite.car_side;
			flip = 1
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}

	moveCar(int enter,int leave) {
		boolean onTown = true;
		if(enter == 0) {//this indicate that it enteres from the top
			this.x = 250;
			this.y = 0;
			do {
				setMove(0, 1, 0, 0);
				this.update();
				this.render();
				if(y > 507)
					onTown = false;
			}while(onTown);
		} else if(enter == 1) {//this indicate that it enteres from the right
			this.x = 571;
			this.y = 250;
			do {
				setMove(0, 0, 0, 1);
				this.update();	
				this.render();
				if(x < 0)
					onTown = false;
			}while(onTown);
		} else if(enter == 2) {//this indicate that it enteres from the bottom
			this.x = 250;
			this.y = 0;
			do {
				setMove(1, 0, 0, 0);
				this.update();	
				this.render();
				if(y < 0)
					onTown = false;
			}while(onTown);
		} else if(enter == 3) {//this indicate that it enteres from the right
			this.x = 0;
			this.y = 250;
			do {
				setMove(0, 0, 0, 1);
				this.update();	
				this.render();
				if(x > 507)
					onTown = false;
			}while(onTown);
		}
	}

	public void run () {
		int entre = random.nextInt(3);
		do {
			int leave = random.nextInt(3);
		}while(entre == leave); 
		moveCar(entre,leave); 
		return; 
	} 
} 
class Move { 
	private boolean up; 
	private boolean down; 
	private boolean right; 
	private boolean left; 

	public boolean setMove(boolean up, boolean down, boolean right, boolean left){
		this.up = up;
		this.down = down;
		this.right = right;
		this.left = left;
	} 
}
