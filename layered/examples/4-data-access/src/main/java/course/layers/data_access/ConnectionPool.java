package course.layers.data_access;

import java.sql.Connection;

public interface ConnectionPool {

    /**
     * Retorna una conexión del pool.
     * */
    Connection getConnection();

    /**
     * Libera la conexión para que pueda ser utilizada por otro objeto en la capa de acceso a datos.
     * */
    boolean releaseConnection(Connection connection);

}
