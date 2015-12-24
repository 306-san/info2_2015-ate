// Chopstick class

import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JLabel;

public class Chopstick extends ReentrantLock{

	int id;
	JLabel label;

	public Chopstick(int id, JLabel label) {
		super();
		this.id = id;
		this.label = label;
	}
	
	public synchronized void pickUp(){
		this.lock();
		label.setIcon(DiningPhilosophers.chopstickUsedIcons[id]);
	}
	
	public synchronized void putDown(){
		this.unlock();
		label.setIcon(DiningPhilosophers.chopstickAvailableIcons[id]);
	}
}
