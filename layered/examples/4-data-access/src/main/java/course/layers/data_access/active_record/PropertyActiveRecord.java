package course.layers.data_access.active_record;

import course.layers.data_access.ConnectionPool;
import course.layers.data_access.SQLiteConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyActiveRecord {

    private int propertyId;
    private String name;
    private int type;
    private int maxGuests;

    public PropertyActiveRecord(int propertyId, String name, int type, int maxGuests) {
        this.propertyId = propertyId;
        this.name = name;
        this.type = type;
        this.maxGuests = maxGuests;
    }

    public boolean isBig() {
        return maxGuests > 5;
    }

    public static List<PropertyActiveRecord> getAll() {

        List<PropertyActiveRecord> properties = new ArrayList<>();

        final String querySQL = "SELECT PropertyId, Name, Type, MaxGuests FROM Property";

        Connection connection = ActiveRecordPool.getConnection();

        //Pedir a la base de datos la lista de propiedades registradas
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(querySQL)) {

            //Recorrer resultados
            while (results.next()) {

                properties.add(new PropertyActiveRecord(results.getInt("PropertyId"),
                        results.getString("Name"),
                        results.getInt("Type"),
                        results.getInt("MaxGuests")));

            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        ActiveRecordPool.releaseConnection(connection);

        return properties;
    }

    public static PropertyActiveRecord getById(int propertyId) {

        final String querySQL = "SELECT Name, Type, MaxGuests FROM Property WHERE PropertyId = ?";
        PropertyActiveRecord property = null;

        Connection connection = ActiveRecordPool.getConnection();

        //Pedir a la base de datos la lista de propiedades registradas
        try (PreparedStatement statement = connection.prepareStatement(querySQL)) {

            statement.setInt(1, propertyId);
            try(ResultSet results = statement.executeQuery()) {
                //Obtener resultado
                if (results.next()) {

                    property = new PropertyActiveRecord(propertyId,
                            results.getString("Name"),
                            results.getInt("Type"),
                            results.getInt("MaxGuests"));

                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        ActiveRecordPool.releaseConnection(connection);

        return property;
    }

    public void insert() {

        final String insertSQL = "INSERT INTO Property(PropertyId, Name, Type, MaxGuests) VALUES (?, ?, ?, ?)";

        Connection connection = ActiveRecordPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {

            statement.setInt(1, propertyId);
            statement.setString(2, name);
            statement.setInt(3, type);
            statement.setInt(4, maxGuests);
            statement.executeUpdate();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }

        ActiveRecordPool.releaseConnection(connection);
    }

    public void update() {

        final String updateSQL = "UPDATE Property SET Name = ?, Type = ?, MaxGuests = ? WHERE PropertyId = ?";

        Connection connection = ActiveRecordPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {

            statement.setString(1, name);
            statement.setInt(2, type);
            statement.setInt(3, maxGuests);
            statement.setInt(4, propertyId);
            statement.executeUpdate();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }

        ActiveRecordPool.releaseConnection(connection);

    }

    public void delete() {
        final String deleteSQL = "DELETE FROM Property WHERE PropertyId = ?";

        Connection connection = ActiveRecordPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {

            statement.setInt(1, propertyId);
            statement.executeUpdate();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }

        ActiveRecordPool.releaseConnection(connection);
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }
}
