import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddCustomerUI extends JFrame {

    public static final int FRAME_HEIGHT = 1200, FRAME_WIDTH = 800, FIELD_WIDTH = 30;

    DataAdapter dataAccess;
    CustomerUI parent;

    JTextField nameField = new JTextField(FIELD_WIDTH);
    JTextField customerIDField = new JTextField(FIELD_WIDTH);
    JTextField emailField = new JTextField(FIELD_WIDTH);
    JTextField phoneField = new JTextField(FIELD_WIDTH);
    JTextField addressField = new JTextField(FIELD_WIDTH);
    JTextField paymentInfoField = new JTextField(FIELD_WIDTH);

    JButton saveButton = new JButton("Save Customer");

    public AddCustomerUI() {
        this.setTitle("Add Customer");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        dataAccess = Application.getInstance().getDataAdapter();

        JPanel field1 = new JPanel();
        field1.add(new JLabel("Name:"));
        field1.add(nameField);
        this.getContentPane().add(field1, Component.CENTER_ALIGNMENT);

        JPanel field2 = new JPanel();
        field2.add(new JLabel("Customer ID:"));
        field2.add(customerIDField);
        this.getContentPane().add(field2, Component.CENTER_ALIGNMENT);

        JPanel field3 = new JPanel();
        field3.add(new JLabel("Email:"));
        field3.add(emailField);
        this.getContentPane().add(field3, Component.CENTER_ALIGNMENT);

        JPanel field4 = new JPanel();
        field4.add(new JLabel("Phone:"));
        field4.add(phoneField);
        this.getContentPane().add(field4, Component.CENTER_ALIGNMENT);

        JPanel field5 = new JPanel();
        field5.add(new JLabel("Address:"));
        field5.add(addressField);
        this.getContentPane().add(field5, Component.CENTER_ALIGNMENT);

        JPanel field6 = new JPanel();
        field6.add(new JLabel("Payment Info:"));
        field6.add(paymentInfoField);
        this.getContentPane().add(field6, Component.CENTER_ALIGNMENT);

        JPanel button1 = new JPanel();
        button1.add(saveButton);
        this.getContentPane().add(button1, Component.CENTER_ALIGNMENT);

        saveButton.addActionListener(new SaveListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AddCustomerUI.this.dispose();
            }
        });

    }

    public void run() {
        Dimension screen = Application.getInstance().getScreenSize();
        this.setLocation(screen.width/2-this.getSize().width/2, screen.height/2-this.getSize().height/2);
        this.pack();
        this.setVisible(true);
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(AddCustomerUI.this, "I'll make sure to save that for you.");

            try {
                CustomerModel inputCustomer = new CustomerModel(
                        Integer.parseInt(customerIDField.getText()),
                        nameField.getText(),
                        emailField.getText(),
                        phoneField.getText(),
                        addressField.getText(),
                        paymentInfoField.getText()
                );

                dataAccess.saveCustomer(inputCustomer);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            customerIDField.setText("");
            nameField.setText("");
            emailField.setText("");
            phoneField.setText("");
            addressField.setText("");
            paymentInfoField.setText("");

            if (parent != null) {
                parent.updateTable();
            }
        }
    }
}
