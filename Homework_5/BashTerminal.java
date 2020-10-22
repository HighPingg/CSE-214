package Homework_5;

import java.util.Scanner;

public class BashTerminal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        DirectoryTree directory = new DirectoryTree();
        System.out.println("Starting bash terminal.");

        boolean userQuit = false;
        do {
            System.out.print("$ ");
            String userString = input.nextLine();
            String[] userIn = userString.split(" ");
            try {
                switch (userIn[0]) {
                    case "pwd":

                        break;

                    case "ls":

                        if (userIn.length == 1) {
                            System.out.println(directory.listDirectory());
                        } else if (userIn[1].equals("-R") && userIn.length == 2) {
                            directory.printDirectoryTree();
                        } else
                            throw new IllegalArgumentException(String
                                    .format("%s is not recognized as an internal or external command.", userString));

                        break;
                    case "cd":
                        directory.changeDirectory(userIn[1]);
                        break;
                    case "mkdir":

                        directory.makeDirectory(userIn[1]);

                        break;
                    case "touch":

                        directory.makeFile(userIn[1]);

                        break;
                    case "exit":
                        userQuit = true;
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (!userQuit);

        input.close();
        System.out.println("Program terminating normally...");
    }
}
