//箸クラス
//作成者：Ito　Takumu
public class ChopStick {

  static int counter = 0;

  // 使用中か否か
  boolean isUsed;

  // 優先度
  int rank;

  ChopStick() {
    isUsed = false;

    // それぞれの箸に異なる優先度を与える
    rank = counter++;
  }
}
