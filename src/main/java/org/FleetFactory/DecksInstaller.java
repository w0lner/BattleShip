package org.FleetFactory;
import org.GameMaster.Player;
import org.Utilityes.PathMaker;
import org.Utilityes.Plate;
import org.Utilityes.Position;
import org.shipsFactory.ShipPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;





public class DecksInstaller {
    private final Ship ship;
    private List<Position> installedDecks;
    private int decksLeft;
    private Plate plate;
    private final Inspector inspector;
    private final ShipPrinter shipPrinter;
    private final Player player;

    public DecksInstaller(Ship ship, Inspector inspector, ShipPrinter shipPrinter, Player player) {
        this.player = player;
        this.shipPrinter = shipPrinter;
        this.ship = ship;
        this.installedDecks = ship.getPositionList();
        this.decksLeft = ship.getDecksNumber();
        this.inspector = inspector;
    }

    public void install(Position position) {
        if (installedDecks.isEmpty()) {
            installFirstDeck(position);
        } else if (installedDecks.size() == 1) {
            installSecondDeck(position);
        } else if (installedDecks.size() == 2){
            installThirdDeck(position);
        } else {
            installOtherDecks(position);
        }
    }


    public void installFirstDeck(Position position) {
        installDeck(position);
    }

    public void installSecondDeck(Position position) {
        Set<Position> way = PathMaker.makeRightPath(installedDecks.getFirst(), decksLeft);
        inspector.filter(way);
        if (way.contains(position)) {
            installDeck(position);
        } else {
            way = PathMaker.makeSidePath(installedDecks.getFirst(), decksLeft);
            inspector.filter(way);
            if (way.contains(position)) {
                System.out.println("Собираешься ставть корабль боком? Кто сказал что нельзя?");
                installDeck(position);
            } else {
                System.out.println("Палуба не стыкуется с кораблем, слишком далеко");
            }
        }
    }

    public void installThirdDeck(Position position) {
        this.plate = Plate.findPlate(installedDecks.get(0), installedDecks.get(1));
        Set<Position> way = PathMaker.getWay(installedDecks, decksLeft, plate);
        if (way == null) {
            System.out.println("Корабль располагается неправильно!");
            cancel();
        } else {
            inspector.filter(way);
            if (way.contains(position)) {
                installDeck(position);
            } else {
                System.out.println("Палуба: " + position + " не стыкуется с кораблем!");
                System.out.println("Оставшиеся допустимые позиции: \n" + way);
            }
        }
    }

    public void installOtherDecks(Position position) {
        Set<Position> way = PathMaker.getWay(installedDecks, decksLeft, plate);
        if (way == null) {
            System.out.println("Палуба слишком далеко от корабля!");
            cancel();
        } else {
            inspector.filter(way);
            if (way.contains(position)) {
                installDeck(position);
            } else {
                System.out.println("Палуба: " + position + " слишком далеко от корабля!");
                System.out.println("Оставшиеся допустимые позиции: \n" + way);
            }
        }
    }

    private void installDeck(Position position) {
        if (installedDecks.contains(position)) {
            System.out.println("На этой позиции уже стоит палуба!");
        } else {
            installedDecks.add(position);
            decksLeft--;
            shipPrinter.printShip(player.getSingleField(),1, position);
            shipPrinter.printShip(player.getDoubleField(),1, position);
            System.out.println("Палуба: " + position + " установлена!");
            if (decksLeft == 0) {
                complete();
            }
        }
    }

    public void complete() {
        System.out.println("Корабль \"" + ship.getShipName() + "\" построен!");
    }

    public void cancel() {
        this.installedDecks = new ArrayList<>();
        this.decksLeft = ship.getDecksNumber();
        System.out.println("Строительство отменено! Попробуй ещё раз");
    }

    public boolean isComplete() {
        if (decksLeft != 0) {
            return false;
        } else {
            return true;
        }
    }

}
