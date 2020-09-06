import java.util.Scanner;

/**
 * @author Vincent Zheng
 * @solar_ID - 113469839
 * @email - vincent.zheng@stonybrook.edu
 * @assignment - 1
 * @course - CSE 214
 * @recitation - R02
 * @ta - William Simunek
 */

class PlannerManager {

    public static Course userInputCourse(Scanner input) {

        String name = "";
        do {
            System.out.print("Enter course name: ");
            name = input.nextLine();

            if (!Course.validName(name)) {
                System.out.println("This name isn't valid!");
            }

        } while (!Course.validName(name));

        String department = "";
        do {
            System.out.print("Enter department: ");
            department = input.nextLine();

            if (!Course.validDepartment(department)) {
                System.out.println("This department isn't valid!");
            }

        } while (!Course.validDepartment(department));

        int code = -1;
        do {
            System.out.print("Enter course code: ");
            String codeIn = input.nextLine();

            try {
                code = Integer.parseInt(codeIn);

                if (!Course.validCode(code)) {
                    System.out.println("This code isn't valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("This code isn't valid!");
            }
        } while (!Course.validCode(code));

        byte section = -1;
        do {
            System.out.print("Enter course section: ");
            String sectionIn = input.nextLine();

            try {
                section = (byte) Integer.parseInt(sectionIn);

                if (!Course.vaildSection(section)) {
                    System.out.println("This section isn't valid!");
                }
            } catch (Exception e) {
                System.out.println("This section isn't valid!");
            }
        } while (!Course.vaildSection(section));

        String instructor = "";
        do {
            System.out.print("Enter instructor name: ");
            instructor = input.nextLine();

            if (!Course.validInstructor(instructor)) {
                System.out.println("This name isn't valid!");
            }

        } while (!Course.validInstructor(instructor));

        return new Course(name, department, code, section, instructor);
    }

    public static void main(String[] args) throws IllegalArgumentException {

        Scanner input = new Scanner(System.in);

        Planner planner = new Planner(), backup = new Planner();

        // exit condition (Turns true if user enters "Q" into the menu)
        boolean userQuit = false;

        do {
            // Array of valid inputs to be compared with user input to find a match
            String[] validInputs = { "A", "G", "R", "P", "F", "L", "S", "B", "PB", "RB", "Q" };

            System.out.println("\n\n(A) Add Course");
            System.out.println("(G) Get Course");
            System.out.println("(R) Remove Course");
            System.out.println("(P) Print Courses in Planner");
            System.out.println("(F) Filter by Department Code");
            System.out.println("(L) Look For Course");
            System.out.println("(S) Size");
            System.out.println("(B) Backup");
            System.out.println("(PB) Print Courses in Backup");
            System.out.println("(RB) Revert to Backup");
            System.out.println("(Q) Quit");

            String choice = "";

            // Flag variable: If user input is determined to be valid, this will turn to
            // true.
            boolean isValidInput = false;

            do {
                System.out.print("\n\nEnter Your Selection: ");
                choice = input.nextLine().toUpperCase();

                // comparing the user input with the elements of the accepted answers array
                // until a match is found
                for (int i = 0; i < validInputs.length && !isValidInput; i++) {
                    if (choice.equals(validInputs[i])) {
                        isValidInput = true;
                    }
                }

                // If the user input is invalid, then it repromts the user
                if (!isValidInput) {
                    System.out.println("The selection you have made is invalid. Please enter something else...");
                }

            } while (!isValidInput);

            switch (choice) {
                case "A":

                    if (!planner.isFull()) {
                        Course newCourse = userInputCourse(input);

                        int position = 0;
                        boolean isValidPosition = false;

                        do {
                            System.out.print("Enter position: ");

                            String positionIn = input.nextLine();

                            try {
                                position = Integer.parseInt(positionIn);

                                planner.addCourse(newCourse, position);

                                System.out.println("Course added succesfully...");
                                isValidPosition = true;
                            } catch (NumberFormatException e) {
                                System.out.println("You didn't enter a number!");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Unexpected Error: " + e);
                            }

                        } while (!isValidPosition);
                    } else {
                        System.out.println("The planner is full!");
                    }

                    break;

                case "G":

                    if (planner.size() != 0) {
                        String positionString = "";
                        boolean isValidPosition = false;

                        do {
                            System.out.print("Enter a position: ");
                            positionString = input.nextLine();

                            try {
                                int position = Integer.parseInt(positionString);

                                Course course = planner.getCourse(position);

                                if (course == null) {
                                    System.out.println("This position is empty");
                                } else {
                                    Planner oneCourse = new Planner();
                                    oneCourse.addCourse(course);
                                    oneCourse.printAllCourses();
                                }

                                isValidPosition = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a number.");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Unexpected Error: " + e);
                            }

                        } while (!isValidPosition);
                    } else {
                        System.out.println("Planner is empty!");
                    }

                    break;

                case "R":
                    if (planner.size() != 0) {
                        String removePositionString = "";
                        boolean isValidRemovePosition = false;

                        do {
                            System.out.print("Enter a position: ");
                            removePositionString = input.nextLine();

                            try {
                                int position = Integer.parseInt(removePositionString);

                                planner.removeCourse(position);

                                System.out.println(" has been sucessfully removed from the planner.");
                                isValidRemovePosition = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a number.");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Unexpected Error: " + e);
                            }

                        } while (!isValidRemovePosition);
                    } else {
                        System.out.println("Planner is empty!");
                    }

                    break;

                case "P":

                    planner.printAllCourses();

                    break;

                case "F":

                    String department = "";
                    do {
                        System.out.print("Enter department: ");
                        department = input.nextLine();

                        if (!Course.validDepartment(department)) {
                            System.out.println("This department isn't valid!");
                        }

                    } while (!Course.validDepartment(department));

                    Planner.filter(planner, department);

                    break;

                case "L":
                    Course course = userInputCourse(input);

                    int location = planner.locate(course);

                    System.out.println(" was found in planner at position " + (location + 1));

                    break;

                case "S":
                    System.out.println("\nThere Are " + planner.size() + " Courses In The Planner.\n");
                    break;

                case "B":
                    backup = (Planner) planner.clone();

                    System.out.println("\nPlanner Successfully Backed Up...\n");
                    break;

                case "PB":
                    backup.printAllCourses();
                    break;

                case "RB":
                    planner = (Planner) backup.clone();

                    System.out.println("\nBackup Successfully Reverted...\n");
                    break;

                case "Q":
                    userQuit = true;
                    break;
            }

        } while (!userQuit);

        System.out.println("\n\nProgram terminating sucessfully...\n");
    }
}