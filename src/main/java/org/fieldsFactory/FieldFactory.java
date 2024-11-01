package org.fieldsFactory;
import lombok.Getter;

public class FieldFactory {
    @Getter
    private final FieldParameters fieldParameters;
    @Getter
    private final DoubleFieldParameters doubleFieldParameters;
    private final FieldPrinter fieldPrinter;
    private final FieldMarker fieldMarker;

    public FieldFactory(FieldParameters fieldParameters, DoubleFieldParameters doubleFieldParameters) {
        this.fieldParameters = fieldParameters;
        this.doubleFieldParameters = doubleFieldParameters;
        fieldMarker = new FieldMarker(fieldParameters, doubleFieldParameters);
        fieldPrinter = new FieldPrinter(fieldParameters, doubleFieldParameters);
    }

    public Field getSingleField() {
        Field field = new Field(fieldPrinter.printSingleField());
        fieldMarker.markSingleField(field);
        return field;
    }

    public Field getDoubleField() {
        Field field = new Field(fieldPrinter.printDoubleField());
        fieldMarker.markDoubleField(field);
        return field;
    }

}
