package ui;

import model.User;
import model.Artist;
import model.Admin;
import service.LoginService;

import java.util.Scanner;

public class LoginPanel {
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to V01Dify!");
        System.out.print("Enter your role (user / artist / admin): ");
        String role = scanner.nextLine().trim().toLowerCase();

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        User user = LoginService.login(role, username, password);

        if (user == null) {
            System.out.println("Login failed. Try again.");
            return;
        }

        System.out.println("Login successful. Welcome, " + user.getUsername() + "!");

        switch (role) {
            case "user" -> UserPanel.show((model.User) user);      // placeholder for now
            case "artist" -> ArtistPanel.show((model.Artist) user); // placeholder for now
            case "admin" -> AdminPanel.show((model.Admin) user);    // placeholder for now
        }
    }
}
