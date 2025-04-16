package service;

import model.Artist;
import model.Song;

import java.io.*;

public class ArtistService {

    private static final String EDIT_REQUESTS_FILE = "data/edit_requests.txt";

    public static void uploadSong(Artist artist, String title, String genre, String lyrics) {
        Song song = new Song(title, artist.getUsername(), genre, lyrics);
        song.saveToFile();
        System.out.println("✅ Song uploaded.");
    }

    public static void requestGenreUpdate(Artist artist, String newGenre) {
        try (FileWriter writer = new FileWriter(EDIT_REQUESTS_FILE, true)) {
            writer.write(artist.getUsername() + "," + newGenre + "\n");
            System.out.println("📨 Genre update request sent.");
        } catch (IOException e) {
            System.out.println("❌ Error submitting genre update request.");
        }
    }

    public static void viewMySongs(String artistUsername) {
        File folder = new File("data/songs/");
        if (!folder.exists()) {
            System.out.println("📁 No songs directory found.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("📂 No songs to view.");
            return;
        }

        boolean found = false;
        for (File file : files) {
            String title = file.getName().replace(".txt", "").replace("_", " ");
            Song song = Song.loadFromFile(title);
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
