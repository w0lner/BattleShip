package org.FleetFactory;


import lombok.Getter;
import org.GameMaster.Game;
import org.Utilityes.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Fleet {
    private int fleetSize = 0;
    @Getter
    private Map<Position, Ship> fleetMap;
    private boolean condition = true;

    public Fleet() {
        this.fleetMap = new HashMap<>();
    }

    public void addShip(Ship ship) {
        fleetMap.putAll(
                ship.getPositionList().stream()
                        .peek(p -> fleetSize++)
                        .collect(Collectors.toMap(
                                k -> k,
                                v -> ship
                        ))
        );
    }

    public InfoForPrinter hitFleet(Position position) {
        if (fleetMap.containsKey(position)) {
            hit();
            if (!condition) {
                System.out.println("Флот уничтожен!");
            }
            return fleetMap.remove(position).hitShip(position);
        } else {
            System.out.println("Промах!");
            return new InfoForPrinter(Collections.emptyList(), true);
        }
    }

    private void hit() {
        fleetSize--;
        if (fleetSize <= 0) {
            condition = false;
        }
    }

    public boolean getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        fleetMap.values().stream()
                .distinct()
                .forEach(ship -> {
                    sb.append("\nИмя корабля: \"").append(ship.getShipName());
                    sb.append("\", .оличество палуб : ").append(ship.getDecksNumber());
                });
        return sb.toString();
    }
}
