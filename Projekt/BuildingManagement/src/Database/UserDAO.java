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
    public void updateDoubleValue(String column, double value, int apartmentNr) throws SQLException {
        String sql = "UPDATE apartments SET " + column + " = " + column + " + ? WHERE nr = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, value);
            pstmt.setInt(2, apartmentNr);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update boolean value
    public void updateBooleanValue(String column, boolean value, int apartmentNr) throws SQLException {
        String sql = "UPDATE apartments SET " + column + " = " + value + " WHERE nr = ?";

        try (Connection con = DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, apartmentNr);
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
    public ArrayList<String> apartmentsOwners() throws SQLException{
        String sql = "SELECT a.nr, u.id AS user_id, u.username " +
                "FROM apartments a " +
                "LEFT JOIN users u ON a.userid = u.id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                i++;
                String userId = rs.getString("user_id") != null ? rs.getString("user_id") : "null";
                String username = rs.getString("username") != null ? rs.getString("username") : "null";
                String string = " [" + i + "] " + "Apartment nr: " + rs.getInt("nr") +
                        " | User ID: " + userId +
                        " | User: " + username;
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

    //add apartment
    public int addApartmentToDB(String apartmentNumber) {
        String sql = "INSERT INTO apartments (nr) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(apartmentNumber));
            pstmt.executeUpdate();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return e.getErrorCode();
        }
        return 0;
    }

    //add user
    public int addUserToDB(String username, String pin, String role) {
        String sql = "INSERT INTO users (username, pin, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, pin);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getErrorCode();
        }
        return 0;
    }

    //add user to apartment
    public int addUserToApartmentDB(String username, String apartmentNumber) {
        String sql = "UPDATE apartments SET userid = (SELECT id FROM users WHERE username = ?) WHERE nr = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, Integer.parseInt(apartmentNumber));
            pstmt.executeUpdate();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return e.getErrorCode();
        }
        return 0;
    }

//    public void withdraw(String username, double amount) throws SQLException {
//        String checkSQL = "SELECT balance FROM users WHERE username = ?";
//        String updateSQL = "UPDATE users SET balance = balance - ? WHERE username = ?";
//
//        try(Connection con = DatabaseConnection.getConnection();
//            PreparedStatement checkStmt = con.prepareStatement(checkSQL)){
//            checkStmt.setString(1, username);
//            ResultSet rs = checkStmt.executeQuery();
//            if (rs.next() && rs.getDouble("balance") >= amount) {
//                try(PreparedStatement updateStmt = con.prepareStatement(updateSQL)) {
//                    updateStmt.setDouble(1, amount);
//                    updateStmt.setString(2, username);
//                    updateStmt.executeUpdate();
//                    System.out.println("Wpłata zakończona sukcesem!");
//                }
//            }
//            else System.out.println("Brak kaski");
//        }
//
//    }

}
