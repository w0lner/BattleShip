package org.GameMaster;

import org.FleetFactory.FleetFactory;
import org.Utilityes.ConsoleReader;
import org.Utilityes.Position;
import org.fieldsFactory.FieldFactory;
import org.fieldsFactory.PreparedForms;
import org.shipsFactory.ShipPrinter;

import java.util.Random;

public class GameMaster {

    private final GameSettings gameSettings;
    private FieldFactory fieldFactory;
    private ShipPrinter shipPrinter;

    public GameMaster() {
        gameSettings = new GameSettings();
    }


    public void run() {
        System.out.println("Добро пожаловать на Морской бой !");
        gameSetup();
        startGame();
    }

    private void startGame() {
        System.out.println("Да начнется игра !");
        Game game = new Game(gameSettings, shipPrinter);
    }

    private void gameSetup() {
        setGameMode();
        setFieldParam();
        shipPrinter = new ShipPrinter(gameSettings.getFieldParameters(), gameSettings.getDoubleFieldParameters());
        fieldFactory = new FieldFactory(gameSettings.getFieldParameters(), gameSettings.getDoubleFieldParameters());
        if (gameSettings.getGameMode().equals("pvp")) {
            playerRegistration();
            playerRegistration();
            GameTranslator.clearConsole();
        } else {
            playerRegistration();
            GameTranslator.clearConsole();
        }
        for (Player p : GameSettings.getPlayers()) {
            arrangement(p);
        }

    }


    private void setFieldParam() {
        System.out.println("Размер поля ? x(n) на y(n) клеток");
        gameSettings.setFieldSize(getFieldSize());
        System.out.println("Масштаб поля ? small/medium/large/custom");
        gameSettings.setPreparedForms(getFieldScale());
        gameSettings.setFieldParam();
    }

    private void setGameMode() {
        String gameMod;
        while (true) {
            System.out.println("PvP или PvE ?");
            gameMod = ConsoleReader.readLine().toLowerCase();
            if (gameMod.equals("pvp")) {
                gameSettings.setGameMode(gameMod);
                break;
            }
            if (gameMod.equals("pve")) {
                System.out.println("Режим PvE пока не работает!");
                setGameMode();
//                gameSettings.setGameMode(gameMod);
//                break;
            }
            System.out.println("Настройка введена неверно!");
        }
    }

    private void PvP() {

    }

    private void PvE() {
        System.out.println("PvE пока не работает!");
        setGameMode();
    }

    private void arrangement(Player player) {
        System.out.println("\nИгрок: \"" + player.getPlayerName() + "\" расставляет свои корабли");
        System.out.println("Кординаты вводятся по одной в формате \"x y\" через пробел цифра/цифра или буква/цифра");
        FleetFactory fleetFactory = new FleetFactory(player, gameSettings, shipPrinter);
        fleetFactory.createFleet();
        GameTranslator.clearConsole();
    }

    public int getFleetSize() {
        Integer fleetHealth;
        while (true) {
            fleetHealth = ConsoleReader.readInt();
            if (fleetHealth == null) {
                System.out.println("Что то ты не то ввел, вводи цифру!");
            } else if (fleetHealth > 30){
                System.out.println("Давай по меньше..");
            } else if (fleetHealth == 0){
                System.out.println("Вот и поиграли ?");
            } else if (fleetHealth < 0){
                System.out.println("Умножу на -1");
                System.out.flush();
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
            if (!GameSettings.checkName(playerName)) {
                System.out.println("Имя \"" + playerName + "\" уже занято!");
                continue;
            }
            return playerName;
        }
    }

    private Position getFieldSize() {
        Position position;
        while (true) {
            position = ConsoleReader.readPos();
            if (position == null) {
                System.out.println("Ты что то не то ввел, вводи цифры через пробел.");
                continue;
            }
            if (position.x() > 30 || position.y() > 30) {
                System.out.println("Максимальный размер поля: 30 на 30");
                continue;
            }
            if (position.x() < 4 || position.y() < 4) {
                System.out.println("Минимальный размер поля: 4 на 4");
                continue;
            }
            return position;
        }
    }

    public PreparedForms getFieldScale() {
        String fieldScale;
        PreparedForms preparedForms;
        while (true) {
            fieldScale = ConsoleReader.readLine().toLowerCase();
            switch (fieldScale) {
                case "small":
                    preparedForms = PreparedForms.Small;
                    break;
                case "medium":
                    preparedForms = PreparedForms.Medium;
                    break;
                case "large":
                    preparedForms = PreparedForms.Large;
                    break;
                case "custom":
                    preparedForms = PreparedForms.Custom;
                    break;
                default:
                    System.out.println("Ты что то не то ввел, доступные команды: small/medium/large/custom");
                    continue;
            }
            return preparedForms;
        }
    }

    public void playerRegistration() {
        Player player;
        if (GameSettings.getPlayers().isEmpty()) {
            System.out.println("Первый игрок назови себя");
        } else if (GameSettings.getPlayers().size() == 1) {
            System.out.println("Второй игрок назови себя");
        }
        String playerName = getPlayerName();
        System.out.println("Насколько большой будет твой флот?");
        int fleetHealth = getFleetSize();
        player = new Player(playerName, fleetHealth);
        GameSettings.addPlayer(player);
        player.setSingleField(fieldFactory.getSingleField());
        player.setDoubleField(fieldFactory.getDoubleField());
        System.out.println("Игрок \"" + playerName + "\"" + " зарегистрирован!");
    }

    private Position getShipsSize() {
        Position position;
        while (true) {
            System.out.println("Размер кораблей?");
            position = ConsoleReader.readPos();
        }
    }

}
