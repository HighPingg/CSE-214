package Homework_2;

import java.util.Scanner;

public class TrainManager {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

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

                    System.out.println("F");

                    break;

                case "B":

                    System.out.println("B");

                    break;

                case "I":

                    System.out.println("I");

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

                    System.out.println("T");

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