package Homework_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PythonTracer {

    // Determines the indentation of each statement
    public static final int SPACE_COUNT = 4;

    public static Complexity traceFile(String filename) throws IllegalArgumentException, FileNotFoundException {
        if (filename == null) {
            throw new IllegalArgumentException("The file name given in null!");
        }

        // Creates new Scanner and tries to connect it to the selected file. If it's
        // unable to connect, then throws a FileNotFoundException
        Scanner fileIn;

        try {
            fileIn = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The file was not found in the given path!");
        }

        // Creates a new empty stack
        BlockStack stack = new BlockStack();

        while (fileIn.hasNextLine()) {
            String nextLine = fileIn.nextLine();

            // if line is not empty and line does not start with '#'. Trim to not account
            // for leading whitespaces.
            if (!nextLine.trim().equals("") && nextLine.trim().charAt(0) != '#') {

                // Gets the number of spaces before the first character by using a counting
                // variable and looping through until we reach the end of the line or it reaches
                // the first non-space character and then divding the counter by SPACE_COUNT.
                int spaceCount = 0;
                while (spaceCount <= nextLine.length() && nextLine.charAt(spaceCount) == ' ') {
                    spaceCount++;
                }
                System.out.print(spaceCount +" ");
                spaceCount /= SPACE_COUNT;

                // while indents is less than size of stack
                while (spaceCount < stack.size()) {

                    // if indents is 0 close file and return the total complexity of stack.top.
                    if (spaceCount == 0) {
                        fileIn.close();
                        return stack.pop().getBlockComplexity();

                    } else {
                        CodeBlock oldTop = stack.pop();
                        Complexity oldTopComplexity = oldTop.getBlockComplexity();

                        // if oldTopComplexity is higher order than stack.top's highest sub-complexity
                        // stack.top's highest sub-complexity = oldTopComplexity
                        if (oldTopComplexity.compare(stack.peek().getHighestSubComplexity()) == 1) {
                            stack.peek().setHighestSubComplexity(oldTopComplexity);
                        }
                    }
                }

                //TODO: FINISH CHECKING KEYWORD ALGO
                // if line contains a keyword

                // Loops through all elements of the enum BLOCK_TYPES in CodeBlock.java until a
                // keyword matches or there are no more elements
                boolean containsKeyword = false;
                for (CodeBlock.BLOCK_TYPES type : CodeBlock.BLOCK_TYPES.values()) {
                    if (!containsKeyword) {

                        // adds a space before and after to make sure it is a keyword rather than part
                        // of another word
                        String keywordWithSpaces = " " + type.getType() + " ";

                        containsKeyword = nextLine.contains(keywordWithSpaces);
                        if(containsKeyword)
                            System.out.println(keywordWithSpaces);
                    }
                }
            }
        }

        fileIn.close();

        return new Complexity();
    }

    /**
     * Main method of the program. Prompts the user for the name of a file
     * containing a single Python function, determines its order of complexity, and
     * prints the result to the console.
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // exit condition (Turns true if user enters "quit" into the selection)
        boolean userQuit = false;

        do {
            // Takes the user input
            System.out.print("Please enter a file name (or 'quit' to quit): ");
            String userIn = input.nextLine();

            if (userIn.equals("quit")) {

                // If the input is "quit", then flips userQuit to true which stops the loop
                userQuit = true;
            } else {

                try {

                    // All the errors are thrown here. If traceFile runs successfull, then it will
                    // print out the overall complexity.
                    Complexity overall = traceFile(userIn);
                    System.out.println("Overall complexity of matrix_multiply: " + overall.toString());

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Unexpected Error: " + e);
                }
            }

        } while (!userQuit);

        System.out.println("\n\nProgram terminating successfully...");
        input.close();
    }

}