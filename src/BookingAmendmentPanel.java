import javax.swing.*;
import java.awt.*;

//Carl - Payment | Wahab
public class BookingAmendmentPanel extends JPanel {

    private MainApp mainApp;

    public BookingAmendmentPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Booking Amendment Page");
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> {
            mainApp.showPage("menu"); // go to another page
        });

        add(label);
        add(backButton);
    }

}
