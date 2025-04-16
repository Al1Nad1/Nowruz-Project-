package service;

import java.io.*;

public class QandAService {

    private static final String FILE = "data/qna.txt";

    public static void askQuestion(String songTitle, String question) {
        try (FileWriter writer = new FileWriter(FILE, true)) {
            writer.write("[" + songTitle + "] Q: " + question + "\n");
            System.out.println("✅ Question added.");
        } catch (IOException e) {
            System.out.println("Error writing question.");
        }
    }

    public static void answerQuestion(String songTitle, String answer) {
        try (FileWriter writer = new FileWriter(FILE, true)) {
            writer.write("[" + songTitle + "] A: " + answer + "\n");
            System.out.println("✅ Answer added.");
        } catch (IOException e) {
            System.out.println("Error writing answer.");
        }
    }

    public static void viewQnA(String songTitle) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            System.out.println("Q&A for: " + songTitle);
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[" + songTitle + "]")) {
                    System.out.println(line.replace("[" + songTitle + "]", "").trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Q&A.");
        }
    }
}
