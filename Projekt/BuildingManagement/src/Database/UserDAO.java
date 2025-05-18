package Database;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    // Authenticate user
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

    // Delete apartment
    public int deleteApartmentDB(int nr) {
        String sql = "DELETE FROM apartments WHERE nr = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, nr);
            pstmt.executeUpdate();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return e.getErrorCode();
        }
        return 0;
    }

    // Delete user
    public int deleteUserDB(String username) {
        String sqlNullForeignKey = "UPDATE apartments SET userid = NULL WHERE userid = (SELECT id FROM users WHERE username = ?)";
        String sqlDeleteUser = "DELETE FROM users WHERE username = ? AND role = 'user'";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt1 = con.prepareStatement(sqlNullForeignKey);
             PreparedStatement stmt2 = con.prepareStatement(sqlDeleteUser)) {
            stmt1.setString(1, username);
            stmt1.executeUpdate();

            stmt2.setString(1, username);
            stmt2.executeUpdate();
        } catch (SQLException e) {
            return e.getErrorCode();
        }
        return 0;
    }

    // Delete report
    public int deleteReportDB(int id) {
        String sql = "DELETE FROM reports WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getErrorCode();
        }
        return 0;
    }

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
    public ArrayList<String> apartmentsList() throws SQLException{
        String sql = "SELECT a.nr, u.id AS user_id, u.username " +
                "FROM apartments a " +
                "LEFT JOIN users u ON a.userid = u.id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            list.add("APARTMENTS:");
            int i = 0;
            while (rs.next()) {
                i++;
                String string;
                String userId = rs.getString("user_id");
                String username = rs.getString("username");
                if (userId == null && username == null) {
                    string = " [" + i + "] " + "Apartment nr: " + rs.getInt("nr") + " | Empty";
                }
                else {
                    string = " [" + i + "] " + "Apartment nr: " + rs.getInt("nr") +
                            " | User ID: " + userId +
                            " | User: " + username;
                }
                list.add(string);
            }
            return list;
        }
    }

    //view all users
    public ArrayList<String> usersList() throws SQLException{
        String sql = "SELECT * FROM users";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            list.add("USERS:");
            int i = 0;
            while (rs.next()) {
                i++;
                String username = rs.getString("username");
                String userId = rs.getString("id");
                String role = rs.getString("role");
                String pin = rs.getString("pin");
                String string = " [" + i + "] " + role + ": " + username + " | ID: " + userId + " | PIN: " + pin;
                list.add(string);
            }
            return list;
        }
    }

    public ArrayList<String> reportsList() throws SQLException {
        String sql = "SELECT * FROM reports";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            list.add("REPORTS:");
            while (rs.next()) {
                String reportId = rs.getString("id");
                Timestamp time = rs.getTimestamp("messagetime");
                String apartmentNr = rs.getString("apartmentnr");
                String username = rs.getString("username");
                String string = "{ " + reportId + " } <" + time + ">(Apartment: " + apartmentNr +
                         ") " + username + ": " + rs.getString("message");
                list.add(string);
            }
            return list;
        }
    }

    public ArrayList<String> getUser(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";

        ArrayList<String> userDetails = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userDetails.add(rs.getString("id"));
                userDetails.add(rs.getString("role"));
                userDetails.add(rs.getString("username"));
                userDetails.add(rs.getString("pin"));
            }
        }
        return userDetails;
    }

    public int getApartmentNumber(String username) throws SQLException {
        String sql = "SELECT nr FROM apartments WHERE userid = (SELECT id FROM users WHERE username = ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("nr");
            }
        }
        return -1;
    }

    public int getUserId(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            return e.getErrorCode();
        }
        return -1;
    }

    // Create report
    public int createReportDB(String message, String username, int apartmentNr, int userId) {
        String sql = "INSERT INTO reports (message, username, apartmentnr, userid) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, message);
            pstmt.setString(2, username);
            pstmt.setInt(3, apartmentNr);
            pstmt.setInt(4, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getErrorCode();
        }
        return 0;
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
}
