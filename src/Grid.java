import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private static final Random random = new Random();

    private final ArrayList<Cell> cellArrayList = new ArrayList<>();
    private final int gridSize;

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        generateCellCoordinates(gridSize);
    }

    public Cell getCell(String coordinates) {
        for (Cell cell : cellArrayList) {
            if (cell.getCoordinates().equalsIgnoreCase(coordinates)) {
                return cell;
            }
        }
        return null;
    }

    public boolean validateCoordinates(String coordinates) {
        boolean validCoordinates = false;
        for (Cell cell : cellArrayList) {
            if (cell.getCoordinates().equalsIgnoreCase(coordinates)) {
                validCoordinates = true;
                break;
            }
        }
        return validCoordinates;
    }

    private void generateCellCoordinates(int gridSize) {
        ArrayList<String> cellCoordinatesList = getCellCoordinates(gridSize);
        for (String cellCoordinates : cellCoordinatesList) {
            Cell newCell = new Cell(cellCoordinates);
            cellArrayList.add(newCell);
        }
    }

    private ArrayList<String> getCellCoordinates(int gridSize) {
        ArrayList<String> cellCoordinatesList = new ArrayList<>();
        StringBuilder cellCoordinates = new StringBuilder();
        int maxRow = 'A' + gridSize;
        for(char row = 'A'; row < maxRow; row++) {
            for (int column = 0; column < gridSize; column++) {
                cellCoordinatesList.add((cellCoordinates.append(row).append(column+1)).toString());
                cellCoordinates.delete(0,cellCoordinates.length());
            }
        }
        return cellCoordinatesList;
    }

    public ArrayList<Cell> getRandomVerticalCoordinateList(int length) {
        ArrayList<Cell> randomVerticalCoordinateList = new ArrayList<>();
        int maxIndex = (int) Math.pow(gridSize, 2) - ((length - 1) * gridSize);

        boolean isUnoccupiedCellArray = false;
        while(!isUnoccupiedCellArray) {
            int startingIndex = random.nextInt(maxIndex);
            int index = startingIndex;

            Cell cell = cellArrayList.get(startingIndex);
            randomVerticalCoordinateList.add(cell);

            while (randomVerticalCoordinateList.size() < length) {
                cell = cellArrayList.get(index + gridSize);
                randomVerticalCoordinateList.add(cell);
                index += gridSize;
            }

            isUnoccupiedCellArray = checkCellsAreUnoccupied(randomVerticalCoordinateList);
            if (isUnoccupiedCellArray) {
                occupyCells(randomVerticalCoordinateList);
            } else {
                randomVerticalCoordinateList.clear();
            }
        }

        return randomVerticalCoordinateList;
    }

    public ArrayList<Cell> getRandomHorizontalCoordinates(int length) {
        ArrayList<Cell> randomHorizontalCoordinateList = new ArrayList<>();

        boolean isUnoccupiedCellArray = false;
        while(!isUnoccupiedCellArray) {
            int startingIndex = random.nextInt(gridSize - length) + random.nextInt(gridSize) * gridSize;

            for (int index = startingIndex; index < startingIndex + length; index++) {
                Cell cell = cellArrayList.get(index);
                randomHorizontalCoordinateList.add(cell);
            }

            isUnoccupiedCellArray = checkCellsAreUnoccupied(randomHorizontalCoordinateList);
            if (isUnoccupiedCellArray) {
                occupyCells(randomHorizontalCoordinateList);
            } else {
                randomHorizontalCoordinateList.clear();
            }
        }

        return randomHorizontalCoordinateList;
    }

    private void occupyCells(ArrayList<Cell> cellList) {
        for (Cell cell : cellList) {
            cell.setOccupied(true);
        }
    }

    private boolean checkCellsAreUnoccupied(ArrayList<Cell> cellList) {
        boolean isUnoccupied = true;
        for (Cell cell : cellList) {
            if (cell.isOccupied()) {
                isUnoccupied = false;
                break;
            }
        }
        return isUnoccupied;
    }

}
