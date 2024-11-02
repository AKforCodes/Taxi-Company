import java.util.ArrayList;
import java.util.List;

/**
 * Model shared features of taxis and shuttles.
 */
public class Vehicle
{
    private String id;
    private ArrayList<Booking> bookings;

    /**
     * Constructor for objects of class Vehicle.
     * @param id The vehicle's ID.
     */
    public Vehicle(String id)
    {
        this.id = id;
        this.bookings = new ArrayList<>();
    }

    /**
     * Return the booking ArrayList;
     * @return bookings
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Add a booking to the vehicle's list of bookings.
     * @param booking The booking to be added.
     */
    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    /**
     * Return the id detail.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Return the daily takings for this vehicle.
     * @return The takings.
     */
    public int getTakings()
    {
        throw new RuntimeException("getTakings must be overridden");
    }

    /**
     * Return details of this vehicle.
     * @return A string representation of this vehicle.
     */
    public String getDetails()
    {
        return "getDetails must be overridden";
    }
}
