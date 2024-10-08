package org.example;

public class DoubleFieldParameters {
    int marginSpacing = 2;
    int totalX;
    int startSecondField;

    int doubleTotalX;
    char[][] field;
    char[][] doubleField;
    int amendmentX;
    int fieldX;

    DoubleFieldParameters(FieldFabric fieldFabric) {
        this.field = fieldFabric.field;
        this.totalX = fieldFabric.totalX;
        this.amendmentX = fieldFabric.amendmentX;
        this.fieldX = fieldFabric.fieldX;
        countUpDoubleTotalX();
        this.doubleField = new char[fieldFabric.totalY][doubleTotalX];
    }

    private void countUpDoubleTotalX() {
        this.doubleTotalX = totalX * 2 + marginSpacing;
    }

    private void countUpStartSecondField() {
        this.startSecondField = marginSpacing +  amendmentX + 1 + fieldX;
    }

    public char[][] getDoubleField() {
        return this.doubleField;
    }
}
