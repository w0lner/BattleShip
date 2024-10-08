package org.fieldFabric;

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
        countUpDoubleTotalX();
        countUpStartSecondField();
        this.doubleField = new char[fieldParameters.totalY][doubleTotalX];
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
