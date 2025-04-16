package service;

import model.Artist;

import java.io.*;
import java.util.*;

public class ArtistService {

    public static void uploadSong(Artist artist, String title, String genre, String lyrics) {
        try (FileWriter writer = new FileWriter("data/songs.txt", true)) {
            writer.write(artist.getUsername() + "," + title + "," + genre + "," + lyrics.replace(",", ";") + "\n");
            System.out.println("✅ Song uploaded.");
        } catch (IOException e) {
            System.out.println("Error uploading song.");
        }
    }

    public static void requestGenreUpdate(Artist artist, String newGenre) {
        try (FileWriter writer = new FileWriter("data/edit_requests.txt", true)) {
            writer.write(artist.getUsername() + "," + newGenre + "\n");
            System.out.println("📨 Genre update request sent.");
        } catch (IOException e) {
            System.out.println("Error submitting edit request.");
        }
    }

    public static void viewMySongs(String artistUsername) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/songs.txt"))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts[0].equals(artistUsername)) {
                    found = true;
                    System.out.println("🎵 Title: " + parts[1]);
                    System.out.println("   Genre: " + parts[2]);
                    System.out.println("   Lyrics: " + parts[3]);
                    System.out.println("-----------------------------");
                }
            }
            if (!found) {
                System.out.println("No songs uploaded yet.");
            }
        } catch (IOException e) {
            System.out.println("Error reading songs.");
        }
    }
}
