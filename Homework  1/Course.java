/**
 * @author Vincent Zheng
 * @solar_ID - 113469839
 * @email - vincent.zheng@stonybrook.edu
 * @assignment - 1
 * @course - CSE 214
 * @recitation - R02
 * @ta - William Simunek
 */

class Course {

    private String name;
    private String department;
    private int code;
    private byte section;
    private String instructor;

    /**
     * Creates a new Course using the parameters and checks whether or not inputs
     * are valid using the static valid checkers
     * 
     * @param name       The String to set name to.
     * @param department The String to set department to.
     * @param code       The int to set code to.
     * @param section    The byte to set section to.
     * @param instructor The String to set instructor to.
     * 
     * @throws IllegalArgumentException Thrown if given parameters aren't valid
     *                                  inputs and fails the static check methods
     */

    public Course(String name, String department, int code, byte section, String instructor)
            throws IllegalArgumentException {

        if (!validName(name))
            throw new IllegalArgumentException("Input name invalid");

        if (!validDepartment(department))
            throw new IllegalArgumentException("Input department invalid");

        if (!validCode(code))
            throw new IllegalArgumentException("Input code invalid");

        if (!vaildSection(section))
            throw new IllegalArgumentException("Input section invalid");

        if (!validInstructor(instructor))
            throw new IllegalArgumentException("Input instructor invalid");

        this.name = name;
        this.department = department;
        this.code = code;
        this.section = section;
        this.instructor = instructor;
    }

    /**
     * Return the name of the course
     * 
     * @return this course's name as a String
     */

    public String getName() {
        return name;
    }

    /**
     * Return the department of the course
     * 
     * @return this course's department as a String
     */

    public String getDepartment() {
        return department;
    }

    /**
     * Return the code of the course
     * 
     * @return this course's code as an int
     */

    public int getCode() {
        return code;
    }

    /**
     * Return the section of the course
     * 
     * @return this course's section as a byte
     */

    public byte getSection() {
        return section;
    }

    /**
     * Return the instructor of the course
     * 
     * @return this course's instructor as a String
     */

    public String getInstructor() {
        return instructor;
    }

    /**
     * Sets the name of this Course object
     * 
     * @param name The String to set the name of this object to
     * 
     * @throws IllegalArgumentException Thrown if the provided name is empty and
     *                                  doesn't contain only letters and spaces
     */

    public void setName(String name) throws IllegalArgumentException {
        if (validName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name input");
        }
    }

    /**
     * Sets the department of this Course object
     * 
     * @param department The String to set the name of this object to
     * 
     * @throws IllegalArgumentException Thrown if the provided department is empty
     *                                  and doesn't contain only letters and spaces
     */

    public void setDepartment(String department) throws IllegalArgumentException {
        if (validDepartment(department)) {
            this.department = department;
        } else {
            throw new IllegalArgumentException("Input department invaild");
        }
    }

    /**
     * Sets the code of this Course object
     * 
     * @param code The int to set the code of this object to
     * 
     * @throws IllegalArgumentException Thrown if the provided code is negative
     */

    public void setCode(int code) throws IllegalArgumentException {
        if (validDepartment(code)) {
            this.code = code;
        } else {
            throw new IllegalArgumentException("Input code invaild");
        }
    }

    /**
     * Sets the section of this Course object
     * 
     * @param section The byte to set the section of this object to
     * 
     * @throws IllegalArgumentException Thrown if the provided section is negative
     */

    public void setSection(byte section) throws IllegalArgumentException {
        if (validDepartment(section)) {
            this.section = section;
        } else {
            throw new IllegalArgumentException("Input section invaild");
        }
    }

    /**
     * Sets the instructor of this Course object
     * 
     * @param name The String to set the instructor of this object to
     * 
     * @throws IllegalArgumentException Thrown if the provided instructor is empty
     *                                  and doesn't contain only letters and spaces
     */

    public void setInstructor(String instructor) throws IllegalArgumentException {
        if (validDepartment(instructor)) {
            this.instructor = instructor;
        } else {
            throw new IllegalArgumentException("Input instructor invaild");
        }
    }

    /**
     * Returns a new clone of this Object
     * 
     * @return a new clone of this object using the current values of name,
     *         department, code, section, and instructor
     */

    @Override
    public Object clone() {
        return new Course(name, department, code, section, instructor);
    }

    /**
     * Checks whether or not a given Object equals this Object
     * 
     * @param obj the Object to be checked whether or not it is the same as this
     *            Object
     * 
     * @return - true if given Object is a Course and contains the same name,
     *         department, code, section, and instructor as this Object - false if
     *         given Object is not an instance of Course or doesn't contain the same
     *         name, department, code, section, and instructor as this Object
     */

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            Course course = (Course) obj;

            return course.name.equals(name) && course.department.equals(department) && course.code == code
                    && course.section == section && course.instructor.equals(instructor);
        }

        return false;
    }

    /**
     * Checks whether or not the given Object is a valid name
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            name
     * 
     * @return - true if Object is a String, only contains letters and spaces, and
     *         isn't empty - false if Object isn't a String, contains characters
     *         other than letters and spaces, or is empty
     */

    public static boolean validName(Object obj) {
        if (obj instanceof String) {
            String name = (String) obj;

            return name.matches("[a-zA-z ]*") && name.length() != 0;
        }

        return false;
    }

    /**
     * Checks whether or not the given Object is a valid department
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            department
     * 
     * @return - true if Object is a String, only contains capital letters, and
     *         isn't empty - false if Object isn't a String, contains characters
     *         other than capital letters, or is empty
     */

    public static boolean validDepartment(Object obj) throws IllegalArgumentException {
        if (obj instanceof String) {
            String department = (String) obj;

            return department.matches("[A-Z]*") && department.length() != 0;
        }

        return false;
    }

    /**
     * Checks whether or not the given Object is a valid code
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            code
     * 
     * @return - true if Object is an Integer and is not negative - false if object
     *         isn't an Integer or is negative
     */

    public static boolean validCode(Object obj) {
        if (obj instanceof Integer) {
            int code = (int) obj;

            return code >= 0;
        }

        return false;
    }

    /**
     * Checks whether or not the given Object is a valid section
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            section
     * 
     * @return - true if Object is a Byte and is not negative - false if object
     *         isn't a Byte or is negative
     */

    public static boolean vaildSection(Object obj) {
        if (obj instanceof Byte) {
            byte section = (byte) obj;

            return section >= 0;
        }

        return false;
    }

    /**
     * Checks whether or not the given Object is a valid instructor
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            instructor
     * 
     * @return - true if Object is a String, only contains letters and spaces, and
     *         isn't empty - false if Object isn't a String, contains characters
     *         other than letters and spaces, or is empty
     */

    public static boolean validInstructor(Object obj) {
        if (obj instanceof String) {
            String instructor = (String) obj;

            return instructor.matches("[a-zA-z ]*") && instructor.length() != 0;
        }

        return false;
    }

}