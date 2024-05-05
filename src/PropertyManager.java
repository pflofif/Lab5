import RoomTypes.Property;

import java.util.List;

public class PropertyManager {
    private final List<Property> properties;

    public PropertyManager() {
        this.properties = FileManager.loadProperties();
    }

    public List<Property> getProperties() {
        return properties;
    }

    public Property getPropertyById(String id) {
        for (Property property : properties) {
            if (property.getId().equals(id)) {
                return property;
            }
        }
        return null;
    }

    public void addProperty(Property property) {
        properties.add(property);
        FileManager.saveProperties(properties);
    }

    public void removeProperty(String id) {
        properties.removeIf(p -> p.getId().equals(id));
        FileManager.saveProperties(properties);
    }

    public void editProperty(String id, Property updatedProperty) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getId().equals(id)) {
                properties.set(i, updatedProperty);
                FileManager.saveProperties(properties);
                break;
            }
        }
    }
}
