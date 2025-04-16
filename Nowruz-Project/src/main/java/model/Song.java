package model;

import java.io.*;
import java.util.*;

public class Song {
    private String title;
    private String artist;
    private String genre;
    private String lyrics;

    // Metadata
    private int likes;
    private int dislikes;
    private List<String> comments;

    public Song(String title, String artist, String genre, String lyrics) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.lyrics = lyrics;
        this.likes = 0;
        this.dislikes = 0;
        this.comments = new ArrayList<>();
    }

    public static Song fromLine(String line) {
        // Format: artist,title,genre,lyrics,likes,dislikes
        String[] parts = line.split(",", 6);
        if (parts.length < 4) return null;

        Song song = new Song(parts[1], parts[0], parts[2], parts[3]);
        if (parts.length >= 6) {
            song.likes = parseInt(parts[4]);
            song.dislikes = parseInt(parts[5]);
        }
        return song;
    }

    public String toLine() {
        return String.join(",", artist, title, genre, lyrics.replace(",", ";"), String.valueOf(likes), String.valueOf(dislikes));
    }

    public void display() {
        System.out.println("🎵 Title : " + title);
        System.out.println("🎙️ Artist: " + artist);
        System.out.println("🎼 Genre : " + genre);
        System.out.println("📜 Lyrics: " + lyrics);
        System.out.println("👍 Likes : " + likes + " | 👎 Dislikes: " + dislikes);
        if (!comments.isEmpty()) {
            System.out.println("💬 Comments:");
            comments.forEach(c -> System.out.println(" - " + c));
        }
        System.out.println("────────────────────────────");
    }

    private static int parseInt(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }

    // Getters
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getGenre() { return genre; }
    public String getLyrics() { return lyrics; }
}
