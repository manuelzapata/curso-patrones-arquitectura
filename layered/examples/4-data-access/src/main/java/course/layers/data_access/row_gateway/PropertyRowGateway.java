package course.layers.data_access.row_gateway;

import java.sql.*;

public class PropertyRowGateway {

    private int propertyId;
    private String name;
    private int type;
    private int maxGuests;

    public PropertyRowGateway(int propertyId, String name, int type, int maxGuests) {
        this.propertyId = propertyId;
        this.name = name;
        this.type = type;
        this.maxGuests = maxGuests;
    }

    public void insert() {

        final String insertSQL = "INSERT INTO Property(PropertyId, Name, Type, MaxGuests) VALUES (?, ?, ?, ?)";

        Connection connection = RowGatewayPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {

            statement.setInt(1, propertyId);
            statement.setString(2, name);
            statement.setInt(3, type);
            statement.setInt(4, maxGuests);
            statement.executeUpdate();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }

        RowGatewayPool.releaseConnection(connection);
    }

    public void update() {

        final String updateSQL = "UPDATE Property SET Name = ?, Type = ?, MaxGuests = ? WHERE PropertyId = ?";

        Connection connection = RowGatewayPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {

            statement.setString(1, name);
            statement.setInt(2, type);
            statement.setInt(3, maxGuests);
            statement.setInt(4, propertyId);
            statement.executeUpdate();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }

        RowGatewayPool.releaseConnection(connection);

    }

    public void delete() {
        final String deleteSQL = "DELETE FROM Property WHERE PropertyId = ?";

        Connection connection = RowGatewayPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {

            statement.setInt(1, propertyId);
            statement.executeUpdate();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }

        RowGatewayPool.releaseConnection(connection);
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
