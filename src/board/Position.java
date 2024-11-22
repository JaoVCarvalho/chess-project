package board;

public class Position {
    private Integer row;
    private Integer collumn;

    public Position(Integer row, Integer collumn) {
        this.row = row;
        this.collumn = collumn;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCollumn() {
        return collumn;
    }

    public void setCollumn(Integer collumn) {
        this.collumn = collumn;
    }

    @Override
    public String toString() {
        return row + ", " + collumn;
    }
}
