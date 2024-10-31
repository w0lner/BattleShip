package org.fieldsFactory;

public class Field {
     private char[][] field;

    public Field(char[][] field) {
        this.field = field;
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
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
