package org.Bot.AutoShipPlacement;

import org.FleetFactory.InfoForPrinter;
import org.GameMaster.Player;
import org.Utilityes.Position;

import java.util.*;

import static org.Bot.AutoShipPlacement.BotTools.getEdgePoses;
import static org.Bot.AutoShipPlacement.BotTools.getPosArea;
import static org.Utilityes.PathMaker.getPositionsAreaV2;


public class BotShooter {
    private final ShipImage shipImage = new ShipImage();
    private final List<Position> potentialShipPos = new ArrayList<>();
    private final Position fieldSize;
    private Position lastShot;
    private InfoForPrinter lastInfo;

    public BotShooter(Position fieldSize) {
        this.fieldSize = fieldSize;
        feelPotentialShipPos();
    }

    private void feelPotentialShipPos() {
        for (int y = 1; y <= fieldSize.y(); y++) {
            for (int x = 1; x <= fieldSize.x(); x++) {
                potentialShipPos.add(new Position(x, y));
            }
        }
        Collections.shuffle(potentialShipPos);
    }

    public void shot(Player enemy) {
        if (shipImage.getPotentialPoses().isEmpty()) {
            simpleShot(enemy);
        } else {
            priorityShot(enemy);
        }
    }

    public void simpleShot(Player enemy) {
        Position position = potentialShipPos.removeFirst();
        InfoForPrinter infoForPrinter = enemy.hit(position);
        this.lastShot = position;
        this.lastInfo = infoForPrinter;
        if (infoForPrinter != null && !infoForPrinter.positions().isEmpty()) {
            if (infoForPrinter.condition()) {
                shipImage.addFirstHit(position);

                shipImage.addPotentialPoses(getPosArea(position));
                System.out.println("Неотфильтрованные потенциальные позиции:");
                System.out.println(shipImage.getPotentialPoses());
                filterPotentialPoses(shipImage.getPotentialPoses());
                System.out.println("Отфильтрованные потенциальные позиции:");
                System.out.println(shipImage.getPotentialPoses());
                Collections.shuffle(shipImage.getPotentialPoses());
            } else {
                narrowPotentialShipZone(position);
            }
        }
    }


    public void priorityShot(Player enemy) {
        Position position = shipImage.getNextPotentialPos();
        potentialShipPos.remove(position);
        InfoForPrinter infoForPrinter = enemy.hit(position);
        this.lastShot = position;
        this.lastInfo = infoForPrinter;
        if (infoForPrinter != null && !infoForPrinter.positions().isEmpty()) {
            if (infoForPrinter.condition()) {
                if (shipImage.getHits().size() == 1) {
                    shipImage.addSecondHit(position);
                } else if (shipImage.getHits().size() > 1) {
                    shipImage.addOtherHits(position);
                }
                shipImage.addPotentialPoses(getEdgePoses(shipImage.getHits(), shipImage.getPlate()));
                filterPotentialPoses(shipImage.getPotentialPoses());
                Collections.shuffle(shipImage.getPotentialPoses());
            } else {
                narrowPotentialShipZone(shipImage.getHits());
                shipImage.clean();
            }
        }
    }

    public void filterPotentialPoses(List<Position> positions) {
        positions.removeIf(p -> !potentialShipPos.contains(p));
    }

    public void narrowPotentialShipZone(Position position) {
        potentialShipPos.removeAll(getPositionsAreaV2(position, 3));
    }

    public void narrowPotentialShipZone(List<Position> positions) {
        for (Position position : positions) {
            narrowPotentialShipZone(position);
        }
    }

    public Position getLastShot() {
        return lastShot;
    }

    public InfoForPrinter getLastInfo() {
        return lastInfo;
    }
}
