package year2022.day6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */

public class Decoder {
    private static int startOfPacket;
    private static int startOfMessage;

    private static final String INPUT_FILE = "src/main/java/year2022/day6/input.txt";


    public static void main(String[] args) {
        String message = readInputFromFile();

        calculateStartOfPacket(message);
        calculateStartOfMessage(message);

        System.out.println("FOUND START OF PACKET AT INDEX: " + startOfPacket);
        System.out.println("FOUND START OF MESSAGE AT INDEX: " + startOfMessage);

    }

    private static void calculateStartOfPacket(String message) {
        for ( int i = 0 ; i < message.length(); i++) {
            try {
                String subMessage = message.substring(i, i+4);
                if (!hasDuplicateLetters(subMessage)) {
                    startOfPacket = i+4;
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
    }

    private static void calculateStartOfMessage(String message) {
        for ( int i = 0 ; i < message.length(); i++) {
            try {
                String subMessage = message.substring(i, i+14);
                if (!hasDuplicateLetters(subMessage)) {
                    startOfMessage = i+14;
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
    }

    public static boolean hasDuplicateLetters(String str) {
        return str.chars()
                .boxed()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count > 1);
    }



    // === ALL BELOW RELATES TO READING INPUT AND CREATING DATA STRUCTURES
    private static String readInputFromFile() {
        String message = "";
        try (Scanner input = openFile(INPUT_FILE)) {
            while (input.hasNextLine()) {
                message = input.nextLine();
            }
            return message;
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    private static Scanner openFile(String filePath) throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(filePath)));
    }
}
