package course.microservices.property.data_access;

import course.microservices.property.entities.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Proporciona el acceso a datos para las propiedades.
 * */
public class PropertyGateway {

    private static PropertyGateway instance;

    private Connection connection;

    private PropertyGateway() {

    }

    public static PropertyGateway getInstance() {

        if(instance == null) {
            instance = new PropertyGateway();
            instance.setupDatabase();
        }
        return instance;
    }


    public List<Property> getAll() {

        List<Property> properties = new ArrayList<>();

        final String querySQL = "SELECT PropertyId, Name, Type, MaxGuests FROM Property";

        //Pedir a la base de datos la lista de propiedades registradas
        try(Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(querySQL)) {

            //Recorrer resultados
            while(results.next()) {

                properties.add(new Property(results.getInt("PropertyId"),
                        results.getString("Name"),
                        results.getInt("Type"),
                        results.getInt("MaxGuests")));

            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return properties;

    }

    private void setupDatabase() {

        try {
            //Parámetros de la base de datos en memoria.

            String url = "jdbc:sqlite::memory:";
            //Crear conexión a la base de datos
            connection = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

            // Crear tabla que guarda las propiedades.
            try(Statement statement = connection.createStatement()) {

                String createTableSQL = "CREATE TABLE Property (\n"
                        + " PropertyId integer PRIMARY KEY,\n"
                        + " Name text not null,\n"
                        + " Type integer not null,\n"
                        + " MaxGuests integer not null)";
                statement.execute(createTableSQL);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //Insertar datos de prueba
            insertProperty(1, "Apartamento de 3 habitaciones en el sur de la ciudad", 1, 5);
            insertProperty(2, "Habitación con cama doble", 2, 2);
            insertProperty(3, "Sofá cama en apartamento bonito", 3, 1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertProperty(int propertyId, String name, int type, int maxGuests) {

        final String insertSQL = "INSERT INTO Property(PropertyId, Name, Type, MaxGuests) VALUES(?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(insertSQL)) {

            statement.setInt(1, propertyId);
            statement.setString(2, name);
            statement.setInt(3, type);
            statement.setInt(4, maxGuests);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
