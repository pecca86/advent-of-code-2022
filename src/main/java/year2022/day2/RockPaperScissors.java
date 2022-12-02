package year2022.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class RockPaperScissors {
    private static final int LOSS = 0;
    private static final int DRAW = 3;
    private static final int WIN = 6;
    public static final String FILE_PATH = "src/main/java/year2022/day2/input.txt";

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

        int secondFormulaResult = getHumanPointSecondChallenge();
        System.out.println(secondFormulaResult);
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

    /**
     * X = Lose
     * Y = Draw
     * Z = Win
     * @return total points
     */
    private static int getHumanPointSecondChallenge() {
        int humanPoints = 0;
        for (int i = 0; i < humanSequence.size(); i++) {
            String elfHand = Hands.valueOf(elfSequence.get(i)).hand;
            String humanHand = Hands.valueOf(humanSequence.get(i)).hand;

            if (humanHand.equals(Hands.X.hand)) {
                humanPoints += elfHand.equals(Hands.A.hand) ? Hands.C.points + LOSS : 0;
                humanPoints += elfHand.equals(Hands.B.hand) ? Hands.A.points + LOSS : 0;
                humanPoints += elfHand.equals(Hands.C.hand) ? Hands.B.points + LOSS : 0;
            } else if (humanHand.equals(Hands.Y.hand)) {
                humanPoints += elfHand.equals(Hands.A.hand) ? Hands.A.points + DRAW : 0;
                humanPoints += elfHand.equals(Hands.B.hand) ? Hands.B.points + DRAW : 0;
                humanPoints += elfHand.equals(Hands.C.hand) ? Hands.C.points + DRAW : 0;
            } else {
                humanPoints += elfHand.equals(Hands.A.hand) ? Hands.B.points + WIN : 0;
                humanPoints += elfHand.equals(Hands.B.hand) ? Hands.C.points + WIN : 0;
                humanPoints += elfHand.equals(Hands.C.hand) ? Hands.A.points + WIN : 0;
            }
        }
        return humanPoints;
    }

    private static void readInputFileToLists() {
        elfSequence = new ArrayList<>();
        humanSequence = new ArrayList<>();
        String[] splitted;

        try (Scanner input = openFile()) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                splitted = line.split(" ");
                elfSequence.add(splitted[0]);
                humanSequence.add(splitted[1]);
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Scanner openFile() throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(FILE_PATH)));
    }
}