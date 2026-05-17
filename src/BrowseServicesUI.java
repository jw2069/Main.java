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
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Components
        JLabel vehicleLabel = new JLabel("Vehicle Type:");
        String[] vehicles = {"Standard", "Executive", "Minivan"};
        JComboBox<String> vehicleBox = new JComboBox<>(vehicles);

        JLabel luggageLabel = new JLabel("Luggage Amount:");
        String[] luggageOptions = {"0 bags", "1 bag", "2 bags", "3+ bags"};
        JComboBox<String> luggageBox = new JComboBox<>(luggageOptions);

        JLabel distanceLabel = new JLabel("Distance to Airport (km):");
        JTextField distanceField = new JTextField();


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
                String time = (String) timeBox.getSelectedItem();

                double cost = calculateCost(vehicle, luggage, distance, time);
                resultLabel.setText("Estimated Cost: £" + String.format("%.2f", cost));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid distance.");
            }
        });

        setVisible(true);
    }

    // Wrapper method for UI
    double calculateCost(String vehicle, String luggage, double distance, String time) {
        return TariffCalculator.calculateCost(vehicle, luggage, distance, time);
    }

    // Main method to run the UI
    public static void main(String[] args) {
        new BrowseServicesUI();
    }
}
