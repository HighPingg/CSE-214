package Homework_2;

public class TrainCar {
    
    private double carLength;

    private double carWeight;

    private ProductLoad load;

    public TrainCar(double carLength, double carWeight, ProductLoad load) {
        
        this.carLength = carLength;
        this.carWeight = carWeight;
        this.load = load;
    }

    public double getCarLength() {
        return carLength;
    }

    public ProductLoad getLoad() {
        return load;
    }

    public double getCarWeight() {
        return carWeight;
    }

    public void setLoad(ProductLoad load) {
        this.load = load;
    }

    public boolean isEmpty() {
        return true;
    }
}
