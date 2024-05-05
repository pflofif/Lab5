import RoomTypes.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

public class FileManager {
    private static final String FILE_PATH = "properties.json";
    private static final Gson gson = new GsonBuilder().registerTypeAdapterFactory(
            RuntimeTypeAdapterFactory
                    .of(Property.class, "type")
                    .registerSubtype(Residential.class, Residential.class.getName())
                    .registerSubtype(Commercial.class, Commercial.class.getName())
                    .registerSubtype(Cottage.class, Cottage.class.getName())
                    .registerSubtype(Industrial.class, Industrial.class.getName())
    ).create();

    public static void saveProperties(List<Property> properties) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(properties, writer);
        } catch (IOException e) {
            System.err.println("Error saving properties to JSON: " + e.getMessage());
        }
    }

    public static List<Property> loadProperties() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type propertyListType = new TypeToken<ArrayList<Property>>() {
            }.getType();
            List<Property> properties = gson.fromJson(reader, propertyListType);
            if (properties == null) {
                properties = new ArrayList<>();
            }
            return properties;
        } catch (IOException e) {
            System.err.println("Error loading properties from JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
