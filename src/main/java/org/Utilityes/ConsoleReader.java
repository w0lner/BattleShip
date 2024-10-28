package org.Utilityes;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleReader {
    private static final Pattern two;
    private static final Pattern one;
    private static final Scanner scanner;

    static {
        two = Pattern.compile("(\\d+) (\\d+)");
        one = Pattern.compile("(\\d+)");
        scanner = new Scanner(System.in);
    }

    public static Position readPos() {
        String s = scanner.nextLine();
        char ch = RuCharacters.getRuCharFromString(s);
        int x;
        int y;
        if (ch != 0) {
            x = RuCharacters.getIntFromChar(ch);
            Matcher matcher1 = one.matcher(s);
            if (matcher1.find()) {
                y = Integer.parseInt(matcher1.group());
            } else {
                return null;
            }
        } else {
            Matcher matcher2 = two.matcher(s);
            if (matcher2.find()) {
                x = Integer.parseInt(matcher2.group(1));
                y = Integer.parseInt(matcher2.group(2));
            } else {
                return null;
            }
        }
        return new Position(x, y);
    }

    public static Integer readInt() {
        String s = scanner.nextLine();
        Matcher m1 = one.matcher(s);
        if (m1.find()) {
            return Integer.parseInt(m1.group());
        } else {
            return null;
        }
    }

    public static String readLine() {
        return scanner.nextLine();
    }

}
