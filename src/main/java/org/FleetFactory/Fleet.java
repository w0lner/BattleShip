package org.FleetFactory;


import lombok.Getter;
import org.Utilityes.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Fleet {
    private int fleetSize = 0;
    @Getter
    private Map<Position, Ship> fleetMap;

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

    public void hitFleet(Position position) {
        if (fleetMap.containsKey(position)) {
            fleetMap.get(position).hitShip(position);
            fleetMap.remove(position);
            if (fleetMap.isEmpty()) {
                System.out.println("Флот уничтожен!");
            }
        } else {
            System.out.println("Промах!");
        }
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
