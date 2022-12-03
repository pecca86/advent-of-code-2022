package year2021.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Answer = Sum up all UNMARKED numbers on winning board * the last number drawn before winning.
 * A winning line is either diagonal or horizontal.
 */
public class Bingo {
    private static final String FILE_PATH = "src/main/java/year2021/day4/input.txt";
    private static List<Integer> lotteryNumbers;
    private static final List<List<Integer>> allCards = new ArrayList<>();
    private static List<Integer> card = new ArrayList<>();
    private static int winningCardIndex;
    private static int lastDrawnNumber;

    private static List<List<Integer>> winningRows;

    public static void main(String[] args) {
        intanziateWinningRowsListList();
        readInputFileToLists();

        int originalSize = lotteryNumbers.size();
        for (int i = 0; i < originalSize; i++) {
            lastDrawnNumber = lotteryNumbers.get(0);
            markDrawnNumberOnAllCards(lotteryNumbers.remove(0));
            if (calcuLateIfWeHaveAWinner()) {
                break;
            }
        }

        System.out.println("=> Index of Winning Card: " + winningCardIndex);
        System.out.println("=> Last Drawn number: " + lastDrawnNumber);
        int boardSum = calculateWinningBoardSum();
        int magicNumber = boardSum * lastDrawnNumber;
        System.out.println("=> Answer: " + magicNumber);
        System.out.println("\n=> Winning Card" + allCards.get(winningCardIndex));
    }

    private static int calculateWinningBoardSum() {
        return allCards.get(winningCardIndex).stream()
                .filter(i -> i != -1)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static void intanziateWinningRowsListList() {
        winningRows = new ArrayList<>();

        winningRows.add(List.of(0,1,2,3,4));
        winningRows.add(List.of(5,6,7,8,9));
        winningRows.add(List.of(10,11,12,13,14));
        winningRows.add(List.of(15,16,17,18,19));
        winningRows.add(List.of(20,21,22,23,24));

        winningRows.add(List.of(0,5,10,15,20));
        winningRows.add(List.of(1,6,11,16,21));
        winningRows.add(List.of(2,7,12,17,22));
        winningRows.add(List.of(3,8,13,18,23));
        winningRows.add(List.of(4,9,14,19,24));
    }


    private static boolean calcuLateIfWeHaveAWinner() {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        for (List<Integer> allCard : allCards) {
            for (int i = 0; i < allCard.size(); i++) {
                if (allCard.get(i) == -1) {
                    indexes.add(i);
                }
            }
            result.add(indexes);
            indexes = new ArrayList<>();
        }
        return containsWinningCard(result);
    }

    /**
     * Index: 0-4, 5-9, 10-14, 15-19, 20-24  <== All diagonals
     * Index: (0,5,10,15,20), (1,6,11,16,21), (2,7,12,17,22), (3,8,13,18,23), (4,9,14,19,24)  <== All Horizontals
     */
    private static boolean containsWinningCard(List<List<Integer>> result) {
        for (int i = 0; i < result.size(); i++) {
            HashSet<Integer> uniqueNumbers = new HashSet<>(result.get(i));
            List<List<Integer>> winningIndexes = winningRows.stream()
                    .filter(uniqueNumbers::containsAll)
                    .toList();

            if (winningIndexes.size() >= 1) {
                winningCardIndex = i;
                return true;
            }
        }
        return false;
    }

    private static void markDrawnNumberOnAllCards(Integer lotteryNumber) {
        allCards
                .forEach(list -> list.replaceAll(e -> Objects.equals(e, lotteryNumber) ? -1 : e));
    }

    private static void readInputFileToLists() {
        try (Scanner input = openFile()) {
            while (input.hasNextLine()) {
                if (lotteryNumbers == null) {
                    lotteryNumbers = new ArrayList<>();
                    String[] split = input.nextLine().split(",");
                    lotteryNumbers.addAll(parseStringToIntArray(split));
                }
                String value = input.nextLine();
                if (value.isEmpty() && card != null) {
                    allCards.add(card);
                    card = new ArrayList<>();
                } else if (!value.isEmpty()) {
                    String[] split = value.split(" ");
                    card.addAll(parseStringToIntArray(split));
                 }
            }
            if (!card.isEmpty()) {
                allCards.add(card);
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> parseStringToIntArray(String[] split) {
        return Arrays.stream(split)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static Scanner openFile() throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(FILE_PATH)));
    }
}
