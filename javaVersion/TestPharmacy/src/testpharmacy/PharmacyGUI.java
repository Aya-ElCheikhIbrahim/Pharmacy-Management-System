package testpharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PharmacyGUI extends JFrame {

    private Connection connection; //object to connect to the data base

    public PharmacyGUI() { //method
        // Set up database connection
        String url = "jdbc:mysql://localhost:3306/projectdb"; //url of mysql
        String username = "root";
        String password = "Chem@20042005";

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }

        // Set up GUI components design
        setTitle("Pharmacy Management System"); //title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x to exit
        setLayout(new BorderLayout());
        setBackground(new Color(204, 229, 255)); // Light blue background

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); //leave spaces between bottons
        buttonsPanel.setBackground(new Color(204, 229, 255)); // Light blue background

        JButton addButton = createButton("Add Medicine");//new botton leads to the method openAddMedicineWindow
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddMedicineWindow();
            }
        });
        buttonsPanel.add(addButton); //set the button

        JButton sellMedicineButton = createButton("Sell Medicine");
        sellMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSellMedicineWindow();
            }
        });
        buttonsPanel.add(sellMedicineButton);

        JButton restockMedicineButton = createButton("Restock Medicine");
        restockMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRestockMedicineWindow();
            }
        });
        buttonsPanel.add(restockMedicineButton);

        JButton searchByNameButton = createButton("Search By Name");
        searchByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchByNameWindow();
            }
        });
        buttonsPanel.add(searchByNameButton);

        
        JButton searchByDoseButton = createButton("Search By Dose");
        searchByDoseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchByDoseWindow();
            }
        });
        buttonsPanel.add(searchByDoseButton);

        JButton searchByCompositionButton = createButton("Search By Composition");
        searchByCompositionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchByCompositionWindow();
            }
        });
        buttonsPanel.add(searchByCompositionButton);

        JButton changePriceButton = createButton("Change Price");
        changePriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openChangePriceWindow();
            }
        });
        buttonsPanel.add(changePriceButton);

        JButton deleteMedicineButton = createButton("Delete Medicine");
        deleteMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDeleteMedicineWindow();
            }
        });
        buttonsPanel.add(deleteMedicineButton);

        JButton exitButton = createButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
        buttonsPanel.add(exitButton);

        add(buttonsPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private JButton createButton(String text) { //button design
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 50));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(51, 153, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return button;
    }

