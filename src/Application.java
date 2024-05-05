import RoomTypes.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Application extends JFrame {
    private final PropertyManager propertyManager;
    private JTable table;
    private DefaultTableModel tableModel;

    public Application() {
        propertyManager = new PropertyManager();
        initializeUI();
        loadProperties();
    }


    private void initializeUI() {
        setTitle("Real Estate Rental System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Table Model
        String[] columnNames = {"ID", "Address", "Size", "Rental Price", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        addButton.addActionListener(this::addProperty);
        editButton.addActionListener(this::editProperty);
        deleteButton.addActionListener(this::deleteProperty);
    }

    private void loadProperties() {
        List<Property> properties = propertyManager.getProperties();
        properties.forEach(this::addPropertyToTable);
    }

    private void addProperty(ActionEvent e) {
        // Property type selection
        String[] propertyTypes = {"Residential", "Commercial", "Cottage", "Industrial"};
        JComboBox<String> typeComboBox = new JComboBox<>(propertyTypes);
        JOptionPane.showMessageDialog(null, typeComboBox, "Select Property Type", JOptionPane.QUESTION_MESSAGE);
        String selectedType = (String) typeComboBox.getSelectedItem();

        // Common property data
        String id = JOptionPane.showInputDialog("Enter ID:");
        String address = JOptionPane.showInputDialog("Enter Address:");
        double size = Double.parseDouble(JOptionPane.showInputDialog("Enter Size:"));
        double rentalPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter Rental Price:"));

        Property property = null;
        switch (selectedType) {
            case "Residential":
                int numBedrooms = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Bedrooms:"));
                boolean hasParking = JOptionPane.showConfirmDialog(null, "Does it have parking?", "Parking Availability", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                property = new Residential(id, address, size, rentalPrice, numBedrooms, hasParking);
                break;
            case "Commercial":
                String businessType = JOptionPane.showInputDialog("Enter Business Type:");
                boolean utilitiesIncluded = JOptionPane.showConfirmDialog(null, "Are utilities included?", "Utilities", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                property = new Commercial(id, address, size, rentalPrice, businessType, utilitiesIncluded);
                break;
            case "Cottage":
                double landSize = Double.parseDouble(JOptionPane.showInputDialog("Enter Land Size:"));
                boolean isFurnished = JOptionPane.showConfirmDialog(null, "Is it furnished?", "Furniture", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                property = new Cottage(id, address, size, rentalPrice, landSize, isFurnished);
                break;
            case "Industrial":
                double maxLoadCapacity = Double.parseDouble(JOptionPane.showInputDialog("Enter Max Load Capacity:"));
                String sectorType = JOptionPane.showInputDialog("Enter Sector Type:");
                property = new Industrial(id, address, size, rentalPrice, maxLoadCapacity, sectorType);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid property type selected", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        propertyManager.addProperty(property);
        addPropertyToTable(property);
    }


    private void editProperty(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            String type = (String) tableModel.getValueAt(selectedRow, 4); // Assuming type is stored in the fifth column

            String newAddress = JOptionPane.showInputDialog("Enter new Address:", tableModel.getValueAt(selectedRow, 1));
            double newSize = Double.parseDouble(JOptionPane.showInputDialog("Enter new Size:", tableModel.getValueAt(selectedRow, 2).toString()));
            double newRentalPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new Rental Price:", tableModel.getValueAt(selectedRow, 3).toString()));

            Property updatedProperty = null;

            switch (type) {
                case "Residential":
                    Residential currentResidential = (Residential) propertyManager.getPropertyById(id);
                    int newNumBedrooms = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Bedrooms:", currentResidential.getNumBedrooms()));
                    boolean newHasParking = JOptionPane.showConfirmDialog(null, "Does it have parking?", "Parking Availability", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    updatedProperty = new Residential(id, newAddress, newSize, newRentalPrice, newNumBedrooms, newHasParking);
                    break;
                case "Commercial":
                    Commercial currentCommercial = (Commercial) propertyManager.getPropertyById(id);
                    String newBusinessType = JOptionPane.showInputDialog("Enter Business Type:", currentCommercial.getBusinessType());
                    boolean newUtilitiesIncluded = JOptionPane.showConfirmDialog(null, "Are utilities included?", "Utilities", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    updatedProperty = new Commercial(id, newAddress, newSize, newRentalPrice, newBusinessType, newUtilitiesIncluded);
                    break;
                case "Cottage":
                    Cottage currentCottage = (Cottage) propertyManager.getPropertyById(id);
                    double newLandSize = Double.parseDouble(JOptionPane.showInputDialog("Enter Land Size:", currentCottage.getLandSize()));
                    boolean newIsFurnished = JOptionPane.showConfirmDialog(null, "Is it furnished?", "Furniture", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    updatedProperty = new Cottage(id, newAddress, newSize, newRentalPrice, newLandSize, newIsFurnished);
                    break;
                case "Industrial":
                    Industrial currentIndustrial = (Industrial) propertyManager.getPropertyById(id);
                    double newMaxLoadCapacity = Double.parseDouble(JOptionPane.showInputDialog("Enter Max Load Capacity:", currentIndustrial.getMaxLoadCapacity()));
                    String newSectorType = JOptionPane.showInputDialog("Enter Sector Type:", currentIndustrial.getSectorType());
                    updatedProperty = new Industrial(id, newAddress, newSize, newRentalPrice, newMaxLoadCapacity, newSectorType);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid property type selected", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            propertyManager.editProperty(id, updatedProperty);
            updateTableModel(selectedRow, updatedProperty);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a property to edit.", "No Property Selected", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTableModel(int row, Property property) {
        tableModel.setValueAt(property.getAddress(), row, 1);
        tableModel.setValueAt(property.getSize(), row, 2);
        tableModel.setValueAt(property.getRentalPrice(), row, 3);
        tableModel.setValueAt(property.getClass().getSimpleName(), row, 4);
    }


    private void deleteProperty(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String id = tableModel.getValueAt(selectedRow, 0).toString();
            propertyManager.removeProperty(id);
            tableModel.removeRow(selectedRow);
        }
    }

    private void addPropertyToTable(Property property) {
        Object[] row = new Object[] {
                property.getId(),
                property.getAddress(),
                property.getSize(),
                property.getRentalPrice(),
                property.getClass().getSimpleName() // Display the type of property
        };
        tableModel.addRow(row);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Application().setVisible(true);
        });
    }
}
