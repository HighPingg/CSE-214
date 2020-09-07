/**
 * @author Vincent Zheng
 * @solar_ID - 113469839
 * @email - vincent.zheng@stonybrook.edu
 * @assignment - 1
 * @course - CSE 214
 * @recitation - R02
 * @ta - William Simunek
 */

class Planner {

    private final static int MAX_COURSES = 5;

    // Keeps track total number of courses inside the array. Initialized as 0, but
    // changed inside the addCourse and removeCourse methods

    private Course[] courses;
    private int courseCount;

    // Initializes this Planner with an empty array of courses and totalCourses = 0

    public Planner() {
        courses = new Course[MAX_COURSES];
        courseCount = 0;
    }

    /**
     * Return the total number of Courses inside the course array
     * 
     * @return courseCount (the total number of Courses inside the array)
     */

    public int size() {
        return courseCount;
    }

    /**
     * Determines whether or not the courses array is fully populated or not
     * 
     * @return - true if the array is completely filled - false if not all spaces in
     *         the courses array is filled
     */

    public boolean isFull() {
        if (courseCount == MAX_COURSES) {
            return true;
        }

        return false;
    }

    /**
     * Adds a given newCourse to the end of the courses array
     * 
     * @param newCourse The course to be added into the back of the array
     * 
     * @throws FullPlannerException Thrown if the current size of the array +
     *                              newCourse would overflow out of array bounds
     */

    public void addCourse(Course newCourse) throws FullPlannerException {
        if (size() + 1 > MAX_COURSES) {
            throw new FullPlannerException("There isn't enough space in the Planner");
        }

        courses[courseCount] = newCourse;
        courseCount++;
    }

    /**
     * Adds a given newCourse to the given position of the array and pushes all
     * elements back 1 index. If the position > the elements in the array, then adds
     * to the back of the last element
     * 
     * @param newCourse The course to be added into the courses array
     * 
     * @param position  The position in which to add the Course into
     * 
     * @throws FullPlannerException     thrown if there isn't enough room in the
     *                                  array to fit the newCourse
     * 
     * @throws IllegalArgumentException thrown if position is out of bounds (not
     *                                  between 1 - MAX_COURSES)
     */

    public void addCourse(Course newCourse, int position) throws FullPlannerException, IllegalArgumentException {
        if (size() + 1 > MAX_COURSES) {
            throw new FullPlannerException("There isn't enough space in the Planner");
        }

        if (position < 1 || position > MAX_COURSES) {
            throw new IllegalArgumentException("The entered position is out-of-bounds");
        }

        // if the desired position is higher than the amount of courses, then add them
        // to courses[courseCount] using the addCourse(newCourse) method
        if (position > courseCount) {
            addCourse(newCourse);
        } else {
            // pushes all elements greater than position 1 backwards
            for (int i = courseCount; i >= position; i--) {
                courses[i] = courses[i - 1];
            }

            courses[position - 1] = newCourse;
            courseCount++;
        }
    }

    /**
     * Removes a Course at the given position
     * 
     * @param position the position to remove the course from
     * 
     * @throws IllegalArgumentException thrown if position is out of bounds (not
     *                                  between 1 - MAX_COURSES) or is empty/null
     */

    public void removeCourse(int position) throws IllegalArgumentException {
        if (position < 1 || position > MAX_COURSES) {
            throw new IllegalArgumentException("The entered position is out-of-bounds");
        }

        if (position > courseCount) {
            throw new IllegalArgumentException("There is nothing in this position!");
        }

        System.out.println(courses[position - 1].getDepartment() + " " + courses[position - 1].getCode() + "."
                + courses[position - 1].getSection() + " has been sucessfully removed from the planner.");

        // stop 1 before otherwise it will go out of bounds

        for (int i = position - 1; i < courseCount - 1; i++) {
            courses[i] = courses[i + 1];
        }

        // set last one to null
        courses[courseCount - 1] = null;
        courseCount--;
    }

