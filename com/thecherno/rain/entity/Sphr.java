package com.thecherno.rain.entity;

import java.util.concurrent.*;


public class Sphr {
	public static Semaphore Feu11 = new Semaphore(0, true);
	public static Semaphore Feu12= new Semaphore(0,true);
	public static Semaphore Feu21 = new Semaphore(1,true);
	public static Semaphore Feu22= new Semaphore(1,true);
}
