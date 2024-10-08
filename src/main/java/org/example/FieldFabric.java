package org.example;

public class FieldFabric extends FieldParameters {

    public FieldFabric(int countX, int countY, int lengthX, int lengthY, int amendmentX, int amendmentY) {
        super(countX, countY, lengthX, lengthY, amendmentX, amendmentY);
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

    public char[] makeBorders() {
        char[] borders = new char[totalX];
        int error = -1;
        for (int a = 0; a < amendmentX; a++) {
            borders[a] = ' ';
        }
        for (int w = 1 + amendmentX; w <= totalX; w++) {
            if (w == (1 + amendmentX) || (w + error - amendmentX) % lengthX == 0) {
                borders[w - 1] = '+';
                error++;
            } else {
                borders[w - 1] = '-';
            }
        }
        return borders;
    }

    public char[] makeSpaces() {
        char[] spaces = new char[totalX];
        int error = -1;
        for (int a = 0; a < amendmentX; a++) {
            spaces[a] = ' ';
        }
        for (int w = 1 + amendmentX; w <= totalX; w++) {
            if (w == 1 || (w + error - amendmentX) % lengthX == 0) {
                spaces[w - 1] = '|';
                error++;
            } else {
                spaces[w - 1] = ' ';
            }
        }
        return spaces;
    }

    public char[] printer(char spase, char filling, char innerSpase) {
        char[] spaces = new char[totalX];
        int error = -1;
        for (int a = 0; a < amendmentX; a++) {
            spaces[a] = spase;
        }
        for (int w = 1 + amendmentX; w <= totalX; w++) {
            if (w == 1 || (w + error - amendmentX) % lengthX == 0) {
                spaces[w - 1] = filling;
                error++;
            } else {
                spaces[w - 1] = innerSpase;
            }
        }
        return spaces;
    }

    public void makeField() {
        for (int a1 = 0; a1 < amendmentY; a1++) {
            for (int a2 = 0; a2 < totalX; a2++) {
                field[a1][a2] = ' ';
            }
        }

        for (int h = amendmentY; h < fieldY + amendmentY; h++) {
            field[h] =  makeBorders();
            for (int s = 0; s < lengthY; s++) {
                h++;
                if (h + 1 <= totalY) {
                    field[h] = makeSpaces();
                }
            }
        }
    }

    public void makeDoubleFiledV2() {
        DoubleFieldParameters doubleFieldParameters = new DoubleFieldParameters(this);
        char[][] doubleField = doubleFieldParameters.doubleField;

    }

    public void makeDoubleField() {
        DoubleFieldParameters doubleFieldParameters = new DoubleFieldParameters(this);
        char[][] doubleField = doubleFieldParameters.doubleField;

        for (int a1 = 0; a1 < amendmentY; a1++) {
            for (int a2 = 0; a2 < doubleFieldParameters.doubleTotalX; a2++) {
                doubleField[a1][a2] = ' ';
            }
        }
        int startSecondField = doubleFieldParameters.startSecondField;
        char[] secondBorders = makeBorders();
        char[] secondSpaces = makeSpaces();

        for (int h = amendmentY; h < fieldY + amendmentY; h++) {
            doubleField[h] = makeBorders();

            for (int i = amendmentX + 1 + fieldX; i < startSecondField; i++) {
                doubleField[h][i] = '#';
                if (i + 1 >= startSecondField) {
                    System.arraycopy(secondBorders,0,doubleField[h],i + 1, secondBorders.length);
                }
            }
            for (int s = 0; s < lengthY; s++) {
                h++;
                if (h + 1 <= totalY) {
                    doubleField[h] = makeSpaces();
                    for (int i = amendmentX + 1 + fieldX; i < startSecondField; i++) {
                        doubleField[h][i] = '#';
                        if (i + 1 >= startSecondField) {
                            System.arraycopy(secondSpaces,0,doubleField[h],i + 1, secondBorders.length);
                        }
                    }
                }
            }
        }
    }

    public void printField() {
        for (char[] string : field) {
            for (char ch : string) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }

    public void printDoubleField(char[][] doubleField) {
        for (char[] string : doubleField) {
            for (char ch : string) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }


}
