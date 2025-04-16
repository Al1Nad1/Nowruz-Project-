package service;

import java.io.*;

public class QandAService {

    private static final String QNA_FILE = "data/qna.txt";

    public static void askQuestion(String username, String songTitle, String question) {
        try (FileWriter writer = new FileWriter(QNA_FILE, true)) {
            writer.write("[Q] " + username + " → " + songTitle + ": " + question + "\n");
            System.out.println("❓ Question submitted.");
        } catch (IOException e) {
            System.out.println("❌ Error saving question.");
        }
    }

    public static void answerQuestion(String songTitle, String answer) {
        try (FileWriter writer = new FileWriter(QNA_FILE, true)) {
            writer.write("[A] Answer for " + songTitle + ": " + answer + "\n");
            System.out.println("✅ Answer submitted.");
        } catch (IOException e) {
            System.out.println("❌ Error saving answer.");
        }
    }

    public static void viewQnA(String songTitle) {
        try (BufferedReader reader = new BufferedReader(new FileReader(QNA_FILE))) {
            String line;
            System.out.println("📄 Q&A for song: " + songTitle);
            while ((line = reader.readLine()) != null) {
                if (line.contains(songTitle)) {
                    System.out.println("  " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading QnA.");
        }
    }
}
