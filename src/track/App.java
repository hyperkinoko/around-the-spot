package track;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Player player = new Player(dice(args));
        List<Result> results = player.play();

        results.forEach(System.out::println);
        System.out.println("Final Score: " + results.get(results.size() - 1).getScore());
    }

    private static Dice dice(String[] args) {
        if (args.length > 0 && !args[0].trim().isEmpty()) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0].trim()))) {
                String s = stream.collect(Collectors.joining(System.lineSeparator())).trim();
                if (s.isEmpty()) {
                    return new RandomDice();
                } else {
                    return new ParameterizedDice(new ByteArrayInputStream(s.getBytes()));
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        } else {
            return new RandomDice();
        }
    }
}
