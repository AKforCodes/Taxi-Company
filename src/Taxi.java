/**
 * Model a Taxi.
 */
public class Taxi extends Vehicle
{
    private String driver;
    private int pricePerJourney;

    /**
     * Constructor for creating a Taxi object.
     * @param id The taxi's ID.
     * @param driver The taxi's driver.
     * @param pricePerJourney The price per journey.
     */

    public Taxi(String id, String driver, int pricePerJourney)
    {
        super(id);
        this.driver = driver;
        this.pricePerJourney = pricePerJourney;
    }

    /**
     * Getter method of the driver of the taxi.
     * @return The driver of the taxi.
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Getter method of the price per journey of the taxi.
     * @return The price per journey of the taxi.
     */
    public int getPricePerJourney() {
        return pricePerJourney;
    }

    /**
     * Override the toString method to return a string representation of the Taxi object.
     * @return A string representation of the Taxi object.
     */
    public String toString(){
        return "Taxi " + this.getId() + " driven by " + driver;
    }

    /**
     * Override the getTakings method to calculate the total takings of the taxi.
     * @return The total takings of the taxi.
     */
    @Override
    public int getTakings(){
        return pricePerJourney * getBookings().size();
    }

}

