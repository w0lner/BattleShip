package org.FleetFactory;

import org.Utilityes.Position;

import java.util.List;

public record InfoForPrinter(List<Position> positions, boolean condition) {}
