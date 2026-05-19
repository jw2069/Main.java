import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

//Adam Mounir
public class BookingConfirmationPanel extends JPanel {

    private MainApp mainApp;
    private JLabel fileStatusLabel;
    private File uploadedIDFile;

    public BookingConfirmationPanel(MainApp mainApp) {
        this.mainApp = mainApp;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Booking Confirmation Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel detailsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Your Booking Details"));
        detailsPanel.add(new JLabel("Status: Confirmed"));
        detailsPanel.add(new JLabel("Driver Assigned: Pending ID Verification"));
        detailsPanel.setMaximumSize(new Dimension(400, 100));
        detailsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel uploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        uploadPanel.setBorder(BorderFactory.createTitledBorder("Driver ID Verification"));

        JButton uploadButton = new JButton("Upload Driver's License");
        fileStatusLabel = new JLabel("No file uploaded");

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                uploadedIDFile = fileChooser.getSelectedFile();
                fileStatusLabel.setText("Uploaded: " + uploadedIDFile.getName());
            }
        });
        uploadPanel.add(uploadButton);
        uploadPanel.add(fileStatusLabel);
        uploadPael.setMaximumSize(new Dimension(400, 80));
        uploadPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back to Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            mainApp.showPage("menu");
        });

        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(detailsPanel);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(uploadPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(backButton);
        

        backButton.addActionListener(e -> {
            mainApp.showPage("menu"); // go to another page
        });

        add(label);
        add(backButton);
    }

}
