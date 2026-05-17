import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BrowseServicesUITest {

    private BrowseServicesUI ui;

    @BeforeEach
    public void setUp() {
        // Initialize BrowseServicesUI instance for testing
        // NOTE: This will display the UI window; for pure unit testing,
        // consider refactoring calculateCost into a separate utility class
        ui = new BrowseServicesUI();
    }

    // Test basic calculation with Standard vehicle, no luggage, Morning time
    @Test
    public void testBasicCalculation() {
        double cost = ui.calculateCost("Standard", "0 bags", 10.0, "Morning");
        double expected = (5.0 + 1.2 * 10.0) * 1.0 * 1.0; // (5 + 12) * 1.0 * 1.0 = 17.0
        assertEquals(expected, cost, 0.01);
    }

    // Test Executive vehicle multiplier
    @Test
    public void testExecutiveVehicleMultiplier() {
        double cost = ui.calculateCost("Executive", "0 bags", 10.0, "Morning");
        double expected = (5.0 + 1.2 * 10.0) * 1.5 * 1.0; // (5 + 12) * 1.5 * 1.0 = 25.5
        assertEquals(expected, cost, 0.01);
    }

    // Test Minivan vehicle multiplier
    @Test
    public void testMinivanVehicleMultiplier() {
        double cost = ui.calculateCost("Minivan", "0 bags", 10.0, "Morning");
        double expected = (5.0 + 1.2 * 10.0) * 1.3 * 1.0; // (5 + 12) * 1.3 * 1.0 = 22.1
        assertEquals(expected, cost, 0.01);
    }

    // Test luggage fee for 1 bag
    @Test
    public void testLuggageOneBag() {
        double cost = ui.calculateCost("Standard", "1 bag", 10.0, "Morning");
        double expected = (5.0 + 1.2 * 10.0 + 2.0) * 1.0 * 1.0; // (5 + 12 + 2) * 1.0 = 19.0
        assertEquals(expected, cost, 0.01);
    }

    // Test luggage fee for 2 bags
    @Test
    public void testLuggageTwoBags() {
        double cost = ui.calculateCost("Standard", "2 bags", 10.0, "Morning");
        double expected = (5.0 + 1.2 * 10.0 + 4.0) * 1.0 * 1.0; // (5 + 12 + 4) * 1.0 = 21.0
        assertEquals(expected, cost, 0.01);
    }

    // Test luggage fee for 3+ bags
    @Test
    public void testLuggageThreePlusBags() {
        double cost = ui.calculateCost("Standard", "3+ bags", 10.0, "Morning");
        double expected = (5.0 + 1.2 * 10.0 + 6.0) * 1.0 * 1.0; // (5 + 12 + 6) * 1.0 = 23.0
        assertEquals(expected, cost, 0.01);
    }

    // Additional sanity checks that align with the current calculateCost API
    @Test
    public void testAfternoonTimeNoMultiplier() {
        double cost = ui.calculateCost("Standard", "0 bags", 10.0, "Afternoon");
        double expected = (5.0 + 1.2 * 10.0) * 1.0 * 1.0; // (5 + 12) * 1.0 = 17.0
        assertEquals(expected, cost, 0.01);
    }

    @Test
    public void testZeroDistance() {
        double cost = ui.calculateCost("Standard", "0 bags", 0.0, "Morning");
        double expected = (5.0 + 0.0) * 1.0 * 1.0;
        assertEquals(expected, cost, 0.01);
    }

    @Test
    public void testLargeDistance() {
        double cost = ui.calculateCost("Standard", "0 bags", 100.0, "Morning");
        double expected = (5.0 + 1.2 * 100.0) * 1.0 * 1.0; // (5 + 120) * 1.0 = 125.0
        assertEquals(expected, cost, 0.01);
    }

    @Test
    public void testRepeatableCalculation() {
        double cost1 = ui.calculateCost("Standard", "0 bags", 10.0, "Morning");
        double cost2 = ui.calculateCost("Standard", "0 bags", 10.0, "Morning");
        double cost3 = ui.calculateCost("Standard", "0 bags", 10.0, "Morning");
        assertEquals(cost1, cost2, 0.01);
        assertEquals(cost2, cost3, 0.01);
    }
}

