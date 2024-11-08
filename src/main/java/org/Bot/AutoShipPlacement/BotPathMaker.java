package org.Bot.AutoShipPlacement;

import org.Utilityes.Direction;
import org.Utilityes.Plate;
import org.Utilityes.Position;

import java.util.*;

import static org.Bot.AutoShipPlacement.BotMover.move;
import static org.Bot.AutoShipPlacement.BotTools.getDirections;
import static org.Bot.AutoShipPlacement.BotTools.getPlates;


public class BotPathMaker {
    private final BotInspector botInspector;

    public BotPathMaker(BotInspector botInspector) {
        this.botInspector = botInspector;
        getPlates();
    }

    public List<Position> getPositions(int shipSize) {
        for (Position p : botInspector.getAccessiblePos()) {
            List<Position> positionList = getShipCoordinates(p, shipSize);
            if (positionList != null) {
                botInspector.expandCloseZones(positionList);
                return positionList;
            }
        }
        return null;
    }

    private List<Position> getShipCoordinates(Position position, int shipSize) {
        List<Position> positions = new ArrayList<>(shipSize);
        List<Plate> plates = getPlates();
        while (!plates.isEmpty()) {
            positions.addAll(getNextByPlate(position, plates.removeFirst(), shipSize - 1));
            if (positions.size() == shipSize - 1) {
                positions.add(position);
                return positions;
            } else {
                positions.clear();
            }
        }
        return null;
    }

    private List<Position> getNextByPlate(Position position, Plate plate, int shipSize) {
        List<Position> positions = new ArrayList<>();
        List<Direction> directions = getDirections(plate);
        while (!directions.isEmpty() && positions.size() < shipSize) {
            positions.addAll(getNextPosByDir(position, directions.removeFirst(), shipSize));
        }
        return positions;
    }

    private List<Position> getNextPosByDir(Position position, Direction direction, int shipSize) {
        List<Position> positions = new ArrayList<>();
        Position p = null;
        while (positions.size() < shipSize) {
            if (p == null) {
                p = move(position, direction);
            } else {
                p = move(p, direction);
            }
            if (botInspector.inspectPos(p)) {
                positions.add(p);
            } else {
                break;
            }
        }
        return positions;
    }

}
