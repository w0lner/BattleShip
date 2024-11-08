package org.Bot.AutoShipPlacement;

import org.Utilityes.Plate;
import org.Utilityes.Position;

import java.util.*;

import static org.Utilityes.Plate.findPlate;


public class ShipImage {
    private final List<Position> hits = new ArrayList<>(2);
    private final List<Position> potentialPoses = new ArrayList<>(4);
    private Plate plate;

    public ShipImage() {}

    public void addFirstHit(Position position) {
        hits.add(position);
    }

    public void addSecondHit(Position position) {
        hits.add(position);
        plate = findPlate(hits.get(0), hits.get(1));
        potentialPoses.clear();
    }

    public void addOtherHits(Position position) {
        hits.add(position);
        potentialPoses.clear();
    }

   public void addPotentialPoses(List<Position> positions) {
        potentialPoses.addAll(positions);
   }

   public Position getNextPotentialPos() {
        return potentialPoses.removeFirst();
   }

   public List<Position> getPotentialPoses() {
        return potentialPoses;
   }

   public List<Position> getHits() {
        return hits;
   }

   public Plate getPlate() {
        return plate;
   }

   public void setPlate(Plate plate) {
        this.plate = plate;
   }
   public void clean() {
        hits.clear();
        potentialPoses.clear();
        plate = null;
   }
}
