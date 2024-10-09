package org.fieldFactory;

public class DoubleFieldParameters {
    //Параметры одиночного поля
    private FieldParameters fieldParameters;
    int totalX;
    int amendmentX;
    int fieldX;
    //Параметры двойного поля
    int marginSpacing = 3;
    char marginSpacingChar = '*';
    int startSecondField;
    int doubleTotalX;

    public DoubleFieldParameters(FieldParameters fieldParameters) {
        this.fieldParameters = fieldParameters;
        this.totalX = fieldParameters.getTotalX();
        this.amendmentX = fieldParameters.amendmentX;
        countUp();
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
}
