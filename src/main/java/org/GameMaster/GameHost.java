package org.GameMaster;

import lombok.Getter;
import lombok.Setter;
import org.FleetFactory.InfoForPrinter;
import org.Utilityes.ConsoleReader;
import org.Utilityes.Position;
import org.fieldsFactory.Field;
import org.shipsFactory.ShipPrinter;

import java.util.List;

@Setter
@Getter
public class GameHost {
    private Position fieldSize;
    private List<Player> players;
    private ShipPrinter shipPrinter;
    private Turn turn;

    public void turnAnnouncer() {
        Player player = players.get(turn.playerNumber() - 1);
        System.out.println("Ход игрока \"" + player.getPlayerName() + "\"");
        System.out.println(player.getPlayerName() + " тут? Введи любой символ");
        ConsoleReader.presenceCheck();
        move(player);
    }

    public void move(Player player) {
        GameTranslator.showDoubleField(player);
        Player enemy = players.get(turn.playerNumberAgainst() - 1);
        System.out.println("Твой выстрел:");
        Position position = getShoot(player);
        InfoForPrinter infoForPrinter = enemy.hit(position);
        printShoots(player, enemy, infoForPrinter, position);
        GameTranslator.showDoubleField(player);
        GameTranslator.clearConsole();
        if (!enemy.getFleet().getCondition()) {
            players.remove(enemy);
            System.out.println("Игрок \"" + enemy.getPlayerName() + "\" потерпел поражение!");
        }
    }

    public void printShoots(Player player, Player enemy, InfoForPrinter infoForPrinter, Position position) {
        Field field = player.getDoubleField();
        Field enemyField = enemy.getDoubleField();
        boolean condition = infoForPrinter.condition();
        List<Position> positions = infoForPrinter.positions();

        if (infoForPrinter.positions().isEmpty()) {
            shipPrinter.printMiss(field, 2, position);
            shipPrinter.printMiss(enemyField, 1, position);
        } else if (condition) {
            shipPrinter.printHit(field, 2, position);
            shipPrinter.printHit(enemyField, 1, position);
        } else {
            for (Position p : positions) {
                shipPrinter.printDestroyedShip(field, 2, p);
                shipPrinter.printDestroyedShip(enemyField, 1, p);
            }
        }
    }

    public Position getShoot(Player player) {
        Position position;
        while (true) {
            position = ConsoleReader.readPos();
            if (position == null) {
                continue;
            }
            if (position.x() > fieldSize.x() || position.y() > fieldSize.y() ||
                    position.x() < 1 || position.y() < 1) {
                System.out.println("Бьёшь мимо поля!");
                continue;
            }
            if (player.getShootsFired().contains(position)) {
                System.out.println("Ты сюда уже стрелял!");
                continue;
            }
            return position;
        }
    }
    public void gameOver() {
        System.out.println("Игрок \"" + players.getFirst().getPlayerName() + "\" победил!");
    }
}
