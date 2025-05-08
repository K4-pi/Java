package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    //Uwierzytelnianie
    public boolean autheticateUser(String role, String pin) throws SQLException {
        String sql = "SELECT * FROM users WHERE role = ? AND pin = ?";

        try (Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, role);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    //add
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

    //saldo konta
    public void deposit(String username, double amount) throws SQLException {
        String sql = "UPDATE users SET balance = balance + ? WHERE username = ?";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setDouble(1, amount);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            System.out.println("Wpłata zakończona");
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

    public void changePin(String username, String newPin) throws SQLException {
        String sql = "UPDATE users SET pin = ? WHERE username = ?";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newPin);
            stmt.setString(2, username);
            stmt.executeUpdate();
            System.out.println("PIN zmieniony");
        }
    }

}
