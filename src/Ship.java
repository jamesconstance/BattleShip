import java.util.ArrayList;
import java.util.Random;

public class Ship {

    enum Orientation {
        HORIZONTAL,
        VERTICAL,
    }
    private String shipClass;
    private int length;
    private ArrayList<Cell> coordinates = new ArrayList<>();
    private int numHits;

    public Ship(String shipClass, int length) {
        this.shipClass = shipClass;
        this.length = length;
    }

    public boolean checkIfHit(Cell targetedCell) {
        for (Cell cell: coordinates) {
            if (cell == targetedCell) {
                numHits++;
                return true;
            }
        }
        return false;
    }

    public boolean checkIfSunk() {
        return numHits == length;
    }

    public String getShipClass() {
        return shipClass;
    }

    public void setCoordinates(Grid grid) {
        coordinates = getRandomCoordinates(length, grid);
    }

    private ArrayList<Cell> getRandomCoordinates(int length, Grid grid) {
        Orientation orientation = getRandomOrientation();
        if (orientation == Orientation.VERTICAL) {
            return grid.getRandomVerticalCoordinateList(length);
        } else {
            return grid.getRandomHorizontalCoordinates(length);
        }
    }

    private Orientation getRandomOrientation() {
        Random random = new Random();
        return Orientation.values()[random.nextInt(2)];
    }
    
}
