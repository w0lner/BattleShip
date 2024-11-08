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

//    public void inspectShip(List<Utilityes.Position> positionList) {
//        int decksNumber = positionList.size() - 1;
//        for (int i = 1; i < positionList.size(); i++) {
//            Utilityes.Position p2 = positionList.get(i);
//            Utilityes.Position p = positionList.get(i - 1);
//            Map<Utilityes.Position, Utilityes.Direction> ways = Utilityes.PathMaker.makeRightPath(p, decksNumber);
//            if (ways.containsKey(p2)) {
//                System.out.println("Палуба " + p2 + " расположилась " + ways.get(p2));
//            } else {
//                Map<Utilityes.Position, Utilityes.Direction> wrongWays = Utilityes.PathMaker.makeSidePath(p, decksNumber);
//                if (wrongWays.containsKey(p2)) {
//                    System.out.println("Палуба " + p2 + " расположилась боком " + wrongWays.get(p2));
//                } else {
//                    System.out.println("Палуба " + p2 + " находится отдельно от корабля!");
//                }
//            }
//            decksNumber--;
//        }
//    }

//PathMaker

//    public static Map<Utilityes.Position, Utilityes.Direction> makeRightPath(Utilityes.Position position, int decksNumber) {
//        int x = position.x();
//        int y = position.y();
//        return IntStream.range(1, decksNumber + 1)
//                .mapToObj(i -> Stream.of(
//                        new AbstractMap.SimpleEntry<>(
//                                new Utilityes.Position(x - i, y), Utilityes.Direction.LEFT),
//                        new AbstractMap.SimpleEntry<>(
//                                new Utilityes.Position(x + i, y), Utilityes.Direction.RIGHT),
//                        new AbstractMap.SimpleEntry<>(
//                                new Utilityes.Position(x, y - i), Utilityes.Direction.TOP),
//                        new AbstractMap.SimpleEntry<>(
//                                new Utilityes.Position(x, y + i), Utilityes.Direction.DOWN)
//                ))
//                .flatMap(p -> p)
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue
//                ));
//    }

//    public static Map<Utilityes.Position, Utilityes.Direction> makeWrongPath(Utilityes.Position position, int decksNumber) {
//        int x = position.x();
//        int y = position.y();
//        return IntStream.range(1, decksNumber + 1)
//                .mapToObj(i -> Stream.of(
//                        new AbstractMap.SimpleEntry<>(
//                                new Utilityes.Position(x - i, y - i), Utilityes.Direction.TOP_LEFT),
//                        new AbstractMap.SimpleEntry<>(
//                                new Utilityes.Position(x + i, y - i), Utilityes.Direction.TOP_RIGHT),
//                        new AbstractMap.SimpleEntry<>(
//                                new Utilityes.Position(x - i, y + i), Utilityes.Direction.DOWN_LEFT),
//                        new AbstractMap.SimpleEntry<>(
//                                new Utilityes.Position(x + i, y + i), Utilityes.Direction.DOWN_RIGHT)
//                ))
//                .flatMap(p -> p)
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue
//                ));
//    }