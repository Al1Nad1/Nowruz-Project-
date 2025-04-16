package service;

import model.User;

import java.io.*;

public class UserService {

    public static void likeSong(User user, String songTitle) {
        saveLikeDislike(user.getUsername(), songTitle, "like");
    }

    public static void dislikeSong(User user, String songTitle) {
        saveLikeDislike(user.getUsername(), songTitle, "dislike");
    }

    private static void saveLikeDislike(String username, String songTitle, String action) {
        File likesDir = new File("data/likes/");
        if (!likesDir.exists()) likesDir.mkdirs();

        File userFile = new File(likesDir, username + ".txt");

        try (FileWriter writer = new FileWriter(userFile, true)) {
            writer.write(action + ":" + songTitle + "\n");
            System.out.println("✅ " + action + " recorded.");
        } catch (IOException e) {
            System.out.println("Error recording interaction.");
        }
    }

    public static void followArtist(User user, String artistName) {
        try (FileWriter writer = new FileWriter("data/following_" + user.getUsername() + ".txt", true)) {
            writer.write(artistName + "\n");
            System.out.println("✅ Followed " + artistName);
        } catch (IOException e) {
            System.out.println("Error following artist.");
        }
    }

    public static void viewFollowing(User user) {
        File file = new File("data/following_" + user.getUsername() + ".txt");

        if (!file.exists()) {
            System.out.println("You are not following anyone yet.");
            return;
        }

        System.out.println("📋 Following list:");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("🎤 " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading following list.");
        }
    }
}
