package service;

import model.Song;

public class SongService {

    public static void likeSong(String title) {
        Song song = Song.loadFromFile(title);
        if (song == null) {
            System.out.println("❌ Song not found.");
            return;
        }
        song.addLike();
        System.out.println("👍 Liked!");
    }

    public static void dislikeSong(String title) {
        Song song = Song.loadFromFile(title);
        if (song == null) {
            System.out.println("❌ Song not found.");
            return;
        }
        song.addDislike();
        System.out.println("👎 Disliked!");
    }

    public static void commentOnSong(String title, String comment) {
        Song song = Song.loadFromFile(title);
        if (song == null) {
            System.out.println("❌ Song not found.");
            return;
        }
        song.addComment(comment);
        System.out.println("💬 Comment added.");
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
