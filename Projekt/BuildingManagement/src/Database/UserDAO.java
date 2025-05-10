package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    //Uwierzytelnianie
    public boolean authenticateUser(String role, String pin, String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE role = ? AND pin = ? AND username = ?";

        try (Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, role);
            stmt.setString(2, pin);
            stmt.setString(3, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    //update door status
    public void updateDoorStatus(boolean status) throws SQLException {
        String sql = "UPDATE doorstatus SET isclosed = ? WHERE id = 1";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setBoolean(1, status);
            pstmt.executeUpdate();
            if (status) System.out.println("Building closed");
            else System.out.println("Building opened");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //view
    public void view(String username) throws SQLException{
        String sql = "SELECT balance FROM users WHERE username = ?";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                System.out.println("Saldo: " + rs.getDouble("balance") + " PLN");
            }
        }
    }

    //close building
    public boolean isClosed() throws SQLException{
        String sql = "SELECT * FROM doorstatus WHERE id = 1";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("isClosed");
            }
            return false;
        }
    }

    /*//add
    public void addUser(String firstName, String lastName, int age) {
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
    }

    public void withdraw(String username, double amount) throws SQLException {
        String checkSQL = "SELECT balance FROM users WHERE username = ?";
        String updateSQL = "UPDATE users SET balance = balance - ? WHERE username = ?";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement checkStmt = con.prepareStatement(checkSQL)){
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getDouble("balance") >= amount) {
                try(PreparedStatement updateStmt = con.prepareStatement(updateSQL)) {
                    updateStmt.setDouble(1, amount);
                    updateStmt.setString(2, username);
                    updateStmt.executeUpdate();
                    System.out.println("Wpłata zakończona sukcesem!");
                }
            }
            else System.out.println("Brak kaski");
        }

    }*/

}
