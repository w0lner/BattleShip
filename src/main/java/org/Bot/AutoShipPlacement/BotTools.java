package org.Bot.AutoShipPlacement;

import org.FleetFactory.Ship;
import org.Utilityes.Direction;
import org.Utilityes.Plate;
import org.Utilityes.Position;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.GameMaster.GameMasterTools.getRandomInt;

public class BotTools {
    public static List<Integer> countShipSizes(int fleetSize, int minShipSize, int maxShipSize) {
        List<Integer> shipSizes = new ArrayList<>();
        int decksLeft = fleetSize;
        while (decksLeft >= minShipSize) {
            int decksLeftOrMaxSize = Math.min(decksLeft, maxShipSize);
            int shipSize = getRandomInt(minShipSize, decksLeftOrMaxSize);
            shipSizes.add(shipSize);
            decksLeft = decksLeft - shipSize;
        }
        return shipSizes;
    }

    public static List<String> countShipNames(int shipsNumber) {
        return IntStream.range(0, shipsNumber)
                .mapToObj(i -> "Корабль_" + (i + 1))
                .collect(Collectors.toList());
    }

    public static Position countRandomPos(Position fieldSize) {
        int x = getRandomInt(1, fieldSize.x());
        int y = getRandomInt(1, fieldSize.y());
        return new Position(x, y);
    }

    public static List<Ship> preparationShips(List<String> shipNames, List<Integer> shipSizes,
                                              int shipsNumber) {
        return IntStream.range(0, shipsNumber)
                .mapToObj(i -> new Ship(shipNames.get(i), shipSizes.get(i)))
                .collect(Collectors.toList());
    }

    public static Plate getRandomPlate() {
        int i = getRandomInt(1, 2);
        return switch (i) {
            case 1 -> Plate.X;
            case 2 -> Plate.Y;
            default -> null;
        };
    }

    public static List<Ship> getPreparationShips(int fleetSize, int minShipSize, int maxShipSize) {
        List<Integer> shipSizes = countShipSizes(fleetSize, minShipSize, maxShipSize);
        List<String> shipNames = countShipNames(shipSizes.size());
        return preparationShips(shipNames, shipSizes, shipSizes.size());
    }

    public static List<Direction> getDirections(Plate plate) {
        List<Direction> directions = new ArrayList<>(2);
        switch (plate) {
            case X -> {
                directions.add(Direction.TOP);
                directions.add(Direction.DOWN);
            }
            case Y -> {
                directions.add(Direction.LEFT);
                directions.add(Direction.RIGHT);
            }
        }
        Collections.shuffle(directions);
        return directions;
    }

    public static List<Direction> getAllDirections() {
        return Arrays.asList(Direction.TOP, Direction.DOWN, Direction.LEFT, Direction.LEFT);
    }

    public static List<Plate> getPlates() {
        List<Plate> plates = new ArrayList<>(2);
        plates.add(Plate.X);
        plates.add(Plate.Y);
        Collections.shuffle(plates);
        return plates;
    }

    public static Map<Position, Direction> getDirectionMap(Position position) {
        Map<Position, Direction> directionMap = new HashMap<>(4);
        //TOP
        directionMap.put(new Position(position.x(), position.y() - 1), Direction.TOP);
        //DOWN
        directionMap.put(new Position(position.x(), position.y() + 1), Direction.DOWN);
        //LEFT
        directionMap.put(new Position(position.x() - 1, position.y()), Direction.LEFT);
        //RIGHT
        directionMap.put(new Position(position.x() + 1, position.y()), Direction.RIGHT);

        return directionMap;
    }

    public static List<Position> getPosArea(Position position) {
        int x = position.x();
        int y = position.y();
        return Stream.of(
                //TOP
                new Position(x, y -1),
                //DOWN
                new Position(x, y + 1),
                //LEFT
                new Position(x - 1, y),
                //RIGHT
                new Position(x + 1, y)
        ).collect(Collectors.toList());
    }

    public static List<Position> getDiagonalPosArea(Position position) {
        int x = position.x();
        int y = position.y();
        return Stream.of(
                //TOP LEFT
                new Position(x - 1, y - 1),
                //TOP RIGHT
                new Position(x + 1, y - 1),
                //DOWN LEFT
                new Position(x - 1, y + 1),
                //DOWN RIGHT
                new Position(x + 1, y + 1)
        ).collect(Collectors.toList());
    }

    public static List<Position> getEdgePoses(List<Position> positions, Plate plate) {
        List<Position> edgePoses = new ArrayList<>(2);
        switch (plate) {
            case Y, DL_TR, TL_DR -> {
                edgePoses.add(positions.stream()
                        .min(Comparator.comparingInt(Position::x))
                                .map(pos -> new Position(pos.x() - 1, pos.y()))
                        .orElse(null));
                edgePoses.add(positions.stream()
                        .max(Comparator.comparingInt(Position::x))
                                .map(pos -> new Position(pos.x() + 1, pos.y()))
                        .orElse(null));
            }
            case X -> {
                edgePoses.add(positions.stream()
                        .min(Comparator.comparingInt(Position::y))
                                .map(pos -> new Position(pos.x(), pos.y() - 1))
                        .orElse(null));
                edgePoses.add(positions.stream()
                        .max(Comparator.comparingInt(Position::y))
                                .map(pos -> new Position(pos.x(), pos.y() + 1))
                        .orElse(null));
            }
        }
        return edgePoses;
    }
}
