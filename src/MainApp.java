import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {

    private CardLayout cardLayout;
    private JPanel container;

    public MainApp() {
        setTitle("Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // Add everyone's panels
        container.add(new MenuPanel(this), "menu");
        container.add(new CancelBookingPanel(this), "cancel");
        container.add(new BookingPaymentPanel(this), "payment");
        container.add(new BookingConfirmationPanel(this), "confirmation");
        container.add(new BrowsingPanel(this), "browse");
        container.add(new BookingAmendmentPanel(this), "amend");
        container.add(new BookingPanel(this), "booking");

        add(container);
        container.add(new MenuPanel(this), "menu");
        setVisible(true);
    }

    // Method panels use to switch screen
    public void showPage(String name) {
        cardLayout.show(container, name);
    }

    public static void main(String[] args) {
        new MainApp();
    }
}