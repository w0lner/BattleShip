package org.Utilityes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Mover {
    private static final Map<Direction, BiFunction<Position, Integer, List<Position>>> moveMap = new HashMap<>();

    public static List<Position> move(Position position, int distance, Direction direction) {
        switch (direction) {
            case TOP -> {
                return goTop(position, distance);
            }
            case DOWN -> {
                return goDown(position, distance);
            }
            case LEFT -> {
                return goLeft(position, distance);
            }
            case RIGHT -> {
                return goRight(position, distance);
            }
        }
        return null;
    }

    public static List<Position> moveV2(Position position, int distance, Direction direction) {
        return moveMap.get(direction).apply(position, distance);
    }

    static {
        moveMap.put(Direction.TOP, Mover::goTop);
        moveMap.put(Direction.DOWN, Mover::goDown);
        moveMap.put(Direction.LEFT, Mover::goLeft);
        moveMap.put(Direction.RIGHT, Mover::goRight);
        moveMap.put(Direction.DOWN_LEFT, Mover::goDownLeft);
        moveMap.put(Direction.DOWN_RIGHT, Mover::goDownRight);
        moveMap.put(Direction.TOP_LEFT, Mover::goTopLeft);
        moveMap.put(Direction.TOP_RIGHT, Mover::goTopRight);
    }

    public static List<Position> mover(Position p, int distance, int dx, int dy) {
        List<Position> positions = new ArrayList<>();
        for (int i = 1; i <= distance; i++) {
            positions.add(new Position(p.x() + i * dx, p.y() + i * dy));
        }
        return positions;
    }

    public static List<Position> goTop(Position p, int distance) {
        return mover(p, distance, 0, -1);
    }

    public static List<Position> goDown(Position p, int distance) {
        return mover(p, distance, 0, 1);
    }

    public static List<Position> goLeft(Position p, int distance) {
        return mover(p, distance, -1, 0);
    }

    public static List<Position> goRight(Position p, int distance) {
        return mover(p, distance, 1, 0);
    }

    public static List<Position> goTopLeft(Position p, int distance) {
        return mover(p, distance, -1, -1);
    }

    public static List<Position> goTopRight(Position p, int distance) {
        return mover(p, distance, 1, -1);
    }

    public static List<Position> goDownLeft(Position p, int distance) {
        return mover(p, distance, -1, 1);
    }

    public static List<Position> goDownRight(Position p, int distance) {
        return mover(p, distance, 1, 1);
    }
}
