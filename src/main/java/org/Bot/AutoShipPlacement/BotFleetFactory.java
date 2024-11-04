package org.Bot.AutoShipPlacement;

import org.FleetFactory.Ship;
import org.GameMaster.Player;
import org.Utilityes.Position;
import org.shipsFactory.ShipPrinter;

import java.util.List;


public class BotFleetFactory {
    private final Player player;
    private final BotPathMaker botPathMaker;
    private final ShipPrinter shipPrinter;
    private final List<Ship> ships;


    public BotFleetFactory(Player player, BotPathMaker botPathMaker, ShipPrinter shipPrinter, List<Ship> ships) {
        this.player = player;
        this.shipPrinter = shipPrinter;
        this.botPathMaker = botPathMaker;
        this.ships = ships;
        arrangeShips();
        printFleet();
    }

    public void arrangeShips() {
        for (Ship ship : ships) {
            List<Position> coordinates = botPathMaker.getPositions(ship.getDecksNumber());
            if (coordinates != null) {
                ship.setPositionList(coordinates);
                player.getFleet().addShip(ship);
            }
        }
    }
    public void printFleet() {
        shipPrinter.printFleet(player.getSingleField(), player.getFleet());
        shipPrinter.printFleet(player.getDoubleField(), player.getFleet());
    }
}
