package org.example;

import org.fieldFactory.Field;
import org.fieldFactory.FieldFactory;
import org.fieldFactory.FieldParameters;
import org.fieldFactory.FieldPrinter;

public class Main {
    public static void main(String[] args) {

        FieldParameters fieldParameters1 =
                new FieldParameters.Builder
                        (6, 6, 5, 1)
                        .setAmendmentX(1)
                        .setAmendmentY(1)
                        .build();

        FieldFactory fieldFactory = new FieldFactory(fieldParameters1);
        Field field1 = fieldFactory.makeSingleField();
        fieldFactory.markSingleField(field1);
        Ships ships = new Ships(field1);
        ships.printShip(2,2,'о');
        ships.printShip(2,3,'о');
        ships.printShoot(2,3,'X');
        System.out.println(field1);


















//        fieldFabric.makeField();
//        fieldFabric.printMarkupY();
//        fieldFabric.printMarkupX();
//        Ships ships = new Ships(fieldFabric);
//        ships.printShoot(1,7, 'о');
//        ships.printShoot(1, 5, 'X');
//        ships.printShip(1, 3,'X');
//        ships.printShip(2, 3, 'X');
//        ships.printShip(4, 5, 'о');
//        ships.printShip(3,5, 'о');
//        fieldFabric.printField();
    }
}