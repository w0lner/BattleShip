package org.example;

public class Main {
    public static void main(String[] args) {
        FieldFabric field = new FieldFabric(10,10,6,2,0,0);
        DoubleFieldParameters doubleFieldParameters = new DoubleFieldParameters(field);
        field.makeDoubleField();
        field.printDoubleField();



//        field.makeField();
//        field.printMarkupY();
//        field.printMarkupX();
//        Ships ships = new Ships(field);
//        ships.printShoot(1,7, 'о');
//        ships.printShoot(1, 5, 'X');
//        ships.printShip(1, 3,'X');
//        ships.printShip(2, 3, 'X');
//        ships.printShip(4, 5, 'о');
//        ships.printShip(3,5, 'о');
//        field.printField();
    }
}