package org.GameMaster;


import org.FleetFactory.Fleet;

public class Player {
    private String playerName;
    private int fleetSize;
    private Fleet fleet;

    public Player(String playerName, int fleetSize) {
        this.playerName = playerName;
        this.fleetSize = fleetSize;
        this.fleet = new Fleet();
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
}
