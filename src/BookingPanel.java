import javax.swing.*;
import java.awt.*;

//Wahab
public class BookingPanel extends JPanel {

    private MainApp mainApp;

    public BookingPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Booking Page");
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> {
            mainApp.showPage("menu"); // go to another page
        });

        add(label);
        add(backButton);
    }

}
