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
    
    private final static int MAX_COURSES = 50;

    private Course [] courses = new Course [MAX_COURSES];


    public Planner() {

    }


    public int size() {
        return 0;
    }


    public void addCourse(Course newCourse) {

    }


    public void addCourse(Course newCourse, int position) {

    }


    public void removeCourse(int position) {
        
    }


    public Course getCourse (int position) {
        return new Course();
    }


    public boolean exists(Course course) {
        return true;
    }


    @Override
    public Object clone(){
        return new Object();
    }


    @Override
    public boolean equals(Object obj) {
        return true;
    }


    public void printAllCourses() {

    }

    public static void filter(Planner planner, String department) {

    }

}