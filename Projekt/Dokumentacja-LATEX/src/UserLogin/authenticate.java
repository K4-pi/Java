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