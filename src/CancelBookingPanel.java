import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class CancelBookingPanel extends JPanel {

    private MainApp mainApp;

    private JLabel resultLabel;
    private JButton cancelBtn;

    public CancelBookingPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());

        // ================= TOP =================
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel title = new JLabel("Cancel Booking");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(title);
        add(topPanel, BorderLayout.NORTH);

        // ================= FORM =================
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Fields
        JTextField bookingField = new JTextField(15);
        JTextField surnameField = new JTextField(15);

        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Booking ID:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(bookingField, gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Surname:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(surnameField, gbc);

        // ================= RESULT LABEL =================
        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // ================= BUTTONS =================
        JButton getBtn = new JButton("Get Booking");
        cancelBtn = new JButton("Cancel Booking");
        cancelBtn.setVisible(false);

        // ================= GET BOOKING =================
        getBtn.addActionListener(e -> {

            String bookingID = bookingField.getText().trim();
            String surname = surnameField.getText().trim();

            if (bookingID.isEmpty() || surname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            Booking b = BookingManager.getBooking(bookingID, surname);

            if (b == null) {
                resultLabel.setText("No booking found.");
                cancelBtn.setVisible(false);
                return;
            }

            String status = BookingManager.getStatus(bookingID);
            if (status.equals("CANCELLED")) {
                status = "CANCELLED";
            } else if (BookingManager.hasBookingPassed(bookingID)) {
                status = "COMPLETED";
            } else {
                status = "ACTIVE";
            }

            String statusColour = status.equals("ACTIVE") ? "green" : "red";
            statusColour = status.equals("COMPLETED") ? "blue" : statusColour;

            DateTimeFormatter inputFormat =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            DateTimeFormatter dateFormat =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDateTime placement = LocalDateTime.parse(b.placementDateTime, inputFormat);
            LocalDateTime booking = LocalDateTime.parse(b.bookingDateTime, inputFormat);

            LocalDateTime now = LocalDateTime.now();

            Duration duration = Duration.between(now, booking);

            boolean past = duration.isNegative();
            if (past) {
                duration = duration.abs();
            }

            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;

            String timeUntil = past
                    ? "Booking passed " + hours + "h " + minutes + "m ago"
                    : hours + "h " + minutes + "m until booking";

            resultLabel.setText(
                "<html>" +
                "Booking: " + b.bookingRef + "<br>" +
                "Surname: " + b.surname + "<br>" +
                "Placement Date: " + placement.format(dateFormat) + "<br>" +
                "Booking Date: " + booking.format(dateFormat) + "<br>" +
                "Time Remaining: " + timeUntil + "<br>" +
                "Vehicle: " + b.vehicleType + "<br>" +
                "Luggage: " + b.luggageCount + "<br>" +
                "Cost: £" + String.format("%.2f", b.cost) + "<br>" +
                "<font color='" + statusColour + "'><b>" + status + "</b></font>" +
                "</html>"
            );

            cancelBtn.setVisible(status.equals("ACTIVE"));
        });

        // ================= CANCEL BOOKING =================
        cancelBtn.addActionListener(e -> {

    String bookingID = bookingField.getText().trim();
    String surname = surnameField.getText().trim();

    double fee = BookingManager.getCancellationFee(bookingID);

    int option;

    if (fee > 0) {

        option = JOptionPane.showOptionDialog(
            this,
            "Your booking is less than 36 hours away.\n" +
            "If you cancel there will be a 10% cancellation fee.\n\n" +
            "This amounts to: £" + String.format("%.2f", fee) + "\n\n" +
            "Are you sure you would like to continue?",
            "Cancellation Warning",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            new String[]{"Cancel Booking", "Go Back"},
            "Go Back"
        );

    } else {

        option = JOptionPane.showOptionDialog(
            this,
            "Are you sure you would like to continue to cancellation?",
            "Confirm Cancellation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new String[]{"Cancel Booking", "Go Back"},
            "Go Back"
        );
    }

    // ================= USER CHOICE =================
    if (option == JOptionPane.YES_OPTION) {

        String result = BookingManager.cancelBooking(bookingID, surname);

        if (result.equals("success")) {

            JOptionPane.showMessageDialog(
                this,
                "Booking cancelled successfully."
            );

            getBtn.doClick(); // refresh UI

        } else if (result.equals("not found")) {
            JOptionPane.showMessageDialog(this, "No matching booking found.");

        } else if (result.equals("already cancelled")) {
            JOptionPane.showMessageDialog(this, "Booking already cancelled.");

        } else {
            JOptionPane.showMessageDialog(this, "Error cancelling booking.");
        }
    }
});

        // ================= ADD TO PANEL =================
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(getBtn, gbc);

        gbc.gridy = 3;
        formPanel.add(resultLabel, gbc);

        gbc.gridy = 4;
        formPanel.add(cancelBtn, gbc);

        add(formPanel, BorderLayout.CENTER);

        // ================= BOTTOM =================
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> mainApp.showPage("menu"));
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}