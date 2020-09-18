package Homework_2;

public class TrainLinkedList {

    // The first node inside the list
    private TrainCarNode head;

    // The last node inside the list
    private TrainCarNode tail;

    // The current selected node inside the list
    private TrainCarNode cursor;

    // Variables holding the total, length, weight, value, and wheter or not the
    // Train has dangerous goods. This is changed every time a car is added and
    // removed.
    private double totalLength;
    private double totalWeight;
    private double totalValue;
    private int totalCars;
    private boolean hasDanger;

    /**
     * Default constructor initializes a new TrainLinkedList with head, tail and
     * cursor variables set to null and length, weight, and value set to 0.
     * hasDanger is set to false because there are no TrainCars.
     */
    public TrainLinkedList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;

        this.totalLength = 0;
        this.totalWeight = 0;
        this.totalValue = 0;
        this.totalCars = 0;
        this.hasDanger = false;
    }

    /**
     * Returns the total length of the train in meters.
     * 
     * @return The sum of the lengths of each TrainCar in the train.
     */
    public double getLength() {
        return totalLength;
    }

    /**
     * Returns the total weight in tons of the train. Note that the weight of the
     * train is the sum of the weights of each empty TrainCar, plus the weight of
     * the ProductLoad carried by that car.
     * 
     * @return The sum of the weight of each TrainCar plus the sum of the
     *         ProductLoad carried by that car.
     */
    public double getWeight() {
        return totalWeight;
    }

    /**
     * Returns the total value of product carried by the train.
     * 
     * @return The sum of the values of each TrainCar in the train.
     */
    public double getValue() {
        return totalValue;
    }

    /**
     * Determines the number of TrainCar objects currently on the train.
     * 
     * @return The number of TrainCar objects on this train.
     */
    public int size() {
        return totalCars;
    }

    /**
     * Whether or not there is a dangerous product on one of the TrainCar objects on
     * the train.
     * 
     * @return Returns true if the train contains at least one TrainCar carrying a
     *         dangerous ProductLoad, false otherwise.
     */
    public boolean isDangerous() {
        return hasDanger;
    }

    /**
     * Returns a reference to the <code>TrainCar</code> at the node currently
     * referenced by the cursor.
     * 
     * @return The reference to the <code>TrainCar</code> at the node currently
     *         referenced by the cursor.
     * 
     * @throws IllegalStateException if cursor and therefore the list is null
     */
    public TrainCar getCursorData() throws IllegalStateException {
        if (cursor == null)
            throw new IllegalStateException("The cursor is empty!");

        return cursor.getCar();
    }

    /**
     * Places car in the node currently referenced by the cursor.
     * 
     * @param car the TrainCar object that is to be set as the car reference in the
     *            node referenced by cursor
     * 
     * @throws IllegalStateException    if the <code>cursor</code> is empty
     * 
     * @throws IllegalArgumentException if the entered car is null
     */
    public void setCursorData(TrainCar car) throws IllegalStateException, IllegalArgumentException {
        if (cursor == null) {
            throw new IllegalStateException("The cursor is empty!");
        } else if (car == null) {
            throw new IllegalArgumentException("The entered car is empty!");
        }

        this.cursor.setCar(car);
    }

    /**
     * Moves the cursor to the next TrainCarNode
     * 
     * @throws IllegalStateException if the cursor is equal to <code>tail</code>, or
     *                               if the list is empty
     *                               (<code>cursor == null</code>)
     */
    public void cursorForward() throws IllegalStateException {
        if (cursor == tail) {
            throw new IllegalStateException("The cursor is at the tail of the list.");
        } else if (cursor == null) {
            throw new IllegalStateException("The list is empty");
        }

        // advance cursor
        cursor = cursor.getNext();
    }

    /**
     * Moves the cursor to the previous TrainCarNode
     * 
     * @throws IllegalStateException if the cursor is equal to <code>head</code> or
     *                               if the list is empty
     *                               (<code>cursor == null</code>)
     */
    public void cursorBackward() throws IllegalStateException {
        if (cursor == head) {
            throw new IllegalStateException("The cursor is at the head of the list.");
        } else if (cursor == null) {
            throw new IllegalStateException("The list is empty");
        }

        // advance cursor
        cursor = cursor.getPrev();
    }

    /**
     * Inserts a car into the train after the cursor position.
     * 
     * @param car the new <code>TrainCar</code> to be inserted into the train.
     * 
     * @throws IllegalArgumentException Indicates that <code>car</code> is
     *                                  <code>null</code>.
     */
    public void insertAfterCursor(TrainCar car) throws IllegalArgumentException {
        if (car == null) {
            throw new IllegalArgumentException("The car you entered is null!");
        }

        // creates a newNode
        TrainCarNode newNode = new TrainCarNode(car);

        // if list is empty
        if (cursor == null) {

            // set all cursor, head, and tail to newNode
            cursor = newNode;
            head = newNode;
            tail = newNode;

            // increment the counters
            totalWeight += car.getCarWeight();
            totalLength += car.getCarLength();
            totalCars++;

            // if cursor is the tail
        } else if (cursor.getNext() == null) {

            // appends the newNode
            cursor.setNext(newNode);
            newNode.setPrev(cursor);

            // advance the cursor and moves tail backwards
            tail = newNode;
            cursor = newNode;

            // increment the counters
            totalWeight += car.getCarWeight();
            totalLength += car.getCarLength();
            totalCars++;

            // if cursor is at head or middle of list
        } else {

            // insert the newNode into the list
            newNode.setNext(cursor.getNext());
            newNode.setPrev(cursor);
            cursor.getNext().setPrev(newNode);
            cursor.setNext(newNode);

            // increment the counters
            totalWeight += car.getCarWeight();
            totalLength += car.getCarLength();
            totalCars++;
        }
    }

    /**
     * 
     * 
     * @param load
     * 
     * @throws IllegalArgumentException
     * 
     * @throws IllegalStateException
     */
    public void addLoadAtCursor(ProductLoad load) throws IllegalArgumentException, IllegalStateException {
        if (load == null) {
            throw new IllegalArgumentException("The load you entered is null!");
        } else if (cursor == null) {
            throw new IllegalStateException("There list is empty!");
        }

        // sets the load
        cursor.getCar().setLoad(load);

        // increments counters
        totalWeight += load.getWeight();
        totalValue += load.getValue();

        // sets hasDanger to true if it already has dangerous items or if load contains
        // dangerous items
        hasDanger = (hasDanger || load.isDangerous());
    }

    public TrainCar removeCursor() {
        return new TrainCar(1, 1, new ProductLoad("name", 1, 1, true));
    }

    public void findProduct(String name) {

    }

    public void removeDangerousCars() {

    }

    public void printManifest() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String headerString = ("\n    CAR:                               LOAD:\n");

        String tableHeaderString = String.format("\n      %s   %s    %s  |    %s      %s     %s   %s\n", "Num",
                "Length (m)", "Weight (t)", "Name", "Weight (t)", "Value ($)", "Dangerous");

        String bigLine = "\n==================================+===================================================\n";

        String tableInsides = "";

        // temporatry pointer to go through the whole list
        TrainCarNode tempPointer = head;

        for (int i = 1; tempPointer != null; i++) {

            // prints arrow if this is the node cursor is pointing to
            if (tempPointer == cursor) {
                tableInsides += " -> ";
            }

            tableInsides += String.format("%4d   %s\n    ", i, tempPointer.toString());

            System.out.println("hello" + i);
            // advance tempPointer
            tempPointer = tempPointer.getNext();
            System.out.println(tempPointer == cursor);
        }

        return headerString + tableHeaderString + bigLine + tableInsides;
    }
}