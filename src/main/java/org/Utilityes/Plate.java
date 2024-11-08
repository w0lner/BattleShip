package org.Utilityes;

public enum Plate {
    X, Y, DL_TR, TL_DR;
    public static Plate findPlate(Position p1, Position p2) {
        if (p1.x() == p2.x()) {
            return X;
        }
        if (p1.y() == p2.y()) {
            return Y;
        }
        if (p1.x() > p2.x() && p1.y() < p2.y() || p1.x() < p2.x() && p1.y() > p2.y()) {
            return DL_TR;
        }
        return TL_DR;
    }
}
