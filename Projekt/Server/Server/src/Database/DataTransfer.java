package Database;

import java.sql.*;

public class DataTransfer {

    //Uwierzytelnianie
/*    public boolean autheticateUser(String username, String pin, String role) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND pin = ? AND role = ?";

        try (Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, pin);
            stmt.setString(3, role);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }*/

    //add message
    public void addMessage(String username, String message) {
        String sql = "INSERT INTO chatlog (username, message) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setTimestamp(1, senttime);
            pstmt.setString(1, username);
            pstmt.setString(2, message);
            pstmt.executeUpdate();
            System.out.println("Wiadomość dodana do bazy danych!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //add user
/*    public void addUser(String firstName, String lastName, int age) {
        String sql = "INSERT INTO user (username, pin, balance, role) VALUES (?, ?, 0, 'user')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();
            System.out.println("Użytkownik dodany!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    //view
 /*   public void view(String username) throws SQLException{
        String sql = "SELECT balance FROM users WHERE username = ?";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                System.out.println("Saldo: " + rs.getDouble("balance") + " PLN");
            }
        }
    }*/

}
