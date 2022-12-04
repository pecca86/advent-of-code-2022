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
public class Bingo2 {
    private static final String FILE_PATH = "src/main/java/year2021/day4/input.txt";
    private static List<Integer> lotteryNumbers;
    private static final Map<Integer, List<Integer>> allCards = new LinkedHashMap<>();
    private static List<Integer> card = new ArrayList<>();
    private static int lastDrawnNumber;
    private static List<List<Integer>> winningRows;

    public static void main(String[] args) {
        instantiateWinningRowsListList();
        readInputFileToLists();

        int originalSize = lotteryNumbers.size();
        for (int i = 0; i < originalSize; i++) {
            lastDrawnNumber = lotteryNumbers.get(0);
            markDrawnNumberOnAllCards(lotteryNumbers.remove(0));
            removeIfWinningCard();
            if (allCards.size() <= 1) {
                break;
            }
        }


        System.out.println(lastDrawnNumber);
        System.out.println(allCards);

        int finalResult = calculateCardSum(allCards.get(14));

        System.out.println(finalResult*lastDrawnNumber);


    }

    private static int calculateCardSum(List<Integer> card) {
        return card.stream()
                .filter(i -> i != -1)
                .mapToInt(Integer::intValue)
                .sum();
    }
    private static void markDrawnNumberOnAllCards(int lotteryNumber) {
        // If we find the drawn number inside a card, we mark its index and negate it
        allCards.values()
                .forEach(card -> card.replaceAll(e -> Objects.equals(e, lotteryNumber) ? card.indexOf(e) * -1 : e));
    }

    private static void removeIfWinningCard() {
        checkIfNewWinningCard();
    }

    private static void checkIfNewWinningCard() {
        List<Integer> keysToRemove = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> cardKeyValuePair : allCards.entrySet()) {
            for (List<Integer> row : winningRows) {
                if (cardKeyValuePair.getValue().containsAll(row)) {
                    keysToRemove.add(cardKeyValuePair.getKey());
                }
            }
        }

        for (Integer key : keysToRemove) {
            allCards.remove(key);
        }
    }

    // === ALL BELOW RELATES TO READING INPUT AND CREATING DATA STRUCTURES
    private static void readInputFileToLists() {
        try (Scanner input = openFile()) {
            int mapIndex = 0;
            while (input.hasNextLine()) {
                if (lotteryNumbers == null) {
                    lotteryNumbers = new ArrayList<>();
                    String[] split = input.nextLine().split(",");
                    lotteryNumbers.addAll(parseStringToIntArray(split));
                }
                String value = input.nextLine();
                if (value.isEmpty() && card != null) {
                    allCards.put(mapIndex, card);
                    mapIndex++;
                    card = new ArrayList<>();
                } else if (!value.isEmpty()) {
                    String[] split = value.split(" ");
                    card.addAll(parseStringToIntArray(split));
                 }
            }
            if (!card.isEmpty()) {
                allCards.put(mapIndex, card);
            }
            allCards.remove(0); // Algorithm creates an empty 0 key entry list
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

    private static void instantiateWinningRowsListList() {
        winningRows = new ArrayList<>();

        winningRows.add(List.of(0,-1,-2,-3,-4));
        winningRows.add(List.of(-5,-6,-7,-8,-9));
        winningRows.add(List.of(-10,-11,-12,-13,-14));
        winningRows.add(List.of(-15,-16,-17,-18,-19));
        winningRows.add(List.of(-20,-21,-22,-23,-24));

        winningRows.add(List.of(0,-5,-10,-15,-20));
        winningRows.add(List.of(-1,-6,-11,-16,-21));
        winningRows.add(List.of(-2,-7,-12,-17,-22));
        winningRows.add(List.of(-3,-8,-13,-18,-23));
        winningRows.add(List.of(-4,-9,-14,-19,-24));
    }
}
