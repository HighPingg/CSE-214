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

    /**
     * Creates a new Course depending on user inputs and checks the validity of
     * these inputs. If they aren't valid, then thorws an error.
     * 
     * @param input input stream to take user inputs from
     * 
     * @return a new Course with the valid user defined variables
     * 
     * preCondition: input is a valid input stream
     */

    public static Course userInputCourse(Scanner input)
        throws IllegalArgumentException {

        // gets the name of the new course and checks if entered name is valid

        String name;
        System.out.print("Enter course name: ");
        name = input.nextLine();

        if (!Course.validName(name)) {
            throw new IllegalArgumentException("This name isn't valid!");
        }

        // gets the department of the new course and checks if entered
        // department is valid

        String department;
        System.out.print("Enter department: ");
        department = input.nextLine();

        if (!Course.validDepartment(department)) {
            throw new IllegalArgumentException("This department isn't valid!");
        }

        // gets the code of the new course and checks if entered code is valid

        int code;
        System.out.print("Enter course code: ");
        String codeIn = input.nextLine();

        try {
            code = Integer.parseInt(codeIn);

            if (!Course.validCode(code)) {
                throw new IllegalArgumentException("This code isn't valid!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("This code isn't valid!");
        }

        // gets the section of the new course and checks if entered section is
        // valid

        byte section;

        System.out.print("Enter course section: ");
        String sectionIn = input.nextLine();

        try {
            section = (byte) Integer.parseInt(sectionIn);

            if (!Course.vaildSection(section)) {
                throw new IllegalArgumentException("This section isn't valid!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("This section isn't valid!");
        }

        // gets the instructor of the new course and checks if entered
        // instructor is valid

        String instructor = "";

        System.out.print("Enter instructor name: ");
        instructor = input.nextLine();

        if (!Course.validInstructor(instructor)) {
            throw new IllegalArgumentException("This instructor isn't valid!");
        }

        // return new course object using the given valid user inputs

        return new Course(name, department, code, section, instructor);
    }

    public static void main(String[] args) throws IllegalArgumentException {

        Scanner input = new Scanner(System.in);

        // Planner and beckup objects. Backup initialized as empty initially.
        Planner planner = new Planner(), backup = new Planner();

        // exit condition (Turns true if user enters "Q" into the menu)
        boolean userQuit = false;

        do {
            // Array of valid inputs to be compared with user input to find a
            // match
            String[] validInputs = { "A", "G", "R", "P", "F", "L", "S", "B",
                                    "PB", "RB", "Q" };

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

            // Flag variable: If user input is determined to be valid, this will
            // turn to true.
            boolean isValidInput = false;

            do {
                System.out.print("\n\nEnter Your Selection: ");
                choice = input.nextLine().toUpperCase();

                // comparing the user input with the elements of the accepted
                // answers array until a match is found
                for (int i = 0; i < validInputs.length && !isValidInput; i++) {
                    if (choice.equals(validInputs[i])) {
                        isValidInput = true;
                    }
                }

                // If the user input is invalid, then it repromts the user
                if (!isValidInput) {
                    System.out.println("The selection you have made is invalid."
                            + " Please enter something else...");
                }

            } while (!isValidInput);

            // switch case to do the option that user selected

            switch (choice) {
                case "A":

                    // check if the planner is full using the .isFull() method.
                    // If isFull() == true, then tells user planner is full and
                    // go back to menu

                    if (!planner.isFull()) {

                        // get newCourse using the user input function
                        Course newCourse;

                        try {
                            newCourse = userInputCourse(input);

                            // gets the position

                            int position;
                            System.out.print("Enter position: ");
                            String positionIn = input.nextLine();

                            // Coverts the input to an Integer if possible
                            // NumberFormatException is thrown here if user
                            // doesn't enter an integer
                            position = Integer.parseInt(positionIn);

                            // IllegalArgumentException is thrown here if
                            // position is out of bounds
                            planner.addCourse(newCourse, position);

                            // set isValidPosition to true to stop the loop
                            System.out.printf("%s %d.%02d has been sucessfully"
                                            + " added to the planner.",
                                    newCourse.getDepartment(),
                                    newCourse.getCode(),
                                    newCourse.getSection());

                        } catch (NumberFormatException e) {
                            System.out.println("You didn't enter a number!");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Unexpected Error: " + e);
                        }

                    } else {
                        System.out.println("The planner is full!");
                    }

                    break;

                case "G":

                    // check if the planner is empty using the size() method. If
                    // size() == 0, then tells user planner is empty and go back
                    // to menu

                    if (planner.size() != 0) {

                        // gets the position

                        String positionString;
                        System.out.print("Enter a position: ");
                        positionString = input.nextLine();

                        try {
                            // Coverts the input to an Integer if possible
                            // NumberFormatException is thrown here if user 
                            // doesn't enter an integer
                            int position = Integer.parseInt(positionString);

                            // IllegalArgumentException is thrown here if 
                            // position is out of bounds
                            Course course = planner.getCourse(position);

                            // Printing the course by creating a new Planner 
                            // object and adding the course to it and usingg the
                            // printAllCourses method. If course is null, then 
                            // there is no object in the given position.

                            if (course == null) {
                                System.out.println("This position is empty");
                            } else {

                                // Planner heading

                                System.out.printf("%3s %-25s %-10s %4s %7s %s\n"
                                        , "No.", "Course Name", "Department",
                                        "Code", "Section", "Instructor");

                                System.out.println(
                                        "--------------------------------------"
                                        + "------------------------------------"
                                        + "-----");

                                // Printing the course

                        System.out.printf("%3d %-25s %-10s %4d      %02d %s\n",
                                    position, course.getName(),
                                        course.getDepartment(), course.getCode()
                                        , course.getSection(),
                                        course.getInstructor());
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a number.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Unexpected Error: " + e);
                        }
                    } else {
                        System.out.println("Planner is empty!");
                    }

                    break;

                case "R":

                    // check if the planner is empty using the size() method. If
                    // size() == 0, then there is noting to remove and tells 
                    // user planner is empty and go back to menu

                    if (planner.size() != 0) {

                        // gets the position

                        String removePositionString = "";
                        System.out.print("Enter a position: ");
                        removePositionString = input.nextLine();

                        try {
                            // Coverts the input to an Integer if possible
                            // NumberFormatException is thrown here if user
                            // doesn't enter an integer
                            int position = 
                                        Integer.parseInt(removePositionString);

                            // IllegalArgumentException is thrown here if 
                            // position is out of bounds
                            planner.removeCourse(position);

                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a number.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Unexpected Error: " + e);
                        }

                    } else {
                        System.out.println("Planner is empty!");
                    }

                    break;

                case "P":

                    System.out.println("\nPlanner:");

                    // just used the printAllCourses() method defined in the
                    // Planner class
                    planner.printAllCourses();

                    break;

                case "F":

                    // Gets the department of the new course and reprompt until
                    // entered department is valid. This is checked using the
                    // validDepartment() method in the Course class.

                    String department = "";

                    System.out.print("Enter department: ");
                    department = input.nextLine();

                    if (!Course.validDepartment(department)) {
                        System.out.println("This department isn't valid!");
                    } else {

                        // After user input is verified to be valid, then use
                        // the filter() method in the Planner class

                        Planner.filter(planner, department);
                    }

                    break;

                case "L":

                    // get newCourse using the user input function

                    Course course;

                    try {
                        course = userInputCourse(input);

                        // uses the locate() method inthe Planner class using
                        // the given course

                        int location = planner.locate(course);

                        // locate returns -1 if the course isn't found and the
                        // index of course if the course is found

                        if (location == -1) {
                            System.out.printf(
                                "%s %d.%02d was not found in the planner",
                                    course.getDepartment(),
                                    course.getCode(), course.getSection());
                        } else {
                            System.out.printf(
                            "%s %d.%02d was found in the planner at position %d"
                                    , course.getDepartment(),
                                    course.getCode(), course.getSection(),
                                    (location + 1));
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case "S":

                    // just uses the size() method in the Planner method

                    System.out.println("\nThere Are " + planner.size() + 
                                            " Courses In The Planner.\n");

                    break;

                case "B":

                    // Sets the backup variable to a clone of planner. Have to
                    // caste because clone() returns an Object.

                    backup = (Planner) planner.clone();

                    System.out.println("\nPlanner Successfully Backed Up...\n");

                    break;

                case "PB":

                    System.out.println("\nPlanner (Backup):");

                    // just uses the printAllCourses() method in the Planner
                    // method

                    backup.printAllCourses();

                    break;

                case "RB":

                    // Sets the planner to a clone of backup. Have to caste 
                    // because clone() returns an Object.

                    planner = (Planner) backup.clone();

                    System.out.println("\nBackup Successfully Reverted...\n");

                    break;

                case "Q":

                    // sets userQuit to true to quit the program

                    userQuit = true;

                    break;
            }

        } while (!userQuit);

        System.out.println("\n\nProgram terminating sucessfully...\n");
    }
}