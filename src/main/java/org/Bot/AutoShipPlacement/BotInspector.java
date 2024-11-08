package org.Bot.AutoShipPlacement;

import org.Utilityes.Position;

import java.util.*;

import static org.Utilityes.PathMaker.getPositionsAreaV2;
import static org.GameMaster.GameMasterTools.getRandomInt;

public class BotInspector {
    private final Position fieldSize;
    private final List<Position> accessiblePos;

    public BotInspector(Position fieldSize) {
        this.fieldSize = fieldSize;
        this.accessiblePos = new ArrayList<>();
        feelAccessiblePos();
    }

    private void feelAccessiblePos() {
        for (int y = 1; y <= fieldSize.y(); y++) {
            for (int x = 1; x <= fieldSize.x(); x++) {
                accessiblePos.add(new Position(x, y));
            }
        }
        Collections.shuffle(accessiblePos);
    }

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



    public void expandCloseZones(List<Position> positions) {
        for (Position p : positions) {
            accessiblePos.removeAll(getPositionsAreaV2(p, 3));
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

    public List<Position> getAccessiblePos() {
        return accessiblePos;
    }

}
