package course.layers.data_access.table_gateway;

import course.layers.entities.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyTableGateway {

    /* Inicio implementación del patrón de diseño singleton, para garantizar un solo objeto de la clase. */

    private static PropertyTableGateway instance;

    private PropertyTableGateway() {

    }

    public static PropertyTableGateway getInstance() {

        if(instance == null) {
            instance = new PropertyTableGateway();
            instance.setupDatabase();
        }
        return instance;
    }

    /* Fin implementación patrón singleton*/

    private Connection connection;

    /* Métodos para manejar las filas en la tabla Property */

    public List<Property> getAll() {

        List<Property> properties = new ArrayList<>();

        final String querySQL = "SELECT PropertyId, Name, Type, MaxGuests FROM Property";

        //Pedir a la base de datos la lista de propiedades registradas
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(querySQL)) {

            //Recorrer resultados
            while (results.next()) {

                properties.add(new Property(results.getInt("PropertyId"),
                        results.getString("Name"),
                        results.getInt("Type"),
                        results.getInt("MaxGuests")));

            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return properties;
    }

    public Property getById(int propertyId) {

        final String querySQL = "SELECT Name, Type, MaxGuests FROM Property WHERE PropertyId = ?";
        Property property = null;

        //Pedir a la base de datos la lista de propiedades registradas
        try (PreparedStatement statement = connection.prepareStatement(querySQL)) {

            statement.setInt(1, propertyId);
            try(ResultSet results = statement.executeQuery()) {
                //Obtener resultado
                if (results.next()) {

                    property = new Property(propertyId,
                            results.getString("Name"),
                            results.getInt("Type"),
                            results.getInt("MaxGuests"));

                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return property;
    }


    public void insert(int propertyId, String name, int type, int maxGuests) {

        final String insertSQL = "INSERT INTO Property(PropertyId, Name, Type, MaxGuests) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {

            statement.setInt(1, propertyId);
            statement.setString(2, name);
            statement.setInt(3, type);
            statement.setInt(4, maxGuests);
            statement.executeUpdate();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public void update(int propertyId, String name, int type, int maxGuests) {
        final String updateSQL = "UPDATE Property SET Name = ?, Type = ?, MaxGuests = ? WHERE PropertyId = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {

            statement.setString(1, name);
            statement.setInt(2, type);
            statement.setInt(3, maxGuests);
            statement.setInt(4, propertyId);
            statement.executeUpdate();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void delete(int propertyId) {
        final String deleteSQL = "DELETE FROM Property WHERE PropertyId = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {

            statement.setInt(1, propertyId);
            statement.executeUpdate();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }



    private void setupDatabase() {

        try {
            //Parámetros de la base de datos en memoria.

            String url = "jdbc:sqlite::memory:";
            //Crear conexión a la base de datos
            connection = DriverManager.getConnection(url);

            // Crear tabla que guarda las propiedades.
            try(Statement statement = connection.createStatement()) {

                String createTableSQL = "CREATE TABLE Property (\n"
                        + " PropertyId integer PRIMARY KEY,\n"
                        + " Name text not null,\n"
                        + " Type integer not null,\n"
                        + " MaxGuests integer not null)";
                statement.execute(createTableSQL);

            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


}
