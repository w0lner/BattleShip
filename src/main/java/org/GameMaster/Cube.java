package org.GameMaster;

import java.util.Random;

public class Cube {
    private final static Random random;

    static {
         random = new Random();
    }

    public static int getRandom(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
