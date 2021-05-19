package track;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Dice dice;

    /**
     * コンストラクタ
     * 引数に与えられたDiceを使ってゲームをする
     * @param dice RandomDiceを使うか、ParameterizedDiceを使うかを指定する
     */
    public Player(Dice dice) {
        this.dice = dice;
    }

    /**
     * ゲームをする
     * @return 結果のリスト
     */
    public List<Result> play() {
        // 返り値として返すための複数回の試行結果を格納するリスト
        // Resultが3個のサイコロを投げた1回分の結果（サイコロの目のリストと得点）
        // 返り値の型がListなので、Listで宣言しても大丈夫
        // ArrayListでもOK、具体的なものから抽象的なものへの変換は自由）
        ArrayList<Result> results = new ArrayList<>();

        // サイコロを3個投げる回数をカウントする（
        // 246ゾロ目のときは1回にカウントしない）
        int count = 0;

        // 得点
        int score = 0;

        // カウントが0、1、2の間（3未満）の3カウント分
        // 3個のサイコロを振るのを繰り返す
        while(count < 3) {
            // まず1回サイコロを3個振り、
            // その結果（サイコロの目3つぶんのリスト）をtrialという変数に入れる
            ArrayList<Integer> trial = this.dice.roll(3);

            // trialが246のゾロ目のとき
            if(is246Zorome(trial)) {
                // scoreを2倍にする
                score *= 2;
            // 246ゾロ目でないとき
            } else {
                // 3つのサイコロの目の得点を集計してscoreに足す
                score += calculateNDice(trial);
                // カウントを1増やす
                count++;
            }
            // scoreが計算されたら、
            // Resultインスタンスを作成して、それをリストに追加する
            results.add(new Result(trial, score));
        }
        // 3カウント分の試行が終わったら（ゾロ目の回数によっては3回とは限らない）
        // 結果が格納されたresultsを返す
        return results;
    }

    /**
     * 引数に与えられた目のリストが、246のいずれかのゾロ目であるかどうかを調べる
     * @param dice : サイコロの目のリスト
     * @return : 246のゾロ目であればtrue、そうでなければfalse
     */
    private boolean is246Zorome(ArrayList<Integer> dice) {
        int firstDice = dice.get(0); // 1つめの目を覚えておく

        // 2で割った余りが0でないとき（1、3、5のとき）
        // 246のゾロ目ではない
        if(firstDice % 2 != 0) {
            return false;
        }

        // firstDiseと残りの目が同じかを調べて、
        // 1つでも違うものがあった時点でreturn false（脱落）
        // 0番目はfirstDiceそのものなので、1番目から調べる
        for(int i = 1; i < dice.size(); i++) {
            if(firstDice != dice.get(i)) {
                return false;
            }
        }

        // ここまでreturnされずに生き残ったものだけが246のゾロ目
        return true;
    }

    /**
     * ゾロ目でない場合のサイコロの目の集計をする
     * @param dice : サイコロの目のリスト
     * @return : 集計結果
     */
    private int calculateNDice(ArrayList<Integer> dice) {
        // 合計を計算するための変数
        int sum = 0;

        // リストの目を1つずつ確認して、点数を足す
        for(int i = 0; i < dice.size(); i++) {
            // i番目の目の点数を出す
            switch (dice.get(i)) {
                case 1: // 1が出たとき
                    sum += 1; // 1点足す
                    break;
                case 3: // 3が出たとき
                    sum += 2; // 2点足す
                    break;
                case 5: // 5が出たとき
                    sum += 4; // 4点足す
                    break;
                // defaultを書かなくてもいいが、
                // それ以外の時を忘れずに考慮していますよ、
                // その結果何もしてませんよというアピール
                default:
            }
        }
        return sum;
    }
}
