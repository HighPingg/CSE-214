package Homework_3;

public class Complexity {

    // the exponent of the n variable inside the Big-Oh notation
    private int nPower;

    // the base of the log expression inside the Big-Oh notation
    private int logPower;

    /**
     * Constructor that initializes a new <code>Complexity</code> and sets the
     * values of <code>n_power</code> and <code>log_power</code> to 0.
     */
    public Complexity() {
        this.nPower = 0;
        this.logPower = 0;
    }

    /**
     * Overloaded Constructor that creates a new <code>Complexity</code> and sets
     * the values of <code>n_power</code> and <code>log_power</code> to the given
     * values
     * 
     * @param nPower   the value to set n_power to
     * 
     * @param logPower the value to set log_power to
     */
    public Complexity(int nPower, int logPower) {

        this.nPower = nPower;
        this.logPower = logPower;
    }

    /**
     * Returns the current value of <code>n_power</code>
     * 
     * @return the value of <code>n_power</code>
     */
    public int getNPower() {
        return nPower;
    }

    /**
     * Returns the current value of <code>log_power</code>
     * 
     * @return the value of <code>log_power</code>
     */
    public int getLogPower() {
        return logPower;
    }

    /**
     * Sets the value of <code>n_power</code> to the given value.
     * 
     * @param n_power the value to set <code>n_power</code> to.
     */
    public void setNPower(int n_power) {
        this.nPower = n_power;
    }

    /**
     * Sets the value of <code>log_power</code> to the given value.
     * 
     * @param log_power the value to set <code>log_power</code> to.
     */
    public void setLogPower(int log_power) {
        this.logPower = log_power;
    }

    /**
     * Returns a human readable Big-Oh notation as a string.
     * 
     * @return the Big-Oh notation as a String.
     */
    @Override
    public String toString() {

        if (nPower == 0 && logPower == 0) {

            // Complexity is O(1) if nPower and logPower is 0.
            return "O(1)";

        }

        // Holds the "^(int)" expression and will print it out inside the return value.
        // They will remain empty if nPower or logPower equals 0 or 1.
        String nPowerString = "";
        String logPowerString = "";

        // if nPower or logPower is not equal to 1 or 0, it will concatenate ^ and the
        // power of its respective experssion to the String
        if (nPower != 0 || nPower != 1) {
            nPowerString += String.format("^%d", nPower);
        }
        if (logPower != 0 || logPower != 1) {
            logPowerString += String.format("^%d", logPower);
        }

        // if either nPower or logPower is 0, it will won't add that respective
        // expression into the Big-Oh expression
        if (nPower == 0) {

            return String.format("O(log(n)^%s)", logPowerString);

        } else if (logPower == 0) {

            return String.format("O(n^%s)", nPowerString);

        }

        // if both nPower and logPower aren't equal to 0, just print out the Big-Oh
        // notation with its respective values (Note: if nPower or logPower == 1, it
        // will print nothing after it)
        return String.format("O(n%s * log(n)%s)", nPowerString, logPowerString);
    }
}
