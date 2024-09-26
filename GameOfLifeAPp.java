import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Cell {
    int x, y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cell cell = (Cell) obj;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}

class GameOfLife {

    private Set<Cell> getNeighbors(Cell cell) {
        Set<Cell> neighbors = new HashSet<>();
        int[] directions = {-1, 0, 1};

        for (int dx : directions) {
            for (int dy : directions) {
                if (!(dx == 0 && dy == 0)) {
                    neighbors.add(new Cell(cell.x + dx, cell.y + dy));
                }
            }
        }
        return neighbors;
    }

    public Set<Cell> getNextGeneration(Set<Cell> liveCells) {
        Set<Cell> newLiveCells = new HashSet<>();
        Set<Cell> potentialCells = new HashSet<>();
        Set<Cell> checkedCells = new HashSet<>();

        for (Cell liveCell : liveCells) {
            potentialCells.add(liveCell);
            potentialCells.addAll(getNeighbors(liveCell));
        }

        for (Cell cell : potentialCells) {
            if (checkedCells.contains(cell)) continue;
            checkedCells.add(cell);

            int liveNeighbors = (int) getNeighbors(cell).stream()
                    .filter(liveCells::contains)
                    .count();

            if (liveCells.contains(cell)) {
                if (liveNeighbors == 2 || liveNeighbors == 3) {
                    newLiveCells.add(cell);
                }
            } else {
                if (liveNeighbors == 3) {
                    newLiveCells.add(cell);
                }
            }
        }
        return newLiveCells;
    }
}


class GridInputHandler {
    private final Scanner scanner;

    public GridInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public Set<Cell> collectLiveCells() {
        Set<Cell> liveCells = new HashSet<>();
        System.out.println("Enter the coordinates of live cells (format: x, y). Type 'done' to finish:");

        while (true) {
            System.out.print("Enter live cell coordinates: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) break;

            try {
                String[] parts = input.split(",");
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[1].trim());
                liveCells.add(new Cell(x, y));
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter in the format x, y (e.g., 1, 2).");
            }
        }
        return liveCells;
    }
}

class GameRunner {
    private final GameOfLife gameOfLife;
    private final GridInputHandler inputHandler;

    public GameRunner(GameOfLife gameOfLife, GridInputHandler inputHandler) {
        this.gameOfLife = gameOfLife;
        this.inputHandler = inputHandler;
    }

    public void run() {
        Set<Cell> liveCells = inputHandler.collectLiveCells();
        System.out.println("Initial Generation:");
        printLiveCells(liveCells);

        Set<Cell> nextGeneration = gameOfLife.getNextGeneration(liveCells);
        System.out.println("Next Generation:");
        printLiveCells(nextGeneration);
    }

    private void printLiveCells(Set<Cell> liveCells) {
        if (liveCells.isEmpty()) {
            System.out.println("No live cells.");
        } else {
            liveCells.forEach(System.out::println);
        }
    }
}

public class GameOfLifeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameOfLife gameOfLife = new GameOfLife();
        GridInputHandler inputHandler = new GridInputHandler(scanner);
        GameRunner gameRunner = new GameRunner(gameOfLife, inputHandler);
        gameRunner.run();
    }
}
