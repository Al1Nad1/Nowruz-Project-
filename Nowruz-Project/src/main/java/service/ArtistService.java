package service;

import model.Artist;
import model.Song;

import java.io.*;

public class ArtistService {

    private static final String SONGS_FILE = "data/songs.txt";
    private static final String EDIT_REQUESTS_FILE = "data/edit_requests.txt";

    // Upload a new song
    public static void uploadSong(Artist artist, String title, String genre, String lyrics) {
        Song song = new Song(title, artist.getUsername(), genre, lyrics);
        song.saveToFile(); // ✅ Save as a formatted .txt file in data/songs/
        System.out.println("✅ Song uploaded.");
    }


    // Submit a genre update request
    public static void requestGenreUpdate(Artist artist, String newGenre) {
        try (FileWriter writer = new FileWriter(EDIT_REQUESTS_FILE, true)) {
            writer.write(artist.getUsername() + "," + newGenre + "\n");
            System.out.println("📨 Genre update request sent.");
        } catch (IOException e) {
            System.out.println("❌ Error submitting genre update request.");
        }
    }

    // View songs uploaded by this artist
    public static void viewMySongs(String artistUsername) {
        File folder = new File("data/songs/");
        if (!folder.exists()) {
            System.out.println("📁 No songs found.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("📁 No songs found.");
            return;
        }

        boolean found = false;

        for (File file : files) {
            Song song = Song.loadFromFile(file.getName().replace(".txt", "").replace("_", " "));
            if (song != null && song.getArtist().equals(artistUsername)) {
                song.displayFull();
                found = true;
            }
        }

        if (!found) {
            System.out.println("🪹 You haven't uploaded any songs yet.");
        }
    }

}

