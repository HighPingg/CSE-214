package Homework_2;

public class TrainCarNode {

    // TrainCarNode holding the previous link to this Node
    private TrainCarNode prev;

    // TrainCarNode holding the next link to this Node
    private TrainCarNode next;

    // TrainCar holding the load and data of this Node
    private TrainCar car;

    /**
     * Default constructor initializes this TrainCarNode and sets everything to null
     */
    public TrainCarNode() {
        this.prev = null;
        this.next = null;
        this.car = null;
    }

    /**
     * Overloader constructor initializes this TrainCarNode with prev and next to
     * null, but with a TrainCar object. There is no need to valid check car because
     * in order for a new TrainCar to be initialized, it has to already have gone
     * through a valid checker.
     * 
     * @param car the TrainCar to set this car to
     */
    public TrainCarNode(TrainCar car) {
        this.prev = null;
        this.next = null;
        this.car = car;
    }

    /**
     * Returns the previous TrainCarNode
     * 
     * @return returns the previous TrainCarNode
     */
    public TrainCarNode getPrev() {
        return prev;
    }

    /**
     * Returns the next TrainCarNode
     * 
     * @return returns the next TrainCarNode
     */
    public TrainCarNode getNext() {
        return next;
    }

    /**
     * Returns this Node's TrainCar information
     * 
     * @return returns the TrainCar assigned to this node
     */
    public TrainCar getCar() {
        return car;
    }

    /**
     * Sets the previous node to the given TrainCarNode. Don't need to check if
     * <code>prev</code> is valid because in order for a new TrainCarNode to be
     * initialized, the member variables must be valid
     * 
     * @param prev the TrainCarNode to set this <code>prev<code> to
     */
    public void setPrev(TrainCarNode prev) {
        this.prev = prev;
    }

    /**
     * Sets the next node to the given TrainCarNode. Don't need to check if
     * <code>next</code> is valid because in order for a new TrainCarNode to be
     * initialized, the member variables must be valid
     * 
     * @param next the TrainCarNode to set this <code>next</code> to
     */
    public void setNext(TrainCarNode next) {
        this.next = next;
    }

    /**
     * Sets the car to the given <code>TrainCar</code>. Don't need to check if
     * <code>car</code> is valid because in order for a new TrainCar to be
     * initialized, the member variables must be checked
     * 
     * @param car the TrainCar to set this <code>car<code> to
     */
    public void setCar(TrainCar car) {
        this.car = car;
    }

    /**
     * Returns a String form of this TrainCarNode with the car length, car weight,
     * load name, load weight, load value, and whether or not the load is dangerous
     * 
     * @return a formatted String representation of this TrainCarNode
     */
    @Override
    public String toString() {

        String loadNameString;
        String loadWeightString;
        String loadValueString;
        String isDangerousString;

        // if there is no load
        if (car.getLoad() == null) {

            loadNameString = "EMPTY";
            loadWeightString = "0.0";
            loadValueString = "0.00";
            isDangerousString = "NO";

            // else if there is a load
        } else {

            loadNameString = car.getLoad().getName();
            loadWeightString = String.valueOf(car.getLoad().getWeight());
            loadValueString = String.valueOf(car.getLoad().getValue());

            // turns the boolean isDangerous to a String. YES if true, No if false
            if (car.getLoad().isDangerous()) {
                isDangerousString = "YES";
            } else {
                isDangerousString = "NO";
            }
        }

        return String.format("%10s    %10s  | %25s    %10s    %10s   %s", car.getCarLength(), car.getCarWeight(),
                loadNameString, loadWeightString, loadValueString, isDangerousString);
    }
}