import javax.swing.*;
import java.awt.*;

//Adam
public class BookingConfirmationPanel extends JPanel {

    private MainApp mainApp;

    public BookingConfirmationPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Booking Confirmation Page");
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> {
            mainApp.showPage("menu"); // go to another page
        });

        add(label);
        add(backButton);
    }

}
