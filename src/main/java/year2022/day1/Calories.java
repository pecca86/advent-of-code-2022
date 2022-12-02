package year2022.day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;

public class Calories {
    public static void main(String[] args) {
        List<Integer> caloriesPerElf = calculateIndividualCalories();
        System.out.println(Collections.max(caloriesPerElf));


        int totalAmountOfCaloriesTop3 = sumTopThreeInList.apply(caloriesPerElf);
        System.out.println(totalAmountOfCaloriesTop3);
    }

    public static Function<List<Integer>, Integer> sumTopThreeInList =
            list -> list.stream()
                    .sorted(Comparator.reverseOrder())
                    .limit(3)
                    .mapToInt(Integer::intValue)
                    .sum();

    private static List<Integer> calculateIndividualCalories() {
        List<Integer> myList = new ArrayList<>();
        int totalCalories = 0;
        try(Scanner input = new Scanner(new BufferedReader(new FileReader("src/main/java/day1/calories.txt")))) {
            while(input.hasNextLine()) {
                String value = input.nextLine();
                if (value.isEmpty()) {
                    myList.add(totalCalories);
                    totalCalories = 0;
                } else {
                    totalCalories += Integer.parseInt(value);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return myList;
    }
}
