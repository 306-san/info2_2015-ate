// Philosopher class

import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;
import java.util.TimerTask;

public class Philosopher implements Runnable {

	public int id;
	Chopstick leftChopstick, rightChopstick;
	private JLabel philLabel, comments, meal;
	private Random randomPeriod = new Random();
	int maxPeriod = 5000;
	int numberOfMeals = 0;

	public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick, JLabel philLabel, JLabel comments, JLabel meal) {
		super();
		this.id = id;

		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;

		this.philLabel = philLabel;
		this.comments = comments;
		this.meal = meal;
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.think();
				this.hungry();
				this.eat();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void think() throws InterruptedException {
		philLabel.setIcon(DiningPhilosophers.philThinkingIcons[id]);
		comments.setText("Philosopher# "+id+" Thinking");
		comments.setForeground(Color.RED);
		Thread.sleep(randomPeriod.nextInt(maxPeriod));
	}

	public void hungry() throws InterruptedException {
		while (DiningPhilosophers.leftNeighbourPhilosopher(id) || DiningPhilosophers.rightNeighbourPhilosopher(id)) {
			Thread.sleep(randomPeriod.nextInt(10));
		}
		DiningPhilosophers.permissions.acquire();
		leftChopstick.pickUp();
		rightChopstick.pickUp();
		DiningPhilosophers.philIsEating[id] = true;
	}

	private void eat() throws InterruptedException {
		numberOfMeals++;
		comments.setText("Philosopher# "+id+" Eating");
		comments.setForeground(Color.GREEN);

		meal.setText("Meal # :" + numberOfMeals);
		meal.setForeground(Color.BLUE);

		philLabel.setIcon(DiningPhilosophers.philEatingIcons[id]);

		Thread.sleep(randomPeriod.nextInt(maxPeriod));

		leftChopstick.putDown();
		rightChopstick.putDown();

		DiningPhilosophers.permissions.release();
		DiningPhilosophers.philIsEating[id] = false;
	}
}
