package org.FleetFactory;

import org.GameMaster.Player;
import org.Utilityes.ConsoleReader;
import org.Utilityes.Position;


public class FleetFactory {
    private static FleetFactory instance;
    private final Player player;
    private final Fleet fleet;
    private final Inspector inspector;
    private int remainsToInstalled;

    private FleetFactory(Player player) {
        this.player = player;
        this.fleet = player.getFleet();
        this.inspector = new Inspector(fleet);
        this.remainsToInstalled = player.getFleetSize();
    }

    public static FleetFactory getInstance(Player player) {
        if (instance == null) {
            instance = new FleetFactory(player);
        }
        return instance;
    }

    public void createFleet() {
        while (remainsToInstalled > 0) {
            System.out.println("Осталось расставить: " + remainsToInstalled + " палуб");
            this.fleet.addShip(createShip());
        }
        player.setFleet(fleet);
        System.out.println("Флот построен!");
        System.out.println(fleet);
    }

    public Ship createShip() {
        Ship ship = new Ship(getShipName(), getDecksNumber());
        DecksInstaller decksInstaller = new DecksInstaller(ship, inspector);
        do {
            decksInstaller.install(getPosition());
        } while (!decksInstaller.isComplete());
        inspector.extendCloseZone(ship);
        remainsToInstalled = remainsToInstalled - ship.getDecksNumber();
        return ship;
    }

    public Position getPosition() {
        Position position;
        while (true) {
            System.out.println("Координаты палубы: ");
            position = ConsoleReader.readPos();
            if (!inspector.inspectPosition(position)) {
                continue;
            }
            return position;
        }

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
            if (inspector.inspectShipSize(decksNumber, remainsToInstalled)) {
                return decksNumber;
            }
        }
    }
}
