package org.observer;

public class Listener implements Observer{
    private final String name;
    public Listener(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.format("%s, получена новость: %s\n", name, message);
    }
}
