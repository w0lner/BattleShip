package org.FleetFactory;


import org.GameMaster.GameSettings;
import org.Utilityes.PathMaker;
import org.Utilityes.Position;

import java.util.HashSet;
import java.util.Set;

public class Inspector {
    private final Fleet fleet;
    private final HashSet<Position> closeZone;
    private final HashSet<String> shipNames;

    public Inspector(Fleet fleet) {
        this.fleet = fleet;
        this.closeZone = new HashSet<>();
        this.shipNames = new HashSet<>();
    }

    public boolean inspectPosition(Position position) {
        if (position == null) {
            System.out.println("Некорректный ввод координат!");
            return false;
        } else if (closeZone.contains(position)) {
            System.out.println("Слишком близко к другому кораблю!");
            return false;
        } else if (fleet.getFleetMap().containsKey(position)){
            System.out.println("Позиция уже занята!");
            return false;
        } else {
            return true;
        }
    }

    public boolean inspectShipName(String shipName) {
        if (shipNames.contains(shipName)) {
            System.out.println("Корабль с именем \"" + shipName + "\" уже существует, введи другое.");
            return false;
        } else {
            shipNames.add(shipName);
            return true;
        }
    }

    public boolean inspectShipSize(Integer shipSize, int decksLeft, GameSettings gameSettings) {
        int min = gameSettings.getMinShipSize();
        int max = gameSettings.getMaxShipSize();
        if (shipSize == null) {
            System.out.println("Что то ты не то ввел, вводи цифру!");
            return false;
        }
        if (shipSize > decksLeft) {
            System.out.println("У тебя не осталось столько палуб! Осталось: " + decksLeft);
            return false;
        }
        if (shipSize > max) {
            System.out.println("Максимальное количество палуб: " + max);
            return false;
        }
        if (shipSize < min) {
            System.out.println("Минимальное количество палуб: " + min);
            return false;
        }
        return true;
    }

    public boolean inspectFieldSize(Position position) {
        if (position.x() > 30 || position.y() > 30) {
            System.out.println("Поле слишком большое! максимальный размер поля: 30 на 30");
            return false;
        } else {
            return true;
        }
    }

    public void extendCloseZone(Ship ship) {
        for (Position p : ship.getPositionList()) {
            closeZone.addAll(PathMaker.getPositionsAreaV2(p, 3));
        }
//        ship.getPositionList().stream()
//                .map(p -> Utilityes.PathMaker.getPositionsAreaV2(p, 3))
//                .forEach(closeZone::addAll);
    }

    public void filter(Set<Position> toFilter) {
        toFilter.removeAll(closeZone);
    }
}
