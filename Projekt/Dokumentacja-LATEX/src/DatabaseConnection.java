public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/building";
    private static final String USER = "app";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}