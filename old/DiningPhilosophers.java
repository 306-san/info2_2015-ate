import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.*;
public class DiningPhilosophers extends JFrame implements Runnable{

  static final int N = 5;

  static ChopStick[] chopsticks;

  static Philosopher[] philosophers;

  ImageIcon icon4;
  JLabel label4;
  ImageIcon eat_icon;

  public static void main(String[] args) {
    DiningPhilosophers frame = new DiningPhilosophers();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 1010, 1010);
    frame.setTitle("title");
    frame.setVisible(true);
  //  getContentPane().add(p, BorderLayout.CENTER);

     chopsticks = new ChopStick[N];

    for (int i = 0; i < N; i++) {
      chopsticks[i] = new ChopStick();
    }


    philosophers = new Philosopher[N];

    for (int i = 0; i < N; i++) {
      philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i+1)%N]);
    }


    for (int i = 0; i < N; i++) {
      System.out.printf("Philosopher %d         ", i);
    }
    System.out.println();

    for (int i = 0; i < N; i++) {
      System.out.print("--------------------  ");
    }
    System.out.println();

    for (int i = 0; i < N; i++) {
      philosophers[i].start();
    }
  }
  DiningPhilosophers(){
    //table & Philosophers
    JPanel p = new JPanel();
    p.setLayout(null);
    ImageIcon icon1 = new ImageIcon("./data/img01.png");
    JLabel label1 = new JLabel(icon1);
    label1.setBounds(390, 150, 190, 190);
    ImageIcon icon2 = new ImageIcon("./data/img02.png");
    JLabel label2 = new JLabel(icon2);
    label2.setBounds(580, 280, 190, 190);
    ImageIcon icon3 = new ImageIcon("./data/img03.png");
    JLabel label3 = new JLabel(icon3);
    label3.setBounds(525, 530, 190, 190);
    ImageIcon icon4 = new ImageIcon("./data/img04.png");
    JLabel label4 = new JLabel(icon4);
    label4.setBounds(250, 530, 190, 190);
    ImageIcon icon5 = new ImageIcon("./data/img05.png");
    JLabel label5 = new JLabel(icon5);
    label5.setBounds(190, 280, 190, 190);
    ImageIcon icon6 = new ImageIcon("./data/0.PNG");
    JLabel label6 = new JLabel(icon6);
    label6.setBounds(180, 10, 600, 900);
   //chopsticks
    ImageIcon icon7 = new ImageIcon("./data/c0.png");
    JLabel label7 = new JLabel(icon7);
    label7.setBounds(225, 150, 190, 190);
    ImageIcon icon8 = new ImageIcon("./data/c1.png");
    JLabel label8 = new JLabel(icon8);
    label8.setBounds(550, 150, 190, 190);
    ImageIcon icon9 = new ImageIcon("./data/c2.png");
    JLabel label9 = new JLabel(icon9);
    label9.setBounds(600, 450, 190, 190);
    ImageIcon icon10 = new ImageIcon("./data/c3.png");
    JLabel label10 = new JLabel(icon10);
    label10.setBounds(390, 590, 190, 190);
    ImageIcon icon11 = new ImageIcon("./data/c4.png");
    JLabel label11 = new JLabel(icon11);
    label11.setBounds(170, 450, 190, 190);

    p.add(label1);
    p.add(label2);
    p.add(label3);
    p.add(label4);
    p.add(label5);
    p.add(label6);
    p.add(label7);
    p.add(label8);
    p.add(label9);
    p.add(label10);
    p.add(label11);


    getContentPane().add(p, BorderLayout.CENTER);
  }
  public void set_eat_icon(int n){
    ImageIcon eat_icon = new ImageIcon("./data/img02.png");
    System.out.printf("\ndebug:n=%d\n",n);
    if(n==4){
      label4.setIcon(eat_icon);
    }
  }
}
