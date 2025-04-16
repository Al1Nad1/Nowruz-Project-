package service;

import model.Song;

import java.io.*;

public class SearchService {

    public static void searchSongByTitle(String keyword) {
        searchInFiles(keyword.toLowerCase(), "title");
    }

    public static void searchSongByGenre(String keyword) {
        searchInFiles(keyword.toLowerCase(), "genre");
    }

    private static void searchInFiles(String keyword, String type) {
        File folder = new File("data/songs/");
        if (!folder.exists()) {
            System.out.println("📁 No songs directory found.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("📂 No songs to search.");
            return;
        }

        boolean found = false;

        for (File file : files) {
            String titleFromFile = file.getName().replace(".txt", "").replace("_", " ");
            Song song = Song.loadFromFile(titleFromFile);
            if (song == null) continue;

            if ((type.equals("title") && song.getTitle().toLowerCase().contains(keyword)) ||
                    (type.equals("genre") && song.getGenre().toLowerCase().contains(keyword))) {
                song.displayFull();
                found = true;
            }
        }

        if (!found) System.out.println("🔍 No matching songs found.");
    }
}
