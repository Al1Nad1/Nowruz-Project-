package service;

import model.Song;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class SongService {

    private static final String LIKES_FOLDER = "data/likes/";

    public static void likeSong(String username, String title) {
        Song song = Song.loadFromFile(title);
        if (song == null) {
            System.out.println("❌ Song not found.");
            return;
        }

        // Make sure likes folder exists
        File dir = new File(LIKES_FOLDER);
        if (!dir.exists()) dir.mkdirs();

        File userLikes = new File(LIKES_FOLDER + username + ".txt");

        // Create if doesn't exist
        try {
            if (!userLikes.exists()) userLikes.createNewFile();

            List<String> likedSongs = new ArrayList<>(Files.readAllLines(userLikes.toPath()));
            if (likedSongs.contains(title)) {
                System.out.println("⚠️ You already liked this song.");
                return;
            }

            // Add song title to user's likes file
            likedSongs.add(title);
            Files.write(userLikes.toPath(), likedSongs);

            // Increment like in song file
            song.addLike();
            System.out.println("👍 Liked successfully!");

            song.displayFull();

        } catch (IOException e) {
            System.out.println("❌ Error handling user like.");
        }
    }

    public static void dislikeSong(String title) {
        Song song = Song.loadFromFile(title);
        if (song == null) {
            System.out.println("❌ Song not found.");
            return;
        }

        song.addDislike();
        System.out.println("👎 Disliked!");
        song.displayFull();
    }

    public static void commentOnSong(String title, String comment) {
        Song song = Song.loadFromFile(title);
        if (song == null) {
            System.out.println("❌ Song not found.");
            return;
        }

        song.addComment(comment);
        System.out.println("💬 Comment added!");
        song.displayFull();
    }

    public static void viewSongDetails(String title) {
        Song song = Song.loadFromFile(title);
        if (song == null) {
            System.out.println("❌ Song not found.");
            return;
        }

        song.displayFull();
    }
}
