package org.example;

import lombok.experimental.Delegate;
import org.fieldFactory.FieldParameters;
import org.fieldFactory.Field;

public class Ships {
    private Field field;
    @Delegate
    private FieldParameters fieldParameters;
    private int shipsWidth;
    private int shipsHeight;
    private char charX = 'X';
    private char charo = 'о';

    Ships(Field field) {
        this.field = field;
        this.fieldParameters = field.getFieldParameters();
        countUp();
    }

    void countUp() {
        countUpShipsHeight();
        countUpShipsWidth();
    }

    void countUpShipsWidth() {
        if (getLengthX() % 2 == 0) {
            shipsWidth = 2;
        } else {
            shipsWidth = 1;
        }
    }

    void countUpShipsHeight() {
        if (getLengthY() % 2 == 0) {
            shipsHeight = 2;
        } else {
            shipsHeight = 1;
        }
    }

    void printShoot(int cellY, int cellX) {
        int y = getAmendmentY() - (getLengthY() / 2) + getLengthY() * cellY;
        int x = getAmendmentX() - 1 - (getLengthX() / 2) + getLengthX() * cellX;
        int errorY = -1 + cellY;
        int errorX = -1 + cellX;
        for (int h = 0; h < shipsHeight; h++) {
            for (int w = 0; w < shipsWidth; w++) {
                field.getField()[y + h + errorY][x + w - errorX] = charX;
            }
        }
    }

    void printShip(int cellY, int cellX) {
        int y = getAmendmentY() + ((cellY - 1) * getLengthY());
        int x = getAmendmentX() + ((cellX - 1) * getLengthX());
        int errorY = cellY - 1;
        int errorX = cellX - 1;

        for (int h = y; h < getLengthY() + 2 + y; h++) {
            for (int w = x; w < getLengthX() + x; w++) {
                field.getField()[h + errorY][w - errorX] = charo;
            }
        }
    }
}