private void openAddMedicineWindow() { //new window through the button
    JFrame addMedicineFrame = createWindow("Add Medicine"); //add medicine button window

    JPanel inputPanel = new JPanel(new GridLayout(7, 2));
    inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    inputPanel.add(createLabel("Name:"));
    JTextField nameField = new JTextField();
    inputPanel.add(nameField);

    inputPanel.add(createLabel("Composition:"));
    JTextField compositionField = new JTextField();
    inputPanel.add(compositionField);

    inputPanel.add(createLabel("Dose (mg):"));
    JTextField doseField = new JTextField();
    inputPanel.add(doseField);

    inputPanel.add(createLabel("Price:"));
    JTextField priceField = new JTextField();
    inputPanel.add(priceField);

    inputPanel.add(createLabel("Quantity:"));
    JTextField quantityField = new JTextField();
    inputPanel.add(quantityField);

    inputPanel.add(createLabel("Minimum Age:"));
    JTextField minAgeField = new JTextField();
    minAgeField.setText("0"); // Set default value to 0
    inputPanel.add(minAgeField);

    inputPanel.add(createLabel("Prescription Required:"));
    JCheckBox prescriptionCheckBox = new JCheckBox();
    inputPanel.add(prescriptionCheckBox);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //designed in the centre
    JButton confirmButton = createButton("Confirm"); 
    
    confirmButton.addActionListener(e -> {
        String name = nameField.getText(); //gets what is written in the previous field
        String composition = compositionField.getText();
        int dose = Integer.parseInt(doseField.getText());
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        int minAge = Integer.parseInt(minAgeField.getText());
        boolean prescription = prescriptionCheckBox.isSelected();
        addMedicine(name, composition, dose, price, quantity, minAge, prescription); //fills the culumns
        addMedicineFrame.dispose();
    });
    buttonPanel.add(confirmButton);

    addMedicineFrame.add(inputPanel, BorderLayout.CENTER);
    addMedicineFrame.add(buttonPanel, BorderLayout.SOUTH);

    addMedicineFrame.pack();  
    addMedicineFrame.setVisible(true);  //so the method appear
    addMedicineFrame.setLocationRelativeTo(null);
}


    private void openChangePriceWindow() {
        JFrame changePriceFrame = createWindow("Change Price");

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField();
        JTextField doseField = new JTextField();
        JTextField priceChangeField = new JTextField();

        inputPanel.add(createLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(createLabel("Dose (mg):"));
        inputPanel.add(doseField);

        inputPanel.add(createLabel("Price Change (%):"));
        inputPanel.add(priceChangeField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = createButton("Confirm");
        confirmButton.addActionListener(e -> {
            String name = nameField.getText();
            int dose = Integer.parseInt(doseField.getText());
            double percentage = Double.parseDouble(priceChangeField.getText());
            changePrice(name, dose, percentage);
            changePriceFrame.dispose();
        });
        buttonPanel.add(confirmButton);

        changePriceFrame.add(inputPanel, BorderLayout.CENTER);
        changePriceFrame.add(buttonPanel, BorderLayout.SOUTH);

        changePriceFrame.pack();
        changePriceFrame.setVisible(true);
        changePriceFrame.setLocationRelativeTo(null);
    }

    private void openSearchByNameWindow() {
        JFrame searchByNameFrame = createWindow("Search By Name");

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        JButton confirmButton = createButton("Search");
        confirmButton.addActionListener(e -> {
            String name = nameField.getText();
            searchByName(name);
            searchByNameFrame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(confirmButton);

        searchByNameFrame.add(inputPanel, BorderLayout.CENTER);
        searchByNameFrame.add(buttonPanel, BorderLayout.SOUTH);

        searchByNameFrame.pack();
        searchByNameFrame.setVisible(true);
        searchByNameFrame.setLocationRelativeTo(null);
    }

    private void openSearchByDoseWindow() {
        JFrame searchByDoseFrame = createWindow("Search By Dose");

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField doseField = new JTextField();
        inputPanel.add(new JLabel("Dose (mg):"));
        inputPanel.add(doseField);

        JButton confirmButton = createButton("Search");
        confirmButton.addActionListener(e -> {
            int dose = Integer.parseInt(doseField.getText());
            searchByDose(dose);
            searchByDoseFrame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(confirmButton);

        searchByDoseFrame.add(inputPanel, BorderLayout.CENTER);
        searchByDoseFrame.add(buttonPanel, BorderLayout.SOUTH);

        searchByDoseFrame.pack();
        searchByDoseFrame.setVisible(true);
        searchByDoseFrame.setLocationRelativeTo(null);
    }

    private void openSearchByCompositionWindow() {
        JFrame searchByDoseFrame = createWindow("Search By Composition");

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField compositionField = new JTextField();
        inputPanel.add(new JLabel("Composition :"));
        inputPanel.add(compositionField);

        JButton confirmButton = createButton("Search");
        confirmButton.addActionListener(e -> {
            String composition = compositionField.getText();
            searchByComposition(composition);
            searchByDoseFrame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(confirmButton);

        searchByDoseFrame.add(inputPanel, BorderLayout.CENTER);
        searchByDoseFrame.add(buttonPanel, BorderLayout.SOUTH);

        searchByDoseFrame.pack();
        searchByDoseFrame.setVisible(true);
        searchByDoseFrame.setLocationRelativeTo(null);
    }

    private void openSellMedicineWindow() {
        JFrame sellMedicineFrame = createWindow("Sell Medicine");

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        JTextField doseField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField minAgeField = new JTextField();
        JCheckBox prescriptionCheckBox = new JCheckBox();

        inputPanel.add(createLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(createLabel("Dose (mg):"));
        inputPanel.add(doseField);

        inputPanel.add(createLabel("Quantity:"));
        inputPanel.add(quantityField);

        inputPanel.add(createLabel("Customer Age:"));
        inputPanel.add(minAgeField);

        inputPanel.add(createLabel("Prescription Available:"));
        inputPanel.add(prescriptionCheckBox);

        JButton confirmButton = createButton("Sell");
        confirmButton.addActionListener(e -> {
            String name = nameField.getText();
            int dose = Integer.parseInt(doseField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            boolean prescriptionRequired = prescriptionCheckBox.isSelected();
            int minAge = 0; // Default age
            boolean medicineRequiresAge = requiresMinimumAge(name, dose);
            if (medicineRequiresAge) {
                if (minAgeField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Minimum age is required for selling this medicine!", "Minimum Age Required", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                minAge = Integer.parseInt(minAgeField.getText());
            }
            sellMedicine(name, dose, quantity, minAge, prescriptionRequired);
            sellMedicineFrame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(confirmButton);

        sellMedicineFrame.add(inputPanel, BorderLayout.CENTER);
        sellMedicineFrame.add(buttonPanel, BorderLayout.SOUTH);

        sellMedicineFrame.pack();
        sellMedicineFrame.setVisible(true);
        sellMedicineFrame.setLocationRelativeTo(null);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return label;
    }

    private void openRestockMedicineWindow() {
        JFrame restockMedicineFrame = createWindow("Restock Medicine");

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        JTextField doseField = new JTextField();
        JTextField quantityField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Dose (mg):"));
        inputPanel.add(doseField);

        inputPanel.add(new JLabel("Restock Quantity:"));
        inputPanel.add(quantityField);

        JButton confirmButton = createButton("Restock");
        confirmButton.addActionListener(e -> {
            String name = nameField.getText();
            int dose = Integer.parseInt(doseField.getText());
            int restockQuantity = Integer.parseInt(quantityField.getText());
            restockMedicine(name, dose, restockQuantity);
            restockMedicineFrame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(confirmButton);

        restockMedicineFrame.add(inputPanel, BorderLayout.CENTER);
        restockMedicineFrame.add(buttonPanel, BorderLayout.SOUTH);

        restockMedicineFrame.pack();
        restockMedicineFrame.setVisible(true);
        restockMedicineFrame.setLocationRelativeTo(null);
    }

    private void openDeleteMedicineWindow() {
        JFrame deleteMedicineFrame = createWindow("Delete Medicine");

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        JTextField doseField = new JTextField();

        inputPanel.add(createLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(createLabel("Dose (mg):"));
        inputPanel.add(doseField);

        JButton deleteButton = createButton("Delete");
        deleteButton.addActionListener(e -> {
            String name = nameField.getText();
            int dose = Integer.parseInt(doseField.getText());
            deleteMedicine(name, dose);
            deleteMedicineFrame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(deleteButton);

        deleteMedicineFrame.add(inputPanel, BorderLayout.CENTER);
        deleteMedicineFrame.add(buttonPanel, BorderLayout.SOUTH);

        deleteMedicineFrame.pack();
        deleteMedicineFrame.setVisible(true);
        deleteMedicineFrame.setLocationRelativeTo(null);
    }

    private JFrame createWindow(String title) {
        JFrame frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(204, 229, 255)); // Light blue background
        return frame;
    }

    private void addMedicine(String name, String composition, int dose, double price, int quantity, int minAge, boolean prescription) {//already filled
        try {
            // Check if a medicine with the same name and dose already exists
            String checkSql = "SELECT * FROM medicines WHERE name = ? AND dose = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setString(1, name);
            checkStatement.setInt(2, dose);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Medicine with the same name and dose already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if medicine already exists
            }

            // Add the new medicine
            String sql = "INSERT INTO medicines (name, composition, dose, price, quantity, minAge, prescription) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, composition);
            statement.setInt(3, dose);
            statement.setDouble(4, price);
            statement.setInt(5, quantity);
            statement.setInt(6, minAge);
            statement.setBoolean(7, prescription);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Medicine added successfully!");
        } catch (SQLException ex) {
            System.err.println("Error adding medicine: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error adding medicine: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changePrice(String name, int dose, double percentage) {
        try {
            String sql;
            PreparedStatement statement;

            if (percentage >= 0) {
                // Increase price
                sql = "UPDATE medicines SET price = price + (price * ? / 100) WHERE name = ? AND dose = ?";
                statement = connection.prepareStatement(sql);
            } else {
                // Decrease price
                sql = "UPDATE medicines SET price = price - (price * ? / 100) WHERE name = ? AND dose = ?";
                statement = connection.prepareStatement(sql);
                percentage = Math.abs(percentage); // Use positive value for calculation
            }

            statement.setDouble(1, percentage);
            statement.setString(2, name);
            statement.setInt(3, dose);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Price changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Medicine not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("Error changing price: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error changing price: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByName(String name) {
        try {
            String sql = "SELECT * FROM medicines WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("Name: ").append(resultSet.getString("name")).append("\n");
                result.append("Composition: ").append(resultSet.getString("composition")).append("\n");
                result.append("Dose: ").append(resultSet.getInt("dose")).append(" mg\n");
                result.append("Price: ").append(resultSet.getDouble("price")).append("\n");
                result.append("Quantity: ").append(resultSet.getInt("quantity")).append("\n");
                result.append("Minimum Age: ").append(resultSet.getInt("minAge")).append("\n");
                result.append("Prescription Required: ").append(resultSet.getBoolean("prescription") ? "Yes" : "No").append("\n\n");
            }

            if (result.length() == 0) {
                JOptionPane.showMessageDialog(this, "No medicine found with that name!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JTextArea textArea = new JTextArea(result.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(this, scrollPane, "Search Result", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("Error searching by name: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error searching by name: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByDose(int dose) {
        try {
            String sql = "SELECT * FROM medicines WHERE dose = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, dose);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("Name: ").append(resultSet.getString("name")).append("\n");
                result.append("Composition: ").append(resultSet.getString("composition")).append("\n");
                result.append("Dose: ").append(resultSet.getInt("dose")).append(" mg\n");
                result.append("Price: ").append(resultSet.getDouble("price")).append("\n");
                result.append("Quantity: ").append(resultSet.getInt("quantity")).append("\n");
                result.append("Minimum Age: ").append(resultSet.getInt("minAge")).append("\n");
                result.append("Prescription Required: ").append(resultSet.getBoolean("prescription") ? "Yes" : "No").append("\n\n");
            }

            if (result.length() == 0) {
                JOptionPane.showMessageDialog(this, "No medicine found with that dose!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JTextArea textArea = new JTextArea(result.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(this, scrollPane, "Search Result", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("Error searching by dose: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error searching by dose: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByComposition(String composition) {
        try {
            String sql = "SELECT * FROM medicines WHERE composition = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, composition);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("Name: ").append(resultSet.getString("name")).append("\n");
                result.append("Composition: ").append(resultSet.getString("composition")).append("\n");
                result.append("Dose: ").append(resultSet.getInt("dose")).append(" mg\n");
                result.append("Price: ").append(resultSet.getDouble("price")).append("\n");
                result.append("Quantity: ").append(resultSet.getInt("quantity")).append("\n");
                result.append("Minimum Age: ").append(resultSet.getInt("minAge")).append("\n");
                result.append("Prescription Required: ").append(resultSet.getBoolean("prescription") ? "Yes" : "No").append("\n\n");
            }

            if (result.length() == 0) {
                JOptionPane.showMessageDialog(this, "No medicine found with that composition!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JTextArea textArea = new JTextArea(result.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(this, scrollPane, "Search Result", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("Error searching by composition: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error searching by composition: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean hasValidPrescription(String name, int dose) {
        try {
            String sql = "SELECT prescription FROM medicines WHERE name = ? AND dose = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, dose);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("prescription");
            }
        } catch (SQLException ex) {
            System.err.println("Error checking prescription requirement: " + ex.getMessage());
        }
        return false; // Return false by default if an error occurs
    }

    private boolean requiresMinimumAge(String name, int dose) {
        try {
            String sql = "SELECT minAge FROM medicines WHERE name = ? AND dose = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, dose);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int minAge = resultSet.getInt("minAge");
                return minAge > 0; // If minAge is greater than 0, it requires an age
            }
        } catch (SQLException ex) {
            System.err.println("Error getting minimum age: " + ex.getMessage());
        }
        return false; // Return false by default if an error occurs or no minimum age is found
    }
    
    private int getMinimumAge(String name, int dose) {
    try {
        String minAgeSql = "SELECT minAge FROM medicines WHERE name = ? AND dose = ?";
        PreparedStatement minAgeStatement = connection.prepareStatement(minAgeSql);
        minAgeStatement.setString(1, name);
        minAgeStatement.setInt(2, dose);
        ResultSet minAgeResult = minAgeStatement.executeQuery();

        if (minAgeResult.next()) {
            return minAgeResult.getInt("minAge");
        }
    } catch (SQLException ex) {
        System.err.println("Error getting minimum age: " + ex.getMessage());
    }
    return 0; // Default value if no minimum age is found
}


   private void sellMedicine(String name, int dose, int quantity, int customerAge, boolean prescriptionRequired) {
    try {
        boolean medicineRequiresPrescription = hasValidPrescription(name, dose);
        boolean medicineRequiresAge = requiresMinimumAge(name, dose);

        // Check if the medicine requires a prescription and if one is provided
        if (medicineRequiresPrescription && !prescriptionRequired) {
            JOptionPane.showMessageDialog(this, "This medicine requires a prescription for selling!", "Prescription Required", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the customer age meets the minimum age requirement
        if (medicineRequiresAge && customerAge < getMinimumAge(name, dose)) {
            JOptionPane.showMessageDialog(this, "Minimum age requirement not met for selling this medicine!", "Minimum Age Required", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if sufficient quantity is available
        String checkSql = "SELECT quantity FROM medicines WHERE name = ? AND dose = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkSql);
        checkStatement.setString(1, name);
        checkStatement.setInt(2, dose);
        ResultSet resultSet = checkStatement.executeQuery();

        if (!resultSet.next()) {
            JOptionPane.showMessageDialog(this, "Medicine not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int availableQuantity = resultSet.getInt("quantity");
        if (quantity < 0) {
            JOptionPane.showMessageDialog(this, "Enter a positive number!", "Positive Number", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (quantity > availableQuantity) {
            JOptionPane.showMessageDialog(this, "Insufficient quantity available!", "Insufficient Quantity", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the quantity in the database
        String updateSql = "UPDATE medicines SET quantity = ? WHERE name = ? AND dose = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateSql);
        updateStatement.setInt(1, availableQuantity - quantity);
        updateStatement.setString(2, name);
        updateStatement.setInt(3, dose);
        updateStatement.executeUpdate();

        JOptionPane.showMessageDialog(this, "Medicine sold successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
        System.err.println("Error selling medicine: " + ex.getMessage());
        JOptionPane.showMessageDialog(this, "Error selling medicine: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void restockMedicine(String name, int dose, int restockQuantity) {
        try {
            // Check if the medicine exists
            String checkSql = "SELECT * FROM medicines WHERE name = ? AND dose = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setString(1, name);
            checkStatement.setInt(2, dose);
            ResultSet resultSet = checkStatement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Medicine not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (restockQuantity < 0) {
            JOptionPane.showMessageDialog(this, "Enter a positive number!", "Positive Number", JOptionPane.ERROR_MESSAGE);
            return;
        }

            // Update the quantity in the database
            int currentQuantity = resultSet.getInt("quantity");
            String updateSql = "UPDATE medicines SET quantity = ? WHERE name = ? AND dose = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setInt(1, currentQuantity + restockQuantity);
            updateStatement.setString(2, name);
            updateStatement.setInt(3, dose);
            updateStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Medicine restocked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.err.println("Error restocking medicine: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error restocking medicine: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteMedicine(String name, int dose) {
        try {
            String deleteSql = "DELETE FROM medicines WHERE name = ? AND dose = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setString(1, name);
            deleteStatement.setInt(2, dose);
            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Medicine deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Medicine not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("Error deleting medicine: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error deleting medicine: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exitProgram() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Program", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Close the database connection
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing database connection: " + ex.getMessage());
            }
            // Exit the program
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PharmacyGUI().setVisible(true));
    }
}