package Homework_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Contain main methods. Runs the program and is the "main" file in the program.
 * 
 * @author Vincent Zheng
 *      <li>Solar_ID: 113469839</li>
 *      <li>Email: vincent.zheng@stonybrook.edu</li>
 *      <li>Assignment: 3</li>
 *      <li>Course: CSE 214</li>
 *      <li>Recitation: R02</li>
 *      <li>TA: William Simunek</li>
 */
public class PythonTracer {

    // Determines the indentation of each statement
    public static final int SPACE_COUNT = 4;

    /**
     * Returns an array of <code>String</code> where <code>str</code> is split
     * into different parts using <code>c</code> (Ex: charSplit("f.2f.3khn.2",
     * '.') == {"f", "2f", "3khn", "2"}).This is basically the
     * <code>String.split()</code> method, but it takes in a char and it also
     * works with '.' while <code>String.split()</code> doesn't.
     * 
     * @param str the <code>String</code> we are trying to split up
     * 
     * @param c   the <code>char</code> where we split up <code>str</code>
     * 
     * @return the array where <code>str</code> is split up into different parts
     *         using <code>c</code>
     */
    public static String[] charSplit(String str, char c) {

        // Goes through the string and determine how many matches we have with 
        // the character. matchCount + 1 will be the number of elements in the
        // String array
        int matchCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                matchCount++;
            }
        }

        // Goes through the String again character by character and adds to the
        // array at index counter. When we encounter a match with the
        // characcter, we add 1 to counter and move on to the next index.
        String[] arr = new String[matchCount + 1];
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            // if this spot in the array is null, then we need to initialize it
            // with an empty String otherwise it will throw a null exception
            if (arr[counter] == null)
                arr[counter] = "";

            // if the charater doesn't match, then append to the array at the
            // current index, otherwise increment counter.
            if (str.charAt(i) != c)
                arr[counter] += str.charAt(i);
            else
                counter++;
        }

        return arr;
    }

    /**
     * Opens the indicated file and traces through the code of the Python
     * function contained within the file, returning the Big-Oh order of
     * complexity of the function. During operation, the stack trace should be
     * printed to the console as code blocks are pushed to/popped from the
     * stack.
     * 
     * @param filename the name/path to the file we are connecting to
     * 
     * @return the overall complexity of the code inside the file
     * 
     * @throws IllegalArgumentException thrown if <code>filename</code> is
     *                                  <code>null</code>
     * 
     * @throws FileNotFoundException    thrown if the <code>Scanner</code> is
     *                                  unable to connect to the
     *                                  <code>filename</code>
     */
    public static Complexity traceFile(String filename)
        throws IllegalArgumentException, FileNotFoundException
    {
        if (filename == null) {
            throw new IllegalArgumentException("The file name given in null!");
        }

        // Creates new Scanner and tries to connect it to the selected file. If
        // it's unable to connect, then throws a FileNotFoundException
        Scanner fileIn;

        try {
            fileIn = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(
                            "The file was not found in the given path!");
        }

        // Creates a new empty stack
        BlockStack stack = new BlockStack();

        // previousName is neccessary for the print stack trace feature.
        // Whenever  we exit a codeblock, we lost the name of that block and
        // therefore cannot keep track of the name of the codeblock we are
        // entering next. previousName stores that name so whenever we detect an
        // exited codeblock, we can update the name of the next codeblock
        // accordingly.
        String previousName = "";

        while (fileIn.hasNextLine()) {
            String nextLine = fileIn.nextLine();

            // if line is not empty and line does not start with '#'. Trim to
            // not account for leading whitespaces.
            if (!nextLine.trim().equals("") && !nextLine.contains("#")) {

                // Gets the number of spaces before the first character by using
                // a counting variable and looping through until we reach the
                // end of the line or it reaches the first non-space character
                // and then divding the counter by SPACE_COUNT.
                int spaceCount = 0;
                while (spaceCount <= nextLine.length() &&
                                        nextLine.charAt(spaceCount) == ' ') {
                    spaceCount++;
                }
                spaceCount /= SPACE_COUNT;

                // System.out.println(stack.size());

                // while indents is less than size of stack
                while (spaceCount < stack.size()) {

                    // if indents is 0 close file and return the total
                    // complexity of stack.top.
                    if (spaceCount == 0) {
                        System.out.printf("\nLeaving block %s.\n",
                                            stack.peek().getName());

                        fileIn.close();
                        return stack.pop().totalComplexity();
                    } else {
                        CodeBlock oldTop = stack.pop();
                        Complexity oldTopComplexity = oldTop.totalComplexity();

                        // System.out.println(oldTopComplexity);

                        // if oldTopComplexity is higher order than stack.top's
                        // highest sub-complexity stack.top's highest
                        // sub-complexity = oldTopComplexity
                        if (oldTopComplexity.compare(
                                stack.peek().getHighestSubComplexity()) == 1)
                        {
                            stack.peek().setHighestSubComplexity(
                                                        oldTopComplexity);

                            System.out.printf(
                                "\nLeaving block %s, updating block %s:\n",
                                    oldTop.getName(), stack.peek().getName());
                            System.out.printf(
                                    "\tBlock %s:\t\t\tblock complexity = %s\t\t"
                                    + "\thighest sub-complexity = %s\n",
                                    stack.peek().getName(),
                                    stack.peek().getBlockComplexity(),
                                    stack.peek().getHighestSubComplexity());
                        } else {
                            System.out.printf(
                                "\nLeaving block %s, nothing to update.\n",
                                oldTop.getName());
                            System.out.printf(
                                    "\tBlock %s:\t\t\tblock complexity = %s\t\t"
                                    + "\thighest sub-complexity = %s\n",
                                    stack.peek().getName(),
                                    stack.peek().getBlockComplexity(),
                                    stack.peek().getHighestSubComplexity());
                        }

                    }
                }

                // if line contains a keyword

                // Loops through all elements of the enum BLOCK_TYPES in
                // CodeBlock.java until a keyword matches the first word in the
                // line or there are no matches
                CodeBlock.BLOCK_TYPES keyword = null;
                for (CodeBlock.BLOCK_TYPES type : 
                                        CodeBlock.BLOCK_TYPES.values())
                {
                    if (keyword == null) {

                        // adds a space after to make sure it is a keyword 
                        // rather than part of another word
                        String keywordCheck = type.getType();

                        //System.out.println(nextLine.trim().split(" ")[0]+ " "
                        //+ nextLine.trim().split(" ")[0].equals(keywordCheck));

                        // Matching the keyword to the first word in the line.
                        if (nextLine.trim().split(" ")[0].equals(keywordCheck))
                        {
                            keyword = type;
                        }
                    }
                }

                //System.out.println(keyword);

                // If a keyword is null, that means there is no keyword and its
                // skips that line. Else there is a keyword on the line.
                if (keyword != null) {

                    String name = "";

                    // If the stack is empty, then we know it's the opening
                    // "def ():" and set name to 1
                    if (stack.isEmpty()) {

                        name = "1";

                    } else {

                        // splits the current and previous name and splits it
                        String[] currentNameArr = charSplit(
                                                stack.peek().getName(), '.');
                        String[] previousNameArr = charSplit(previousName, '.');

                        // for (String string : previousNameArr) {
                        // System.out.println(string);
                        // }

                        // System.out.println(currentNameArr.length);
                        // System.out.println(previousName + " " +
                        // previousNameArr.length);

                        // if the previous name's length is greater than the
                        // current, then we know that we have exited a codeblock
                        // and we can go back out of the codeblock and add 1 to
                        // the name of the current codeblock.
                        if (currentNameArr.length < previousNameArr.length) {

                            // Goes through the previousName array and appends
                            // those values to name until we reach the length of
                            // the currentName array. This is the codeblock we
                            // backed all the way out to. We then would add one
                            // to the last reference in the previousName and
                            // append it to the name of the next codeblock.
                            for (int i = 0; i <= currentNameArr.length; i++) {
                                if (i == currentNameArr.length)
                                    name += String.valueOf(Integer.parseInt(
                                                    previousNameArr[i]) + 1);
                                else
                                    name += previousNameArr[i] + ".";
                            }

                            // Else we know that we entered a sub-codeblock and
                            // we append ".1" to the previous name
                        } else {
                            name = stack.peek().getName() + ".1";
                        }
                    }

                    // Updates the previousName to the name of the new codeblock
                    previousName = name;

                    Complexity blockComplexity;
                    Complexity highestSubComplexity = new Complexity();
                    String loopVariable = null;

                    // If the keyword is a for loop
                    if (keyword == CodeBlock.BLOCK_TYPES.FOR) {

                        // detects whether or not the last word mathches log_N:
                        // else it's N: and changes the blockComplexity
                        // accordingly
                        if (nextLine.substring(nextLine.length() - 7).equals(
                                                                " log_N:"))
                        {
                            blockComplexity = new Complexity(0, 1);
                        } else {
                            blockComplexity = new Complexity(1, 0);
                        }

                    } else if (keyword == CodeBlock.BLOCK_TYPES.WHILE) {

                        blockComplexity = new Complexity();

                        // gets the loopVariable by taking the first reference
                        // to the String " while " with 2 spaces and going to
                        // the beginning of the next word, and adding to
                        // loopVarible until the end of that word.
                        loopVariable = "";
                        int index = nextLine.indexOf(" while ") + 7;

                        while (index < nextLine.length() &&
                                    nextLine.charAt(index) != ' ')
                        {
                            loopVariable += nextLine.charAt(index);
                            index++;
                        }

                        // System.out.println("loopVar: " + loopVariable +
                        // "end");

                        // Push the newCodeBlock onto the stack with the
                        // loopVariable
                    } else {

                        // Push an O(1) CodeBlock onto the stack
                        blockComplexity = new Complexity();
                    }

                    // Push that code block onto the stack
                    CodeBlock newCodeBlock = new CodeBlock(
                                name, blockComplexity, highestSubComplexity);
                    newCodeBlock.setLoopVariable(loopVariable);
                    stack.push(newCodeBlock);

                    // Outputs the entering new block info
                    System.out.printf("\nEntering block %s \"%s\":\n", name,
                                                        keyword.getType());
                    System.out.printf(
                        "\tBlock %s:\t\t\tblock complexity = %s\t\t\thighest su"
                        + "b-complexity = %s\n", name, blockComplexity,
                        highestSubComplexity);

                    // If the loopVariable actually has a value, that means that
                    // top is a while loop
                } else if (!stack.isEmpty() && 
                            stack.peek().getLoopVariable() != null)
                {

                    // Store the loop variable and puts a space right before
                    String updateString = " " + stack.peek().getLoopVariable();

                    // if the line is "loopVariable -= 1", then sets the nPower
                    // of the blockComplexity to 1
                    if (nextLine.contains(updateString + " -= 1")) {
                        Complexity blockComplexity = 
                                            stack.peek().getBlockComplexity();

                        blockComplexity.setNPower(1);

                        stack.peek().setBlockComplexity(blockComplexity);

                        System.out.printf("\nFound update statement, updating b"
                                + "lock %s\n", stack.peek().getName());
                        System.out.printf("\tBlock %s:\t\t\tblock complexity = "
                                + "%s\t\t\thighest sub-complexity = %s\n",
                                stack.peek().getName(),
                                stack.peek().getBlockComplexity(),
                                stack.peek().getHighestSubComplexity());
                    }

                    // if the line is "loopVariable /= 2", then sets the
                    // logPower of the blockComplexity to 1
                    if (nextLine.contains(updateString + " /= 2")) {
                        Complexity blockComplexity = 
                                        stack.peek().getBlockComplexity();

                        blockComplexity.setLogPower(1);

                        stack.peek().setBlockComplexity(blockComplexity);
                        // System.out.println("done");
                        System.out.printf("\nFound update statement, updating b"
                                + "lock %s\n", stack.peek().getName());
                        System.out.printf("\tBlock %s:\t\t\tblock complexity = "
                                + "%s\t\t\thighest sub-complexity = %s\n",
                                stack.peek().getName(),
                                stack.peek().getBlockComplexity(),
                                stack.peek().getHighestSubComplexity());
                    }
                }
            }
        }

        // reduces the all the elements currently inside the stack until there's
        // only 1 left
        while (stack.size() > 1) {
            CodeBlock oldTop = stack.pop();
            Complexity oldTopComplexity = oldTop.totalComplexity();

            if (oldTopComplexity.compare(
                        stack.peek().getHighestSubComplexity()) == 1)
            {
                stack.peek().setHighestSubComplexity(oldTopComplexity);

                System.out.printf("\nLeaving block %s, updating block %s:\n",
                            oldTop.getName(), stack.peek().getName());
                System.out.printf("\tBlock %s:\t\t\tblock complexity = %s\t\t\t"
                        + "highest sub-complexity = %s\n",
                        stack.peek().getName(),
                        stack.peek().getBlockComplexity(),
                        stack.peek().getHighestSubComplexity());
            } else {
                System.out.printf("\nLeaving block %s, nothing to update.\n",
                            oldTop.getName());
                System.out.printf("\tBlock %s:\t\t\tblock complexity = %s\t\t\t"
                        + "highest sub-complexity = %s\n",
                        stack.peek().getName(),
                        stack.peek().getBlockComplexity(),
                        stack.peek().getHighestSubComplexity());
            }
        }

        System.out.printf("\nLeaving block %s.\n", stack.peek().getName());

        // close the file and returns the highest sub complexity of the function
        fileIn.close();

        return stack.peek().getHighestSubComplexity();
    }

    /**
     * Main method of the program. Prompts the user for the name of a file
     * containing a single Python function, determines its order of complexity,
     * and prints the result to the console.
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

                // If the input is "quit", then flips userQuit to true which
                // stops the loop
                userQuit = true;
            } else {

                try {

                    // All the errors are thrown here. If traceFile runs
                    // successfull, then it will print out the overall
                    // complexity.
                    Complexity overall = traceFile(userIn);
                    System.out.println("\n\nOverall complexity of matrix_multip"
                                + "ly: " + overall.toString());

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