import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MattressSalesGUI extends JFrame {

    private JTextField txtCustomer;
    private JTextField txtPrice;

    private JComboBox<String> cmbProduct;

    private JButton btnProcess;

    public MattressSalesGUI() {

        setTitle("Mattress Sales Application");

        setSize(450, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(5, 2, 10, 10));

        // Menu bar

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem exitItem = new JMenuItem("Exit");

        JMenuItem processItem = new JMenuItem("Process");

        JMenuItem clearItem = new JMenuItem("Clear");

        fileMenu.add(exitItem);

        toolsMenu.add(processItem);

        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);

        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);

        // Customer name

        add(new JLabel("Customer Name:"));

        txtCustomer = new JTextField();

        add(txtCustomer);

        // Product selection

        add(new JLabel("Select Product:"));

        cmbProduct = new JComboBox<>(new String[]{
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        });

        add(cmbProduct);

        // Product price

        add(new JLabel("Enter Price:"));

        txtPrice = new JTextField();

        add(txtPrice);

        // Process button

        btnProcess = new JButton("PROCESS");

        add(new JLabel(""));

        add(btnProcess);

        // Button action

        btnProcess.addActionListener(e -> processSale());

        processItem.addActionListener(e -> processSale());

        clearItem.addActionListener(e -> clearFields());

        exitItem.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
    }

    private void processSale() {

        try {

            String customer = txtCustomer.getText().trim();

            String product = (String) cmbProduct.getSelectedItem();

            if (customer.isEmpty() || txtPrice.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter all information."
                );

                return;
            }

            double price = Double.parseDouble(txtPrice.getText());

            if (price <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid price."
                );

                return;
            }

            ProductCalculations calculation =
                    new ProductCalculations(price);

            double vat = calculation.GetVat();

            double commission = calculation.GetCommission();

            double total = calculation.GetTotal();

            // Display results

            JOptionPane.showMessageDialog(
                this,
                "Customer Name: " + customer
                + "\nProduct: " + product
                + "\nVAT: R " + vat
                + "\nCOMMISSION: R " + commission
                + "\nTOTAL: R " + total
            );

            // Save information to data.txt

            try (PrintWriter writer = new PrintWriter(
                    new FileWriter("data.txt", true))) {

                writer.println("DATA LOG");
                writer.println("********************************");
                writer.println("Customer Name: " + customer);
                writer.println("Product: " + product);
                writer.println("VAT: R " + vat);
                writer.println("COMMISSION: R " + commission);
                writer.println("TOTAL: R " + total);
                writer.println("********************************");

            }

            JOptionPane.showMessageDialog(
                this,
                "Information saved successfully!"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numerical price."
            );

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Error saving data: " + ex.getMessage()
            );
        }
    }

    private void clearFields() {

        txtCustomer.setText("");

        txtPrice.setText("");

        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new MattressSalesGUI().setVisible(true);

        });
    }
}