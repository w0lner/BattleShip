package org.Utilityes;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PathMaker {
    public static Set<Position> makeRightPath(Position position, int decksNumber) {
        int x = position.x();
        int y = position.y();
        return IntStream.range(1, decksNumber + 1)
                .mapToObj(i -> Stream.of(
                        new Position(x - i, y),
                        new Position(x + i, y),
                        new Position(x, y - i),
                        new Position(x, y + i)
                ))
                .flatMap(Function.identity())
                .collect(Collectors.toSet());
    }

    public static Set<Position> makeSidePath(Position position, int decksNumber) {
        int x = position.x();
        int y = position.y();
        return IntStream.range(1, decksNumber + 1)
                .mapToObj(i -> Stream.of(
                        new Position(x - i, y - i),
                        new Position(x + i, y - i),
                        new Position(x - i, y + i),
                        new Position(x + i, y + i)
                ))
                .flatMap(p -> p)
                .collect(Collectors.toSet());
    }

    public static Map<Position, String> getPositionsArea(int x, int y, int mod) {
        Map<Position, String> surroundingCoords = new HashMap<>();

        if (mod == 1 || mod == 3) {
            surroundingCoords.put(new Position(x - 1, y), "слева"); // слева
            surroundingCoords.put(new Position(x, y - 1), "сверху"); // сверху
            surroundingCoords.put(new Position(x + 1, y), "справа"); // справа
            surroundingCoords.put(new Position(x, y + 1), "снизу"); // снизу
        }

        if (mod == 2 || mod == 3) {
            surroundingCoords.put(new Position(x - 1, y - 1), "слева сверху"); // слева сверху
            surroundingCoords.put(new Position(x + 1, y - 1), "справа сверху"); // справа сверху
            surroundingCoords.put(new Position(x + 1, y + 1), "справа снизу"); // справа снизу
            surroundingCoords.put(new Position(x - 1, y + 1), "слева снизу"); // слева снизу
            surroundingCoords.put(new Position(x, y), "прямо на другом корабле"); // прямо на палубе
        }

        return surroundingCoords;
    }

    public static Set<Position> getPositionsAreaV2(int x, int y, int mod) {
        Set<Position> closeZone = new HashSet<>();

        if (mod == 1 || mod == 3) {
            closeZone.add(new Position(x - 1, y)); // слева
            closeZone.add(new Position(x, y - 1)); // сверху
            closeZone.add(new Position(x + 1, y)); // справа
            closeZone.add(new Position(x, y + 1)); // снизу
        }

        if (mod == 2 || mod == 3) {
            closeZone.add(new Position(x - 1, y - 1)); // слева сверху
            closeZone.add(new Position(x + 1, y - 1)); // справа сверху
            closeZone.add(new Position(x + 1, y + 1)); // справа снизу
            closeZone.add(new Position(x - 1, y + 1)); // слева снизу
            closeZone.add(new Position(x, y)); // прямо на палубе
        }

        return closeZone;
    }

    public static Map<Position, String> getPositionsArea(Position position, int mod) {
        return getPositionsArea(position.x(), position.y(), mod);
    }

    public static Set<Position> getPositionsAreaV2(Position position, int mod) {
        return getPositionsAreaV2(position.x(), position.y(), mod);
    }

    public static List<Map<Position, String>> getPositionsMap(List<Position> positionList) {
        return positionList.stream()
                .map(position -> getPositionsArea(position, 3))
                .collect(Collectors.toList());
    }

    public static List<Position> getSpacePositions(Position p1, Position p2) {
        List<Position> positions = new ArrayList<>();
        int countX;
        int countY;
        int[] x = spaceBetween(p1.x(), p2.x());
        int[] y = spaceBetween(p1.y(), p2.y());
        int[] sX;
        int[] sY;

        if (x == null && y == null) {
            return null;
        }

        if (x != null) {
            countX = x.length;
        } else {
            countX = 1;
            x = new int[]{p1.x()};
        }
        if (y != null) {
            countY = y.length;
        } else {
            countY = 1;
            y = new int[]{p1.y()};
        }
        if (countX > countY) {
            sX = x;
            sY = new int[countX];
        } else {
            sX = new int[countY];
            sY = y;
        }
        if (countX > countY) {
            for (int i = 0; i < countX; i++) {
                if (i < countY) {
                    sY[i] = y[i];
                } else {
                    sY[i] = y[y.length - 1];
                }
            }
        } else {
            for (int i = 0; i < countY; i++) {
                if (i < countX) {
                    sX[i] = x[i];
                } else {
                    sX[i] = x[i - 1];
                }
            }
        }
        for (int i = 0; i < sX.length; i++) {
            positions.add(new Position(sX[i], sY[i]));
        }
        return positions;
    }

    private static int[] spaceBetween(int i1, int i2) {
        int diff = Math.abs(i1 - i2) - 1;
        if (diff > 0) {
            int[] ints = new int[diff];
            int counter = 0;
            if (i1 < i2) {
                for (int i = i1 + 1; i < i2; i++) {
                    ints[counter] = i;
                    counter++;
                }
            } else {
                for (int i = i2 + 1; i < i1; i++) {
                    ints[counter] = i;
                    counter++;
                }
            }
            return ints;
        }
        return null;
    }

    public static void sortPositionsByPlate(List<Position> positions, Plate plate) {
        switch (plate) {
            case X -> {
                positions.sort(Comparator.comparingInt(Position::y));
            }
            case Y -> {
                positions.sort(Comparator.comparingInt(Position::x));
            }
            case TL_DR -> {
                positions.sort(Comparator.comparingInt(Position::x).thenComparingInt(Position::y));
            }
            case DL_TR -> {
                positions.sort(Comparator.comparingInt(Position::x).thenComparingInt(Position::y).reversed());
            }
        }
    }

    public static Set<Position> getWay(List<Position> positionList, int remainDecks, Plate plate) {
        List<Position> spaces = new ArrayList<>();
        sortPositionsByPlate(positionList, plate);
        for (int i = 0; i < positionList.size() - 1; i++) {
            List<Position> s = getSpacePositions(positionList.get(i), positionList.get(i + 1));
            if (s != null) {
                spaces.addAll(s);
            }
        }
        int indent = remainDecks - spaces.size();
        if (indent < 0) {
            return null;
        }
        List<Position> way = new ArrayList<>();
        if (indent > 0) {
            way.addAll(Objects.requireNonNull(getIndents(positionList, indent, plate)));
        }
        way.addAll(spaces);
        return new HashSet<>(way);
    }

    public static List<Position> getIndents(List<Position> positions, int indent, Plate plate) {
        List<Position> indents = new ArrayList<>();
        switch (plate) {
            case X -> {
                indents.addAll(Mover.goTop(positions.getFirst(), indent));
                indents.addAll(Mover.goDown(positions.getLast(), indent));
                return indents;
            }
            case Y -> {
                indents.addAll(Mover.goLeft(positions.getFirst(), indent));
                indents.addAll(Mover.goRight(positions.getLast(), indent));
                return indents;
            }
            case TL_DR -> {
                indents.addAll(Mover.goTopLeft(positions.getFirst(), indent));
                indents.addAll(Mover.goDownRight(positions.getLast(), indent));
                return indents;
            }
            case DL_TR -> {
                indents.addAll(Mover.goDownLeft(positions.getLast(), indent));
                indents.addAll(Mover.goTopRight(positions.getFirst(), indent));
                return indents;
            }
        }
        return null;
    }
}
