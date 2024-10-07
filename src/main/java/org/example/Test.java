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