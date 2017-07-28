package ai.minimax;

import java.util.ArrayList;

/**
 * Created by morsalin on 7/24/17.
 */
public class MiniMaxSolution {
    //Initial utility value for each state
    private final int MIN_UTILITY_VALUE = -100;
    private final int MAX_UTILITY_VALUE = 100;

    //Initial value for maximizer alpha and minimizer beta;
    private final int INITIAL_ALPHA_VALUE = -100;
    private final int INITIAL_BETA_VALUE = 100;

    public MiniMaxSolution() {
    }

    public MiniMaxNode alphaBetaMiniMaxSolution(MiniMaxNode root) {
        return maxBestNode(root, INITIAL_ALPHA_VALUE, INITIAL_BETA_VALUE);
    }

    private MiniMaxNode maxBestNode(MiniMaxNode node, int alpha, int beta) {
        if (node.isTerminal()) {
            node.setUtility(node.getTerminalUtility());
            return node;
        }

        ArrayList<MiniMaxNode> children = node.getChildrenOfMax();
        MiniMaxNode bestChild = children.get(0); // Let assume the 1st child will give best solution node.
        bestChild.setUtility(MIN_UTILITY_VALUE);

        for (MiniMaxNode child : children) {
            bestChild = getMaxNode(bestChild, minBestNode(child, alpha, beta));
            alpha = Math.max(alpha, bestChild.getUtility());
            if (beta <= alpha) return bestChild;
        }
        return bestChild;
    }

    private MiniMaxNode minBestNode(MiniMaxNode node, int alpha, int beta) {
        if (node.isTerminal()) {
            node.setUtility(node.getTerminalUtility());
            return node;
        }

        ArrayList<MiniMaxNode> children = node.getChildrenOfMin();
        MiniMaxNode bestChild = children.get(0); // Let assume the 1st child will give best solution node.
        bestChild.setUtility(MAX_UTILITY_VALUE);

        for (MiniMaxNode child : children) {
            bestChild = getMinNode(bestChild, maxBestNode(child, alpha, beta));
            beta = Math.min(beta, bestChild.getUtility());
            if (beta <= alpha) return bestChild;
        }
        return bestChild;
    }

    private MiniMaxNode getMaxNode(MiniMaxNode child1, MiniMaxNode child2) {
        return child1.getUtility() >= child2.getUtility() ? child1 : child2;
    }

    private MiniMaxNode getMinNode(MiniMaxNode child1, MiniMaxNode child2) {
        return child1.getUtility() <= child2.getUtility() ? child1 : child2;
    }
}
