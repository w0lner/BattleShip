package org.fieldsFactory;

public class Field {
     private char[][] field;
     private FieldParameters fieldParameters;
     private DoubleFieldParameters doubleFieldParameters;

    public Field(char[][] field, FieldParameters fieldParameters) {
        this.field = field;
        this.fieldParameters = fieldParameters;
    }

    public Field(char[][] field, FieldParameters fieldParameters, DoubleFieldParameters doubleFieldParameters) {
        this.field = field;
        this.fieldParameters = fieldParameters;
        this.doubleFieldParameters = doubleFieldParameters;
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    public FieldParameters getFieldParameters() {
        return fieldParameters;
    }

    public void setFieldParameters(FieldParameters fieldParameters) {
        this.fieldParameters = fieldParameters;
    }

    public DoubleFieldParameters getDoubleFieldParameters() {
        return doubleFieldParameters;
    }

    public void setDoubleFieldParameters(DoubleFieldParameters doubleFieldParameters) {
        this.doubleFieldParameters = doubleFieldParameters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] string : field) {
            for (char ch : string) {
                sb.append(ch);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
