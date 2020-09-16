package Homework_2;

public class ProductLoad {

    // String holding the name of the load
    private String name;

    // double value holding the weight of the load
    private double weight;

    // double value holding the value of the load
    private double value;

    // boolean holding whether or not the load is dangerous
    private boolean isDangerous;

    /**
     * Create and initializes a new ProductLoad using the given values and uses the
     * valid checkers to determine whether or not the given values are valid.
     * 
     * @param name        the name of the load
     * 
     * @param weight      the weight of the load
     * 
     * @param value       the value in dollars of the load
     * 
     * @param isDangerous whether or not the load is dangerous or not
     * 
     * @throws IllegalArgumentException if the given values fail the valid checkers
     */
    public ProductLoad(String name, double weight, double value, boolean isDangerous) throws IllegalArgumentException {

        // Checks the values. If invalid it will pass on the throw Exception of the
        // valid checkers

        validName(name);
        validWeight(weight);
        validValue(value);
        validIsDangerous(isDangerous);

        this.name = name;
        this.weight = weight;
        this.value = value;
        this.isDangerous = isDangerous;
    }

    /**
     * Returns the name of the load.
     *
     * @return Returns the name of the load.
     **/
    public String getName() {
        return name;
    }

    /**
     * Returns the weigth of the load.
     *
     * @return Returns the weight of the items in the load.
     **/
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the value of the load.
     *
     * @return Returns the value of the items in the load.
     **/
    public double getValue() {
        return value;
    }

    /**
     * Returns the whether or not the load is dangerous.
     *
     * @return Returns whether or not the load contains dangerous it number of
     *         items.
     **/
    public boolean isDangerous() {
        return isDangerous;
    }

    /**
     * Sets the name of this ProductLoad object
     * 
     * @param name
     * 
     * @throws IllegalArgumentException
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the name of this Course object
     * 
     * @param weight
     * 
     * @throws IllegalArgumentException
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Sets the name of this Course object
     * 
     * @param value
     * 
     * @throws IllegalArgumentException
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets the name of this Course object
     * 
     * @param isDangerous
     * 
     * @throws IllegalArgumentException
     */
    public void setDangerous(boolean isDangerous) {
        this.isDangerous = isDangerous;
    }

    /**
     * Checks whether or not the given Object is a valid name
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            name
     * 
     * @return true if the Object passes all conditions and no errors are thrown
     * 
     * @throws IllegalArgumentException If Object is not an instance of String,
     *                                  contains letters other than letters and
     *                                  spaces, is empty, or is greater than 25
     *                                  characters
     */
    public static boolean validName(Object obj) throws IllegalArgumentException {
        if (obj instanceof String) {
            String name = (String) obj;

            if (name.length() == 0)
                throw new IllegalArgumentException("The given name is empty");

            if (name.length() > 25)
                throw new IllegalArgumentException("The given name is too long");

            if (!name.matches("[a-zA-Z ]*"))
                throw new IllegalArgumentException("The given name contains illegal characters");

            return true;
        }

        throw new IllegalArgumentException("The given name not an instance of String");
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
     * Checks whether or not the given Object is a valid value
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            value
     * 
     * @return true if the Object passes all conditions and no errors are thrown
     * 
     * @throws IllegalArgumentException If Object is not an instance of double, or
     *                                  is negative
     */
    public static boolean validValue(Object obj) throws IllegalArgumentException {
        if (obj instanceof Double) {
            double value = (double) obj;

            if (value < 0)
                throw new IllegalArgumentException("The given value is negative");

            return true;
        }

        throw new IllegalArgumentException("The given value not an instance of double");
    }

    /**
     * Checks whether or not the given Object is a valid isDangerous
     * 
     * @param obj The Object that needs to be checked whether or not it is a valid
     *            isDangerous
     * 
     * @return true if the Object passes all conditions and no errors are thrown
     * 
     * @throws IllegalArgumentException If Object is not an instance of boolean
     */
    public static boolean validIsDangerous(Object obj) throws IllegalArgumentException {
        if (obj instanceof Boolean) {
            return true;
        }

        throw new IllegalArgumentException("The given value not an instance of boolean");
    }
}