import javax.swing.*;

// �N�w�҃N���X
class Philosopher extends Thread {

  static int counter = 0;

  // �҂����Ԃ̍ő�l�B�K���ɐݒ肵�Ă��������B
  final long WAITTIME = 100;

  // ���ʔԍ��B�N�w�҃I�u�W�F�N�g�𕡐����̂ŁA
  // �\���̍ۂɌ��₷���悤�A�e�X�ɈقȂ�ID��^���܂��B
  int number;

  // �����̃e���g���[�ɂ���2�{�̔��B
  // lower : �D��x�̒Ⴂ��,
  // higher: �D��x�̍�����
  ChopStick lowerStick;
  ChopStick higherStick;

  // ���������������Ă��邩�ۂ�
  boolean hasLowerStick;
  boolean hasHigherStick;

  // message
  String msg;
  DiningPhilosophers frame = new DiningPhilosophers();

  Philosopher(ChopStick c1, ChopStick c2) {
    // ID�̕t�^
    number = counter++;

    // �������g���锢��o�^
    if (c1.rank < c2.rank) {
      lowerStick = c1;
      higherStick = c2;
    } else {
      lowerStick = c2;
      higherStick = c1;
    }

    // �ŏ��͔��������Ă��Ȃ����
    hasLowerStick = false;
    hasHigherStick = false;
  }

  // �N�w�҂̍s����o�^�������\�b�h�Q

  public void run () {
    for (int i = 0; i < 5; i++) {
      if(i == 6 && number == 6){
        DiningPhilosophers frame = new DiningPhilosophers();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 1010, 1010);
        frame.setTitle("title");
        frame.setVisible(true);
      }
      // �D��x�̍����������
      picUpHigherStick();

      // �l����������
      if(hasHigherStick)
        think();

      // �D��x�̒Ⴂ�������
      picUpLowerStick();

      // �H�ׂ�
      eat();

      // �܂��l����

      think();

      // �D��x�̍�������u��
      putDownHigherStick();

      // �D��x�̒Ⴂ����u��
      putDownLowerStick();

      // �܂��܂��l����������
      think();
    }
  }

  // �D��x�̍����������
  synchronized void picUpHigherStick() {

    // �D��x�̍��������󂭂܂ő҂�
    while(higherStick.isUsed)
      await();

      higherStick.isUsed = true;
      hasHigherStick = true;
      printAnEvent("pick up stick No." + higherStick.rank);
  }

  // �D��x�̒Ⴂ�������
  synchronized void picUpLowerStick() {
    if (hasHigherStick) {

      // �D��x�̒Ⴂ�����󂭂܂ő҂�
      while(lowerStick.isUsed)
        await();

      lowerStick.isUsed = true;
      hasLowerStick = true;
      printAnEvent("pick up stick No." + lowerStick.rank);
    }
  }


  // �H�������郁�\�b�h
  void eat() {
    if (hasLowerStick && hasHigherStick) {
      frame.set_eat_icon(number);
      printAnEvent("***eating***");

      // �����_�����Ԃ����ҋ@
      waitRandom();
    }
  }

  // �l����
  void think() {
    printAnEvent("      ***thinking***");

    waitRandom();
  }

  // �D��x�̍�������u��
  synchronized void putDownHigherStick() {
    // ���̏����́A�P�� (hasHigherStick) �����ł��悢�B
    if (hasLowerStick && hasHigherStick) {
      higherStick.isUsed = false;
      hasHigherStick = false;

      printAnEvent("put down stick No." + higherStick.rank);
    }
  }

  // �D��x�̒Ⴂ����u��
  synchronized void putDownLowerStick() {
    if (hasLowerStick) {
      lowerStick.isUsed = false;
      hasLowerStick = false;

      printAnEvent("put down stick No." + lowerStick.rank);
    }
  }

  // �\���p
  synchronized void printAnEvent(String str) {

    msg = "";

    for(int i = 0; i < 22 * number; i++) {
      msg += " ";
    }
    msg += str;
    System.out.println(msg);
  }

  // �K���Ȏ��Ԃ����҂�
  void waitRandom() {
    try {
      sleep((long)(Math.random() * WAITTIME));
    } catch (InterruptedException e) { }
  }

  // ��u�����҂� (����A�ʂ�waitRandom()���\�b�h�ł���p��)
  synchronized void await() {
    try {
      sleep(1);
    } catch (InterruptedException e) { }
  }
}
