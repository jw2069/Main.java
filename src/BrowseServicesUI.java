import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrowseServicesUI extends JFrame {

    // Constructor
    public BrowseServicesUI() {
        super("Browse Services & Tariffs");

        // Window settings
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        // Components
        JLabel vehicleLabel = new JLabel("Vehicle Type:");
        String[] vehicles = {"Standard", "Executive", "Minivan"};
        JComboBox<String> vehicleBox = new JComboBox<>(vehicles);

        JLabel luggageLabel = new JLabel("Luggage Amount:");
        String[] luggageOptions = {"0 bags", "1 bag", "2 bags", "3+ bags"};
        JComboBox<String> luggageBox = new JComboBox<>(luggageOptions);

        JLabel distanceLabel = new JLabel("Distance to Airport (km):");
        JTextField distanceField = new JTextField();

        JLabel dayLabel = new JLabel("Day of Week:");
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        JComboBox<String> dayBox = new JComboBox<>(days);

        JLabel timeLabel = new JLabel("Time of Day:");
        String[] times = {"Morning", "Afternoon", "Evening", "Night"};
        JComboBox<String> timeBox = new JComboBox<>(times);

        JButton calculateButton = new JButton("Calculate Cost");
        JLabel resultLabel = new JLabel("Estimated Cost: £0.00");

        // Add components to panel
        panel.add(vehicleLabel);
        panel.add(vehicleBox);

        panel.add(luggageLabel);
        panel.add(luggageBox);

        panel.add(distanceLabel);
        panel.add(distanceField);

        panel.add(dayLabel);
        panel.add(dayBox);

        panel.add(timeLabel);
        panel.add(timeBox);

        panel.add(calculateButton);
        panel.add(resultLabel);

        add(panel, BorderLayout.CENTER);

        // Button logic
        calculateButton.addActionListener(e -> {
            try {
                double distance = Double.parseDouble(distanceField.getText());
                String vehicle = (String) vehicleBox.getSelectedItem();
                String luggage = (String) luggageBox.getSelectedItem();
                String day = (String) dayBox.getSelectedItem();
                String time = (String) timeBox.getSelectedItem();

                double cost = calculateCost(vehicle, luggage, distance, day, time);
                resultLabel.setText("Estimated Cost: £" + String.format("%.2f", cost));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid distance.");
            }
        });

        setVisible(true);
    }

    // Example formula (you can change this)
    private double calculateCost(String vehicle, String luggage, double distance, String day, String time) {
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

    // Main method to run the UI
    public static void main(String[] args) {
        new BrowseServicesUI();
    }
}
