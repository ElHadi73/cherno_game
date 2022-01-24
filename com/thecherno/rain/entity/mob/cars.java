package com.thecherno.rain.entity.mob;

class Car extends Mob implements Runnable {

	private Sprite sprite;
	private boolean moving = false;

	public Player (int entry){
		if (entry == 0) { // this is for the road where the car will be coming in to the map from
			this.x = 14;
			this.y = 14;
			this.sprite = Sprite.car_right;
		} else if(entry == 1) {
			this.x = 60;
			this.y = 69;
			this.sprite = sprite.car_forward;
		}
	}

	public void update() {
		xa = 0, ya = 0;

//		for(int i =0; i < 1000; i++) {

	run () {
	}

}
