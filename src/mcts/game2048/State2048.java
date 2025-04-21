package game2048;

import core.*;

import java.util.*;

public class State2048 implements State<Game2048> {
    private final int[][] grid;
    private final RandomState randomState;
    private final Game2048 game;

    public State2048() {
        this.grid = new int[4][4];
        this.randomState = new RandomState(4);
        this.game = new Game2048();
        spawnTile();
        spawnTile();
    }

    public State2048(int[][] grid, RandomState randomState, Game2048 game) {
        this.grid = grid;
        this.randomState = randomState;
        this.game = game;
    }

    @Override
    public Game2048 game() {
        return game;
    }

    @Override
    public boolean isTerminal() {
        return getAvailableMoves().isEmpty();
    }

    @Override
    public int player() {
        return 0; // Single player
    }

    @Override
    public Optional<Integer> winner() {
        return Optional.empty();
    }

    @Override
    public Random random() {
        return new Random();
    }

    @Override
    public Collection<Move<Game2048>> moves(int player) {
        List<Move<Game2048>> moves = new ArrayList<>();
        for (Move2048 move : getAvailableMoves()) {
            moves.add(move);
        }
        return moves;
    }

    @Override
    public State<Game2048> next(Move<Game2048> move) {
        return applyMove((Move2048) move);
    }

    public List<Move2048> getAvailableMoves() {
        List<Move2048> moves = new ArrayList<>();
        if (canMoveUp()) moves.add(Move2048.UP);
        if (canMoveDown()) moves.add(Move2048.DOWN);
        if (canMoveLeft()) moves.add(Move2048.LEFT);
        if (canMoveRight()) moves.add(Move2048.RIGHT);
        return moves;
    }

    public State2048 applyMove(Move2048 move) {
        int[][] newGrid = deepCopyGrid(grid);
        boolean moved = switch (move) {
            case UP -> moveUp(newGrid);
            case DOWN -> moveDown(newGrid);
            case LEFT -> moveLeft(newGrid);
            case RIGHT -> moveRight(newGrid);
        };

        if (!moved) return this;

        spawnTile(newGrid);
        return new State2048(newGrid, randomState.next(), game);
    }

    public double evaluate() {
        int maxTile = getMaxTile();
        return Math.log(maxTile);
    }

    public int getMaxTile() {
        int max = 0;
        for (int[] row : grid)
            for (int val : row)
                max = Math.max(max, val);
        return max;
    }

    public int[][] getGrid() {
        return grid;
    }

    // -------------------- GUI-accessible methods --------------------

    public boolean moveUp() {
        return moveUp(grid);
    }

    public boolean moveDown() {
        return moveDown(grid);
    }

    public boolean moveLeft() {
        return moveLeft(grid);
    }

    public boolean moveRight() {
        return moveRight(grid);
    }

    public boolean isGameOver() {
        return isTerminal();
    }

    public void spawnTile() {
        spawnTile(this.grid);
    }

    // --------------------- Internal Helpers -------------------------

    private boolean moveUp(int[][] grid) {
        return move(grid, 0, 1);
    }

    private boolean moveDown(int[][] grid) {
        return move(grid, 0, -1);
    }

    private boolean moveLeft(int[][] grid) {
        return move(grid, 1, 1);
    }

    private boolean moveRight(int[][] grid) {
        return move(grid, 1, -1);
    }

    private boolean move(int[][] grid, int axis, int dir) {
        boolean moved = false;
        for (int i = 0; i < 4; i++) {
            int[] line = axis == 0 ? getColumn(grid, i) : grid[i];
            int[] merged = mergeLine(line, dir);
            if (!Arrays.equals(line, merged)) {
                if (axis == 0) setColumn(grid, i, merged);
                else grid[i] = merged;
                moved = true;
            }
        }
        return moved;
    }

    private int[] mergeLine(int[] line, int dir) {
        int[] result = new int[4];
        int pos = dir > 0 ? 0 : 3;
        int step = dir > 0 ? 1 : -1;
        int last = 0;

        for (int i = (dir > 0 ? 0 : 3); i >= 0 && i < 4; i += step) {
            if (line[i] == 0) continue;
            if (line[i] == last) {
                result[pos - step] *= 2;
                last = 0;
            } else {
                result[pos] = line[i];
                last = line[i];
                pos += step;
            }
        }
        return result;
    }

    private void spawnTile(int[][] grid) {
        List<int[]> empty = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (grid[i][j] == 0) empty.add(new int[]{i, j});
        if (empty.isEmpty()) return;
        int[] pos = empty.get(random().nextInt(empty.size()));
        grid[pos[0]][pos[1]] = random().nextDouble() < 0.9 ? 2 : 4;
    }

    private int[][] deepCopyGrid(int[][] grid) {
        int[][] newGrid = new int[4][4];
        for (int i = 0; i < 4; i++)
            System.arraycopy(grid[i], 0, newGrid[i], 0, 4);
        return newGrid;
    }

    private int[] getColumn(int[][] grid, int col) {
        int[] column = new int[4];
        for (int i = 0; i < 4; i++)
            column[i] = grid[i][col];
        return column;
    }

    private void setColumn(int[][] grid, int col, int[] column) {
        for (int i = 0; i < 4; i++)
            grid[i][col] = column[i];
    }

    private boolean canMoveUp() {
        return canMove(0, 1);
    }

    private boolean canMoveDown() {
        return canMove(0, -1);
    }

    private boolean canMoveLeft() {
        return canMove(1, 1);
    }

    private boolean canMoveRight() {
        return canMove(1, -1);
    }

    private boolean canMove(int axis, int dir) {
        for (int i = 0; i < 4; i++) {
            int[] line = axis == 0 ? getColumn(grid, i) : grid[i];
            for (int j = (dir > 0 ? 1 : 2); j >= 0 && j < 3; j += dir) {
                if (line[j] == 0 && line[j - dir] != 0) return true;
                if (line[j] != 0 && line[j] == line[j - dir]) return true;
            }
        }
        return false;
    }
}
