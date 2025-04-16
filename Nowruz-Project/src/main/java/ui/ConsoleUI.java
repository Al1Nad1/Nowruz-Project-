package ui;

import java.util.List;

public class ConsoleUI {

    public static void printMenu(String title, List<String> options) {
        // Calculate the maximum width required for the title and options
        int width = getMaxWidth(title, options) + 4;  // Add space for the borders

        // Generate the top, divider, and bottom lines
        String top = "╔" + "═".repeat(width - 2) + "╗";
        String divider = "╠" + "═".repeat(width - 2) + "╣";
        String bottom = "╚" + "═".repeat(width - 2) + "╝";

        // Print the top border
        System.out.println(top);
        // Print the centered title
        System.out.println(centerLine(title, width));
        // Print the divider line
        System.out.println(divider);

        // Print each option, ensuring they're aligned within the box
        for (String option : options) {
            // Align the options to fit the box width, add padding for the borders
            System.out.println("║ " + padRight(option, width - 4) + " ║");
        }

        // Print the bottom border
        System.out.println(bottom);
    }

    // Get the maximum width required for the title and options
    private static int getMaxWidth(String title, List<String> options) {
        int max = title.length();
        for (String option : options) {
            if (option.length() > max) {
                max = option.length();
            }
        }
        return max;
    }

    // Center the title within the box
    private static String centerLine(String text, int width) {
        int padding = (width - 2 - text.length()) / 2;
        return "║" + " ".repeat(padding) + text + " ".repeat(width - 2 - padding - text.length()) + "║";
    }

    // Pad the text to the right to fit within the specified width
    private static String padRight(String text, int length) {
        return text + " ".repeat(Math.max(0, length - text.length()));
    }
}
