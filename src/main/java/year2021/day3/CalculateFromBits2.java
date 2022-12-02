package year2021.day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CalculateFromBits2 {
    private static final String FILE_PATH = "src/main/java/year2021/day3/input.txt";

    public static void main(String[] args) {
        calculate();
    }

    private static void calculate() {
        List<String> mostCommonList = readInstructions();
        List<String> leastCommonList = new ArrayList<>(mostCommonList);

        calculateMostCommonAndLeastCommon(mostCommonList, leastCommonList);
    }

    private static void calculateMostCommonAndLeastCommon(List<String> mostCommonList, List<String> leastCommonList) {
        String mostCommonBit;
        String leastCommonBit;

        for (int i = 0; i <= mostCommonList.get(0).length()-1; i++) {
            if (mostCommonList.size() < 2) {
                break;
            }
            mostCommonBit = filterAccordingToBit(mostCommonList, i);
            leastCommonBit = filterAccordingToBit(leastCommonList, i);
            leastCommonBit = leastCommonBit.equals("1") ? "0" : "1";

            mostCommonList = parseBitStringStartingWithIndexOf(mostCommonList, mostCommonBit, i);
            if (leastCommonList.size() > 1) {
                leastCommonList = parseBitStringStartingWithIndexOf(leastCommonList, leastCommonBit, i);
            }
        }

        Long mostCommon = Long.parseLong(mapBinaryToLong.apply(mostCommonList), 2);
        Long leastCommon = Long.parseLong(mapBinaryToLong.apply(leastCommonList), 2);

        printFinalResult(mostCommon, leastCommon);
    }

    private static void printFinalResult(Long mostCommon, Long leastCommon) {
        Long finalResult = mostCommon * leastCommon;
        System.out.println(finalResult);
    }

    public static Function<List<String>, String> mapBinaryToLong =
            binaryList -> binaryList.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(""));

    private static List<String> parseBitStringStartingWithIndexOf(List<String> targetList, String targetValue, int startIndex) {
        return targetList.stream()
                .filter(s -> s.substring(startIndex).startsWith(targetValue))
                .collect(Collectors.toList());
    }

    private static String filterAccordingToBit(List<String> bitList, int bitIndex) {
        int one = 0;
        int zero = 0;

        for (int i = 0; i <= bitList.size(); i++) {
            if (one >= bitList.size() / 2) {
                return "1";
            }
            if (zero > bitList.size() / 2) {
                return "0";
            }
            if (bitList.get(i).charAt(bitIndex) == '1') {
                one++;
            } else {
                zero++;
            }
        }

        return "1";
    }

    public static List<String> readInstructions() {
        List<String> result = new ArrayList<>();
        try(Scanner input = openFile()) {
            while (input.hasNextLine()) {
                String binaryString = input.nextLine();
                result.add(binaryString);
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }


    private static Scanner openFile() throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(FILE_PATH)));
    }

}
