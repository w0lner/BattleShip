package org.GameMaster;

import lombok.Getter;
import lombok.Setter;
import org.Utilityes.Position;
import org.fieldsFactory.DoubleFieldParameters;
import org.fieldsFactory.FieldParameters;
import org.fieldsFactory.PreparedFieldForm;

import java.util.*;

@Setter
public class GameSettings {
    @Getter
    private static final List<Player> players;
    @Getter
    private Position fieldSize;
    @Getter
    private PreparedFieldForm preparedFieldForm;
    @Getter
    private String gameMode;
    @Getter
    private int positionsLimit;
    @Getter
    private int minShipLength = 1;
    @Getter
    private int maxShipLength = 4;
    @Getter
    private FieldParameters fieldParameters;
    @Getter
    private DoubleFieldParameters doubleFieldParameters;


    static {
        players = new ArrayList<>();
    }


    public static void addPlayer(Player player) {
        players.add(player);
    }

    public void setPositionsLimit() {
        positionsLimit = fieldSize.x() * fieldSize.y();
    }

    public void setFieldSize(Position position) {
        this.fieldSize = position;
        setPositionsLimit();
    }

    public void setFieldParam() {
        this.fieldParameters = preparedFieldForm.getFieldParameters(fieldSize.x(), fieldSize.y(), preparedFieldForm);
        setDoubleFieldParam();
    }

    private void setDoubleFieldParam() {
        this.doubleFieldParameters = new  DoubleFieldParameters.Builder(fieldParameters).build();
    }

    public List<Player> getPlayersList() {
        return players;
    }

}
