package org.shipsFactory;

import lombok.experimental.Delegate;
import org.Utilityes.Position;
import org.fieldsFactory.DoubleFieldParameters;
import org.fieldsFactory.Field;
import org.fieldsFactory.FieldParameters;

public class ShipPrinter {
    @Delegate
    private FieldParameters fieldParameters;
    @Delegate
    private DoubleFieldParameters doubleFieldParameters;
    private int shipsWidth;
    private int shipsHeight;
    private final char charX = 'X';
    private final char charo = 'о';

    public ShipPrinter(FieldParameters fieldParameters, DoubleFieldParameters doubleFieldParameters) {
        this.fieldParameters = fieldParameters;
        this.doubleFieldParameters = doubleFieldParameters;
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

    public void printHit(Field field, int fieldNumber, Position position) {
        shootPrinter(field, fieldNumber, position, charX);
    }

    public void printMiss(Field field, int fieldNumber, Position position) {
        shootPrinter(field, fieldNumber, position, charo);
    }

    private void shootPrinter(Field field, int fieldNumber, Position position, char charo) {
        int cellX = position.x();
        int cellY = position.y();

        int y = getAmendmentY() - (getLengthY() / 2) + getLengthY() * cellY;
        int x = shootX(fieldNumber, cellX);
        int errorY = -1 + cellY;
        int errorX = -1 + cellX;
        for (int h = 0; h < shipsHeight; h++) {
            for (int w = 0; w < shipsWidth; w++) {
                field.getField()[y + h + errorY][x + w - errorX] = charo;
            }
        }
    }

    public void printShip(Field field, int fieldNumber, Position position) {
        shipPrinter(field, fieldNumber, position, charo);
    }

    public void printDestroyedShip(Field field, int fieldNumber, Position position) {
        shipPrinter(field, fieldNumber, position, charX);
    }

    private void shipPrinter(Field field, int fieldNumber, Position position, char ch) {
        int cellX = position.x();
        int cellY = position.y();
        int y = getAmendmentY() + ((cellY - 1) * getLengthY());
        int x = shipX(fieldNumber, cellX);
        int errorY = cellY - 1;
        int errorX = cellX - 1;

        for (int h = y; h < getLengthY() + 2 + y; h++) {
            for (int w = x; w < getLengthX() + x; w++) {
                field.getField()[h + errorY][w - errorX] = ch;
            }
        }
    }

    private int shootX(int fieldNumber, int cellX) {
        if (fieldNumber == 1) {
            return getAmendmentX() - 1 - (getLengthX() / 2) + getLengthX() * cellX;
        } else if (fieldNumber == 2) {
            return getAmendmentX() - 1 - (getLengthX() / 2) + getLengthX() * cellX +
                    getStartSecondField() - getAmendmentX() + getDigitX();
        } else {
            throw new RuntimeException("Номер поля может быть только 1 или 2");
        }
    }

    private int shipX(int fieldNumber, int cellX) {
        if (fieldNumber == 1) {
            return getAmendmentX() + ((cellX - 1) * getLengthX());
        } else if (fieldNumber == 2) {
            return getAmendmentX() + ((cellX - 1) * getLengthX()) +
                    getStartSecondField() - getAmendmentX() + getDigitX();
        } else {
            throw new RuntimeException("Номер поля может быть только 1 или 2");
        }
    }
}
