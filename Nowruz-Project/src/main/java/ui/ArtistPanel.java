package ui;

import model.Artist;
import service.ArtistService;

import java.util.List;
import java.util.Scanner;

public class ArtistPanel {

    public static void show(Artist artist) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<String> menu = List.of(
                    "1. Upload a new song 🎶",
                    "2. Request genre update 📝",
                    "3. View my songs 📂",
                    "0. Logout 🚪"
            );

            ConsoleUI.printMenu("🎤 Artist Panel", menu);

            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Song title: ");
                    String title = scanner.nextLine();
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Lyrics: ");
                    String lyrics = scanner.nextLine();
                    ArtistService.uploadSong(artist, title, genre, lyrics);
                }
                case 2 -> {
                    System.out.print("New genre: ");
                    String newGenre = scanner.nextLine();
                    ArtistService.requestGenreUpdate(artist, newGenre);
                }
                case 3 -> ArtistService.viewMySongs(artist.getUsername());
                case 0 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
