package Homework_2;

import java.util.Scanner;

/**
 * Contains the main method which drives the application and uses all the
 * classes
 * 
 * @author Vincent Zheng
 *      Solar_ID: 113469839
 *      Email: vincent.zheng@stonybrook.edu
 *      Assignment: 2
 *      Course: CSE 214
 *      Recitation: R02
 *      TA: William Simunek
 */
public class TrainManager {

    /**
     * The main method runs a menu driven application which first creates an
     * empty TrainLinkedList object. The program prompts the user for a command
     * to execute an operation. Once a command has been chosen, the program may
     * ask the user for additional information if necessary, and performs the
     * operation.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        TrainLinkedList list = new TrainLinkedList();

        // exit condition (Turns true if user enters "Q" into the menu)
        boolean userQuit = false;

        do {
            System.out.println("\n\n(F) Cursor Forward");
            System.out.println("(B) Cursor Backward");
            System.out.println("(I) Insert Car After Cursor");
            System.out.println("(R) Remove Car At Cursor");
            System.out.println("(L) Set Product Load");
            System.out.println("(S) Search For Product");
            System.out.println("(T) Display Train");
            System.out.println("(M) Display Manifest");
            System.out.println("(D) Remove Dangerous Cars");
            System.out.println("(Q) Quit\n");

            System.out.print("\nEnter a Selection: ");

            // puts the user's choice in a switch case. Used .toUpperCase() to
            // make user input case insensitive
            switch (input.nextLine().toUpperCase()) {
                case "F":

                    // uses the cursor forward method in TrainLinkedList
                    try {

                        // IllegalStateException thrown here if cursor is at
                        // tail or list is empty
                        list.cursorForward();

                        System.out.println(
                                    "\nCursor Successfully Moved Forwards");

                    } catch (IllegalStateException e) {

                        System.out.println(e.getMessage());

                    } catch (Exception e) {

                        System.out.println("Unexpected Error: " + e);

                    }

                    break;

                case "B":

                    // uses the cursor backwards method in TrainLinkedList
                    try {
                        // IllegalStateException thrown here if cursor is at
                        // head or list is empty
                        list.cursorBackward();

                        System.out.println(
                                    "\nCursor Successfully Moved Backwards");

                    } catch (IllegalStateException e) {

                        System.out.println(e.getMessage());

                    } catch (Exception e) {

                        System.out.println("Unexpected Error: " + e);

                    }

                    break;

                case "I":

                    // uses the .insertAfterCursor() method in TrainLinkedList
                    try {
                        // get user inputs
                        System.out.print("Enter car length in meters: ");
                        String lengthString = input.nextLine();

                        // NumberFormatException is thrown here if lengthString
                        // can not be turned into a double
                        double length = Double.parseDouble(lengthString);

                        // checks if input is valid
                        TrainCar.validLength(length);

                        System.out.print("Enter car weight in tons: ");
                        String weightString = input.nextLine();

                        // NumberFormatException is thrown here if weightString
                        // can not be turned into a double
                        double weight = Double.parseDouble(weightString);

                        // checks if input is valid
                        TrainCar.validWeight(weight);

                        // insert into list throws IllegalArgumentException here
                        // if car is null
                        list.insertAfterCursor(new TrainCar(length, weight));

                        System.out.printf(
                            "\nNew train car %,.2f meters %,.2f tons "
                            + "inserted into train.", length, weight);

                    } catch (NumberFormatException e) {

                        System.out.println("\nYour input isn't a double value");

                    } catch (IllegalArgumentException e) {

                        System.out.println("\n" + e.getMessage());

                    } catch (Exception e) {

                        System.out.println("Unexpected Error: " + e);

                    }

                    break;

                case "R":

                    // uses the .removeCursor() method in TrainLinkedList
                    try {

                        // IllegalStateException is thrown here if the list is
                        // empty
                        TrainCar car = list.removeCursor();

                        if (car.getLoad() != null) {
                            ProductLoad load = car.getLoad();

                            System.out.println(
                                "Car successfully unlinked. The following "
                                + "load has been unlinked from the train:\n\n");

                            load.printLoad();
                        } else {
                            System.out.println(
                                "Empty car removed from the list.");
                        }

                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Unexpected Error: " + e);

                        e.printStackTrace();
                    }

                    break;

                case "L":

                    // checks if the list is not empty or has no load
                    if (list.size() != 0 &&
                            list.getCursorData().getLoad() == null)
                    {
                        try {

                            // gets user inputs
                            System.out.print("\nEnter product name: ");
                            String name = input.nextLine();

                            // checks if entered name is a valid name
                            // IllegalArgumentException is thrown here
                            ProductLoad.validName(name);

                            System.out.print("\nEnter product weight: ");

                            // NumberFormatException is thrown here if the input
                            // can not be turned into a double
                            double weight = Double.parseDouble(
                                                        input.nextLine());

                            // checks if entered weight is a valid weight
                            // IllegalArgumentException is thrown here
                            ProductLoad.validWeight(weight);

                            System.out.print("\nEnter product value: ");

                            // NumberFormatException is thrown here if the input
                            // can not be turned into a double
                            double value = Double.parseDouble(input.nextLine());

                            // checks if entered value is a valid value
                            // IllegalArgumentException is thrown here
                            ProductLoad.validValue(value);

                            System.out.print(
                                "\nEnter is product dangerous? (y/n): ");
                            String isDangerousString = input.nextLine();

                            switch (isDangerousString.toLowerCase()) {
                                case "y":

                                    // if user indicated that load is dangerous,
                                    // enter true into the constructor
                                    // IllegalArgumentException is thrown if any
                                    // of the values are invalid
                                    list.addLoadAtCursor(new ProductLoad(name,
                                                        weight, value, true));

                                    break;

                                case "n":

                                    // if user indicated that load is not
                                    // dangerous, enter false into the
                                    // constructor IllegalArgumentException is
                                    // thrown if any of the values are invalid
                                    list.addLoadAtCursor(new ProductLoad(name,
                                                        weight, value, false));

                                    break;

                                default:

                                    System.out.println(
                                        "Please enter either y  or n!");

                                    break;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a double!");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Unexpected Error: " + e);
                        }
                        // If the list is empty
                    } else if (list.size() == 0) {
                        System.out.println("The list is empty!");
                        // if a load already exists
                    } else if (list.getCursorData().getLoad() != null) {
                        System.out.println("There is already a load here!");
                    }

                    break;

                case "S":

                    // checks if the list is empty
                    if (list.size() != 0) {
                        try {
                            // gets the name
                            System.out.print("Enter product name: ");
                            String name = input.nextLine();

                            // checks if entered name is a valid name
                            // IllegalArgumentException is thrown here
                            ProductLoad.validName(name);

                            // use the .findProduct() method in TrainLinkedList 
                            // IllegalArgumentException is thrown here
                            list.findProduct(name);

                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Unexpected Error: "
                                                 + e.getMessage());
                        }

                        // if the list is empty tell the user
                    } else if (list.size() == 0) {
                        System.out.println("The list is empty!");
                    }

                    break;

                case "T":
                    // prints the train using the
                    // <code>TrainLinkedList.toString()</code> function
                    System.out.println(list.toString());

                    break;

                case "M":

                    // prints the manifest using the
                    // <code>TrainLinkedList.printManifest()</code> function
                    list.printManifest();

                    break;

                case "D":

                    try {
                        // uses the .removeDangerousCars() method in
                        // TrainLinkedList. IllegalStateException is thrown here
                        list.removeDangerousCars();

                        System.out.println(
                                    "Dangerous items successfully removed.");
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case "Q":

                    // set userQuit to true to stop the loop and therefore the
                    // program
                    userQuit = true;

                    break;

                // if the input doesn't match any of the cases, then it tells
                // the user that it's invalid
                default:

                    System.out.println(
                                "Your input didn't match any of the choices!");

                    break;
            }

        } while (!userQuit);

        input.close();
        System.out.println("\n\nProgram Terminated Successfully...");
    }
}