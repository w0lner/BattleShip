package org.GameMaster;

import org.Utilityes.ConsoleReader;
import org.Utilityes.Position;
import org.fieldsFactory.PreparedFieldForm;

import java.util.List;

public class GameMasterTools {

    public static PreparedFieldForm getFieldScale() {
        String fieldScale;
        PreparedFieldForm fieldForm;
        while (true) {
            fieldScale = ConsoleReader.readLine().toLowerCase();
            switch (fieldScale) {
                case "small":
                    fieldForm = PreparedFieldForm.Small;
                    break;
                case "medium":
                    fieldForm = PreparedFieldForm.Medium;
                    break;
                case "large":
                    fieldForm = PreparedFieldForm.Large;
                    break;
                case "custom":
                    fieldForm = PreparedFieldForm.Custom;
                    break;
                default:
                    System.out.println("Ты что то не то ввел, доступные команды: small/medium/large/custom");
                    continue;
            }
            return fieldForm;
        }
    }

    public static Position getFieldSize() {
        Position position;
        while (true) {
            position = ConsoleReader.readCoordinate();
            if (position == null) {
                System.out.println("Ты что то не то ввел, вводи цифры через пробел.");
                continue;
            }
            if (position.x() > 30 || position.y() > 30) {
                System.out.println("Максимальный размер поля: 30 на 30");
                continue;
            }
            if (position.x() < 4 || position.y() < 4) {
                System.out.println("Минимальный размер поля: 4 на 4");
                continue;
            }
            return position;
        }
    }

    private static Position getShipsSize(Position fieldSize) {
        Position position;
        while (true) {
            System.out.println("Размер кораблей? от (n) и до (n)");
            position = ConsoleReader.readTwoNum();
            if (position == null) {
                System.out.println("Ты что то не то ввел, введи 2 цифры через пробел!");
                continue;
            }
            if (position.x() < 1) {
                System.out.println("Размер кораблей не может быть меньше 1");
                continue;
            }
            if (position.y() > fieldSize.x() || position.y() > fieldSize.y()) {
                int maxSize = Math.min(fieldSize.x(), fieldSize.y());
                System.out.println("Максимальный размер корабля на этом поле: " + maxSize);
            }
        }
    }

    public static String getPlayerName(List<Player> players) {
        String playerName;
        while (true) {
            playerName = ConsoleReader.readLine();
            if (playerName == null) {
                System.out.println("Имя не определено!");
                continue;
            }
            if (playerName.isBlank() || playerName.isEmpty()) {
                System.out.println("Имя должно содержать символы!");
                continue;
            }
            if (checkName(players, playerName)) {
                System.out.println("Имя \"" + playerName + "\" уже занято!");
                continue;
            }
            return playerName;
        }
    }

    public static boolean checkName(List<Player> players, String playerName) {
        for (Player p : players) {
            if (p.getPlayerName().equals(playerName)) {
                return false;
            }
        }
        return true;
    }


    public static int getFleetSize() {
        Integer fleetHealth;
        while (true) {
            fleetHealth = ConsoleReader.readInt();
            if (fleetHealth == null) {
                System.out.println("Что то ты не то ввел, вводи цифру!");
            } else if (fleetHealth > 30){
                System.out.println("Давай по меньше..");
            } else if (fleetHealth == 0){
                System.out.println("Вот и поиграли ?");
            } else if (fleetHealth < 0){
                System.out.println("Умножу на -1");
                System.out.flush();
                return fleetHealth * -1;
            } else {
                return fleetHealth;
            }
        }
    }







    public static Position readCoordinate() {
        Position position;
        while (true) {
            position = ConsoleReader.readCoordinate();
            if (position == null) {
                System.out.println("Ты что то не то ввел, попробуй ещё раз");
                continue;
            }

        }
    }
}
