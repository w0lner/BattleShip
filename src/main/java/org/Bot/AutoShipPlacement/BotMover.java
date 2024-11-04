package org.Bot.AutoShipPlacement;

import org.Utilityes.Direction;
import org.Utilityes.Position;

public class BotMover {
    public static Position move(Position position, Direction direction) {
        switch (direction) {
            case TOP -> {
                return goTop(position);
            }
            case DOWN -> {
                return goDown(position);
            }
            case LEFT -> {
                return goLeft(position);
            }
            case RIGHT -> {
                return goRight(position);
            }
            default -> {
                return null;
            }
        }
    }

    public static Position goTop(Position position) {
        return new Position(position.x(), position.y() - 1);
    }

    public static Position goDown(Position position) {
        return new Position(position.x(), position.y() + 1);
    }

    public static Position goLeft(Position position) {
        return new Position(position.x() - 1, position.y());
    }

    public static Position goRight(Position position) {
        return new Position(position.x() + 1, position.y());
    }
}
