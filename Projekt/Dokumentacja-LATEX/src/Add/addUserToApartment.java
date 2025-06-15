private JButton addUserToApartmentBtn(JTextField username, JTextField apartment) {
    JButton addBtn = new JButton("ADD");
    addBtn.addActionListener(_ -> {
        String usernameValue = username.getText();
        String apartmentValue = apartment.getText();
        if (!CustomComponents.isNumber(apartmentValue)) {
            errorWindow("Apartment number must be a number!");
            return;
        }
        if (usernameValue.length() > 25) {
            errorWindow("Usernames must be less than 25 characters!");
            return;
        }
        if (usernameValue.isEmpty()) {
            errorWindow("Provide username!");
            return;
        }
        if (apartmentValue.isEmpty()) {
            errorWindow("Provide apartment number!");
            return;
        }
        try {
            if (!userDAO.apartmentExists(Integer.parseInt(apartmentValue))) {
                errorWindow("Apartment does not exist!");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        errorCode = userDAO.addUserToApartmentDB(usernameValue, apartmentValue);
        if (errorCode == 0) messageWindow("User: " + usernameValue + " added to apartment: " + apartmentValue);
        else errorWindow("Error number: " + errorCode);
    });
    return addBtn;
}