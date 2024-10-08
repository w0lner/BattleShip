package org.fieldFabric;

public class FieldParameters {
    protected char[][] field;
    protected int countX;
    protected int countY;
    protected int lengthX;
    protected int lengthY;
    protected int fieldX;
    protected int fieldY;
    protected int totalX;
    protected int totalY;
    protected int amendmentX;
    protected int amendmentY;
    protected int digitY;
    protected int digitX;
    protected int cellCount;

    public FieldParameters(int countX, int countY, int lengthX, int lengthY, int amendmentX, int amendmentY) {
        this.countX = countX - 1;
        this.countY = countY;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.amendmentX = amendmentX;
        this.amendmentY = amendmentY;
        countUp();
        this.field = new char[this.totalY][this.totalX];
    }

    private void countUp () {
        countUpField();
        countUpDigit();
        countUpAmendment();
        countUpTotal();
    }

    private  void countUpCellCount() {
        this.cellCount = countX * countY;
    }

    private void countUpField() {
        this.fieldY = (lengthY * countY) + countY + 1;
        this.fieldX = lengthX + ((countX * lengthX) - countX);
    }

    private void countUpTotal() {
        this.totalX = fieldX + amendmentX;
        this.totalY = fieldY + amendmentY;
    }

    private void countUpDigit() {
        this.digitY = 1;
        this.digitX = String.valueOf(countY).length();
    }

    private void countUpAmendment() {
        this.amendmentY = this.amendmentY + digitY;
        this.amendmentX = this.amendmentX + digitX;
    }

    char[][] getField() {
        return field;
    }
}
