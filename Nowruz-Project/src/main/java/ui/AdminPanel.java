package ui;

import model.Admin;
import service.AdminService;

import java.util.List;
import java.util.Scanner;

public class AdminPanel {

    public static void show(Admin admin) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<String> menu = List.of(
                    "1. View all users 👥",
                    "2. View all artists 🎙️",
                    "3. View artist edit requests 📝",
                    "4. Approve edit request ✅",
                    "5. View Q&A 📄",
                    "6. Answer Q&A 💬",
                    "0. Logout 🚪"
            );

            ConsoleUI.printMenu("🛡️ Admin Panel", menu);

            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> AdminService.showAllUsers();
                case 2 -> AdminService.showAllArtists();
                case 3 -> AdminService.showEditRequests();
                case 4 -> {
                    System.out.print("Username to approve: ");
                    String username = scanner.nextLine();
                    System.out.print("New genre: ");
                    String newGenre = scanner.nextLine();
                    AdminService.approveEditRequest(username, newGenre);
                }
                case 5 -> AdminService.showQnA();
                case 6 -> {
                    System.out.print("Question: ");
                    String q = scanner.nextLine();
                    System.out.print("Answer: ");
                    String a = scanner.nextLine();
                    AdminService.answerQnA(q, a);
                }
                case 0 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
