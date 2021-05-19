package track;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private Dice dice;

    public Player(Dice dice) {
        this.dice = dice;
    }

    public List<Result> play() {
        // 回数をカウントするための変数
        int count = 0;
        // スコア
        int score = 0;
        // 返り値にするArrayList<Result>
        ArrayList<Result> results = new ArrayList<>();

        while(count < 3) {
            // 3個のdiceをrollすると1つのResultが生まれる
            ArrayList<Integer> diceKekka = this.dice.roll(3);
            // 246のゾロ目が出たときはカウントをプラスしない
            if(!is246Zorome(diceKekka)) {
                count++;
            }
            score = tensuuKeisan(diceKekka, score);
            Result result = new Result(diceKekka, score);
            results.add(result);
        }
        return results;
    }

    private static int tensuuKeisan(ArrayList<Integer> diceKekka, int score) {
        if(is246Zorome(diceKekka)) {
            return score * 2;
        }
        return score += shuukeiNDice(diceKekka);
    }

    private static int shuukeiNDice(ArrayList<Integer> diceKekka) {
        int sum = 0;
        for(int i = 0; i < diceKekka.size(); i++) {
            switch(diceKekka.get(i)) {
                case 1:
                    sum += 1;
                    break;
                case 3:
                    sum += 2;
                    break;
                case 5:
                    sum += 4;
                    break;
                default:
            }
        }
        return sum;
    }

    private static boolean is246Zorome(ArrayList<Integer> diceKekka) {
        int firstDice = diceKekka.get(0);
        if(firstDice == 1 || firstDice == 3 || firstDice == 5) {
            return false;
        }
        for(int i = 0; i < diceKekka.size(); i++) {
            if(firstDice != diceKekka.get(i)) {
                return false;
            }
        }
        return true;
    }
}
