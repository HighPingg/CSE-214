package Homework_2;

public class TrainLinkedList {

    private TrainCarNode head;

    private TrainCarNode tail;

    private TrainCarNode cursor;

    public TrainLinkedList() {

    }

    public TrainCar getCursorData() {
        return cursor.getCar();
    }

    public void setCursorData(TrainCar car) {
        this.cursor.setCar(car);
    }

    public void cursorForward() {

    }

    public void cursorBackward() {

    }

    public void insertAfterCursor(TrainCar car) {

    }

    public TrainCar removeCursor() {
        return new TrainCar(1, 1, new ProductLoad("name", 1, 1, true));
    }

    public int size() {
        return 1;
    }

    public double getLength() {
        return 1;
    }
    public double getWeight() {
        return 1;
    }

    public double getValue() {
        return 1;
    }

    public boolean isDangerous() {
        return true;
    }

    public void findProduct(String name) {

    }

    public void printManifest() {

    }

    public void removeDangerousCars() {

    }

    @Override
    public String toString() {
        
        return super.toString();
    }
}