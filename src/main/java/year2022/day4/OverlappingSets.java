package year2022.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class OverlappingSets {
    private static final String FILE_PATH = "src/main/java/year2022/day4/input.txt";
    static Map<Integer, SetPair> setPairsMap = new HashMap<>();
    static int count = 0;
    static int overlaps = 0;


    public static void main(String[] args) {
        readDataAndCreateDataStructures();

        setPairsMap.values().forEach(pair -> {
            Set<Integer> s1 = pair.getSet1();
            Set<Integer> s2 = pair.getSet2();

            Set<Integer> s3 = pair.getSet1();
            Set<Integer> s4 = pair.getSet2();

            s1.retainAll(s2);
            s4.retainAll(s3);

            if (s1.size() > 0) {
                overlaps++;
            } else if (s4.size() > 0) {
                overlaps++;
            }
        });
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
