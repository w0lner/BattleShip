import org.Bot.AutoShipPlacement.BotFleetFactory;
import org.Bot.AutoShipPlacement.BotInspector;
import org.Bot.AutoShipPlacement.BotPathMaker;
import org.Bot.AutoShipPlacement.BotTools;
import org.FleetFactory.Ship;
import org.GameMaster.GameMaster;
import org.GameMaster.GameMasterTools;
import org.GameMaster.GameSettings;
import org.GameMaster.Player;
import org.Utilityes.ConsoleReader;
import org.Utilityes.Enumerator;
import org.Utilityes.MassageKeeper;
import org.Utilityes.Position;
import org.fieldsFactory.DoubleFieldParameters;
import org.fieldsFactory.Field;
import org.fieldsFactory.FieldFactory;
import org.fieldsFactory.FieldParameters;
import org.shipsFactory.ShipPrinter;

import java.util.List;

public static void main(String[] args) {
    FieldParameters fieldParameters = new FieldParameters.Builder(10, 10, 6, 1).build();
    DoubleFieldParameters doubleFieldParameters = new DoubleFieldParameters.Builder(fieldParameters).build();
    ShipPrinter shipPrinter = new ShipPrinter(fieldParameters, doubleFieldParameters);
    BotInspector botInspector = new BotInspector(new Position(10, 10));
    BotPathMaker botPathMaker = new BotPathMaker(botInspector);
    Player p = new Player("Bot", 100);
    FieldFactory fieldFactory = new FieldFactory(fieldParameters, doubleFieldParameters);
    p.setSingleField(fieldFactory.getSingleField());
    p.setDoubleField(fieldFactory.getDoubleField());
    List<Ship> ships = BotTools.getPreparationShips(p.getFleetSize(), 4, 8);
    BotFleetFactory botFleetFactory = new BotFleetFactory(p, botPathMaker, shipPrinter, ships);
    Field field = p.getSingleField();
    System.out.println(field);
    System.out.println(botInspector.getAccessiblePos());
    System.out.println(botInspector.getPotentialToPlace());
}
