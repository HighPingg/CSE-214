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
     * @param weight      the weight of the load
     * @param value       the value in dollars of the load
     * @param isDangerous whether or not the load is dangerous or not
     */
    public ProductLoad(String name, double weight, double value, boolean isDangerous) {

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

}
