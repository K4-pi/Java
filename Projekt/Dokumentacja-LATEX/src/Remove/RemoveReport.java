private JButton removeReportBtn(JTextField idField) {
    JButton removeBtn = new JButton("REMOVE");
    removeBtn.addActionListener(_ -> {
        String id = idField.getText();
        if (id.isEmpty()) {
            errorWindow("Provide report ID!");
            return;
        }
        if (!CustomComponents.isNumber(id)) {
            errorWindow("ID must be a number!");
            return;
        }
        errorCode = userDAO.deleteReportDB(Integer.parseInt(id));
        if (errorCode == 0) messageWindow("Report with ID: " + id + " removed!");
        else errorWindow("Error: " + errorCode);
    });
    return removeBtn;
}