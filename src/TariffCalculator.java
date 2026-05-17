/**
 * Utility class for calculating airport taxi tariffs.
 * Separated from UI for testability.
 */
public class TariffCalculator {

    /**
     * Calculates the cost based on vehicle type, luggage, distance, and time.
     *
     * @param vehicle   Vehicle type: "Standard", "Executive", or "Minivan"
     * @param luggage   Luggage amount: "0 bags", "1 bag", "2 bags", or "3+ bags"
     * @param distance  Distance to airport in kilometers
     * @param time      Time of day: "Morning", "Afternoon", "Evening", or "Night"
     * @return Calculated cost in pounds
     */
    public static double calculateCost(String vehicle, String luggage, double distance, String time) {
        double base = 5.0;
        double perKm = 1.2 * distance;

        double vehicleMultiplier = switch (vehicle) {
            case "Executive" -> 1.5;
            case "Minivan" -> 1.3;
            default -> 1.0;
        };

        double luggageFee = switch (luggage) {
            case "1 bag" -> 2;
            case "2 bags" -> 4;
            case "3+ bags" -> 6;
            default -> 0;
        };

        double timeMultiplier = switch (time) {
            case "Night" -> 1.4;
            case "Evening" -> 1.2;
            default -> 1.0;
        };

        return (base + perKm + luggageFee) * vehicleMultiplier * timeMultiplier;
    }
}

