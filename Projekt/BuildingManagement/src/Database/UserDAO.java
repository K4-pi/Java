package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            throw new RuntimeException(e);
        }
    }

    //view list of taken apartments by users
    /*public ArrayList<Integer> apartmentsList() throws SQLException{
        String sql = "SELECT nr FROM apartments WHERE role = 'user'";
        ArrayList<Integer> list = new ArrayList<>();
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                list.add(rs.getInt("nr"));
            }
        }
        return list;
    }*/

    // Update double value
    public void updateDoubleValue(String column, double value) throws SQLException {
        String sql = "UPDATE apartments SET " + column + " = " + column + " + ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update boolean value
    public void updateBooleanValue(String column, boolean value) throws SQLException {
        String sql = "UPDATE apartments SET " + column + " = " + value;

        try (Connection con = DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //View one apartment info
    public ArrayList<Object> viewApartment(int apartmentNr) throws SQLException{
        String sql = "SELECT * FROM apartments WHERE nr = ?";
//                "JOIN users u ON a.userid = u.id " +
//                "WHERE u.role = 'user'";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, apartmentNr);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Object> list = new ArrayList<>();
            if (rs.next()) {
                list.add(rs.getInt("nr"));
                list.add(rs.getBoolean("electricity"));
                list.add(rs.getBoolean("light"));
                list.add(rs.getDouble("airtemp"));
                list.add(rs.getDouble("watertemp"));
            }
            return list;
        }
    }

    //view all apartments info
    public ArrayList<String> apartmentsInfo() throws SQLException{
        String sql = "SELECT * FROM apartments a " +
                "JOIN users u ON a.userid = u.id " +
                "WHERE u.role = 'user'";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                i++;
                String string = "[" + i + "] " + "Apartment nr: " + rs.getInt("nr") +
                                " |🔌 " + rs.getBoolean("electricity") +
                                " |💡 " + rs.getBoolean("light") +
                                " |💨️🌡️ " + rs.getDouble("airtemp") +
                                " |💦🌡️ " + rs.getDouble("watertemp");
                list.add(string);
            }
            return list;
        }
    }

    //view user id
    public int userId(String username) throws SQLException{
        String sql = "SELECT id FROM users WHERE username = ?";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
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
            throw new RuntimeException(e);
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
