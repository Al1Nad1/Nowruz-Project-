package service;

import model.Song;

import java.io.*;
import java.util.*;

public class SearchService {

    public static void searchSongByTitle(String keyword) {
        searchSong(keyword.toLowerCase(), "title");
    }

    public static void searchSongByGenre(String keyword) {
        searchSong(keyword.toLowerCase(), "genre");
    }

    private static void searchSong(String keyword, String type) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/songs.txt"))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                Song song = Song.fromLine(line);
                if (song == null) continue;

                if (type.equals("title") && song.getTitle().toLowerCase().contains(keyword)) {
                    song.display();
                    found = true;
                } else if (type.equals("genre") && song.getGenre().toLowerCase().contains(keyword)) {
                    song.display();
                    found = true;
                }
            }

            if (!found) System.out.println("🔍 No matching songs found.");
        } catch (IOException e) {
            System.out.println("Error reading songs.");
        }
    }
}
