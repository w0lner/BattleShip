package org.fieldFactory;

import lombok.experimental.Delegate;

public class FieldPrinter {

    @Delegate
    private FieldParameters fieldParameters;

    public FieldPrinter(FieldParameters fieldParameters) {
        this.fieldParameters = fieldParameters;
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
}
