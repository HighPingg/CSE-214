package Homework_5;

import java.util.Scanner;

public class BashTerminal {

    /**
     * Runs a program which takes user input and builds a DirectoryTree using the
     * valid bash commands.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        DirectoryTree directory = new DirectoryTree();
        System.out.println("Starting bash terminal.");

        // Flag variable turns true when user enters "exit" and stops the loop.
        boolean userQuit = false;
        do {
            System.out.print("$ ");
            String userString = input.nextLine();
            String[] userIn = userString.split(" ");
            try {
                switch (userIn[0]) {
                    case "pwd":

                        System.out.println(directory.presentWorkingDirectory());

                        break;
                    case "ls":

                        if (userIn.length == 1) {
                            System.out.println(directory.listDirectory());
                        } else if (userIn[1].equals("-R") && userIn.length == 2) {
                            directory.printDirectoryTree();
                        } else {
                            throw new IllegalArgumentException(
                                    String.format("%s contains unknown arguments.", userString));
                        }

                        break;
                    case "cd":

                        if (userIn.length == 2) {

                            switch (userIn[1]) {
                                case "..":

                                    directory.moveToPreviousNode();

                                    break;
                                case "/":

                                    directory.resetCursor();

                                    break;
                                default:

                                    directory.changeDirectory(userIn[1]);

                                    break;
                            }

                        } else {
                            throw new IllegalArgumentException(
                                    String.format("%s has more than one arguments!", userString));
                        }

                        break;
                    case "find":

                        if (userIn.length == 2) {
                            System.out.println(directory.findInTree(userIn[1]));
                        }

                        break;
                    case "mkdir":

                        if (userIn.length == 2)
                            directory.makeDirectory(userIn[1]);
                        else
                            throw new IllegalArgumentException(
                                    String.format("%s has more than one arguments!", userString));

                        break;
                    case "touch":

                        if (userIn.length == 2)
                            directory.makeFile(userIn[1]);
                        else
                            throw new IllegalArgumentException(
                                    String.format("%s has more than one arguments!", userString));

                        break;
                    case "mv":

                        if (userIn.length == 3)
                            directory.moveDirectory(userIn[1], userIn[2]);
                        else
                            throw new IllegalArgumentException(
                                    String.format("%s has more than two arguments!", userString));
                        break;
                    case "exit":

                        userQuit = true;

                        break;
                    default:
                        throw new Exception("");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (!userQuit);

        input.close();
        System.out.println("Program terminating normally...");
    }
}
