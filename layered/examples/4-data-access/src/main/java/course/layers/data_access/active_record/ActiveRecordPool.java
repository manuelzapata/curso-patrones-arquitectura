package course.layers.data_access.active_record;

import course.layers.data_access.ConnectionPool;
import course.layers.data_access.SQLiteConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

class ActiveRecordPool {

    private static ConnectionPool pool;

    static {
        try {
            pool = SQLiteConnectionPool.create();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    static void releaseConnection(Connection connection) {
        pool.releaseConnection(connection);
    }

    static Connection getConnection(){
        return pool.getConnection();
    }

}
