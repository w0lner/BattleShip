package org.Utilityes;

public enum Direction {
    LEFT, RIGHT, TOP, DOWN, TOP_LEFT, TOP_RIGHT, DOWN_LEFT, DOWN_RIGHT;

    public static Direction findDirection(Position initial, Position comparison) {
        if (initial.x() > comparison.x() && initial.y() == comparison.y()) {
            return LEFT;
        }
        if (initial.x() < comparison.x() && initial.y() == comparison.y()) {
            return RIGHT;
        }
        if (initial.x() == comparison.x() && initial.y() > comparison.y()) {
            return TOP;
        }
        if (initial.x() == comparison.x() && initial.y() < comparison.y()) {
            return DOWN;
        }
        if (initial.x() > comparison.x() && initial.y() > comparison.y()) {
            return TOP_LEFT;
        }
        if (initial.x() < comparison.x() && initial.y() > comparison.y()) {
            return TOP_RIGHT;
        }
        if (initial.x() > comparison.x()) {
            return DOWN_LEFT;
        }
        if (initial.x() < comparison.x()) {
            return DOWN_RIGHT;
        }
        return null;
    }

    public static Position findDifferent(Position initial, Position comparison) {
        return new Position(initial.x() - comparison.x(), initial.y() - comparison.y());
    }

    public static int findDifferentX(Position initial, Position comparison) {
        return initial.x() - comparison.x();
    }

    public static int findDifferentY(Position initial, Position comparison) {
        return initial.y() - comparison.y();
    }

}
