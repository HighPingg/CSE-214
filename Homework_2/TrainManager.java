package Homework_2;

import java.util.Scanner;

public class TrainManager {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        TrainLinkedList list = new TrainLinkedList();

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

            System.out.println("\nEnter a Selection: ");

            switch (input.nextLine().toUpperCase()) {
                case "F":

                    // uses the cursor forward method in TrainLinkedList
                    try {

                        // IllegalStateException thrown here if cursor is at tail or list is empty
                        list.cursorForward();

                        System.out.println("\nCursor Successfully Moved Forwards");

                    } catch (IllegalStateException e) {

                        System.out.println(e.getMessage());

                    } catch (Exception e) {

                        System.out.println("Unexpected Error: " + e);

                    }

                    break;

                case "B":

                    // uses the cursor backwards method in TrainLinkedList
                    try {
                        // IllegalStateException thrown here if cursor is at head or list is empty
                        list.cursorBackward();

                        System.out.println("\nCursor Successfully Moved Forwards");

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

                        // NumberFormatException is thrown here if lengthString can not be turned into a
                        // double
                        double length = Double.parseDouble(lengthString);

                        // checks if input is valid
                        TrainCar.validLength(length);

                        System.out.print("Enter car weight in tons: ");
                        String weightString = input.nextLine();

                        // NumberFormatException is thrown here if weightString can not be turned into a
                        // double
                        double weight = Double.parseDouble(weightString);

                        // checks if input is valid
                        TrainCar.validWeight(weight);

                        // insert into list throws IllegalArgumentException here if car is null
                        list.insertAfterCursor(new TrainCar(length, weight));

                        System.out.printf("\nNew train car %f meters %f tons inserted into train.", length, weight);

                    } catch (NumberFormatException e) {

                        System.out.println("\nYour input isn't a double value");

                    } catch (IllegalArgumentException e) {

                        System.out.println("\n" + e.getMessage());

                    } catch (Exception e) {

                        System.out.println("Unexpected Error: " + e);

                    }

                    break;

                case "R":

                    System.out.println("R");

                    break;

                case "L":

                    System.out.println("L");

                    break;

                case "S":

                    System.out.println("S");

                    break;

                case "T":

                    list.printManifest();

                    break;

                case "M":

                    System.out.println("M");

                    break;

                case "D":

                    System.out.println("D");

                    break;

                case "Q":

                    // set userQuit to true to stop the loop and therefore the program
                    userQuit = true;

                    break;

                default:

                    System.out.println("Your input didn't match any of the choices!");

                    break;
            }

        } while (!userQuit);

        input.close();
        System.out.println("\n\nProgram Terminated Successfully...");
    }
}