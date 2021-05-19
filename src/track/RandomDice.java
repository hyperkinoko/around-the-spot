package track;

import java.util.ArrayList;
import java.util.Random;

public class RandomDice implements Dice {
  /**
    * 引数で指定された数のサイコロを振って、そのリストを返す
    * @param n 一度に振るサイコロの数
    * @return サイコロの目のリスト
    */
  @Override
  public ArrayList<Integer> roll(int n) {
    ArrayList<Integer> list = new ArrayList<>();
    Random rand = new Random();
    for(int i = 0; i < n; i++){
      int me = rand.nextInt(6) + 1;
      list.add(me);
    }
    return list;
  }

  public static void main(String[] args) {
    RandomDice randomDice = new RandomDice();
    ArrayList<Integer> list = randomDice.roll(3);
    System.out.println(list);
  }
}