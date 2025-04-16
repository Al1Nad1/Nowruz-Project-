package ui;

import java.util.List;


public class ConsoleUI {

    public static void printMenu(String title, List<String> options) {
        int width = getMaxWidth(title, options) + 4;

        String top = "╔" + "═".repeat(width - 2) + "╗";
        String divider = "╠" + "═".repeat(width - 2) + "╣";
        String bottom = "╚" + "═".repeat(width - 2) + "╝";

        System.out.println(top);
        System.out.println(centerLine(title, width));
        System.out.println(divider);

        for (String option : options) {
            System.out.println("║ " + padRight(option, width - 3) + "║");
        }

        System.out.println(bottom);
    }

    private static int getMaxWidth(String title, List<String> options) {
        int max = title.length();
        for (String option : options) {
            if (option.length() > max) {
                max = option.length();
            }
        }
        return max;
    }

    private static String centerLine(String text, int width) {
        int padding = (width - 2 - text.length()) / 2;
        return "║" + " ".repeat(padding) + text + " ".repeat(width - 2 - padding - text.length()) + "║";
    }

    private static String padRight(String text, int length) {
        return text + " ".repeat(Math.max(0, length - text.length()));
    }
}
