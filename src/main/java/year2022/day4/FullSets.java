package year2022.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class FullSets {
    private static final String FILE_PATH = "src/main/java/year2022/day4/input.txt";
    static Map<Integer, SetPair> setPairsMap = new HashMap<>();
    static int count = 0;
    static int subsets = 0;


    public static void main(String[] args) {
        readDataAndCreateDataStructures();

        setPairsMap.values().forEach(pair -> {
            if (pair.getSet1().containsAll(pair.getSet2())) {
                subsets++;
            } else if (pair.getSet2().containsAll(pair.getSet1())) {
                subsets++;
            }
        });

        System.out.println(subsets);
    }

    // === ALL BELOW RELATES TO READING INPUT AND CREATING DATA STRUCTURES
    private static void readDataAndCreateDataStructures() {
        try (Scanner input = openFile()) {
            while (input.hasNextLine()) {
                parseInputToMap(input.nextLine());
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Scanner openFile() throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(FILE_PATH)));
    }

    public static void parseInputToMap(String input) {
        String[] list = input.split(",");
        Set<Integer>  intSet1 = parseToSet(list[0]);
        Set<Integer>  intSet2 = parseToSet(list[1]);

        SetPair sp = new SetPair();
        sp.setSet1(intSet1);
        sp.setSet2(intSet2);
        setPairsMap.put(count, sp);
        count++;
    }

    private static Set<Integer> parseToSet(String s) {
        String[] splitString = s.split("-");
        int start = Integer.parseInt(splitString[0]);
        int stop = Integer.parseInt(splitString[1]);
        Set<Integer> result = new HashSet<>();

        for (int i = start; i <= stop; i++) {
            result.add(i);
        }
        return result;
    }
}
