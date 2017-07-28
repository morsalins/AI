package tic_tac_toe;

import ai.minimax.MiniMaxNode;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by morsalin on 7/19/17.
 */
public class Node implements MiniMaxNode{
    private int[][] grid = new int[3][3];
    private int utility;
    private ArrayList<Pair<Integer, Integer>> actions;

    private final int PLAYER_UTILITY_VALUE = -1;
    private final int CPU_UTILITY_VALUE = 1;
    private final int TIE_UTILITY_VALUE = 0;

    public Node(int[][] _grid) {
        this(_grid, 0, new ArrayList<Pair<Integer, Integer>>());
    }

    public Node(Node node) {
        this(node.grid, node.utility, new ArrayList<Pair<Integer, Integer>>(node.actions));
    }

    public Node(int[][] grid, int utility, ArrayList<Pair<Integer, Integer>> actions) {
        for (int i = 0; i < grid.length; i++) {
            this.grid[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        this.utility = utility;
        this.actions = actions;
    }

    @Override
    public boolean isTerminal() {
       return isWinner(GamePlay.PLAYER_TOKEN_VALUE) || isWinner(GamePlay.CPU_TOKEN_VALUE) || isTied();
    }

    @Override
    public int getTerminalUtility() {
        if (isWinner(GamePlay.PLAYER_TOKEN_VALUE)) return PLAYER_UTILITY_VALUE; //If Player wins;
        if (isWinner(GamePlay.CPU_TOKEN_VALUE)) return CPU_UTILITY_VALUE; //If CPU wins;
        return TIE_UTILITY_VALUE; //If Game tied;
    }

    @Override
    public ArrayList<MiniMaxNode> getChildrenOfMax() {
        return getChildren(GamePlay.CPU_TOKEN_VALUE);
    }

    @Override
    public ArrayList<MiniMaxNode> getChildrenOfMin() {
        return getChildren(GamePlay.PLAYER_TOKEN_VALUE);
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

    private ArrayList<MiniMaxNode> getChildren(int tokenValue) {
        ArrayList<MiniMaxNode> children = new ArrayList<MiniMaxNode>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != GamePlay.PLAYER_TOKEN_VALUE && grid[i][j] != GamePlay.CPU_TOKEN_VALUE) {
                    children.add(getChildNode(i, j, tokenValue));
                }
            }
        }
        return children;
    }

    private Node getChildNode(int row, int col, int tokenValue) {
        Node childNode = new Node(this);
        childNode.grid[row][col] = tokenValue;
        childNode.actions.add(new Pair<>(row, col));
        return childNode;
    }

    @Override
    public int getUtility() {
        return utility;
    }

    @Override
    public void setUtility(int utility) {
        this.utility = utility;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public ArrayList<Pair<Integer, Integer>> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Pair<Integer, Integer>> actions) {
        this.actions = actions;
    }
}
