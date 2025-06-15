private JButton removeUserBtn(JTextField usernameField) {
    JButton removeBtn = new JButton("REMOVE");
    removeBtn.addActionListener(_ -> {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            errorWindow("Provide username!");
            return;
        }
        if (username.equals("admin")) {
            errorWindow("You can't remove account 'admin'\nit is permanent account!");
            return;
        }
        errorCode = userDAO.deleteUserDB(username);
        if (errorCode == 0) messageWindow("User: " + username + " removed!");
        else errorWindow("Error: " + errorCode);
    });
    return removeBtn;
}