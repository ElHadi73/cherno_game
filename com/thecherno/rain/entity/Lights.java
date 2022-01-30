package com.thecherno.rain.entity;

import com.thecherno.rain.graphics.Sprite;

import java.lang.Thread;

public class Lights extends Thread {

	private int timeScap = 5000;
	public int x1,y1,x2,y2;
	public boolean running = true;
	public Sprite flights = Sprite.flights;
	public Sprite stright_green = Sprite.stright_green;
	public Sprite stright_red = Sprite.stright_red;
	public Sprite small_green = Sprite.small_green;
	public Sprite small_red = Sprite.small_red;
	public boolean light_1 = false;

	public void Lights() {
	}

	public void run() {
		while(running) {
			if(light_1)
				light_1 = false;
			else
				light_1 = true;
			try {
				sleep(3000);
			} catch(InterruptedException e) {}
			System.out.println(light_1);
		}
	}
}
