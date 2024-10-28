package org.shipsFactory;

import java.util.LinkedList;

public class Ship {
    private final String name;
    private int decksCount;
    private int[][] decksCoordinate;
    private LinkedList<Deck> decks;
    private boolean condition = true;

    private Ship(Builder builder) {
        this.name = builder.name;
        this.decksCount = builder.decksCount;
        this.decks = builder.decks;

    }
    public static class Builder {
        private String name;
        private int decksCount;
        private LinkedList<Deck> decks;
        private int[][] coordinates;

        public Builder(int[][] coordinates) {
            this.coordinates = coordinates;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Ship build() {
            countDecks();
            createDecks();
            return new Ship(this);
        }

        private void countDecks() {
            this.decksCount = coordinates.length;
        }

        private void createDecks() {
            for (int i = 0; i < decksCount; i++) {
                this.decks.add(new Deck(coordinates[i][0], coordinates[i][1]));
            }
        }
    }
}
