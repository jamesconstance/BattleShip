public class Cell {
    private boolean isHit;
    private String coordinates;
    private boolean isOccupied;

    public Cell(String coordinates) {
        this.isHit = false;
        this.coordinates = coordinates;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
