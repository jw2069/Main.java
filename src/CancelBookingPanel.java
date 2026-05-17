import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//Josh
public class CancelBookingPanel extends JPanel {

    private MainApp mainApp;

    public CancelBookingPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Cancel Booking Page");
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> {
            mainApp.showPage("menu"); // go to another page
        });

        add(label);
        add(backButton);
    }

}
