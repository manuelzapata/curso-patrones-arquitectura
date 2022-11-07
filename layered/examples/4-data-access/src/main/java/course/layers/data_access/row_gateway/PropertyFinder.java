package course.layers.data_access.row_gateway;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyFinder {

    public List<PropertyRowGateway> getAll() {

        List<PropertyRowGateway> properties = new ArrayList<>();

        final String querySQL = "SELECT PropertyId, Name, Type, MaxGuests FROM Property";

        Connection connection = RowGatewayPool.getConnection();

        //Pedir a la base de datos la lista de propiedades registradas
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(querySQL)) {

            //Recorrer resultados
            while (results.next()) {

                properties.add(new PropertyRowGateway(results.getInt("PropertyId"),
                        results.getString("Name"),
                        results.getInt("Type"),
                        results.getInt("MaxGuests")));

            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        RowGatewayPool.releaseConnection(connection);

        return properties;
    }

    public PropertyRowGateway getById(int propertyId) {

        final String querySQL = "SELECT Name, Type, MaxGuests FROM Property WHERE PropertyId = ?";
        PropertyRowGateway property = null;

        Connection connection = RowGatewayPool.getConnection();

        //Pedir a la base de datos la lista de propiedades registradas
        try (PreparedStatement statement = connection.prepareStatement(querySQL)) {

            statement.setInt(1, propertyId);
            try(ResultSet results = statement.executeQuery()) {
                //Obtener resultado
                if (results.next()) {

                    property = new PropertyRowGateway(propertyId,
                            results.getString("Name"),
                            results.getInt("Type"),
                            results.getInt("MaxGuests"));

                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        RowGatewayPool.releaseConnection(connection);

        return property;
    }

}
