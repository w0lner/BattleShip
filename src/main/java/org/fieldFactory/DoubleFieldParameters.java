package org.fieldFactory;

import lombok.Builder;
import lombok.experimental.Delegate;

public class DoubleFieldParameters {
    //Параметры одиночного поля
    @Delegate
    private final FieldParameters fieldParameters;
    //Параметры двойного поля
    private final int marginSpacing;
    private final char marginSpacingChar;
    private int startSecondField;
    private int doubleTotalX;

    private DoubleFieldParameters(Builder builder) {
        this.fieldParameters = builder.fieldParameters;
        this.marginSpacing = builder.marginSpacing;
        this.marginSpacingChar = builder.marginSpacingChar;
        countUp();
    }

    public static class Builder {
        private int marginSpacing = 3;
        private char marginSpacingChar = '*';
        private final FieldParameters fieldParameters;

        public Builder(FieldParameters fieldParameters) {
            this.fieldParameters = fieldParameters;
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
            return new DoubleFieldParameters(this);
        }

    }

    private void countUp(){
        countUpDoubleTotalX();
        countUpStartSecondField();
    }

    private void countUpDoubleTotalX() {
        this.doubleTotalX = getTotalX() * 2 + marginSpacing;
    }

    private void countUpStartSecondField() {
        this.startSecondField = marginSpacing + getAmendmentX() + getFieldX();
    }

    public FieldParameters getFieldParameters() {
        return fieldParameters;
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
