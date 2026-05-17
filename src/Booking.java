public class Booking {

    String bookingRef;
    String surname;
    String placementDateTime;
    String vehicleType;
    int luggageCount;
    String bookingDateTime;
    double cost;
    boolean isCancelled;

    public Booking(String bookingRef,
                   String surname,
                   String placementDateTime,
                   String vehicleType,
                   int luggageCount,
                   String bookingDateTime,
                   double cost,
                   boolean isCancelled) {

        this.bookingRef = bookingRef;
        this.surname = surname;
        this.placementDateTime = placementDateTime;
        this.vehicleType = vehicleType;
        this.luggageCount = luggageCount;
        this.bookingDateTime = bookingDateTime;
        this.cost = cost;
        this.isCancelled = isCancelled;
    }
}