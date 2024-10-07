package org.example;

public class Field {
    private char[][] field;
    private int countX;
    private int countY;
    private int lengthX;
    private int lengthY;
    private int x;
    private int y;
    private int amendmentX = 5;
    private int amendmentY = 2;

    public Field(FieldParameters parameters) {
        countX = parameters.countX;
        countY = parameters.countY;
        lengthX = parameters.lengthX;
        lengthY = parameters.lengthY;
        x = parameters.fieldX;
        y = parameters.fieldY;
        makeField();
        markupY();
    }


    public char[] makeBorders() {
        char[] borders = new char[x + amendmentX];
        int error = -1;
        for (int a = 0; a < amendmentX; a++) {
            borders[a] = ' ';
        }
        for (int w = 1 + amendmentX; w <= x + amendmentX; w++) {
            if (w == (1 + amendmentX)  || (w + error - amendmentX) % lengthX == 0) {
                borders[w - 1] = '+';
                error++;
            } else {
                borders[w - 1] = '-';
            }
        }
        return borders;
    }

    public char[] makeSpaces() {
        char[] spaces = new char[x + amendmentX];
        int error = -1;
        for (int a = 0; a < amendmentX; a++) {
            spaces[a] = ' ';
        }
        for (int w = 1 + amendmentX; w <= x + amendmentX; w++) {
            if (w == 1 || (w + error - amendmentX) % lengthX == 0) {
                spaces[w - 1] = '|';
                error++;
            } else {
                spaces[w - 1] = ' ';
            }
        }
        return spaces;
    }

    public void makeField() {
        char[] space = makeSpaces();
        char[] border = makeBorders();

        char[][] field = new char[y + amendmentY][x + amendmentX];

        for (int a1 = 0; a1 < amendmentY; a1++) {
            for (int a2 = 0; a2 < x + amendmentX; a2++) {
                field[a1][a2] = ' ';
            }
        }

        for (int h = 0 + amendmentY; h < y + amendmentY; h++) {
            field[h] = border;
            for (int s = 0; s < lengthY; s++) {
                h++;
                if (h + 1 <= y + amendmentY) {
                    field[h] = space;
                }
            }
        }
        this.field = field;
    }

    public void printField() {
        if (field != null) {
            for (char[] string : field) {
                for (char ch : string) {
                    System.out.print(ch);
                }
                System.out.println();
            }
        } else {
            System.out.println("Нет поля");
        }
    }

    public void markupY() {
        int numberLength = String.valueOf(countY).length();
        for (int h = 0 + amendmentY; h < y + amendmentY; h++) {
            for (int a = 0; a < amendmentX; a++) {
                if (a == amendmentX - numberLength) {
                    if (numberLength == 1) {
                        field[h][a] = (char) ((h - amendmentY) + '0');
                    } else if (numberLength == 2){
                        field[h][a - 1] = (char) ((h - amendmentY) / 10 + '0');
                        System.out.println((char) ((h - amendmentY) / 10 + '0'));

                        field[h][a] = (char) ((h - amendmentY) % 10 + '0');
                        System.out.println((char) ((h - amendmentY) % 10 + '0'));
                    } else {
                        System.out.println("Слишком большое поле!");
                    }
                    break;
                } else {
                    field[h][a] = ' ';
                }
            }
        }
    }

    //Устаревшее
//    public static char[][] makeCube(int height, int width) {
//        char[][] cube = new char[height][width];
//        for (int h = 0; h < height; h++) {
//            if (h == 0 || (h + 1) % height == 0) {
//                cube[h] = makeBorder(width);
//            } else {
//                cube[h] = makeSpace(width);
//            }
//        }
//        return cube;
//    }

//    public static char[] makeBorder(int width) {
//        char[] border = new char[width];
//
//        for (int b = 0; b < width; b++) {
//            if (b == 0 || (b + 1) % width == 0) {
//                border[b] = '+';
//            } else {
//                border[b] = '-';
//            }
//        }
//        return border;
//    }

//    public static char[] makeSpace(int width) {
//        char[] space = new char[width];
//
//        for (int b = 0; b < width; b++) {
//            if (b == 0 || (b + 1) % width == 0) {
//                space[b] = '|';
//            } else {
//                space[b] = ' ';
//            }
//        }
//        return space;
//    }
}
