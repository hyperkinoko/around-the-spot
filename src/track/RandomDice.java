package track;

import java.util.ArrayList;
import java.util.Random;

public class RandomDice implements Dice {
    public ArrayList<Integer> roll(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(new Random().nextInt(6) + 1);
        }
        return list;
    }
}
