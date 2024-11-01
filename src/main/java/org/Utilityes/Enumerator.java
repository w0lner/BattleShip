package org.Utilityes;

import java.util.ArrayList;
import java.util.List;

public class Enumerator {
    private static final List<String> enumeration;

    static  {
        enumeration = new ArrayList<>();
        enumeration.add("Первый");
        enumeration.add("Второй");
        enumeration.add("Третий");
        enumeration.add("Четвёртый");
        enumeration.add("Пятый");
    }

    public static String getEnum(int i) {
        if (check(i)) {
            return enumeration.get(i - 1);
        } else {
            return null;
        }
    }

    private static boolean check(int i) {
        return i >= 0 && i <= enumeration.size() + 1;
    }
}
