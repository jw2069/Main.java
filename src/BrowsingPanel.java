import javax.swing.*;
import java.awt.*;

//Adam - Discount Offers | Carl - Browsing
public class BrowsingPanel extends JPanel {

    private MainApp mainApp;

    public BrowsingPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Browsing Page");
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> {
            mainApp.showPage("menu"); // go to another page
        });

        add(label);
        add(backButton);
    }

}
