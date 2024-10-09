package org.fieldFactory;

public class DoubleFieldParameters {
    int marginSpacing = 3;
    char marginSpacingChar = '*';
    int totalX;
    int startSecondField;

    int doubleTotalX;
    char[][] field;
    char[][] doubleField;
    int amendmentX;
    int fieldX;

    public DoubleFieldParameters(FieldParameters fieldParameters) {
        this.field = fieldParameters.field;
        this.totalX = fieldParameters.totalX;
        this.amendmentX = fieldParameters.amendmentX;
        this.fieldX = fieldParameters.fieldX;
        countUp();
        this.doubleField = new char[fieldParameters.totalY][doubleTotalX];
    }

    private void countUp(){
        countUpDoubleTotalX();
        countUpStartSecondField();
    }

    private void countUpDoubleTotalX() {
        this.doubleTotalX = totalX * 2 + marginSpacing;
    }

    private void countUpStartSecondField() {
        this.startSecondField = marginSpacing + amendmentX + fieldX;
    }

    public char[][] getDoubleField() {
        return this.doubleField;
    }
}
