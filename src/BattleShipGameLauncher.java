import java.util.ArrayList;
import java.util.Scanner;

public class BattleShipGameLauncher {

    private Scanner scanner = new Scanner(System.in);
    private Grid grid = new Grid(7);
    private ArrayList<Ship> shipList = new ArrayList<Ship>();
    private int shipsSunk = 0;

    public BattleShipGameLauncher() {
        initialize(7);
    }

    private void initialize(int gridSize) {
        System.out.println("Hiding ships...");
        shipList.add(new Ship("Battleship", 5));
        shipList.add(new Ship("Submarine", 3));
        shipList.add(new Ship("Frigate", 2));

        for (Ship ship : shipList) {
            ship.setCoordinates(grid);
        }
    }

    public void start() {
        boolean gameOver = false;
        while(!gameOver) {
            String coordinates = getCoordinates();
            Cell targetedCell = grid.getCell(coordinates);
            if(targetedCell.isHit()) {
                System.out.println("Coordinates previously targeted");
                continue;
            } else {
                targetedCell.setHit(true);
                Ship ship = checkForHit(targetedCell);
                if (ship != null) {
                    System.out.println("Hit");
                    if(ship.checkIfSunk()) {
                        System.out.println(ship.getShipClass() + " Sunk!");
                        shipsSunk++;
                        gameOver = checkGameOver();
                    }
                } else {
                    System.out.println("Miss");
                }
            }

        }
        System.out.println("Game over, you won!");
    }

    private boolean checkGameOver() {
        return shipsSunk == shipList.size();
    }

    private Ship checkForHit(Cell targetedCell) {
        for (Ship ship: shipList) {
            if(ship.checkIfHit(targetedCell)) {
                return ship;
            }
        }
        return null;
    }

    private String getCoordinates() {
        String coordinates;
        boolean invalidCoordinates = true;

        System.out.print("Enter coordinates\n:");
        coordinates = scanner.nextLine();

        while (!grid.validateCoordinates(coordinates)) {
            System.out.print("Invalid coordinates, enter new coordinates\n:");
            coordinates = scanner.nextLine();
        }

        return coordinates;
    }
}
