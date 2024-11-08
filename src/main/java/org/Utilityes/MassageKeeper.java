package org.Utilityes;

public class MassageKeeper {
    private final StringBuilder stringBuilder;

    public MassageKeeper() {
        this.stringBuilder = new StringBuilder();
    }

    public void add(String message) {
        if (!stringBuilder.isEmpty()) {
            stringBuilder.append("\n");
        }
        this.stringBuilder.append(message);
    }

    public void print() {
        if (!(stringBuilder.isEmpty())) {
            System.out.println(stringBuilder);
        }
    }

    public void clean() {
        stringBuilder.setLength(0);
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }
}
