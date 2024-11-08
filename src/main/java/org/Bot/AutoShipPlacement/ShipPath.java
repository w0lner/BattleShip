package org.Bot.AutoShipPlacement;


import org.Utilityes.Plate;
import org.Utilityes.Position;

import java.util.Set;

public record ShipPath(Set<Position> positions, Plate plate) {}
