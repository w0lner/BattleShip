package org.Bot.AutoShipPlacement;

import org.FleetFactory.Fleet;
import org.FleetFactory.Ship;
import org.GameMaster.GameSettings;
import org.GameMaster.Player;
import org.Utilityes.Position;
import org.shipsFactory.ShipPrinter;

import java.util.List;

import static org.Bot.AutoShipPlacement.BotTools.*;

public class AutoShipPlacement {
    public AutoShipPlacement(GameSettings gameSettings, ShipPrinter shipPrinter, Player player) {
        List<Integer> shipSizes = countShipSizes(player.getFleetSize(), gameSettings.getMinShipSize(), gameSettings.getMaxShipSize());
        int shipsNumber = shipSizes.size();
        List<String> shipNames = countShipNames(shipsNumber);
        BotInspector botInspector = new BotInspector(gameSettings.getFieldSize());
        BotPathMaker botPathMaker = new BotPathMaker(botInspector);
        BotFleetFactory botFleetFactory = new BotFleetFactory(player, botPathMaker, shipPrinter, preparationShips(shipNames, shipSizes, shipsNumber));
        botFleetFactory.arrangeShips();
        botFleetFactory.printFleet();
    }
}