    /**
     * Returns the Course inside a given position
     * 
     * @param position the position to get the course from
     * 
     * @return the Course at the given position
     * 
     * @throws IllegalArgumentException thrown if position is out of bounds (not
     *                                  between 1 - MAX_COURSES)
     */

    public Course getCourse(int position) throws IllegalArgumentException {
        if (position < 1 || position > MAX_COURSES) {
            throw new IllegalArgumentException("The entered position is out-of-bounds");
        }

        return courses[position - 1];
    }

    /**
     * Checks whether or not a given Course already exists inside the courses array
     * 
     * @param course the course to match with a course inside the courses array
     * 
     * @return - true if the course matches a course inside the array - false if
     *         there are not matches of course indide the array
     */

    public boolean exists(Course course) {
        for (int i = 0; i < courseCount; i++) {
            if (course.equals(courses[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Locates the first Course in courses that equals a given course
     * 
     * @param course The course that we look through courses to find a match for
     * 
     * @return -1 if there are no matches to the given course, the index of the
     *         first match in courses
     */

    public int locate(Course course) {
        for (int i = 0; i < courseCount; i++) {
            System.out.println(i + " " + course.equals(courses[i]));
            if (course.equals(courses[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns a clone of this PLanner class
     * 
     * @return a cloned copy of this Planner class
     */

    @Override
    public Object clone() {
        Planner clonePlanner = new Planner();

        for (int i = 0; i < courseCount; i++) {
            try {
                clonePlanner.addCourse((Course) courses[i].clone());
            } catch (Exception e) {
                System.out.println(e + ": " + e.getMessage());
            }
        }

        return clonePlanner;
    }

    /**
     * Determines whether or not a given object is the same as this Planner
     * 
     * @param obj the object that is being compared to this planner
     * 
     * @return - true if object is a Planner and contains the same courses and same
     *         size of courses - false if object isn't a Planner or if its courses
     *         aren't the same as this planner's courses, or if the object's size
     *         doesn't match this one's
     */

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Planner) {
            Planner planner = (Planner) obj;

            if (planner.size() == courseCount) {
                for (int i = 0; i < courseCount; i++) {
                    if (!planner.getCourse(i).equals(courses[i])) {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }

    /**
     * Prints all Courses that are within the specified department.
     * 
     * @param planner    the planner that needs to be filtered for a certain
     *                   department
     * 
     * @param department the department to look for inside planner
     * 
     * @throws IllegalArgumentException thrown when the given department is an
     *                                  invalid input
     */

    public static void filter(Planner planner, String department) throws IllegalArgumentException {
        if (!Course.validDepartment(department)) {
            throw new IllegalArgumentException("Invalid department");
        }

        // Iterates through all elements in array, if departments match, add it
        // to filteredPlanner, then use the .printAllCourses() method

        Planner filteredPlanner = new Planner();

        for (int i = 1; i <= planner.size(); i++) {
            System.out.println(i);
            if (planner.getCourse(i).getDepartment().equals(department)) {
                try {
                    filteredPlanner.addCourse(planner.getCourse(i));
                } catch (Exception e) {
                    System.out.println(e + ": " + e.getMessage());
                }
            }
        }

        filteredPlanner.printAllCourses();
    }

    /**
     * Print all elements in the courses array using .toString()
     */

    public void printAllCourses() {
        System.out.println(toString());
    }

    /**
     * Returns a String representing the courses array
     * 
     * @return a String with the elements of the courses array in order
     */

    @Override
    public String toString() {
        String top = String.format("%3s %-25s %-10s %4s %7s %s", "No.", "Course Name", "Department", "Code", "Section",
                "Instructor");
        String line = "-------------------------------------------------------------------------------";
        String coursesString = "";

        for (int i = 0; i < courseCount; i++) {
            Course course = courses[i];

            coursesString += String.format("%3d %-25s %-10s %4s %7d %s\n", i + 1, course.getName(),
                    course.getDepartment(), course.getCode(), course.getSection(), course.getInstructor());
        }

        return top + "\n" + line + "\n" + coursesString;
    }

}