package year2021.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Submarine {

    private static final String FILE_PATH = "src/main/java/year2021/day2/input.txt";
    private int depth;
    private int horizontal;

    public Submarine() {
    }

    private void moveUpwards(int amount) {
        this.depth -= amount;
    }

    private void moveDownwards(int amount) {
        this.depth += amount;
    }

    private void moveForward(int amount) {
        this.horizontal += amount;
    }

    private void move(String direction, int amount) {
        switch (direction) {
            case "up" -> this.moveUpwards(amount);
            case "down" -> this.moveDownwards(amount);
            case "forward" -> this.moveForward(amount);
            default -> {
            }
        }
    }

    public int calculateEndCoordinates() {
        return this.horizontal * this.depth;
    }

    public void readInstructions() {
        try(Scanner input = openFile()) {
            while (input.hasNextLine()) {
                String instruction = input.nextLine();
                String[] splitInstruction = instruction.split(" ");
                this.move(splitInstruction[0], Integer.parseInt(splitInstruction[1]));
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static Scanner openFile() throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(FILE_PATH)));
    }


    public static void main(String[] args) {
        Submarine sub = new Submarine();
        sub.readInstructions();
        System.out.println(sub.calculateEndCoordinates());
    }
}
