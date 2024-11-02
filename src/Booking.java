public record Booking(String vehicleId, String pickupLocation, String destination) {
    // This record defines a Booking with three components: vehicleId, pickupLocation, and destination.

    @Override
    public String toString(){
        // Override toString method to provide a custom string representation of the Booking.
        return vehicleId + "," + pickupLocation + "," + destination;
        // Returns a string concatenating the booking information.
    }
}
