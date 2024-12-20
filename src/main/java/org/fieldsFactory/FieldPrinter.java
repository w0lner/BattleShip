package org.fieldsFactory;

import lombok.experimental.Delegate;

public class FieldPrinter {

    @Delegate
    private FieldParameters fieldParameters;
    @Delegate
    private DoubleFieldParameters doubleFieldParameters;

    public FieldPrinter(FieldParameters fieldParameters, DoubleFieldParameters doubleFieldParameters) {
        this.fieldParameters = fieldParameters;
        this.doubleFieldParameters = doubleFieldParameters;
    }

    public char[] printBorders() {
        char[] borders = new char[getTotalX()];
        int error = -1;
        for (int a = 0; a < getAmendmentX(); a++) {
            borders[a] = getSideSpace();
        }
        for (int w = 1 + getAmendmentX(); w <= getTotalX(); w++) {
            if (w == (1 + getAmendmentX()) || (w + error - getAmendmentX()) % getLengthX() == 0) {
                borders[w - 1] = getCrosshair();
                error++;
            } else {
                borders[w - 1] = getHorizontalStick();
            }
        }
        return borders;
    }

    public char[] printSpaces() {
        char[] spaces = new char[getTotalX()];
        int error = -1;
        for (int a = 0; a < getAmendmentX(); a++) {
            spaces[a] = getSideSpace();
        }
        for (int w = 1 + getAmendmentX(); w <= getTotalX(); w++) {
            if (w == 1 || (w + error - getAmendmentX()) % getLengthX() == 0) {
                spaces[w - 1] = getVerticalStick();
                error++;
            } else {
                spaces[w - 1] = getInnerSpace();
            }
        }
        return spaces;
    }

    private void doubleStringPrinter(char[][] doubleField, char[] charString, int h) {
        System.arraycopy(charString,0,doubleField[h], 0, charString.length);
        for (int i = getTotalX(); i < getStartSecondField(); i++) {
            doubleField[h][i] = getMarginSpacingChar();
        }
        System.arraycopy(charString,getAmendmentX() - getDigitX(), doubleField[h],
                getStartSecondField(), charString.length - getAmendmentX() + getDigitX());
    }

    public char[][] printSingleField() {
        char[][] singleField = new char[getTotalY()][getTotalX()];
        char[] borders = printBorders();
        char[] spaces = printSpaces();

        for (int a1 = 0; a1 < getAmendmentX(); a1++) {
            for (int a2 = 0; a2 < getTotalX(); a2++) {
                singleField[a1][a2] = getTopSpace();
            }
        }

        for (int h = getAmendmentY(); h < getFieldY() + getAmendmentY(); h++) {
            System.arraycopy(borders, 0, singleField[h], 0, borders.length);
            for (int s = 0; s < getLengthY(); s++) {
                h++;
                if (h + 1 <= getTotalY()) {
                    System.arraycopy(spaces, 0, singleField[h], 0, spaces.length);
                }
            }
        }
        return singleField;
    }

    public char[][] printDoubleField() {
        char[][] doubleField = new char[getTotalY()][getDoubleTotalX()];
        //Пишем отступ сверху
        for (int a1 = 0; a1 < getAmendmentY(); a1++) {
            for (int a2 = 0; a2 < getDoubleTotalX(); a2++) {
                doubleField[a1][a2] = getTopSpace();
            }
        }
        //Пишем поле
        char[] borders = printBorders();
        char[] spaces = printSpaces();

        for (int h = getAmendmentY(); h < getFieldY() + getAmendmentY(); h++) {
            //Пишем бордюры
            doubleStringPrinter(doubleField, borders, h);
            for (int s = 0; s < getLengthY(); s++) {
                h++;
                if (h + 1 <= getTotalY()) {
                    //Пишем пробелы
                    doubleStringPrinter(doubleField, spaces, h);
                }
            }
        }
        return doubleField;
    }
}
