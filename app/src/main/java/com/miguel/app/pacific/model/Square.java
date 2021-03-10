package com.miguel.app.pacific.model;

public class Square { // CASELLA
    private PacificType type;
    private int dRow; // delta riga
    private int dColumn; // delta colonna
    private int turn;

    public Square(PacificType type, int dRow, int dColumn) {
        this.type = type;
        this.dRow = dRow;
        this.dColumn = dColumn;
        this.turn = 0;
    }

    public Square() {
        this(PacificType.VUOTO, 0, 0);
    }


    public PacificType getType() {
        return type;
    }

    public Square setType(PacificType type) {
        this.type = type;
        return this;
    }

    public int getdRow() {
        return dRow;
    }

    public int getdColumn() {
        return dColumn;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
