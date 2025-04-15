package login;

import run.Input;

import java.util.HashMap;
import java.util.Map;

public class LoginManagment {
    Map<String, String> credentials  = new HashMap<>();

    private void InsertCredentials () {
        Input input = new Input();
        String username = "";
        String password = "";
        System.out.print("Username: ");
        username = input.StringInput();

        System.out.print("Password: ");
        password = input.StringInput();

        if (!username.isEmpty() && !password.isEmpty()) credentials.put(username, password);
        else System.out.println("Incorrect username or password");
    }

    private void LoginCredentials() {
        Input input = new Input();
        String username, password;

        System.out.println("Login: ");
        System.out.print("Username: ");
        username = input.StringInput();

        System.out.print("Password: ");
        password = input.StringInput();

        if (!username.isEmpty() && !password.isEmpty()) {
            if (credentials.containsKey(username)) {
                if (credentials.get(username).contains(password))
                    System.out.println("Logged in as: " + username + " : " + credentials.get(username));
                else
                    System.out.println("Wrong password");
            }
            else
                System.out.println("Wrong username");
        }
        else
            System.out.println("Incorrect username or password");
    }

    public void RunLogin() {
        System.out.println("1 - insert\n2 - login\n3 - exit");

        while (true) {
            Input input = new Input();
            System.out.print("Choose option: ");

            int choice = input.IntInput();
            switch (choice) {
                case 1:
                    InsertCredentials();
                    break;
                case 2:
                    LoginCredentials();
                    break;
                case 3:
                    return;
            }
        }
    }

}
