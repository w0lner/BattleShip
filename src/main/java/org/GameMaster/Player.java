package org.GameMaster;
import org.FleetFactory.Fleet;
import org.FleetFactory.InfoForPrinter;
import org.Utilityes.Position;
import org.fieldsFactory.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private String playerName;
    private Fleet fleet;
    private int fleetSize;
    private Field singleField;
    private Field doubleField;
    private List<Position> shootsFired;

    public Player(String playerName, int fleetSize) {
        this.shootsFired = new ArrayList<>();
        this.playerName = playerName;
        this.fleetSize = fleetSize;
        this.fleet = new Fleet();
    }

    public InfoForPrinter hit(Position position) {
        return fleet.hitFleet(position);
    }


    public void setSingleField(Field singleField) {
        this.singleField = singleField;
    }

    public void setDoubleField(Field doubleField) {
        this.doubleField = doubleField;
    }

    public Field getSingleField() {
        return singleField;
    }

    public Field getDoubleField() {
        return doubleField;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getFleetSize() {
        return fleetSize;
    }

    public void setFleetSize(int fleetSize) {
        this.fleetSize = fleetSize;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }

    public List<Position> getShootsFired() {
        return shootsFired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerName);
    }
}
