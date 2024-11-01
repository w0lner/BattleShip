package org.example;//Устаревшее
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

//    public char[][] makeDoubleField() {
//        DoubleFieldParameters doubleFieldParameters = new DoubleFieldParameters(this);
//        char[][] doubleField = doubleFieldParameters.doubleField;
//
//        //Пишем отступ сверху
//        for (int a1 = 0; a1 < amendmentY; a1++) {
//            for (int a2 = 0; a2 < doubleFieldParameters.doubleTotalX; a2++) {
//                doubleField[a1][a2] = '-';
//            }
//        }
//
//        //Пишем поле
//        int startSecondField = doubleFieldParameters.startSecondField;
//        char[] spaces = makeSpaces();
//        char[] borders = makeBorders();
//
//        for (int h = amendmentY; h < fieldY + amendmentY; h++) {
//            //Пишем бордюры
//            doubleFieldPrinter(doubleFieldParameters.marginSpacingChar, doubleField,
//                    startSecondField, borders, h);
//            for (int s = 0; s < lengthY; s++) {
//                h++;
//                if (h + 1 <= totalY) {
//                    //Пишем пробелы
//                    doubleFieldPrinter(doubleFieldParameters.marginSpacingChar, doubleField,
//                            startSecondField, spaces, h);
//                }
//            }
//        }
//        return doubleField;
//    }
//
//    private void doubleFieldPrinter(char marginSpacingChar, char[][] doubleField,
//                                    int startSecondField, char[] charsString, int h) {
//
//        System.arraycopy(charsString,0,doubleField[h], 0, charsString.length);
//
//        for (int i = totalX; i < startSecondField; i++) {
//            doubleField[h][i] = marginSpacingChar;
//        }
//        System.arraycopy(charsString,amendmentX - digitX,doubleField[h],
//                startSecondField, charsString.length - amendmentX + digitX);
//    }

//    public void printField() {
//        for (char[] string : field) {
//            for (char ch : string) {
//                System.out.print(ch);
//            }
//            System.out.println();
//        }
//    }
