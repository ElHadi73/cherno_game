package com.thecherno.rain.entity;

import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.entity.Sphr;

import java.lang.Thread;

public class Lights extends Thread {

	private int timeScap = 5000;
	public int x1,y1,x2,y2;
	public boolean running = true;
	public Sprite flights = Sprite.flights;
	public Sprite light_right = Sprite.light_right;
	public Sprite stright_green = Sprite.stright_green;
	public Sprite stright_red = Sprite.stright_red;
	public Sprite small_green = Sprite.small_green;
	public Sprite small_red = Sprite.small_red;
	public boolean light_1 = false;

	public void Lights() {
	}

	public void run() {
		while(running) {
			if(light_1){
				try {
					Sphr.Feu11.acquire();
					Sphr.Feu12.acquire();
				} catch (InterruptedException e) {
				}
				finally {
					Sphr.Feu21.release();
					Sphr.Feu22.release();
				}
				light_1 = false;
			}
			else{
				try {
					Sphr.Feu21.acquire();
					Sphr.Feu22.acquire();
				} catch (InterruptedException e) {
				}
				finally {
					Sphr.Feu11.release();
					Sphr.Feu12.release();
				}
				light_1 = true;
			}
			try {
				sleep(20000);
			} catch(InterruptedException e) {}
		}
	}
}
