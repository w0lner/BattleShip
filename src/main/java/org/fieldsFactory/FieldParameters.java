package org.fieldsFactory;

public class FieldParameters {
    protected char topSpace;
    protected char sideSpace;
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
    protected char verticalStick;
    protected char horizontalStick;
    protected char crosshair;
    protected char innerSpace;

    public static class Builder {
        private final int countX;
        private final int countY;
        private final int lengthX;
        private final int lengthY;
        private int amendmentX = 0;
        private int amendmentY = 0;
        private char verticalStick = '|';
        private char horizontalStick = '-';
        private char crosshair = '+';
        private char innerSpace = ' ';
        private char sideSpace = ' ';
        private char topSpace = ' ';

        public Builder(int countX, int countY, int lengthX, int lengthY) {
            this.countX = countX;
            this.countY = countY;
            this.lengthX = lengthX;
            this.lengthY = lengthY;
        }

        public Builder setAmendmentX(int amendmentX) {
            this.amendmentX = amendmentX;
            return this;
        }

        public Builder setAmendmentY(int amendmentY) {
            this.amendmentY = amendmentY;
            return this;
        }

        public Builder setVerticalStick(char verticalStick) {
            this.verticalStick = verticalStick;
            return this;
        }

        public Builder setHorizontalStick(char horizontalStick) {
            this.horizontalStick = horizontalStick;
            return this;
        }

        public Builder setCrosshair(char crosshair) {
            this.crosshair = crosshair;
            return this;
        }

        public Builder setInnerSpace(char innerSpace) {
            this.innerSpace = innerSpace;
            return this;
        }

        public Builder setSideSpace(char sideSpace) {
            this.sideSpace = sideSpace;
            return this;
        }

        public Builder setTopSpace(char topSpace) {
            this.topSpace = topSpace;
            return this;
        }

        public FieldParameters build() {
            return new FieldParameters(this);
        }

    }

    private FieldParameters(Builder builder) {
        this.countX = builder.countX - 1;
        this.countY = builder.countY;
        this.lengthX = builder.lengthX;
        this.lengthY = builder.lengthY;
        this.amendmentX = builder.amendmentX;
        this.amendmentY = builder.amendmentY;
        this.verticalStick = builder.verticalStick;
        this.horizontalStick = builder.horizontalStick;
        this.crosshair = builder.crosshair;
        this.innerSpace = builder.innerSpace;
        this.sideSpace = builder.sideSpace;
        this.topSpace = builder.topSpace;
        countUp();
    }

    private void countUp() {
        countUpField();
        countUpDigit();
        countUpAmendment();
        countUpTotal();
        countUpCellCount();
    }

    private void countUpCellCount() {
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


    public int getCountX() {
        return countX;
    }

    public int getCountY() {
        return countY;
    }

    public int getLengthX() {
        return lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public int getFieldX() {
        return fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }

    public int getTotalX() {
        return totalX;
    }

    public int getTotalY() {
        return totalY;
    }

    public int getAmendmentX() {
        return amendmentX;
    }

    public int getAmendmentY() {
        return amendmentY;
    }

    public int getDigitY() {
        return digitY;
    }

    public int getDigitX() {
        return digitX;
    }

    public int getCellCount() {
        return cellCount;
    }

    public char getVerticalStick() {
        return verticalStick;
    }

    public char getHorizontalStick() {
        return horizontalStick;
    }

    public char getInnerSpace() {
        return innerSpace;
    }

    public char getTopSpace() {
        return topSpace;
    }

    public char getCrosshair() {
        return crosshair;
    }

    public char getSideSpace() {
        return sideSpace;
    }
}
