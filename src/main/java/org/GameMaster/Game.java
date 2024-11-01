package org.GameMaster;
import org.shipsFactory.ShipPrinter;

public class Game {
    private final GameHost gameHost;

    public Game(GameSettings gameSettings, ShipPrinter shipPrinter) {
        this.gameHost = new GameHost();
        this.gameHost.setPlayers(gameSettings.getPlayersList());
        this.gameHost.setFieldSize(gameSettings.getFieldSize());
        this.gameHost.setShipPrinter(shipPrinter);
        this.gameHost.setTurn(new Turn(gameSettings.getPlayersList().size()));
    }

    public void start() {
        play();
    }

    private void play() {
        while (gameHost.getPlayers().size() > 1) {
            gameHost.turnAnnouncer();
        }
        gameHost.gameOver();
    }
}
