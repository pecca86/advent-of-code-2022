package year2022.day5;

import java.io.*;
import java.util.*;

public class CrateStacks {
    private static final String FILE_PATH_TO_STACK = "src/main/java/year2022/day5/stack_map.txt";
    private static final String FILE_PATH_TO_INSTRUCTIONS = "src/main/java/year2022/day5/input.txt";
    private static final Map<Integer, Deque<String>> stackMap = new HashMap<>();
    private static int charCount = 1;
    private static int blankCount;

    public static void main(String[] args) {
        initializeStacks();
        readInstructions();
        System.out.println(stackMap);

        stackMap.values().forEach(val -> System.out.println(val.pop()));
    }

    // === ALL BELOW RELATES TO READING INPUT AND CREATING DATA STRUCTURES
    private static void initializeStacks() {
        try (Scanner input = openFile(FILE_PATH_TO_STACK)) {
            input.useDelimiter("\n");
            while (input.hasNextLine()) {
                String next = input.nextLine();
                String parsed = next.replaceAll("[\\[\\]]", "");
                parseInputToMap(parsed);
                charCount = 1;
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Scanner openFile(String filePath) throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(filePath)));
    }

    public static void parseInputToMap(String input) {
        List<String> lineSplit = List.of(input.split("\s"));
        lineSplit.forEach(CrateStacks::countBlanksAndAddToMap);
    }

    private static void countBlanksAndAddToMap(String input) {
        if (charCount > 9) charCount = 1;

        if (blankCount == 4) {
            charCount++;
            blankCount = 0;
        }
        if (input.isBlank()) {
            blankCount++;
            return;
        }

        if (stackMap.get(charCount) == null) {
            stackMap.put(charCount, new ArrayDeque<>());
        }

        stackMap.get(charCount).addLast(input);
        charCount++;
    }


    // INSTRUCTIONS
    private static void readInstructions() {
        try (Scanner input = openFile(FILE_PATH_TO_INSTRUCTIONS)) {
            while (input.hasNextLine()) {
                String in = input.nextLine();
                String parsed = in.replaceAll("[A-Za-z]", " ").trim().replaceAll("\s+", ",");
                String[] split = parsed.split(",");
                //TODO: Change algorithm here!
                //moveItems(split[0], split[1], split[2]);
                moveMany(split[0], split[1], split[2]);
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void moveMany(String amount, String from, String to) {
        int i = 0;
        int itemsToMove = Integer.parseInt(amount);
        int fromIndex = Integer.parseInt(from);
        int toIndex = Integer.parseInt(to);
        List<String> moveList = new ArrayList<>();

        while (i < itemsToMove) {
            moveList.add(stackMap.get(fromIndex).pop());
            i++;
        }

        Collections.reverse(moveList);

        for (String s : moveList) {
            stackMap.get(toIndex).push(s);
        }
    }

    private static void moveItems(String amount, String from, String to) {
        int i = 0;
        int itemsToMove = Integer.parseInt(amount);
        int fromIndex = Integer.parseInt(from);
        int toIndex = Integer.parseInt(to);

        while (i < itemsToMove) {
            stackMap.get(toIndex).push(stackMap.get(fromIndex).pop());
            i++;
        }
    }

}
