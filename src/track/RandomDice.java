package track;

import java.util.ArrayList;
import java.util.Random;

public class RandomDice implements Dice {
    public ArrayList<Integer> roll(int n) {
        // 出た目を順に入れておくためのリスト
        // 返り値の型がArrayListなので、ListではなくArrayListで扱う
        // 具体的 ArrayList > List 抽象的
        ArrayList<Integer> list = new ArrayList<>();

        // 乱数発生器を生成
        Random random = new Random();
        // 乱数を作ってリストに入れるのをn回繰り返す
        for(int i = 0; i < n; i++) {
            // 0〜5の乱数を発生 →
            // 1を足して1〜6の乱数にする →
            // できた乱数をリストに追加する
            list.add(random.nextInt(6) + 1);
        }
        // できあがったリストを返す
        return list;
    }

    // RandomDiceのrollメソッドをテストするためのmainメソッド
    // 本来はmainメソッドを書かずに、テストコードを書くのが正しい
    public static void main(String[] args) {
        RandomDice dice = new RandomDice();
        // dice.roll(n)で発生させたn個のサイコロの目が入ったリストを表示
        System.out.println(dice.roll(5));
    }
}
