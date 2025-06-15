private void onEnter(String pin) throws SQLException {
    // Check if admin
    if (userDAO.authenticateUser("admin", pin, username)) {
        new AdminPanel(username + "'s panel", 800, 600, true, true).run();
        this.dispose();
        System.out.println("PIN accepted");
    }
    // Check if user
    else if (userDAO.authenticateUser("user", pin, username)) {
        if (userDAO.isClosed()) {
            errorWindow("Building is closed!");
        }
        else if(!userDAO.userHasApartment(username)) {
            errorWindow("You don't have an apartment!");
        }
        else {
            new UserPanel("User panel", 700, 500, false, false, username).run();
            this.dispose();
            System.out.println("PIN accepted");
        }
    } else {
        errorWindow("Wrong PIN or username!");
    }
}