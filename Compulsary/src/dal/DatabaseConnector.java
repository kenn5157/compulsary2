package dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private SQLServerDataSource dataSource;

    public DatabaseConnector(){
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("wolf11_Compulsary");
        dataSource.setUser("CSe20B_11");
        dataSource.setPassword("bwa69rgq");
        dataSource.setPortNumber(1433);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException {

        DatabaseConnector databaseConnector = new DatabaseConnector();
        try (Connection connection = databaseConnector.getConnection()){
            System.out.println("Is it open? " + !connection.isClosed());

        }//Closes connection

    }

}
