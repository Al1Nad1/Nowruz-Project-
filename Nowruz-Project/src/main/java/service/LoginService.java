package service;

import model.Admin;
import model.Artist;
import model.User;

import java.io.*;
import java.util.Scanner;

public class LoginService {

    public static User login(String role, String username, String password) {
        String filePath = switch (role.toLowerCase()) {
            case "user" -> "data/users.txt";
            case "artist" -> "data/artists.txt";
            case "admin" -> "data/admins.txt";
            default -> null;
        };

        if (filePath == null) {
            System.out.println("Invalid role selected.");
            return null;
        }

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return switch (role.toLowerCase()) {
                        case "user" -> new User(parts[0], parts[1]);
                        case "artist" -> new Artist(parts[0], parts[1], parts.length > 2 ? parts[2] : "unknown");
                        case "admin" -> new Admin(parts[0], parts[1]);
                        default -> null;
                    };
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }

        return null;
    }
}
