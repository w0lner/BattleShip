package org.GameMaster;

import org.FleetFactory.FleetFactory;
import org.Utilityes.ConsoleReader;
import org.Utilityes.Enumerator;
import org.fieldsFactory.FieldFactory;
import org.shipsFactory.ShipPrinter;

import static org.GameMaster.GameMasterTools.*;

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
        game.start();
    }

    private void gameSetup() {
        setGameMode();
        setFieldParam();
        registrationParticipants();
        shipPlacement();
    }

    private void shipPlacement() {
        for (Player p : GameSettings.getPlayers()) {
            arrangement(p);
        }
    }

    private void registrationParticipants() {
        if (gameSettings.getGameMode().equals("pvp")) {
            playerRegistration();
            playerRegistration();
            GameTranslator.clearConsole();
        } else if (gameSettings.getGameMode().equals("pve")){
            playerRegistration();
            botRegistration();
            GameTranslator.clearConsole();
        }
    }

    private void setFieldParam() {
        System.out.println("Размер поля ? x(n) на y(n) клеток");
        gameSettings.setFieldSize(getFieldSize());
        System.out.println("Масштаб поля ? small/medium/large/custom");
        gameSettings.setPreparedFieldForm(getFieldScale());
        gameSettings.setFieldParam();
        shipPrinter = new ShipPrinter(gameSettings.getFieldParameters(), gameSettings.getDoubleFieldParameters());
        fieldFactory = new FieldFactory(gameSettings.getFieldParameters(), gameSettings.getDoubleFieldParameters());
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
                break;
            }
            System.out.println("Настройка введена неверно!");
        }
    }

    private void arrangement(Player player) {
        System.out.println("\nИгрок: \"" + player.getPlayerName() + "\" расставляет свои корабли");
        System.out.println("Кординаты вводятся по одной в формате \"x y\" через пробел цифра/цифра или буква/цифра");
        FleetFactory fleetFactory = new FleetFactory(player, gameSettings, shipPrinter);
        fleetFactory.createFleet();
        GameTranslator.clearConsole();
    }

    public void playerRegistration() {
        //Перый, Второй , Третий и.т.д
        String playerNumber = Enumerator.getEnum(gameSettings.getPlayersList().size() + 1);
        System.out.println(playerNumber + " игрок назови себя:");
        String playerName = getPlayerName(gameSettings.getPlayersList());
        System.out.println("Насколько большой будет твой флот?");
        int fleetHealth = getFleetSize();
        Player player = new Player(playerName, fleetHealth);
        GameSettings.addPlayer(player);
        player.setSingleField(fieldFactory.getSingleField());
        player.setDoubleField(fieldFactory.getDoubleField());
        System.out.println("Игрок \"" + playerName + "\"" + " зарегистрирован!");
    }

    public void botRegistration() {
        String botName = "bot";
        int botNumber = 0;
        while (true) {
            if (!checkName(gameSettings.getPlayersList(), botName)) {
                botNumber++;
                botName = "bot_" + botNumber;
            } else {
                break;
            }
        }
        System.out.println("Выбери размер кораблей у " + botName);
        int fleetSize = getFleetSize();
        Player bot = new Player(botName, fleetSize);
        GameSettings.addPlayer(bot);
        bot.setSingleField(fieldFactory.getSingleField());
        bot.setDoubleField(fieldFactory.getDoubleField());
        System.out.println("Бот \"" + botName + "\"" + " создан!");
    }
}
