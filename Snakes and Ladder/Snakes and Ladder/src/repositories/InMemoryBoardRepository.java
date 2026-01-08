package repositories;

import entities.Cell;
import entities.GenericCell;
import entities.LadderCell;
import entities.Player;
import entities.SnakeCell;
import java.util.HashMap;
import java.util.Map;

public class InMemoryBoardRepository implements BoardRepository {
    private Cell[][] board;
    private int rows;
    private int cols;
    private int boardSize;
    private Map<String, Integer> playerPositions;
    private Map<Integer, Cell> cellMap;

    public InMemoryBoardRepository(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.boardSize = rows * cols;
        this.board = new Cell[rows][cols];
        this.playerPositions = new HashMap<>();
        this.cellMap = new HashMap<>();
        initializeBoard();
    }

    private void initializeBoard() {
        int cellNum = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new GenericCell(cellNum);
                cellMap.put(cellNum, board[i][j]);
                cellNum++;
            }
        }
    }

    public void addSnake(int from, int to) {
        int[] position = getCellPosition(from);
        SnakeCell snakeCell = new SnakeCell(from, to);
        board[position[0]][position[1]] = snakeCell;
        cellMap.put(from, snakeCell);
    }

    public void addLadder(int from, int to) {
        int[] position = getCellPosition(from);
        LadderCell ladderCell = new LadderCell(from, to);
        board[position[0]][position[1]] = ladderCell;
        cellMap.put(from, ladderCell);
    }

    private int[] getCellPosition(int cellNum) {
        int row = (cellNum - 1) / cols;
        int col = (cellNum - 1) % cols;
        return new int[]{row, col};
    }

    @Override
    public int makeAMove(Player p, int numOnDie) {
        int currentPosition = playerPositions.getOrDefault(p.getId(), 0);
        int newPosition = currentPosition + numOnDie;

        if (newPosition > boardSize) {
            return currentPosition;
        }

        // Remove player from old position
        if (currentPosition > 0) {
            int[] oldPos = getCellPosition(currentPosition);
            board[oldPos[0]][oldPos[1]].addPlayer(null);
        }

        // Check for snake or ladder
        Cell newCell = cellMap.get(newPosition);
        if (newCell instanceof SnakeCell) {
            SnakeCell snake = (SnakeCell) newCell;
            newPosition = snake.getTarget();
            System.out.println(p.getName() + " got bitten by a snake! Moving from " + snake.getNum() + " to " + newPosition);
        } else if (newCell instanceof LadderCell) {
            LadderCell ladder = (LadderCell) newCell;
            newPosition = ladder.getTarget();
            System.out.println(p.getName() + " climbed a ladder! Moving from " + ladder.getNum() + " to " + newPosition);
        }

        // Place player at new position
        int[] newPos = getCellPosition(newPosition);
        board[newPos[0]][newPos[1]].addPlayer(p);
        playerPositions.put(p.getId(), newPosition);

        return newPosition;
    }

    @Override
    public int getPlayerPosition(Player p) {
        return playerPositions.getOrDefault(p.getId(), 0);
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    @Override
    public Cell[][] getBoard() {
        return board;
    }

    public void addPlayer(Player p) {
        playerPositions.put(p.getId(), 0);
    }
}
