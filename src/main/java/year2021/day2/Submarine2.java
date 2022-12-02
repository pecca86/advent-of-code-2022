package year2021.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Submarine2 {

    private static final String FILE_PATH = "src/main/java/year2021/day2/input.txt";
    private int depth;
    private int horizontal;
    private int aim;

    public Submarine2() {
    }

    private void decreaseDepth(int amount) {
        this.aim -= amount;
    }

    private void increaseDepth(int amount) {
        this.aim += amount;
    }

    private void moveForward(int amount) {
        this.horizontal += amount;
        this.depth += amount * aim;
    }

    private void move(String direction, int amount) {
        switch (direction) {
            case "up" -> this.decreaseDepth(amount);
            case "down" -> this.increaseDepth(amount);
            case "forward" -> this.moveForward(amount);
            default -> {}
        }
    }

    public Long calculateEndCoordinates() {
        return (long) this.horizontal * (long) this.depth;
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

    public static void main(String[] args) {
        Submarine2 sub = new Submarine2();
        sub.readInstructions();
        System.out.println(sub.calculateEndCoordinates());
    }

    private static Scanner openFile() throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(FILE_PATH)));
    }
}
