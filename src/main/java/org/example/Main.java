package org.example;

import org.fieldsFactory.*;

public class Main {
    public static void main(String[] args) {

        FieldParameters fieldParameters1 =
                new FieldParameters.Builder
                        (10, 9, 5, 1)
                        .setAmendmentX(3)
                        .setAmendmentY(1)
                        .build();
        DoubleFieldParameters doubleFieldParameters1 =
                new DoubleFieldParameters.Builder
                        (fieldParameters1)
                        .setMarginSpacing(1)
                        .setMarginSpacingChar(' ')
                        .build();

        FieldFactory fieldFactory = new FieldFactory(fieldParameters1);
        fieldFactory.setDoubleFieldParameters(doubleFieldParameters1);
        Field field1 = fieldFactory.makeDoubleField();
        fieldFactory.markDoubleField(field1);

        System.out.println(field1);
    }
}