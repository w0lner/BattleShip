package org.GameMaster;

import org.FleetFactory.FleetFactory;
import org.Utilityes.ConsoleReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMaster {
    private List<Player> players;
    private Scanner scanner;

    public GameMaster() {
        startGame();
    }

    private void startGame() {
        scanner = new Scanner(System.in);
        players = new ArrayList<>();
        System.out.println("Да начнется игра !");
        gameSetting();
    }

    private void gameSetting() {
        System.out.println("PvP или PvE ?");
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "pvp":
                PvP();
                break;
            case "pve":
                PvE();
                break;
            default:
                System.out.println("неизвестная команда");
                gameSetting();
        }
    }

    private void PvP() {
        System.out.println("Первый игрок назови себя");
        String playerName1 = getPlayerName();
        int fleetHealth1 = getFleetSize();
        players.add(new Player(playerName1, fleetHealth1));

        System.out.println("Второй игрок назови себя");
        String playerName2 =  getPlayerName();
        int fleetHealth2 = getFleetSize();
        players.add(new Player(playerName2, fleetHealth2));

        for (Player player : players) {
            arrangement(player);
        }
    }

    private void PvE() {
        System.out.println("PvE пока не работает!");
        gameSetting();
    }

    private void arrangement(Player player) {
        System.out.println("Игрок: " + player.getPlayerName() + " расставляет свои корабли");
        System.out.println("Кординаты вводятся по одной в формате \"x y\" через пробел цифра/цифра или буква/цифра");
        FleetFactory fleetFactory = FleetFactory.getInstance(player);
        fleetFactory.createFleet();
    }

    public int getFleetSize() {
        Integer fleetHealth;
        while (true) {
            System.out.println("Насколько большой будет твой флот?");
            fleetHealth = ConsoleReader.readInt();
            if (fleetHealth == null) {
                System.out.println("Что то ты не то ввел, вводи цифру!");
            } else if (fleetHealth > 30){
                System.out.println("Давай по меньше..");
            } else if (fleetHealth == 0){
                System.out.println("Вот и поиграли ?");
            } else if (fleetHealth < 0){
                System.out.println("Умножу на -1");
                return fleetHealth * -1;
            } else {
                return fleetHealth;
            }
        }
    }

    public String getPlayerName() {
        String playerName;
        while (true) {
            playerName = ConsoleReader.readLine();
            if (playerName == null) {
                System.out.println("Имя не определено!");
                continue;
            }
            if (playerName.isBlank() || playerName.isEmpty()) {
                System.out.println("Имя должно содержать символы!");
                continue;
            }
            return playerName;
        }
    }

    public void playerRegistration() {

    }
}
