package sample;
import java.sql.*;

public class DbConnection {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "memento56";
    private static final String CONN = "jdbc:postgresql://localhost:5432/perpustakaan";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
    }

    public static Statement getStatement() throws SQLException{
        return getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

}
