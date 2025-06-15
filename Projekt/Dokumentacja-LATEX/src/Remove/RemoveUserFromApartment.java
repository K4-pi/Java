private JButton emptyApartmentBtn(JTextField apartmentField) {
    JButton removeBtn = new JButton("REMOVE");
    removeBtn.addActionListener(_ -> {
        String apartment = apartmentField.getText();
        if (!CustomComponents.isNumber(apartment)) {
            errorWindow("Apartment number must be a number!");
            return;
        }
        if (apartment.isEmpty()) {
            errorWindow("Provide apartment number!");
            return;
        }
        errorCode = userDAO.deleteUserFromApartmentDB(Integer.parseInt(apartmentField.getText()));
        if (errorCode == 0) messageWindow("Users from apartment nr: " + apartment + " removed!");
        else errorWindow("Error: " + errorCode);
    });
    return removeBtn;
}