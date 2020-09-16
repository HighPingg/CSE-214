package Homework_2;

public class TrainCar {

    // double holding the length of the TrainCar
    private double carLength;

    // double holding the weight of the TrainCar
    private double carWeight;

    // the ProductLoad of the TrainCar
    private ProductLoad load;

    /**
     * Create and initializes a new TrainCar using the given values and uses the
     * valid checkers to determine whether or not the given values are valid. The
     * load variable is also set to null and can be changed using the setLoad()
     * mutator.
     * 
     * @param carLength The value to set the length of this car to
     * 
     * @param carWeight The value to set the weight of this car to
     * 
     * @throws IllegalArgumentException if the given values fail the valid checkers
     */
    public TrainCar(double carLength, double carWeight) throws IllegalArgumentException {

        validWeight(carWeight);
        validLength(carLength);

        this.carLength = carLength;
        this.carWeight = carWeight;

        this.load = null;
    }

    /**
     * Returns the length of the TrainCar.
     *
     * @return Returns the length of the TrainCar.
     */
    public double getCarLength() {
        return carLength;
    }

    /**
     * Returns the load of the TrainCar.
     *
     * @return Returns the load of the TrainCar.
     */
    public ProductLoad getLoad() {
        return load;
    }

    /**
     * Returns the weight of the TrainCar.
     *
     * @return Returns the weight of the TrainCar.
     */
    public double getCarWeight() {
        return carWeight;
    }

    /**
     * Sets the load of this TrainCar object
     * 
     * @param load the value to set load to
     * 
     * @throws IllegalArgumentException if the given value fail the valid checker
     */
    public void setLoad(ProductLoad load) {
        validLoad(load);

        this.load = load;
    }

    /**
     * Checks whether or not this TrainCar has a load or not
     * 
     * @return true if load is null/empty false is load has an ProductLoad inside of
     *         it
     */
    public boolean isEmpty() {
        return load == null;
    }

    /**
     * Checks whether or not the given Object is a valid weight
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            weight
     * 
     * @return true if the Object passes all conditions and no errors are thrown
     * 
     * @throws IllegalArgumentException If Object is not an instance of double, or
     *                                  is negative
     */
    public static boolean validWeight(Object obj) throws IllegalArgumentException {
        if (obj instanceof Double) {
            double weight = (double) obj;

            if (weight < 0)
                throw new IllegalArgumentException("The given weight is negative");

            return true;
        }

        throw new IllegalArgumentException("The given weight not an instance of double");
    }

    /**
     * Checks whether or not the given Object is a valid length
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            length
     * 
     * @return true if the Object passes all conditions and no errors are thrown
     * 
     * @throws IllegalArgumentException If Object is not an instance of double, or
     *                                  is negative
     */
    public static boolean validLength(Object obj) throws IllegalArgumentException {
        if (obj instanceof Double) {
            double length = (double) obj;

            if (length < 0)
                throw new IllegalArgumentException("The given length is negative");

            return true;
        }

        throw new IllegalArgumentException("The given length not an instance of double");
    }

    /**
     * Checks whether or not the given Object is a valid ProductLoad
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            ProductLoad
     * 
     * @return true if the Object is an instance of ProductLoad
     * 
     * @throws IllegalArgumentException If Object is not an instance of isn't an
     *                                  instance of ProductLoad. There is no need to
     *                                  run futher checks because in order for a new
     *                                  ProductLoad to be instatiated, it must have
     *                                  already go e through the valid checkers
     */
    public static boolean validLoad(Object obj) throws IllegalArgumentException {
        if (obj instanceof ProductLoad) {
            return true;
        }

        throw new IllegalArgumentException("The given Object not an instance of ProductLoad");
    }
}
