package year2021.day3;

import year2021.day2.Submarine2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CalculateFromBits {
    private static final String FILE_PATH = "src/main/java/year2021/day3/input.txt";

    private static List<Integer> bitArray;

    public static void main(String[] args) {

        List<Integer> resultBits = calculateMostCommonBit(readInstructions(), 0);
        System.out.println(resultBits.size());
        calculatePowerConsumption(resultBits);
    }

    private static void calculatePowerConsumption(List<Integer> resultBits) {
        Long gammaRate = Long.parseLong(resultBits.stream()
                                .map(bit -> String.valueOf(bit))
                                .collect(Collectors.joining("")), 2);

        Long epsilonRate = Long.parseLong(resultBits.stream()
                .map(b -> b == 1 ? 0 : 1)
                .map(newBit -> String.valueOf(newBit))
                .collect(Collectors.joining("")), 2);

        Long powerConsumption = gammaRate * epsilonRate;

        System.out.println(powerConsumption);
    }

    private static List<Integer> calculateMostCommonBit(List<String> bitList, int bitIndex) {
        int one = 0;
        int zero = 0;

        if (bitArray == null) {
            bitArray = new ArrayList<>();
        }

        for (int i = 0; i <= bitList.size(); i++) {
            if (one >= bitList.size() / 2) {
                bitArray.add(1);
                break;
            }
            if (zero > bitList.size() / 2) {
                bitArray.add(0);
                break;
            }
            if (bitList.get(i).charAt(bitIndex) == '1') {
                one++;
            } else {
                zero++;
            }
        }

        bitIndex++;
        if (bitIndex >= bitList.get(0).length()) {
            return bitArray;
        }

        return calculateMostCommonBit(bitList, bitIndex);
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
