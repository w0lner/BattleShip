package org.shipsFactory;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Deck {
    private final int x;
    private final int y;
    @Setter
    @Getter
    private boolean condition;

    public Deck(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return x == deck.x && y == deck.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
