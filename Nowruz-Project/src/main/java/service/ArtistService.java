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
        try (FileWriter writer = new FileWriter(SONGS_FILE, true)) {
            writer.write(song.toLine() + "\n");
            System.out.println("✅ Song uploaded.");
        } catch (IOException e) {
            System.out.println("❌ Error uploading song.");
        }
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
        File file = new File(SONGS_FILE);
        if (!file.exists()) {
            System.out.println("❌ No songs file found.");
            return;
        }

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Song song = Song.fromLine(line);
                if (song != null && song.getArtist().equals(artistUsername)) {
                    song.display();
                    found = true;
                }
            }

            if (!found) {
                System.out.println("🪹 You haven't uploaded any songs yet.");
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading songs.");
        }
    }
}
