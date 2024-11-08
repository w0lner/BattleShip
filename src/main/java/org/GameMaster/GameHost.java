package org.GameMaster;

import lombok.Getter;
import lombok.Setter;
import org.FleetFactory.InfoForPrinter;
import org.Utilityes.ConsoleReader;
import org.Utilityes.MassageKeeper;
import org.Utilityes.Position;
import org.Utilityes.RuCharacters;
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
    private MassageKeeper massageKeeper = new MassageKeeper();

    public void turnAnnouncer() {
        Player player = players.get(turn.playerNumber() - 1);
        if (player.isBot()) {
            botMove(player);
        } else {
            massageKeeper.add("Ход игрока \"" + player.getPlayerName() + "\"");
            massageKeeper.add(player.getPlayerName() + " тут? Введи любой символ");
            massageKeeper.print();
            massageKeeper.clean();
            ConsoleReader.presenceCheck();
            move(player);
        }
    }

    public void botMove(Player bot) {
        massageKeeper.clean();
        Player enemy = players.get(turn.playerNumberAgainst() - 1);
        bot.botShot(enemy);
        Position position = bot.getBotLastShot();
        InfoForPrinter infoForPrinter = bot.botLastInfo();
        printShoots(bot, enemy, infoForPrinter, position);
    }

    public void move(Player player) {
        massageKeeper.print();
        massageKeeper.clean();
        GameTranslator.showDoubleField(player);
        Player enemy = players.get(turn.playerNumberAgainst() - 1);
        System.out.println("Твой выстрел:");
        Position position = getShoot(player);
        InfoForPrinter infoForPrinter = enemy.hit(position);
        printShoots(player, enemy, infoForPrinter, position);
        GameTranslator.showDoubleField(player);
        if (infoForPrinter != null) {
            System.out.println(infoForPrinter.massage());
        }
        GameTranslator.clearConsole();
    }

    public void printShoots(Player player, Player enemy, InfoForPrinter infoForPrinter, Position position) {
        boolean condition;
        if (infoForPrinter == null) {
            condition = false;
        } else {
            condition = infoForPrinter.condition();
        }
        Field field = player.getDoubleField();
        Field enemyField = enemy.getDoubleField();

        if (infoForPrinter == null || infoForPrinter.positions().isEmpty()) {
            //Принт промаха
            shipPrinter.printMiss(field, 2, position);
            shipPrinter.printMiss(enemyField, 1, position);
        } else if (condition) {
            //Принт попадания
            shipPrinter.printHit(field, 2, position);
            shipPrinter.printHit(enemyField, 1, position);
        } else {
            //Принт уничтоженного корабля
            for (Position p : infoForPrinter.positions()) {
                shipPrinter.printDestroyedShip(field, 2, p);
                shipPrinter.printDestroyedShip(enemyField, 1, p);
            }
        }
        massageKeeper.add("Последний выстрел: " + RuCharacters.getCharFromInt(position.x()) + position.y() +
                " от \"" + player.getPlayerName() +
                "\" по \"" + enemy.getPlayerName() + "\":");
        if (infoForPrinter == null || infoForPrinter.positions().isEmpty()) {
            massageKeeper.add("промах!");
        } else if (condition) {
            massageKeeper.add("попадание по кораблю: " + infoForPrinter.shipName() + "!");
        } else {
            if (!enemy.getFleet().getCondition()) {
                players.remove(enemy);
                massageKeeper.add("флот игрока \"" + enemy.getPlayerName() + "\" уничтожен!");
            } else {
                massageKeeper.add("корабль: \"" + infoForPrinter.shipName() + "\" уничтожен!" );
            }
        }
    }

    public Position getShoot(Player player) {
        Position position;
        while (true) {
            position = ConsoleReader.readCoordinate();
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
            player.addShotFired(position);
            return position;
        }
    }
    public void gameOver() {
        System.out.println("Игрок \"" + players.getFirst().getPlayerName() + "\" победил!");
    }
}
