package Homework_5;

import java.util.Scanner;

/**
 * Contains a single <code>main</code> method which allows a user to interact
 * with a file system implemented by an instance of <code>DirectoryTree</code>
 * using the following commands.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *         <li><b>Solar_ID:</b> 113469839</li>
 *         <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *         <li><b>Assignment:</b> 5</li>
 *         <li><b>Course</b>: CSE 214</li>
 *         <li><b>Recitation</b>: R02</li>
 *         <li><b>TA</b>: William Simunek</li>
 */
public class BashTerminal {

    /**
     * Runs a program which takes user input and builds a DirectoryTree using
     * the valid bash commands.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        DirectoryTree directory = new DirectoryTree();
        System.out.println("Starting bash terminal.");

        // Flag variable turns true when user enters "exit" and stops the loop.
        boolean userQuit = false;
        do {
            System.out.print("[vinzheng@ngnlseason2(´д｀)] $ ");
            String userString = input.nextLine();
            String[] userIn = userString.split(" ");
            try {
                switch (userIn[0]) {

                    // If user entered "pwd", just call presentWorkingDirectory.
                    case "pwd":

                        System.out.println(directory.presentWorkingDirectory());

                        break;
                    case "ls":

                        // If user entered "ls", checks whether or not they also
                        // put "-R". If yes then print tree, else print
                        // children.
                        if (userIn.length == 1) {
                            System.out.println(directory.listDirectory());
                        } else if (userIn[1].equals("-R") && userIn.length == 2)
                        {
                            directory.printDirectoryTree();
                        } else {
                            throw new IllegalArgumentException(
                                            String.format(
                                            "%s contains unknown arguments.",
                                            userString));
                        }

                        break;
                    case "cd":

                        // If user entered "cd", check second argument and do
                        // the according action.
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
                                            String.format(
                                            "%s has more than one arguments!",
                                            userString));
                        }

                        break;
                    case "find":

                        // If user entered "find", just call the find method.
                        if (userIn.length == 2) {
                            System.out.println(directory.findInTree(userIn[1]));
                        } else
                            throw new IllegalArgumentException(
                                            String.format(
                                            "%s has more than one arguments!",
                                            userString));

                        break;
                    case "mkdir":

                        // If user entered "mkdir", just call the makeDirectory
                        // method.
                        if (userIn.length == 2)
                            directory.makeDirectory(userIn[1]);
                        else
                            throw new IllegalArgumentException(
                                            String.format(
                                            "%s has more than one arguments!", 
                                            userString));

                        break;
                    case "touch":

                        // If user entered "touch", just call the makeFile
                        // method.
                        if (userIn.length == 2)
                            directory.makeFile(userIn[1]);
                        else
                            throw new IllegalArgumentException(
                                            String.format(
                                            "%s has more than one arguments!",
                                            userString));

                        break;
                    case "mv":

                        // If user entered "mv", just call the moveDirectory
                        // method and use the 2 paths.
                        if (userIn.length == 3)
                            directory.moveDirectory(userIn[1], userIn[2]);
                        else
                            throw new IllegalArgumentException(
                                            String.format(
                                            "%s has more than two arguments!",
                                            userString));
                        break;
                    case "exit":

                        userQuit = true;

                        break;
                    default:
                        throw new IllegalArgumentException(
                                String.format("'%s' is not recognized as an int"
                                    + "ernal or external command.", userIn[0]));

                }
            } catch (FullDirectoryException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (NotADirectoryException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (!userQuit);

        input.close();
        System.out.println("Program terminating normally...");
    }
}
