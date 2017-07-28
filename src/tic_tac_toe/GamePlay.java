package tic_tac_toe;

import ai.minimax.MiniMaxSolution;
import javafx.util.Pair;

/**
 * Created by morsalin on 7/19/17.
 */
public class GamePlay {

    public int[][] grid= new int[][]{{-1, -1, -1},
                                     {-1, -1, -1},
                                     {-1, -1, -1} };

    public static final int PLAYER_TOKEN_VALUE = 1;
    public static final int CPU_TOKEN_VALUE = 0;

    public static final int PLAYER_UTILITY_VALUE = -1;
    public static final int CPU_UTILITY_VALUE = 1;
    public static final int TIE_UTILITY_VALUE = 0;

    private final String PLAYER_WIN_MESSAGE = "Player Won!!!";
    private final String CPU_WIN_MESSAGE = "CPU Won!!!";
    private final String GAME_TIE_MESSAGE = "Game TIED!!!";

    public final String TOKENS[] = {"O", "X"};

    public MiniMaxSolution solution = new MiniMaxSolution();

    public GamePlay() {
    }

    public Pair<Integer, Integer> performCPUMove() {
        Node nextMove = getNextMove();
        if (nextMove.getActions().size() > 0) {
            int row = nextMove.getActions().get(0).getKey();
            int col = nextMove.getActions().get(0).getValue();
            updateGrid(row, col, CPU_TOKEN_VALUE);
            return new Pair<>(row, col); // For UI update.
        }
        return null;
    }

    public void performPlayerMove(int row, int col) {
        updateGrid(row, col, PLAYER_TOKEN_VALUE);
    }

    private Node getNextMove() {
        Node root = new Node(grid);
        return (Node)(solution.alphaBetaMiniMaxSolution(root));
    }

    private void updateGrid(int row, int col, int tokenValue) {
        grid[row][col] = tokenValue;
    }

    public boolean isResultDecided() {
        if (getResult() == null) return false;
        return true;
    }

    public String getResult() {
        if (isWinner(GamePlay.PLAYER_TOKEN_VALUE)) return PLAYER_WIN_MESSAGE; //If Player Wins.
        if (isWinner(GamePlay.CPU_TOKEN_VALUE)) return CPU_WIN_MESSAGE; //If CPU Wins.
        if (isTied()) return GAME_TIE_MESSAGE; //If Game TIED.
        return null; //Result not Decided.
    }

    private boolean isWinner(int tokenValue) {
        //checking for winner. row col checking
        for (int i = 0; i < 3; i++) {
            if (isAllEqual(grid[i][0], grid[i][1], grid[i][2], tokenValue)
                    || isAllEqual(grid[0][i], grid[1][i], grid[2][i], tokenValue)) {
                return true;
            }
        }
        //checking for winner. diagonal checking
        return isAllEqual(grid[0][0], grid[1][1], grid[2][2], tokenValue)
                || isAllEqual(grid[0][2], grid[1][1], grid[2][0], tokenValue);
    }

    private boolean isTied() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != GamePlay.PLAYER_TOKEN_VALUE && grid[i][j] != GamePlay.CPU_TOKEN_VALUE) {
                    return false; //result not decided
                }
            }
        }
        return true;
    }

    private boolean isAllEqual(int a, int b, int c, int d) {
        return a == d && b == d && c == d;
    }

    public void resetGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = -1;
            }
        }
    }

    public void showResult() {
        System.out.println(getResult());
    }

    public void showGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] >= 0) System.out.print(TOKENS[grid[i][j]]);
                else System.out.print(" ");
                if (j < 2) System.out.print(" |");
            }
            System.out.println("");
            if (i < 2) System.out.println("--|--|--");
        }
        System.out.println();
    }
}
