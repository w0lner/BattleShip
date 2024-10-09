package org.example;

import org.fieldFactory.FieldFactory;

public class Ships {
    char[][] field;
    int cellCount;
    int shipsWidth;
    int shipsHeight;
    int countX;
    int countY;
    int amendmentX;
    int amendmentY;
    int lengthX;
    int lengthY;

    Ships(FieldFactory fieldFactory) {
        this.field = fieldFactory.field;
        this.lengthX = fieldFactory.lengthX;
        this.lengthY = fieldFactory.lengthY;
        this.amendmentX = fieldFactory.amendmentX;
        this.amendmentY = fieldFactory.amendmentY;
        this.cellCount = fieldFactory.cellCount;
        this.countX = fieldFactory.countX;
        this.countY = fieldFactory.countY;
        countUpShipsWidth();
        countUpShipsHeight();
    }

    void countUpShipsWidth() {
        if (lengthX % 2 == 0) {
            shipsWidth = 2;
        } else {
            shipsWidth = 1;
        }
    }

    void countUpShipsHeight() {
        if (lengthY % 2 == 0) {
            shipsHeight = 2;
        } else {
            shipsHeight = 1;
        }
    }

    void printShoot(int cellY, int cellX, char ch) {
        int y = amendmentY - (lengthY / 2) + lengthY * cellY;
        int x = amendmentX - 1 - (lengthX / 2) + lengthX * cellX;
        int errorY = -1 + cellY;
        int errorX = -1 + cellX;
        for (int h = 0; h < shipsHeight; h++) {
            for (int w = 0; w < shipsWidth; w++) {
                field[y + h + errorY][x + w - errorX] = ch;
            }
        }
    }

    void printShip(int cellY, int cellX, char ch) {

        int y = amendmentY + ((cellY - 1) * lengthY);
        int x = amendmentX + ((cellX - 1) * lengthX);
        int errorY = cellY - 1;
        int errorX = cellX - 1;

        for (int h = y; h < lengthY + 2 + y; h++) {
            for (int w = x; w < lengthX + x; w++) {
                field[h + errorY][w - errorX] = ch;
            }
        }
    }
}
