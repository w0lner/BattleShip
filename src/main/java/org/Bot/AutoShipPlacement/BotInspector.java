package org.Bot.AutoShipPlacement;

import org.Utilityes.Position;

import java.util.*;

import static org.Utilityes.PathMaker.getPositionsAreaV2;
import static org.GameMaster.GameMasterTools.getRandomInt;

public class BotInspector {
    private final Position fieldSize;
    private final Set<Position> accessiblePos;
//    private final List<Position> potentialToPlace;

    public BotInspector(Position fieldSize) {
        this.fieldSize = fieldSize;
        this.accessiblePos = new HashSet<>();
//        potentialToPlace = new ArrayList<>();
        feelAccessiblePos();
//        feelPotentialZones();
    }

    private void feelAccessiblePos() {
        for (int y = 1; y <= fieldSize.y(); y++) {
            for (int x = 1; x <= fieldSize.x(); x++) {
                accessiblePos.add(new Position(x, y));
            }
        }
    }
//    private void feelPotentialZones() {
//        potentialToPlace.addAll(accessiblePos);
//        Collections.shuffle(potentialToPlace);
//    }

    public Position getRandomAccessiblePos() {
        Position position;
        int random = getRandomInt(0, accessiblePos.size() - 1);
        Iterator<Position> iterator = accessiblePos.iterator();
        for (int i = 0; i < random; i++) {
            iterator.next();
        }
        position = iterator.next();
        iterator.remove();
        return position;
    }

//    public Position getRandomPotentialPos() {
//        if (potentialToPlace == null || potentialToPlace.isEmpty()) {
//            return null;
//        }
//        return potentialToPlace.removeFirst();
//    }


    public void expandCloseZones(List<Position> positions) {
        for (Position p : positions) {
//            Set<Position> closeZones = getPositionsAreaV2(p, 3);
            accessiblePos.removeAll(getPositionsAreaV2(p, 3));
//            potentialToPlace.removeAll(closeZones);
        }
    }

    public void expandCloseZones(Position position) {
        accessiblePos.remove(position);
    }

    public boolean inspectPoses(Set<Position> positions) {
        for (Position p : positions) {
            if (!inspectPos(p)) {
                return false;
            }
        }
        return true;
    }

    public boolean inspectPos(Position position) {
        return accessiblePos.contains(position);
    }

    public Set<Position> getAccessiblePos() {
        return accessiblePos;
    }

//    public List<Position> getPotentialToPlace() {
//        return potentialToPlace;
//    }
}
