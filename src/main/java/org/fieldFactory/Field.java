package org.fieldFactory;

public class Field {
     private char[][] field;
     private FieldParameters fieldParameters;

    public Field(char[][] field, FieldParameters fieldParameters) {
        this.field = field;
        this.fieldParameters = fieldParameters;
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
