package org.FleetFactory;

import org.GameMaster.GameTranslator;
import org.GameMaster.GameSettings;
import org.GameMaster.Player;
import org.Utilityes.ConsoleReader;
import org.Utilityes.Position;
import org.Utilityes.RuCharacters;
import org.shipsFactory.ShipPrinter;


public class FleetFactory {
    private final Player player;
    private final Fleet fleet;
    private final Inspector inspector;
    private final GameSettings gameSettings;
    private final ShipPrinter shipPrinter;
    private int remainsToInstalled;

    public FleetFactory(Player player, GameSettings gameSettings, ShipPrinter shipPrinter) {
        this.shipPrinter = shipPrinter;
        this.gameSettings = gameSettings;
        this.player = player;
        this.fleet = player.getFleet();
        this.inspector = new Inspector(fleet);
        this.remainsToInstalled = player.getFleetSize();
    }

    public void createFleet() {
        GameTranslator.showSingleField(player);
        while (remainsToInstalled > 0) {
            this.fleet.addShip(createShip());
        }
        player.setFleet(fleet);
        System.out.println("Флот построен!");
        System.out.println();
        System.out.println("Флот:\n" + fleet);
    }

    public Ship createShip() {
        System.out.println("Осталось расставить: " + remainsToInstalled + " палуб");
        Ship ship = new Ship(getShipName(), getDecksNumber());
        DecksInstaller decksInstaller = new DecksInstaller(ship, inspector, shipPrinter, player);
        do {
            decksInstaller.install(getPosition());
            GameTranslator.showSingleField(player);
        } while (!decksInstaller.isComplete());
        inspector.extendCloseZone(ship);
        remainsToInstalled = remainsToInstalled - ship.getDecksNumber();
        return ship;
    }

    public Position getPosition() {
        Position position;
        while (true) {
            System.out.println("Координаты палубы: ");
            position = ConsoleReader.readCoordinate();
            if (!inspector.inspectPosition(position)) {
                continue;
            }
            if (!checkPos(position)) {
                continue;
            }
            return position;
        }
    }

    private boolean checkPos(Position position) {
        int maxX = gameSettings.getFieldSize().x();
        int maxY = gameSettings.getFieldSize().y();
        if (position == null) {
            System.out.println("Позиция не определена");
            return false;
        }
        if (position.x() > maxX) {
            System.out.println("Максимальная граница по x = " +
                    maxX + " или " + RuCharacters.getCharFromInt(maxX));
            return false;
        }
        if (position.y() > maxY) {
            System.out.println("Максимальная граница по y = " + maxY);
            return false;
        }
        if (position.x() < 1 || position.y() < 1) {
            System.out.println("Координаты должны быть больше 1");
            return false;
        }
        return true;
    }

    public String getShipName() {
        String shipName;
        while (true) {
            System.out.println("Имя корабля: ");
            shipName = ConsoleReader.readLine();
            if (inspector.inspectShipName(shipName)) {
                return shipName;
            }
        }
    }

    public int getDecksNumber() {
        Integer decksNumber;
        while (true) {
            System.out.println("Количество палуб корабля: ");
            decksNumber = ConsoleReader.readInt();
            if (decksNumber == null) {
                continue;
            }
            if (inspector.inspectShipSize(decksNumber, remainsToInstalled, gameSettings)) {
                return decksNumber;
            }
        }
    }
}
