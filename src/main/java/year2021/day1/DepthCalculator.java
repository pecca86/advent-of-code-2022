package year2021.day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.IntStream;

public class DepthCalculator {
    public static void main(String[] args) {
        List<Integer> results = depthMap();

        long timesLargerThanPrev = getTimesLargerThanPrev.apply(results);

        System.out.println(timesLargerThanPrev);

        int timesIncreased = getTimesIncreased();
        System.out.println(timesIncreased);
    }

    private static Function<List<Integer>, Long> getTimesLargerThanPrev =
        list -> list
                .stream()
                .filter(entry -> entry > 0)
                .mapToLong(Integer::intValue)
                .sum();


    private static int getTimesIncreased() {
        int timesIncreased = 0;
        List<Integer> valueList = getValuesToList();

        for (int i = 0; i < valueList.size(); i++) {
            try {
                if (valueList.get(i) < valueList.get(i+3)) {
                    timesIncreased++;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }

        }
        return timesIncreased;
    }

    private static List<Integer> getValuesToList() {
        List<Integer> results = new ArrayList<>();
        try (Scanner input = new Scanner(new BufferedReader(new FileReader("src/main/java/year2021/day1/input.txt")))) {
            while (input.hasNextLine()) {
                results.add(Integer.parseInt(input.nextLine()));
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    private static List<Integer> depthMap() {
        List<Integer> resultList = new ArrayList<>();
        Integer oldValue = null;
        try(Scanner input = new Scanner(new BufferedReader(new FileReader("src/main/java/year2021/day1/input.txt")))) {
            while(input.hasNextLine()) {
                if (oldValue == null) {
                    oldValue = Integer.parseInt(input.nextLine());
                }
                int newValue = Integer.parseInt(input.nextLine());

                if (oldValue < newValue) {
                    resultList.add(1);
                } else {
                    resultList.add(-1);
                }
                oldValue = newValue;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
