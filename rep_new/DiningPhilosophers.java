// DiningPhilosophers class

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Calendar;

public class DiningPhilosophers {

	private JFrame frame;

	static final int NUM_PHILOSOPHERS = 5;

	Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];

	static Semaphore permissions = new Semaphore(2);

	static boolean [] philIsEating = {false, false, false, false, false};

	JLabel[] PhilsLables = new JLabel[5];
	JLabel[] chopsticksLables = new JLabel[5];
	JLabel[] comments = new JLabel[5];
	JLabel[] meals = new JLabel[5];
	JLabel philLable_0;
	JLabel philLable_1;
	JLabel philLable_2;
	JLabel philLable_3;
	JLabel philLable_4;
	JLabel chopstickLable_0;
	JLabel chopstickLable_1;
	JLabel chopstickLable_2;
	JLabel chopstickLable_3;
	JLabel chopstickLable_4;
	private JLabel lblNewLabel_0;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel meal_0;
	private JLabel meal_1;
	private JLabel meal_2;
	private JLabel meal_3;
	private JLabel meal_4;

	ImageIcon plateIcon = new ImageIcon("data/0.png");
	static ImageIcon []philThinkingIcons = new ImageIcon[5];
	static ImageIcon []philEatingIcons = new ImageIcon[5];
	static ImageIcon []chopstickAvailableIcons = new ImageIcon[5];
	static ImageIcon []chopstickUsedIcons = new ImageIcon[5];

  static Calendar set_time = Calendar.getInstance();

	public static void main(String[] args) {
    set_time.getTime();
    set_time.add(Calendar.MINUTE, 5);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiningPhilosophers window = new DiningPhilosophers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DiningPhilosophers() {
		initialize();
	}

  public boolean in_time(){
    Calendar now_time = Calendar.getInstance();
    now_time.getTime();
    //return true;
    if (now_time != null && set_time != null){
       if (now_time.before(set_time)==true){
         return true;
       }else{
         return false;
       }
    } else {
      return true;
    }

    }

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0,900, 900);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Button start = new Button("Start");
		start.setBounds(356,639 , 77, 22);
		start.setBackground(Color.GREEN);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(start);

		for(int i = 0; i < philThinkingIcons.length; i++){
			philThinkingIcons[i]= new ImageIcon("data/img0"+i+".png");
			philEatingIcons[i]= new ImageIcon("data/img0"+i+"eat.png");
			chopstickAvailableIcons[i]= new ImageIcon("data/c"+i+".png");
			chopstickUsedIcons[i]= new ImageIcon("data/c"+i+"use.png");
		}

		JLabel plateLable = new JLabel("Midle Plate");
		plateLable.setBounds(220, 170, 336, 333);
		plateLable.setIcon(plateIcon);
		frame.getContentPane().add(plateLable);

		philLable_0 = new JLabel("Philosopher 0");
		philLable_0.setBounds(344, 50, 89, 110);
		philLable_0.setIcon(philThinkingIcons[0]);
		frame.getContentPane().add(philLable_0);

		philLable_1 = new JLabel("Philosopher 1");
		philLable_1.setBounds(548, 165, 100, 110);
		philLable_1.setIcon(philThinkingIcons[1]);
		frame.getContentPane().add(philLable_1);

		philLable_2 = new JLabel("Philosopher 2");
		philLable_2.setBounds(523, 420, 100, 110);
		philLable_2.setIcon(philThinkingIcons[2]);
		frame.getContentPane().add(philLable_2);

		philLable_3 = new JLabel("Philosopher 3");
		philLable_3.setBounds(162, 434, 100, 110);
		philLable_3.setIcon(philThinkingIcons[3]);
		frame.getContentPane().add(philLable_3);

		philLable_4 = new JLabel("Philosopher 4");
		philLable_4.setBounds(134, 165, 100, 110);
		philLable_4.setIcon(philThinkingIcons[4]);
		frame.getContentPane().add(philLable_4);


		chopstickLable_0 = new JLabel("Chopstick 0");
		chopstickLable_0.setBounds(244, 88, 55, 110);
		chopstickLable_0.setIcon(chopstickAvailableIcons[0]);
		frame.getContentPane().add(chopstickLable_0);

		chopstickLable_1 = new JLabel("Chopstick 1");
		chopstickLable_1.setBounds(479, 88, 63, 110);
		chopstickLable_1.setIcon(chopstickAvailableIcons[1]);
		frame.getContentPane().add(chopstickLable_1);

		chopstickLable_2 = new JLabel("Chopstick 2");
		chopstickLable_2.setBounds(558, 286, 120, 100);
		chopstickLable_2.setIcon(chopstickAvailableIcons[2]);
		frame.getContentPane().add(chopstickLable_2);

		chopstickLable_3 = new JLabel("Chopstick 3");
		chopstickLable_3.setBounds(382, 514, 27, 96);
		chopstickLable_3.setIcon(chopstickAvailableIcons[3]);
		frame.getContentPane().add(chopstickLable_3);

		chopstickLable_4 = new JLabel("Chopstick 4");
		chopstickLable_4.setBounds(99, 298, 111, 96);
		chopstickLable_4.setIcon(chopstickAvailableIcons[4]);
		frame.getContentPane().add(chopstickLable_4);

		lblNewLabel_0 = new JLabel("Philosopher# 0");
		lblNewLabel_0.setBounds(344, 8, 169, 14);
		frame.getContentPane().add(lblNewLabel_0);

		lblNewLabel_1 = new JLabel("Philosopher# 1");
		lblNewLabel_1.setBounds(658, 213, 169, 14);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Philosopher# 2");
		lblNewLabel_2.setBounds(632, 489, 169, 14);
		frame.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Philosopher# 3");
		lblNewLabel_3.setBounds(0, 482, 169, 14);
		frame.getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Philosopher# 4");
		lblNewLabel_4.setBounds(0, 213, 169, 14);
		frame.getContentPane().add(lblNewLabel_4);

		meal_0 = new JLabel("Meal #");
		meal_0.setBounds(344, 25, 80, 14);
		frame.getContentPane().add(meal_0);

		meal_1 = new JLabel("Meal #");
		meal_1.setBounds(658, 238, 80, 14);
		frame.getContentPane().add(meal_1);

		meal_2 = new JLabel("Meal #");
		meal_2.setBounds(633, 514, 80, 14);
		frame.getContentPane().add(meal_2);

		meal_3 = new JLabel("Meal #");
		meal_3.setBounds(0, 501, 80, 14);
		frame.getContentPane().add(meal_3);

		meal_4 = new JLabel("Meal #");
		meal_4.setBounds(0, 238, 80, 14);
		frame.getContentPane().add(meal_4);

		PhilsLables[0] = philLable_0;
		PhilsLables[1] = philLable_1;
		PhilsLables[2] = philLable_2;
		PhilsLables[3] = philLable_3;
		PhilsLables[4] = philLable_4;
		chopsticksLables[0] = chopstickLable_0;
		chopsticksLables[1] = chopstickLable_1;
		chopsticksLables[2] = chopstickLable_2;
		chopsticksLables[3] = chopstickLable_3;
		chopsticksLables[4] = chopstickLable_4;
		comments[0] = lblNewLabel_0;
		comments[1] = lblNewLabel_1;
		comments[2] = lblNewLabel_2;
		comments[3] = lblNewLabel_3;
		comments[4] = lblNewLabel_4;
		meals[0] = meal_0;
		meals[1] = meal_1;
		meals[2] = meal_2;
		meals[3] = meal_3;
		meals[4] = meal_4;
	}

	public void start() {
		Chopstick[] chopsticks = new Chopstick[NUM_PHILOSOPHERS];

		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			chopsticks[i] = new Chopstick(i, chopsticksLables[i]);
		}

		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			philosophers[i] = new Philosopher(i, chopsticks[(i + 1) % NUM_PHILOSOPHERS], chopsticks[i], PhilsLables[i], comments[i], meals[i]);
			new Thread(philosophers[i]).start();
		}
	}

	public synchronized static boolean leftNeighbourPhilosopher(int id) {
		return DiningPhilosophers.philIsEating[(id + 1)
				% DiningPhilosophers.NUM_PHILOSOPHERS];
	}

	public synchronized static boolean rightNeighbourPhilosopher(int id) {

		if (id == 0)
			return DiningPhilosophers.philIsEating[4];
		else
			return DiningPhilosophers.philIsEating[(id - 1)
					% DiningPhilosophers.NUM_PHILOSOPHERS];
	}
}
