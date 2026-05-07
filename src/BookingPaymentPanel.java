import javax.swing.*;
import java.awt.*;

// Josh
public class BookingPaymentPanel extends JPanel {

    private MainApp mainApp;

    public BookingPaymentPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Booking Payment Page");
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> {
            mainApp.showPage("menu"); // go to another page
        });

        add(label);
        add(backButton);
    }

}
