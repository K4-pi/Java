package Database;

import java.io.PrintWriter;
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

    //Send back
    public void Send(PrintWriter out) throws SQLException{
        String sql = "SELECT message FROM chatlog";
        StringBuilder buffer = new StringBuilder();

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String row = rs.getString("message");
                if (!row.isEmpty()) {
                    buffer.append(row).append("\n");
                }
            }
            buffer.append("<END>\n");
        }
        out.print(buffer);
        out.flush();
    }
}
