import org.FleetFactory.InfoForPrinter;
import org.GameMaster.GameMaster;
import org.GameMaster.Turn;
import org.Utilityes.Position;
import org.observer.Listener;
import org.observer.Source;

import java.util.ArrayList;
import java.util.List;

public static void main(String[] args) {
    GameMaster gameMaster = new GameMaster();
    gameMaster.run();

//    Turn turn = new Turn(3);
//    for (int i = 0; i < 10; i++) {
//        System.out.println("Ход игрока: " + turn.playerNumber());
//        System.out.println("против игрока: " + turn.playerNumberAgainst());
//        System.out.println();
//    }
}
