package org.FleetFactory;

import org.Utilityes.Position;

import java.util.List;

public record InfoForPrinter(String shipName, List<Position> positions, boolean condition, String massage) {}

