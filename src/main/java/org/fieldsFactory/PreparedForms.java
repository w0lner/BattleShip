package org.fieldsFactory;

import org.Utilityes.ConsoleReader;
import org.Utilityes.Position;

public enum PreparedForms {
    Small, Medium, Large, Custom;

    public FieldParameters get(int countX, int countY, PreparedForms preparedForm) {
        switch (preparedForm) {
            case Small -> {
                return small(countX, countY);
            }
            case Medium -> {
                return medium(countX, countY);
            }
            case Large -> {
                return large(countX, countY);
            }
            case Custom -> {
                return custom(countX, countY);
            }
        }
        return null;
    }

    private FieldParameters small(int countX, int countY) {
        return new FieldParameters.Builder(countX, countY, 4, 1).build();
    }

    private FieldParameters medium(int countX, int countY) {
        return new FieldParameters.Builder(countX, countY, 6, 1).build();
    }

    private FieldParameters large(int countX, int countY) {
        return new FieldParameters.Builder(countX, countY, 10, 2).build();
    }

    private FieldParameters custom(int countX, int countY) {
        Position position;
        System.out.println("Введи масштаб X(n) и Y(n)");
        System.out.println("Рекомендуется от 4 1 до 10 2");
        while (true) {
            position = ConsoleReader.readPos();
            if (position == null) {
                System.out.println("Попробуй ещё раз");
                continue;
            }
            break;
        }
        return new FieldParameters.Builder(countX, countY, position.x(), position.y()).build();
    }
}
