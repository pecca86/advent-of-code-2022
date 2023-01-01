package year2022.day7.trees;

import year2022.day7.File;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Solve {
    private static final String INPUT_FILE = "src/main/java/year2022/day7/input.txt";
    private static final String COMMAND = "$";
    private static final String DIR = "dir";
    private static final String CHANGE_DIRECTORY = "cd";
    private static final TreeNode ROOT_FOLDER = new TreeNode();

    static TreeNode currentDirectory;
    static TreeNode parentDirectory;


    public static void main(String[] args) {
        ROOT_FOLDER.name = "/";
        currentDirectory = ROOT_FOLDER;
        readInput();
    }

    public static void readInput() {
        readInputFromFile();
    }


    // === ALL BELOW RELATES TO READING INPUT AND CREATING DATA STRUCTURES
    private static void readInputFromFile() {
        try (Scanner input = openFile(INPUT_FILE)) {
            while (input.hasNextLine()) {
                String[] command = input.nextLine().split(" ");
                interpretCommand(command);
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Scanner openFile(String filePath) throws FileNotFoundException {
        return new Scanner(new BufferedReader(new FileReader(filePath)));
    }

    private static void interpretCommand(String[] command) {
        if (command[0].equals(COMMAND)) {
            executeCommand(command);
        } else if (command[0].equals(DIR)) {
            createNewDirectoryInsideCurrentFolder(command);
        } else {
            createNewFileInsideCurrentFolder(command);
        }
    }

    private static void executeCommand(String[] command) {
        if (command[1].equals(CHANGE_DIRECTORY)) {
            changeDirectory(command[2]);
        } else {
            listFilesInCurrentFolder(command[1]);
        }
    }

    private static void createNewDirectoryInsideCurrentFolder(String[] command) {
        System.out.println("Creating new directory: " + command[1] + " in " + currentDirectory.name);
        TreeNode node = new TreeNode(); //(command[1]);
        node.name = command[1];
        currentDirectory.addChild(node);
    }

    private static void createNewFileInsideCurrentFolder(String[] command) {
        System.out.println("Adding new file: " + command[1] + " in " + currentDirectory.name);
        File file = new File(Integer.parseInt(command[0]), command[1]);
        currentDirectory.addFile(file);
    }

    private static void changeDirectory(String folderName) throws RuntimeException {
        System.out.println("Changing directory to: " + folderName);
        if (ROOT_FOLDER.name.equals(folderName)) {
            return;
        } else if (folderName.equals("..")) {
            currentDirectory = currentDirectory.parent;
        } else {
            parentDirectory = currentDirectory;
            currentDirectory = Optional.of(currentDirectory.children.stream()
                    .filter(f -> f.name.equals(folderName))
                    .findFirst())
                    .get()
                    .orElse(null);
            if (currentDirectory != null) {
                currentDirectory.parent = parentDirectory;
            } else {
                System.out.println("FOLDER NOT FOUND!");
            }
        }
    }

    private static void listFilesInCurrentFolder(String s) {
        System.out.println("Listing files in folder...");
    }
}
