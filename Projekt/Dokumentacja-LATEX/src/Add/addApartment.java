private JButton addApartmentBtn(JTextField nr) {
    JButton addBtn = new JButton("ADD");
    addBtn.addActionListener(_ -> {
        System.out.println("PIN: " + nr.getText());
        String apartmentNr = nr.getText();
        if (!CustomComponents.isNumber(apartmentNr)) {
            errorWindow("Apartment number must be a number!");
            return;
        }
        if (apartmentNr.isEmpty()) {
            errorWindow("Provide apartment number!");
            return;
        }
        errorCode = userDAO.addApartmentToDB(apartmentNr);
        if (errorCode == 0) messageWindow("Apartment " + nr.getText() + " added");
        else if (errorCode == 1062) errorWindow("Apartment already exists!");
        else errorWindow("Error number: " + errorCode);
    });
    return addBtn;
}