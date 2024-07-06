package ai.webtch.search.maze;

import lombok.EqualsAndHashCode;

import java.util.Arrays;

public class Maze {

    public enum Cell {

        EMPTY(" "),
        BLOCKED("X"),
        START("S"),
        GOAL("G"),
        PATH("*");

        private final String code;

        Cell(String c) {
            code = c;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    @EqualsAndHashCode
    public static class MazeLocation {
        public final int row;
        public final int colum;

        public MazeLocation(int row, int colum) {
            this.row = row;
            this.colum = colum;
        }
    }

    private final int rows, columns;
    private final MazeLocation start, goal;
    private Cell[][] grid;

    public Maze(int rows, int columns, MazeLocation start, MazeLocation goal, double sparseness) {

        // initialize basic instance variables
        this.rows = rows;
        this.columns = columns;
        this.start = start;
        this.goal = goal;

        // fill the grid with empty cells
        grid = new Cell[rows][columns];
        for (Cell[] row: grid) {
            Arrays.fill(row, Cell.EMPTY);
        }

        // populate the grid with blocked cells
        randomlyFill(sparseness);

        // fill the start and goal locations
        grid[start.row][start.colum] = Cell.START;
        grid[goal.row][start.colum] = Cell.GOAL;
    }

    public Maze() {
        this(
                10,
                10,
                new MazeLocation(0, 0),
                new MazeLocation(9, 9),
                0.2
        );
    }

    private void randomlyFill(double sparseness) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if(Math.random() < sparseness) {
                    grid[row][column] = Cell.BLOCKED;
                }
            }
        }
    }

    public boolean goalTest(MazeLocation ml) {
        return goal.equals(ml);
    }

    // return a nicely formatted version of the maze for printing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row: grid) {
            for (Cell cell: row) {
                sb.append(cell.toString());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
