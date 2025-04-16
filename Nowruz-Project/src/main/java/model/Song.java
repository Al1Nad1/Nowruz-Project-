package model;

import java.io.*;
import java.util.*;

public class Song {
    private String title;
    private String artist;
    private String genre;
    private String lyrics;
    private int likes;
    private int dislikes;
    private List<String> comments = new ArrayList<>();

    public Song(String title, String artist, String genre, String lyrics) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.lyrics = lyrics;
        this.likes = 0;
        this.dislikes = 0;
    }

    public static Song loadFromFile(String title) {
        File file = new File("data/songs/" + title.replaceAll(" ", "_") + ".txt");
        if (!file.exists()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String songTitle = "", artist = "", genre = "", lyrics = "";
            int likes = 0, dislikes = 0;
            List<String> comments = new ArrayList<>();

            boolean inLyrics = false, inComments = false;
            StringBuilder lyricsBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Title: ")) songTitle = line.substring(7).trim();
                else if (line.startsWith("Artist: ")) artist = line.substring(8).trim();
                else if (line.startsWith("Genre: ")) genre = line.substring(7).trim();
                else if (line.equals("Lyrics:")) inLyrics = true;
                else if (line.startsWith("Likes: ")) likes = Integer.parseInt(line.substring(7).trim());
                else if (line.startsWith("Dislikes: ")) dislikes = Integer.parseInt(line.substring(10).trim());
                else if (line.equals("Comments:")) {
                    inLyrics = false;
                    inComments = true;
                } else if (inLyrics) {
                    lyricsBuilder.append(line).append("\n");
                } else if (inComments && line.startsWith("- ")) {
                    comments.add(line.substring(2).trim());
                }
            }

            Song song = new Song(songTitle, artist, genre, lyricsBuilder.toString().trim());
            song.likes = likes;
            song.dislikes = dislikes;
            song.comments = comments;
            return song;

        } catch (IOException e) {
            return null;
        }
    }

    public void saveToFile() {
        File dir = new File("data/songs/");
        if (!dir.exists()) dir.mkdirs();

        File file = new File(dir, title.replaceAll(" ", "_") + ".txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("Title: " + title);
            writer.println("Artist: " + artist);
            writer.println("Genre: " + genre);
            writer.println("Lyrics:");
            writer.println(lyrics);
            writer.println();
            writer.println("Likes: " + likes);
            writer.println("Dislikes: " + dislikes);
            writer.println();
            writer.println("Comments:");
            for (String c : comments) {
                writer.println("- " + c);
            }
            writer.println("──────────────────────");
        } catch (IOException e) {
            System.out.println("❌ Error saving song file.");
        }
    }

    public void addLike() {
        likes++;
        saveToFile();
    }

    public void addDislike() {
        dislikes++;
        saveToFile();
    }

    public void addComment(String comment) {
        comments.add(comment);
        saveToFile();
    }

    public void displayFull() {
        System.out.println("🎵 Title: " + title);
        System.out.println("🎙️ Artist: " + artist);
        System.out.println("🎼 Genre: " + genre);
        System.out.println("📜 Lyrics:\n" + lyrics);
        System.out.println("👍 Likes: " + likes + " | 👎 Dislikes: " + dislikes);
        if (!comments.isEmpty()) {
            System.out.println("💬 Comments:");
            for (String c : comments) System.out.println(" - " + c);
        }
        System.out.println("──────────────────────");
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getGenre() { return genre; }
}
