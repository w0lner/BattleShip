package org.FleetFactory;


import org.Utilityes.Position;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

public class Ship {
    private final String shipName;
    private int decksNumber;
    private List<Position> positionList;
    private boolean condition = true;

    public Ship(String shipName, int decksNumber) {
        this.decksNumber = decksNumber;
        this.shipName = shipName;
        this.positionList = new ArrayList<>();
    }

    public void destroyShip() {
        this.condition = false;
        System.out.println("Корабль \"" + shipName + "\" уничтожен!");
    }

    public void hitShip(Position position) {
        if (positionList.contains(position)) {
            positionList.remove(position);
            if (!positionList.isEmpty()) {
                System.out.println("Попадение по кораблю \"" + shipName + "\" выстрелом: " + position);
            } else {
                destroyShip();
            }
        } else {
            System.out.println("У корабля \"" + shipName + "\" нет позиции: " + position);
        }
    }

    public int getDecksNumber() {
        return decksNumber;
    }

    public void addDeck(Position position) {
        positionList.add(position);
    }

    public String getShipName() {
        return shipName;
    }

    public int getShipSize() {
        return positionList.size();
    }

    public boolean condition() {
        return condition;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(shipName, ship.shipName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(shipName);
    }

    @Override
    public String toString() {
        return "FleetFactory.Ship{" +
                "name='" + shipName + '\'' +
                ", shipLength=" + getShipSize() +
                '}';
    }
}
