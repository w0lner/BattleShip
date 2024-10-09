package org.fieldFactory;

import lombok.experimental.Delegate;

public class FieldMarker {
    private char[][] field;
    @Delegate
    private FieldParameters fieldParameters;

    FieldMarker(char[][] field, FieldParameters fieldParameters) {
        this.field = field;
        this.fieldParameters = fieldParameters;
    }

    public void printMarkupY() {
        int[][] coordinatesY = getCoordinatesMarkupY();
        for (int i = 1; i <= coordinatesY.length; i++) {
            if (i < 10) {
                field[coordinatesY[i - 1][0]][coordinatesY[i - 1][1]] = (char) ((i) + '0');
            } else {
                char first = (char) ((i / 10) + '0');
                char second = (char) ((i % 10) + '0');
                field[coordinatesY[i - 1][0]][coordinatesY[i - 1][1] - 1] = first;
                field[coordinatesY[i - 1][0]][coordinatesY[i - 1][1]] = second;

            }
        }
    }

    public void printMarkupX() {
        int[][] coordinatesX = getCoordinatesMarkupX();
        for (int i = 0; i < coordinatesX.length; i++) {
            field[coordinatesX[i][0]][coordinatesX[i][1]] = RuCharacters.letters[i];
        }
    }

    public int[][] getCoordinatesMarkupY() {
        int periodicity = fieldY / countY;
        int[][] coordinatesY = new int[countY][2];
        for (int number = 0; number < countY; number++) {
            coordinatesY[number][0] = (periodicity * (number + 1)) + amendmentY - (lengthY / 2 + 1);
            coordinatesY[number][1] = amendmentX - 1;
        }
        return coordinatesY;
    }

    public int[][] getCoordinatesMarkupX() {
        int periodicity = fieldX / (countX + 1);
        int[][] coordinatesX = new  int[countX + 1][2];
        for (int number = 0; number < countX + 1; number++) {
            coordinatesX[number][0] = amendmentY - digitY;
            coordinatesX[number][1] = (periodicity * (number + 1)) + amendmentX - (lengthX / 2);
        }
        return coordinatesX;
    }

    public void markSingleField() {

    }
}
