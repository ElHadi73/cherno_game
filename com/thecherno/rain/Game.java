package com.thecherno.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.level.OneMapLevel;
import com.thecherno.rain.entity.Lights;
import com.thecherno.rain.entity.Car;

@SuppressWarnings("unchecked")
public class Game extends Canvas implements Runnable { 
	private static final long serialVersionUID = 1L;

	private static int width = 507;
	private static int height = width;
	private static int scale = 2;

	public static String title = "Rain";

	private Thread thread;
	private JFrame frame;
	private OneMapLevel carrfor;
	private boolean running = false;
	private Lights lights;
	public ArrayList<Car>[] cars_list = (ArrayList<Car>[]) new ArrayList[4];
	public ArrayList<Car> top_cars    = cars_list[0] = new ArrayList<Car>();
	public ArrayList<Car> right_cars  = cars_list[1] = new ArrayList<Car>();
	public ArrayList<Car> bottom_cars = cars_list[2] = new ArrayList<Car>();
	public ArrayList<Car> left_cars   = cars_list[3] = new ArrayList<Car>();

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height,cars_list);
		frame = new JFrame();
		carrfor = new OneMapLevel("/res/levels/map-export_original.png");
		lights = new Lights();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean free_slut(int enter){
		Car test_car;
		for(int counter = 0; counter < this.cars_list[enter].size(); counter++) {
			test_car = cars_list[enter].get(counter);
			if(enter == 0 && test_car.y + 39 <= 0)
				return false;
			if(enter == 1 && test_car.x + 39 >=571)
				return false;
			if(enter == 2 && test_car.y + 39 >=571)
				return false;
			if(enter == 3 && test_car.x + 39 <= 0)
				return false;
		}
		return true;
	}

	public void run() {
		double ns = 1000000000.0 / 60.0;
		double delta = 0;

		int frames = 0;
		int updates = 0;

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();

		requestFocus();

		lights.start();
		Random random = new Random();
		Car car;
		int car_n = 0;
		Thread car_thread;
		int enter;

		while (running) {

			//create car
			System.out.println(cars_list[0].size()+"  |  "+cars_list[1].size()+"  |  "+cars_list[2].size()+"  |  "+cars_list[3].size());
			enter = random.nextInt(4);
			if(random.nextInt(10) == 0 && free_slut(enter)){
				car = new Car(cars_list,enter);
				cars_list[enter].add(car);
				car_thread = new Thread(car,String.valueOf(car_n));
				car.setthread(car_thread);
				car_n++;
				car_thread.start();
			}



			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if(System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				frames = 0;
				updates = 0;
			}
		}

		stop();
	}

	public void update() {
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		screen.renderAll(carrfor, lights);

		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
