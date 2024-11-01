package org.observer;

import java.util.ArrayList;
import java.util.List;

public class Source {
    private final List<Listener> listeners = new ArrayList<>();

    public void add(Listener listener) {
        listeners.add(listener);
    }

    public void remove(Listener listener) {
        listeners.remove(listener);
    }

    public void setNews(String message) {
        for (Listener listener : listeners) {
            listener.update(message);
        }
    }
}
