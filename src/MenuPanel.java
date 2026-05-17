import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(MainApp main) {

        setLayout(new FlowLayout());

        JLabel title = new JLabel("Main Menu");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JButton bookingBtn = new JButton("Make Booking");
        JButton amendBtn = new JButton("Amend Booking");
        JButton cancelBtn = new JButton("Cancel Booking");
        JButton browseBtn = new JButton("Browse");
        JButton paymentBtn = new JButton("Payment");
        JButton confirmBtn = new JButton("Confirmation");

        bookingBtn.addActionListener(e -> main.showPage("booking"));
        amendBtn.addActionListener(e -> main.showPage("amend"));
        cancelBtn.addActionListener(e -> main.showPage("cancel"));
        browseBtn.addActionListener(e -> main.showPage("browse"));
        paymentBtn.addActionListener(e -> main.showPage("payment"));
        confirmBtn.addActionListener(e -> main.showPage("confirmation"));

        add(title);
        add(bookingBtn);
        add(amendBtn);
        add(cancelBtn);
        add(browseBtn);
        add(paymentBtn);
        add(confirmBtn);
    }
}