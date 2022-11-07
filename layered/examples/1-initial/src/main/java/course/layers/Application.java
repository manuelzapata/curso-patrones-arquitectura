package course.layers;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class Application {

    private static Connection connection;

    public static void main(String[] args) {

        setupDatabase();

        int option;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Operaciones del sistema");


        do {

            System.out.println("1. Listar propiedades.");
            System.out.println("2. Ver si es posible cancelar reserva.");
            System.out.println("3. Salir.");

            option = scanner.nextInt();

            switch (option) {

                case 1: //Listar propiedades.

                    final String querySQL = "SELECT PropertyId, Name, Type, MaxGuests FROM Property";

                    //Pedir a la base de datos la lista de propiedades registradas
                    try(Statement statement = connection.createStatement();
                        ResultSet results = statement.executeQuery(querySQL)) {

                        final String format = "Nombre: %s, Tipo: %s, Huéspedes: %d\n";

                        //Recorrer resultados
                        while(results.next()) {

                            String typeAsString = "";
                            switch(results.getInt("Type")) {
                                case 1:
                                    typeAsString = "Apartamento";
                                    break;
                                case 2:
                                    typeAsString = "Habitación";
                                    break;
                                case 3:
                                    typeAsString = "Cama";
                                    break;
                            }

                            System.out.format(format, results.getString("Name"),
                                                        typeAsString,
                                                        results.getInt("MaxGuests"));
                        }
                        System.out.println();

                    } catch (SQLException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 2: //Ver si es posible cancelar reserva.
                    System.out.println("¿Cuántos días faltan para el inicio de la reserva?");
                    int days = scanner.nextInt();

                    System.out.println("¿Qué tipo de propiedad es? (1 = Apartamento, 2 = Habitación, 3 = Cama)");
                    int propertyType = scanner.nextInt();

                    boolean cancel = true;
                    if(days < 3) {
                        cancel = false;
                    } else {
                        //La reserva no se puede cancelar si es un apartamento.
                        if(propertyType == 1) {
                            cancel = false;
                        }
                    }

                    if(cancel) {
                        System.out.println("Se puede cancelar la reserva\n");
                    } else {
                        System.out.println("No se puede cancelar la reserva\n");
                    }
                    break;

            }

        } while (option != 3);

        closeConnection();
        System.out.println("Aplicación terminada");

    }

    private static void setupDatabase() {

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

    private static void insertProperty(int propertyId, String name, int type, int maxGuests) {

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

    private static void closeConnection(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}