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



//    public void inspectShip(List<Utilityes.Position> positionList) {
//        int decksNumber = positionList.size() - 1;
//        for (int i = 1; i < positionList.size(); i++) {
//            Utilityes.Position p2 = positionList.get(i);
//            Utilityes.Position p = positionList.get(i - 1);
//            Map<Utilityes.Position, Utilityes.Direction> ways = Utilityes.PathMaker.makeRightPath(p, decksNumber);
//            if (ways.containsKey(p2)) {
//                System.out.println("Палуба " + p2 + " расположилась " + ways.get(p2));
//            } else {
//                Map<Utilityes.Position, Utilityes.Direction> wrongWays = Utilityes.PathMaker.makeSidePath(p, decksNumber);
//                if (wrongWays.containsKey(p2)) {
//                    System.out.println("Палуба " + p2 + " расположилась боком " + wrongWays.get(p2));
//                } else {
//                    System.out.println("Палуба " + p2 + " находится отдельно от корабля!");
//                }
//            }
//            decksNumber--;
//        }
//    }

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
//        boolean b = fleet.getFleetMap().values().stream()
//                .anyMatch(ship -> shipName.equals(ship.getShipName()));
//        return !b;
    }

    public boolean inspectShipSize(Integer shipSize, int decksLeft, GameSettings gameSettings) {
        int min = gameSettings.getMinShipLength();
        int max = gameSettings.getMaxShipLength();
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
