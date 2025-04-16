package service;

import java.io.*;
import java.util.*;

public class AdminService {

    public static void showAllUsers() {
        readAndPrintFile("data/users.txt");
    }

    public static void showAllArtists() {
        readAndPrintFile("data/artists.txt");
    }

    public static void showEditRequests() {
        readAndPrintFile("data/edit_requests.txt");
    }

    public static void approveEditRequest(String username, String newGenre) {
        File inputFile = new File("data/edit_requests.txt");
        File tempFile = new File("data/temp_requests.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username)) {
                    updateArtistGenre(username, newGenre);
                    continue; // Skip writing this request (it's handled)
                }
                writer.println(line); // Keep others
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    private static void updateArtistGenre(String username, String newGenre) {
        File inputFile = new File("data/artists.txt");
        File tempFile = new File("data/temp_artists.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    writer.println(parts[0] + "," + parts[1] + "," + newGenre);
                } else {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public static void showQnA() {
        readAndPrintFile("data/qna.txt");
    }

    public static void answerQnA(String question, String answer) {
        try (FileWriter writer = new FileWriter("data/qna.txt", true)) {
            writer.write("Q: " + question + "\n");
            writer.write("A: " + answer + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAndPrintFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("> " + line);
            }
        } catch (IOException e) {
            System.out.println("Couldn't read file: " + path);
        }
    }
}
