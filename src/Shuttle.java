import java.util.Arrays;

/**
 * Model a shuttle on a circular route.
 */
public class Shuttle extends Vehicle
{
    private int pricePerStop;
    private String[] route;

    /**
     *
     * @param id The shuttle's ID.
     * @param pricePerStop The price per stop.
     * @param route The route.
     */
    public Shuttle(String id, int pricePerStop, String[] route)
    {
        super(id);
        this.pricePerStop = pricePerStop;
        this.route = route;
    }

    /**
     * Get the price charged per stop.
     * @return The price per stop.
     */
    public int getPricePerStop() {
        return pricePerStop;
    }

    /**
     * Get the array of destinations on the route.
     * @return The route array.
     */
    public String[] getRoute() {
        return route;
    }

    /**
     * Return a string representation of the Shuttle object.
     * @return A string describing the Shuttle object.
     */
    @Override
    public String toString() {
        return "Shuttle " + this.getId() + " has the route " + Arrays.toString(route);
    }

    /**
     * Overrides the getTakings method to calculate the total takings of the shuttle based on the number of stops in the journey
     * and the price per stop.
     * @return The total takings of the shuttle.
     */
    @Override
    public int getTakings() {
        int totalStops = 0;
        String[] route = getRoute();
        for (Booking booking : getBookings()) {
            String pickupLocation = booking.pickupLocation();
            String destination = booking.destination();

            // Find the index of pickup and destination in the route array
            int pickupIndex = Arrays.asList(route).indexOf(pickupLocation);
            int destinationIndex = Arrays.asList(route).indexOf(destination);

            // Calculate the number of stops for the journey
            int stops = Math.abs(destinationIndex - pickupIndex);
            // Adjust stops for circular routes
            if (destinationIndex < pickupIndex) {
                stops = route.length - stops;
            }

            totalStops += stops;
        }
        return totalStops * getPricePerStop();
    }
}
