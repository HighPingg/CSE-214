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
            if (!nextLine.trim().equals("") && !nextLine.contains("#")) {

                // Gets the number of spaces before the first character by using a counting
                // variable and looping through until we reach the end of the line or it reaches
                // the first non-space character and then divding the counter by SPACE_COUNT.
                int spaceCount = 0;
                while (spaceCount <= nextLine.length() && nextLine.charAt(spaceCount) == ' ') {
                    spaceCount++;
                }
                spaceCount /= SPACE_COUNT;

                System.out.println(stack.size());

                // while indents is less than size of stack
                while (spaceCount < stack.size()) {

                    // if indents is 0 close file and return the total complexity of stack.top.
                    if (spaceCount == 0) {
                        fileIn.close();
                        return stack.pop().totalComplexity();
                    } else {
                        CodeBlock oldTop = stack.pop();
                        Complexity oldTopComplexity = oldTop.totalComplexity();

                        System.out.println(oldTopComplexity);

                        // if oldTopComplexity is higher order than stack.top's highest sub-complexity
                        // stack.top's highest sub-complexity = oldTopComplexity
                        if (oldTopComplexity.compare(stack.peek().getHighestSubComplexity()) == 1) {
                            stack.peek().setHighestSubComplexity(oldTopComplexity);
                        }
                    }
                }

                // if line contains a keyword

                // Loops through all elements of the enum BLOCK_TYPES in CodeBlock.java until a
                // keyword matches the first word in the line or there are no more elements
                CodeBlock.BLOCK_TYPES keyword = null;
                for (CodeBlock.BLOCK_TYPES type : CodeBlock.BLOCK_TYPES.values()) {
                    if (keyword == null) {

                        // adds a space after to make sure it is a keyword rather than part
                        // of another word
                        String keywordWithSpaces = type.getType() + " ";

                        if (nextLine.trim().length() > keywordWithSpaces.length()
                                && nextLine.trim().substring(0, keywordWithSpaces.length()).equals(keywordWithSpaces)) {
                            keyword = type;
                            System.out.println(keywordWithSpaces);
                        }
                    }
                }

                if (keyword != null) {
                    if (keyword == CodeBlock.BLOCK_TYPES.FOR) {

                        // TODO: IMPLEMENT NAME THING
                        String name = "";

                        Complexity blockComplexity;
                        Complexity highestSubComplexity = new Complexity();

                        if (nextLine.substring(nextLine.length() - 7).equals(" log_N:")) {
                            blockComplexity = new Complexity(0, 1);
                        } else {
                            blockComplexity = new Complexity(1, 0);
                        }

                        CodeBlock newCodeBlock = new CodeBlock(name, blockComplexity, highestSubComplexity);
                        stack.push(newCodeBlock);

                    } else if (keyword == CodeBlock.BLOCK_TYPES.WHILE) {

                        String name = "";

                        Complexity blockComplexity = new Complexity();
                        Complexity highestSubComplexity = new Complexity();

                        // gets the loopVariable by taking the first reference to the String " while "
                        // with 2 spaces and going to the beginning of the next word, and adding to
                        // loopVarible until the end of that word.
                        String loopVariable = "";
                        int index = nextLine.indexOf(" while ") + 7;

                        while (index < nextLine.length() && nextLine.charAt(index) != ' ') {
                            loopVariable += nextLine.charAt(index);
                            index++;
                        }

                        // System.out.println("loopVar: " + loopVariable + "end");

                        // Push the newCodeBlock onto the stack with the loopVariable
                        CodeBlock newCodeBlock = new CodeBlock(name, blockComplexity, highestSubComplexity);
                        newCodeBlock.setLoopVariable(loopVariable);
                        stack.push(newCodeBlock);
                    } else {

                        // Push an O(1) CodeBlock onto the stack
                        String name = "";

                        Complexity blockComplexity = new Complexity();
                        Complexity highestSubComplexity = new Complexity();

                        CodeBlock newCodeBlock = new CodeBlock(name, blockComplexity, highestSubComplexity);
                        stack.push(newCodeBlock);
                    }

                    // If the loopVariable actually has a value, that means that top is a while loop
                } else if (!stack.isEmpty() && stack.peek().getLoopVariable() != null) {
                    String updateString = " " + stack.peek().getLoopVariable();

                    if (nextLine.contains(updateString + " -= 1")) {
                        Complexity blockComplexity = stack.peek().getBlockComplexity();

                        blockComplexity.setNPower(1);

                        stack.peek().setBlockComplexity(blockComplexity);
                    }

                    if (nextLine.contains(updateString + " /= 2")) {
                        Complexity blockComplexity = stack.peek().getBlockComplexity();

                        blockComplexity.setLogPower(1);

                        stack.peek().setBlockComplexity(blockComplexity);
                        System.out.println("done");
                    }
                }
            }
        }

        while (stack.size() > 1) {
            CodeBlock oldTop = stack.pop();
            Complexity oldTopComplexity = oldTop.totalComplexity();

            if (oldTopComplexity.compare(stack.peek().getHighestSubComplexity()) == 1) {
                stack.peek().setHighestSubComplexity(oldTopComplexity);
            }
        }

        fileIn.close();

        return stack.peek().getHighestSubComplexity();
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
                    e.printStackTrace();
                }
            }

        } while (!userQuit);

        System.out.println("\n\nProgram terminating successfully...");
        input.close();
    }
}