package org.fieldsFactory;

public class DoubleFieldParameters {
    private final int marginSpacing;
    private final char marginSpacingChar;
    private final int startSecondField;
    private final int doubleTotalX;

    private DoubleFieldParameters(Builder builder) {
        this.startSecondField = builder.startSecondField;
        this.doubleTotalX = builder.doubleTotalX;
        this.marginSpacing = builder.marginSpacing;
        this.marginSpacingChar = builder.marginSpacingChar;
    }

    public static class Builder {
        private int startSecondField;
        private int doubleTotalX;
        private int marginSpacing = 3;
        private char marginSpacingChar = ' ';
        private final int fieldX;
        private final int totalX;
        private final int digitX;

        public Builder(FieldParameters fieldParameters) {
            this.fieldX = fieldParameters.fieldX;
            this.totalX = fieldParameters.totalX;
            this.digitX = fieldParameters.digitX;
        }

        private void countUp(){
            countUpDoubleTotalX();
            countUpStartSecondField();
        }

        private void countUpDoubleTotalX() {
            this.doubleTotalX = totalX + fieldX + marginSpacing + digitX;
        }

        private void countUpStartSecondField() {
            this.startSecondField = marginSpacing + totalX;
        }

        public Builder setMarginSpacing(int marginSpacing) {
            this.marginSpacing = marginSpacing;
            return this;
        }

        public Builder setMarginSpacingChar(char marginSpacingChar) {
            this.marginSpacingChar = marginSpacingChar;
            return this;
        }

        public DoubleFieldParameters build() {
            countUp();
            return new DoubleFieldParameters(this);
        }

    }

    public int getMarginSpacing() {
        return marginSpacing;
    }

    public char getMarginSpacingChar() {
        return marginSpacingChar;
    }

    public int getStartSecondField() {
        return startSecondField;
    }

    public int getDoubleTotalX() {
        return doubleTotalX;
    }
}
