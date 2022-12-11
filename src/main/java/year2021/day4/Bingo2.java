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
    private static List<List<Integer>> cardsThatWon = new ArrayList<>();
    private static int lastDrawnNumber;
    private static int lastDrawnWinningNumber;
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

        int finalResult = calculateCardSum(cardsThatWon.get(cardsThatWon.size()-1));

        System.out.println(finalResult*lastDrawnNumber);
    }

    public static void printCard(List<Integer> card) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < card.size(); i++) {
            String numberToBeAdded = card.get(i).toString();
            if (i < 2 || i % 5 != 0) {
                sb.append(numberToBeAdded).append(" | ");
            } else {
                sb.append("\n").append(numberToBeAdded).append(" | ");
            }
        }
        System.out.printf(sb.toString());
    }

    private static int calculateCardSum(List<Integer> card) {
        return card.stream()
                .filter(i -> i > -1)
                .mapToInt(Integer::intValue)
                .sum();
    }
    private static void markDrawnNumberOnAllCards(int lotteryNumber) {
        // If we find the drawn number inside a card, we mark its index and negate it
        allCards.values()
                .forEach(card -> card.replaceAll(e -> Objects.equals(e, lotteryNumber) ? (card.indexOf(e)+1000) * -1 : e));
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
            cardsThatWon.add(allCards.remove(key));
            lastDrawnWinningNumber = lastDrawnNumber;
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

        winningRows.add(List.of(-1000,-1001,-1002,-1003,-1004));
        winningRows.add(List.of(-1005,-1006,-1007,-1008,-1009));
        winningRows.add(List.of(-1010,-1011,-1012,-1013,-1014));
        winningRows.add(List.of(-1015,-1016,-1017,-1018,-1019));
        winningRows.add(List.of(-1020,-1021,-1022,-1023,-1024));

        winningRows.add(List.of(-1000,-1005,-1010,-1015,-1020));
        winningRows.add(List.of(-1001,-1006,-1011,-1016,-1021));
        winningRows.add(List.of(-1002,-1007,-1012,-1017,-1022));
        winningRows.add(List.of(-1003,-1008,-1013,-1018,-1023));
        winningRows.add(List.of(-1004,-1009,-1014,-1019,-1024));
    }
}
