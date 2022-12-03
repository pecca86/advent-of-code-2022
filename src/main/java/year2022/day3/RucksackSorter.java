package year2022.day3;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class RucksackSorter {
    private static Map<Character, Integer> charToPointsMap;
    private static final String FILE_PATH = "src/main/java/year2022/day3/input.txt";
    private static final List<Integer> pointList = new ArrayList<>();
    private static final List<Integer> secondChallengePointList = new ArrayList<>();
    private static final List<String> rucksacks = new ArrayList<>();
    private static final List<Character> groupBadgeItem = new ArrayList<>();

    private static final Map<Integer, List<String>> groupMap = new HashMap<>();

    public static void main(String[] args) {
        // == PART 1 ==
        readInputFileToLists();
        initializeCharacterMaps();

        processRucksackList();

        int result = calculatePoints();
        System.out.println("Result of problem no.1: " + result);

        // == PART 2 ==
        generateGroupMap();
        processGroupsToFindCommonItem();

        calculatePointsToSecondChallengeList();

        int finalResult = secondChallengePointList.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Result of problem no.2: " + finalResult);
    }

    private static void calculatePointsToSecondChallengeList() {
        groupBadgeItem
                .forEach(e -> secondChallengePointList.add(charToPointsMap.get(e)));
    }

    private static void processGroupsToFindCommonItem() {
        groupMap.values()
                .forEach(RucksackSorter::insertCommonGroupItemToList);
    }

    private static void insertCommonGroupItemToList(List<String> group) {
        String intermediateResult = parseOutAllDifferencesFromBothHalves(group.get(0), group.get(1));
        String result = parseOutAllDifferencesFromBothHalves(intermediateResult, group.get(2));
        groupBadgeItem.add(result.charAt(0));
    }

    private static void generateGroupMap() {
        List<String> group = new ArrayList<>();
        int groupNumber = 0;
        for (int i = 0; i < rucksacks.size(); i++) {
            if (i > 1 && i % 3 == 0) {
                groupMap.put(groupNumber, group);
                group = new ArrayList<>();
                groupNumber++;
            }
            group.add(rucksacks.get(i));
        }

        if (!group.isEmpty()) {
            groupMap.put(groupNumber, group);
        }
    }

    private static int calculatePoints() {
        return pointList.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static void processRucksackList() {
        rucksacks
                .forEach(RucksackSorter::addToPointListIfSameCharacterFound);
    }

    private static void addToPointListIfSameCharacterFound(String targetString) {
        List<String> splitStrings = splitString(targetString);
        String firstHalf = splitStrings.get(0);
        String secondHalf = splitStrings.get(1);

        String resultString = parseOutAllDifferencesFromBothHalves(firstHalf, secondHalf);

        if (!resultString.isEmpty()) {
            pointList.add(charToPointsMap.get(resultString.charAt(0)));
        }
    }

    @Contract(pure = true)
    private static @NotNull String parseOutAllDifferencesFromBothHalves(@NotNull String firstHalf, @NotNull String secondHalf) {
        String regex = "[^"+ secondHalf +"]";
        return firstHalf.replaceAll(regex, "");
    }

    private static @NotNull List<String> splitString(@NotNull String targetString) {
        List<String> result = new ArrayList<>();
        result.add(targetString.substring(0,targetString.length() / 2));
        result.add(targetString.substring(targetString.length() / 2));
        return result;
    }

    private static void initializeCharacterMaps() {
        charToPointsMap = new HashMap<>();

        List<Character> lowerCaseAlphabet = Arrays.stream("abcdefghijklmnopqrstuvwxyz".split("")).map(e -> e.charAt(0)).toList();
        List<Character> upperCaseAlphabet = Arrays.stream("abcdefghijklmnopqrstuvwxyz".toUpperCase().split("")).map(e -> e.charAt(0)).toList();

        AtomicInteger counter = new AtomicInteger(1);
        lowerCaseAlphabet.forEach(character -> charToPointsMap.put(character, counter.getAndIncrement()));
        counter.set(27);
        upperCaseAlphabet.forEach(character -> charToPointsMap.put(character, counter.getAndIncrement()));
    }

    private static void readInputFileToLists() {
        try (Scanner input = openFile()) {
            while (input.hasNextLine()) {
                rucksacks.add(input.nextLine());
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Scanner openFile() throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(FILE_PATH)));
    }
}
