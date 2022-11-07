package course.layers.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Implementación basada en el ejemplo mostrado en https://www.baeldung.com/java-connection-pooling
 * */
public class SQLiteConnectionPool implements ConnectionPool{

    private List<Connection> connectionPool;
    private List<Connection> usedConnections;

    private static SQLiteConnectionPool instance;
    private static int INITIAL_POOL_SIZE = 1;

    private SQLiteConnectionPool(List<Connection> connections) {
        connectionPool = connections;
        usedConnections = new ArrayList<>();
    }

    public static SQLiteConnectionPool create() throws SQLException {

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for(int index = 0; index < INITIAL_POOL_SIZE; index++) {
            Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            pool.add(connection);
        }

        instance = new SQLiteConnectionPool(pool);
        /*Para efectos de este demo, vamos a crear la tabla desde aquí y facilitar la creación de datos.
        * En otros escenarios, la base de datos ya estaría creada para cuando el pool se inicializa.
        * */
        instance.setupDatabase();

        return instance;
    }

    private void setupDatabase() {

        Connection connection = getConnection();

        // Crear tabla que guarda las propiedades.
        try(java.sql.Statement statement = connection.createStatement()) {

            String createTableSQL = "CREATE TABLE Property (\n"
                    + " PropertyId integer PRIMARY KEY,\n"
                    + " Name text not null,\n"
                    + " Type integer not null,\n"
                    + " MaxGuests integer not null)";
            statement.execute(createTableSQL);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        releaseConnection(connection);
    }

    @Override
    public Connection getConnection() {

        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;

    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

}
