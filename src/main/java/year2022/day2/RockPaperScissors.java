package year2022.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class RockPaperScissors {
    private static final int LOSS = 0;
    private static final int DRAW = 3;
    private static final int WIN = 6;

    private static List<String> elfSequence;
    private static List<String> humanSequence;

    public enum Hands {
        A("ROCK", 1),
        B("PAPER", 2),
        C("SCISSORS", 3),
        X("ROCK", 1),
        Y("PAPER", 2),
        Z("SCISSORS", 3);

        public final String hand;
        public final int points;

        Hands(String hand, int points) {
            this.hand = hand;
            this.points = points;
        }
    }


    public static void main(String[] args) {
        readInputFileToLists();

        int humanPoints = getHumanPoints();
        System.out.println(humanPoints);
    }

    private static int getHumanPoints() {
        int humanPoints = 0;
        for (int i = 0; i < humanSequence.size(); i++) {
            String elfHand = Hands.valueOf(elfSequence.get(i)).hand;
            String humanHand = Hands.valueOf(humanSequence.get(i)).hand;
            int handPoints = Hands.valueOf(humanSequence.get(i)).points;

            if (elfHand.equals(humanHand)) {
                humanPoints += DRAW + handPoints;
            } else if (elfHand.equals(Hands.A.hand) && humanHand.equals(Hands.Z.hand)) {
                humanPoints += LOSS + handPoints;
            } else if (elfHand.equals(Hands.B.hand) && humanHand.equals(Hands.X.hand)) {
                humanPoints += LOSS + handPoints;
            } else if (elfHand.equals(Hands.C.hand) && humanHand.equals(Hands.Y.hand)) {
                humanPoints += LOSS + handPoints;
            } else {
                humanPoints += WIN + handPoints;
            }
        }
        return humanPoints;
    }

    private static void readInputFileToLists() {
        elfSequence = new ArrayList<>();
        humanSequence = new ArrayList<>();

        try (Scanner input = openFile("src/main/java/year2022/day2/input.txt")) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                elfSequence.add(line.split(" ")[0]);
                humanSequence.add(line.split(" ")[1]);
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Scanner openFile(String filePath) throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(filePath)));
    }

}


