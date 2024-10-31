package org.GameMaster;

public class Turn {
    private int moveCounter = -1;
    private final int[] moveOrder;


    public Turn(int playersNumber) {
        moveOrder = new int[playersNumber];
        fillMoverOrder();
        shuffleMovesOrder();
    }

    private void fillMoverOrder() {
        for (int i = 0; i < moveOrder.length; i++) {
            moveOrder[i] = i + 1;
        }
    }

    private void shuffleMovesOrder() {
        int temp;
        int cube;
        for (int i = moveOrder.length - 1; i > 0; i --) {
            temp = moveOrder[i];
            cube = Cube.getRandom(0, i);
            moveOrder[i] = moveOrder[cube];
            moveOrder[cube] = temp;
        }
    }

    public int playerNumber() {
        if (moveCounter + 1 == moveOrder.length) {
            moveCounter = -1;
        }
        moveCounter++;
        return moveOrder[moveCounter];
    }

    public int playerNumberAgainst() {
        if (moveCounter + 1 == moveOrder.length) {
            return moveOrder[0];
        }
        return moveOrder[moveCounter + 1];
    }
}
