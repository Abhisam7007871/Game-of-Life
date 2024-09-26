# Game of Life

The **Game of Life** is a cellular automaton devised by mathematician John Conway. It simulates the life and death of cells on an infinite two-dimensional orthogonal grid. Each cell can be in one of two states: **live** or **dead**. The state of each cell changes based on the states of its eight neighboring cells according to specific rules.

## Rules of the Game

1. Any live cell with fewer than two live neighbors dies (loneliness).
2. Any live cell with more than three live neighbors dies (overcrowding).
3. Any live cell with two or three live neighbors lives unchanged to the next generation.
4. Any dead cell with exactly three live neighbors becomes a live cell.

The initial pattern of live cells constitutes the "seed" of the system. Each generation is created by applying the above rules simultaneously to every cell, with births and deaths happening simultaneously.

## Project Structure

- **Cell Class**: Represents a cell in the grid with its coordinates and overrides methods for equality checks and string representation.
- **GameOfLife Class**: Contains the logic for calculating the next generation of cells based on the current state.
- **GridInputHandler Class**: Handles user input for live cell coordinates.
- **GameRunner Class**: Coordinates the game execution, taking input and displaying output.
- **GameOfLifeApp Class**: The entry point of the application.

## Input and Output

The input consists of the coordinates of live cells in the format `x, y`. After processing the input, the program outputs the coordinates of live cells in the next generation.

### Example Inputs and Outputs

- **Input A (Block Pattern - Still Life)**
    ```
    1, 1
    1, 2
    2, 1
    2, 2
    ```
  **Output A**
    ```
    1, 1
    1, 2
    2, 1
    2, 2
    ```

- **Input B (Boat Pattern - Still Life)**
    ```
    0, 1
    1, 0
    2, 1
    0, 2
    1, 2
    ```
  **Output B**
    ```
    0, 1
    1, 0
    2, 1
    0, 2
    1, 2
    ```

- **Input C (Blinker Pattern - Oscillator)**
    ```
    1, 1
    1, 0
    1, 2
    ```
  **Output C**
    ```
    1, 1
    0, 1
    2, 1
    ```

- **Input D (Toad Pattern - Two Phase Oscillator)**
    ```
    1, 1
    1, 2
    1, 3
    2, 2
    2, 3
    2, 4
    ```
  **Output D**
    ```
    0, 2
    1, 1
    1, 4
    2, 1
    2, 4
    3, 3
    ```

## How to Run the Project

1. Clone the repository.
2. Navigate to the project directory.
3. Compile the Java files:
   ```bash
   javac GameOfLifeApp.java
