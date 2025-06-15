private JButton addUserBtn(JTextField username, JTextField pin, JComboBox role) {
    JButton userBtn = new JButton("ADD");
    userBtn.addActionListener(_ -> {
        String pinValue = pin.getText();
        String usernameValue = username.getText();
        String roleValue = Objects.requireNonNull(role.getSelectedItem()).toString();
        if (usernameValue.isEmpty()) {
            errorWindow("Provide username!");
            return;
        }
        if (usernameValue.length() > 25) {
            errorWindow("Usernames must be less than 25 characters!");
            return;
        }
        if (pinValue.isEmpty()) {
            errorWindow("Provide PIN!");
            return;
        }
        if (pinValue.length() > 4) {
            errorWindow("PIN must be 4 digits!");
            return;
        }
        if (!CustomComponents.isNumber(pinValue)) {
            errorWindow("PIN must be a number!");
            return;
        }
        errorCode = userDAO.addUserToDB(usernameValue, pinValue, roleValue);
        if (errorCode == 0) messageWindow("User: " + usernameValue + " added");
        else if (errorCode == 1062) errorWindow("User already exists!");
        else errorWindow("Error number: " + errorCode);
    });
    return userBtn;
}