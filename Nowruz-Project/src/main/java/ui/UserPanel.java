package ui;

import model.User;
import service.*;

import java.util.List;
import java.util.Scanner;

public class UserPanel {

    public static void show(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<String> menu = List.of(
                    "🎯 1. Search songs by title",
                    "🎵 2. Search songs by genre",
                    "👍 3. Like a song",
                    "👎 4. Dislike a song",
                    "❓ 5. Ask a question",
                    "💬 6. Answer a question",
                    "📄 7. View Q&A",
                    "🧑‍🎤 8. Follow an artist",
                    "📋 9. View following list",
                    "✍️ 10. Comment on a song",
                    "🔍 11. View song details by title",

                    "🚪 0. Logout"
            );


            ConsoleUI.printMenu("🎧 User Panel", menu);

            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter keyword: ");
                    String kw = scanner.nextLine();
                    SearchService.searchSongByTitle(kw);
                }
                case 2 -> {
                    System.out.print("Enter genre: ");
                    String g = scanner.nextLine();
                    SearchService.searchSongByGenre(g);
                }
                case 3 -> {
                    System.out.print("Song title to like: ");
                    String title = scanner.nextLine();
                    SongService.likeSong(user.getUsername(), title); // ✅ send username
                }
                case 4 -> {
                    System.out.print("Song title to dislike: ");
                    String t = scanner.nextLine();
                    UserService.dislikeSong(user, t);
                }
                case 5 -> {
                    System.out.print("Song title: ");
                    String t = scanner.nextLine();
                    System.out.print("Your question: ");
                    String q = scanner.nextLine();
                    QandAService.askQuestion(t, q);
                }
                case 6 -> {
                    System.out.print("Song title: ");
                    String t = scanner.nextLine();
                    System.out.print("Your answer: ");
                    String a = scanner.nextLine();
                    QandAService.answerQuestion(t, a);
                }
                case 7 -> {
                    System.out.print("Song title: ");
                    String t = scanner.nextLine();
                    QandAService.viewQnA(t);
                }
                case 8 -> {
                    System.out.print("Artist name to follow: ");
                    String name = scanner.nextLine();
                    UserService.followArtist(user, name);
                }
                case 9 -> UserService.viewFollowing(user);
                case 0 -> {
                    System.out.println("Logging out...");
                    return;
                }
                case 10 -> {
                    System.out.print("Song title: ");
                    String t = scanner.nextLine();
                    System.out.print("Your comment: ");
                    String c = scanner.nextLine();
                    SongService.commentOnSong(t, c);
                }
                case 11 -> {
                    System.out.print("Song title: ");
                    String t = scanner.nextLine();
                    SongService.viewSongDetails(t);
                }

                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
