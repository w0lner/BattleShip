package org.fieldsFactory;

import lombok.Setter;

public class FieldFactory {

    private final FieldParameters fieldParameters;
    @Setter
    private DoubleFieldParameters doubleFieldParameters;

    public FieldFactory(FieldParameters fieldParameters) {
        this.fieldParameters = fieldParameters;
    }

    public Field makeSingleField() {
        return new Field(new FieldPrinter(fieldParameters).printSingleField(), fieldParameters);
    }

    public Field markSingleField(Field field) {
        return new FieldMarker(field).markSingleField();
    }

    public Field markDoubleField(Field field) {
        return new FieldMarker(field, doubleFieldParameters).markDoubleField();
    }

    public Field makeDoubleField() {
        return new Field(new FieldPrinter
                (fieldParameters, doubleFieldParameters).printDoubleField(),fieldParameters, doubleFieldParameters);
    }

//    public char[][] makeDoubleField() {
//        DoubleFieldParameters doubleFieldParameters = new DoubleFieldParameters(this);
//        char[][] doubleField = doubleFieldParameters.doubleField;
//
//        //Пишем отступ сверху
//        for (int a1 = 0; a1 < amendmentY; a1++) {
//            for (int a2 = 0; a2 < doubleFieldParameters.doubleTotalX; a2++) {
//                doubleField[a1][a2] = '-';
//            }
//        }
//
//        //Пишем поле
//        int startSecondField = doubleFieldParameters.startSecondField;
//        char[] spaces = makeSpaces();
//        char[] borders = makeBorders();
//
//        for (int h = amendmentY; h < fieldY + amendmentY; h++) {
//            //Пишем бордюры
//            doubleFieldPrinter(doubleFieldParameters.marginSpacingChar, doubleField,
//                    startSecondField, borders, h);
//            for (int s = 0; s < lengthY; s++) {
//                h++;
//                if (h + 1 <= totalY) {
//                    //Пишем пробелы
//                    doubleFieldPrinter(doubleFieldParameters.marginSpacingChar, doubleField,
//                            startSecondField, spaces, h);
//                }
//            }
//        }
//        return doubleField;
//    }
//
//    private void doubleFieldPrinter(char marginSpacingChar, char[][] doubleField,
//                                    int startSecondField, char[] charsString, int h) {
//
//        System.arraycopy(charsString,0,doubleField[h], 0, charsString.length);
//
//        for (int i = totalX; i < startSecondField; i++) {
//            doubleField[h][i] = marginSpacingChar;
//        }
//        System.arraycopy(charsString,amendmentX - digitX,doubleField[h],
//                startSecondField, charsString.length - amendmentX + digitX);
//    }

//    public void printField() {
//        for (char[] string : field) {
//            for (char ch : string) {
//                System.out.print(ch);
//            }
//            System.out.println();
//        }
//    }

}
