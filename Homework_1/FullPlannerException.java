package Homework_1;

/**
 * FullPlannerException is a custom Exception used in the Planner class mainly
 * for when the courses array is full. Takes in a string as a message to be
 * used by using the .getMessage() method in the Exception class.
 * 
 * @author Vincent Zheng
 * @solar_ID - 113469839
 * @email - vincent.zheng@stonybrook.edu
 * @assignment - 1
 * @course - CSE 214
 * @recitation - R02
 * @ta - William Simunek
 */

public class FullPlannerException extends Exception  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that creates a FullPlannerException using s as the message
     * 
     * @param s the message to add to the Exception
     * 
     *          postCondition: This FullPlannerException is instatiated with a valid
     *          message.
     */
    
    public FullPlannerException(String s) {
        super(s);
    }

}