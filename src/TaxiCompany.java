import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TaxiCompany
{
    private HashMap<String, Vehicle> storeVehicle;

    /**
     * Constructs a new TaxiCompany object.
     */
    public TaxiCompany()
    {
        storeVehicle = new HashMap<>();
    }

    /**
     * Adds a vehicle to the taxi company's fleet.
     * @param aVehicle The vehicle to add.
     */
    public void addVehicle(Vehicle aVehicle)
    {
        storeVehicle.put(aVehicle.getId(), aVehicle);
    }

    /**
     * Lists all vehicles in the taxi company's fleet.
     */
    public void listAllVehicles()
    {
        Iterator<Vehicle> it = storeVehicle.values().iterator();
        while(it.hasNext()){
            Vehicle vehicle = it.next();
            System.out.println(vehicle.toString());
        }
    }

    /**
     * Lists all bookings for all vehicles in the taxi company's fleet.
     */
    public void listAllBookings()
    {
        for (Vehicle vehicle : storeVehicle.values()) {
            for (Booking booking : vehicle.getBookings()) {
                System.out.println(booking.toString());
            }
            System.out.println();
        }
    }

    /**
     * Gets the total takings for a specific vehicle.
     * @param id The ID of the vehicle.
     * @return The total takings of the vehicle, or -1 if the vehicle is not found.
     */
    public int getVehicleTakings(String id)
    {
        Vehicle vehicle = storeVehicle.get(id);
        if (vehicle != null) {
            return vehicle.getTakings();
        } else {
            System.out.println("Vehicle with id " + id + " not found.");
            return -1;
        }
    }

    /**
     * Read the bookings from the given file.
     * @param bookingsFilename The name of the file containing the bookings.
     * @throws IOException If there is an error reading the file.
     */
    public void readBookings(String bookingsFilename)
            throws IOException
    {
        Path filePath = Paths.get(bookingsFilename);
        List<String> lines = Files.readAllLines(filePath);
        for(String bookingDetails : lines) {
            bookingDetails = bookingDetails.trim();
            String[] parts = bookingDetails.split(",");
            String id = parts[0];
            String pickupLocation = parts[1];
            String destination = parts[2];

            // Using the id, pickupLocation and destination.
            Booking bookingX = new Booking(id,pickupLocation,destination);
            Vehicle vehicle = storeVehicle.get(id);
            if (vehicle != null) {
                vehicle.addBooking(bookingX);
            } else {
                System.out.println("Vehicle with id " + vehicle + " not found.");
            }
        }
    }


    /**
     * Write a report of all of the vehicles.
     * @param reportFilename The name of the file to write the report to.
     * @throws IOException If there is an error writing the file.
     */
    public void writeReport(String reportFilename)
        throws IOException
    {
        FileWriter writer = new FileWriter(reportFilename);
        writer.write("Taxi Company Report\n");
        // Iterate over all vehicles in the HashMap
        int totalTakings = 0; // Initialize total takings

        // Iterate over all vehicles in the HashMap
        for (Vehicle vehicle : storeVehicle.values()) {
            // Check if the vehicle is a Taxi
            if (vehicle instanceof Taxi) {
                Taxi taxi = (Taxi) vehicle;
                int takings = taxi.getTakings();
                totalTakings += takings; // Add takings to total
                // Format the output for taxis
                writer.write("Taxi " + taxi.getId() + " driven by " + taxi.getDriver() +
                        " had " + taxi.getBookings().size() + " journeys and made £" +
                        takings + "\n");
            }
            // Check if the vehicle is a Shuttle
            else if (vehicle instanceof Shuttle) {
                Shuttle shuttle = (Shuttle) vehicle;
                int takings = shuttle.getTakings();
                totalTakings += takings; // Add takings to total
                // Format the output for shuttles
                writer.write("Shuttle " + shuttle.getId() + " had " +
                        shuttle.getBookings().size() + " journeys and made £" +
                        takings + "\n");
            }
        }

        // Write the total takings summary
        writer.write("\nTotal takings for the day £" + totalTakings);

        writer.close();
    }
}
