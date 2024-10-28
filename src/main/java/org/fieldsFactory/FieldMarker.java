package org.fieldsFactory;
import org.Utilityes.RuCharacters;
import lombok.experimental.Delegate;

public class FieldMarker {
    private Field field;
    @Delegate
    private FieldParameters fieldParameters;
    @Delegate
    private DoubleFieldParameters doubleFieldParameters;

    FieldMarker(Field field) {
        this.field = field;
        this.fieldParameters = field.getFieldParameters();
    }

    FieldMarker(Field field, DoubleFieldParameters doubleFieldParameters) {
        this(field);
        this.doubleFieldParameters = doubleFieldParameters;
    }

    public void printMarkupY(int fieldNumber) {
        int[][] coordinatesY;
        if (fieldNumber == 1) {
            coordinatesY = firstCoordinatesY();
        } else if (fieldNumber == 2) {
            coordinatesY = secondCoordinatesY();
        } else {
            throw new RuntimeException("Номер поля может быть только 1 или 2");
        }

        for (int i = 1; i <= coordinatesY.length; i++) {
            if (i < 10) {
                field.getField()[coordinatesY[i - 1][0]][coordinatesY[i - 1][1]] = (char) ((i) + '0');
            } else {
                char first = (char) ((i / 10) + '0');
                char second = (char) ((i % 10) + '0');
                field.getField()[coordinatesY[i - 1][0]][coordinatesY[i - 1][1] - 1] = first;
                field.getField()[coordinatesY[i - 1][0]][coordinatesY[i - 1][1]] = second;
            }
        }
    }

    public void printMarkupX(int fieldNumber) {
        int[][] coordinatesX;
        if (fieldNumber == 1) {
            coordinatesX = firstCoordinatesX();
        } else if (fieldNumber == 2) {
            coordinatesX = secondCoordinatesX();
        } else {
            throw new RuntimeException("Номер поля может быть только 1 или 2");
        }
        for (int i = 0; i < coordinatesX.length; i++) {
            field.getField()[coordinatesX[i][0]][coordinatesX[i][1]] = RuCharacters.letters[i];
        }
    }

    public int[][] firstCoordinatesY() {
        int periodicity = getFieldY() / getCountY();
        int[][] coordinatesY = new int[getCountY()][2];
        for (int number = 0; number < getCountY(); number++) {
            coordinatesY[number][0] = (periodicity * (number + 1)) + getAmendmentY() - (getLengthY() / 2 + 1);
            coordinatesY[number][1] = getAmendmentX() - 1;
        }
        return coordinatesY;
    }

    public int[][] firstCoordinatesX() {
        int periodicity = getFieldX() / (getCountX() + 1);
        int[][] coordinatesX = new int[getCountX() + 1][2];
        for (int number = 0; number < getCountX() + 1; number++) {
            coordinatesX[number][0] = getAmendmentY() - getDigitY();
            coordinatesX[number][1] = (periodicity * (number + 1)) + getAmendmentX() - (getLengthX() / 2);
        }
        return coordinatesX;
    }

    public int[][] secondCoordinatesX() {
        int periodicity = getFieldX() / (getCountX() + 1);
        int[][] coordinatesX = new int[getCountX() + 1][2];
        for (int number = 0; number < getCountX() + 1; number++) {
            coordinatesX[number][0] = getAmendmentY() - getDigitY();
            coordinatesX[number][1] = (periodicity * (number + 1)) +
                    getAmendmentX() - (getLengthX() / 2) + getStartSecondField() - getAmendmentX() + getDigitX();
        }
        return coordinatesX;
    }

    public int[][] secondCoordinatesY() {
        int periodicity = getFieldY() / getCountY();
        int[][] coordinatesY = new int[getCountY()][2];
        for (int number = 0; number < getCountY(); number++) {
            coordinatesY[number][0] = (periodicity * (number + 1)) + getAmendmentY() - (getLengthY() / 2 + 1);
            coordinatesY[number][1] = getStartSecondField() + getDigitX() - 1;
        }
        return coordinatesY;
    }

    public Field markSingleField() {
        printMarkupY(1);
        printMarkupX(1);
        return field;
    }

    public Field markDoubleField() {
        printMarkupY(2);
        printMarkupX(2);
        return markSingleField();
    }
}
