package service;

import java.io.*;
import java.util.*;

public class SearchService {

    public static void searchSongByTitle(String keyword) {
        searchSong(keyword, 1); // title = index 1
    }

    public static void searchSongByGenre(String keyword) {
        searchSong(keyword, 2); // genre = index 2
    }

    private static void searchSong(String keyword, int column) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/songs.txt"))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length >= 4 && parts[column].toLowerCase().contains(keyword.toLowerCase())) {
                    found = true;
                    System.out.println("🎵 Title: " + parts[1]);
                    System.out.println("   Artist: " + parts[0]);
                    System.out.println("   Genre: " + parts[2]);
                    System.out.println("   Lyrics: " + parts[3]);
                    System.out.println("----------------------");
                }
            }

            if (!found) System.out.println("No matching songs found.");

        } catch (IOException e) {
            System.out.println("Error reading songs.");
        }
    }
}
