package org.example;

import org.fieldFactory.FieldFactory;
import org.fieldFactory.FieldParameters;

public class Main {
    public static void main(String[] args) {
        FieldParameters fieldParameters1 =
                new FieldParameters.Builder
                        (2, 2, 6, 1)
                        .setAmendmentX(1)
                        .setAmendmentY(1)
                        .build();

        FieldFactory fieldFactory = new FieldFactory(fieldParameters1);
        fieldFactory.makeSingleField();

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