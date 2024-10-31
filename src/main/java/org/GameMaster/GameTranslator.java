package org.GameMaster;


public class GameTranslator {

    public static void showDoubleField(Player player) {
        System.out.println(player.getDoubleField());
    }

    public static void showSingleField(Player player) {
        System.out.println(player.getSingleField());
    }

    public static void clearConsole() {
        int clearDistance = 30;
        for (int i = 0; i < clearDistance; i++) {
            System.out.println();
        }
    }
}
